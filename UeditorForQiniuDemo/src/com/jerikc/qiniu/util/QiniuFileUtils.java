package com.jerikc.qiniu.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuFileUtils {
	private static final String AK_CONFIG_KEY = "qiniu.ak";
	private static final String SK_CONFIG_KEY = "qiniu.sk";
	private static final String BUCKET_CONFIG_KEY = "qiniu.bucket";
	
	private static String mUpToken = null;

	static {
		mUpToken = Auth.create(PropertiesUtils.getPropertyValue(AK_CONFIG_KEY), 
							   PropertiesUtils.getPropertyValue(SK_CONFIG_KEY)
							   ).uploadToken(PropertiesUtils.getPropertyValue(BUCKET_CONFIG_KEY));
	}
	
	public static String getUpToken() {
		return mUpToken;
	}
	
	public static void uploadFileToQiniu(String srcPath, String dstPath, String upToken) {
		UploadManager uploadMgr = new UploadManager();
		try {
			// push the local file into the qiniu cloud storage
			Response response = uploadMgr.put(srcPath, dstPath, upToken);
			System.out.println("xjp response:" + response.toString());
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
