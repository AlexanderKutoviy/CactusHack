package com.anykeyapp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.anykeyapp.BinApplication;
import com.anykeyapp.R;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.presenter.FeedPresenter;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;
import com.anykeyapp.view.adapters.ProductsRecyclerAdapter;
import com.anykeyapp.view.drawer.DrawerView;
import com.anykeyapp.view.screen.AddItemScreen;
import com.febaisi.CustomTextView;

import java.util.List;

import javax.inject.Inject;

public class FeedView extends DrawerLayout implements RouterOwner {

    private Router router;
    private Context context;

    @Inject
    FeedPresenter feedPresenter;
    private RelativeLayout addItemBtn;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recycler;
    private ProductsRecyclerAdapter adapter;
    private CustomTextView noProductsMsg;

    public FeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerFeedView_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);
        this.context = context;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        feedPresenter.attachView(this);
        initViews();
        feedPresenter.setData();
    }

    @Override
    public void onDetachedFromWindow() {
        feedPresenter.detachView();
        super.onDetachedFromWindow();
    }

    private void initViews() {
        DrawerView drawer = (DrawerView) findViewById(R.id.left_drawer);
        findViewById(R.id.menu_item).setOnClickListener(v -> drawer.drawerLayout.openDrawer(Gravity.LEFT));

        addItemBtn = (RelativeLayout) findViewById(R.id.add_item_btn);
        addItemBtn.setOnClickListener(btn -> router.goTo(new AddItemScreen()));

        recycler = (RecyclerView) findViewById(R.id.product_recycler);
        noProductsMsg = (CustomTextView) findViewById(R.id.no_products_msg);
    }

    @Override
    public void injectRouter(Router router) {
        this.router = router;
        ((DrawerView) findViewById(R.id.left_drawer)).init(this, router);
    }

    public void displayData(List<ProductItem> productItems) {
        if (!productItems.isEmpty()) {
            noProductsMsg.setVisibility(GONE);
            recycler.setVisibility(VISIBLE);
            adapter = new ProductsRecyclerAdapter(context, productItems, feedPresenter);
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(context));
        } else {
            noProductsMsg.setVisibility(VISIBLE);
            recycler.setVisibility(GONE);
        }
    }

    @dagger.Component(dependencies = AppComponent.class)
    @ApplicationScope
    interface Component {
        void inject(FeedView view);
    }
}
