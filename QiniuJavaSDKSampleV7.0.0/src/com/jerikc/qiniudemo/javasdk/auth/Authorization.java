package com.jerikc.qiniudemo.javasdk.auth;

import com.qiniu.util.Auth;

public class Authorization {
        public static final String ACCESS_KEY = "your ak";
        public static final String SECRET_KEY = "your sk";
        public static final String BUCKET_NAME = "your bucket";
	    
	    private Authorization() {
	    	
	    }
	    
        public static String getAuthToken() {
        	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    	    return auth.uploadToken(BUCKET_NAME);
        }
}

