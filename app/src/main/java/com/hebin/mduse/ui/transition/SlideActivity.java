package com.hebin.mduse.ui.transition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Window;
import android.view.WindowManager;

import com.hebin.mduse.R;


public class SlideActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) ;
		// 透明状态栏
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		getWindow().setAllowEnterTransitionOverlap(true);
		getWindow().setAllowReturnTransitionOverlap(true);
		Slide slide = new Slide();
		slide.setDuration(1000);
		getWindow().setEnterTransition(slide);
		getWindow().setExitTransition(slide);


		setContentView(R.layout.activity_animation1);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
//		finishAfterTransition();
	}
}
