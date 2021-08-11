package com.ehecd.driving.action;

import com.hjq.toast.ToastUtils;

import androidx.annotation.StringRes;

/**
 * 项目名称：EhecdDrivingAgent
 * 包名：   com.ehecd.driving.action
 * 类名：
 * 创建人：ehecd_ss
 * 时间：2021/6/30 18:45
 */
public interface ToastAction {

    default void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    default void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    default void toast(Object object) {
        ToastUtils.show(object);
    }
}
