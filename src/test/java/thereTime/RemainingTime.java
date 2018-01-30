package thereTime;

import java.util.Calendar;
import java.util.Date;

public class RemainingTime {
	
	
	public static void main(String []args){
		Calendar cal = Calendar.getInstance();
		Date nowDate = cal.getTime();
		cal.set(2017, 3, 12);
		Date startDate =cal.getTime();
		cal.set(2020, 3, 12);
		Date endDate = cal.getTime();
		
	
		long betweenDays = (long)((endDate.getTime() - 
				startDate.getTime()) / (1000 * 60 * 60 *24) + 0.5); 
		
		System.out.println("start time :"+startDate.toLocaleString().toString());
		System.out.println("end time   :"+endDate.toLocaleString().toString());
		System.out.println("all time   :"+betweenDays);
		
		System.out.println("now time   :"+nowDate.toLocaleString());
		long remDays = (long)((endDate.getTime() - 
				nowDate.getTime()) / (1000 * 60 * 60 *24) + 0.5); 
		
		System.out.println("work day   :"+(betweenDays-remDays));
		
		System.out.println("rem days   :"+remDays);
		System.out.println("rem weeks  :"+remDays/7.0);
		System.out.println("rem months :"+remDays/30.0);
		
		System.out.println("rem years  :"+remDays/365.0);
		System.out.println("rem rate   :"+1.0*remDays/betweenDays);
	}

}
