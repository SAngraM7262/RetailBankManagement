package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

	      System.out.println("Current Date: " + ft.format(dNow));


	}

}
