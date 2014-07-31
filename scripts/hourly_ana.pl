#!/usr/bin/perl

	my $sum=0;
	my $cond;

	#use Time::Piece;
	#use Time::Seconds;
        #use Time::Clock;

	#global variable
	my $temp_start_date = '2005-04-01';
       	my $format = '%Y-%m-%d';
	my $temp_original_date = '2005-04-01';
	@temp_dates_array = split(/-/, $temp_start_date);
	$temp_date = $temp_dates_array[2]+31*$temp_dates_array[1]+365*$temp_dates_array[0];
	#my $tempdate = Time::Piece->strptime($temp_start_date, $format);
	my $temp_hour = 0;
        my $interval_sum = 0.0;
        my $interval_average = 0.0;
	my $count=0;
        #This flag is for the case where there is only a one hour
	my $flag=0;
	my $date_comparison_flag=0;
	my $current_hour_changed_date=0;
   

	
	while ($input = <STDIN>) {

	@columns = split(/\t/, $input);
                #print "Field values are: @fields\n";

	my $date = $columns[0];
	my $time = $columns[1];
	my $score = $columns[2];
	chomp($date); chomp($time); chomp($score);

#	print "$date : ";
#	print "$time\n";	#$time	$score\n";

#	print "Printing input time : ";
#	print "$time\n";

=for
	print "Printing input score : ";
	print "$score\n";
	print "Printing temp date : ";
	print "$temp_date\n";
=cut

	#handling the date
#	my $dt1 = Time::Piece->strptime($date, $format);

	#handling the time
	#my $current_time = Time::Clock->new($time); 
	@current_time = split(/:/,$time);
        my $current_hour = $current_time[0] + 0;
#	print "Current Hour : $current_hour \n";


        # date filteration for one day	
        # here we will do all the necessray calculations
	
	@current_date_array = split(/-/, $date);
	$db_date = $current_date_array[2]+31*$current_date_array[1]+365*$current_date_array[0];
	
#	print "Printing input date after conversion : ";
#	print "$db_date\n";

	if ($db_date == $temp_date)
	{
#		print "yes date compared\n";
		$date_comparison_flag=1;
	
                if ($current_hour == $temp_hour)
		{
#				print "yes hour compared\n";
#				my $current_minute = Time::Clock->new($time); 				
				my $current_minute = $current_time[1] + 0;
				if ($current_minute <= 60)
				{
					$interval_sum += $score;
					$count++;
#					print "Total count so far first: $count \n";
					$flag=1;
				}
		}
		else
		{
		  if ($temp_hour != 0 )
		  {
			$interval_average = $interval_sum/$count;
			#print "average : ";
			my $temporary_current_hour = $current_hour-1;
			if ($temporary_current_hour == 0)
			{
				$temporary_current_hour++;
			}	
			print "$date, $temporary_current_hour:00:00, $interval_average\n";
			$temporary_current_hour=0;
			$count=0;
			$interval_sum=0;
			$flag=1;

			#for handling the changed hour score
#			print "For handling the changed hour score\n";
#			my $current_minute_for_changed_hour = Time::Clock->new($time); 				
			my $current_minute_for_changed_hour = $current_time[1] + 0;
				if ($current_minute_for_changed_hour <= 60)
				{
					$interval_sum += $score;
					$count++;
#					print "Total count so far second: $count \n";
				}
#				my $current_hour_changed = Time::Clock->new($time); 
				my $current_hour_changed = $current_time[0] + 0;
				$current_hour_changed_date = $current_hour_changed; #displaying the last hour at output
				$temp_hour = $current_hour_changed;

			
		  }
			else #first time hour check
			{
#                                print "First Time hour setting \n";
#                                my $current_minute = Time::Clock->new($time); 	
				my $current_minute = $current_time[1] + 0;
				if ($current_minute <= 60)
				{
					$interval_sum += $score;
					$count++;
#					print "Total count so far first-time: $count \n";
					$flag=1;
				}
#				my $current_hour_inside = Time::Clock->new($time); 
				my $current_hour_inside = $current_time[0] + 0;
#				$temp_hour = $current_hour_inside->hour;
                                $current_hour_changed_date = $current_hour_inside;
				$temp_hour = $current_hour_inside;
			}

		}
		
	}
	else
	{
	   if ($date_comparison_flag ==  1)
	   {
		$interval_average = $interval_sum/$count;
#		print "average : ";
		my $temporary_current_hour = $current_hour;
		if ($current_hour_changed_date == 0)
			{
				$current_hour_changed_date++;
			}	
		print "$temp_original_date, $current_hour_changed_date:00:00, $interval_average\n";
		$current_hour_changed_date=0;
	   }	
#		print "Assigining new date \n";
		$temp_date = $db_date;
		$temp_original_date = $date;
		$interval_sum=0;
		$count=0;
		$flag=1;
	
		my $current_minute_date_changed = $current_time[1] + 0;
			if ($current_minute_date_changed <= 60)
			{
				$interval_sum += $score;
				$count++;
#				print "Total count so far date changed: $count \n";
			}
			$current_hour_changed_date = $current_time[0] + 0;
			$temp_hour = $current_hour_changed_date;	 		
	}

		

   }#end of while loop

		#for handling one hour only (case where hour will never change)
		if ($flag == 1)
		{
			$interval_average = $interval_sum/$count;
#			print "average : ";
			#print $current_hour;
			if ($current_hour_changed_date == 0)
			{
				$current_hour_changed_date++;
			}
			print "$temp_original_date, $current_hour_changed_date:00:00, $interval_average\n";
		}


