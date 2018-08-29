package com.witpos.inventory.machine.store;

import com.witpos.inventory.machine.base.BasePresenter;
import com.witpos.inventory.machine.base.BaseView;
import com.witpos.inventory.machine.store.bean.Store;
import java.util.List;

/**
 * @author madan
 */
public interface ChooseStoreContract {

    interface View extends BaseView<Presenter> {

        /**
         * 加载数据
         * @param data
         */
        void loadData(List<Store> data);
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

    }
}
