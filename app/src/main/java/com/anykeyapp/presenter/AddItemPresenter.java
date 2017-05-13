package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
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

    public AddItemPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        this.context = context;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public void attachView(AddItemView addItemView) {
        this.addItemView = addItemView;
    }

    public void detachView() {
        addItemView = null;
        router = null;
    }

    public void onCalendarClicked(Calendar calendar) {
        addItemView.viewExpDate(calendar.getTime());
    }
}
