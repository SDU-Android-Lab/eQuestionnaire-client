package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	EditText register_nameE = (EditText) findViewById(R.id.account_name);
	EditText register_phone_numberE = (EditText) findViewById(R.id.account_phoneNumber);
	EditText register_provinceE = (EditText) findViewById(R.id.account_province);
	EditText register_cityE = (EditText) findViewById(R.id.account_city);
	EditText register_streetE = (EditText) findViewById(R.id.account_street);
	EditText register_mailE = (EditText) findViewById(R.id.account_email);
	Button register = (Button) findViewById(R.id.account_btn_apply);
	Button cancel = (Button) findViewById(R.id.account_btn_cancel);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register_layout);

	}
}
