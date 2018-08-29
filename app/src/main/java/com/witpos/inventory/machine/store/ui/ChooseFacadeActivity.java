package com.witpos.inventory.machine.store.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.witpos.inventory.machine.R;
import com.witpos.inventory.machine.store.ChooseStoreContract;
import com.witpos.inventory.machine.store.ChooseStoreContract.Presenter;
import com.witpos.inventory.machine.store.bean.Store;
import com.witpos.inventory.machine.store.presenter.ChooseFacadePresenter;
import com.witpos.inventory.machine.store.ui.adapter.StoreAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择门面
 *
 * @author madan
 */
public class ChooseFacadeActivity extends Activity implements ChooseStoreContract.View {


    private RecyclerView mRecyclerView;
    private StoreAdapter mAdapter;
    private Presenter mPresenter;
    private List<Store> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooce_facade);
        new ChooseFacadePresenter(this);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new StoreAdapter(R.layout.item_chooce_store, mData);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(view.getContext(),"选择门面 "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void loadData(List<Store> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }
}
