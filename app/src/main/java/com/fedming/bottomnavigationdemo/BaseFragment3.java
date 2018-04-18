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


public class BaseFragment3 extends BaseMainFragment {
    private Toolbar mToolbar;
    public static BaseFragment3 newInstance(String info) {
        Bundle args = new Bundle();
        BaseFragment3 fragment = new BaseFragment3();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }
    public void onHiddenChanged(boolean hidden) {

        if (hidden) {
            //相当于Fragment的onPause
            Log.d("BaseFragment3","界面不可见");
        } else {
            Log.d("BaseFragment3","界面可见");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getArguments().getString("info"));
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
}
