package com.example.routehistorydemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity implements OnClickListener {
	private ArrayList<LatLng> routeList;// 路线点的集合
	/*
	 * 自己写的json数据
	 */
	private String routeJson = "{\"list\":[{\"lon\":122.235502,\"lat\":37.330564},{\"lon\":122.235727,\"lat\":37.328527},{\"lon\":122.236086,\"lat\":37.326676},{\"lon\":122.23694,\"lat\":37.325083},{\"lon\":122.237218,\"lat\":37.324703},{\"lon\":122.238862,\"lat\":37.323153},{\"lon\":122.240982,\"lat\":37.321596},{\"lon\":122.243381,\"lat\":37.319867},{\"lon\":122.245267,\"lat\":37.318454},{\"lon\":122.247504,\"lat\":37.316804},{\"lon\":122.249696,\"lat\":37.315182},{\"lon\":122.251762,\"lat\":37.313661},{\"lon\":122.253451,\"lat\":37.312075},{\"lon\":122.254547,\"lat\":37.31008},{\"lon\":122.25478,\"lat\":37.307677},{\"lon\":122.254196,\"lat\":37.30523},{\"lon\":122.253478,\"lat\":37.302603},{\"lon\":122.252939,\"lat\":37.300565},{\"lon\":122.25231,\"lat\":37.298663},{\"lon\":122.251735,\"lat\":37.297156},{\"lon\":122.251223,\"lat\":37.296022},{\"lon\":122.250253,\"lat\":37.293962},{\"lon\":122.249282,\"lat\":37.291895},{\"lon\":122.24816,\"lat\":37.289519},{\"lon\":122.24718,\"lat\":37.287431},{\"lon\":122.246102,\"lat\":37.285155},{\"lon\":122.245105,\"lat\":37.283081},{\"lon\":122.243938,\"lat\":37.280604},{\"lon\":122.24294,\"lat\":37.278458},{\"lon\":122.241782,\"lat\":37.275823},{\"lon\":122.241135,\"lat\":37.271222},{\"lon\":122.241458,\"lat\":37.269096},{\"lon\":122.242303,\"lat\":37.266799},{\"lon\":122.243372,\"lat\":37.264587},{\"lon\":122.244557,\"lat\":37.262189},{\"lon\":122.245761,\"lat\":37.259741},{\"lon\":122.247001,\"lat\":37.257386},{\"lon\":122.248097,\"lat\":37.255167},{\"lon\":122.249327,\"lat\":37.252668},{\"lon\":122.25081,\"lat\":37.249709},{\"lon\":122.252121,\"lat\":37.24698},{\"lon\":122.253118,\"lat\":37.244502},{\"lon\":122.253549,\"lat\":37.243202},{\"lon\":122.253657,\"lat\":37.242929},{\"lon\":122.253936,\"lat\":37.241845},{\"lon\":122.254277,\"lat\":37.240272},{\"lon\":122.2546,\"lat\":37.238527},{\"lon\":122.255032,\"lat\":37.23625},{\"lon\":122.25566,\"lat\":37.23296},{\"lon\":122.257026,\"lat\":37.227767},{\"lon\":122.258221,\"lat\":37.225303},{\"lon\":122.258993,\"lat\":37.224017},{\"lon\":122.259029,\"lat\":37.223952},{\"lon\":122.259415,\"lat\":37.223471},{\"lon\":122.260161,\"lat\":37.222429},{\"lon\":122.261041,\"lat\":37.221193},{\"lon\":122.261931,\"lat\":37.219979},{\"lon\":122.263053,\"lat\":37.218449},{\"lon\":122.263871,\"lat\":37.217256},{\"lon\":122.264814,\"lat\":37.215597},{\"lon\":122.265479,\"lat\":37.214038},{\"lon\":122.265542,\"lat\":37.21398},{\"lon\":122.265641,\"lat\":37.21375},{\"lon\":122.265955,\"lat\":37.212744},{\"lon\":122.266224,\"lat\":37.211472},{\"lon\":122.266476,\"lat\":37.208879},{\"lon\":122.266575,\"lat\":37.206436},{\"lon\":122.266656,\"lat\":37.204244},{\"lon\":122.266745,\"lat\":37.201901},{\"lon\":122.266862,\"lat\":37.199501},{\"lon\":122.266961,\"lat\":37.197015},{\"lon\":122.267042,\"lat\":37.194622},{\"lon\":122.26715,\"lat\":37.192164},{\"lon\":122.267518,\"lat\":37.189109},{\"lon\":122.268488,\"lat\":37.186536},{\"lon\":122.270042,\"lat\":37.184099},{\"lon\":122.271722,\"lat\":37.181749},{\"lon\":122.273168,\"lat\":37.179729},{\"lon\":122.274588,\"lat\":37.177652},{\"lon\":122.275836,\"lat\":37.175682},{\"lon\":122.276303,\"lat\":37.174518},{\"lon\":122.276699,\"lat\":37.173756},{\"lon\":122.276735,\"lat\":37.17359},{\"lon\":122.276995,\"lat\":37.172756},{\"lon\":122.277283,\"lat\":37.171354},{\"lon\":122.277525,\"lat\":37.16978},{\"lon\":122.277849,\"lat\":37.16781},{\"lon\":122.278244,\"lat\":37.165251},{\"lon\":122.278585,\"lat\":37.163065},{\"lon\":122.27898,\"lat\":37.160772},{\"lon\":122.279187,\"lat\":37.159326},{\"lon\":122.279223,\"lat\":37.159017},{\"lon\":122.279313,\"lat\":37.158607},{\"lon\":122.279474,\"lat\":37.157637},{\"lon\":122.279753,\"lat\":37.156191},{\"lon\":122.280409,\"lat\":37.153826},{\"lon\":122.280777,\"lat\":37.152977},{\"lon\":122.2819,\"lat\":37.150849},{\"lon\":122.283113,\"lat\":37.14854},{\"lon\":122.284406,\"lat\":37.146139},{\"lon\":122.286023,\"lat\":37.143082},{\"lon\":122.287919,\"lat\":37.139608},{\"lon\":122.289365,\"lat\":37.136911},{\"lon\":122.290443,\"lat\":37.134574},{\"lon\":122.291036,\"lat\":37.132172},{\"lon\":122.29117,\"lat\":37.129582},{\"lon\":122.291206,\"lat\":37.127165},{\"lon\":122.291251,\"lat\":37.124784},{\"lon\":122.291305,\"lat\":37.122813},{\"lon\":122.29126,\"lat\":37.122144},{\"lon\":122.29126,\"lat\":37.122144},{\"lon\":122.291269,\"lat\":37.122159},{\"lon\":122.291278,\"lat\":37.122144},{\"lon\":122.291278,\"lat\":37.122166},{\"lon\":122.291296,\"lat\":37.12218}]}";
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	// private ProgressBar pb;// 进度条
	private SeekBar seekBar1;
	private ImageView playIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// pb = (ProgressBar) findViewById(R.id.progressBar1);
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		playIv = (ImageView) findViewById(R.id.location_ivPlay);
		playIv.setOnClickListener(this);
		parseJson();// 解析json
		initBaiduMap();
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			// 松开时调用
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			// 按下时调用
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			// 拖动条发生变化时
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser == true) {
					mMapView.getMap().clear();
					
					PS = (routeList.size()-1)*progress/100;
					OverlayOptions polyLine1 = new PolylineOptions()
					.width(5)
					.color(0xFF1694FF)
					.points(routeList.subList(0,PS));
			mBaiduMap.addOverlay(polyLine1);
					setProgress(progress);
					// 点击播放按钮会调用是因为这时候进度条也同步在走了
					mHandler.sendEmptyMessage(SEEKBARPG);
				}
			}
		});
	}
	private int PS;
	private final static int SEEKBARPG = 134;

	private void initBaiduMap() {
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(12.0f));
		addRouteLine(routeList);// 添加路线
	}

	private Marker routeMarker;
	private int ROUTETIME = 300;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == ROUTE) {
				mHandler.sendEmptyMessage(PROGRESS);
				if (routeIndex == routeList.size() - 1) {
					routeFlag = false;
					playIv.setImageResource(R.drawable.play);
					Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_LONG)
							.show();
					routeIndex = 0;
					if (routeMarker != null) {
						routeMarker.remove();
						routeMarker = null;
					}
					addRouteLine(routeList);
					return;
				}
				if (routeIndex != 0) {
					OverlayOptions polyLine = new PolylineOptions()
							.width(5)
							.color(0xFF1694FF)
							.points(routeList.subList(routeIndex - 1,
									routeIndex + 1));
					mBaiduMap.addOverlay(polyLine);
				}
				// 页面跟随移动,注掉这行就是在原图上绘制
				// moveToLocation(routeList.get(routeIndex), false);
				if (routeMarker == null) {
					OverlayOptions cur = new MarkerOptions()
							.position(routeList.get(routeIndex++))
							.perspective(false).anchor(0.5f, 0.5f).zIndex(10);
				} else {
					routeMarker.setPosition(routeList.get(routeIndex++));
				}
				mHandler.sendEmptyMessageDelayed(ROUTE, ROUTETIME);
			}

			if (msg.what == PROGRESS) {
				if (routeIndex == 0) {// 因为播放完毕时routeIndex被赋值成了0，不写进度条会直接跳到0的位置
					setProgss(100);
				} else {
					setProgss((routeIndex + 1) * 100 / routeList.size());
				}
			}
			if(msg.what == SEEKBARPG){
			routeIndex = PS;
				if (routeIndex == routeList.size() - 1) {
					//routeFlag = false;
				//	playIv.setImageResource(R.drawable.play);
					Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_LONG)
							.show();
					
					//routeIndex = 0;
					if (routeMarker != null) {
						routeMarker.remove();
						routeMarker = null;
					}
					addRouteLine(routeList);
					return;
				}
				if (routeIndex != 0) {
					OverlayOptions polyLine = new PolylineOptions()
							.width(5)
							.color(0xFF1694FF)
							.points(routeList.subList(routeIndex - 1,
									routeIndex + 1));
					mBaiduMap.addOverlay(polyLine);

				}
				// 页面跟随移动,注掉这行就是在原图上绘制
				// moveToLocation(routeList.get(routeIndex), false);
				if (routeMarker == null) {
					OverlayOptions cur = new MarkerOptions()
							.position(routeList.get(routeIndex++))
							.perspective(false).anchor(0.5f, 0.5f).zIndex(10);
				} else {
					routeMarker.setPosition(routeList.get(routeIndex++));
				}
				
			}
		};
	};

	/**
	 * 添加路线
	 * 
	 * @param routeList
	 */
	private void addRoute(List<LatLng> routeList) {
		mBaiduMap.clear();
		// 百度最多支持10000个点连线
		if (routeList.size() > 10000) {
			routeList = routeList.subList(0, 10000);
		}
		mBaiduMap.addOverlay(new PolylineOptions().width(5).color(0xFF1694FF)
				.points(routeList));
		moveToLocation(routeList.get(routeList.size() / 2), true);

	}
	
	private void setProgss(int index) {
		seekBar1.setProgress(index);
	}

	private final static int PROGRESS = 1234;

	/**
	 * 添加路线
	 * 
	 * @param routeList
	 */
	private void addRouteLine(List<LatLng> routeList) {
		mBaiduMap.clear();
		// 百度最多支持10000个点连线
		if (routeList.size() > 10000) {
			routeList = routeList.subList(0, 10000);
		}
		mBaiduMap.addOverlay(new PolylineOptions().width(5).color(0xFF1694FF)
				.points(routeList));
		moveToLocation(routeList.get(routeList.size() / 2), true);

	}

	/**
	 * 移动到指定位置 并缩放
	 * 
	 * @param latlng
	 */
	private void moveToLocation(LatLng latlng, boolean flag) {
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latlng);// 设置新的中心点
		mBaiduMap.animateMapStatus(u);
		// if (flag && mBaiduMap.getMapStatus().zoom < 12.0f) {
		// // 加个延时播放的效果,就可以有先平移 ，再缩放的效果
		// mTimer.start();
		// }
	}

	// private CountDownTimer mTimer = new CountDownTimer(2000, 2000) {
	//
	// @Override
	// public void onTick(long millisUntilFinished) {
	//
	// }
	//
	// @Override
	// public void onFinish() {
	// if (mBaiduMap != null) {
	// MapStatusUpdate u1 = MapStatusUpdateFactory.zoomTo(12.0f);
	// mBaiduMap.animateMapStatus(u1);
	// }
	// }
	// };

	/**
	 * 解析json
	 */
	private void parseJson() {
		try {
			JSONObject jObject = new JSONObject(routeJson);
			JSONArray jArray = jObject.getJSONArray("list");
			System.out.println(jArray.length());
			JSONObject object = null;
			routeList = new ArrayList<LatLng>();
			for (int i = 0; i < jArray.length(); i++) {
				object = jArray.getJSONObject(i);
				routeList.add(new LatLng(object.getDouble("lat"), object
						.getDouble("lon")));
			}
			System.out.println("latlng size=" + routeList.size());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	List<LatLng> points2 = new ArrayList<LatLng>();

	private int routeIndex;
	private boolean routeFlag;
	private final int ROUTE = 0;

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.location_ivPlay) {
			if (routeList == null || routeList.size() <= 0) {
				return;
			}
			routeFlag = !routeFlag;
			playIv.setImageResource(routeFlag ? R.drawable.pause
					: R.drawable.play);

			if (routeFlag) {
				if (routeIndex == 0) {
					mBaiduMap.clear();
					routeMarker = null;
				}
				mHandler.sendEmptyMessageDelayed(ROUTE, 0);
			} else {
				mHandler.removeMessages(0);
			}
		}

	}

}
