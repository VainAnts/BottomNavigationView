package com.fedming.bottomnavigationdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by bruce on 2016/11/1.
 * MainActivity 主界面
 */

public class MainFragment extends SupportFragment {
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];
    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(BaseFragment1.class);
        if (firstFragment == null) {
            mFragments[FIRST] = BaseFragment1.newInstance("新闻");
//            mFragments[SECOND] =BaseFragment2.newInstance("图书");
            mFragments[SECOND] =ViewPagerFragment.newInstance();

            mFragments[THIRD] = BaseFragment3.newInstance("发现");
            mFragments[FOUR] = BaseFragment4.newInstance("更多");

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],mFragments[FOUR]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(BaseFragment2.class);
            mFragments[THIRD] = findChildFragment(BaseFragment3.class);
            mFragments[FOUR] =  findChildFragment(BaseFragment4.class);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                showHideFragment(mFragments[FIRST]);
                                break;
                            case R.id.item_lib:
                                showHideFragment(mFragments[SECOND]);
                                break;
                            case R.id.item_find:
                                showHideFragment(mFragments[THIRD]);
                                break;
                            case R.id.item_more:
                                showHideFragment(mFragments[FOUR]);
                                break;
                        }
                        return false;
                    }
                });

        return view;
    }
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}