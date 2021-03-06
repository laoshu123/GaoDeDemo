package com.ehecd.driving.action;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * 项目名称：EhecdDrivingAgent
 * 包名：   com.ehecd.driving.action
 * 类名：
 * 创建人：ehecd_ss
 * 时间：2021/6/30 18:44
 */
public interface BundleAction {

    @Nullable
    Bundle getBundle();

    default int getInt(String name) {
        return getInt(name, 0);
    }

    default int getInt(String name, int defaultValue) {
        return getBundle() == null ? defaultValue : getBundle().getInt(name, defaultValue);
    }

    default long getLong(String name) {
        return getLong(name, 0);
    }

    default long getLong(String name, int defaultValue) {
        return getBundle() == null ? defaultValue : getBundle().getLong(name, defaultValue);
    }

    default float getFloat(String name) {
        return getFloat(name, 0);
    }

    default float getFloat(String name, int defaultValue) {
        return getBundle() == null ? defaultValue : getBundle().getFloat(name, defaultValue);
    }

    default double getDouble(String name) {
        return getDouble(name, 0);
    }

    default double getDouble(String name, int defaultValue) {
        return getBundle() == null ? defaultValue : getBundle().getDouble(name, defaultValue);
    }

    default boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    default boolean getBoolean(String name, boolean defaultValue) {
        return getBundle() == null ? defaultValue : getBundle().getBoolean(name, defaultValue);
    }

    default String getString(String name) {
        return getBundle() == null ? null : getBundle().getString(name);
    }

    default <P extends Parcelable> P getParcelable(String name) {
        return getBundle() == null ? null : getBundle().getParcelable(name);
    }

    @SuppressWarnings("unchecked")
    default <S extends Serializable> S getSerializable(String name) {
        return (S) (getBundle() == null ? null : getBundle().getSerializable(name));
    }

    default ArrayList<String> getStringArrayList(String name) {
        return getBundle() == null ? null : getBundle().getStringArrayList(name);
    }

    default ArrayList<Integer> getIntegerArrayList(String name) {
        return getBundle() == null ? null : getBundle().getIntegerArrayList(name);
    }
}
