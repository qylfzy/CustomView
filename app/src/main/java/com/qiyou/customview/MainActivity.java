package com.qiyou.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiyou.customview.activity.BezierActivity;
import com.qiyou.customview.activity.BezierWaterActivity;
import com.qiyou.customview.activity.CircleImgActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qiyou.customview.activity.AliPlayActivity;
import com.qiyou.customview.activity.CircleImgActivity;
import com.qiyou.customview.activity.FiveStarActivity;
import com.qiyou.customview.activity.SpiderActivity;
import com.qiyou.customview.adapter.RecyclerViewDivider;
import com.qiyou.customview.adapter.ViewTypeAdapter;
import com.qiyou.customview.contant.Contants;
import com.qiyou.customview.util.StatusUtils;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRvView;
    private List<String> viewType;
    private ViewTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusUtils.getInstancs().setStatusBarColor(this, R.color.royalblue);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initEvent();
    }

    private void initView() {
        mRvView = findViewById(R.id.rv_view);
    }

    private void initData() {
        viewType = Arrays.asList(getResources().getStringArray(R.array.CustomView_Type));
    }

    private void initAdapter() {
        mRvView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new ViewTypeAdapter(R.layout.item_rv_type, viewType);
        mRvView.addItemDecoration(new RecyclerViewDivider(MainActivity.this, LinearLayoutManager.HORIZONTAL,
                3, getResources().getColor(R.color.gainsboro)));
        mRvView.setAdapter(adapter);
    }

    private void startActivity(Class tClass, String title) {
        Intent intent = new Intent(MainActivity.this, tClass);
        intent.putExtra(Contants.TYPE_TITLE, title);
        startActivity(intent);
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(FiveStarActivity.class, viewType.get(position));
                        break;
                    case 1:
                        startActivity(SpiderActivity.class, viewType.get(position));
                        break;
                    case 2:
                        startActivity(AliPlayActivity.class, viewType.get(position));
                        break;
                    case 3:
                        startActivity(CircleImgActivity.class, viewType.get(position));
                        break;
                    case 4:
                        startActivity(BezierActivity.class, viewType.get(position));
                    case 5:
                        startActivity(BezierWaterActivity.class, viewType.get(position));
                }
            }
        });
    }
}
