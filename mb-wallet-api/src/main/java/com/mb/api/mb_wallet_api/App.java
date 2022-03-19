package com.mb.api.mb_wallet_api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.api.mb_wallet_api.user.UserDaoService;
import com.mb.api.mb_wallet_api.user.UserDaoServiceImpl;
import com.mb.api.mb_wallet_api.user.UserTokenGenerator;
import com.mb.api.mb_wallet_api.userWallet.UserBalance;
import com.mb.api.mb_wallet_api.userWallet.UserTransaction;
import com.mb.api.mb_wallet_api.userWallet.UserWalletDaoServiceImpl;

import io.netty.handler.codec.http.HttpHeaderNames;
import ratpack.exec.Promise;
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

//Working
//	private void runServer() throws Exception {
//		RatpackServer.start(serverDefinition -> serverDefinition
//				.handlers(handler -> handler
//						.path("login", ctx -> ctx.byMethod(action -> action.post(this::createUser)))
//						.path("balance", ctx -> ctx.byMethod(action -> action.get(this::getBalance)))));
//
//	}
	private void runServer() throws Exception {
		RatpackServer.start(serverDefinition -> serverDefinition
				.handlers(handler -> handler.path("login", ctx -> ctx.byMethod(action -> action.post(() -> {
					Promise<String> userPromise = Promise.value(this.createUser(ctx));
					userPromise.then(token -> respondWith20x(ctx,"token" ,token));

				}))).path("balance", ctx -> ctx.byMethod(action -> action.get(() -> {
					Promise<UserBalance> userBalancePromise = Promise.value(this.getBalance(ctx));
//					Promise<UserBalance> userBalancePromise =  Promise.async(ctx).blockingMap(this.getBalance(ctx));
					userBalancePromise.then(balance -> respondWithBalance(ctx, balance));

				})))
						.path("spend", ctx -> ctx.byMethod(action -> action.post(() -> {
						//Reading JSON from request body
						 String json = ctx.getRequest().getBody().toString();
						 //Converting into object
//						 ObjectMapper mapper = new ObjectMapper();
//						 UserTransaction newtransaction1 = mapper.readValue(json, UserTransaction.class);
//						 System.out.println("newtransaction1"+newtransaction1);
						 
						 Promise<UserTransaction> userTransactionPromise= ctx.parse(UserTransaction.class)
					        .onError(error -> ctx.getResponse().status(500)
					                .send(error.getMessage()))
								 ;
//						 userTransactionPromise.blockingMap(this.insertTransaction(ctx));
//						 Promise<UserTransaction> userTransactionPromise = Promise.value(this.insertTransaction(ctx));
//						 userTransactionPromise.map(this.insertTransaction(ctx)).then(newtransaction -> respondWith20x(ctx, "Transaction Updated"));
						 userTransactionPromise.then(
								 userTransaction -> {
									 System.out.println("here userTransaction:"+userTransaction);
									 this.insertTransaction(ctx,userTransaction);
									 respondWith20x(ctx,"message", "Transaction Updated");
								 
								 }
								 );
//
						})))
						
						));

	}
//	private Transaction mapJsonInputToTransaction(Context ctx) {
//		return null;
//	}

	private void insertTransaction(Context ctx,UserTransaction userTransaction) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String userId = validateAndGetUserIdFromToken(ctx);

		userTransaction.setUserId(Integer.valueOf(userId));
		 System.out.println("newtransaction:"+userTransaction);
		 objUserWalletDaoService.createTransactionForUserID(userTransaction);
		 objUserWalletDaoService.updateBalanceForUserID(userTransaction);

	}

	private String createUser(Context ctx) {
		String userId = objUserDaoService.save(); // creating user Id
		objUserWalletDaoService.createBalanceForUserID(Integer.valueOf(userId)); // Initialising balance

		String token = utg.generateUserToken(userId);
		return token;

	}
//Working	
//	private void createUser(Context ctx) {
//		String userId = objUserDaoService.save(); // creating user Id
//		objUserWalletDaoService.createBalanceForUserID(Integer.valueOf(userId)); // Initialising balance
//
//		String token = utg.generateUserToken(userId);
//
//		respondWith20x(ctx, token);
//
//	}

//	private String validateAndGetUserIdFromToken(Context ctx) {
//		String token = ctx.getRequest().getHeaders().get("Authorization");
//		System.out.println("after fetching token"+token);
//		if(token==null || "".equals(token))
//			respondWith40x(ctx,401, "Invalid token!");
//		
//		String userId=utg.parseUserIdFromInputToken(token);
//		System.out.println("after fetching userID"+userId);
//		if(userId==null)
//			respondWith40x(ctx,401, "Invalid token!");
//		return userId;
//	}
	private String validateAndGetUserIdFromToken(Context ctx) {
		String token = null;
		;
		String tokenHeader = ctx.getRequest().getHeaders().get(HttpHeaderNames.AUTHORIZATION);
		if (tokenHeader != null && tokenHeader.startsWith("Bearer")) {
			// Confirm token is valid
			List<String> parts = Arrays.asList(tokenHeader.trim().split(" "));
			if (parts.size() == 2) {
				token = parts.get(1);
			} else {
				respondWith40x(ctx, 401, "Invalid token!");
			}
		} else {
			token = ctx.getRequest().getHeaders().get(HttpHeaderNames.AUTHORIZATION);
		}
		System.out.println("after fetching token" + token);
		if (token == null || "".equals(token))
			respondWith40x(ctx, 401, "Invalid token!");

		String userId = utg.parseUserIdFromInputToken(token);
		System.out.println("after fetching userID" + userId);
		if (userId == null)
			respondWith40x(ctx, 401, "Invalid token!");
		return userId;
	}

	private UserBalance getBalance(Context ctx) {

		String userId = validateAndGetUserIdFromToken(ctx);

		UserBalance balance = objUserWalletDaoService.getBalanceForUserID(Integer.valueOf(userId));
		System.out.println("after fetching balance" + balance);
		return balance;
	}

	private void respondWithBalance(Context ctx, UserBalance balance) {
		if (balance != null) {
			ctx.getResponse().status(200);
			ctx.render(Jackson.json(balance));
		} else {

			ctx.render(Jackson.json(balance));
			respondWith40x(ctx, 404, "Balance not available!");
		}
	}

//Working
//	private void getBalance(Context ctx) {
//
//		String userId = validateAndGetUserIdFromToken(ctx);
//		// userId="4";//temp
//		UserBalance balance = objUserWalletDaoService.getBalanceForUserID(Integer.valueOf(userId));
//		System.out.println("after fetching balance" + balance);
//
//		if (balance != null) {
//			ctx.getResponse().status(200);
//			ctx.render(Jackson.json(balance));
//		} else {
//
//			ctx.render(Jackson.json(balance));
//			respondWith40x(ctx, 404, "Balance not available!");
//		}
//
//	}

	private void respondWith20x(Context ctx,String key ,String token) {
		PublicAddress url = ctx.get(PublicAddress.class);
		System.out.println("inside respondWith20x");

//		UserToken utoken = new UserToken(token);
		JSONObject obj = new JSONObject();
		obj.put(key, token);

		ctx.getResponse().status(201);
		ctx.render(Jackson.json(obj));
	}

	private void respondWith40x(Context ctx, int statusCode, String msg) {
		PublicAddress url = ctx.get(PublicAddress.class);
		System.out.println("inside respondWith401");

//		UserToken utoken = new UserToken(token);
		JSONObject obj = new JSONObject();
		obj.put("error", msg);

		ctx.getResponse().status(statusCode);
		ctx.render(Jackson.json(obj));
	}
}
