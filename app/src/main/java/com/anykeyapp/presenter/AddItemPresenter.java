package com.anykeyapp.presenter;

import android.content.Context;

import com.anykeyapp.activity.OcrCaptureActivity;
import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.Category;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.router.Router;
import com.anykeyapp.util.BitSchedulers;
import com.anykeyapp.view.AddItemView;
import com.anykeyapp.view.screen.FeedScreen;

import java.util.Calendar;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class AddItemPresenter {

    private final String TAG = AddItemPresenter.class.getSimpleName();

    private AddItemView addItemView;
    private Router router;

    private Context context;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private ProductItem productItem;
    private ProductItem savedState = new ProductItem();

    private CompositeSubscription subscription;

    public AddItemPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        this.context = context;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public void attachView(AddItemView addItemView, Router router) {
        this.addItemView = addItemView;
        this.router = router;
        productItem = new ProductItem();
        subscription = new CompositeSubscription();
        subscription.add(OcrCaptureActivity.dateObservable
                .subscribeOn(BitSchedulers.io())
                .observeOn(BitSchedulers.main())
                .subscribe(this::dateScanned));
    }

    public void detachView() {
        addItemView = null;
        productItem = null;
        router = null;
        subscription.unsubscribe();
    }

    public void setData() {
        List<Category> categories = categoryDao.read();
        addItemView.displayCategories(categories);
        if (savedState != null) {
            productItem = new ProductItem(savedState);
            addItemView.displayData(productItem);
        }
    }

    public void onCalendarClicked(Calendar calendar) {
        addItemView.viewExpDate(calendar.getTime());
        productItem.expirationDate = calendar.getTimeInMillis();
        savedState.expirationDate = calendar.getTimeInMillis();
    }

    public void dateScanned(String date) {
        addItemView.viewExpDate(date);
    }

    public void categoryClicked(long id, String name) {
        productItem.categoryId = id;
        savedState.categoryId = id;
        productItem.name = name;
        savedState.name = name;
        addItemView.setClickedId(id, name);
    }

    public void scanBtnClicked() {
        saveState();
        OcrCaptureActivity.start(context);
    }

    public void saveProduct() {
        productItem.name = addItemView.getName();
        productItem.expirationDate = addItemView.getDate();
        if (productItem.categoryId == null || productItem.name == null || productItem.expirationDate == 0) {
            return;
        }
        productDao.create(productItem);
        router.goTo(new FeedScreen());
    }

    public void saveState() {
        savedState.name = addItemView.getName();
        savedState.expirationDate = addItemView.getDate();
    }
}
