package com.witpos.inventory.machine.store.presenter;


import com.witpos.inventory.machine.data.TestData;
import com.witpos.inventory.machine.store.ChooseStoreContract;
import com.witpos.inventory.machine.store.bean.Store;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * 选择门面逻辑
 */
public class ChooseFacadePresenter implements ChooseStoreContract.Presenter {


    private ChooseStoreContract.View mUi;
    private CompositeDisposable mCompositeDisposable;

    public ChooseFacadePresenter(ChooseStoreContract.View ui) {
        mUi = ui;
        mCompositeDisposable = new CompositeDisposable();
        mUi.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void subscribe() {
        loadInitData();
    }

    private void loadInitData() {
        //测试数据
        List<Store> data = TestData.getTestStoreList();
        mUi.loadData(data);
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }
}
