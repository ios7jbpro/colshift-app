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
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.google.android.material.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class DebugmenuActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private TextView textview1;
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear15;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private TextView textview2;
	private TextView textview14;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private TextView textview10;
	private TextView textview11;
	private TextView textview12;
	private TextView textview13;
	
	private Intent debugMenuLauncherIntent = new Intent();
	private RequestNetwork sendRemoteIP;
	private RequestNetwork.RequestListener _sendRemoteIP_request_listener;
	private SharedPreferences config;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.debugmenu);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear15 = findViewById(R.id.linear15);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		textview2 = findViewById(R.id.textview2);
		textview14 = findViewById(R.id.textview14);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		textview10 = findViewById(R.id.textview10);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		textview13 = findViewById(R.id.textview13);
		sendRemoteIP = new RequestNetwork(this);
		config = getSharedPreferences("config", Activity.MODE_PRIVATE);
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sendRemoteIP.startRequestNetwork(RequestNetworkController.GET, "https://api.telegram.org/bot6671863690:AAEAQwdt-Cv68FghIC4mc6BMW7ZecxCbs8s/sendMessage?chat_id=-1001937666983&text=REMOTE_IP_LOGIN_".concat(config.getString("deviceIp", "")), "", _sendRemoteIP_request_listener);
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), WalldownloadActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), SettingsDialogFragmentActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), Setwall1Activity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), RequestFragmentFragmentActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), DanbooruFragmentFragmentActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), DebugActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), SbcustompageActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), GithuberrorActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncherIntent.setClass(getApplicationContext(), DebugmenuActivity.class);
				startActivity(debugMenuLauncherIntent);
			}
		});
		
		_sendRemoteIP_request_listener = new RequestNetwork.RequestListener() {
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