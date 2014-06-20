package org.bsanalytics.hive;

/*import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.Text;

public class prediction extends UDAF{
	
	static File file = new File("/home/hduser/hexample/result.txt");
	static BufferedWriter buff=null;
	static Text textout=new Text();
	static DataOutput out = null;
	static PrintWriter pw= null;
	
	
	
	public static class MeanDoubleUDAFEvaluator implements UDAFEvaluator {
		
		public static class PartialResult {
		double sum;
		long count;
		}
		private PartialResult partial;			
		
		
		public void init() {
			partial = null;
			
			FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile());
				buff = new BufferedWriter(fw);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			}
		
		public boolean iterate(DoubleWritable value) {
		
			if (value == null) {
				return true;
			}
			
		if (partial == null) {
		
			partial = new PartialResult();
		
		}
			
			partial.sum += value.get();			
			partial.count++;
			
			double avg = value.get()/partial.count;
			
			pw.println(avg+"");
			
			try {
				buff.write(avg + " ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(value.get()/partial.count);
			new DoubleWritable(avg);	
			return true;			
		
		}
		
		public PartialResult terminatePartial() {
			return partial;
		}
			
		public boolean merge(PartialResult other) {
			
		if (other == null) {
			return true;
		}
			
		if (partial == null) {
			partial = new PartialResult();			
		}
			
		partial.sum += other.sum;
		partial.count += other.count;
		
		
		//partial result calculations
		System.out.println(other.sum/other.count);
		return true;
		}
			
		public DoubleWritable terminate() {
		
			if (partial == null) {
			return null;
			}
			pw.close();
				return new DoubleWritable(partial.sum / partial.count);			
		}			
	}			
}
			
*/

public class prediction{
}