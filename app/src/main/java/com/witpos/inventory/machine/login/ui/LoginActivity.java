package com.witpos.inventory.machine.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.witpos.inventory.machine.BuildConfig;
import com.witpos.inventory.machine.R;
import com.witpos.inventory.machine.base.Constants;
import com.witpos.inventory.machine.login.LoginContract;
import com.witpos.inventory.machine.login.LoginContract.Presenter;
import com.witpos.inventory.machine.login.presenter.LoginPresenter;
import com.witpos.inventory.machine.store.ui.ChooseFacadeActivity;
import com.witpos.inventory.machine.utils.SharedPreferenceUtils;

/**
 * 登录页面
 * @author madan
 */
public class LoginActivity extends Activity implements LoginContract.View {

    private Presenter mPresenter;
    private EditText mInputUsername, mInputPassword;
    private TextView mLoginAction, mVersion;
    private LinearLayout mRecordAction;
    private ImageView mRecordIcon;
    private boolean mIsRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginPresenter(this);
        initView();
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
        SharedPreferenceUtils.getInstance()
                .setValue(Constants.IS_SELECT_RECORD_USERNAME_KEY, mIsRecord);
        if (mIsRecord) {
            SharedPreferenceUtils.getInstance().setValue(Constants.RECORD_USERNAME_KEY,
                    mInputUsername.getText().toString());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    /**
     * 初始化view
     */
    private void initView() {
        mInputUsername = findViewById(R.id.ed_input_username);
        mInputPassword = findViewById(R.id.ed_input_password);
        mLoginAction = findViewById(R.id.tv_login_action);
        mVersion = findViewById(R.id.tv_version);
        mRecordAction = findViewById(R.id.ll_record_username);
        mRecordIcon = findViewById(R.id.iv_record);

        //版本号
        mVersion.setText(getString(R.string.login_version, BuildConfig.VERSION_NAME));

        //是否勾选记录用户名
        mIsRecord = SharedPreferenceUtils.getInstance()
                .getBoolanValue(Constants.IS_SELECT_RECORD_USERNAME_KEY, true);
        mRecordIcon.setImageResource(
                mIsRecord ? R.drawable.icon_checkbox_focus : R.drawable.icon_checkbox_unfocus);

        mRecordAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsRecord = !mIsRecord;
                mRecordIcon.setImageResource(
                        mIsRecord ? R.drawable.icon_checkbox_focus
                                : R.drawable.icon_checkbox_unfocus);
            }
        });

        //获取上次记录的用户名
        if (mIsRecord) {
            String recordUserName = SharedPreferenceUtils.getInstance()
                    .getStringValue(Constants.RECORD_USERNAME_KEY, "");
            if (!TextUtils.isEmpty(recordUserName)) {
                mInputUsername.setText(recordUserName);
            }
        }

        //登陆
        mLoginAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mInputUsername.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.not_empty, getString(R.string.login_username)),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = mInputPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.not_empty, getString(R.string.login_password)),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mPresenter.login(username, password);

                Intent intent = new Intent(LoginActivity.this, ChooseFacadeActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

    }

}
