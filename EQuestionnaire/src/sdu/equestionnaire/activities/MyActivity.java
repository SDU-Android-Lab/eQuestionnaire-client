package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.layouts.Rotate3d;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyActivity extends Activity {

	private int mCenterX = 160;
	private int mCenterY = 0;
	private ViewGroup layoutA;
	private ViewGroup layoutC;
	private ViewGroup layoutB;
	private ViewGroup layoutD;

	private Rotate3d leftAnimation;
	private Rotate3d rightAnimation;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_front);// 显示front

		// 显示正面
		layoutA = (ViewGroup) findViewById(R.id.layout_front);
		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// 左旋转
	public void initFirst() {
		leftAnimation = new Rotate3d(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new Rotate3d(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// 右旋转
	public void initSecond() {
		leftAnimation = new Rotate3d(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new Rotate3d(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// B面转到A面所在位置
	public void B2A(Rotate3d rightAnimation) {
		setContentView(R.layout.layout_right);
		layoutB = (ViewGroup) findViewById(R.id.layout_right);
		layoutB.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.right_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.right_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallB_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallB_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D面转到A面所在位置
	public void D2A(Rotate3d leftAnimation) {
		setContentView(R.layout.layout_left);
		layoutD = (ViewGroup) findViewById(R.id.layout_left);
		layoutD.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.left_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.left_rightBtn);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallD_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallD_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A面转到D面所在位置
	public void A2D(Rotate3d rightAnimation) {
		setContentView(R.layout.layout_front);
		layoutA = (ViewGroup) findViewById(R.id.layout_front);
		layoutA.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C面转到D面所在位置
	public void C2D(Rotate3d leftAnimation) {
		setContentView(R.layout.layout_back);
		layoutC = (ViewGroup) findViewById(R.id.layout_back);
		layoutC.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.back_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.back_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallC_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallC_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// C面转到B面所在位置
	public void C2B(Rotate3d rightAnimation) {
		setContentView(R.layout.layout_back);
		layoutC = (ViewGroup) findViewById(R.id.layout_back);
		layoutC.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.back_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.back_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallC_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallC_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A面转到B面所在位置
	public void A2B(Rotate3d leftAnimation) {
		setContentView(R.layout.layout_front);
		layoutA = (ViewGroup) findViewById(R.id.layout_front);
		layoutA.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.front_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.front_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallA_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// D面转到C面所在位置
	public void D2C(Rotate3d rightAnimation) {
		setContentView(R.layout.layout_left);
		layoutD = (ViewGroup) findViewById(R.id.layout_left);
		layoutD.startAnimation(rightAnimation);

		Button leftBtn = (Button) findViewById(R.id.left_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.left_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallD_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallD_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// B面转到C面所在位置
	public void B2C(Rotate3d leftAnimation) {
		setContentView(R.layout.layout_right);
		layoutB = (ViewGroup) findViewById(R.id.layout_right);
		layoutB.startAnimation(leftAnimation);

		Button leftBtn = (Button) findViewById(R.id.right_leftBtn);
		Button rightBtn = (Button) findViewById(R.id.right_rightBtn);

		leftBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallB_leftMoveHandle();
				v.setEnabled(false);
			}
		});
		rightBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				wallB_rightMoveHandle();
				v.setEnabled(false);
			}
		});
	}

	// A位于正面时向左旋转 直到B位于正面
	public void wallA_leftMoveHandle() {
		initFirst();
		layoutA.startAnimation(leftAnimation);
		B2A(rightAnimation);
	}

	// A位于正面时向右旋转 直到D位于正面
	public void wallA_rightMoveHandle() {
		initSecond();
		layoutA.startAnimation(rightAnimation);
		D2A(leftAnimation);
	}

	// D位于正面时向左旋转 直到A位于正面
	public void wallD_leftMoveHandle() {
		initFirst();
		layoutD.startAnimation(leftAnimation);
		A2D(rightAnimation);
	}

	// D位于正面时向右旋转 直到C位于正面
	public void wallD_rightMoveHandle() {
		initSecond();
		layoutD.startAnimation(rightAnimation);
		C2D(leftAnimation);
	}

	// B位于正面时向左旋转 直到C位于正面
	public void wallB_leftMoveHandle() {
		initFirst();
		layoutB.startAnimation(leftAnimation);
		C2B(rightAnimation);
	}

	// B位于正面时向右旋转 直到A位于正面
	public void wallB_rightMoveHandle() {
		initSecond();
		layoutB.startAnimation(rightAnimation);
		A2B(leftAnimation);
	}

	// C位于正面时向左旋转 直到D位于正面
	public void wallC_leftMoveHandle() {
		initFirst();
		layoutC.startAnimation(leftAnimation);
		D2C(rightAnimation);
	}

	// C位于正面时向左旋转 直到B位于正面
	public void wallC_rightMoveHandle() {
		initSecond();
		layoutC.startAnimation(rightAnimation);
		B2C(leftAnimation);
	}

}