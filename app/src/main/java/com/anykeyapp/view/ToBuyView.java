package com.anykeyapp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;

import com.anykeyapp.BinApplication;
import com.anykeyapp.R;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;
import com.anykeyapp.view.drawer.DrawerView;

public class ToBuyView extends DrawerLayout implements RouterOwner {

    private Router router;
    private Context context;

    public ToBuyView(Context context) {
        super(context);
        DaggerToBuyView_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);
        this.context = context;
    }

    public ToBuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ToBuyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initViews();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void initViews() {
        DrawerView drawer = (DrawerView) findViewById(R.id.left_drawer);
        findViewById(R.id.menu_item).setOnClickListener(v -> drawer.drawerLayout.openDrawer(Gravity.LEFT));
    }

    @Override
    public void injectRouter(Router router) {
        this.router = router;
        ((DrawerView) findViewById(R.id.left_drawer)).init(this, router);
    }

    @dagger.Component(dependencies = AppComponent.class)
    @ApplicationScope
    interface Component {
        void inject(ToBuyView view);
    }
}