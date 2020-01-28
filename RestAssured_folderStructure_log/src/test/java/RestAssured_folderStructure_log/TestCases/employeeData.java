package RestAssured_folderStructure_log.TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import RestAssured_folderStructure_log.Base.base;

//@Listeners(RestAssured_folderStructure_log.Utilities.listener.class) //can be added to the other test cases (if it presents in the xml suite - listened is not needed in the class)

public class employeeData {
	
	Logger logger = Logger.getLogger(employeeData.class);
	public RequestSpecification httpRequest;
	public Response response;
	public Headers header;
			
@BeforeClass	
	public void employeesInfo () throws Exception	{	
	
	PropertyConfigurator.configure("log4j.properties"); 
	logger.info("-= Get all employees =-");		
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
    response = httpRequest.request(Method.GET,"/employees");
    
	Thread.sleep(3000);
	}

@Test	
	public void getAllEmployees () {
	logger.info("-= Test response body =-");		
	String respomdBody = response.getBody().asString();
    System.out.println("Response body is: " + respomdBody);
    logger.info("Response body is: " + respomdBody);
//  logger.trace("Trace Message!");
//  logger.debug("Debug Message!");
//  logger.info("Info Message!");
//  logger.warn("Warn Message!");
//  logger.error("Error Message!");
//  logger.fatal("Fatal Message!");
//	Assert.assertTrue(respomdBody!=null);
//	Assert.assertNotNull(respomdBody);
	Assert.assertNotNull(respomdBody, "The response is null!");
//	logger.error("Error respond");
//	Assert.assertNull(respomdBody, "The response is not null!");	
	}

@Test
	public void statusCode () {
	
	logger.info("-= Test statusCode =-");		
	int statusCode = response.getStatusCode();
	System.out.println("Response body status code is: " + statusCode);	
	logger.info("Test statusCode is: " + statusCode);
	Assert.assertEquals(statusCode, 200);

	}

@Test
	public void statusLine () {
	
	logger.info("-= Test statusLine =-");
	String statusLine = response.getStatusLine();
	logger.info("Test statusLine is: " + statusLine);
	System.out.println("The status line is: " + statusLine);
	Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
}

@Test
	public void contentType () {
	
	logger.info("-= Test Content-Type =-");
	String contentType = response.header("Content-type");
	logger.info("Test Content-Type: "+contentType);
	System.out.println("Test Content-Type is: " + contentType);
	Assert.assertEquals(contentType, "application/json;charset=utf-8");
			
}

@Test
	public void server () {
	
		logger.info("-= Server =-");
		String server = response.header("Server");
		logger.info("Test Server is: " + server);
		System.out.println("Test Server is: " + server);
		Assert.assertEquals(server, "nginx/1.16.0");		
		
}

@Test
	public void contentEncoding () {
		
	logger.info("-= contentEncoding =-");
	String contentEncoding = response.header("Content-Encoding");
	logger.info("Test Content-Encoding is: " + contentEncoding);
	System.out.println("Test Server is: " + contentEncoding);
	Assert.assertEquals(contentEncoding, "gzip");	

}

@Test
	public void contentLength () {
	
	logger.info("-= contentLength =-");
	String contentLength = response.header("Content-Length");
	logger.info("Test Content-Length is: " + contentLength);
	System.out.println("Test Content-Length is: " + contentLength);
	
	if (Integer.parseInt(contentLength)>100)
	logger.warn("The content lenght is bigger than 100");	
	
	Assert.assertTrue(Integer.parseInt(contentLength)>100);

}

@Test
	public void responseTime () {
	
	logger.info("-= ResponseTime =-");
	long responseTime = response.getTime();
	logger.info("Test Response Time is: " + responseTime);
	System.out.println("Test Response Time is: " + responseTime);
	
	if (responseTime>1000)
	logger.warn("The response time is greater that 1 second!");	
	
//	Assert.assertTrue(responseTime<1000);
	
}
}