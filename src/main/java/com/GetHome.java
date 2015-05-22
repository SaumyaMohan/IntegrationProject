package main.java.com;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import spark.Request;
import spark.Response;
import spark.Route;

public class GetHome implements Route{

	public Object handle(Request request, Response response) throws Exception {
		String string = "No data";
		String string1 = getFileWithUtil("file/test.txt");
		
		return string1;
	}
	
	private String getFileWithUtil(String fileName) {
		 
		String result = "";
	 
		ClassLoader classLoader = getClass().getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		return result;
	  }

}
