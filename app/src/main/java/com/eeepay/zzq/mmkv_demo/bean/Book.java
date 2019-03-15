package com.eeepay.zzq.mmkv_demo.bean;

import java.util.List;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/2/18-14:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class Book {
    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private List<Integer> ids;

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ids=" + ids +
                ", name='" + name + '\'' +
                '}';
    }
}
