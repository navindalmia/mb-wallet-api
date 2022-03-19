package com.mb.api.mb_wallet_api.userWallet;

import java.util.concurrent.CopyOnWriteArrayList;

public class UserWalletDaoServiceImpl implements UserWalletDaoService{

//	private static List<UserBalance> userBalances = new ArrayList();
	private static CopyOnWriteArrayList<UserBalance> userBalances
    = new CopyOnWriteArrayList<UserBalance>();

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
