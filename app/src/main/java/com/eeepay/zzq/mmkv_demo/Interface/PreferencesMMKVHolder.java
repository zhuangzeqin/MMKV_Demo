package com.eeepay.zzq.mmkv_demo.Interface;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

/**
 * 描述：IPreferencesHolder 具体的实现
 * 作者：zhuangzeqin
 * 时间: 2019/2/15-15:45
 * 邮箱：zzq@eeepay.cn
 * 备注: 在 Application 的 onCreate() 方法中传入具体的实现 PreferencesManager.getInstance().setPreferencesHolder(new PreferencesMMKVHolder());
 */
public class PreferencesMMKVHolder implements IPreferencesHolder {
    private final static Gson MGSON = new Gson();

    @Override
    public String serialize(String key, Object src) {
        String json = MGSON.toJson(src);
        MMKV kv = MMKV.defaultMMKV();
        kv.putString(key, json);
        return json;
    }

    @Override
    public <T> T deserialize(String key, Class<T> classOfT) {
        MMKV kv = MMKV.defaultMMKV();
        String json = kv.decodeString(key, "");
        if (!TextUtils.isEmpty(json)) {
            return MGSON.fromJson(json, classOfT);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        MMKV kv = MMKV.defaultMMKV();
        kv.remove(key);
    }
}
