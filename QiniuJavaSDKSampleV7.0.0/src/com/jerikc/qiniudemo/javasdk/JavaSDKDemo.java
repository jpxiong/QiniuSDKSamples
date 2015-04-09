package com.jerikc.qiniudemo.javasdk;

import com.qiniu.http.Response;

public class JavaSDKDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UploadFile uploadFile = new UploadFile();
		Response response = uploadFile.uploadFileToCloud();
		
		System.out.println(response == null ? null : response.toString());
	}
}
