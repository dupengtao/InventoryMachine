package com.witpos.inventory.machine.login;

import com.witpos.inventory.machine.base.BasePresenter;
import com.witpos.inventory.machine.base.BaseView;

/**
 * @author madan
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        /**
         * 登陆逻辑
         * @param username 用户名
         * @param password 密码
         */
        void login(String username, String password);
    }
}
