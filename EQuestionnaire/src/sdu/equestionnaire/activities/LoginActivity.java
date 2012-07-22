package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.net.ConnectionDetector;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	private Button login_btn;

	private ConnectionDetector detector;
	private Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

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
					LoginActivity.this.finish();
					break;
				case CONFIRM_FAILED:
					break;
				}
			}

		};
		initWidget();
		initListener();
	}

	private void initWidget() {
		login_btn = (Button) findViewById(R.id.login_btn_login);
		account_edit = (EditText) findViewById(R.id.login_edit_account);
		password_edit = (EditText) findViewById(R.id.login_edit_pwd);
	}

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
					/*
					 * 连接服务器
					 */
					Message msg = handler.obtainMessage();
					msg.what = CONFIRM_SUCCESS;// or CONFIRM_FAILED
					handler.sendMessage(msg);
				}
			}
		});

	}

}
