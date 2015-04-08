package com.jerikc.qiniudemo.javasdk.uploadfile;

import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;

public class UploadFile {

        private final static String LOCAL_FILE_PATH = "/Users/jerikc/test.txt";
        private final static String FILE_NAME_ON_QINIU = "test.txt";

        public void uploadFileToQiniu(String filePath, String fileName) {
                String upToken = Authorization.getAuthToken();
                PutExtra putExtra = new PutExtra();
                
                // push the file into the Qiniu cloud storage
                PutRet putRet = IoApi.putFile(upToken, fileName, filePath, putExtra);
                
                System.out.println(putRet.getKey()); //the file name on Qiniu cloud storage
        }

        public static void main(String args[]) {
                UploadFile uploadFile = new UploadFile();
                try {
                        uploadFile.uploadFileToQiniu(LOCAL_FILE_PATH, FILE_NAME_ON_QINIU);
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }
}
