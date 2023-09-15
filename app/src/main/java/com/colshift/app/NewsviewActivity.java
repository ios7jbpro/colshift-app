package com.colshift.app;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

public class NewsviewActivity extends AppCompatActivity {
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private ImageView imageview1;
	private TextView title;
	private WebView webview1;
	
	private SharedPreferences tempCache;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newsview);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = findViewById(R.id.linear2);
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		title = findViewById(R.id.title);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		tempCache = getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		//webviewOnProgressChanged
		webview1.setWebChromeClient(new WebChromeClient() {
				@Override public void onProgressChanged(WebView view, int _newProgress) {
					
				}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				title.setText(webview1.getTitle());
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				title.setText(webview1.getTitle());
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		webview1.loadUrl(tempCache.getString("newsLink", ""));
		switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
			    case Configuration.UI_MODE_NIGHT_YES:
			if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK));
			WebSettingsCompat.setForceDark(webview1.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
			        break;
			    case Configuration.UI_MODE_NIGHT_NO:
			if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK));
			WebSettingsCompat.setForceDark(webview1.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
			imageview1.setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
			break; 
		}
	}
	
	@Override
	public void onBackPressed() {
		if (webview1.canGoBack()) {
			webview1.goBack();
		}
		else {
			finish();
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