package com.anykeyapp.di.modules;

import android.content.Context;

import com.anykeyapp.controller.ExpirationController;
import com.anykeyapp.dao.ProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {

    @Singleton
    @Provides
    ExpirationController provideExpirationController(Context context, ProductDao productDao) {
        return new ExpirationController(context, productDao);
    }
}