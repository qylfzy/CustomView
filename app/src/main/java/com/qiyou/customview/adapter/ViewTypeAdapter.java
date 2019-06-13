package com.qiyou.customview.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qiyou.customview.R;

import java.util.List;

/**
 * Created by QiYou
 * on 2019/5/22
 */
public class ViewTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ViewTypeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_type, item);
    }
}
