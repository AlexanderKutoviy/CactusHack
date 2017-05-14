package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.activity.OcrCaptureActivity;
import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.Category;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.router.Router;
import com.anykeyapp.view.AddItemView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddItemPresenter {

    private final String TAG = AddItemPresenter.class.getSimpleName();

    private AddItemView addItemView;
    private Router router;

    private Context context;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private ProductItem productItem;

    public AddItemPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        this.context = context;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public void attachView(AddItemView addItemView) {
        this.addItemView = addItemView;
        productItem = new ProductItem();
    }

    public void detachView() {
        addItemView = null;
        router = null;
    }

    public void onCalendarClicked(Calendar calendar) {
        addItemView.viewExpDate(calendar.getTime());
        productItem.expirationDate = calendar.getTimeInMillis();
    }

    public void categoryClicked(long id) {
        productItem.id = id;
    }

    public void saveProduct(String name, String date) {
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy");
        try {
            Date dDate = format.parse(date);
            productItem.expirationDate = dDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productItem.name = name;
        productDao.create(productItem);
    }

    public void setData() {
        List<Category> categories = categoryDao.read();
        addItemView.displayCategories(categories);
        if (productItem != null) {
            addItemView.displayData(productItem);
        } else {
            return;
        }
    }

    public void scanBtnClicked() {
        OcrCaptureActivity.start(context);
    }
}
