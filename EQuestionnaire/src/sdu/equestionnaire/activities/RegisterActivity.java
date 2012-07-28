package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText register_nameE;
	EditText register_phone_numberE;
	EditText register_password;
	EditText register_provinceE;
	EditText register_cityE;
	EditText register_streetE;
	EditText register_mailE;
	Button register;
	Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register_layout);
		initWidget();
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String name = register_nameE.getText().toString();
				String passw = register_password.getText().toString();
				String phone = register_phone_numberE.getText().toString();
				String city = register_cityE.getText().toString();
				String street = register_streetE.getText().toString();
				String province = register_provinceE.getText().toString();
				String mail = register_mailE.getText().toString();

				if (name.length() == 0 || phone.length() == 0
						|| city.length() == 0 || street.length() == 0
						|| province.length() == 0 || mail.length() == 0
						|| passw.length() == 0) {
					Toast.makeText(RegisterActivity.this, "信息不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(RegisterActivity.this, "注册成功！您的账号是10011，请牢记",
						Toast.LENGTH_LONG).show();
				// } else {
				// User p = new User();
				// p.setName(name);
				// p.setPhone(phone);
				// p.setProvince(province);
				// p.setCity(city);
				// p.setStreet(street);
				// p.setEmail(mail);
				// Messages msg = new Messages(Message_Type.Regesiter, p);
				// UserInfo.m_cliient.sendmes(msg);
				// msg = (Messages) UserInfo.m_cliient.getMessage();
				// }

			}
		});
	}

	private void initWidget() {
		register_nameE = (EditText) findViewById(R.id.register_name);
		register_phone_numberE = (EditText) findViewById(R.id.register_phoneNumber);
		register_password = (EditText) findViewById(R.id.register_password);
		register_provinceE = (EditText) findViewById(R.id.register_province);
		register_cityE = (EditText) findViewById(R.id.register_city);
		register_streetE = (EditText) findViewById(R.id.register_street);
		register_mailE = (EditText) findViewById(R.id.register_email);
		register = (Button) findViewById(R.id.register_btn_apply);
		cancel = (Button) findViewById(R.id.register_btn_cancel);

		register_nameE.setText("");
		register_phone_numberE.setText("");
		register_provinceE.setText("");
		register_cityE.setText("");
		register_streetE.setText("");
		register_mailE.setText("");
	}
}
