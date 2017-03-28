package com.qs.qswlw.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by 小羽 on 2017/3/22.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Object initView = initView();
        if (initView instanceof View) {
            setContentView((View) initView);
        } else {
            setContentView((Integer) initView);
        }
        initData();
    }

    private void initData() {
        initfindviewByid();
        setOnclick();
    }

    abstract Object initView();

    abstract void initfindviewByid();

    abstract void setOnclick();
}
