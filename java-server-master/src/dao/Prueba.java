package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Prueba {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String x = dateFormat.format(date);
	}
}
