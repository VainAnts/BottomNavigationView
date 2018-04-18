package com.fedming.bottomnavigationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment2 extends BaseMainFragment {
    public static BaseFragment2 newInstance(String info) {
        Bundle args = new Bundle();
        BaseFragment2 fragment = new BaseFragment2();
        fragment.setArguments(args);
        return fragment;
    }
    public void onHiddenChanged(boolean hidden) {
            if (hidden) {
            //相当于Fragment的onPause
            Log.d("BaseFragment2","界面不可见");
        } else {
            Log.d("BaseFragment2","界面可见");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base2, null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance());
        }
    }
}
