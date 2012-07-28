package sdu.equestionnaire.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sdu.equestionnaire.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private List<Map<String, Object>> list = null;
	static final String[] TYPE = { "济南乐呵呵纯牛奶口味调查", "iclass手机客户端满意度调查", };

	public final class Holder {
		ImageView picture;
		TextView text;
	}

	public HomeListAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		list = getDate();
	}

	private List<Map<String, Object>> getDate() {
		List<Map<String, Object>> listLitong = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < TYPE.length; i++) {
			Map<String, Object> maplitong = new HashMap<String, Object>();
			maplitong.put("text", TYPE[i]);
			maplitong.put("img_pre", R.drawable.main_home_item_mark);
			listLitong.add(maplitong);
		}

		return listLitong;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.main_home_list_item, null);
			holder.picture = (ImageView) convertView
					.findViewById(R.id.home_list_img);
			holder.text = (TextView) convertView
					.findViewById(R.id.home_list_text);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.picture.setImageResource(R.drawable.main_home_item_mark);
		holder.text.setText((String) list.get(position).get("text"));

		return convertView;
	}

}