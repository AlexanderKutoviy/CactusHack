package com.anykeyapp.database;

import android.database.sqlite.SQLiteDatabase;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

@com.raizlabs.android.dbflow.annotation.Database(name = Database.NAME, version = Database.VERSION)
public class Database {

    public static final String TAG = Database.class.getSimpleName();
    public static final String NAME = "bin";
    public static final int VERSION = 0;

    public static void dropTable(DatabaseWrapper db, Class cls) {
        ModelAdapter myAdapter = FlowManager.getModelAdapter(cls);
        db.execSQL("DROP TABLE IF EXISTS " + myAdapter.getTableName());
    }

    public static void dropTable(SQLiteDatabase db, Class cls) {
        ModelAdapter myAdapter = FlowManager.getModelAdapter(cls);
        db.execSQL("DROP TABLE IF EXISTS " + myAdapter.getTableName());
    }
}