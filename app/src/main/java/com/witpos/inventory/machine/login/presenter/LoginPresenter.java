package com.witpos.inventory.machine.login.presenter;


import com.witpos.inventory.machine.login.LoginContract;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author madan
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mUi;
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(LoginContract.View ui) {
        mUi = ui;
        mCompositeDisposable = new CompositeDisposable();
        mUi.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadInitData();
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void login(String username, String password) {

    }

    /**
     * 加载初始化数据
     */
    private void loadInitData() {

    }
}
