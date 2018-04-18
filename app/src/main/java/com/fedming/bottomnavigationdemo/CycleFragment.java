package com.fedming.bottomnavigationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class CycleFragment extends SupportFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;
    private static final int REQ_MODIFY_FRAGMENT = 100;
    private int mNumber;

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        mBtnNextWithFinish = (Button) view.findViewById(R.id.btn_next_with_finish);

        String title = "CyclerFragment " + mNumber;

        mToolbar.setTitle(title);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startForResult(CycleFragment2.newInstance(1), REQ_MODIFY_FRAGMENT);
            }
        });
        mBtnNextWithFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithPop(CycleFragment.newInstance(mNumber + 1));
            }
        });
    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            mToolbar.setTitle("CyclerFragment "+data.getInt("title"));
            // 保存被改变的 title
            getArguments().putInt(ARG_NUMBER, data.getInt("title"));
            Toast.makeText(_mActivity, R.string.modify_title, Toast.LENGTH_SHORT).show();
        }
    }
}
