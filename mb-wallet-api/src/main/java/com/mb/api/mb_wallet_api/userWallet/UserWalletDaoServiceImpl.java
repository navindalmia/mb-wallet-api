package com.mb.api.mb_wallet_api.userWallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserWalletDaoServiceImpl implements UserWalletDaoService{

//	private static List<UserBalance> userBalances = new ArrayList();
//	private static CopyOnWriteArrayList<UserBalance> userBalances
//    = new CopyOnWriteArrayList<UserBalance>();
//	private static CopyOnWriteArrayList<UserTransaction> userTransactions
//    = new CopyOnWriteArrayList<UserTransaction>();
	private static Map<Integer,UserBalance> userBalances
  = new ConcurrentHashMap<Integer,UserBalance>();
	private static Map<Integer,CopyOnWriteArrayList<UserTransaction>> userTransactionsMap
	  = new ConcurrentHashMap<Integer,CopyOnWriteArrayList<UserTransaction>>();

	//WOrking
//	@Override
//	public UserBalance getBalanceForUserID(Integer userId) {
//		// TODO Auto-generated method stub
//
//		UserBalance balance = userBalances
//				.stream().filter(x -> x.getUserID() == userId).findFirst().get();
//		return balance;
//	}
	
	@Override
	public UserBalance getBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub

		UserBalance balance = userBalances.get(userId);
		return balance;
	}
	

	@Override
	public void createBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub
		UserBalance userBalance=	new UserBalance( userId);
		userBalances.put(userId,userBalance);
		System.out.println(userBalances);
	}
	
	public void updateBalanceForUserID(UserTransaction userTransaction) {
		// TODO Auto-generated method stub
		while(true) {
		UserBalance userBalance=getBalanceForUserID(userTransaction.getUserId());
		BigDecimal oldBalanceAmount = userBalance.getCurrentBalance();
		BigDecimal newBalanceAmount=oldBalanceAmount.subtract(userTransaction.getAmount());
//		userBalance.setCurrentBalance(newBalanceAmount);
		UserBalance newUserBalance = new UserBalance(userTransaction.getUserId(),newBalanceAmount,userBalance.getCurrency());
		
		if(userBalances.replace(userTransaction.getUserId(),userBalance,newUserBalance)) {
			return;
			
		}

		System.out.println(userBalances);
		}
	}


	@Override
	public void createTransactionForUserID(UserTransaction userTransaction) {
		// TODO Auto-generated method stub
//		userTransactions.add(userTransaction);
		CopyOnWriteArrayList<UserTransaction> userTransactionsList= userTransactionsMap.get(userTransaction.getUserId());
		if(userTransactionsList == null) {
			CopyOnWriteArrayList<UserTransaction> newUserTransactionsList= new CopyOnWriteArrayList<UserTransaction>();
			newUserTransactionsList.add(userTransaction);
			userTransactionsMap.put(userTransaction.getUserId(), newUserTransactionsList);
		}
			else {
				userTransactionsList.add(userTransaction);
				
				userTransactionsMap.put(userTransaction.getUserId(), userTransactionsList);
			}
		
		
		System.out.println(userTransactionsMap);
	}


	public List getUserTransactionList(Integer userId) {
		// TODO Auto-generated method stub
		List userTransactionsList=userTransactionsMap.get(userId);
		
		
		return userTransactionsList;
	}

}
