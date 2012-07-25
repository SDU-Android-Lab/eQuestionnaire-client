package sdu.equestionnaire.customUI;


import sdu.equestionnaire.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PhotoEditorView extends ImageView implements OnClickListener {
	
	public static final String TAG="PhotoEditorView";
	private boolean mHasSetPhoto = false;//�Ƿ�������ͼƬ
	public static final int REQUEST_PICK_PHOTO = 1;

	public PhotoEditorView(Context context) {
		super(context);
		
	}
	public PhotoEditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	
	protected void onFinishInflate() {//��View����������Ӷ����XML�е���֮�󣬵��ô˷���
        super.onFinishInflate();
        this.setOnClickListener(this);
    }
	
	//��ImageView������
	public void onClick(View v) {
		//����ʹ���û������ͼƬ
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< click >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if(mListener!=null){
			mListener.onRequest(REQUEST_PICK_PHOTO);
		}
	}
	
	//��Ҫ�������ImageView�Ƿ���������Ƭ
	public interface EditorListener {
		
		 public void onRequest(int request);
      
   }
	
	private EditorListener mListener;
	
	public void setEditorListener(EditorListener listener) {
		mListener=listener;
	}
	
	//ImageView�Ƿ��Ѿ�����ͼƬ
	public boolean hasSetPhoto() {
        return mHasSetPhoto;
    }
	
	
	//����Ĭ�ϵ�ͼƬ��ImageView
	public void resetDefaultPhoto(){//�������һ�����Ū������ͼƬ����
		setScaleType(ImageView.ScaleType.FIT_CENTER);
		setImageResource(R.drawable.contact_add_icon);
		setEnabled(true);
		mHasSetPhoto=false;
	}
	
	public void setPhotoBitmap(Bitmap photo) {
		if(photo==null){
			resetDefaultPhoto();
			return;
		}
		//���Ϊ�գ���ô�������µ�ͼƬ
        setImageBitmap(photo);
        setEnabled(true);
        mHasSetPhoto = true;
        System.out.println("set bitmap success");
	}

}
