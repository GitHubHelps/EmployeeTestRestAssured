package RestAssured_folderStructure_log.TestCases;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import RestAssured_folderStructure_log.Base.base;
import RestAssured_folderStructure_log.Utilities.RandomValues;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class updateEmployee extends base {
	
	JSONObject json;
	String randomName = RandomValues.name();
	String randomAge = RandomValues.age();
	String randomSalary = RandomValues.salary();

@BeforeClass
	public void setupUrl () {
		
		logger.info("-= SetupURL =-");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

	}
@Test
	public void updEmployee () {
		
		JSONObject requestParam = new JSONObject();
		
		requestParam.put("name", randomName);
		requestParam.put("age", randomAge);
		requestParam.put("salary", randomSalary);
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParam.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/update/"+empID);
		
		String res = response.getBody().asString();
		System.out.println(res);	

		String success = response.body().asString();
		if (success.contains("success"))
			logger.info("The record is updated successfully");
		if (!success.contains("success"))
			logger.info("The updated is not successfull");

	}
@Test
	public void statusCode () {
		
		logger.info("-= Status Code =-");
		int statusCode = response.getStatusCode();
		logger.info("THe Status Code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String getBody = response.getBody().asString();
		Assert.assertEquals(getBody.contains(randomName), true);
		Assert.assertEquals(getBody.contains(randomAge), true);
		Assert.assertEquals(getBody.contains(randomSalary), true);
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
		if (contentType.contains("application/json;charset=utf-8"))
			logger.info("Content Type is correct: "+contentType);
		if (!contentType.contains("application/json;charset=utf-8"))
			logger.info("Content Type is incorrect: "+contentType);
		Assert.assertEquals("application/json;charset=utf-8", contentType);	
					
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
@Test
	public void contentEncoding () {
		
		logger.info("-= Content-Length =-");
		String contentLength = response.header("Content-Length");
		if (Integer.parseInt(contentLength)<800)
			logger.info("The Content-Length less than 800 symmbols - " + contentLength);
		if (Integer.parseInt(contentLength)>800)
			logger.warn("The Content-Length more than 800 symmbols - " + contentLength);
	
	}
}
