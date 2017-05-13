package com.anykeyapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.anykeyapp.BinApplication;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.presenter.AddItemPresenter;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;

import javax.inject.Inject;

public class AddItemView extends RelativeLayout implements RouterOwner {

    private Router router;
    private Context context;

    @Inject
    AddItemPresenter presenter;

    public AddItemView(Context context) {
        super(context);
        DaggerAddItemView_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);
        this.context = context;
    }

    public AddItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public AddItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void injectRouter(Router router) {
        this.router = router;
    }

    @dagger.Component(dependencies = AppComponent.class)
    @ApplicationScope
    interface Component {
        void inject(AddItemView view);
    }
}