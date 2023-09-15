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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class RequestFragmentFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview8;
	
	private RequestNetwork sendGetWallRequest;
	private RequestNetwork.RequestListener _sendGetWallRequest_request_listener;
	private SharedPreferences config;
	private TimerTask delayIpCheck;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.request_fragment_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		textview8 = _view.findViewById(R.id.textview8);
		sendGetWallRequest = new RequestNetwork((Activity) getContext());
		config = getContext().getSharedPreferences("config", Activity.MODE_PRIVATE);
		
		_sendGetWallRequest_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Request was successful!");
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Request failed! Do you have an internet connection?");
			}
		};
	}
	
	private void initializeLogic() {
	}
	
}