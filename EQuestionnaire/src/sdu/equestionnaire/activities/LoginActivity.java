package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.net.ConnectionDetector;
import sdu.equestionnaire.user.User;
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
 * ��½����
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
					Toast.makeText(LoginActivity.this, "δ��������",
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
			int uid = sp.getInt("uid", 0);
			String upswd = sp.getString("upswd", null);
			if (uid == 0)
				account_edit.setText("");
			else
				account_edit.setText("" + uid);
			password_edit.setText(upswd);
			savePassword_cb.setChecked(true);
			User.user_id = uid;
			User.user_password = upswd;
		}
	}

	/**
	 * �Զ���¼
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
	 * ��¼
	 * 
	 * @return -true ����¼�ɹ� - false ��¼ʧ��
	 */
	private boolean login() {
		return true;
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
					int accout = Integer.parseInt(account_edit.getText()
							.toString());
					String password = password_edit.getText().toString();
					boolean savePswd = savePassword_cb.isChecked();
					/*
					 * ���ӷ�����
					 */
					boolean in = login();
					if (in) {
						if (savePswd) {
							Editor editor = sp.edit();
							editor.putInt("uid", accout);
							editor.putString("upswd", password);
							editor.putBoolean("auto", autoLogin_cb.isChecked());
							editor.putBoolean("save", savePswd);
							editor.commit();
							User.user_id = accout;
							User.user_password = password;
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
