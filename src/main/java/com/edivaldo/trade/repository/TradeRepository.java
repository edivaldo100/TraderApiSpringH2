package com.edivaldo.trade.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {
	
	List<Trade> findAllByUser(User user);
	
	
	//@Query("SELECT HC "
	//		+ " FROM TbWebHitControl HC "
	//		+ " WHERE HC.idPonto = ?1 ")
	//List<Trade> findAllTraderBySymbolByTypeAndDatePeriod(User user);
	//Timestamp tradeTimestamp
	@Query(value = "from Trade t where t.stockSymbol = :stockSymbol AND t.type = :type AND t.tradeTimestamp BETWEEN :startDate AND :endDate")
	public List<Trade> getAllBetweenDates(String stockSymbol, String type, Timestamp startDate,Timestamp endDate);

	@Query(value = "from Trade t where t.stockSymbol = :stockSymbol AND t.tradeTimestamp BETWEEN :startDate AND :endDate")
	public List<Trade> getPriceBetweenDates(String stockSymbol, Timestamp startDate,Timestamp endDate);
}