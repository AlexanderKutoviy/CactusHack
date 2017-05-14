package com.anykeyapp.dao;

import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.anykeyapp.dao.models.ProductItem;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ProductDao {

    private final String TAG = ProductDao.class.getSimpleName();

    public void create(ProductItem product) {
        Log.e(TAG, product.toString());
        product.save();
    }

    public List<ProductItem> read() {
        return Stream.of(SQLite.select().from(ProductItem.class).queryList())
                .collect(Collectors.toList());
    }

    public void update(ProductItem product) {
        create(product);
    }

    public void delete(ProductItem product) {
        product.delete();
    }

    public void sort(List<ProductItem> productItems) {
        Stream.of(productItems)
                .sorted((p1, p2) -> {
                    return (int)(p1.expirationDate - p2.expirationDate);
                }).collect(Collectors.toList());
    }
}
