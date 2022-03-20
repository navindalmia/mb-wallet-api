package com.mb.api.mb_wallet_api.user;

import ratpack.func.Block;
import ratpack.handling.Context;

public interface UserDaoService {
	
	public User save();
//	public void findOne(Integer id);

	public Integer getUserIdFromToken(String token);

}
