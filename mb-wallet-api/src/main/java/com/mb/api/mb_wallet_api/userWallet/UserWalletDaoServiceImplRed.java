package com.mb.api.mb_wallet_api.userWallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.mb.api.mb_wallet_api.error.ErrorMessage;
import com.mb.api.mb_wallet_api.redis.MyRedisClient;

public class UserWalletDaoServiceImplRed implements UserWalletDaoService {

	RedissonClient redisson = MyRedisClient.getRedisClient();
	RMap<Integer, UserBalance> userBalances = redisson.getMap("userBalanceMap");
	RMap<Integer, CopyOnWriteArrayList<UserTransaction>> userTransactionsMap = redisson.getMap("userTransactionsMap");

	@Override
	public UserBalance getBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub

		UserBalance balance = userBalances.get(userId);
		return balance;
	}

	@Override
	public void createBalanceForUserID(Integer userId) {
		// TODO Auto-generated method stub

		UserBalance userBalance = new UserBalance(userId);
		userBalances.put(userId, userBalance);
		//System.out.println(userBalances);
	}
@Override
	public void updateBalanceForUserID(UserTransaction userTransaction) {
		// TODO Auto-generated method stub
		while (true) {
			UserBalance userBalance = getBalanceForUserID(userTransaction.getUserId());
			BigDecimal oldBalanceAmount = userBalance.getCurrentBalance();
			BigDecimal newBalanceAmount = oldBalanceAmount.subtract(userTransaction.getAmount());
			UserBalance newUserBalance = new UserBalance(userTransaction.getUserId(), newBalanceAmount,
					userBalance.getCurrency());

			if (userBalances.replace(userTransaction.getUserId(), userBalance, newUserBalance)) {
				return;

			}

			//System.out.println(userBalances);
		}
	}

	@Override
	public Map createTransactionForUserID(UserTransaction userTransaction) {
		// TODO Auto-generated method stub
		Map outMap = new HashMap();
//		System.out.println("amount:"+userTransaction.getAmount());
		if(null == userTransaction.getAmount() ) {
			outMap.put("errMessage",new ErrorMessage("400","Invalid Amount!"));
			
//			System.out.println("insiden outMap:"+outMap);
			return outMap;
		}
		CopyOnWriteArrayList<UserTransaction> userTransactionsList = userTransactionsMap
				.get(userTransaction.getUserId());
		if (userTransactionsList == null) {
			CopyOnWriteArrayList<UserTransaction> newUserTransactionsList = new CopyOnWriteArrayList<UserTransaction>();
			newUserTransactionsList.add(userTransaction);
			userTransactionsMap.put(userTransaction.getUserId(), newUserTransactionsList);
		} else {
			userTransactionsList.add(userTransaction);

			userTransactionsMap.put(userTransaction.getUserId(), userTransactionsList);
		}

		//System.out.println(userTransactionsMap);
		return outMap;
	}

	public List<UserTransaction> getUserTransactionList(Integer userId) {
		// TODO Auto-generated method stub
		List<UserTransaction> userTransactionsList = userTransactionsMap.get(userId);

		return userTransactionsList;
	}

}
