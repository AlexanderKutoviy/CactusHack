package com.anykeyapp.view.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anykeyapp.R;
import com.anykeyapp.dao.models.ProductItem;
import com.anykeyapp.presenter.FeedPresenter;
import com.febaisi.CustomTextView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder> {

    private final String TAG = ProductsRecyclerAdapter.class.getSimpleName();
    private static final int TYPE_EVEN = 1001;
    private static final int TYPE_ODD = 1002;

    private Context context;
    private LayoutInflater inflater;

    private List<ProductItem> data;

    public ProductsRecyclerAdapter(Context context, List<ProductItem> data, FeedPresenter presenter) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        if (data.get(position) != null)
            return data.get(position).id;
        else
            return -1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_EVEN;
        } else {
            return TYPE_ODD;
        }
    }

    @Override
    public ProductsRecyclerAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_EVEN) {
            view = inflater.inflate(R.layout.product_item_view_layout_even, parent, false);
        } else {
            view = inflater.inflate(R.layout.product_item_view_layout_odd, parent, false);
        }

        return new ProductsRecyclerAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsRecyclerAdapter.ProductViewHolder holder, final int position) {
        if (data == null) {
            return;
        }
        ProductItem productItem = data.get(position);
        holder.productTitle.setText(productItem.name);
        holder.productIcon.setImageBitmap(BitmapFactory.decodeFile(productItem.avatarPath, new BitmapFactory.Options()));

        long days = countDaysToExptire(productItem.expirationDate);

        if (days <= 5) {
            for (Map.Entry<Integer, ImageView> entry : holder.statues.entrySet()) {
                if (entry.getKey() < days) {
                    entry.getValue()
                            .setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.counter_unchecked));
                }
                if (entry.getKey() == days) {
                    entry.getValue()
                            .setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.fish_counter));
                }
                if (entry.getKey() > days) {
                    entry.getValue()
                            .setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.counter_checked));
                }
            }
        } else {
            for (Map.Entry<Integer, ImageView> entry : holder.statues.entrySet()) {
                entry.getValue()
                        .setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.counter_unchecked));
            }
        }

    }

    private long countDaysToExptire(long expirationTimestamp) {
        Date dateNow = new Date();
        Date expirationDate = new Date(expirationTimestamp);
        long diff = expirationDate.getTime() - dateNow.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ProductItem> data) {
        Log.d(TAG, "set data : " + data.size());
        this.data = data;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productIcon;
        CustomTextView productTitle;
        LinearLayout statusLayout;
        Map<Integer, ImageView> statues = new HashMap<>(5);

        public ProductViewHolder(View view) {
            super(view);
            productIcon = (ImageView) view.findViewById(R.id.product_icon);
            productTitle = (CustomTextView) view.findViewById(R.id.product_title);
            statusLayout = (LinearLayout) view.findViewById(R.id.status_layout);
            statues.put(1, (ImageView) view.findViewById(R.id.status1));
            statues.put(2, (ImageView) view.findViewById(R.id.status2));
            statues.put(3, (ImageView) view.findViewById(R.id.status3));
            statues.put(4, (ImageView) view.findViewById(R.id.status4));
            statues.put(5, (ImageView) view.findViewById(R.id.status5));
        }
    }
}