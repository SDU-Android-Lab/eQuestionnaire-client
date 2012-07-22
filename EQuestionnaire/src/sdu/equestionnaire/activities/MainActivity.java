package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.animations.SquareRotate;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 
 * @author lhy
 * 
 */
public class MainActivity extends Activity {

	private int mCenterX = 160;
	private int mCenterY = 0;
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
		setContentView(R.layout.main_layout_front);// 显示front
		disManager = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(disManager);
		final int screenWidth = disManager.widthPixels;
		final int screenHeight = disManager.heightPixels;
		mCenterX = screenWidth >> 1;

		// 显示正面
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// 左旋转
	private void initFirst() {
		leftAnimation = new SquareRotate(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// 右旋转
	private void initSecond() {
		leftAnimation = new SquareRotate(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// B面转到A面所在位置
	private void right2front(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.layout_right);
		layoutRight.startAnimation(rightAnimation);
		Button leftBtn = (Button) findViewById(R.id.right_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.right_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallRight_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallRight_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A面转到B面所在位置
	private void front2right(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		layoutFront.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D面转到A面所在位置
	private void left2front(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.layout_left);
		layoutLeft.startAnimation(leftAnimation);
		Button leftBtn = (Button) findViewById(R.id.left_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.left_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);
		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallLeft_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallLeft_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A面转到D面所在位置
	private void front2left(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_front);
		layoutFront = (ViewGroup) findViewById(R.id.layout_front);
		layoutFront.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallFront_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C面转到D面所在位置
	private void back2left(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.layout_back);
		layoutBack.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.back_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.back_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallBack_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallBack_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D面转到C面所在位置
	private void left2back(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_left);
		layoutLeft = (ViewGroup) findViewById(R.id.layout_left);
		layoutLeft.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.left_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.left_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallLeft_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallLeft_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C面转到B面所在位置
	private void back2right(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_back);
		layoutBack = (ViewGroup) findViewById(R.id.layout_back);
		layoutBack.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.back_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.back_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallBack_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallBack_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// B面转到C面所在位置
	private void right2back(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_right);
		layoutRight = (ViewGroup) findViewById(R.id.layout_right);
		layoutRight.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.right_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.right_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallRight_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallRight_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A位于正面时向左旋转 直到B位于正面
	private void wallFront_leftMoveHandle() {
		initFirst();
		layoutFront.startAnimation(leftAnimation);
		right2front(rightAnimation);
	}

	// A位于正面时向右旋转 直到D位于正面
	private void wallFront_rightMoveHandle() {
		initSecond();
		layoutFront.startAnimation(rightAnimation);
		left2front(leftAnimation);
	}

	// D位于正面时向左旋转 直到A位于正面
	private void wallLeft_leftMoveHandle() {
		initFirst();
		layoutLeft.startAnimation(leftAnimation);
		front2left(rightAnimation);
	}

	// D位于正面时向右旋转 直到C位于正面
	private void wallLeft_rightMoveHandle() {
		initSecond();
		layoutLeft.startAnimation(rightAnimation);
		back2left(leftAnimation);
	}

	// B位于正面时向左旋转 直到C位于正面
	private void wallRight_leftMoveHandle() {
		initFirst();
		layoutRight.startAnimation(leftAnimation);
		back2right(rightAnimation);
	}

	// B位于正面时向右旋转 直到A位于正面
	private void wallRight_rightMoveHandle() {
		initSecond();
		layoutRight.startAnimation(rightAnimation);
		front2right(leftAnimation);
	}

	// C位于正面时向左旋转 直到D位于正面
	private void wallBack_leftMoveHandle() {
		initFirst();
		layoutBack.startAnimation(leftAnimation);
		left2back(rightAnimation);
	}

	// C位于正面时向左旋转 直到B位于正面
	private void wallBack_rightMoveHandle() {
		initSecond();
		layoutBack.startAnimation(rightAnimation);
		right2back(leftAnimation);
	}

}
