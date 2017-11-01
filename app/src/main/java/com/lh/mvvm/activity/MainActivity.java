package com.lh.mvvm.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lh.mvvm.R;
import com.lh.mvvm.bean.UserBean;
import com.lh.mvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //它的名字取决于你的layout文件名，activity_main
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //初始化数据
        UserBean userBean1 = new UserBean(1, "aaa", 1, 1);
        UserBean userBean2 = new UserBean(2, "bbb", 2, 4);
        UserBean userBean3 = new UserBean(3, "ccc", 3, 15);
        ArrayList<UserBean> data = new ArrayList<>();
        data.add(userBean1);
        data.add(userBean2);
        data.add(userBean3);

        //这里的方法和xml中定义的方法相对应
        //赋值过后，控件会自动填充数据
        activityMainBinding.setUser(userBean1);
        activityMainBinding.setList(data);
        //有id的控件也可以通过databinding得到
        activityMainBinding.btnTest.setOnClickListener(this);

        com.lh.mvvm.observable.UserBean ouserBean1 = new com.lh.mvvm.observable.UserBean();
        ouserBean1.userId.set(1);
        ouserBean1.userName.set("oaaa");
        ouserBean1.userAge.set(1d);
        ouserBean1.userSex.set(1f);
        activityMainBinding.setObservableuser(ouserBean1);
        activityMainBinding.btnTest1.setOnClickListener(this);
        activityMainBinding.btnListview.setOnClickListener(this);
        activityMainBinding.btnRecyclerview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(activityMainBinding.btnTest)) {
            //修改databinding中的数据
            activityMainBinding.getList().get(0).setUserName("bbb");
            //每一个定义的变量都有相对应的get/set方法
            activityMainBinding.btnTest.setText(activityMainBinding.getList().get(0).getUserName());
        } else if (v.equals(activityMainBinding.btnTest1)) {
            //双向绑定数据不需要重新调用控件，数据改变，相对应绑定数据的view也会改变
            activityMainBinding.getObservableuser().userName.set("动态修改后的数据，view自动刷新数据");
            activityMainBinding.getObservableuser().userAge.set(100000);
        } else if (v.equals(activityMainBinding.btnListview)) {
            Intent intent = new Intent(this, ListViewActivity.class);
            startActivity(intent);
        }else if (v.equals(activityMainBinding.btnRecyclerview)) {
            Intent intent = new Intent(this, RecyclerViewActivity.class);
            startActivity(intent);
        }
    }
}
