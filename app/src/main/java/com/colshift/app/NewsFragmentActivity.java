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

public class NewsFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private ArrayList<HashMap<String, Object>> returnedPostMap = new ArrayList<>();
	
	private FrameLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	
	private SharedPreferences tempCache;
	private TimerTask webviewCacher;
	private RequestNetwork fetchNews;
	private RequestNetwork.RequestListener _fetchNews_request_listener;
	private Intent launchNewWebview = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.news_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		listview1 = _view.findViewById(R.id.listview1);
		tempCache = getContext().getSharedPreferences("tempCache", Activity.MODE_PRIVATE);
		fetchNews = new RequestNetwork((Activity) getContext());
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				launchNewWebview.setClass(getContext().getApplicationContext(), NewsviewActivity.class);
				tempCache.edit().putString("newsLink", returnedPostMap.get((int)_position).get("link").toString()).commit();
				startActivity(launchNewWebview);
			}
		});
		
		_fetchNews_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				returnedPostMap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview1.setAdapter(new Listview1Adapter(returnedPostMap));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		fetchNews.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/ios7jbpro/colshiftdatabase/main/news.json", "", _fetchNews_request_listener);
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
				_view = _inflater.inflate(R.layout.newslist, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout imagecontainer = _view.findViewById(R.id.imagecontainer);
			final TextView title = _view.findViewById(R.id.title);
			final TextView desc = _view.findViewById(R.id.desc);
			final ImageView imagebanner = _view.findViewById(R.id.imagebanner);
			
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(returnedPostMap.get((int)_position).get("banner").toString())).into(imagebanner);
			title.setText(returnedPostMap.get((int)_position).get("title").toString());
			desc.setText(returnedPostMap.get((int)_position).get("desc").toString());
			imagecontainer.setClipToOutline(true);
			
			return _view;
		}
	}
}