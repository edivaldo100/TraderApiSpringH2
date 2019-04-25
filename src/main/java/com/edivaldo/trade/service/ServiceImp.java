package com.edivaldo.trade.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;
import com.edivaldo.trade.repository.TradeRepository;
import com.edivaldo.trade.repository.UserRepository;

@Service("serviceImp")
public class ServiceImp implements ServiceTrader {
	private static final AtomicLong counter = new AtomicLong();
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public List<Trade> findAllTrade() {
		return (List<Trade>) tradeRepository.findAll();
	}

	@Override
	public User findByName(String name) {
		for (User user : findAllUsers()) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Trade findTradeById(Long id) {

		Optional<Trade> findById = tradeRepository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}

		return null;
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	@Override
	public boolean isTradeExist(Trade trade) {

		Trade findTradeById = findTradeById(trade.getId());
		if (findTradeById != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User findById(long id) {
		Optional<User> findById = userRepository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

	@Override
	public void saveTrade(Trade trade) {
		tradeRepository.save(trade);
	}

	@Override
	public void deleteTrade(Trade trade) {
		tradeRepository.delete(trade);
	}

	@Override
	public void deleteAllTrade() {
		tradeRepository.deleteAll();

	}

	@Override
	public List<Trade> findAllByUser(User user) {
		// TODO Auto-generated method stub
		return tradeRepository.findAllByUser(user);
	}

	@Override
	public List<Trade> findAllTradeDentreDatas(String stockSymbol, String type, Timestamp startDateTs,
			Timestamp endDateTs) {
		// TODO Auto-generated method stub
		return tradeRepository.getAllBetweenDates(stockSymbol, type, startDateTs, endDateTs);
	}

	@Override
	public List<Trade> findPriceTradeDentreDatas(String stockSymbol, Timestamp startDateTs, Timestamp endDateTs) {

		List<Trade> priceBetweenDates = tradeRepository.getPriceBetweenDates(stockSymbol, startDateTs, endDateTs);
		Comparator<Trade> comparator = Comparator.comparing(Trade::getStockPrice);

		// Get Min or Max Object
		Trade minObject = priceBetweenDates.stream().min(comparator).get();
		Trade maxObject = priceBetweenDates.stream().max(comparator).get();

		return Arrays.asList(minObject, maxObject);
	}

}
