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
import android.widget.*;
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

public class GithuberrorActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private TextView textview3;
	private TextView textview4;
	
	private SharedPreferences tempCache;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.githuberror);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		tempCache = getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tempCache.edit().putString("githubDialogOption", "force").commit();
				finish();
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tempCache.edit().putString("githubDialogOption", "exit").commit();
				finish();
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	public void _convertToBottomSheet() {
	}
	private androidx.coordinatorlayout.widget.CoordinatorLayout mCoordinatorLayout;
	@Override
	public void finish(){
		com.google.android.material.bottomsheet.BottomSheetBehavior.from(mCoordinatorLayout.getChildAt(0)).setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
	}
	private void superFinish(){
		super.finish();
	}
	 @Override
	public void setContentView(int layId){
			if(mCoordinatorLayout == null){
					overridePendingTransition(0,0);
					mCoordinatorLayout = new androidx.coordinatorlayout.widget.CoordinatorLayout(this);
					makeActivityTransparent();
			mCoordinatorLayout.setBackgroundColor(0x80000000);
					mCoordinatorLayout.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick (View v){
										finish();
							}
					});
			}
			mCoordinatorLayout.removeAllViews();
			androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			final com.google.android.material.bottomsheet.BottomSheetBehavior behavior = new com.google.android.material.bottomsheet.BottomSheetBehavior();
			params.setBehavior(behavior);
			behavior.setBottomSheetCallback(new com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback(){
					@Override
					public void onSlide(View bottomSheet, float slideOffset){
							
					}
					@Override
					public void onStateChanged(View bottomSheet, int newState){
							if(newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED){
									superFinish();
					overridePendingTransition(0,0);
							}
					}
			});
			View inflated = getLayoutInflater().inflate(layId,null);	
			mCoordinatorLayout.addView(inflated,params);
			
			if(mCoordinatorLayout.getParent()!= null)((ViewGroup)mCoordinatorLayout.getParent()).removeView(mCoordinatorLayout);
			setContentView(mCoordinatorLayout);
		inflated.post(new Runnable(){
			@Override
			            public void run() {
				behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED);
			}});
		
	}
	
	private void makeActivityTransparent(){
		getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
			try {
					java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 
					getActivityOptions.setAccessible(true);
					Object options = getActivityOptions.invoke(this);
					Class<?>[] classes = Activity.class.getDeclaredClasses();
					Class<?> translucentConversionListenerClazz = null;
					for (Class clazz : classes) { 
							if (clazz.getSimpleName().contains("TranslucentConversionListener")) { 
									translucentConversionListenerClazz = clazz;
							} 
					} 
					java.lang.reflect.Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class); 
					convertToTranslucent.setAccessible(true); 
					convertToTranslucent.invoke(this, null, options); 
			} catch (Throwable t) {
			}
	}
	
	{
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