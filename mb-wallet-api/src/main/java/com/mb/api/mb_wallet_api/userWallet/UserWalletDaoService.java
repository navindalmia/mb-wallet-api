package com.mb.api.mb_wallet_api.userWallet;

import java.util.List;

public interface UserWalletDaoService {

	
	public UserBalance getBalanceForUserID(Integer userId) ;
	public void createBalanceForUserID(Integer userId) ;
	void createTransactionForUserID(UserTransaction userTransaction);
	void updateBalanceForUserID(UserTransaction userTransaction);
	List getUserTransactionList(Integer userId);
	
}
