package com.mb.api.mb_wallet_api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public final class Utility {
	private static InputStream input = null;
	private static Properties prop = null;

	private Utility() {
	}

	static {
		try {
			prop = new Properties();
			input = new FileInputStream("src\\main\\java\\com\\mb\\api\\mb_wallet_api\\resources\\config.properties");
			System.out.println("loading prop input:" + input);
			prop.load(input);

			System.out.println("creating prop:" + prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			prop = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("exception creating prop:" + prop);
			e.printStackTrace();
			prop = null;
		}

	}

	public static String getProperty(String key) {
		System.out.println("reading key:" + key);
		return prop.getProperty(key);
	}

}
