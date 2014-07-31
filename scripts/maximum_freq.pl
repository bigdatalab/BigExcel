#!/usr/bin/perl

	my $maximum_freq=0;
	my $freq = 0;
	while ($input = <STDIN>)
	{

		@row = split(/\t/,$input);
		$freq = $row[5];

		if ($freq >= $maximum_freq && $freq<10000000)
		{
			$maximum_freq=$freq;
		}


	}
		print "Maximum Frequency: $maximum_freq \n";
