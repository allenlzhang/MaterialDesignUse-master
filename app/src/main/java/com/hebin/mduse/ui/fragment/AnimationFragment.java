package com.hebin.mduse.ui.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hebin.mduse.R;
import com.hebin.mduse.ui.transition.TransitionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationFragment extends Fragment {



    @BindView(R.id.bt1)
    Button   bt1;
    @BindView(R.id.bt2)
    Button   bt2;
    @BindView(R.id.curved)
    TextView curved;
    @BindView(R.id.state_anim)
    TextView stateAnim;
    @BindView(R.id.vector_anim)
    TextView vectorAnim;
    @BindView(R.id.transitions)
    Button   transitions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_animation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.curved, R.id.state_anim, R.id.vector_anim, R.id.transitions})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                /**
                 * 参数1:空间本身
                 * 参数2和参数3:圆心的坐标x,y
                 * 参数4:动画开始时圆的半径
                 * 参数5:动画结束是圆的半径
                 */
                Animator animator1 = ViewAnimationUtils.createCircularReveal(bt1, bt1.getWidth() / 2, bt1.getHeight() / 2, bt1.getWidth(), 0);
                //控制动画的过程
                animator1.setInterpolator(new CycleInterpolator(3f));
                animator1.setDuration(1000);
                animator1.start();

                break;
            case R.id.bt2:
                Animator animator2 = ViewAnimationUtils.createCircularReveal(bt2, 0, bt2.getHeight(), 0, (float) Math.hypot(bt2.getWidth(), bt2.getHeight()));
                animator2.setDuration(1000);
                animator2.start();
                break;
            case R.id.curved:
                curved();
                break;
            case R.id.state_anim:
                break;
            case R.id.vector_anim:
                vector();
                break;
            case R.id.transitions:
                transitions();
                break;
        }
    }

    private void curved() {
        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(1000, 300, 700, 300);


        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(curved, View.X, View.Y, path);

        Path p = new Path();
        p.lineTo(0.6f, 0.9f);
        p.lineTo(0.75f, 0.2f);
        p.lineTo(1f, 1f);
        mAnimator.setInterpolator(new PathInterpolator(p));

        mAnimator.setDuration(3000);
        mAnimator.start();
    }

    public void vector() {
        Drawable drawable = vectorAnim.getBackground();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    public void transitions() {
        Intent intent = new Intent(getActivity(), TransitionsActivity.class);
        startActivity(intent);

    }
}
