package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * µÇÂ½½çÃæ
 * 
 * @author lhy
 * 
 */
public class LoginActivity extends Activity {
	private final int CONNECTION_SUCCESS = 0;
	private final int CONNECTION_FAILED = 1;
	private final int CONFIRM_SUCCESS = 2;
	private final int CONFIRM_FAILED = 3;

	private Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		handler = new Handler() {

			@SuppressLint("ParserError")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				int info = msg.what;
				switch (info) {
				case CONNECTION_SUCCESS:
					break;
				case CONNECTION_FAILED:
					break;
				case CONFIRM_SUCCESS:
					break;
				case CONFIRM_FAILED:
					break;
				}
			}

		};

	}
}
