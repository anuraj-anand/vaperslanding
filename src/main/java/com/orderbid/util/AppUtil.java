package com.orderbid.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class AppUtil {
	
	static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
	public static final String EURO = "\u20ac";
	
	public static String formatJson(String result, HttpServletResponse response){
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("application/json");
		byte[] byteArr = null;
		try {
		//	byteArr = result.getBytes("UTF-8");
			byteArr = result.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		result = new String(byteArr);
		return result;
	}
	
	public static String getJsonString(Object obj){
		String json = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			json =  mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
/*	public static Map getMapFromJsonString(String json){
		if(json != null && json.length() > 0 && !json.equals("{}")){
			JsonParserFactory factory=JsonParserFactory.getInstance();
			JSONParser parser=factory.newJsonParser();
			Map jsonData = parser.parseJson(json);
			return jsonData;
		}
		return null;
	}*/
	
	public static String generateRandomKey() {
		String[] symbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		int length = 6;
		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int indexRandom = random.nextInt(symbols.length);
			sb.append(symbols[indexRandom]);
		}
		String returnKey = sb.toString();
		return returnKey;
	}
	
	public static String generateRandomPassword() {
		String[] symbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z" };
		int length = 15;
		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int indexRandom = random.nextInt(symbols.length);
			sb.append(symbols[indexRandom]);
		}
		String password = sb.toString();
		return password;
	}

	public static String getPriorityString(Integer priority) {
		String priorityString = "";
		switch (priority) {
		case 1:
			priorityString = "Normal";
			break;
		case 2:
			priorityString = "Medium";
			break;
		case 3:
			priorityString = "High";
			break;
		case 4:
			priorityString = "Important";
			break;
		case 5:
			priorityString = "Urgent";
			break;
		default:
			break;
		}
		return priorityString;
	}

	
	private static String incrementOneMonth(String mm) {
		int month = 0;
		String actualMonth;
		month = Integer.parseInt(mm);
		month += 1;
		actualMonth = Integer.toString(month);
		return actualMonth;
	}

	public static boolean validateFileExtn(String fileNme, String extension) {
		Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(" + extension
				+ "))$)");
		Matcher mtch = fileExtnPtrn.matcher(fileNme);
		if (mtch.matches()) {
			return false;
		}
		return true;
	}

	public static String getAttributeValueFromParameterMap(
			HttpServletRequest request, String parameterName) {
		Map<?, ?> requestParameterMap = request.getParameterMap();
		String[] requestParameterValue = (String[]) requestParameterMap
				.get(parameterName);
		String resultValue = "";
		if (requestParameterValue != null)
			resultValue = requestParameterValue[0];
		return resultValue;
	}

	public static String getFileIconImagePath(String fileName) {
		String path = "empty.png";
		if (fileName == null) {
			return path;
		}
		int mid = fileName.lastIndexOf(".");
		String ext = fileName.substring(mid + 1, fileName.length())
				.toLowerCase();
		ext = ";" + ext + ";";
		path = "unknowndocument";
		if (";pdf;".contains(ext)) {
			path = "pdf";
		} else if (";txt;log;".contains(ext)) {
			path = "text";
		} else if (";doc;rtf;wps;wpd;docx;".contains(ext)) {
			path = "word";
		} else if (";xls;csv;wks;xlsx;".contains(ext)) {
			path = "excel";
		} else if (";zip;rar;7z;pkg;gz;sit;sitx;tar;zipx;".contains(ext)) {
			path = "zip";
		} else if (";fnt;fon;otf;ttf;".contains(ext)) {
			path = "font";
		} else if (";htm;html;mhtml;".contains(ext)) {
			path = "webpage";
		} else if (";bmp;gif;png;tiff;psd;jpg;jpeg;thm;tif;yuv;ai;svg;"
				.contains(ext)) {
			path = "image";
		} else if (";ppt;pptx;".contains(ext)) {
			path = "ppt";
		} else if (";3g2;3gp;asf;asx;avi;flv;mov;mp4;mpg;mpeg;rm;swf;vob;wmv;divx;"
				.contains(ext)) {
			path = "video";
		} else if (";aac;aif;iff;m3u;mp3;mpa;ra;wav;wma;".contains(ext)) {
			path = "audio";
		}
		return path;
	}
	
	public static String getDownloadFileIcon(String fileName) {
		String path = "unknownIcon";
		if (fileName == null) {
			return path;
		}
		int mid = fileName.lastIndexOf(".");
		String ext = fileName.substring(mid + 1, fileName.length())
				.toLowerCase();
		ext = ";" + ext + ";";
		path = "unknownIcon";
		if (";pdf;".contains(ext)) {
			path = "pdfIcon";
		} else if (";txt;log;".contains(ext)) {
			path = "textIcon";
		} else if (";doc;rtf;wps;wpd;docx;".contains(ext)) {
			path = "wordIcon";
		} else if (";xls;csv;wks;xlsx;".contains(ext)) {
			path = "excelIcon";
		} else if (";zip;rar;7z;pkg;gz;sit;sitx;tar;zipx;".contains(ext)) {
			path = "zipIcon";
		} else if (";htm;html;mhtml;".contains(ext)) {
			path = "webpageIcon";
		} else if (";bmp;gif;png;tiff;psd;jpg;jpeg;thm;tif;yuv;ai;svg;"
				.contains(ext)) {
			path = "imageIcon";
		} else if (";ppt;pptx;".contains(ext)) {
			path = "pptIcon";
		} else if (";3g2;3gp;asf;asx;avi;flv;mov;mp4;mpg;mpeg;rm;swf;vob;wmv;divx;"
				.contains(ext)) {
			path = "videoIcon";
		} else if (";aac;aif;iff;m3u;mp3;mpa;ra;wav;wma;".contains(ext)) {
			path = "audioIcon";
		}
		return path;
	}
	
	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;

	public static String fileSizeRepresentation(Long value){
	    final long[] dividers = new long[] { T, G, M, K, 1 };
	    final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	    if(value.longValue() < 1)
	        return "0 B";
	    String result = null;
	    for(int i = 0; i < dividers.length; i++){
	        final long divider = dividers[i];
	        if(value.longValue() >= divider){
	            result = format(value.longValue(), divider, units[i]);
	            break;
	        }
	    }
	    return result;
	}

	private static String format(final long value,
	    final long divider,
	    final String unit){
	    final double result =
	        divider > 1 ? (double) value / (double) divider : (double) value;
	    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}
	/*public Map<String,String> getCommaSeperatedStrings(String string){
		String[] array = string.split(",");
		
		
		return null;
	}*/
	
	public static Date getDateOnly(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String getFormattedDate(Date date){
		SimpleDateFormat ft = 
			      new SimpleDateFormat ("dd-MMM-yyyy");
		return ft.format(date);
	}
	
	public static int convertLongToInt(long longValue) {
	    int intValue= (int)longValue;
	    if ((long)intValue != longValue) {
	        throw new IllegalArgumentException(longValue + " cannot be cast to int without changing its value.");
	    }
	    return intValue;
	}
	
	/**
	 * Get file extension from file name
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	public static GridRangeData getGridRangeData(int itemCount, int pageNO){
		return new GridRangeData(((pageNO * itemCount )-itemCount), (pageNO * itemCount ));
	}
}
