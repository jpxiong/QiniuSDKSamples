package com.jerikc.qiniudemo.javasdk.uploadfile;

import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;

public class UploadFile {

        private final static String LOCAL_FILE_FULL_PATH = "/Users/jerikc/test.txt";
        private final static String FILE_NAME_ON_CLOUD = "test.txt";

        public void uploadFileToQiniu(String fullPath, String fileNameOnCloud) {
                String upToken = Authorization.getAuthToken();
                PutExtra putExtra = new PutExtra();
                
                // push the file into the Qiniu cloud storage
                PutRet putRet = IoApi.putFile(upToken, fileNameOnCloud, fullPath, putExtra);
                
                System.out.println(putRet.getKey()); //the file name on Qiniu cloud storage
        }

        public static void main(String args[]) {
                UploadFile uploadFile = new UploadFile();
                try {
                        uploadFile.uploadFileToQiniu(LOCAL_FILE_FULL_PATH, FILE_NAME_ON_CLOUD);
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }
}
