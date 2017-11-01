package com.lh.mvvm.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lh.mvvm.R;
import com.lh.mvvm.adapter.ListViewAdapter;
import com.lh.mvvm.databinding.ActivityListViewBinding;
import com.lh.mvvm.observable.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2017/10/31.
 */

public class ListViewActivity extends Activity {

    //相对应xml布局文件名
    ActivityListViewBinding activityListViewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_view);
        //初始化一个listview的adapter
        ListViewAdapter listViewAdapter = new ListViewAdapter(initData());
        //绑定adapter
        activityListViewBinding.setAdapter(listViewAdapter);
        //通知adapter刷新数据
        listViewAdapter.notifyDataSetChanged();
    }

    //初始化测试数据
    private List<UserBean> initData() {
        List<UserBean> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            UserBean userBean = new UserBean();
            userBean.userId.set(i);
            userBean.userName.set(i + "aaa");
            userBean.userAge.set(18 + i);
            userBean.userSex.set(i % 2 == 0 ? 1 : 0);
            list.add(userBean);
        }
        return list;
    }
}
