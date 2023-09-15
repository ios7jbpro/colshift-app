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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsDialogFragmentActivity extends DialogFragment {
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private TextView textview5;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private TextView textview2;
	private LinearLayout linear12;
	private LinearLayout linear3;
	private LinearLayout linear10;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear14;
	private LinearLayout linear11;
	private TextView textview3;
	private TextView textview6;
	private LinearLayout linear5;
	private EditText edittext1;
	private TextView textview7;
	private TextView textview8;
	private ImageView imageview1;
	private LinearLayout linear13;
	private TextView textview14;
	private TextView textview15;
	private CircleImageView circleimageview1;
	private TextView textview4;
	private CircleImageView circleimageview5;
	private TextView textview12;
	private CircleImageView circleimageview2;
	private TextView textview9;
	private CircleImageView circleimageview3;
	private TextView textview10;
	private CircleImageView circleimageview4;
	private TextView textview11;
	private CircleImageView circleimageview7;
	private TextView textview16;
	private CircleImageView circleimageview6;
	private TextView textview13;
	
	private SharedPreferences config;
	private RequestNetwork sendGetWallRequest;
	private RequestNetwork.RequestListener _sendGetWallRequest_request_listener;
	private Intent initiateForceCrash = new Intent();
	private Intent debugMenuLauncher = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear2 = _view.findViewById(R.id.linear2);
		textview5 = _view.findViewById(R.id.textview5);
		linear4 = _view.findViewById(R.id.linear4);
		linear6 = _view.findViewById(R.id.linear6);
		textview2 = _view.findViewById(R.id.textview2);
		linear12 = _view.findViewById(R.id.linear12);
		linear3 = _view.findViewById(R.id.linear3);
		linear10 = _view.findViewById(R.id.linear10);
		linear7 = _view.findViewById(R.id.linear7);
		linear8 = _view.findViewById(R.id.linear8);
		linear9 = _view.findViewById(R.id.linear9);
		linear14 = _view.findViewById(R.id.linear14);
		linear11 = _view.findViewById(R.id.linear11);
		textview3 = _view.findViewById(R.id.textview3);
		textview6 = _view.findViewById(R.id.textview6);
		linear5 = _view.findViewById(R.id.linear5);
		edittext1 = _view.findViewById(R.id.edittext1);
		textview7 = _view.findViewById(R.id.textview7);
		textview8 = _view.findViewById(R.id.textview8);
		imageview1 = _view.findViewById(R.id.imageview1);
		linear13 = _view.findViewById(R.id.linear13);
		textview14 = _view.findViewById(R.id.textview14);
		textview15 = _view.findViewById(R.id.textview15);
		circleimageview1 = _view.findViewById(R.id.circleimageview1);
		textview4 = _view.findViewById(R.id.textview4);
		circleimageview5 = _view.findViewById(R.id.circleimageview5);
		textview12 = _view.findViewById(R.id.textview12);
		circleimageview2 = _view.findViewById(R.id.circleimageview2);
		textview9 = _view.findViewById(R.id.textview9);
		circleimageview3 = _view.findViewById(R.id.circleimageview3);
		textview10 = _view.findViewById(R.id.textview10);
		circleimageview4 = _view.findViewById(R.id.circleimageview4);
		textview11 = _view.findViewById(R.id.textview11);
		circleimageview7 = _view.findViewById(R.id.circleimageview7);
		textview16 = _view.findViewById(R.id.textview16);
		circleimageview6 = _view.findViewById(R.id.circleimageview6);
		textview13 = _view.findViewById(R.id.textview13);
		config = getContext().getSharedPreferences("config", Activity.MODE_PRIVATE);
		sendGetWallRequest = new RequestNetwork((Activity) getContext());
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(initiateForceCrash);
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				debugMenuLauncher.setClass(getContext().getApplicationContext(), DebugmenuActivity.class);
				startActivity(debugMenuLauncher);
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				config.edit().putString("timeout", _charSeq).commit();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_sendGetWallRequest_request_listener = new RequestNetwork.RequestListener() {
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
		edittext1.setText(config.getString("timeout", ""));
		linear6.setClipToOutline(true);
	}
	
}