package RestAssured_folderStructure_log.TestCases;

import org.junit.Assert;
import org.testng.annotations.*;

import com.mongodb.util.JSON;

import RestAssured_folderStructure_log.Base.base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class SingleEmployeeData extends base {
@BeforeClass	
	public void employeeInfo () throws Exception {
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);

		Thread.sleep(3000);
				
	}
@Test	
	public void statusCode () {
	
		logger.info("-= Status Code =-");
		int statusCode = response.getStatusCode();
		logger.info("The status code is: " + statusCode);
		String res1 = response.getBody().asString();
		//System.out.println(res1);
		if (statusCode==404)
			logger.info("Connection or application error!");
		if (res1.contains(empID) & (statusCode==200))
			logger.info("Employee info is: "+ res1);
		if (!res1.contains(empID)& (statusCode==401))
			logger.error("No such an empoloyee! - "+ res1);		
		Assert.assertEquals(res1.contains(empID), true);

	}
@Test
	public void statusLine () {

	String statusLine = response.getStatusLine();
	logger.info("-= Status Line =-");
	if (statusLine.contains("HTTP/1.1 200 OK"))
		logger.info("Status line is correct: "+ statusLine);
	if (!statusLine.contains("HTTP/1.1 200 OK"))
		logger.error("Status line is not correct! - "+ statusLine);
	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
	
}
@Test
	public void contentType () {
	
	String contentType = response.header("Content-Type");
	logger.info("-= Content Type =-");
	if (contentType.contains("text/html; charset=UTF-8"))
		logger.info("Content type is correct: "+ contentType);
	if (!contentType.contains("text/html; charset=UTF-8"))
		logger.error("Content type is not correct! - "+ contentType);
	Assert.assertEquals(contentType, "text/html; charset=UTF-8");	
	
}
@Test
	public void server () {
	
	String server = response.header("Server");
	logger.info("-= Server =-");
	if (server.contains("nginx/1.16.0"))
		logger.info("The server is correct: "+ server);
	if (!server.contains("nginx/1.16.0"))
		logger.error("The server is not correct! - "+ server);
	Assert.assertEquals(server, "nginx/1.16.0");	
	
}
@Test
	public void contentEncoding () {
	
	String contentEncoding = response.header("Content-Encoding");
	logger.info("-= Content-Encoding =-");
	if (contentEncoding.contains("gzip"))
		logger.info("The Content-Encoding is correct: "+ contentEncoding);
	if (!contentEncoding.contains("gzip"))
		logger.error("The Content-Encoding is not correct! - "+ contentEncoding);
	Assert.assertEquals(contentEncoding, "gzip");
	
}
@Test
	public void contentLength () {
	
	String contentLength = response.header("Content-Length");
	logger.info("-= Content-Length =-");
	System.out.println(contentLength);
	if (Integer.parseInt(contentLength)<800)
		logger.info("The Content-Length less than 800 symmbols - " + contentLength);
	if (Integer.parseInt(contentLength)>800)
		logger.warn("The Content-Length more than 800 symmbols - " + contentLength);
	
}
@Test
	public void responseTime () {

	logger.info("-= ResponseTime =-");
	long responseTime = response.getTime();
	if (responseTime>2000)
		logger.warn("The response time is slow - greater than 2 seconds! - "+ responseTime);
	if (responseTime<2000)
		logger.warn("The response time is OK - less than 2 seconds! - "+ responseTime);

}	

}
