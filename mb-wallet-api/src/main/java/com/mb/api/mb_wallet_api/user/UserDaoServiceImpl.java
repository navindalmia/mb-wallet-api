package com.mb.api.mb_wallet_api.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mb.api.mb_wallet_api.userWallet.UserBalance;

import ratpack.func.Block;
import ratpack.handling.Context;
import ratpack.server.PublicAddress;

public class UserDaoServiceImpl implements UserDaoService {
//	private static List<User> users = new ArrayList();
	private static CopyOnWriteArrayList<User> users
    = new CopyOnWriteArrayList<User>();

	private static int userCount = 0;

	
	
//
//	@Override
//	public void findOne(Integer id) {
//		// TODO Auto-generated method stub
//
//		User user = users.stream().filter(x -> x.getUserId() == id).findFirst().get();
//		System.out.println("User found" + user);
//
//	}


	@Override
	public User save() {
		// TODO Auto-generated method stub
		User user = new User();
		if (user.getUserId() == null) {
			user.setUserId(++userCount);
			System.out.println("User created" + user);
		}
		users.add(user);
		
//		return String.valueOf(userCount);
		return user;
	}



	@Override
	public Integer getUserIdFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
