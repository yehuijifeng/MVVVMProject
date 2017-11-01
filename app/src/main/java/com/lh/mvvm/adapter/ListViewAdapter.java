package com.lh.mvvm.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lh.mvvm.BR;
import com.lh.mvvm.R;
import com.lh.mvvm.databinding.ItemMvvmBinding;
import com.lh.mvvm.observable.UserBean;

import java.util.List;

/**
 * Created by LuHao on 2017/10/31.
 */

public class ListViewAdapter extends BaseAdapter {
    //数据
    private List<UserBean> list;

    public ListViewAdapter(List<UserBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //这个databinding也是根据布局文件item_mvvm而命名的
        ItemMvvmBinding itemMvvmBinding;
        if (convertView == null) {
            //创建一个databinding
            itemMvvmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mvvm, parent, false);
            //获取convertView
            convertView = itemMvvmBinding.getRoot();
        } else {
            //去除convertView中bangding的dataBinding
            itemMvvmBinding = DataBindingUtil.getBinding(convertView);
        }
        UserBean userBean = list.get(position);
        //绑定数据，这里的BR.user根据item布局文件中的变量声明来决定
        itemMvvmBinding.setVariable(BR.user, userBean);
        itemMvvmBinding.btnUpdate.setOnClickListener(new OnBtnClickListener(1, userBean));
        itemMvvmBinding.btnDelete.setOnClickListener(new OnBtnClickListener(2, position));
        return convertView;
    }

    private class OnBtnClickListener implements View.OnClickListener {
        private int stats;//1,修改；2，删除
        private UserBean userBean;
        private int position;

        OnBtnClickListener(int stats, UserBean userBean) {
            this.stats = stats;
            this.userBean = userBean;
        }

        OnBtnClickListener(int stats, int position) {
            this.stats = stats;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (stats) {
                case 1:
                    userBean.userName.set("修改后的名字");
                    break;
                case 2:
                    list.remove(position);
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
