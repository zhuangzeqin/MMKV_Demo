package com.eeepay.zzq.mmkv_demo.Interface;

import android.support.annotation.NonNull;

/**
 * 描述：APT 生成的代码就通过此入口来调用 持久化+序列化+反序列化 方法,这里采用单例模式
 * 作者：zhuangzeqin
 * 时间: 2019/2/15-15:41
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class PreferencesManager {
    /**
     * ------注释说明---定义好操作接口-----
     **/
    private IPreferencesHolder preferencesHolder;

    private PreferencesManager() {
    }

    private static class SingletonHolder {
        private static final PreferencesManager INSTANCE = new PreferencesManager();
    }

    public static PreferencesManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 由外部传进来
     *
     * @param preferencesHolder
     */
    public void setPreferencesHolder(@NonNull IPreferencesHolder preferencesHolder) {
        this.preferencesHolder = preferencesHolder;
    }

    /**
     * 向外提供获取的实例 preferencesHolder，面先接口编程
     *
     * @return
     */
    public IPreferencesHolder getPreferencesHolder() {
        return preferencesHolder;
    }
}
