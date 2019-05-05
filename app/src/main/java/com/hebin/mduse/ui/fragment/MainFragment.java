package com.hebin.mduse.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebin.mduse.R;
import com.hebin.mduse.adapter.RecyclerAdapter;
import com.hebin.mduse.entity.RecyclerBean;
import com.hebin.mduse.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    RecyclerView       recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    //    @BindView(R.id.vp_books)
    //    AutoScrollViewPager vpBooks;
    //    @BindView(R.id.ll_point)
    //    LinearLayout        llPoint;
    //    @BindView(R.id.header)
    //    RecyclerViewHeader  header;
    //    @BindView(R.id.recyclerView_gird)
    //    RecyclerView        recyclerViewGird;

    private List<RecyclerBean> mList     = new ArrayList<>();
    private String[]           title     = {"测试文字_01", "测试文字_02", "测试文字_03", "测试文字_04", "测试文字_05", "测试文字_06", "测试文字_07", "测试文字_08", "测试文字_09", "测试文字_10"};
    private int[]              imgPath   = {R.mipmap.ic_recyclerview_01, R.mipmap.ic_recyclerview_02, R.mipmap.ic_recyclerview_03, R.mipmap.ic_recyclerview_04, R.mipmap.ic_recyclerview_05,
            R.mipmap.ic_recyclerview_06, R.mipmap.ic_recyclerview_07, R.mipmap.ic_recyclerview_08, R.mipmap.ic_recyclerview_09, R.mipmap.ic_recyclerview_10};
    private int[]              vpImgPath = {R.mipmap.ic_viewpager_01, R.mipmap.ic_viewpager_02, R.mipmap.ic_viewpager_03};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setProgressBackgroundColor(R.color.refresh_bg);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < vpImgPath.length; i++) {
            RecyclerVpFragment recyclerVpFragment = RecyclerVpFragment.newInstance(vpImgPath[i]);
            fragmentList.add(recyclerVpFragment);
        }
        setData();
        RecyclerAdapter mAdapter = new RecyclerAdapter(getActivity(), mList);
        //RecyclerView子项的点击事件
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mAdapter.onItemClickListener));
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },3000);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setData() {
        mList.clear();
        for (int i = 0; i < title.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();
            recyclerBean.setImg(imgPath[i]);
            recyclerBean.setInfo(title[i]);
            recyclerBean.setTitle(title[i]);
            recyclerBean.setCatalog(title[i]);
            recyclerBean.setAuthor_intro(title[i]);
            recyclerBean.setSummary(title[i]);
            recyclerBean.setImglarge(imgPath[i]);
            mList.add(recyclerBean);
        }
    }
}
