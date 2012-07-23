package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.net.ConnectionDetector;
import sdu.equestionnaire.user.UserInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登陆界面
 * 
 * @author lhy
 * 
 */
public class LoginActivity extends Activity {
	private final int CONNECTION_SUCCESS = 0;
	private final int CONNECTION_FAILED = 1;
	private final int CONFIRM_SUCCESS = 2;
	private final int CONFIRM_FAILED = 3;

	private EditText account_edit;
	private EditText password_edit;
	private CheckBox savePassword_cb;
	private CheckBox autoLogin_cb;
	private Button login_btn;

	private SharedPreferences sp;
	private ConnectionDetector detector;
	private Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		detector = new ConnectionDetector(getApplicationContext());
		handler = new Handler() {
			@SuppressLint("ParserError")
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int info = msg.what;
				switch (info) {
				case CONNECTION_SUCCESS:
					break;
				case CONNECTION_FAILED:
					Toast.makeText(LoginActivity.this, "未连接网络",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
					LoginActivity.this.startActivity(intent);
					break;
				case CONFIRM_SUCCESS:
					Intent intent1 = new Intent();
					intent1.setClass(LoginActivity.this, MainActivity.class);
					LoginActivity.this.startActivity(intent1);
//					overridePendingTransition(R.anim.hyperspace_in,
//							R.anim.hyperspace_out);
					LoginActivity.this.finish();
					break;
				case CONFIRM_FAILED:
					break;
				}
			}

		};
		initWidget();
		initSavedAccount();
		autoLogin();
		initListener();
	}

	/**
	 * 初始化控件
	 */
	private void initWidget() {
		login_btn = (Button) findViewById(R.id.login_btn_login);
		account_edit = (EditText) findViewById(R.id.login_edit_account);
		password_edit = (EditText) findViewById(R.id.login_edit_pwd);
		savePassword_cb = (CheckBox) findViewById(R.id.login_cb_savepwd);
		autoLogin_cb = (CheckBox) findViewById(R.id.login_cb_auto);
	}

	/**
	 * 初始化被记忆的账号
	 */
	private void initSavedAccount() {
		if (sp.getBoolean("save", false)) {
			String uname = sp.getString("uname", null);
			String upswd = sp.getString("upswd", null);
			account_edit.setText(uname);
			password_edit.setText(upswd);
			savePassword_cb.setChecked(true);
			UserInfo.user_name = uname;
			UserInfo.user_password = upswd;
		}
	}

	/**
	 * 自动登录
	 */
	private void autoLogin() {
		if (sp.getBoolean("auto", false)) {
			boolean in = login();
			if (in) {
				Message msg = handler.obtainMessage();
				msg.what = CONFIRM_SUCCESS;
				handler.sendMessage(msg);
			} else {
				Message msg = handler.obtainMessage();
				msg.what = CONFIRM_FAILED;
				handler.sendMessage(msg);
			}
		}
	}

	/**
	 * 登录
	 * 
	 * @return -true 如果登录成功 - false 登录失败
	 */
	private boolean login() {
		return true;
	}

	/**
	 * 添加监听器
	 */
	private void initListener() {
		login_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!detector.isConnectingToInternet()) {
					Message msg = handler.obtainMessage();
					msg.what = CONNECTION_FAILED;
					handler.sendMessage(msg);
				} else {
					String accout = account_edit.getText().toString();
					String password = password_edit.getText().toString();
					boolean savePswd = savePassword_cb.isChecked();
					/*
					 * 连接服务器
					 */
					boolean in = login();
					if (in) {
						if (savePswd) {
							Editor editor = sp.edit();
							editor.putString("uname", accout);
							editor.putString("upswd", password);
							editor.putBoolean("auto", autoLogin_cb.isChecked());
							editor.putBoolean("save", savePswd);
							editor.commit();
							UserInfo.user_name = accout;
							UserInfo.user_password = password;
						}
						Message msg = handler.obtainMessage();
						msg.what = CONFIRM_SUCCESS;
						handler.sendMessage(msg);
					} else {
						Message msg = handler.obtainMessage();
						msg.what = CONFIRM_FAILED;
						handler.sendMessage(msg);
					}
				}
			}
		});

	}

}
