package RestAssured_folderStructure_log.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import RestAssured_folderStructure_log.Base.base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


public class deleteEmployee extends base {

@Test
	public void deleteEmp () {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		//Get the Json path (structire of the file)
		JsonPath jsonPathEvaulator = response.jsonPath();
		String res1 = response.getBody().asString(); 
		System.out.println(res1);
		//Capture id (first id of the list!)
		String empID = jsonPathEvaulator.get("data.id[0]");
		System.out.println(empID);
		//Pass id to delete
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		String ress = response.getBody().asString(); 
	    System.out.println(ress);

	}

@Test
	public void statusCode () {
	
		logger.info("-= Status Code =-");
		int statusCode = response.getStatusCode();
		logger.info("THe Status Code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);

	}
@Test
	public void statusLine () {
	
		logger.info("-= Status Line =-");
		String statusLine = response.getStatusLine();
		logger.info("The status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
	}
@Test
	public void contentType () {

		logger.info("-= Content Type =-");
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		if (contentType.contains("application/json;charset=utf-8"))
			logger.info("Content Type is correct: "+contentType);
		if (!contentType.contains("application/json;charset=utf-8"))
			logger.info("Content Type is incorrect: "+contentType);
		Assert.assertEquals("text/html; charset=UTF-8", contentType);	
					
	}
@Test
	public void server () {

		logger.info("-= Server =-");
		String server = response.header("Server");
		if (server.contains("nginx/1.16.0"))
			logger.info("The Server is correct: "+server);
		if (!server.contains("nginx/1.16.0"))
			logger.info("The Server is incorrect: "+server);
		Assert.assertEquals("nginx/1.16.0", server);

	}

}