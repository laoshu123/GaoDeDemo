package com.ehecd.driving.amap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：EhecdDrivingAgent
 * 包名：   com.ehecd.driving.amap
 * 类名：
 * 创建人：ehecd_ss
 * 时间：2021/7/1 15:38
 */
public class AmapUtils {

    private DistanceSearch distanceSearch;

    /**
     * 计算两点的驾车距离
     * @param context
     */
    public void getDistance(Context context) {
        if (distanceSearch == null) {
            distanceSearch = new DistanceSearch(context);
        }
        distanceSearch.setDistanceSearchListener(new DistanceSearch.OnDistanceSearchListener() {
            @Override
            public void onDistanceSearched(DistanceResult distanceResult, int i) {
                List<DistanceItem> distanceItems = distanceResult.getDistanceResults();
                float distance = distanceItems.get(0).getDistance();
                ToastUtils.show(distance+"");
            }
        });
        LatLonPoint start = new LatLonPoint(39.907315, 116.274002);
//        LatLonPoint start1 = new LatLonPoint(39.90000, 116.407525);
//        LatLonPoint start2 = new LatLonPoint(38.540103, 76.978787);
//        LatLonPoint start3 = new LatLonPoint(10.90000, 116.407525);
        LatLonPoint dest = new LatLonPoint(39.908369, 116.515702);

        //设置起点和终点，其中起点支持多个
        List<LatLonPoint> latLonPoints = new ArrayList<LatLonPoint>();
        latLonPoints.add(start);
//        latLonPoints.add(start1);
//        latLonPoints.add(start2);
//        latLonPoints.add(start3);

        DistanceSearch.DistanceQuery distanceQuery= new DistanceSearch.DistanceQuery();
        distanceQuery.setOrigins(latLonPoints);
        distanceQuery.setDestination(dest);
        //设置测量方式，支持直线和驾车
        distanceQuery.setType(DistanceSearch.TYPE_DRIVING_DISTANCE);

        distanceSearch.calculateRouteDistanceAsyn(distanceQuery);
    }

    /**
     * 自定义图标 view 转 bitmap
     * @param mActivity
     * @param iLayout
     * @return
     */
    public static Bitmap convertViewToBitmap(Activity mActivity,int iLayout) {
        View view = View.inflate(mActivity, iLayout,null);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
