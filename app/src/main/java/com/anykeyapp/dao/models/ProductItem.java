package com.anykeyapp.dao.models;

import com.anykeyapp.database.Database;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = Database.class, insertConflict = ConflictAction.REPLACE)
public class ProductItem extends BaseModel {

    @Column
    @PrimaryKey
    public Long id;
    @Column
    public Long categoryId;
    @Column
    public String name;
    @Column
    public String description;
    @Column
    public long expirationDate;
    @Column
    public boolean freshStatus;
    @Column
    public boolean liveStatus;

    public ProductItem() {
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", expirationDate=" + expirationDate +
                ", freshStatus=" + freshStatus +
                ", liveStatus=" + liveStatus +
                '}';
    }
}