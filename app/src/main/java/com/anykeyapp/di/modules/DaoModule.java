package com.anykeyapp.di.modules;

import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.dao.ProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    @Singleton
    @Provides
    CategoryDao provideCategoryDao() {
        return new CategoryDao();
    }

    @Singleton
    @Provides
    ProductDao provideProductDao() {
        return new ProductDao();
    }
}