package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.router.Router;
import com.anykeyapp.view.ToBuyView;

import java.util.List;

public class ToBuyPresenter {

    private ToBuyView toBuyView;
    private Context context;
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private Router router;

    private ProductItem productItem;

    public ToBuyPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        this.context = context;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public void attachView(ToBuyView feedView) {
        this.toBuyView = feedView;
        productItem = new ProductItem();
    }

    public void detachView() {
        toBuyView = null;
        router = null;
    }

    public void setData() {
        List<ProductItem> productItems = productDao.getExpiredOrOver();
        if (productItems != null) {
            toBuyView.displayData(productItems);
        } else {
            return;
        }
    }
}
