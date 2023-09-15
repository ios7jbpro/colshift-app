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
import com.bumptech.glide.Glide;
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

public class FavoritesFargmentFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> favWallMap = new HashMap<>();
	private double currentLoadProgress = 0;
	
	private ArrayList<HashMap<String, Object>> favWallListMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> categoryMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> returnedWallpapers = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	private GridView gridview1;
	
	private SharedPreferences favoriteSystem;
	private TimerTask repeaterWall;
	private SharedPreferences tempCache;
	private Intent loadWallDownload = new Intent();
	private RequestNetwork fetchCategories;
	private RequestNetwork.RequestListener _fetchCategories_request_listener;
	private RequestNetwork fetchWallpapers;
	private RequestNetwork.RequestListener _fetchWallpapers_request_listener;
	private Intent launchWallDownloader = new Intent();
	private TimerTask BackActionCheck;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.favorites_fargment_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		listview1 = _view.findViewById(R.id.listview1);
		gridview1 = _view.findViewById(R.id.gridview1);
		favoriteSystem = getContext().getSharedPreferences("favoriteSystem", Activity.MODE_PRIVATE);
		tempCache = getContext().getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		fetchCategories = new RequestNetwork((Activity) getContext());
		fetchWallpapers = new RequestNetwork((Activity) getContext());
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try{
					fetchWallpapers.startRequestNetwork(RequestNetworkController.GET, categoryMap.get((int)_position).get("link").toString(), "", _fetchWallpapers_request_listener);
					tempCache.edit().putString("wallCanGoBack", "1").commit();
					listview1.setVisibility(View.GONE);
					gridview1.setVisibility(View.VISIBLE);
				}catch(Exception e){
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
				}
			}
		});
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try{
					tempCache.edit().putString("wallLink", returnedWallpapers.get((int)_position).get("link").toString()).commit();
					tempCache.edit().putString("wallName", returnedWallpapers.get((int)_position).get("name").toString()).commit();
					launchWallDownloader.setClass(getContext().getApplicationContext(), WalldownloadActivity.class);
					startActivity(launchWallDownloader);
				}catch(Exception e){
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
				}
			}
		});
		
		_fetchCategories_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try{
					categoryMap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					listview1.setAdapter(new Listview1Adapter(categoryMap));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				}catch(Exception e){
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_fetchWallpapers_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try{
					returnedWallpapers = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					gridview1.setAdapter(new Gridview1Adapter(returnedWallpapers));
				}catch(Exception e){
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		fetchCategories.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/ios7jbpro/colshiftdatabase/main/colshiftcategories.json", "", _fetchCategories_request_listener);
		gridview1.setVisibility(View.GONE);
		BackActionCheck = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (tempCache.getString("wallCanGoBack", "").equals("1")) {
							gridview1.setVisibility(View.VISIBLE);
							listview1.setVisibility(View.GONE);
						}
						else {
							gridview1.setVisibility(View.GONE);
							listview1.setVisibility(View.VISIBLE);
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(BackActionCheck, (int)(0), (int)(150));
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
			
			try{
				textview1.setText(categoryMap.get((int)_position).get("category").toString());
			}catch(Exception e){
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
			}
			
			return _view;
		}
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.wallpaperlist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView wallimage = _view.findViewById(R.id.wallimage);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView wallname = _view.findViewById(R.id.wallname);
			final TextView authorname = _view.findViewById(R.id.authorname);
			
			try{
				wallname.setText(returnedWallpapers.get((int)_position).get("name").toString());
				authorname.setText(" ");
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(returnedWallpapers.get((int)_position).get("link").toString())).into(wallimage);
			}catch(Exception e){
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "A crash detected. Please report this to the devs.");
			}
			
			return _view;
		}
	}
}