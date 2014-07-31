#!/usr/bin/perl


	my $word1;
	my $word2;
	my $word3;
	my $word4;
	my $word5;
	my $frequency;
	my $number1;
	my $left_etro;
	my $right_etro;
	my $number2;
	my $sum=0;
	my $max=0;
	
	while($input = <STDIN>)
	{

		@row = split(/\t/,$input);
		$word1 = lc($row[0]);
		$word2 = lc($row[1]);
		$word3 = lc($row[2]);
		$word4 = lc($row[3]);
		$word5 = lc($row[4]);
		$word1 =~ s/^\s+|\s+$//g;
		$word2 =~ s/^\s+|\s+$//g;
		$word3 =~ s/^\s+|\s+$//g;
		$word4 =~ s/^\s+|\s+$//g;
		$word5 =~ s/^\s+|\s+$//g;
		$frequency = $row[5];
		$number1 = $row[6];
		$left_etro = $row[7];
		$right_etro = $row[8];
		$number2 = $row[9];

	
#		if (	

=comments
			index($word1,'france') != -1 || 
			index($word1,'italy beat france') != -1 || 
			index($word1,'world cup') != -1 || 			
			index($word1,'football') != -1 || 			
			index($word1,'Football') != -1 ||
			index($word1,'fifa') != -1 || 			
			index($word1,'Fifa') != -1 ||
			index($word2,'france') != -1 || 
		 	index($word2,'France') != -1 || 
			index($word2,'italy') != -1 || 
			index($word2,'Itay') != -1 || 
			index($word2,'World Cup') != -1 || 
			index($word2,'world cup') != -1 || 
			index($word2,'World cup') != -1 || 
			index($word2,'world Cup') != -1 || 			
			index($word2,'football') != -1 || 			
			index($word2,'Football') != -1 ||
			index($word2,'fifa') != -1 || 			
			index($word2,'Fifa') != -1 ||
			index($word3,'france') != -1 || 
		 	index($word3,'France') != -1 || 
			index($word3,'italy') != -1 || 
			index($word3,'Itay') != -1 || 
			index($word3,'World Cup') != -1 || 
			index($word3,'world cup') != -1 || 
			index($word3,'World cup') != -1 || 
			index($word3,'world Cup') != -1 || 			
			index($word3,'football') != -1 || 			
			index($word3,'Football') != -1 ||
			index($word3,'fifa') != -1 || 			
			index($word3,'Fifa') != -1 ||
			index($word4,'france') != -1 || 
		 	index($word4,'France') != -1 || 
			index($word4,'italy') != -1 || 
			index($word4,'Itay') != -1 || 
			index($word4,'World Cup') != -1 || 
			index($word4,'world cup') != -1 || 
			index($word4,'World cup') != -1 || 
			index($word4,'world Cup') != -1 || 			
			index($word4,'football') != -1 || 			
			index($word4,'Football') != -1 ||
			index($word4,'fifa') != -1 || 			
			index($word4,'Fifa') != -1 ||
			index($word5,'france') != -1 || 
		 	index($word5,'France') != -1 || 
			index($word5,'italy') != -1 || 
			index($word5,'Itay') != -1 || 
			index($word5,'World Cup') != -1 || 
			index($word5,'world cup') != -1 || 
			index($word5,'World cup') != -1 || 
			index($word5,'world Cup') != -1 || 			
			index($word5,'football') != -1 || 			
			index($word5,'Football') != -1 ||
			index($word5,'fifa') != -1 || 			
			index($word5,'Fifa') != -1

			index($whole_string,'italy beat france') != -1 ||
			index($whole_string,'france loose world cup') != -1 ||
			index($whole_string,'italy wins world cup') != -1 ||
			index($whole_string,'france loose from italy')
=cut
		my $whole_string = $word1 . " " . $word2. " " . $word3. " " . $word4. " " . $word5 ;
#		print $whole_string;
#		print "\n";

		

		if (
			index($whole_string,'h5n1') != -1			
			
		   )			

		{
				#print "Yes it found \n";
				print "$word1 , $word2, $word3, $word4, $word5, $frequency, $number1, $left_etro, $right_etro, $number2\n";
			
		}

	}#end of while statement

		
