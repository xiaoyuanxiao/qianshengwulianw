package com.qs.qswlw.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qs.qswlw.R;
import com.qs.qswlw.view.imageswitchview.Image3DSwitchView;
import com.qs.qswlw.view.imageswitchview.Image3DView;

/**
 * Created by 小羽 on 2017/3/22.
 */
public class MainActivity extends BaseActivity {
    private Image3DSwitchView imageSwitchView;
    private String[] listViewData = new String[]{
            "test1", "test2", "test3",
            "test4"};
    ImageView iv_main_avater;

    @Override
    Object initView() {
        return R.layout.activity_main;
    }

    @Override
    void initfindviewByid() {
        imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
//        imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
//            @Override
//            public void onImageSwitch(int currentImage) {
//                // Log.d("TAG", "current image is " + currentImage);
//            }
//        });
        imageSwitchView.setCurrentImage(1);
        Image3DView image1 = (Image3DView) findViewById(R.id.image1);
        TextView textView = new TextView(this);
        textView.setText("全联盟让利金额排行榜");
        image1.addHeaderView(textView);
        image1.setBColor(Color.parseColor("#fff000"));
        TextView textView2 = new TextView(this);
        textView2.setText("查看全部排名");
        image1.addFooterView(textView2);
        Image3DView image2 = (Image3DView) findViewById(R.id.image2);
        Image3DView image3 = (Image3DView) findViewById(R.id.image3);
        Image3DView image4 = (Image3DView) findViewById(R.id.image4);
        Image3DView image5 = (Image3DView) findViewById(R.id.image5);
        Image3DView image6 = (Image3DView) findViewById(R.id.image6);
        image1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        image2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        image3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        image4.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        image5.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        image6.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listViewData));
        iv_main_avater = (ImageView) findViewById(R.id.iv_main_avater);

        TextView textView22 = new TextView(this);
        textView22.setText("无敌低2222222222222");
        image2.addHeaderView(textView22);


        TextView textView3 = new TextView(this);
        textView3.setText("无敌低33333333333");
        image3.addHeaderView(textView3);
        Bitmap bitmap2 = Bitmap.createBitmap(1, 1,
                Bitmap.Config.ARGB_8888);
        bitmap2.eraseColor(Color.parseColor("#00ff00"));
        image2.setmBitmap(bitmap2);

        TextView textView4 = new TextView(this);
        textView4.setText("无敌低44444444444");
        image4.addHeaderView(textView4);


        TextView textView5 = new TextView(this);
        textView5.setText("无敌低555555555555");
        image5.addHeaderView(textView5);


        TextView textView6 = new TextView(this);
        textView6.setText("无敌低66666666666666666");
        image6.addHeaderView(textView6);


    }

    @Override
    void setOnclick() {
        iv_main_avater.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_avater:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageSwitchView.clear();
    }
}
