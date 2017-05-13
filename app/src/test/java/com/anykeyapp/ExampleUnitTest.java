package com.anykeyapp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Date date = new Date();
        Date date2 = new Date(1494892800000L);
        System.out.println("date 1 : " + new SimpleDateFormat().format(date));
        System.out.println("date 2 : " + new SimpleDateFormat().format(date2));
        long diff = date2.getTime() - date.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("DAYS : " + days);
    }
}