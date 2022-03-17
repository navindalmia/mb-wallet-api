package com.mb.api.mb_wallet_api;

import org.json.simple.JSONObject;

import com.mb.api.mb_wallet_api.user.UserDaoService;
import com.mb.api.mb_wallet_api.user.UserDaoServiceImpl;
import com.mb.api.mb_wallet_api.user.UserToken;
import com.mb.api.mb_wallet_api.user.UserTokenGenerator;
import com.mb.api.mb_wallet_api.userWallet.UserBalance;
import com.mb.api.mb_wallet_api.userWallet.UserWalletDaoServiceImpl;

import ratpack.handling.Context;
import ratpack.jackson.Jackson;
import ratpack.server.PublicAddress;
import ratpack.server.RatpackServer;

public class App {
	UserDaoService objUserDaoService = new UserDaoServiceImpl();
	UserWalletDaoServiceImpl objUserWalletDaoService = new UserWalletDaoServiceImpl();
	UserTokenGenerator utg = new UserTokenGenerator();

	public static void main(String[] args) throws Exception {

		new App().runServer();

	}

	private void runServer() throws Exception {
		RatpackServer.start(serverDefinition -> serverDefinition.handlers(
				handler -> handler
				.path("login", ctx -> ctx.byMethod(action -> action.post(this::createUser)))
				.path("balance", ctx -> ctx.byMethod(action -> action.get(this::getBalance)))
				));

	}

	private void createUser(Context ctx) {
		String userId = objUserDaoService.save(); // creating user Id
		objUserWalletDaoService.createBalanceForUserID(Integer.valueOf(userId)); // Initialising balance

		

		String token = utg.generateUserToken(userId);

		respondWith201(ctx, token);

	}
	
	private String validateAndGetUserIdFromToken(Context ctx) {
		String token = ctx.getRequest().getHeaders().get("Authorization");
		System.out.println("after fetching token"+token);
		if(token==null || "".equals(token))
			respondWith40(ctx,401, "Invalid token!");
		
		String userId=utg.parseUserIdFromInputToken(token);
		System.out.println("after fetching userID"+userId);
		if(userId==null)
			respondWith40(ctx,401, "Invalid token!");
		return userId;
	}
	
	
	private void getBalance(Context ctx) {

		String userId=validateAndGetUserIdFromToken( ctx);
		//userId="4";//temp
		UserBalance balance = objUserWalletDaoService.getBalanceForUserID( Integer.valueOf(userId));
		System.out.println("after fetching balance"+balance);
		
		if(balance != null) {
		ctx.getResponse().status(200);
		ctx.render(Jackson.json(balance));
		}
		else {
			ctx.getResponse().status(200);
			ctx.render(Jackson.json(balance));
			respondWith40(ctx,404, "Balance not available!");
		}


	}

	private void respondWith201(Context ctx, String token) {
		PublicAddress url = ctx.get(PublicAddress.class);
		System.out.println("inside respondWith201");

//		UserToken utoken = new UserToken(token);
		JSONObject obj = new JSONObject();
		obj.put("token", token);

		ctx.getResponse().status(201);
		ctx.render(Jackson.json(obj));
	}
	private void respondWith40(Context ctx,int statusCode, String msg) {
		PublicAddress url = ctx.get(PublicAddress.class);
		System.out.println("inside respondWith401");

//		UserToken utoken = new UserToken(token);
		JSONObject obj = new JSONObject();
		obj.put("error", msg);

		ctx.getResponse().status(statusCode);
		ctx.render(Jackson.json(obj));
	}
}
