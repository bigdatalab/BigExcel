package org.bsanalytics.hadoop;

import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileStatus;


public class AccessHadoopFileSystem {

	
	 public void LoadFilesToHDFS (final String file_name) {

		 System.out.println("===Creating HDFS Files====");
	        try {
	            UserGroupInformation ugi
	                = UserGroupInformation.getLoginUser();

	            ugi.doAs(new PrivilegedExceptionAction<Void>() {

	                public Void run() throws Exception {

	                    Configuration conf = new Configuration();
	                    conf.set("fs.defaultFS", "hdfs://localhost:9000/user/hduser");
	                    conf.set("hadoop.job.ugi", "hduser");

	                    FileSystem fs = FileSystem.get(conf);

	                    fs.createNewFile(new Path("/user/hduser/"+file_name));

	                    FileStatus[] status = fs.listStatus(new Path("/user/hduser"));
	                    for(int i=0;i<status.length;i++){
	                        System.out.println(status[i].getPath());
	                    }
	                    return null;
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	 public static void main(String args[]){
		 
		 new AccessHadoopFileSystem().LoadFilesToHDFS("abc");
	 }
}
