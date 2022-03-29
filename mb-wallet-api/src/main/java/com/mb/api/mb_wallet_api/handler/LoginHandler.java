package com.mb.api.mb_wallet_api.handler;

import org.json.simple.JSONObject;

import com.mb.api.mb_wallet_api.user.User;
import com.mb.api.mb_wallet_api.user.UserDaoService;
import com.mb.api.mb_wallet_api.user.UserDaoServiceImplRed;
import com.mb.api.mb_wallet_api.user.UserTokenGenerator;
import com.mb.api.mb_wallet_api.userWallet.UserWalletDaoService;
import com.mb.api.mb_wallet_api.userWallet.UserWalletDaoServiceImplRed;

import ratpack.exec.Blocking;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Response;
import ratpack.jackson.Jackson;

public class LoginHandler implements Handler{
	UserDaoService objUserDaoService = new UserDaoServiceImplRed();
	UserWalletDaoService objUserWalletDaoService = new UserWalletDaoServiceImplRed();
	UserTokenGenerator utg = new UserTokenGenerator();
	@Override
	public void handle(Context ctx) throws Exception {
		// TODO Auto-generated method stub
//		 Blocking
//         .get(
//        		 () -> {
		System.out.println("Inside handler");
        			 User user = objUserDaoService.save();
        				System.out.println("Inside 333 handler");
		Integer userId = user.getUserId();// creating user Id
		System.out.println("Inside 555 handler");
		objUserWalletDaoService.createBalanceForUserID(userId); // Initialising balance
		ctx.header("Authorization", user.getToken());
        Response response = ctx.getResponse();
        response.status(201);
        response.send();
//        JSONObject obj = new JSONObject();
//		obj.put(key, token);
//		ctx.getResponse().status(201);
//		ctx.render(Jackson.json(obj));
        
        
        
//		return user.getToken();
//         }
//).then();
		
	}
	


}
