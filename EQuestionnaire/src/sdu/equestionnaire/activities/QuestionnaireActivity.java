package sdu.equestionnaire.activities;

import sdu.equestionnaire.R;
import sdu.equestionnaire.customUI.QuickAction;
import sdu.equestionnaire.customUI.QuickActionBar;
import sdu.equestionnaire.customUI.QuickActionWidget;
import android.app.Activity;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class QuestionnaireActivity extends Activity implements OnClickListener {

	Handler handler = new Handler() {
		public void handleMessage(Message paramMessage) {
			if (paramMessage.what == 1) {
				info.findViewById(R.id.loadingbar).setVisibility(View.GONE);
				info.findViewById(R.id.serverdata).setVisibility(View.VISIBLE);
			}
		}
	};

	private LinearLayout info;
	private Animation enterAnim;
	private Animation exitAnim;
	private QuickActionWidget mBar;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question_main_layout);

		LayoutInflater inflater = LayoutInflater.from(this);
		info = (LinearLayout) inflater.inflate(R.layout.question_list_layout,
				null);

		LinearLayout scroll = (LinearLayout) findViewById(R.id.lite_list);

		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		scroll.addView(info, layoutParams);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}.start();

		prepareQuickActionBar();
		enterAnim = AnimationUtils.loadAnimation(this, R.anim.anim_enter);
		exitAnim = AnimationUtils.loadAnimation(this, R.anim.anim_exit);

		View btnRequest = findViewById(R.id.requestroute);
		btnRequest.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.requestroute: {
			mBar.show(v);
			break;
		}

		}

	}

	private void prepareQuickActionBar() {
		this.mBar = new QuickActionBar(this);
		this.mBar.addQuickAction(new MyQuickAction(this,
				R.drawable.question_model_list, "列表格式"));
		this.mBar.addQuickAction(new MyQuickAction(this,
				R.drawable.question_model_grid, "网格格式"));
		this.mBar.addQuickAction(new MyQuickAction(this,
				R.drawable.question_model_gallery, "画廊格式"));
		// this.mBar.setOnQuickActionClickListener(this.mActionListener);
	}

	private static class MyQuickAction extends QuickAction {
		private static final ColorFilter BLACK_CF = new LightingColorFilter(
				-16777216, -16777216);

		// public MyQuickAction(Context paramContext, int paramInt1, int
		// paramInt2)
		// {
		// super(buildDrawable(paramContext, paramInt1),
		// String.valueOf(paramInt2));
		// }

		public MyQuickAction(Context paramContext, int paramInt,
				CharSequence paramCharSequence) {
			super(paramContext, paramInt, paramCharSequence);
		}

		// private static Drawable buildDrawable(Context paramContext, int
		// paramInt)
		// {
		// Drawable localDrawable =
		// paramContext.getResources().getDrawable(paramInt);
		// localDrawable.setColorFilter(BLACK_CF);
		// return localDrawable;
		// }
	}
}