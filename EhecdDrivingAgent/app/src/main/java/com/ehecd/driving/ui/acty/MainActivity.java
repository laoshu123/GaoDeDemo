package com.ehecd.driving.ui.acty;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.ehecd.driving.R;
import com.ehecd.driving.common.MyActivity;
import com.ehecd.driving.widget.SlideRightViewDragHelper;

public class MainActivity extends MyActivity implements INaviInfoCallback {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        findViewById(R.id.btnAmapRoute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAmapRoute();
            }
        });


    }

    @Override
    protected void initData() {

    }

    private void startAmapRoute() {
        //起点
        Poi start = new Poi("北京首都机场", new LatLng(40.080525, 116.603039), "");
        //途经点
//        List<Poi> poiList = new ArrayList();
//        poiList.add(new Poi("故宫", new LatLng(39.918058, 116.397026), ""));
        //终点
        Poi end = new Poi("北京大学", new LatLng(39.941823, 116.426319), "");
        // 组件参数配置
        AmapNaviParams params = new AmapNaviParams(start, null, end, AmapNaviType.DRIVER, AmapPageType.NAVI);
        // 启动组件
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, this);

    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
//        toast("位置发生变化了");
    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {
//        toast("退出导航");
    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public void onNaviDirectionChanged(int i) {

    }

    @Override
    public void onDayAndNightModeChanged(int i) {

    }

    @Override
    public void onBroadcastModeChanged(int i) {

    }

    @Override
    public void onScaleAutoChanged(boolean b) {

    }

    @Override
    public View getCustomMiddleView() {
        return null;
    }

    @Override
    public View getCustomNaviBottomView() {
        //底部自定义区域
        return null;
    }

    @Override
    public View getCustomNaviView() {
        //返回null则不显示自定义区域
        return getCustomCenterView();
    }

    private View getCustomBottomView() {
        LinearLayout linearLayout = new LinearLayout(this);
        try {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setBackgroundColor(0xFFFF0101);

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.view_bottom,null);
            SlideRightViewDragHelper dragHelper = view.findViewById(R.id.swipe_right);
            dragHelper.setOnReleasedListener(new SlideRightViewDragHelper.OnReleasedListener() {
                @Override
                public void onReleased() {
                    toast("右滑-到底");
                    linearLayout.setBackgroundColor(0xFF279a38);
                }
            });

            linearLayout.addView(view,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(params);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return linearLayout;
    }

    private Button button;
    private View getCustomCenterView(){
        LinearLayout linearLayout = new LinearLayout(this);
        try {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            button = new Button(this);
            button.setText("等待");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast("等待");
                }
            });

            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.addView(button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 120;
            linearLayout.setLayoutParams(params);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return linearLayout;
    }

}