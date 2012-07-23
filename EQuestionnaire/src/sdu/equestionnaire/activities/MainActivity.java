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

	private SquareRotate leftAnimation;
	private SquareRotate rightAnimation;

	private DisplayMetrics disManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout_front);// ��ʾfront
		disManager = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(disManager);
		final int screenWidth = disManager.widthPixels;
		final int screenHeight = disManager.heightPixels;
		mCenterX = screenWidth >> 1;

		// ��ʾ����
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.front_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.front_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		setFrontListener();
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	private void setFrontListener() {
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
	}

	private void setLeftListener() {
		layoutLeft.setOnTouchListener(new OnTouchListener() {

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
	}

	private void setRightListener() {
		layoutRight.setOnTouchListener(new OnTouchListener() {

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
	}

	private void setBackListener() {
		layoutBack.setOnTouchListener(new OnTouchListener() {

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
	}

	// ����ת
	private void initFirst() {
		leftAnimation = new SquareRotate(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// ����ת
	private void initSecond() {
		leftAnimation = new SquareRotate(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// B��ת��A������λ��
	private void right2front(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.layout_right);
		layoutRight.startAnimation(rightAnimation);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.right_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.right_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallRight_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A��ת��B������λ��
	private void front2right(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		layoutFront.startAnimation(leftAnimation);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.front_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.front_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D��ת��A������λ��
	private void left2front(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.layout_left);
		layoutLeft.startAnimation(leftAnimation);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.left_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.left_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallLeft_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A��ת��D������λ��
	private void front2left(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		layoutFront.startAnimation(rightAnimation);
		ImageButton leftBtn = (ImageButton) findViewById(R.id.front_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.front_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C��ת��D������λ��
	private void back2left(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.layout_back);
		layoutBack.startAnimation(leftAnimation);

		ImageButton leftBtn = (ImageButton) findViewById(R.id.back_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.back_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallBack_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D��ת��C������λ��
	private void left2back(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.layout_left);
		layoutLeft.startAnimation(rightAnimation);

		ImageButton leftBtn = (ImageButton) findViewById(R.id.left_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.left_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallLeft_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C��ת��B������λ��
	private void back2right(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.layout_back);
		layoutBack.startAnimation(rightAnimation);

		ImageButton leftBtn = (ImageButton) findViewById(R.id.back_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.back_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallBack_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// B��ת��C������λ��
	private void right2back(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.layout_right);
		layoutRight.startAnimation(leftAnimation);

		ImageButton leftBtn = (ImageButton) findViewById(R.id.right_leftBtn);
		ImageButton rightBtn = (ImageButton) findViewById(R.id.right_rightBtn);

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
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				wallRight_rightMoveHandle();
				v.setEnabled(false);
			}
		});
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
