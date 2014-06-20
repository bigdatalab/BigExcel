package org.bsanalytics.client.analysis;

import java.util.List;



/**
 * @author asif
 * This class is responsible for calculating Extrapolation
 *
 */
public class ExtrapoationbasedPrediction {
	
	
	public double calculateExtrapolation(List<List<Object>> list){
		
		int len=0;
		double sum=0.0;
		int list_count=0;
		int double_count=0;
		int double_list_size = list.size();
		double_count = 0;
		
		
		for (List li : list){
			
			double_count++;
			
			len = li.size();
			String temp = (String) li.get(1);
			
			sum = sum + Double.parseDouble(temp);
			list_count++;
			
			//leaving last element
			if (double_list_size-1 == double_count)
				break;
		}
		
		return sum/list_count;
		
	}

}
