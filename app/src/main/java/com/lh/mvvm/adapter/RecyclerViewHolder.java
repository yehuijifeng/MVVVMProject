package com.lh.mvvm.adapter;

import android.support.v7.widget.RecyclerView;

import com.lh.mvvm.databinding.ItemMvvmBinding;

/**
 * Created by LuHao on 2017/10/30.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    //这里只要给RecyclerView.ViewHolder返回一个view就可以，所以我们将构造方法中传入databinding
    ItemMvvmBinding itemMvvmBinding;

    public RecyclerViewHolder(ItemMvvmBinding itemMvvmBinding) {
        super(itemMvvmBinding.getRoot());
        this.itemMvvmBinding = itemMvvmBinding;
    }

    public ItemMvvmBinding getBinding() {
        return itemMvvmBinding;
    }

    public void setBinding(ItemMvvmBinding itemMvvmBinding) {
        this.itemMvvmBinding = itemMvvmBinding;
    }
}
