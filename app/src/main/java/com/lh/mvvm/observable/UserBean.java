package com.lh.mvvm.observable;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

/**
 * Created by LuHao on 2017/10/31.
 *
 */

public class UserBean {
    public ObservableInt userId = new ObservableInt();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableDouble userAge = new ObservableDouble();
    public ObservableFloat userSex = new ObservableFloat();
}
