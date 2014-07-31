package org.bsanalytics.apis.queryconstructor;

public class QueryConstructor {
	
	public String hourlyAnalysis(String query){
		
		
		System.out.println(query);
		String split[] = query.split("@");
		System.out.println(split.length);
		String table_name = split[0];
		System.out.println(table_name);
		
		String where_clasue = split[1];
		String where_split[] = where_clasue.split(",");
		
		String dates = split[2];
		String date_split[] = dates.split(",");
		
				
		String executing_query = "create table csv_dump ROW FORMAT DELIMITED FIELDS "
				+ "TERMINATED BY ',' LINES TERMINATED BY '\n' as "
				+ "select transform(date,time,buzz_scores) using 'test_perl.pl' as log1, log2, log3 "
				+ "from "+ query +" where product_type='EBOOKS' and date in"
				+  "('2005-05-23')";
				//+ "('2005-05-23','2005-05-24','2005-05-25''2005-05-26','2005-05-27')";
		
		
		int length_where = where_split.length;
		String temp_string = new String();
		for (int i=0; i<length_where ; i++){
			if (i != length_where-1)
			{
				temp_string = temp_string + where_split[i] + " AND ";
			}
			else
			{
				temp_string = temp_string + where_split[i];
			}
		}
		
		
		
		String date_string = new String();
		
			date_string = date_split[0]+">="+"'"+date_split[1]+"'"+" AND "
			+date_split[0]+"<="+"'"+date_split[2]+"'";
			
		
		
		String executing_query1 = "create table csv_dump ROW FORMAT DELIMITED FIELDS"
				+ " TERMINATED BY ',' LINES TERMINATED BY '\n' as select transform(date,buzz_scores)"
				+ " using 'daily_analysis.pl' as log1,log2 from "+ table_name + " WHERE " 
				+ temp_string + " AND " + date_string;
		
		System.out.println(executing_query1);
		
		return executing_query1;
	}
	
	
	public static void main(String args[]){
		String query = "yahoo_buzz@product_type='EBOOKS',score3=56.21@date,20050624,20050727";
		String split_abc[] = query.split(",");		System.out.println(split_abc[0]);
		new QueryConstructor().hourlyAnalysis(query);	
		
	}
	
	
	
}
