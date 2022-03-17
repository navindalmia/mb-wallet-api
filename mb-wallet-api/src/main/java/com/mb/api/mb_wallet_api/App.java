package com.mb.api.mb_wallet_api;

import com.mb.api.mb_wallet_api.user.UserDaoService;
import com.mb.api.mb_wallet_api.user.UserDaoServiceImpl;
import com.mb.api.mb_wallet_api.user.UserToken;
import com.mb.api.mb_wallet_api.user.UserTokenGenerator;

import ratpack.handling.Context;
import ratpack.jackson.Jackson;
import ratpack.server.PublicAddress;
import ratpack.server.RatpackServer;


public class App 
{UserDaoService objUserDaoService = new  UserDaoServiceImpl();
    public static void main( String[] args ) throws Exception
    {
      
        
            new App().runServer();
        

        
    }
    private void runServer() throws Exception {
        RatpackServer.start(serverDefinition -> serverDefinition
                .handlers(handler -> handler
                		.path("login", ctx -> ctx.byMethod(action -> action
                				
                				.post(this::createUser))

                				
                				)));
                				

    }
    private void createUser(Context ctx) {
    	String userId=objUserDaoService.save();
    	UserTokenGenerator utg = new UserTokenGenerator();
    	
    	String token=utg.generateUserToken(userId);
    	
                    respondWith201(ctx, token);
               
    }

    private void respondWith201(Context ctx, String token) {
        PublicAddress url = ctx.get(PublicAddress.class);
        System.out.println("inside respondWith201");

        UserToken utoken = new UserToken(token);

        ctx.getResponse().status(201);
        ctx.render(Jackson.json(utoken));
    }
}
