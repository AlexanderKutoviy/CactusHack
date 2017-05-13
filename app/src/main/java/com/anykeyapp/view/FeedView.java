package com.anykeyapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.anykeyapp.BinApplication;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.presenter.FeedPresenter;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;

import javax.inject.Inject;

public class FeedView extends RelativeLayout implements RouterOwner {

    private Router router;
    private Context context;

    @Inject
    FeedPresenter feedPresenter;

    public FeedView(Context context) {
        super(context);
        DaggerFeedView_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);
        this.context = context;
    }

    public FeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public FeedView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        void inject(FeedView view);
    }
}
