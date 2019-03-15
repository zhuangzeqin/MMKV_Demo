package com.eeepay.zzq.mmkv_demo.Interface;

/**
 * 描述：定义好操作接口，并由外部传入具体的实现
 * 作者：zhuangzeqin
 * 时间: 2019/2/15-15:38
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public interface IPreferencesHolder {
    //序列化
    String serialize(String key, Object src);

    //反序列化
    <T> T deserialize(String key, Class<T> classOfT);

    //移除指定对象
    void remove(String key);
}
