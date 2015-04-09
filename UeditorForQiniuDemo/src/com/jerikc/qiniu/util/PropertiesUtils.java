package com.jerikc.qiniu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	private static final String CONFIG_FILE_NAME = "config.properties";

	public static String getPropertyValue(String key) {
		String value = null;
		InputStream inStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
		Properties prop = new Properties();
		try {
			prop.load(inStream);
			value = prop.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
