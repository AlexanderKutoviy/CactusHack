package com.anykeyapp.router;

import com.anykeyapp.view.Screen;

public interface Router {
    void goTo(Screen screen);

    void exitPlanner();
}