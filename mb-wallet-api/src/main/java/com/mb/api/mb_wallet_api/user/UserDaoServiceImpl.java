package com.mb.api.mb_wallet_api.user;

import java.util.ArrayList;
import java.util.List;

import ratpack.func.Block;
import ratpack.handling.Context;
import ratpack.server.PublicAddress;

public class UserDaoServiceImpl implements UserDaoService {
	private static List<User> users = new ArrayList();

	private static int userCount = 0;

	
	

	@Override
	public void findOne(Integer id) {
		// TODO Auto-generated method stub

		User user = users.stream().filter(x -> x.getUserId() == id).findFirst().get();
		System.out.println("User found" + user);

	}


	@Override
	public String save() {
		// TODO Auto-generated method stub
		User user = new User();
		if (user.getUserId() == null) {
			user.setUserId(++userCount);
			System.out.println("User created" + user);
		}
		users.add(user);
		
		return String.valueOf(userCount);
//		return user;
	}

}