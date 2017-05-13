package com.anykeyapp.dao.models;

import com.anykeyapp.database.Database;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = Database.class, insertConflict = ConflictAction.REPLACE)
public class Category extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public Long id;
    @Column
    public String name;
    @Column
    public String avatarUrl;
    @Column
    public boolean expirable;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.avatarUrl = name;
        this.expirable = true;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", expirable=" + expirable +
                '}';
    }
}
