package com.eeepay.zzq.mmkv_demo;

import android.app.Application;

import com.eeepay.zzq.mmkv_demo.Interface.PreferencesMMKVHolder;
import com.eeepay.zzq.mmkv_demo.Interface.PreferencesManager;
import com.tencent.mmkv.MMKV;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/2/18-11:43
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
        PreferencesManager.getInstance().setPreferencesHolder(new PreferencesMMKVHolder());
    }
}
