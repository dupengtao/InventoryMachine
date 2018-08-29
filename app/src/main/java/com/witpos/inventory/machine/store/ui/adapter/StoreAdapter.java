package com.witpos.inventory.machine.store.ui.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.witpos.inventory.machine.R;
import com.witpos.inventory.machine.store.bean.Store;
import java.util.List;

/**
 * Created by dupengtao on 2018/8/28.
 */

public class StoreAdapter extends BaseQuickAdapter<Store,BaseViewHolder> {

    public StoreAdapter(int layoutResId,
            @Nullable List<Store> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Store item) {
        helper.setText(R.id.tv_store_name,String.format("门面: %s",item.getName()));
        helper.setText(R.id.tv_store_id,String.format("门店号: %s",item.getId()));
    }
}
