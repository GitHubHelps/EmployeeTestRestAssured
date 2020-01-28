package RestAssured_folderStructure_log.Base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class base {
	
	public RequestSpecification httpRequest;
	public Response response;
	public Headers header;
	public String empID = "12";
	
	public Logger logger;
	
@BeforeClass
public void setup () {
	
	logger = Logger.getLogger("EmployeeTest"); //name of the test/project
	PropertyConfigurator.configure("log4j.properties"); 
	logger.setLevel(Level.DEBUG);
	
}
}