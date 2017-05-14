package com.anykeyapp.util;

import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BitSchedulers {

    private static final Scheduler ioScheduler = Schedulers.from(
            Executors.newSingleThreadExecutor());

    private static final Scheduler mainScheduler = AndroidSchedulers.mainThread();

    public static Scheduler main() {
        return mainScheduler;
    }

    public static Scheduler io() {
        return ioScheduler;
    }
}
