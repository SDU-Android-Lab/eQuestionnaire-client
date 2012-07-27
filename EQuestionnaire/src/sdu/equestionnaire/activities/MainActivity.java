package sdu.equestionnaire.activities;

import java.util.Timer;
import java.util.TimerTask;

import sdu.equestionnaire.R;
import sdu.equestionnaire.adapter.HomeAdvertAdapter;
import sdu.equestionnaire.adapter.HomeListAdapter;
import sdu.equestionnaire.adapter.QuestionListAdapter;
import sdu.equestionnaire.adapter.SettingGridAdapter;
import sdu.equestionnaire.animations.SquareRotate;
import sdu.equestionnaire.info.UserInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author lhy
 * 
 */
public class MainActivity extends Activity {
	private int mCenterX = 160;
	private int mCenterY = 0;
	private int tStartX;
	private int ad_index = 0;
	private boolean home_in_screen = true;

	private ViewGroup layoutFront;
	private ViewGroup layoutBack;
	private ViewGroup layoutRight;
	private ViewGroup layoutLeft;
	private View buttonSelectd;
	private Gallery ad_gallery;

	private SquareRotate leftAnimation;
	private SquareRotate rightAnimation;

	private DisplayMetrics disManager;
	private Timer home_ad_timer;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (!home_in_screen)
				return;
			switch (msg.what) {
			case 1:
				ad_gallery.setSelection(ad_index);
				break;
			default:
				break;
			}
		}
	};
	private TimerTask ad_task = new TimerTask() {
		@Override
		public void run() {
			Message message = new Message();
			message.what = 1;
			ad_index = ad_gallery.getSelectedItemPosition();
			ad_index++;
			handler.sendMessage(message);
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		disManager = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(disManager);
		final int screenWidth = disManager.widthPixels;
		final int screenHeight = disManager.heightPixels;
		mCenterX = screenWidth >> 1;
		mCenterY = screenHeight >> 1;
		setFront(null);
		home_ad_timer = new Timer();
		home_ad_timer.schedule(ad_task, 2000, 2000);
	}

	private void setFront(SquareRotate rorate) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.main_front);
		if (rorate != null)
			layoutFront.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.front_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.front_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);
		ad_gallery = (Gallery) findViewById(R.id.main_home_gallery);
		ListView list = (ListView) findViewById(R.id.main_home_list);

		HomeAdvertAdapter advert_adapter = new HomeAdvertAdapter(this);
		ad_gallery.setAdapter(advert_adapter);
		ad_gallery.setFadingEdgeLength(0);
		ad_gallery.setSpacing(5);

		HomeListAdapter list_adapter = new HomeListAdapter(this);
		list.setAdapter(list_adapter);

		menu_home.setSelected(true);
		buttonSelectd = menu_home;

		layoutFront.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					tStartX = (int) event.getX();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					int tEndX = (int) event.getX();
					if (tEndX - tStartX > 30) {
						wallFront_rightMoveHandle();
					} else if (tStartX - tEndX > 30) {
						wallFront_leftMoveHandle();
					}
				}
				return true;
			}
		});
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_leftMoveHandle();
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
			}
		});
		menu_home.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}

				wallFront_leftMoveHandle();
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}
				wallFront_leftMoveHandle();
				wallRight_leftMoveHandle();

			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallFront_rightMoveHandle();
			}
		});
	}

	private void setLeft(SquareRotate rorate) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.main_left);
		if (rorate != null)
			layoutLeft.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.left_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.left_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);
		GridView grid = (GridView) findViewById(R.id.setting_grid);
		SettingGridAdapter adapter = new SettingGridAdapter(this, disManager);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0: {
					// -----------------------
					//
					// 定位,哈哈
					// -----------------------
					break;
				}
				case 1: {
					// -----------------------
					//
					// 搜索,哈哈
					// -----------------------
					break;
				}
				case 2: {
					// -----------------------
					//
					// 皮肤,哈哈
					// -----------------------
					break;
				}
				case 3: {
					// -----------------------
					//
					// 兑换,哈哈
					// -----------------------
					break;
				}
				case 4: {
					// -----------------------
					//
					// 网络,哈哈
					// -----------------------
					Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
					MainActivity.this.startActivity(intent);
					break;
				}
				case 5: {
					// -----------------------
					//
					// 账号,哈哈
					// -----------------------
					SharedPreferences sp = MainActivity.this
							.getSharedPreferences("userinfo",
									Context.MODE_PRIVATE);
					sp.getBoolean("auto", false);
					Editor editor = sp.edit();
					editor.putBoolean("auto", false);
					editor.commit();
					Toast.makeText(MainActivity.this, "已设定为非自动登录",
							Toast.LENGTH_SHORT).show();
					break;
				}
				case 6: {
					// -----------------------
					//
					// 最近浏览,哈哈
					// -----------------------
					break;
				}
				case 7: {
					// -----------------------
					//
					// 客服,哈哈
					// -----------------------
					break;
				}
				case 8: {
					// -----------------------
					//
					// 更多,哈哈
					// -----------------------
					break;
				}
				}
			}
		});

		menu_setting.setSelected(true);
		buttonSelectd = menu_setting;

		layoutLeft.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					tStartX = (int) event.getX();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					int tEndX = (int) event.getX();
					if (tEndX - tStartX > 30) {
						wallLeft_rightMoveHandle();
					} else if (tStartX - tEndX > 30) {
						wallLeft_leftMoveHandle();
					}
				}
				return true;
			}
		});
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallLeft_leftMoveHandle();

			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallLeft_rightMoveHandle();

			}
		});
		menu_home.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}
				wallLeft_leftMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallLeft_rightMoveHandle();
				wallBack_rightMoveHandle();

			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallLeft_rightMoveHandle();
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
			}
		});

	}

	private void setRight(SquareRotate rorate) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.main_right);
		if (rorate != null)
			layoutRight.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.right_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.right_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);

		QuestionListAdapter menuListAdapter = new QuestionListAdapter(this, 0);
		ListView menuList = (ListView) findViewById(R.id.question_menuList);
		menuList.setAdapter(menuListAdapter);
		menuList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(MainActivity.this,
						QuestionnaireActivity.class);
				MainActivity.this.startActivity(intent);

			}
		});

		menu_question.setSelected(true);
		buttonSelectd = menu_question;

		layoutRight.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					tStartX = (int) event.getX();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					int tEndX = (int) event.getX();
					if (tEndX - tStartX > 30) {
						wallRight_rightMoveHandle();
					} else if (tStartX - tEndX > 30) {
						wallRight_leftMoveHandle();
					}
				}
				return true;
			}
		});

		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallRight_leftMoveHandle();
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallRight_rightMoveHandle();
			}
		});
		menu_home.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}
				wallRight_rightMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallRight_leftMoveHandle();
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallRight_rightMoveHandle();
				wallFront_rightMoveHandle();
			}
		});

	}

	private void setBack(SquareRotate rorate) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.main_back);
		if (rorate != null)
			layoutBack.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.back_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.back_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);
		TextView account_pointT = (TextView) findViewById(R.id.account_point);
		final EditText account_nameE = (EditText) findViewById(R.id.account_name);
		final EditText account_phone_numberE = (EditText) findViewById(R.id.account_phoneNumber);
		final EditText account_provinceE = (EditText) findViewById(R.id.account_province);
		final EditText account_cityE = (EditText) findViewById(R.id.account_city);
		final EditText account_streetE = (EditText) findViewById(R.id.account_street);
		final EditText account_mailE = (EditText) findViewById(R.id.account_email);
		final Button apply = (Button) findViewById(R.id.account_btn_apply);
		final Button cancel = (Button) findViewById(R.id.account_btn_cancel);
		final Button edit = (Button) findViewById(R.id.account_btn_edit);

		menu_account.setSelected(true);
		buttonSelectd = menu_account;

		account_nameE.setText(UserInfo.user_name);
		account_phone_numberE.setText(UserInfo.user_phone);
		account_provinceE.setText(UserInfo.user_province);
		account_cityE.setText(UserInfo.user_city);
		account_streetE.setText(UserInfo.user_street);
		account_mailE.setText(UserInfo.user_email);
		account_pointT.setText(UserInfo.user_point + "");

		layoutBack.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					tStartX = (int) event.getX();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					int tEndX = (int) event.getX();
					if (tEndX - tStartX > 30) {
						wallBack_rightMoveHandle();
					} else if (tStartX - tEndX > 30) {
						wallBack_leftMoveHandle();
					}
				}
				return true;
			}
		});
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallBack_leftMoveHandle();

			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallBack_rightMoveHandle();

			}
		});
		menu_home.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;
				}
				wallBack_rightMoveHandle();
				wallRight_rightMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallBack_rightMoveHandle();
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					buttonSelectd.setSelected(false);
					v.setSelected(true);
					buttonSelectd = v;

				}
				wallBack_leftMoveHandle();
			}
		});

		edit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				account_nameE.setEnabled(true);
				account_phone_numberE.setEnabled(true);
				account_provinceE.setEnabled(true);
				account_cityE.setEnabled(true);
				account_streetE.setEnabled(true);
				account_mailE.setEnabled(true);
				apply.setEnabled(true);
				cancel.setEnabled(true);
				edit.setEnabled(false);
			}
		});

		apply.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				boolean access = false;
				// ---------------------------------------------
				// send message to server and wait for the reply
				//
				//
				//
				// ---------------------------------------------
				access = true;
				if (access) {
					UserInfo.user_name = account_nameE.getText().toString();
					UserInfo.user_email = account_mailE.getText().toString();
					UserInfo.user_phone = account_phone_numberE.getText()
							.toString();
					UserInfo.user_province = account_provinceE.getText()
							.toString();
					UserInfo.user_city = account_cityE.getText().toString();
					UserInfo.user_street = account_streetE.getText().toString();

					account_nameE.setEnabled(false);
					account_phone_numberE.setEnabled(false);
					account_provinceE.setEnabled(false);
					account_cityE.setEnabled(false);
					account_streetE.setEnabled(false);
					account_mailE.setEnabled(false);
					apply.setEnabled(false);
					cancel.setEnabled(false);
					edit.setEnabled(true);
				}

			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				account_nameE.setText(UserInfo.user_name);
				account_phone_numberE.setText(UserInfo.user_phone);
				account_provinceE.setText(UserInfo.user_province);
				account_cityE.setText(UserInfo.user_city);
				account_streetE.setText(UserInfo.user_street);
				account_mailE.setText(UserInfo.user_email);

				account_nameE.setEnabled(false);
				account_phone_numberE.setEnabled(false);
				account_provinceE.setEnabled(false);
				account_cityE.setEnabled(false);
				account_streetE.setEnabled(false);
				account_mailE.setEnabled(false);
				apply.setEnabled(false);
				cancel.setEnabled(false);
				edit.setEnabled(true);
			}
		});
	}

	// ����ת
	private void initFirst() {
		leftAnimation = new SquareRotate(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(600);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(600);
	}

	// ����ת
	private void initSecond() {
		leftAnimation = new SquareRotate(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(600);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(600);
	}

	// B��ת��A������λ��
	private void right2front(SquareRotate rightAnimation) {
		home_in_screen = false;
		if (home_ad_timer != null) {
			home_ad_timer.cancel();
			home_ad_timer = null;
		}
		ad_index = 0;
		setRight(rightAnimation);
	}

	// A��ת��B������λ��
	private void front2right(SquareRotate leftAnimation) {
		home_in_screen = true;
		setFront(leftAnimation);
		home_ad_timer = new Timer();
		ad_task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				ad_index = ad_gallery.getSelectedItemPosition();
				ad_index++;
				handler.sendMessage(message);
			}
		};
		home_ad_timer.schedule(ad_task, 2000, 2000);
	}

	// D��ת��A������λ��
	private void left2front(SquareRotate leftAnimation) {
		home_in_screen = false;
		if (home_ad_timer != null) {
			home_ad_timer.cancel();
			home_ad_timer = null;
		}
		ad_index = 0;
		setLeft(leftAnimation);

	}

	// A��ת��D������λ��
	private void front2left(SquareRotate rightAnimation) {
		home_in_screen = true;
		setFront(rightAnimation);
		home_ad_timer = new Timer();
		ad_task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				ad_index = ad_gallery.getSelectedItemPosition();
				ad_index++;
				handler.sendMessage(message);
			}
		};
		home_ad_timer.schedule(ad_task, 2000, 2000);
	}

	// C��ת��D������λ��
	private void back2left(SquareRotate leftAnimation) {
		setBack(leftAnimation);
	}

	// D��ת��C������λ��
	private void left2back(SquareRotate rightAnimation) {
		setLeft(rightAnimation);
	}

	// C��ת��B������λ��
	private void back2right(SquareRotate rightAnimation) {
		setBack(rightAnimation);
	}

	// B��ת��C������λ��
	private void right2back(SquareRotate leftAnimation) {
		setRight(leftAnimation);
	}

	// Aλ������ʱ������ת ֱ��Bλ������
	private void wallFront_leftMoveHandle() {
		initFirst();
		layoutFront.startAnimation(leftAnimation);
		right2front(rightAnimation);
	}

	// Aλ������ʱ������ת ֱ��Dλ������
	private void wallFront_rightMoveHandle() {
		initSecond();
		layoutFront.startAnimation(rightAnimation);
		left2front(leftAnimation);
	}

	// Dλ������ʱ������ת ֱ��Aλ������
	private void wallLeft_leftMoveHandle() {
		initFirst();
		layoutLeft.startAnimation(leftAnimation);
		front2left(rightAnimation);
	}

	// Dλ������ʱ������ת ֱ��Cλ������
	private void wallLeft_rightMoveHandle() {
		initSecond();
		layoutLeft.startAnimation(rightAnimation);
		back2left(leftAnimation);
	}

	// Bλ������ʱ������ת ֱ��Cλ������
	private void wallRight_leftMoveHandle() {
		initFirst();
		layoutRight.startAnimation(leftAnimation);
		back2right(rightAnimation);
	}

	// Bλ������ʱ������ת ֱ��Aλ������
	private void wallRight_rightMoveHandle() {
		initSecond();
		layoutRight.startAnimation(rightAnimation);
		front2right(leftAnimation);
	}

	// Cλ������ʱ������ת ֱ��Dλ������
	private void wallBack_leftMoveHandle() {
		initFirst();
		layoutBack.startAnimation(leftAnimation);
		left2back(rightAnimation);
	}

	// Cλ������ʱ������ת ֱ��Bλ������
	private void wallBack_rightMoveHandle() {
		initSecond();
		layoutBack.startAnimation(rightAnimation);
		right2back(leftAnimation);
	}

}
