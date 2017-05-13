package com.anykeyapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;

public class FeedView extends RelativeLayout implements RouterOwner {

    public FeedView(Context context) {
        super(context);
//        this.context = context;
    }

    public FeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        this.context = context;
    }

    public FeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this.context = context;
    }

    @Override
    public void injectRouter(Router router) {

    }
}
