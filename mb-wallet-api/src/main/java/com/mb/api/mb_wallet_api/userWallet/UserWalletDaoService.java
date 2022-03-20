package com.mb.api.mb_wallet_api.userWallet;

import java.util.List;
import java.util.Map;

import com.mb.api.mb_wallet_api.error.ErrorMessage;

public interface UserWalletDaoService {

	public UserBalance getBalanceForUserID(Integer userId);

	public void createBalanceForUserID(Integer userId);

	Map createTransactionForUserID(UserTransaction userTransaction);

	void updateBalanceForUserID(UserTransaction userTransaction);
	
	

	List<UserTransaction> getUserTransactionList(Integer userId);

}
