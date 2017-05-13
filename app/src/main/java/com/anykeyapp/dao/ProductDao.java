package com.anykeyapp.dao;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.anykeyapp.dao.models.ProductItem;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ProductDao {

    public void create(ProductItem product) {
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
}
