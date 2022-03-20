package com.mb.api.mb_wallet_api.user;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import com.mb.api.mb_wallet_api.redis.MyRedisClient;

public class UserDaoServiceImplRed implements UserDaoService {

	@Override
	public String save() {
		int userCount = 0;
		try {
			RedissonClient redisson = MyRedisClient.getRedisClient();
			RList<User> users = redisson.getList("userList");
			userCount = users.size();

			User user = new User();
			if (user.getUserId() == null) {
				user.setUserId(++userCount);
				System.out.println("User created" + user);
			}

			System.out.println("before adding User created users:" + users);
			users.add(user);
			System.out.println("after User created users:" + users);
//		MyRedisClient.shutdownRedisClientInstance(redisson);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(userCount);
	}

}
