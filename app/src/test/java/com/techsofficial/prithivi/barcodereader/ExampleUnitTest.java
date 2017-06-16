package com.techsofficial.prithivi.barcodereader;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends Application {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}