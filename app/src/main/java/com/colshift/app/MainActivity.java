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
import androidx.fragment.app.FragmentStatePagerAdapter;
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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager2.*;
import androidx.webkit.*;
import com.bumptech.glide.*;
import com.google.android.material.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> ipMap = new HashMap<>();
	private double firstAPICheck = 0;
	private double isAltDiskUp = 0;
	
	private ArrayList<HashMap<String, Object>> repoStatusMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> returnedBlacklistIP = new ArrayList<>();
	
	private LinearLayout linear1;
	private ViewPager viewpager1;
	private LinearLayout bottombarroot;
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout wallsbutton;
	private TextView textview3;
	private ImageView imageview1;
	private LinearLayout requestbutton;
	private TextView textview4;
	private ImageView imageview2;
	private LinearLayout linear5;
	private TextView textview5;
	private ImageView imageview4;
	private LinearLayout settingsbutton;
	private TextView textview6;
	private ImageView imageview3;
	
	private PageLoaderInitFragmentAdapter pageLoaderInit;
	private SharedPreferences config;
	private TimerTask checkCurrentTab;
	private RequestNetwork checkRepoStatus;
	private RequestNetwork.RequestListener _checkRepoStatus_request_listener;
	private SharedPreferences tempCache;
	private Intent launchErrorPage = new Intent();
	private TimerTask githubDialogCheck;
	private RequestNetwork getDeviceIp;
	private RequestNetwork.RequestListener _getDeviceIp_request_listener;
	private RequestNetwork getIpBlacklist;
	private RequestNetwork.RequestListener _getIpBlacklist_request_listener;
	private Intent blacklistedIpMessageIntent = new Intent();
	private RequestNetwork sendBlacklistResult;
	private RequestNetwork.RequestListener _sendBlacklistResult_request_listener;
	private SharedPreferences favoriteSystem;
	private SharedPreferences favSysCounter;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		viewpager1 = findViewById(R.id.viewpager1);
		bottombarroot = findViewById(R.id.bottombarroot);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		linear4 = findViewById(R.id.linear4);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		wallsbutton = findViewById(R.id.wallsbutton);
		textview3 = findViewById(R.id.textview3);
		imageview1 = findViewById(R.id.imageview1);
		requestbutton = findViewById(R.id.requestbutton);
		textview4 = findViewById(R.id.textview4);
		imageview2 = findViewById(R.id.imageview2);
		linear5 = findViewById(R.id.linear5);
		textview5 = findViewById(R.id.textview5);
		imageview4 = findViewById(R.id.imageview4);
		settingsbutton = findViewById(R.id.settingsbutton);
		textview6 = findViewById(R.id.textview6);
		imageview3 = findViewById(R.id.imageview3);
		pageLoaderInit = new PageLoaderInitFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		checkRepoStatus = new RequestNetwork(this);
		tempCache = getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		getDeviceIp = new RequestNetwork(this);
		getIpBlacklist = new RequestNetwork(this);
		sendBlacklistResult = new RequestNetwork(this);
		favoriteSystem = getSharedPreferences("favoriteSystem", Activity.MODE_PRIVATE);
		favSysCounter = getSharedPreferences("favSysCounter", Activity.MODE_PRIVATE);
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)0);
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)2);
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)3);
			}
		});
		
		wallsbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)0);
			}
		});
		
		requestbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)2);
			}
		});
		
		settingsbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)3);
			}
		});
		
		_checkRepoStatus_request_listener = new RequestNetwork.RequestListener() {
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
		
		_getDeviceIp_request_listener = new RequestNetwork.RequestListener() {
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
		
		_getIpBlacklist_request_listener = new RequestNetwork.RequestListener() {
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
		
		_sendBlacklistResult_request_listener = new RequestNetwork.RequestListener() {
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
	}
	
	private void initializeLogic() {
		firstAPICheck = 0;
		tempCache.edit().putString("wallCanGoBack", "0").commit();
		tempCache.edit().putString("activityListCanGoBack", "0").commit();
		if (favoriteSystem.getString("total", "").equals("")) {
			favoriteSystem.edit().putString("total", "0").commit();
		}
		favoriteSystem.edit().putString("name0", "").commit();
		favoriteSystem.edit().putString("link0", "").commit();
		if (favSysCounter.getString("link", "").equals("")) {
			favSysCounter.edit().putString("link", "0").commit();
		}
		if (favSysCounter.getString("name", "").equals("")) {
			favSysCounter.edit().putString("name", "0").commit();
		}
		// This part sets the user statusbar color as same as the color pulled from the XMLs
		switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
			    case Configuration.UI_MODE_NIGHT_YES:
			 
			        break;
			    case Configuration.UI_MODE_NIGHT_NO:
			imageview1.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
			imageview2.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
			imageview3.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
			break; 
		}
		viewpager1.setOffscreenPageLimit((int)4);
		wallsbutton.setClipToOutline(true);
		requestbutton.setClipToOutline(true);
		linear5.setClipToOutline(true);
		settingsbutton.setClipToOutline(true);
		// Sets how many pages do you have in the ViewPager. IF YOURE A NEWBIE DO NOT TOUCH THIS!
		pageLoaderInit.setTabCount(4);
		viewpager1.setAdapter(pageLoaderInit);
		checkCurrentTab = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (viewpager1.getCurrentItem() == 0) {
							wallsbutton.setBackgroundResource(R.drawable.activetab);
							imageview1.setColorFilter(getResources().getColor(R.color.textactivetab), PorterDuff.Mode.MULTIPLY);
						}
						else {
							wallsbutton.setBackgroundResource(R.drawable.roundedbgviolent);
							imageview1.setColorFilter(getResources().getColor(R.color.textprimary), PorterDuff.Mode.MULTIPLY);
						}
						if (viewpager1.getCurrentItem() == 1) {
							requestbutton.setBackgroundResource(R.drawable.activetab);
							imageview2.setColorFilter(getResources().getColor(R.color.textactivetab), PorterDuff.Mode.MULTIPLY);
						}
						else {
							requestbutton.setBackgroundResource(R.drawable.roundedbgviolent);
							imageview2.setColorFilter(getResources().getColor(R.color.textprimary), PorterDuff.Mode.MULTIPLY);
						}
						if (viewpager1.getCurrentItem() == 2) {
							linear5.setBackgroundResource(R.drawable.activetab);
							imageview4.setColorFilter(getResources().getColor(R.color.textactivetab), PorterDuff.Mode.MULTIPLY);
						}
						else {
							linear5.setBackgroundResource(R.drawable.roundedbgviolent);
							imageview4.setColorFilter(getResources().getColor(R.color.textprimary), PorterDuff.Mode.MULTIPLY);
						}
						if (viewpager1.getCurrentItem() == 3) {
							settingsbutton.setBackgroundResource(R.drawable.activetab);
							imageview3.setColorFilter(getResources().getColor(R.color.textactivetab), PorterDuff.Mode.MULTIPLY);
						}
						else {
							settingsbutton.setBackgroundResource(R.drawable.roundedbgviolent);
							imageview3.setColorFilter(getResources().getColor(R.color.textprimary), PorterDuff.Mode.MULTIPLY);
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(checkCurrentTab, (int)(0), (int)(200));
	}
	
	public class PageLoaderInitFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public PageLoaderInitFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new NewsFragmentActivity();
			}
			if (_position == 1) {
				return new FavoritesFargmentFragmentActivity();
			}
			if (_position == 2) {
				return new DanbooruFragmentFragmentActivity();
			}
			if (_position == 3) {
				return new SettingsDialogFragmentActivity();
			}
			return null;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (String.valueOf((long)(viewpager1.getCurrentItem())).equals("1")) {
			if (tempCache.getString("wallCanGoBack", "").equals("1")) {
				tempCache.edit().putString("wallCanGoBack", "0").commit();
			}
			else {
				viewpager1.setCurrentItem((int)0);
			}
		}
		else {
			if (String.valueOf((long)(viewpager1.getCurrentItem())).equals("2")) {
				if (tempCache.getString("activityListCanGoBack", "").equals("1")) {
					tempCache.edit().putString("activityListCanGoBack", "0").commit();
				}
				else {
					viewpager1.setCurrentItem((int)0);
				}
			}
			else {
				if (String.valueOf((long)(viewpager1.getCurrentItem())).equals("0")) {
					finish();
				}
				else {
					viewpager1.setCurrentItem((int)0);
				}
			}
		}
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