package com.jerikc.qiniudemo.javasdk.uploadfile;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

public class Authorization {
        public static final String ACCESS_KEY = "your ak";
        public static final String SECRET_KEY = "your sk";
        public static final String BUCKET_NAME = "your bucket";
        
        public static String getAuthToken() {
                Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
                PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
                String upToken = null;
                try {
                        upToken = putPolicy.token(mac);
                } catch (AuthException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return upToken;
        }
}

