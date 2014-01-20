package org.bsanalytics.hadoop;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.fs.*;


public class AccessHadoopFileSystem {

	
	 public void LoadFilesToHDFS (final String file_name, final String path) {

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
	                    fs.createNewFile(new Path("/user/hduser/myfolder1/"+file_name));	                    
                        fs.copyFromLocalFile(new Path(path), new Path("/user/hduser/"+file_name));
	                    
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
	 
	 public void copyDataTOHDFSFile(){
		 
	 }
	 
	 
	 public List<String> listHDFSFiles(){
		 
		 Configuration conf = new Configuration();
         conf.set("fs.defaultFS", "hdfs://localhost:9000/user/hduser");
         conf.set("hadoop.job.ugi", "hduser");
         List<String> li = new ArrayList<>();
         
        FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			
			FileStatus[] status = fs.listStatus(new Path("/user/hduser"));
	         for(int i=0;i<status.length;i++){
	        	 li.add(status[i].getPath().getName().toString());
	             System.out.println(status[i].getPath().getName());
	         }	        
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return li;
	 }
	 
	 public static void main(String args[]){
		 
		// new AccessHadoopFileSystem().LoadFilesToHDFS("test_file","abc");
		 new AccessHadoopFileSystem().listHDFSFiles();
	 }
}
