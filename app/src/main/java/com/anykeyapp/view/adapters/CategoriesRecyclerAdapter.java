package com.anykeyapp.view.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anykeyapp.R;
import com.anykeyapp.dao.models.Category;
import com.anykeyapp.presenter.AddItemPresenter;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder> {

    private final String TAG = CategoriesRecyclerAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;

    private List<Category> data;
    private AddItemPresenter presenter;
    private long clickedId;

    public CategoriesRecyclerAdapter(Context context, List<Category> data, AddItemPresenter presenter) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.context = context;
        this.presenter = presenter;
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
    public CategoriesRecyclerAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_recycler_item, parent, false);

        return new CategoriesRecyclerAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoriesRecyclerAdapter.CategoryViewHolder holder, final int position) {
        if (data == null) {
            return;
        }
        Category category = data.get(position);
        holder.id = category.id;
        holder.name = category.name;
        if (category.avatarUrl.equals("Milk")) {
            holder.categoryAvatar.setImageBitmap(
                    BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.milk));
        } else if (category.avatarUrl.equals("Chicken")) {
            holder.categoryAvatar.setImageBitmap(
                    BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.chicken));
        } else if (category.avatarUrl.equals("Steak")) {
            holder.categoryAvatar.setImageBitmap(
                    BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.steak));
        }

        if (category.id == clickedId) {
            holder.view.setBackground(context.getResources().getDrawable(R.drawable.cateegory_icon));
        } else {
            holder.view.setBackground(context.getResources().getDrawable(R.drawable.feed_icon_base_darker));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Category> data) {
        Log.d(TAG, "set data : " + data.size());
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClickedId(long clickedId) {
        this.clickedId = clickedId;
        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView categoryAvatar;
        public long id;
        public String name;

        public CategoryViewHolder(View view) {
            super(view);
            this.view = view;
            categoryAvatar = (ImageView) view.findViewById(R.id.category_avatar);
            view.setOnClickListener(v -> presenter.categoryClicked(id, name));
        }
    }
}
