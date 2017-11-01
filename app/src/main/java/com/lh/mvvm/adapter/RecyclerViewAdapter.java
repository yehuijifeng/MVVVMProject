package com.lh.mvvm.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.mvvm.R;
import com.lh.mvvm.databinding.ItemMvvmBinding;
import com.lh.mvvm.observable.UserBean;

import java.util.List;

/**
 * Created by LuHao on 2017/10/31.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener {
    private List<UserBean> data;
    /**
     * 用于模仿listview的itemclick事件，recyclerview没有itemclick
     */
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public RecyclerViewAdapter(List<UserBean> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMvvmBinding itemMvvmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mvvm, parent, false);
        itemMvvmBinding.getRoot().setOnClickListener(this);
        return new RecyclerViewHolder(itemMvvmBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ItemMvvmBinding itemMvvmBinding = holder.getBinding();
        UserBean userBean = data.get(position);
        itemMvvmBinding.setUser(userBean);
        //将position保存在itemView的Tag中，以便点击时进行获取
        itemMvvmBinding.getRoot().setTag(position);
        itemMvvmBinding.btnUpdate.setOnClickListener(new OnBtnClickListener(1, userBean));
        itemMvvmBinding.btnDelete.setOnClickListener(new OnBtnClickListener(2, position));
        // 立刻执行绑定
        itemMvvmBinding.executePendingBindings();
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
                    data.remove(position);
                    notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
    }
}
