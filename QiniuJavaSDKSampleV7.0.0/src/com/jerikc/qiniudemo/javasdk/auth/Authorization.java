package com.jerikc.qiniudemo.javasdk.auth;

import com.qiniu.util.Auth;

public class Authorization {
//        public static final String ACCESS_KEY = "your ak";
//        public static final String SECRET_KEY = "your sk";
//        public static final String BUCKET_NAME = "your bucket";
        
		public static final String ACCESS_KEY = "-DyVIaGWHtncdap_3MSNbCJ_aVY7LMWD4Dgq6Z1I";
	    public static final String SECRET_KEY = "feZC1S5L6Lg-qkpR1l0DUR-9kLSmI1K-5liKkHqd";
	    public static final String BUCKET_NAME = "testspace";
	    
	    private Authorization() {
	    	
	    }
	    
        public static String getAuthToken() {
        	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    	    return auth.uploadToken(BUCKET_NAME);
        }
}

