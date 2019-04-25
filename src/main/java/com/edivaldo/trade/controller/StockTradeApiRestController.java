package com.edivaldo.trade.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;
import com.edivaldo.trade.service.ServiceImp;
import com.edivaldo.trade.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class StockTradeApiRestController {

	@Autowired
	ServiceImp serviceImp;

	public static final Logger logger = LoggerFactory.getLogger(StockTradeApiRestController.class);
	// -------------------Retrieve All
	// Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = serviceImp.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (serviceImp.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		serviceImp.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// -------------------delete User-------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = serviceImp.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		serviceImp.deleteUserById(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	// -------------------delete all user-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUser() {
		logger.info("Deleting All user");

		serviceImp.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// -------------------Retrieve All
	// trade---------------------------------------------

	@RequestMapping(value = "/trade/", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> listAllTrade() {
		List<Trade> traders = serviceImp.findAllTrade();
		if (traders.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Trade>>(traders, HttpStatus.OK);
	}

	// -------------------Create a trade-------------------------------------------

	@RequestMapping(value = "/trade/", method = RequestMethod.POST)
	public ResponseEntity<?> createTrader(@RequestBody Trade trade, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Trade : {}", trade);
		System.out.println("-----getTradeTimestamp-------> "+trade.getTradeTimestamp());
		if (serviceImp.isTradeExist(trade)) {
			logger.error("Unable to create. A Operação {} already exist", trade.getId());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A trade " + trade.getStockPrice() + " already exist."),
					HttpStatus.BAD_REQUEST);
		}
		serviceImp.saveTrade(trade);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/trade/{id}").buildAndExpand(trade.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// -------------------delete um trade-------------------------------------------

	@RequestMapping(value = "/trade/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrade(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		Trade trade = serviceImp.findTradeById(id);
		if (trade == null) {
			logger.error("Unable to delete. trade with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. trade with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		serviceImp.deleteTrade(trade);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	// -------------------delete all
	// trade-------------------------------------------

	@RequestMapping(value = "/trade/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllTrade() {
		logger.info("Deleting All Traders");

		serviceImp.deleteAllTrade();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// -------------------get all traders- by
	// id------------------------------------------

	@RequestMapping(value = "/trade/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getAllTrader(@PathVariable("userId") long userId) {
		logger.info("Fetching & get All Trader with userId {}", userId);

		User user = serviceImp.findById(userId);
		if (user == null) {
			logger.error("get All Trader with userId {} not found.", userId);
			return new ResponseEntity(new CustomErrorType("Unable to delete. trade with id " + userId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		List<Trade> traders = serviceImp.findAllByUser(user);
		return new ResponseEntity<List<Trade>>(traders, HttpStatus.OK);
	}

	// -------------------get all traders- by
	// id------------------------------------------
	// @RequestMapping(value =
	// "/stocks/{stockSymbol}/trade?type={tradeType}&start={startDate}&end={endDate}",
	// method = RequestMethod.GET)

	@RequestMapping(value = "/stocks/{stockSymbol}/trade", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getFindAllTraderBySymbolByTypeAndDatePeriod(
			@PathVariable("stockSymbol") String stockSymbol, @RequestParam String type, @RequestParam String start,
			@RequestParam String end) {

		logger.info("stockSymbol {}", stockSymbol);
		logger.info("tradeType {}", type);
		logger.info("startDate {}", start);
		logger.info("endDate {}", end);

	
		try {
			SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date startDate = datetimeFormatter.parse(start);
			System.out.println("startDate :" + startDate);
			Timestamp startDateTs = new Timestamp(startDate.getTime());
			System.out.println("startDateTs :" + startDateTs);
			
			Date endDate = datetimeFormatter.parse(end);
			System.out.println("endDate :" + endDate);
			Timestamp endDateTs = new Timestamp(endDate.getTime());
			System.out.println("endDate :" + endDate);
			
			List<Trade> traders = serviceImp.findAllTradeDentreDatas(stockSymbol, type, startDateTs, endDateTs);
			return new ResponseEntity<List<Trade>>(traders, HttpStatus.OK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		/*
		 * User user = serviceImp.findById(userId); if (user == null) {
		 * logger.error("get All Trader with userId {} not found.", userId); return new
		 * ResponseEntity(new CustomErrorType("Unable to delete. trade with id " +
		 * userId + " not found."), HttpStatus.NOT_FOUND); }
		 */
		return new ResponseEntity(new CustomErrorType(" not found."), HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/stocks/{stockSymbol}/price", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getFindPriceTraderBySymbolByDatePeriod(
			@PathVariable("stockSymbol") String stockSymbol, 
			@RequestParam String start,
			@RequestParam String end) {

		logger.info("stockSymbol {}", stockSymbol);
		logger.info("startDate {}", start);
		logger.info("endDate {}", end);

	
		try {
			SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date startDate = datetimeFormatter.parse(start);
			System.out.println("startDate :" + startDate);
			Timestamp startDateTs = new Timestamp(startDate.getTime());
			System.out.println("startDateTs :" + startDateTs);
			
			Date endDate = datetimeFormatter.parse(end);
			System.out.println("endDate :" + endDate);
			Timestamp endDateTs = new Timestamp(endDate.getTime());
			System.out.println("endDate :" + endDate);
			
			List<Trade> traders = serviceImp.findPriceTradeDentreDatas(stockSymbol, startDateTs, endDateTs);
			
			return new ResponseEntity<List<Trade>>(traders, HttpStatus.OK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		/*
		 * User user = serviceImp.findById(userId); if (user == null) {
		 * logger.error("get All Trader with userId {} not found.", userId); return new
		 * ResponseEntity(new CustomErrorType("Unable to delete. trade with id " +
		 * userId + " not found."), HttpStatus.NOT_FOUND); }
		 */
		return new ResponseEntity(new CustomErrorType(" not found."), HttpStatus.NOT_FOUND);
	}
}
