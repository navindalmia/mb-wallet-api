package com.mb.api.mb_wallet_api.redis;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class MyRedisClient {

	public static RedissonClient getRedisClient() {
//Working
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//		RedissonClient redisson = Redisson.create(config);

		Config config;
		RedissonClient redisson;
		try {
			config = Config.fromJSON(new File("C:\\nd\\install\\GitRepo\\mb-wallet-api\\src\\main\\java\\com\\mb\\api\\mb_wallet_api\\resources\\singleNodeConfig.json"));
			 redisson = Redisson.create(config);
			 System.out.println("creating redisson:"+redisson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redisson=null;
		}  
	
		return redisson;
	}

	public static void shutdownRedisClientInstance(RedissonClient redisson) {

		redisson.shutdown();

	}

}
