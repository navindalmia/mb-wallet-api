package com.mb.api.mb_wallet_api.userWallet;

import java.util.ArrayList;
import java.util.List;

import com.mb.api.mb_wallet_api.user.User;

public class UserWalletDaoServiceImpl implements UserWalletDaoService{

	private static List<UserBalance> userBalances = new ArrayList();

	@Override
	public UserBalance getBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub

		UserBalance balance = userBalances
				.stream().filter(x -> x.getUserID() == userId).findFirst().get();
		return balance;
	}

	@Override
	public void createBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub
		UserBalance userBalance=	new UserBalance( userId);
		userBalances.add(userBalance);
		System.out.println(userBalances);
	}

	
}
