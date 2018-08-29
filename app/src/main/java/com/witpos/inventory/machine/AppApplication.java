package com.witpos.inventory.machine;

import android.app.Application;
import com.witpos.inventory.machine.utils.SharedPreferenceUtils;

/**
 * @author madan
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceUtils.init(this);
    }
}
