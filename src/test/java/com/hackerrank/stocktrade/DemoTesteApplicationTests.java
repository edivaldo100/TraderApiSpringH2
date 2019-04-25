package com.hackerrank.stocktrade;

import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.springframework.web.client.RestTemplate;

import com.edivaldo.trade.model.Trade;
import com.edivaldo.trade.model.User;



public class DemoTesteApplicationTests {

	public static final String REST_SERVICE_URI = "http://localhost:8000/api";
    
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
         
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET */
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
       // User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
       // System.out.println(user);
    }
     
    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
          user.setName("Super User "+new Random().nextInt(1100));
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    
    /* POST */
    private static void createTrader(Long id, String dataN) {
        System.out.println("Testing create Trader API----------"+id+" -- "+dataN);
        RestTemplate restTemplate = new RestTemplate();

        
		Trade trade = null;
		//String dataN = "24-04-2019 10:45:11";
		try {
			//MM-dd-yyyy HH:mm:ss"
			
			//uuuu-MM-dd HH:mm:ss.SSS
			// new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date startDate = datetimeFormatter.parse(dataN);
			System.out.println("startDate :" + startDate);
			Timestamp startDateTs = new Timestamp(startDate.getTime());
			System.out.println("startDateTs :" + startDateTs);
	
	
			User user = new User();
			user.setId(1L);
			trade = new Trade(id, "venda", user, "V", 30, new Float(id+15.35), startDateTs);
			
			//System.out.println(startDateTs.toLocalDateTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/trade/", trade, Trade.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
    
    
    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
       // User user  = new User(1,"Tomy",33, 70000);
        //restTemplate.put(REST_SERVICE_URI+"/user/1", user);
       // System.out.println(user);
    }
 
    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
 
 
    /* DELETE */
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }
 
    
    
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllTrade(){
        System.out.println("Testing listAllTrade API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/trade/", List.class);
         
     
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("Trade : id="+map.get("id")+", type="+map.get("type")+", TradeTimestamp="+map.get("tradeTimestamp"));;
            }
        }else{
            System.out.println("No Trade exist----------");
        }
    }
    
    public static void main(String args[]){
    	createUser();
    	listAllUsers();
    	
    	String dataN = "24-04-2019 10:45:11";
    	for (int i = 1; i < 40; i++) {
    		
    		String h = "";
    		String m = "";
    		if(i<10)h = "0"+i;else h=i+"";
    		
    		if((i*2)<10)m = "0"+(i*2);else m=(i*2)+"";
    		
    		String d = "2019-04-24 "+h+":"+m+":21";
    		//"2019-04-24 10:45:11"
    		//createTrader(new Long(new Random().nextInt(1100)),d);
    		createTrader(new Long(i),d);
		}
		/*
		 * getUser();  listAllUsers(); updateUser(); listAllUsers();
		 * deleteUser(); listAllUsers(); deleteAllUsers(); listAllUsers();
		 */
    }
}


