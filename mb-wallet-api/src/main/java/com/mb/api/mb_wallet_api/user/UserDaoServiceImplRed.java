package com.mb.api.mb_wallet_api.user;

import java.util.NoSuchElementException;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import com.mb.api.mb_wallet_api.redis.MyRedisClient;

public class UserDaoServiceImplRed implements UserDaoService {
	RedissonClient redisson = MyRedisClient.getRedisClient();
	RList<User> users = redisson.getList("userList");

	@Override
	public User save() {

		User user = new User();
		UserTokenGenerator utg = new UserTokenGenerator();
		int userCount = 0;
		try {

			userCount = users.size();

			if (user.getUserId() == null) {
				user.setUserId(++userCount);
				//System.out.println("User created" + user);
			}

			String token = utg.generateUserToken(String.valueOf(userCount));
			user.setToken(token);

			//System.out.println("before adding User created users:" + users);
			users.add(user);
			//System.out.println("after User created users:" + users);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Integer getUserIdFromToken(String token) {
		// TODO Auto-generated method stub
		User user=null;
		try {
		//System.out.println("inside getUserIdFromToken" + token);
		 user = users.stream().filter(us -> (us.getToken() != null && us.getToken().equals(token))).findAny().get();
		//System.out.println("after getUserIdFromToken user" + user);
		Integer userId = (user != null) ? user.getUserId() : null;
		//System.out.println("after getUserIdFromToken userId" + userId);
		
		}
		catch(NoSuchElementException e1) {
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user.getUserId();
	}

}
