package com.anykeyapp.controller;

import android.content.Context;
import android.util.Log;

import com.anykeyapp.dao.ProductDao;
import com.anykeyapp.dao.models.ProductItem;

import java.util.Date;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class ExpirationController {

    private final String TAG = ExpirationController.class.getSimpleName();
    private final String STATUS_SLEEPING = "SLEEPING";
    private final String STATUS_RUNNING = "RUNNING";
    private final String STATUS_ERROR = "ERROR";

    private Context context;
    private ProductDao productDao;

    private Thread thread;
    private final Object wakeMeUp = new Object();

    private CompositeSubscription subscriptions;

    public ExpirationController(Context context, ProductDao productDao) {
        this.productDao = productDao;
        this.context = context;
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        tick();
                    } catch (InterruptedException e) {
                        Log.d(TAG, "Thread is interrupted");
                        return;
                    } catch (Throwable e) {
                        Log.e(TAG, "Thread", e);
                        try {
                            synchronized (wakeMeUp) {
                                wakeMeUp.wait(10000);
                            }
                        } catch (InterruptedException e1) {
                            Log.e(TAG, "", e);
                            return;
                        }
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void tick() throws Exception {
        Log.e(TAG, "check expiration");

        List<ProductItem> products = productDao.read();
        for (ProductItem item : products) {
            checkIfExpired(item);
        }

        synchronized (wakeMeUp) {
            wakeMeUp.wait(100000);
            return;
        }
    }

    private void checkIfExpired(ProductItem product) {
        if (product.expirationDate <= new Date().getTime())
            product.freshStatus = false;
        else
            product.freshStatus = true;

        productDao.update(product);
    }

    public void wakeUp() {
        synchronized (wakeMeUp) {
            wakeMeUp.notifyAll();
        }
    }
}