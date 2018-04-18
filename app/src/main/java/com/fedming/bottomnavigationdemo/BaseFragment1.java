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
import android.widget.Toast;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment1 extends BaseMainFragment {
    private Toolbar mToolbar;

    public static BaseFragment1 newInstance(String info) {
        Bundle args = new Bundle();
        BaseFragment1 fragment = new BaseFragment1();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }
    public void onHiddenChanged(boolean hidden) {

        if (hidden) {
            //相当于Fragment的onPause
            Log.d("BaseFragment1","界面不可见");
        } else {
            Log.d("BaseFragment1","界面可见");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getArguments().getString("info"));
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(CycleFragment.newInstance(1));

            }
        });
        return view;
    }
    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        // 入场动画结束后执行  优化,防动画卡顿
    }

}
