package com.orderbid.beans;

import java.util.Date;

import org.hibernate.Session;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
   Vap_Registration vap=new Vap_Registration();
   vap.setVap_Registrationid(1);
   vap.setEmail("prakashpandey032@gmail.com");
   
		
		
/*		Stock stock = new Stock();
        stock.setStockCode("7053");
        stock.setStockName("prakashs");
        session.save(stock);
        
        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());
        
        stockDailyRecords.setStock(stock);        
        stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);
        
*/        
         session.save(vap);     
        System.out.println("Finally Insert");
        
        
        System.out.println("Inserted Detaisls Successfully");
        
        
        
        

		session.getTransaction().commit();
		System.out.println("Done");
	}
}
