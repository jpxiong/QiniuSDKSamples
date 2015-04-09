package com.jerikc.qiniudemo.javasdk;

import com.jerikc.qiniudemo.javasdk.auth.Authorization;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

public class UploadFile {
    private static final String FILE_NAME_ON_CLOUD = "test25.txt";
    private static final String LOCAL_FILE_FULL_PATH = "/Users/jerikc/test.txt";
    
    public Response uploadFileToCloud() {
    	UploadManager uploadManager = new UploadManager();
		
	    try {
			Response response = uploadManager.put(LOCAL_FILE_FULL_PATH, FILE_NAME_ON_CLOUD, Authorization.getAuthToken());
			return response;
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
    }
}
