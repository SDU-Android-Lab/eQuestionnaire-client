package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.common.Message_Type;
import sdu.equestionnaire.common.Messages;
import sdu.equestionnaire.common.Type;
import sdu.equestionnaire.common.User;
import sdu.equestionnaire.info.UserInfo;
import sdu.equestionnaire.net.ConnectionDetector;
import sdu.equestionnaire.net.MainClient;
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
	private Button register_btn;

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
		UserInfo.m_cliient = new MainClient();

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
					Toast.makeText(LoginActivity.this, "网络异常",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
					LoginActivity.this.startActivity(intent);
					break;
				case CONFIRM_SUCCESS:
					Intent intent1 = new Intent();
					intent1.setClass(LoginActivity.this, MainActivity.class);
					LoginActivity.this.startActivity(intent1);
					// overridePendingTransition(R.anim.hyperspace_in,
					// R.anim.hyperspace_out);
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
	 * ��ʼ���ؼ�
	 */
	private void initWidget() {
		login_btn = (Button) findViewById(R.id.login_btn_login);
		register_btn = (Button) findViewById(R.id.login_btn_register);
		account_edit = (EditText) findViewById(R.id.login_edit_account);
		password_edit = (EditText) findViewById(R.id.login_edit_pwd);
		savePassword_cb = (CheckBox) findViewById(R.id.login_cb_savepwd);
		autoLogin_cb = (CheckBox) findViewById(R.id.login_cb_auto);
	}

	/**
	 * ��ʼ����������˺�
	 */
	private void initSavedAccount() {
		if (sp.getBoolean("save", false)) {
			String uid = sp.getString("uid", "");
			String upswd = sp.getString("upswd", null);
			if (uid.length() == 0)
				account_edit.setText("");
			else
				account_edit.setText(uid);
			password_edit.setText(upswd);
			savePassword_cb.setChecked(true);
			if (uid.length() == 0)
				UserInfo.user_id = 0;
			else
				UserInfo.user_id = Integer.parseInt(uid);
			UserInfo.user_password = upswd;
		}
	}

	/**
	 * �Զ���¼
	 */
	private void autoLogin() {
		if (sp.getBoolean("auto", false)) {
			String uid = sp.getString("uid", "");
			String upswd = sp.getString("upswd", null);
			boolean in = login(uid, upswd);
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
	 * ��¼
	 * 
	 * @return -true ����¼�ɹ� - false ��¼ʧ��
	 */
	private boolean login(String id, String password) {
		UserInfo.m_cliient.init();
		User p = new User();
		p.setType(Type.vip);
		p.setId(Integer.parseInt(id));
		p.setPassword(password);
		Messages msg = new Messages(Message_Type.LoginIn, p);
		UserInfo.m_cliient.sendmes(msg);
		Messages login_info = (Messages) UserInfo.m_cliient.getMessage();
		Integer i = (Integer) login_info.getObj();
		if (i == 1) {
			msg.setMessage_type(Message_Type.Information);
			UserInfo.m_cliient.sendmes(msg);
			msg = (Messages) UserInfo.m_cliient.getMessage();
			User u = (User) msg.getObj();
			UserInfo.user_name = u.getName();
			UserInfo.user_email = u.getEmail();
			UserInfo.user_phone = u.getPhone();
			UserInfo.user_province = u.getProvince();
			UserInfo.user_city = u.getCity();
			UserInfo.user_street = u.getStreet();
			UserInfo.user_point = u.getPoint();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��Ӽ�����
	 */
	private void initListener() {
		login_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!detector.isConnectingToInternet()) {
					Message msg = handler.obtainMessage();
					msg.what = CONNECTION_FAILED;
					handler.sendMessage(msg);
				} else {
					String account = account_edit.getText().toString();
					String password = password_edit.getText().toString();
					if (account.length() == 0 || password.length() == 0) {
						Toast.makeText(LoginActivity.this, "用户名或密码不能为空",
								Toast.LENGTH_LONG).show();
						return;
					}
					boolean savePswd = savePassword_cb.isChecked();
					/*
					 * ���ӷ�����
					 */
					boolean in = login(account, password);
					if (in) {
						if (savePswd) {
							Editor editor = sp.edit();
							editor.putString("uid", account);
							editor.putString("upswd", password);
							editor.putBoolean("auto", autoLogin_cb.isChecked());
							editor.putBoolean("save", savePswd);
							editor.commit();
							if (account.length() == 0)
								UserInfo.user_id = 0;
							else
								UserInfo.user_id = Integer.parseInt(account);
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
		register_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				LoginActivity.this.startActivity(intent);

			}
		});
	}
}
