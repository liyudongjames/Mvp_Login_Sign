package com.liyudong.home.encapsulationmvp;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liyudong.home.encapsulationmvp.MVP.presenter.impl.LoginPresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements LoginView<String> {

    @BindView(R.id.login_change_sign_button)
    Button loginChangeSignButton;
    @BindView(R.id.login_sign_button)
    Button loginSignButton;
    @BindView(R.id.login_edit_username)
    EditText loginEditUsername;
    @BindView(R.id.login_phone_number_valid)
    TextView loginPhoneNumberValid;
    @BindView(R.id.login_phone_number_invalid)
    TextView loginPhoneNumberInvalid;
    @BindView(R.id.login_edit_password)
    EditText loginEditPassword;
    @BindView(R.id.login_forget_password_textview)
    TextView loginForgetPasswordTextview;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        loginButton.setAlpha(0.2f);
        loginButton.setClickable(false);
        beforeLogin();
    }
    private LoginPresenter loginPresenter;
    @Override
    public void initData() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    public void beforeLogin() {
        setEditListener();
        setButtonListener();
    }

    @Override
    public void isVertifying() {
        Toast.makeText(this, "开始登陆", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void numberRight() {
        Toast.makeText(this, "手机号码正确", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void numberFailed() {
        Toast.makeText(this, "没有发现手机号没注册或者密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logining() {
        Toast.makeText(this, "开始登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterLogin() {
        Toast.makeText(this, "加载结束", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showView(String s) {
        Log.d("MainActivity", s);
        if(s.charAt(0)=='0'){
            Toast.makeText(this, s.substring(1,s.length()), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, s.substring(1,s.length()), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadfailed() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    public  boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public void setButtonListener(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.getMobile(userName);
                loginPresenter.getPassword(passWord);
                loginPresenter.startGetData();
            }
        });
        loginSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
    }

    private String userName;
    private String passWord;
    public void setEditListener(){
        loginEditUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isMobileNO(s.toString())) {
                    loginPhoneNumberValid.setVisibility(View.VISIBLE);
                    loginPhoneNumberInvalid.setVisibility(View.INVISIBLE);
                }else{
                    loginPhoneNumberValid.setVisibility(View.INVISIBLE);
                    loginPhoneNumberInvalid.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = s.toString();
                if(!s.toString().equals("")&&!loginEditPassword.getText().toString().equals("")){
                    loginButton.setAlpha(1f);
                    loginButton.setClickable(true);
                }else {
                    loginButton.setAlpha(0.2f);
                    loginButton.setClickable(false);
                }
            }
        });

        loginEditPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passWord = s.toString();
                if(!s.toString().equals("")&&!loginEditUsername.getText().toString().equals("")){
                    loginButton.setAlpha(1f);
                    loginButton.setClickable(true);
                }else {
                    loginButton.setAlpha(0.2f);
                    loginButton.setClickable(false);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }
}
