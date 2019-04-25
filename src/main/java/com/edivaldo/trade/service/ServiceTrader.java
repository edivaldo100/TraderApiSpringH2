package com.edivaldo.trade.service;

import java.sql.Timestamp;
import java.util.List;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;

public interface ServiceTrader {
	List<User> findAllUsers();
	
	List<Trade> findAllTrade();
	
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteAllUsers();
	
	boolean isUserExist(User user);
	
	boolean isTradeExist(Trade trade);

		
	void saveTrade(Trade trade);

	void deleteUserById(User user);

	void deleteTrade(Trade trade);

	Trade findTradeById(Long id);

	void deleteAllTrade();

	List<Trade> findAllByUser(User user);

	List<Trade> findAllTradeDentreDatas(String stockSymbol, String type, Timestamp startDateTs, Timestamp endDateTs);

	List<Trade> findPriceTradeDentreDatas(String stockSymbol, Timestamp startDateTs, Timestamp endDateTs);
}
