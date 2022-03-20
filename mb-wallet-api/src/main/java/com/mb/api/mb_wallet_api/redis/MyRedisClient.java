package com.mb.api.mb_wallet_api.redis;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


public final class MyRedisClient {
    private static volatile RedissonClient redisson = null;

    private MyRedisClient() {}
    
    static {Config config;
    	try {
			config = Config.fromJSON(new File("src\\main\\java\\com\\mb\\api\\mb_wallet_api\\resources\\singleNodeConfig.json"));
			redisson = Redisson.create(config);
			 System.out.println("creating redisson:"+redisson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redisson=null;
		}  
	
    	
    }
    
    public static RedissonClient getRedisClient() {
    	return redisson;
    }

   
}
