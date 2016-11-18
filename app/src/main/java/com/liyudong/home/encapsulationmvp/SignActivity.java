package com.liyudong.home.encapsulationmvp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liyudong.home.encapsulationmvp.MVP.presenter.impl.CodePresenter;
import com.liyudong.home.encapsulationmvp.MVP.presenter.impl.CodeVertifyPresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.SignView;
import com.liyudong.home.encapsulationmvp.utils.TimeCount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class SignActivity extends BaseActivity implements SignView<String> {
    @BindView(R.id.sign_change_sign_button)
    Button signChangeSignButton;
    @BindView(R.id.sign_sign_button)
    Button signSignButton;
    @BindView(R.id.sign_edit_username)
    EditText signEditUsername;
    @BindView(R.id.sign_phone_number_valid)
    TextView signPhoneNumberValid;
    @BindView(R.id.sign_phone_number_invalid)
    TextView signPhoneNumberInvalid;
    @BindView(R.id.sign_edit_unideCode)
    EditText signEditUnideCode;
    @BindView(R.id.sign_get_unideCode_button)
    Button signGetUnideCodeButton;
    @BindView(R.id.sign_checkbox)
    CheckBox signCheckbox;
    @BindView(R.id.sign_next_button)
    Button signNextButton;

    private CodePresenter codePresenter;
    private CodeVertifyPresenter codeVertifyPresenter;
    private String mobile;

    @Override
    public int getContentView() {
        return R.layout.sign_layout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        signNextButton.setAlpha(0.2f);
        signNextButton.setClickable(false);
        codePresenter = new CodePresenter();
        codeVertifyPresenter = new CodeVertifyPresenter();
        codePresenter.attachView(this);
        codeVertifyPresenter.attachView(this);
        setOnEditTextListener();
        setButtonClickListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void beforeSign() {

    }

    @Override
    public void vertifyPhoneNumber() {
        Toast.makeText(this, "开始验证手机", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isVertifying() {
        Toast.makeText(this, "正在验证", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void numberRight() {
        Toast.makeText(this, "号码正确,可以注册", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void numberFailed() {
        Toast.makeText(this, "号码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCode(String code) {
        Toast.makeText(this, "获得了code", Toast.LENGTH_SHORT).show();
        Log.d("SignActivity", "getCode" + code);
    }

    @Override
    public void getCodeSuccess() {
        Toast.makeText(this, "成功获得Code", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCodeFailed() {
        Toast.makeText(this, "获得code失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startVertiryCode() {
        Toast.makeText(this, "开始验证code", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isCodeRight(String s) {
        //TODO 跳转
        Intent intent = new Intent(this,SignPassWordActivity.class);
        intent.putExtra("code",code);
        intent.putExtra("mobile",mobile);
        startActivity(intent);
    }

    @Override
    public void isCodeWrong() {
        Toast.makeText(this, "code输入错误请重新输入", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showView(String s) {

    }

    @Override
    public void loadfailed() {

    }

    public void setButtonClickListener(){
        final TimeCount timer = new TimeCount(signGetUnideCodeButton,60000,1000);
        signGetUnideCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codePresenter.getMobile(userName);
                codePresenter.startGetData();
                timer.start();
            }
        });
        signNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = signEditUnideCode.getText().toString();
                mobile = signEditUsername.getText().toString();
                codeVertifyPresenter.getMobile(userName);
                codeVertifyPresenter.getVertify_code(code);
                codeVertifyPresenter.startGetData();
            }
        });
    }

    private String userName;
    private String code;
    public void setOnEditTextListener(){
        signEditUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isMobileNO(s.toString())) {
                    signPhoneNumberValid.setVisibility(View.VISIBLE);
                    signPhoneNumberInvalid.setVisibility(View.INVISIBLE);
                }else{
                    signPhoneNumberValid.setVisibility(View.INVISIBLE);
                    signPhoneNumberInvalid.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = s.toString();
                if(!s.toString().equals("")&&!signEditUnideCode.getText().toString().equals("")){
                    signNextButton.setAlpha(1f);
                    signNextButton.setClickable(true);
                }else {
                    signNextButton.setAlpha(0.2f);
                    signNextButton.setClickable(false);
                }
            }
        });

        signEditUnideCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code = s.toString();
                if(!s.toString().equals("")&&!signEditUsername.getText().toString().equals("")){
                    signNextButton.setAlpha(1f);
                    signNextButton.setClickable(true);
                }else {
                    signNextButton.setAlpha(0.2f);
                    signNextButton.setClickable(false);
                }
            }
        });
    }

    public  boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    @Override
    protected void onDestroy() {
        codePresenter.detachView();
        codeVertifyPresenter.detachView();
        super.onDestroy();
    }

}
