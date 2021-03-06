package com.anykeyapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anykeyapp.BinApplication;
import com.anykeyapp.R;
import com.anykeyapp.controller.ExpirationController;
import com.anykeyapp.dao.CategoryDao;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;
import com.anykeyapp.view.Screen;
import com.anykeyapp.view.ViewType;
import com.anykeyapp.view.screen.FeedScreen;

import javax.inject.Inject;

import br.com.safety.locationlistenerhelper.core.LocationTracker;
import flow.Direction;
import flow.Dispatcher;
import flow.Flow;
import flow.History;
import flow.Traversal;
import flow.TraversalCallback;

public class MainActivity extends AppCompatActivity implements Dispatcher {

    private final String TAG = MainActivity.class.getSimpleName();

    private View currentView;
    private Router flowRouter = new FlowRouter();

    private LocationTracker locationTracker;

    @Inject
    CategoryDao categoryDao;
    @Inject
    ExpirationController expirationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivity_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);

//        categoryDao.create(new Category("Milk"));
//        categoryDao.create(new Category("Chicken"));
//        categoryDao.create(new Category("Steak"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LocationTracker("my.action")
                .setInterval(50000)
                .setGps(true)
                .setNetWork(false)
                .start(getBaseContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        locationTracker.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .dispatcher(this)
                .defaultKey(new FeedScreen())
                .install();
        super.attachBaseContext(baseContext);
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Screen newScreen = traversal.destination.top();
        ViewGroup frame = (ViewGroup) findViewById(R.id.main_frame);

        if (traversal.origin != null) {
            if (frame.getChildCount() > 0) {
                traversal.getState(traversal.origin.top()).save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }
        ViewType viewType = ViewType.fromScreenClass(newScreen.getClass());
        View incomingView = LayoutInflater.from(traversal.createContext(newScreen, this))
                .inflate(viewType.viewLayoutId, frame, false);
        ((RouterOwner) incomingView).injectRouter(flowRouter);
        traversal.getState(traversal.destination.top()).restore(incomingView);
        frame.removeAllViews();
        frame.addView(incomingView);
        callback.onTraversalCompleted();

        currentView = incomingView;
    }

    @Override
    public void onBackPressed() {
        Flow flow = Flow.get(this);
        if (flow.getHistory().size() == 1) {
            super.onBackPressed();
        } else {
            if (flow.getHistory().iterator().next() instanceof FeedScreen) {
                super.onBackPressed();
            } else {
                flow.replaceTop(new FeedScreen(), Direction.FORWARD);
            }
        }
    }

    private class FlowRouter implements Router {

        @Override
        public void goTo(Screen screen) {
            View view = currentView;
            final History.Builder newHistoryBuilder = Flow.get(view).getHistory().buildUpon();
            newHistoryBuilder.push(screen);
            Flow.get(view).setHistory(newHistoryBuilder.build(), Direction.BACKWARD);
        }

        public void exitPlanner() {
            MainActivity.this.finish();
        }
    }

    @dagger.Component(dependencies = AppComponent.class)
    @ApplicationScope
    interface Component {
        void inject(MainActivity activity);
    }

}