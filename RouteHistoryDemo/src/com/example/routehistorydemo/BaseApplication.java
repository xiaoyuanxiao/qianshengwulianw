package com.example.routehistorydemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class BaseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(BaseApplication.this);// 初始化百度地图
	}
}
