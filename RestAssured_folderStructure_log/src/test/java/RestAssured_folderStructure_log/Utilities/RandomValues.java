package RestAssured_folderStructure_log.Utilities;

import org.apache.commons.lang3.RandomStringUtils;


public class RandomValues {

	public static String name () {
		String randomName = RandomStringUtils.randomAlphabetic(6);
		return ("FirstName "+randomName);			
			}
	
	public static String age () {
		String randomAge = RandomStringUtils.randomNumeric(2);
		return (randomAge);			
			}
	
	public static String salary () {
		String randomSalary = RandomStringUtils.randomNumeric(4);
		return (randomSalary);			
			}
}
