package com.anykeyapp.view.screen;

import com.anykeyapp.view.Screen;

public class FeedScreen implements Screen {
    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof FeedScreen;
    }
}