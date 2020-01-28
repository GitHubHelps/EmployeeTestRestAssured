package RestAssured_folderStructure_log.TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import RestAssured_folderStructure_log.Utilities.ExcelDataConfig;
import RestAssured_folderStructure_log.Utilities.RandomValues;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateEmployee {
	
	Response response;
	RequestSpecification request;
	Header header;
	JSONObject json;
	Logger logger = Logger.getLogger(CreateEmployee.class);
//	String randomName = RandomValues.name();
//	String randomAge = RandomValues.age();
//	String randomSalary = RandomValues.salary();
	
	
@BeforeClass	
	public void setupUrl () {
	
		PropertyConfigurator.configure("log4j.properties"); 
		logger.info("-= SetupURL =-");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		request = RestAssured.given();

	} 
	
@Test(dataProvider="datatest")
	public void addEmployee (String randomName, String randomSalary, String randomAge) {
	
	logger.info("-= Create user =-");
	
	JSONObject requestParam = new JSONObject();
	
	requestParam.put("name", randomName);
	requestParam.put("age", randomAge);
	requestParam.put("salary", randomSalary);
	
	request.header("Content-Type","application/json");
	
	request.body(requestParam.toJSONString());
	
	response = request.request(Method.POST,"/create");
		
	String res = response.getBody().asString();
	System.out.println(res);	
	if (res.contains("success"))
		logger.info("The record is created successfully");
	if (!res.contains("success"))
		logger.info("The record is not created");

}
@Test	
	public void statusCode () {
		
		logger.info("-= Status Code =-");		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
			
	}
@Test
	public void statusLine () {
		
		logger.info("-= Status Line =-");
		String statusLine = response.getStatusLine();
		if (statusLine.contains("HTTP/1.1 200 OK"))
			logger.info("Status line is correct: "+statusLine);
		if (!statusLine.contains("HTTP/1.1 200 OK"))
			logger.info("Status line is incorrect: "+statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	
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

@DataProvider(name="datatest")
	public Object[][] passData() {
	
	ExcelDataConfig config = new ExcelDataConfig("C:\\Eclipse\\db3.xlsx");
	
	  int rows = config.getRowCount(0);
	  int columnCount = config.getColumnCount("Sheet1");
	  
	  Object [][] data = new Object [rows][columnCount];
	  
	  for ( int i = 0; i < rows; i++)
	    {
	      for ( int j = 0; j < columnCount; j++)
	      {
	        data[i][j] = config.getData( 0, i, j );
	      }
	    }

	  return data;
		
	}

}
