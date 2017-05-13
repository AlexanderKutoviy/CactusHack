package com.anykeyapp.view.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.anykeyapp.R;
import com.anykeyapp.router.Router;
import com.anykeyapp.view.Screen;
import com.anykeyapp.view.screen.FeedScreen;
import com.anykeyapp.view.screen.ToByScreen;

import java.util.Arrays;
import java.util.List;

import rx.functions.Action1;

public class DrawerView extends ListView {

    private Router router;
    public DrawerLayout drawerLayout;

    public DrawerView(Context context) {
        super(context);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(DrawerLayout drawerLayout, Router router/*, LogoutPresenter logoutPresenter*/) {
        this.router = router;
        this.drawerLayout = drawerLayout;
//        this.logoutPresenter = logoutPresenter;
//        this.prefsHelper = new PrefsHelper(getContext());
        initDrawerItems();
    }

    private void initDrawerItems() {
        List<String> itemNames = Stream.of(Arrays.asList(DrawerOption.values()))
                .map(DrawerOption::getItemStringId)
                .map(getResources()::getString)
                .collect(Collectors.toList());

//        if (!prefsHelper.hasDebugRole()) {
//            String DEBUG = "Debug";
//            itemNames.remove(DEBUG);
//            String USAGE = "Usage";
//            itemNames.remove(USAGE);
//        }

        ArrayAdapter<String> drawerAdapter =
                new ArrayAdapter<>(getContext(), R.layout.drawer_list_item, itemNames);
        setAdapter(drawerAdapter);
        setOnItemClickListener(this::onDrawerClick);
    }

    private void onDrawerClick(AdapterView<?> parent, View view, int position, long id) {
        drawerLayout.closeDrawer(DrawerView.this);
        DrawerOption.values()[position].action.call(this);
    }

    private static Action1<DrawerView> createScreenHandler(Screen screen) {
        return drawer -> drawer.router.goTo(screen);
    }

    private enum DrawerOption {
        TO_BY(R.string.drawer_to_by_option, DrawerView.createScreenHandler(new ToByScreen())),
        FEED(R.string.drawer_feed_option, DrawerView.createScreenHandler(new FeedScreen()));

        private int itemStringId;
        private Action1<DrawerView> action;

        DrawerOption(int itemStringId, Action1<DrawerView> action) {
            this.action = action;
            this.itemStringId = itemStringId;
        }

        int getItemStringId() {
            return itemStringId;
        }
    }
}
