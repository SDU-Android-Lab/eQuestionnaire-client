package sdu.equestionnaire.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络连接状态检测
 * 
 * @author lhy
 * 
 */
public class ConnectionDetector {
	private Context context;

	public ConnectionDetector(Context context) {
		this.context = context;
	}

	/**
	 * 检测是否连接Internet
	 * 
	 * @return 连接状态
	 */
	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
		}
		return false;
	}
}
