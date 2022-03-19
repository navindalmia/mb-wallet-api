package com.mb.api.mb_wallet_api.userWallet;

public interface UserWalletDaoService {

	
	public UserBalance getBalanceForUserID(Integer userId) ;
	public void createBalanceForUserID(Integer userId) ;
	void createTransactionForUserID(UserTransaction userTransaction);
	
}
