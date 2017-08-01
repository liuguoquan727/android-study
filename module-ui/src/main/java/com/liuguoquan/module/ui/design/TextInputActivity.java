package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;

public class TextInputActivity extends AppCompatActivity {

  @BindView(R2.id.input_name) EditText mInputName;
  @BindView(R2.id.input_emali) EditText mInputEmali;
  @BindView(R2.id.input_pwd) EditText mInputPwd;
  @BindView(R2.id.confirm) Button mConfirm;
  @BindView(R2.id.name_layout) TextInputLayout mNameLayout;
  @BindView(R2.id.email_layout) TextInputLayout mEmailLayout;
  @BindView(R2.id.pwd_layout) TextInputLayout mPwdLayout;

  private String mUserName;
  private String mEmali;
  private String mPwd;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_input);
    ButterKnife.bind(this);
  }

  @OnClick({ R2.id.confirm }) public void Onclick(View v) {
    int i = v.getId();
    if (i == R.id.confirm) {
      validate();
    }
  }

  private void validate() {
    mUserName = mInputName.getText().toString().trim();
    mEmali = mInputEmali.getText().toString().trim();
    mPwd = mInputPwd.getText().toString().trim();

    if (TextUtils.isEmpty(mUserName) || mUserName.length() < 3) {
      mNameLayout.setError("至少3个字符");
    } else {
      mNameLayout.setError(null);
    }

    if (TextUtils.isEmpty(mEmali) || !Patterns.EMAIL_ADDRESS.matcher(mEmali).matches()) {
      mEmailLayout.setError("请输入合法的电子邮箱");
    } else {
      mEmailLayout.setError(null);
    }

    if (TextUtils.isEmpty(mPwd) || mPwd.length() < 6 || mPwd.length() > 10) {
      mPwdLayout.setError("密码长度在6到10位之间");
    } else {
      mPwdLayout.setError(null);
    }
  }
}
