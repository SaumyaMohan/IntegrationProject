package main.java.com;



import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class routes {
	 
	public static void main(String[] args) {
		get("/home",  new GetHome());
	}
}
