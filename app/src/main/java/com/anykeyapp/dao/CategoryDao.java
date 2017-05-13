package com.anykeyapp.dao;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.anykeyapp.dao.models.Category;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CategoryDao {

    public void create(Category category) {
        category.save();
    }

    public List<Category> read() {
        return Stream.of(SQLite.select().from(Category.class).queryList())
                .collect(Collectors.toList());
    }

    public void update(Category category) {
        create(category);
    }

    public void delete(Category category) {
        category.delete();
    }
}
