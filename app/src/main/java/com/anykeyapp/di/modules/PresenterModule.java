package com.anykeyapp.di.modules;

import android.content.Context;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.presenter.AddItemPresenter;
import com.anykeyapp.presenter.FeedPresenter;
import com.anykeyapp.presenter.ToBuyPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    FeedPresenter provideFeedPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        return new FeedPresenter(context, categoryDao, productDao);
    }

    @Singleton
    @Provides
    ToBuyPresenter provideToBuyPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        return new ToBuyPresenter(context, categoryDao, productDao);
    }

    @Singleton
    @Provides
    AddItemPresenter provideAddItemPresenter(Context context, CategoryDao categoryDao, ProductDao productDao) {
        return new AddItemPresenter(context, categoryDao, productDao);
    }
}