package sdu.equestionnaire.adapter;

import sdu.equestionnaire.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class HomeAdvertAdapter extends BaseAdapter {
	private int GalleryItemBackground;
	private Context context;
	private int[] picture = { R.drawable.gallery1, R.drawable.gallery2,
			R.drawable.gallery3, R.drawable.gallery4, R.drawable.gallery5,
			R.drawable.gallery6, };

	public HomeAdvertAdapter(Context context) {
		this.context = context;
		TypedArray typedArray = context
				.obtainStyledAttributes(R.styleable.Gallery);
		GalleryItemBackground = typedArray.getResourceId(
				R.styleable.Gallery_android_galleryItemBackground, 0);
	}

	public int getCount() {
		return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(picture[position % picture.length]);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setLayoutParams(new Gallery.LayoutParams(
				Gallery.LayoutParams.FILL_PARENT,
				Gallery.LayoutParams.FILL_PARENT));
		imageView.setBackgroundResource(GalleryItemBackground);
		return imageView;
	}

}
