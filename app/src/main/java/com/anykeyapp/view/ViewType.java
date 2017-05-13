package com.anykeyapp.view;

import com.annimon.stream.Stream;
import com.anykeyapp.R;
import com.anykeyapp.view.screen.FeedScreen;

import java.util.Arrays;

public enum ViewType {

    FEED(FeedScreen.class, R.layout.flow_feed_screen);

    public final int viewLayoutId;
    public final Class<? extends Screen> screenClass;

    ViewType(Class<? extends Screen> screenClass, int viewLayoutId) {
        this.screenClass = screenClass;
        this.viewLayoutId = viewLayoutId;
    }

    public static ViewType fromScreenClass(Class<? extends Screen> screenClass) {
        return Stream.of(Arrays.asList(ViewType.values()))
                .filter(viewType -> viewType.screenClass.equals(screenClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unrecognized screen " + screenClass.getSimpleName()));
    }
}