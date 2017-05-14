package com.anykeyapp.di;

import android.content.Context;
import android.content.res.Resources;

import com.anykeyapp.controller.ExpirationController;
import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.di.modules.ApplicationModule;
import com.anykeyapp.di.modules.ControllerModule;
import com.anykeyapp.di.modules.DaoModule;
import com.anykeyapp.di.modules.PresenterModule;
import com.anykeyapp.di.modules.UtilsModule;
import com.anykeyapp.presenter.AddItemPresenter;
import com.anykeyapp.presenter.FeedPresenter;
import com.anykeyapp.presenter.ToBuyPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class, DaoModule.class,
        ControllerModule.class, PresenterModule.class, UtilsModule.class})
public interface AppComponent {

    //APP
    Context getContext();

    Resources getResources();

    //DAO
    CategoryDao getCategoryDao();

    ProductDao getProductDao();

    //CONTROLLER
    ExpirationController getExpirationController();


    //PRESENTER
    FeedPresenter getFeedPresenter();

    ToBuyPresenter getToBuyPresenter();

    AddItemPresenter getAddItemPresenter();

    //UTIL
}