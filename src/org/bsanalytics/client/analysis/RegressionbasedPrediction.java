package org.bsanalytics.client.analysis;

import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.general.client.ClientSideGsonConversion;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;



/**
 * @author asif
 *This class is responsible for calculating Regression line
 */
public class RegressionbasedPrediction {
	
	
public double calculateRegressionbasedPrediction(List<List<Object>> list){
		
		int len=0;
		double sum=0.0;
		int list_count=0;
		double date_sum=0;
		List<Double> values_list = new ArrayList<>();
		List<Integer> dates_list = new ArrayList<>();
		int double_count=0;
		int double_list_size = list.size();
		
		for (List li : list){
			
			len = li.size();
			int total_days = getNumberofDays((String) li.get(0));
			System.out.println(total_days);
			dates_list.add(total_days);
			date_sum = date_sum + total_days;
			
			String temp = (String) li.get(1);
			double tmp = Double.parseDouble(temp);
			values_list.add(tmp);
			sum = sum + tmp;
			list_count++;
			
			//leaving last element
			if (double_list_size-1 == double_count)
				break;
			
		}
		
		double y_bar = sum/list_count;
		double x_bar = date_sum/list_count; 
		
		double y = calculateRegressionLine(x_bar,y_bar,dates_list,values_list);
		
		System.out.println("Mean Value =" + y_bar);
		System.out.println("Mean Value =" + x_bar);
		return y; //regression line
		
	}

	  public double calculateRegressionLine(double x_bar, double y_bar, List<Integer> dates_list, 
			  List<Double> values_list){
		
		  //regression line
		  // y = a+bx
		  // where b = E(x - x_bar)(y - y_bar)/E(x - x_bar)*(x - x_bar);
		  // a = y_bar - b * x_bar;
		  
		  
		  //calculating x-x_bar. where x is the dates_list
		  List<Double> x_minus_xbar = new ArrayList<>();
		  List<Double> x_minus_xbar_square = new ArrayList<>();
		  
		  //for calculating b
		  double x_minus_xbar_square_sum=0.0;
		  
		  for (int dates : dates_list){
			
			  double tmp = dates-x_bar;
			  double tmp_square = tmp * tmp;
			  x_minus_xbar_square_sum = x_minus_xbar_square_sum + tmp_square;
			  x_minus_xbar.add(tmp);
			  x_minus_xbar_square.add(tmp_square);
		  }
		  
		  List<Double> y_minus_ybar = new ArrayList<>();
		  for (double values : values_list){
				
			  y_minus_ybar.add(values-y_bar);
		  }
		  
		  int len = x_minus_xbar.size();
		  
		  //for calculating b
		  double sum_x_minus_x_bar_multiply_y_minus_y_bar=0.0;
		  
		  List<Double> x_minus_xbar_MULT_y_minus_ybar = new ArrayList<>();
		  for (int i=0; i<len; i++){
			  double tmp = x_minus_xbar.get(i)*y_minus_ybar.get(i);
			  sum_x_minus_x_bar_multiply_y_minus_y_bar = 
					  sum_x_minus_x_bar_multiply_y_minus_y_bar + tmp;
			  x_minus_xbar_MULT_y_minus_ybar.add(tmp);
		  }
		  
		  double b = sum_x_minus_x_bar_multiply_y_minus_y_bar/x_minus_xbar_square_sum;
		  
		  System.out.println("Value of b= " + b);
		  
		  //Now calculating value of a
		  
		  double a = y_bar - b * x_bar;
		  
		  System.out.println("Value of a= " + a);
		  
		  //getting number of days for predicting value
		  int x = getNumberofDays("2005-06-26");
		  
		  double y = a + b * x;
		  
		  System.out.println("Predicted: Value of Y= " + y);
		  return y;
	  
	  }
	  
	  
	  
	
      public int getNumberofDays(String date){
    	  
    	  System.out.println(date);
    	  String date_split[] = date.split("-");
    	  int year = Integer.parseInt(date_split[0]);
    	  int month = Integer.parseInt(date_split[1]);
    	  int day = Integer.parseInt(date_split[2]);
    	  
    	  DateTime start = new DateTime(1900, 1, 1, 0, 0, 0, 0);
    	  DateTime end = new DateTime(year, month, day, 0, 0, 0, 0);

    	  //calculate whole days between two dates
    	  Days days = Days.daysBetween(start, end);
    	  
    	  //System.out.println(days.getDays()+2);
    	  return days.getDays()+2;
      }
      
      
      
      public static void main(String args[]){
    	  
    	  
    	  //String json = "[[\"2005-05-23\",\" 10.3742886731065\"],[\"2005-05-24\",\" 8.46807959722938\"],[\"2005-05-25\",\" 10.0826387551814\"]]";
    	  String json = "[[\"2005-06-24\",\" 12.3\"],[\"2005-06-25\",\" 8.65\"]]";
    	  ClientSideGsonConversion cGson = new ClientSideGsonConversion();
			System.out.println(json);
			List<List<Object>> table_rows_list = new ArrayList<>();
									
			cGson.setListForConversion(json);
			table_rows_list = cGson.getConvertedList();
			
			new RegressionbasedPrediction().calculateRegressionbasedPrediction(table_rows_list);	
    	  
      }

}
