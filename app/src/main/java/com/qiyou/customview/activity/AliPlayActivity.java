package com.qiyou.customview.activity;

import android.view.View;
import android.widget.Button;

import com.qiyou.customview.R;
import com.qiyou.customview.base.BaseActivity;
import com.qiyou.customview.contant.Contants;
import com.qiyou.customview.customView.AliPlayView;

public class AliPlayActivity extends BaseActivity implements View.OnClickListener {


    private AliPlayView mAliplayView;
    private Button mBtnLoading;
    private Button mBtnSuccess;
    private Button mBtnFaild;

    @Override
    protected int setLayoutID() {
        return R.layout.activity_ali_play;
    }

    @Override
    protected void initView() {
        mAliplayView = findViewById(R.id.aliplay_view);
        mBtnLoading = findViewById(R.id.btn_loading);
        mBtnSuccess = findViewById(R.id.btn_success);
        mBtnFaild = findViewById(R.id.btn_faild);

        mBtnLoading.setOnClickListener(this);
        mBtnSuccess.setOnClickListener(this);
        mBtnFaild.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loading:
                mAliplayView.startLoading();
                break;
            case R.id.btn_success:
                mAliplayView.success();
                break;
            case R.id.btn_faild:
                mAliplayView.failed();
                break;
        }
    }
}
