package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.router.Router;
import com.anykeyapp.view.AddItemView;

import java.util.Calendar;

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

    public void nameEntered(String name) {
        productItem.name = name;
    }

    public void saveProduct() {
        productDao.create(productItem);
    }

    public void setData() {
        if (productItem != null) {
            addItemView.displayData(productItem);
        } else {
            return;
        }
    }
}
