package com.anykeyapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anykeyapp.R;
import com.anykeyapp.dao.models.ProductItem;
import com.febaisi.CustomTextView;

import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder> {

    private final String TAG = ProductsRecyclerAdapter.class.getSimpleName();
    private static final int TYPE_EVEN = 1001;
    private static final int TYPE_ODD = 1002;

    private Context context;
    private LayoutInflater inflater;

    private List<ProductItem> data;

    public ProductsRecyclerAdapter(Context context, List<ProductItem> data) {
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
        ImageView status1;
        ImageView status2;
        ImageView status3;
        ImageView status4;
        ImageView status5;

        public ProductViewHolder(View view) {
            super(view);
            productIcon = (ImageView) view.findViewById(R.id.product_icon);
            productTitle = (CustomTextView) view.findViewById(R.id.product_title);
            statusLayout = (LinearLayout) view.findViewById(R.id.status_layout);
            status1 = (ImageView) view.findViewById(R.id.status1);
            status2 = (ImageView) view.findViewById(R.id.status2);
            status3 = (ImageView) view.findViewById(R.id.status3);
            status4 = (ImageView) view.findViewById(R.id.status4);
            status5 = (ImageView) view.findViewById(R.id.status5);
        }
    }
}