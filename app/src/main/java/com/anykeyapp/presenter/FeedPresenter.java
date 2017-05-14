package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.router.Router;
import com.anykeyapp.view.FeedView;

import java.util.List;

public class FeedPresenter {

    private FeedView feedView;
    private Context context;
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private Router router;

    private ProductItem productItem;

    public FeedPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        this.context = context;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public void attachView(FeedView feedView) {
        this.feedView = feedView;
        productItem = new ProductItem();
    }

    public void detachView() {
        feedView = null;
        router = null;
    }

    public void setData() {
        List<ProductItem> productItems = productDao.read();
        if (productItems != null) {
            feedView.displayData(productItems);
        } else {
            return;
        }
    }
}