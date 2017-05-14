package com.anykeyapp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import com.anykeyapp.BinApplication;
import com.anykeyapp.R;
import com.anykeyapp.dao.models.Category;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.scopes.ApplicationScope;
import com.anykeyapp.presenter.AddItemPresenter;
import com.anykeyapp.router.Router;
import com.anykeyapp.router.RouterOwner;
import com.anykeyapp.view.adapters.CategoriesRecyclerAdapter;
import com.anykeyapp.view.drawer.DrawerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddItemView extends DrawerLayout implements RouterOwner {

    private Router router;
    private Context context;

    @Inject
    AddItemPresenter presenter;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView requestRecycler;
    private CategoriesRecyclerAdapter adapter;

    private EditText entryNameEdit;
    private EditText expDateNameEdit;
    private ImageView scanButton;
    private Button saveButton;
    private CalendarView calendarView;

    public AddItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        DaggerAddItemView_Component.builder()
                .appComponent(BinApplication.getAppComponent())
                .build().inject(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this, router);
        initViews();
        presenter.setData();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    private void initViews() {
        DrawerView drawer = (DrawerView) findViewById(R.id.left_drawer);
        findViewById(R.id.menu_item).setOnClickListener(v -> drawer.drawerLayout.openDrawer(Gravity.LEFT));

        requestRecycler = (RecyclerView) findViewById(R.id.categories_recycler);
        entryNameEdit = (EditText) findViewById(R.id.entry_name_edit_text);
        expDateNameEdit = (EditText) findViewById(R.id.exp_date_title_edit_text);

        scanButton = (ImageView) findViewById(R.id.scan_button);
        scanButton.setOnClickListener(btn -> presenter.scanBtnClicked());

        saveButton = (Button) findViewById(R.id.save_btn);
        saveButton.setOnClickListener(btn -> {
            if (entryNameEdit.getText() != null && expDateNameEdit.getText() != null)
                presenter.saveProduct();
        });

        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            presenter.onCalendarClicked(calendar);
        });
    }

    @Override
    public void injectRouter(Router router) {
        this.router = router;
        ((DrawerView) findViewById(R.id.left_drawer)).init(this, router);
    }

    public void viewExpDate(Date time) {
        expDateNameEdit.setText(new SimpleDateFormat("dd:MM:yyyy").format(time));
    }

    public void viewExpDate(String time) {
        expDateNameEdit.setText(time);
    }

    public void displayData(ProductItem productItem) {
        entryNameEdit.setText(productItem.name);
        if (productItem.categoryId != null) {
            setClickedId(productItem.categoryId, productItem.name);
        }
        if (productItem.expirationDate != 0) {
            expDateNameEdit.setText(new SimpleDateFormat("dd:MM:yyyy")
                    .format(new Date(productItem.expirationDate)));
        }
    }

    public void displayCategories(List<Category> categories) {
        adapter = new CategoriesRecyclerAdapter(context, categories, presenter);
        requestRecycler.setAdapter(adapter);
        requestRecycler.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setClickedId(long clickedId, String name) {
        adapter.setClickedId(clickedId);
        entryNameEdit.setText(name);
    }

    public String getName() {
        return entryNameEdit.getText().toString();
    }

    public long getDate() {
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy");
        try {
            Date dDate = format.parse(expDateNameEdit.getText().toString());
            return dDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            DateFormat format2 = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date dDate = format2.parse(expDateNameEdit.getText().toString());
                return dDate.getTime();
            } catch (ParseException e2) {
                e2.printStackTrace();
                DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date dDate = format3.parse(expDateNameEdit.getText().toString());
                    return dDate.getTime();
                } catch (ParseException e3) {
                    e3.printStackTrace();
                    return 0;
                }
            }
        }
    }

    @dagger.Component(dependencies = AppComponent.class)
    @ApplicationScope
    interface Component {
        void inject(AddItemView view);
    }
}