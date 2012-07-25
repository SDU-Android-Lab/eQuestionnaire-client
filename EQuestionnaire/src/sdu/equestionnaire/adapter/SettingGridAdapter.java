package sdu.equestionnaire.adapter;

import sdu.equestionnaire.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SettingGridAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private DisplayMetrics localDisplayMetrics;

	public SettingGridAdapter(Context context, DisplayMetrics dm) {
		this.context = context;
		this.localDisplayMetrics = dm;
		inflater = LayoutInflater.from(context);
	}

	public final int getCount() {
		return 9;
	}

	public final Object getItem(int paramInt) {
		return null;
	}

	public final long getItemId(int paramInt) {
		return paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		paramView = inflater.inflate(R.layout.main_settig_grid_item, null);
		TextView text = (TextView) paramView.findViewById(R.id.activity_name);

		switch (paramInt) {
		case 0: {
			text.setText("定位");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_locatin);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 1: {
			text.setText("搜索");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_search);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 2: {
			text.setText("外观设定");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_appearance);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 3: {
			text.setText("积分兑换");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_exchange);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 4: {
			text.setText("网络设定");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_wifi);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 5: {
			text.setText("账号设定");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_account);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 6: {
			text.setText("最近浏览");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_history);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}

		case 7: {
			text.setText("客服中心");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_service);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}
		case 8: {
			text.setText("更多");
			Drawable draw = context.getResources().getDrawable(
					R.drawable.main_setting_more);
			draw.setBounds(0, 0, draw.getIntrinsicWidth(),
					draw.getIntrinsicHeight());
			text.setCompoundDrawables(null, draw, null, null);
			break;
		}
		}

		paramView.setMinimumHeight((int) (96.0F * localDisplayMetrics.density));
		paramView
				.setMinimumWidth(((-12 + localDisplayMetrics.widthPixels) / 3));

		return paramView;
	}
}
