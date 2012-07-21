package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.animations.Rotate3d;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity {

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
		setContentView(R.layout.main_layout_front);// ��ʾfront

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
		leftAnimation = new Rotate3d(0, -90, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new Rotate3d(90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// ����ת
	public void initSecond() {
		leftAnimation = new Rotate3d(-90, 0, 0.0f, 0.0f, mCenterX, mCenterY);
		rightAnimation = new Rotate3d(0, 90, 0.0f, 0.0f, mCenterX, mCenterY);
		leftAnimation.setFillAfter(true);
		leftAnimation.setDuration(1000);
		rightAnimation.setFillAfter(true);
		rightAnimation.setDuration(1000);
	}

	// B��ת��A������λ��
	public void B2A(Rotate3d rightAnimation) {
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
	public void D2A(Rotate3d leftAnimation) {
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
	public void A2D(Rotate3d rightAnimation) {
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
	public void C2D(Rotate3d leftAnimation) {
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
	public void C2B(Rotate3d rightAnimation) {
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
	public void A2B(Rotate3d leftAnimation) {
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
	public void D2C(Rotate3d rightAnimation) {
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
	public void B2C(Rotate3d leftAnimation) {
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