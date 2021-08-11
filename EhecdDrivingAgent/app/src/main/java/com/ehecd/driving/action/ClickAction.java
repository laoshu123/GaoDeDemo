package com.ehecd.driving.action;

import android.view.View;

import androidx.annotation.IdRes;

/**
 * 项目名称：EhecdDrivingAgent
 * 包名：   com.ehecd.driving.action
 * 类名：
 * 创建人：ehecd_ss
 * 时间：2021/6/30 18:43
 */
public interface ClickAction extends View.OnClickListener {

    <V extends View> V findViewById(@IdRes int id);

    @Override
    default void onClick(View v) {
        // 默认不实现，让子类实现
    }

    default void setOnClickListener(@IdRes int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }

    default void setOnClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }
}
