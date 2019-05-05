package com.hebin.mduse.ui.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.hebin.mduse.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransitionsActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt1, bt2, bt3;
    ImageView bt4;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //getWindow().setAllowEnterTransitionOverlap(true);
        //getWindow().setAllowReturnTransitionOverlap(true);

        setContentView(R.layout.activity_transitions);
        ButterKnife.bind(this);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明状态栏
        //		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (ImageView) findViewById(R.id.bt4);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Transition transition = null;
        switch (v.getId()) {
            case R.id.bt1:
                transition = new Explode();
                intent = new Intent(TransitionsActivity.this, ExplodeActivity.class);
                break;
            case R.id.bt2:
                //淡入淡出
                transition = new Fade();
                intent = new Intent(TransitionsActivity.this, FadeActivity.class);
                break;
            case R.id.bt3:
                //滑动
                transition = new Slide();
                intent = new Intent(TransitionsActivity.this, SlideActivity.class);
                break;
            case R.id.bt4:
                break;

        }

        if (v.getId() != R.id.bt4) {
            //设置专长动画时间
            transition.setDuration(1500);
            //设置进入和退出动画
            getWindow().setEnterTransition(transition);
            getWindow().setExitTransition(transition);
            //设置返回动画
            getWindow().setReturnTransition(transition);
            //设置重新进入的动画
            getWindow().setReenterTransition(transition);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(TransitionsActivity.this).toBundle());
        } else {
            TransitionSet set = new TransitionSet();
            set.addTransition(new Explode());
            set.addTransition(new Fade());
            set.setDuration(1000);
            getWindow().setReturnTransition(set);
            getWindow().setReenterTransition(set);
            getWindow().setEnterTransition(set);
            getWindow().setExitTransition(set);

            //设置共享动画切换时候的动画效果
            bt4.setTransitionName("bt4");
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition(new ChangeTransform());
            transitionSet.addTransition(new ChangeBounds());
            transitionSet.addTarget("bt4");
            transitionSet.setDuration(1500);
            getWindow().setSharedElementEnterTransition(transitionSet);
            getWindow().setSharedElementExitTransition(transitionSet);
            getWindow().setSharedElementReturnTransition(transitionSet);
            getWindow().setSharedElementReenterTransition(transitionSet);
            intent = new Intent(this, ShareActivity.class);


            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, bt4, "bt4");
            startActivity(intent, options.toBundle());

        }
    }
}
