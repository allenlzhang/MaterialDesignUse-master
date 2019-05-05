package com.hebin.mduse.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hebin.mduse.R;
import com.hebin.mduse.ui.activity.WidgetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecycleViewFragment extends Fragment {


    @BindView(R.id.flb_black)
    FloatingActionButton flbBlack;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout    coordinatorLayout;
    @BindView(R.id.bt1)
    Button               bt1;
    @BindView(R.id.bt2)
    Button               bt2;
    @BindView(R.id.bt3)
    Button               bt3;
    @BindView(R.id.bt4)
    Button               bt4;
    @BindView(R.id.bt5)
    Button               bt5;
    @BindView(R.id.bt6)
    Button               bt6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snackbar, container, false);
        ButterKnife.bind(this, view);
        flbBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout,
                        "RecycleViewFragment", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(
                                        getActivity(),
                                        "snackbar OK clicked",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), WidgetActivity.class);
        switch (view.getId()) {
            case R.id.bt1:
                intent.putExtra("orientation", WidgetActivity.LIST_V);
                break;
            case R.id.bt2:
                intent.putExtra("orientation", WidgetActivity.LIST_H);
                break;
            case R.id.bt3:
                intent.putExtra("orientation", WidgetActivity.GRID_V);
                break;
            case R.id.bt4:
                intent.putExtra("orientation", WidgetActivity.GRID_H);
                break;
            case R.id.bt5:
                intent.putExtra("orientation", WidgetActivity.STAGGERED_GRID_V);
                break;
            case R.id.bt6:
                intent.putExtra("orientation", WidgetActivity.STAGGERED_GRID_H);
                break;
        }
        getActivity().startActivity(intent);
    }
}
