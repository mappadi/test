package com.mixpanelValidation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.extent.ExtentReporter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.propertyfilereader.PropertyFileReader;



public class Mixpanel extends ExtentReporter {

//	LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
//	return local.getItem("guestToken");
	
//	waitTime(2000);
//	LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
//	Mixpanel.ValidateParameter(local.getItem("guestToken"),"Login Screen Display");
//
//	waitTime(2000);
//	LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
//	Mixpanel.ValidateParameter(local.getItem("guestToken"),"Skip Registartion");
	
	/**
	 * Global variables
	 */
	static String sheet = "Screen View";
	static String fileName = "Screen View";//ReportName;
	static String xlpath ;
	static String booleanParameters = "";
	static String integerParameters = "";
	static int rownumber;
	static String source = "";

	public static void ValidateParameter(String distinctID, String eventName,String Source)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		System.out.println("Parameter Validation");
		PropertyFileReader Prop = new PropertyFileReader("properties/MixpanelKeys.properties");
		booleanParameters = Prop.getproperty("Boolean");
		integerParameters = Prop.getproperty("Integer");
		fileName = ReportName;
		source = Source;
		xlpath = System.getProperty("user.dir") + "\\" + fileName + ".xlsx";
		fetchEvent(distinctID, eventName);
		// creatExcel();
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		creatExcel();
		fetchEvent("7c6e86eab3b1926a1af7ccdc6cd8e18d", "Screen View");
//		validation();
//		Instant instant = Instant.ofEpochSecond("1601475542");
//		java.util.Date time = new java.util.Date((long)1601475542*1000);
//		System.out.println("Time : "+time);

//		PropertyFileReader Prop = new PropertyFileReader("properties/MixpanelKeys.properties");
//		booleanParameters = Prop.getproperty("Boolean");
//		integerParameters = Prop.getproperty("Integer");
//		System.out.println(Stream.of(booleanParameters).anyMatch("Ad isEmpty"::equals));
//		System.out.println(isContain(booleanParameters,"Ad isEmpty"));
//		System.out.println(Prop.getproperty("Integer"));
//		System.out.println(Prop.getproperty("String"));
//		Date date = new Date();
//		System.out.println(dateToUTC(date));
//		System.out.println(returnDuration("1601487000"));
//	        long ut1 = Instant.now().getEpochSecond();
//	        System.out.println(ut1);
//
//	        long ut2 = System.currentTimeMillis() / 1000L;
//	        System.out.println(ut2);
//
//	        Date now = new Date();
//	        long ut3 = now.getTime() / 1000L;
//	        System.out.println(ut3);
		
		System.out.println(System.getProperty("os.name"));
	}

	/**
	 * Function to fetch logs from mixpanel dash board using rest assured API
	 * 
	 * @param distinct_id
	 * @param eventName
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static void fetchEvent(String distinct_id, String eventName)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String currentDate = dtf.format(now); // Get current date in formate yyyy-MM-dd
		System.out.println("Current Date : "+currentDate);
		Response request = RestAssured.given().auth().preemptive().basic("58baafb02e6e8ce03d9e8adb9d3534a6", "")
				.config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8").formParam("from_date", currentDate)
				.formParam("to_date", currentDate).formParam("event", "[\"" + eventName + "\"]")
				.formParam("where", "properties[\"$distinct_id\"]==\"" + distinct_id + "\"")
				.post("https://data.mixpanel.com/api/2.0/export/");
		request.print();

		sheet = eventName.trim();
		if (request != null) {
			String response = request.asString();
			String s[] = response.split("\n");
			parseResponse(s[s.length - 1]);
//			validation();
		}
	}

	/**
	 * Parse the response and split the response
	 * 
	 * @param reponse
	 */
	public static void parseResponse(String response) {
		String commaSplit[] = response.replace("\"properties\":{", "").replace("}", "")
				.replaceAll("[.,](?=[^\\[]*\\])", "-").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		creatExcel(); // Create an excel file
		for (int i = 0; i < commaSplit.length; i++) {
			if (i != 0) {
				String com[] = commaSplit[i].split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				/** Write key value into excel */
				write(i, com[0].replace("\"", "").replace("$", ""), com[1].replace("\"", "").replace("$", ""));
			}
		}
	}

	/**
	 * Function to create excel file of format .xlsx Function to create sheet
	 */
	public static void creatExcel() {
		try {
			File file = new File(xlpath);
			if (!file.exists()) {
				XSSFWorkbook workbook = new XSSFWorkbook();
				workbook.createSheet(sheet); // Create sheet
				FileOutputStream fos = new FileOutputStream(new File(xlpath));
				workbook.write(fos);
				workbook.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Function to write values into excel
	 * 
	 * @param i
	 * @param parameter
	 */
	public static void write(int i, String key, String value) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
			FileOutputStream output = new FileOutputStream(xlpath);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
			XSSFRow row = myExcelSheet.createRow(i);
			if (row == null) {
				row = myExcelSheet.createRow(i); // create row if not created
			}
			row.createCell(0).setCellValue(key); // write parameter key into excel into first column
			row.createCell(1).setCellValue(value); // write parameter value into excel second column
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validation
	 */
	public static void validation() {
		int NumberOfRows = getRowCount();
		System.out.println(NumberOfRows);
		for (rownumber = 1; rownumber < NumberOfRows; rownumber++) {
			try {
				XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
				XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
				String value = myExcelSheet.getRow(rownumber).getCell(1).toString();
				String key = myExcelSheet.getRow(rownumber).getCell(0).toString();
				if (value.trim().isEmpty()) {
					System.out.println("Paramter is empty :- Key:" + key + " - value" + value);
					fillCellColor();
				} else {
					if (isContain(booleanParameters, key)) {
						validateBoolean(value);
					} else if (isContain(integerParameters, key)) {
						validateInteger(value);
					}
				}
				validateParameter(key,value);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void validateParameter(String key,String value) {
		if(key.equals("User Type")) {
			if(!value.equalsIgnoreCase(userType)) {
				fillCellColor();
			}
		}else if(key.equals("Source")) {
			if(!value.equalsIgnoreCase(source)) {
				fillCellColor();
			}
		}
	}

	/**
	 * Get Row count
	 */
	// Generic method to return the number of rows in the sheet.
	public static int getRowCount() {
		int rc = 0;
		try {
			System.out.println(xlpath);
			FileInputStream fis = new FileInputStream(xlpath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheet);
			rc = s.getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rc;
	}

	public static void fillCellColor() {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
			XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet);
			Row Cellrow = myExcelSheet.getRow(rownumber);
			XSSFCellStyle cellStyle = myExcelBook.createCellStyle();
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
			if (Cellrow.getCell(0) == null) {
				Cellrow.createCell(0);
			}
			Cell cell1 = Cellrow.getCell(0);
			cell1.setCellStyle(cellStyle);
			FileOutputStream fos = new FileOutputStream(new File(xlpath));
			myExcelBook.write(fos);
			fos.close();
		} catch (Exception e) {
		}
	}

	private static void validateInteger(String value) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(value);
		if (!m.matches()) {
			fillCellColor();
		}
	}

	private static boolean isContain(String source, String subItem) {
		String pattern = "\\b" + subItem + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		return m.find();
	}

	private static void validateBoolean(String value) {
		if (!value.equals("true") || value.equals("false")) {
			fillCellColor();
		}
	}

}
