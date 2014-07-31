#!/usr/bin/perl

	my $sum=0;
	my $count=0;
	my $average=0;
	my $temp_start_date = '2005-04-01';
	my $before_split_start = '2005-04-01';
	@temp_dates_array = split(/-/, $temp_start_date);
	$temp_date = $temp_dates_array[2]+31*$temp_dates_array[1]+365*$temp_dates_array[0];
	my $flag=0;
	my $before_split_current;

	while ($input = <STDIN>) {


		@columns = split(/\t/, $input);
                #print "Field values are: @fields\n";

		my $date = $columns[0];		
		my $score = $columns[1];
		chomp($date); chomp($score);

		#current date array

		@current_date_array = split(/-/, $date);
		$db_date = $current_date_array[2]+31*$current_date_array[1]+365*$current_date_array[0];


		if ( $temp_date == $db_date)
		{
			$sum += $score;
			$count++;
		}
		else
		{
		 if ($count != 0)
		 {	
#			print "count is not zero";				
			$average = $sum/$count;
			if ($flag == 1)
			{	
				print "$before_split_current,$average";
				print "\n";
			}
			else
			{
				print "$before_split_start,$average";
				print "\n";
			}


		 }
#			print "coming here";
#			print $db_date;
#			print "\n";
#			print $current_date_array;
#			print "\n";
			
			#$temp_start_date = $current_date_array;
			$before_split_current = $date;
			$temp_date = $current_date_array[2]+31*$current_date_array[1]+365*$current_date_array[0];
			$flag=1;
#			print $temp_date;
			
			$count=0;
			$sum=0;
			$average=0;

			if ($temp_date == $db_date)
			{
#				print "first time score";
				$sum += $score;
				$count++;
			}

			
		}
}
