package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.animations.SquareRotate;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * 
 * @author lhy
 * 
 */
public class MainActivity extends Activity {
	private int mCenterX = 160;
	private int mCenterY = 0;
	private int tStartX;

	private ViewGroup layoutFront;
	private ViewGroup layoutBack;
	private ViewGroup layoutRight;
	private ViewGroup layoutLeft;
	private View tabButtonSelectd;

	private SquareRotate leftAnimation;
	private SquareRotate rightAnimation;

	private DisplayMetrics disManager;

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
		// ��ʾ����
		setFront(null);
	}

	private void setFront(SquareRotate rorate) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		if (rorate != null)
			layoutFront.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.front_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.front_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);

		menu_home.setSelected(true);
		tabButtonSelectd = menu_home;

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
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}

				wallFront_leftMoveHandle();
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}
				wallFront_leftMoveHandle();
				wallRight_leftMoveHandle();

			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallFront_rightMoveHandle();
			}
		});
	}

	private void setLeft(SquareRotate rorate) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.layout_left);
		if (rorate != null)
			layoutLeft.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.left_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.left_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);

		menu_setting.setSelected(true);
		tabButtonSelectd = menu_setting;

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
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}
				wallLeft_leftMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallLeft_rightMoveHandle();
				wallBack_rightMoveHandle();

			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallLeft_rightMoveHandle();
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
			}
		});

	}

	private void setRight(SquareRotate rorate) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.layout_right);
		if (rorate != null)
			layoutRight.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.right_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.right_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);

		menu_question.setSelected(true);
		tabButtonSelectd = menu_question;

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
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}
				wallRight_rightMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallRight_leftMoveHandle();
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallRight_rightMoveHandle();
				wallFront_rightMoveHandle();
			}
		});

	}

	private void setBack(SquareRotate rorate) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.layout_back);
		if (rorate != null)
			layoutBack.startAnimation(rorate);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.back_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.back_rightBtn);
		ImageButton menu_home = (ImageButton) findViewById(R.id.main_menu_home);
		ImageButton menu_question = (ImageButton) findViewById(R.id.main_menu_question);
		ImageButton menu_account = (ImageButton) findViewById(R.id.main_menu_account);
		ImageButton menu_setting = (ImageButton) findViewById(R.id.main_menu_setting);

		menu_account.setSelected(true);
		tabButtonSelectd = menu_account;

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
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;
				}
				wallBack_rightMoveHandle();
				wallRight_rightMoveHandle();
			}
		});
		menu_question.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallBack_rightMoveHandle();
			}
		});
		menu_account.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
			}
		});
		menu_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean selected = v.isSelected();
				if (!selected) {
					tabButtonSelectd.setSelected(false);
					v.setSelected(true);
					tabButtonSelectd = v;

				}
				wallBack_leftMoveHandle();
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
		setRight(rightAnimation);
	}

	// A��ת��B������λ��
	private void front2right(SquareRotate leftAnimation) {
		setFront(leftAnimation);
	}

	// D��ת��A������λ��
	private void left2front(SquareRotate leftAnimation) {
		setLeft(leftAnimation);
	}

	// A��ת��D������λ��
	private void front2left(SquareRotate rightAnimation) {
		setFront(rightAnimation);
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
