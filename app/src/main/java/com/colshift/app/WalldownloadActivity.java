package com.colshift.app;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.annotation.experimental.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.arch.core.*;
import androidx.asynclayoutinflater.*;
import androidx.cardview.*;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.*;
import androidx.coordinatorlayout.*;
import androidx.core.*;
import androidx.core.ktx.*;
import androidx.cursoradapter.*;
import androidx.customview.*;
import androidx.documentfile.*;
import androidx.drawerlayout.*;
import androidx.dynamicanimation.*;
import androidx.emoji2.*;
import androidx.emoji2.viewsintegration.*;
import androidx.fragment.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.interpolator.*;
import androidx.legacy.coreui.*;
import androidx.legacy.coreutils.*;
import androidx.lifecycle.livedata.*;
import androidx.lifecycle.livedata.core.*;
import androidx.lifecycle.process.*;
import androidx.lifecycle.runtime.*;
import androidx.lifecycle.viewmodel.*;
import androidx.lifecycle.viewmodel.savedstate.*;
import androidx.loader.*;
import androidx.localbroadcastmanager.*;
import androidx.print.*;
import androidx.recyclerview.*;
import androidx.savedstate.*;
import androidx.slidingpanelayout.*;
import androidx.startup.*;
import androidx.swiperefreshlayout.*;
import androidx.tracing.*;
import androidx.transition.*;
import androidx.vectordrawable.*;
import androidx.vectordrawable.animated.*;
import androidx.versionedparcelable.*;
import androidx.viewpager.*;
import androidx.viewpager2.*;
import androidx.webkit.*;
import com.bumptech.glide.*;
import com.bumptech.glide.Glide;
import com.google.android.material.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class WalldownloadActivity extends AppCompatActivity {
	
	private String currentWallLink = "";
	private String currentWallName = "";
	private HashMap<String, Object> wallStore = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> walljsonlistmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> wallMapStore = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private CardView cardview1;
	private LinearLayout linear4;
	private ImageView imageview2;
	private TextView textview1;
	private LinearLayout linear3;
	private ImageView imageview1;
	private Button button1;
	private Button button2;
	
	private SharedPreferences selectedItemList;
	private RequestNetwork fetchJson;
	private RequestNetwork.RequestListener _fetchJson_request_listener;
	private RequestNetwork downloadWall;
	private RequestNetwork.RequestListener _downloadWall_request_listener;
	private Intent intentDownloadRemoteWall = new Intent();
	private SharedPreferences wallLink;
	private Intent setWallLoader = new Intent();
	private SharedPreferences config;
	private SharedPreferences temporaryCache;
	private SharedPreferences booruAPI;
	private RequestNetwork requestDanBooruWallpaper;
	private RequestNetwork.RequestListener _requestDanBooruWallpaper_request_listener;
	private SharedPreferences favoriteSystem;
	private SharedPreferences favSysCounter;
	private SharedPreferences tempCache;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.walldownload);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		cardview1 = findViewById(R.id.cardview1);
		linear4 = findViewById(R.id.linear4);
		imageview2 = findViewById(R.id.imageview2);
		textview1 = findViewById(R.id.textview1);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		selectedItemList = getSharedPreferences("selectedItemList", Activity.MODE_PRIVATE);
		fetchJson = new RequestNetwork(this);
		downloadWall = new RequestNetwork(this);
		wallLink = getSharedPreferences("wallLink", Activity.MODE_PRIVATE);
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		temporaryCache = getSharedPreferences("temporaryCache", Activity.MODE_PRIVATE);
		booruAPI = getSharedPreferences("booruAPI", Activity.MODE_PRIVATE);
		requestDanBooruWallpaper = new RequestNetwork(this);
		favoriteSystem = getSharedPreferences("favoriteSystem", Activity.MODE_PRIVATE);
		favSysCounter = getSharedPreferences("favSysCounter", Activity.MODE_PRIVATE);
		tempCache = getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentDownloadRemoteWall.setAction(Intent.ACTION_VIEW);
				intentDownloadRemoteWall.setData(Uri.parse(tempCache.getString("wallLink", "")));
				startActivity(intentDownloadRemoteWall);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				setWallLoader.setClass(getApplicationContext(), Setwall1Activity.class);
				startActivity(setWallLoader);
			}
		});
		
		_fetchJson_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				walljsonlistmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				Glide.with(getApplicationContext()).load(Uri.parse(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString())).into(imageview1);
				textview1.setText(walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("name").toString());
				wallLink.edit().putString("wallLink", walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString()).commit();
				
				currentWallLink = walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("link").toString();
				currentWallName = walljsonlistmap.get((int)Double.parseDouble(selectedItemList.getString("selectedWall", ""))).get("name").toString();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_downloadWall_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_requestDanBooruWallpaper_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				SketchwareUtil.showMessage(getApplicationContext(), "Request was successful!");
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), "Request failed! Do you have an active internet connection?");
			}
		};
	}
	
	private void initializeLogic() {
		Glide.with(getApplicationContext()).load(Uri.parse(tempCache.getString("wallLink", ""))).into(imageview1);
		textview1.setText(tempCache.getString("wallName", ""));
		// Sets the statusbar color from XML and changes the close button color on light theme
		switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
			    case Configuration.UI_MODE_NIGHT_YES:
			 
			        break;
			    case Configuration.UI_MODE_NIGHT_NO:
			imageview2.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
			break; 
		}
		// Overrides corners so that the content is rounded
		cardview1.setCardBackgroundColor(0xFF000000);
		cardview1.setPreventCornerOverlap(true);
		button1.setClipToOutline(true);
		button2.setClipToOutline(true);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}