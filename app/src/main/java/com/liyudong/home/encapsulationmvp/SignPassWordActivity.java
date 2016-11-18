package com.liyudong.home.encapsulationmvp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liyudong.home.encapsulationmvp.MVP.presenter.impl.SignPresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.SignPassWordView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/17.
 */

public class SignPassWordActivity extends BaseActivity implements SignPassWordView<String>{
    @BindView(R.id.reset_edit_old_password)
    EditText resetEditOldPassword;
    @BindView(R.id.reset_edit_new_password)
    EditText resetEditNewPassword;
    @BindView(R.id.reset_two_number_right)
    TextView resetTwoNumberRight;
    @BindView(R.id.reset_two_number_wrong)
    TextView resetTwoNumberWrong;
    @BindView(R.id.reset_complete_button)
    Button resetCompleteButton;

    private SignPresenter signPresenter;

    @Override
    public int getContentView() {
        return R.layout.reset_password_layout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        getPhoneNumber();
        signPresenter = new SignPresenter();
        signPresenter.attachView(this);
        resetCompleteButton.setAlpha(0.2f);
        resetCompleteButton.setClickable(false);
        setEditListener();
        resetCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signPresenter.getMobile(mobile);
                signPresenter.getCode(code);
                signPresenter.getPassword(resetEditNewPassword.getText().toString());
                signPresenter.startGetData();
            }
        });
    }

    @Override
    public void initData() {

    }
    private String code;
    private String mobile;
    @Override
    public void getPhoneNumber() {
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        mobile = intent.getStringExtra("mobile");
    }

    @Override
    public void beforeSignPassword() {

    }

    @Override
    public void signing() {
        Toast.makeText(this, "正在注册", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterSign() {
        Toast.makeText(this, "注册结束", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showView(String s) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadfailed() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    public void setEditListener(){
        resetEditNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String old = resetEditOldPassword.getText().toString();
                String news = s.toString();
                if(!news.equals("")&&!old.equals("")){
                    if(old.equals(news)){
                        resetTwoNumberRight.setVisibility(View.VISIBLE);
                        resetTwoNumberWrong.setVisibility(View.INVISIBLE);
                        resetCompleteButton.setAlpha(1f);
                        resetCompleteButton.setClickable(true);
                    }else{
                        resetTwoNumberRight.setVisibility(View.INVISIBLE);
                        resetTwoNumberWrong.setVisibility(View.VISIBLE);
                        resetCompleteButton.setAlpha(0.2f);
                        resetCompleteButton.setClickable(false);
                    }
                }
            }
        });
        resetEditOldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String news = resetEditNewPassword.getText().toString();
                String old = s.toString();
                if(!news.equals("")&&!old.equals("")){
                    if(old.equals(news)){
                        resetTwoNumberRight.setVisibility(View.VISIBLE);
                        resetTwoNumberWrong.setVisibility(View.INVISIBLE);
                        resetCompleteButton.setAlpha(1f);
                        resetCompleteButton.setClickable(true);
                    }else{
                        resetTwoNumberRight.setVisibility(View.INVISIBLE);
                        resetTwoNumberWrong.setVisibility(View.VISIBLE);
                        resetCompleteButton.setAlpha(0.2f);
                        resetCompleteButton.setClickable(false);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        signPresenter.detachView();
        super.onDestroy();
    }
}
