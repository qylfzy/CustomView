package com.qiyou.customview.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiyou.customview.R;
import com.qiyou.customview.contant.Contants;
import com.qiyou.customview.util.StatusUtils;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusUtils.getInstancs().setStatusBarColor(this, R.color.royalblue);
        setContentView(setLayoutID());
        initView();
        initToolBar();
        setToolbar();
        initData();
        setToolBarTitle(getIntent().getStringExtra(Contants.TYPE_TITLE));
    }

    protected abstract int setLayoutID();

    protected abstract void initView();

    protected abstract void initData();

    private void initToolBar() {
        toolbar = findViewById(R.id.base_toolbar);
        tv_title = findViewById(R.id.tv_toolbar_title);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void setToolBarTitle(String title) {
        tv_title.setText(TextUtils.isEmpty(title) ? "自定义View" : title);
    }

}
