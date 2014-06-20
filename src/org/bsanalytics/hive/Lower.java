package org.bsanalytics.hive;

/*import java.io.DataOutput;
import java.io.IOException;
*/

public class Lower{

}

/*import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public final class Lower extends UDF {
	DataOutput output = null;
	  public Text evaluate(final Text s) {
	    if (s == null) { return null; }
	    //Text word = new Text();
	    try {
			Text.writeString(output, "Hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new Text(s.toString().toLowerCase());
	  }
	}
*/