package com.hackerrank.stocktrade;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
	public static void main(String[] args) {
		ObjectToJson obj = new ObjectToJson();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		Trade trade = createDummyObject();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("C:\\dev\\workspaces\\TraderCrud\\src\\test\\java\\com\\hackerrank\\stocktrade\\OBJECT-JAVA-TO-JSON.json"), trade);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(trade);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(trade);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Trade createDummyObject() {
/*
		private Long id;
		private String type;
		private User user;
		private String stockSymbol;
		private Integer stockQuantity;
		private Float stockPrice;
		private Timestamp tradeTimestamp;
	*/	
		Trade trade = null;
		String dataN = "2019-04-24 10:45:11";
		String dataN2 = "04-24-2019 10:45:11";
		try {
			SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = datetimeFormatter.parse(dataN);
			System.out.println("startDate :" + startDate);
			Timestamp startDateTs = new Timestamp(startDate.getTime());
			System.out.println("startDateTs :" + startDateTs);
	
		
			SimpleDateFormat datetimeFormatter2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			datetimeFormatter2.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date startDate2 = datetimeFormatter2.parse(dataN2);
			
			
			System.out.println("startDate2 :" + startDate2);
			Timestamp startDateTs2 = new Timestamp(startDate2.getTime());
			System.out.println("startDateTs2 :" + startDateTs2.toGMTString());
			
		//	LocalDateTime localDateTime = LocalDateTime.now();
		//	System.out.println(localDateTime);
		//	Timestamp timestamp = Timestamp.valueOf(localDateTime);
			
			trade = new Trade(1L, "compra", new User(1L, "maria"), "C", 30, new Float(15.35), startDateTs2);
			
		//	System.out.println(timestamp.toLocalDateTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	


		
		
		
		/*
		 * staff.setName("mkyong"); staff.setAge(33); staff.setPosition("Developer");
		 * staff.setSalary(new BigDecimal("7500"));
		 * 
		 * List<String> skills = new ArrayList<>(); skills.add("java");
		 * skills.add("python");
		 * 
		 * staff.setSkills(skills);
		 */

		return trade;

	}
}