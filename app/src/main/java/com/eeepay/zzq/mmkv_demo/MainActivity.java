package com.eeepay.zzq.mmkv_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eeepay.zzq.apt_annotation.BindView;
import com.eeepay.zzq.mmkv_demo.bean.Book;
import com.eeepay.zzq.mmkv_demo.bean.UserInfo;
import com.eeepay.zzq.mmkv_demo.bean.UserInfoPreferences;
import com.tencent.mmkv.MMKV;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：MMKV 是基于 mmap 内存映射的 key-value 组件，底层序列化/反序列化使用 protobuf 实现，性能高，稳定性强。
 * 从 2015 年中至今在微信上使用，其性能和稳定性经过了时间的验证。
 * 近期也已移植到 Android / macOS / Windows 平台，一并开源。
 * 作者：zhuangzeqin
 * 时间: 2019/2/13-14:53
 * 邮箱：zzq@eeepay.cn
 * 备注: mmkv 可以完美迁移sp 的数据； 这个是兼容性很好的体现
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_mmkv)
    Button btn_mmkv;
    @BindView(R.id.btn_mmkv_data)
    Button btn_mmkv_data;
    @BindView(R.id.btn_mmkv_delete)
    Button btn_mmkv_delete;
    @BindView(R.id.tv_msg)
    TextView tv_msg;

    String rootDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityViewBinding.bind(this);
//        btn_mmkv = (Button) this.findViewById(R.id.btn_mmkv);
//        btn_mmkv_data = (Button) this.findViewById(R.id.btn_mmkv_data);
//        tv_msg = (TextView) this.findViewById(R.id.tv_msg);
        //1 初始化使用
//        rootDir = MMKV.initialize(getApplicationContext());

        File file = new File(this.getFilesDir(), "MMKV2");//data/data/包名/files

        final String LOG_PATH = Environment.getExternalStorageDirectory() + File.separator + "MMKV3" + File.separator;
        rootDir = MMKV.initialize(LOG_PATH);

        rootDir = MMKV.initialize(LOG_PATH, new MMKV.LibLoader() {
            @Override
            public void loadLibrary(String libName) {
            }
        });
        // String root = context.getFilesDir().getAbsolutePath() + "/mmkv";
        ///data/user/0/com.eeepay.zzq.mmkv_demo/files/mmkv
        Log.d("MainActivity", "rootDir:" + rootDir);
        Toast.makeText(this, rootDir, Toast.LENGTH_SHORT).show();
        //初始化sp 的数据
        initSharePreFerencesData();
        btn_mmkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initTest();

                UserInfo userInfo = new UserInfo();
                userInfo.setAge(30);
                userInfo.setName("zhuangzeqin");
                Book book = new Book();
                book.setName("语文");
                List<Integer> ids = new ArrayList<>();
                ids.add(1);
                ids.add(2);
                ids.add(3);
                ids.add(4);
                book.setName("语文");
                book.setIds(ids);
                userInfo.setBook(book);
                //缓存整个对象
                UserInfoPreferences.get().setUserInfo(userInfo);
            }
        });

        btn_mmkv_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testgetData();
//                testImportSharedPreferences();
                UserInfo userInfo = UserInfoPreferences.get().getUserInfo();

                Toast.makeText(MainActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();


            }
        });

        btn_mmkv_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserInfoPreferences.get().remove();
            }
        });
    }

    private void testgetData() {
        //MMKV 提供一个全局的实例，可以直接使用
        MMKV mmkv = MMKV.defaultMMKV();
        //读取
        String name = mmkv.decodeString("name");
        int age = mmkv.decodeInt("age");
        double money = mmkv.decodeDouble("money");
        boolean bool = mmkv.decodeBool("bool", false);

        mmkv.removeValueForKey("bool");//单个key
        Log.d("MainActivity", "bool: " + mmkv.decodeBool("bool"));
        mmkv.removeValuesForKeys(new String[]{"name", "age"});//数组
        System.out.println("allKeys: " + Arrays.toString(mmkv.allKeys()));
        Log.d("MainActivity", "allKeys: " + Arrays.toString(mmkv.allKeys()));
        boolean hasBool = mmkv.containsKey("bool");//是否存在某个key

        StringBuffer stringBuffer = new StringBuffer(rootDir + "--->");
        stringBuffer.append(name).append(age).append(money).append(bool).append(hasBool);
        tv_msg.setText(stringBuffer.toString());
        Log.d("MainActivity", "stringBuffer:" + stringBuffer.toString());
    }

    private void initTest() {
        //1如果不同业务需要区别存储，也可以单独创建自己的实例：
//        MMKV mmkv = MMKV.mmkvWithID("MyID");
//        mmkv.encode("bool", true);

        //2 如果业务需要多进程访问，那么在初始化的时候加上标志位 MMKV.MULTI_PROCESS_MODE：
        //MMKV mmkv = MMKV.mmkvWithID("InterProcessKV", MMKV.MULTI_PROCESS_MODE);
        //mmkv.encode("bool", true);

        //3 MMKV 提供一个全局的实例，可以直接使用
        //1 支持以下 Java 语言基础类型：
        //boolean、int、long、float、double、byte[]

        //2 支持以下 Java 类和容器：
        //String、Set<String>
        //任何实现了Parcelable的类型

        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("name", "zhuangzeqin");//String
        mmkv.encode("age", 30);//age int
        mmkv.encode("bool", true);
        mmkv.encode("money", 25.66);

        //读取
        String name = mmkv.decodeString("name");
        int age = mmkv.decodeInt("age");
        double money = mmkv.decodeDouble("money");
        boolean bool = mmkv.decodeBool("bool", false);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name).append(age).append(money).append(bool);
        tv_msg.setText(stringBuffer.toString());
        Log.d("MainActivity", "stringBuffer:" + stringBuffer.toString());
    }

    private void initSharePreFerencesData() {
        SharedPreferences preferences = getSharedPreferences("myData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("bool", true);
        editor.putInt("int", Integer.MIN_VALUE);
        editor.putLong("long", Long.MAX_VALUE);
        editor.putFloat("float", -3.14f);
        editor.putString("string", "hello, imported");
        editor.commit();
    }

    //SharedPreferences 迁移数据
    private void testImportSharedPreferences() {
        MMKV mmkvWithID = MMKV.mmkvWithID("myData2");
        // 迁移旧数据
        SharedPreferences old_man = getSharedPreferences("myData", MODE_PRIVATE);
        mmkvWithID.importFromSharedPreferences(old_man);
        old_man.edit().clear().commit();

        boolean bool = mmkvWithID.decodeBool("bool");
        long aLong = mmkvWithID.decodeLong("long");
        int anInt = mmkvWithID.decodeInt("int");
        float aFloat = mmkvWithID.decodeFloat("float");
        String astring = mmkvWithID.decodeString("string");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bool).append(aLong).append(anInt).append(aFloat).append(astring);
        tv_msg.setText(stringBuffer.toString());
        Log.d("MainActivity", "stringBuffer:" + stringBuffer.toString());
        // 跟以前用法一样
//        SharedPreferences.Editor editor = mmkvWithID.edit();
//        editor.putBoolean("bool", false);
//        editor.putInt("int", 123);
//        editor.putLong("long", 300L);
//        editor.putFloat("float", -3.141592364f);
//        editor.putString("string", "hello, zzqybh");
//        HashSet<String> set = new HashSet<String>();
//        set.add("1"); set.add("2"); set.add("3"); set.add("4"); set.add("aaa"); set.add("ttt");
//        editor.putStringSet("string-set", set);
        // 无需调用 commit()
        //editor.commit();
    }
}
