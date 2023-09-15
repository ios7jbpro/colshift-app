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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class DanbooruFragmentFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private String combinedAPIURL = "";
	private String apiID = "";
	private String imageVanillaURL = "";
	private String imageModifiedURL = "";
	private String directoryTag = "";
	private double pageID = 0;
	private String app = "";
	private String activity = "";
	
	private ArrayList<HashMap<String, Object>> booruWallMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> returnedCategoryList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> returnedSubCategoryList = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	private ListView listview2;
	
	private RequestNetwork pullDanBooruJSON;
	private RequestNetwork.RequestListener _pullDanBooruJSON_request_listener;
	private SharedPreferences booruAPI;
	private Intent loadBooruWallAPI = new Intent();
	private SharedPreferences tempCache;
	private TimerTask customPageAwaitRefresher;
	private Intent customPageIntent = new Intent();
	private RequestNetwork fetchActivites;
	private RequestNetwork.RequestListener _fetchActivites_request_listener;
	private RequestNetwork fetchPerCategoryList;
	private RequestNetwork.RequestListener _fetchPerCategoryList_request_listener;
	private Intent intent = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.danbooru_fragment_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		listview1 = _view.findViewById(R.id.listview1);
		listview2 = _view.findViewById(R.id.listview2);
		pullDanBooruJSON = new RequestNetwork((Activity) getContext());
		booruAPI = getContext().getSharedPreferences("booruAPI", Activity.MODE_PRIVATE);
		tempCache = getContext().getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		fetchActivites = new RequestNetwork((Activity) getContext());
		fetchPerCategoryList = new RequestNetwork((Activity) getContext());
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				listview1.setVisibility(View.GONE);
				listview2.setVisibility(View.VISIBLE);
				tempCache.edit().putString("activityListCanGoBack", "1").commit();
				fetchPerCategoryList.startRequestNetwork(RequestNetworkController.GET, returnedCategoryList.get((int)_position).get("link").toString(), "", _fetchPerCategoryList_request_listener);
			}
		});
		
		listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				app = returnedSubCategoryList.get((int)_position).get("app").toString();
				activity = returnedSubCategoryList.get((int)_position).get("activity").toString();
				try{
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.setClassName(app, activity);
					startActivity(intent);
				}catch(Exception e){
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Activity not found.");
				}
			}
		});
		
		_pullDanBooruJSON_request_listener = new RequestNetwork.RequestListener() {
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
		
		_fetchActivites_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				returnedCategoryList = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview1.setAdapter(new Listview1Adapter(returnedCategoryList));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_fetchPerCategoryList_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				returnedSubCategoryList = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview2.setAdapter(new Listview2Adapter(returnedSubCategoryList));
				((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getContext().getApplicationContext(), _tag.concat(_message));
			}
		};
	}
	
	private void initializeLogic() {
		fetchActivites.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/ios7jbpro/colshiftdatabase/main/activitiesdatabase.json", "", _fetchActivites_request_listener);
		listview2.setVisibility(View.GONE);
		customPageAwaitRefresher = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (tempCache.getString("activityListCanGoBack", "").equals("1")) {
							listview1.setVisibility(View.GONE);
							listview2.setVisibility(View.VISIBLE);
						}
						else {
							listview2.setVisibility(View.GONE);
							listview1.setVisibility(View.VISIBLE);
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(customPageAwaitRefresher, (int)(0), (int)(150));
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.categorylist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(returnedCategoryList.get((int)_position).get("name").toString());
			
			return _view;
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.categorylist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(returnedSubCategoryList.get((int)_position).get("name").toString());
			
			return _view;
		}
	}
}