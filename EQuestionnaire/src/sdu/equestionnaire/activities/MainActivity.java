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
	private ViewGroup layoutA;
	private ViewGroup layoutC;
	private ViewGroup layoutB;
	private ViewGroup layoutD;

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

	// ����ת
	public void initFirst() {
		leftAnimation = new SquareRotate(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// ����ת
	public void initSecond() {
		leftAnimation = new SquareRotate(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new SquareRotate(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// B��ת��A������λ��
	public void B2A(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_right);
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

	// D��ת��A������λ��
	public void D2A(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_left);
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

	// A��ת��D������λ��
	public void A2D(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_front);
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

	// C��ת��D������λ��
	public void C2D(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_back);
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

	// C��ת��B������λ��
	public void C2B(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_back);
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

	// A��ת��B������λ��
	public void A2B(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_front);
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

	// D��ת��C������λ��
	public void D2C(SquareRotate rightAnimation) {
		setContentView(R.layout.main_layout_left);
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

	// B��ת��C������λ��
	public void B2C(SquareRotate leftAnimation) {
		setContentView(R.layout.main_layout_right);
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

	// Aλ������ʱ������ת ֱ��Bλ������
	public void wallA_leftMoveHandle() {
		initFirst();
		layoutA.startAnimation(leftAnimation);
		B2A(rightAnimation);
	}

	// Aλ������ʱ������ת ֱ��Dλ������
	public void wallA_rightMoveHandle() {
		initSecond();
		layoutA.startAnimation(rightAnimation);
		D2A(leftAnimation);
	}

	// Dλ������ʱ������ת ֱ��Aλ������
	public void wallD_leftMoveHandle() {
		initFirst();
		layoutD.startAnimation(leftAnimation);
		A2D(rightAnimation);
	}

	// Dλ������ʱ������ת ֱ��Cλ������
	public void wallD_rightMoveHandle() {
		initSecond();
		layoutD.startAnimation(rightAnimation);
		C2D(leftAnimation);
	}

	// Bλ������ʱ������ת ֱ��Cλ������
	public void wallB_leftMoveHandle() {
		initFirst();
		layoutB.startAnimation(leftAnimation);
		C2B(rightAnimation);
	}

	// Bλ������ʱ������ת ֱ��Aλ������
	public void wallB_rightMoveHandle() {
		initSecond();
		layoutB.startAnimation(rightAnimation);
		A2B(leftAnimation);
	}

	// Cλ������ʱ������ת ֱ��Dλ������
	public void wallC_leftMoveHandle() {
		initFirst();
		layoutC.startAnimation(leftAnimation);
		D2C(rightAnimation);
	}

	// Cλ������ʱ������ת ֱ��Bλ������
	public void wallC_rightMoveHandle() {
		initSecond();
		layoutC.startAnimation(rightAnimation);
		B2C(leftAnimation);
	}

}