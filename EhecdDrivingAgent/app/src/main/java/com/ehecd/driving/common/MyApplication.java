package com.ehecd.driving.common;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;

import java.util.Stack;

/**
 * 项目名称：EhecdDrivingAgent
 * 包名：   com.ehecd.driving.common
 * 类名：
 * 创建人：ehecd_ss
 * 时间：2021/6/30 18:02
 */
public class MyApplication extends Application {

    private static Stack<Activity> activityStack;

    private static MyApplication instance;
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = MyApplication.this;
        initSdk();
    }

    private void initSdk() {
        // 吐司工具类
        ToastUtils.init(instance);
        //权限
        XXPermissions.setScopedStorage(true);

        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor() {
            @Override
            public boolean intercept(Toast toast, CharSequence text) {
                boolean intercept = super.intercept(toast, text);
                if (intercept) {
                    Log.e("Toast", "空 Toast");
                } else {
                    Log.i("Toast", text.toString());
                }
                return intercept;
            }
        });
    }

    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public static Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public static void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        instance.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
}
