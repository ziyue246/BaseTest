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
		
		System.out.println("开始时间 :"+startDate.toLocaleString().toString());
		System.out.println("结束时间 :"+endDate.toLocaleString().toString());
		System.out.println("总天数     :"+betweenDays);
		
		System.out.println("现在时间  :"+nowDate.toLocaleString());
		long remDays = (long)((endDate.getTime() - 
				nowDate.getTime()) / (1000 * 60 * 60 *24) + 0.5); 
		
		System.out.println("工作天数  :"+(betweenDays-remDays));
		
		System.out.println("剩余天数   :"+remDays);
		System.out.println("剩余周数   :"+remDays/7.0);
		System.out.println("剩余月数   :"+remDays/30.0);
		
		System.out.println("剩余年数   :"+remDays/365.0);
		System.out.println("剩余比例   :"+1.0*remDays/betweenDays);
	}

}
