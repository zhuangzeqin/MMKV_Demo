package com.eeepay.zzq.mmkv_demo.bean;

import com.eeepay.zzq.apt_annotation.Preferences;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2019/2/18-14:25
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class UserInfo {
    @Preferences
    private String name;

    @Preferences
    private int age;

    @Preferences
    private Book book;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", book=" + book +
                '}';
    }
}
