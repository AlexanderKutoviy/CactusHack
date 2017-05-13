package com.anykeyapp.di.modules;

import android.content.Context;

import com.anykeyapp.presenter.FeedPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    FeedPresenter provideFeedPresenter(Context context) {
        return new FeedPresenter(context);
    }
}