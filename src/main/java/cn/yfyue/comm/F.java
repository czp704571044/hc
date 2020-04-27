package cn.yfyue.comm;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F {
	// 全角转半角
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	// 取日制时间
	public static String getLogTime() {
		Calendar now = Calendar.getInstance();
		String year = Integer.toString(now.get(Calendar.YEAR));
		String mon = Integer.toString(now.get(Calendar.MONTH) + 1);
		String day = Integer.toString(now.get(Calendar.DATE));
		String hour = Integer.toString(now.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(now.get(Calendar.MINUTE));
		String sec = Integer.toString(now.get(Calendar.SECOND));
		String mi = Integer.toString(now.get(Calendar.MILLISECOND));
		mon = (mon.length() == 1) ? "0" + mon : mon;
		day = (day.length() == 1) ? "0" + day : day;
		hour = (hour.length() == 1) ? "0" + hour : hour;
		min = (min.length() == 1) ? "0" + min : min;
		sec = (sec.length() == 1) ? "0" + sec : sec;

		return (year + "-" + mon + "-" + day + " " + hour + ":" + min + ":" + sec + "." + mi);
	}

	// 取日制时间
	public static String getSysTime() {
		Calendar now = Calendar.getInstance();
		String year = Integer.toString(now.get(Calendar.YEAR));
		String mon = Integer.toString(now.get(Calendar.MONTH) + 1);
		String day = Integer.toString(now.get(Calendar.DATE));
		String hour = Integer.toString(now.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(now.get(Calendar.MINUTE));
		String sec = Integer.toString(now.get(Calendar.SECOND));

		mon = (mon.length() == 1) ? "0" + mon : mon;
		day = (day.length() == 1) ? "0" + day : day;
		hour = (hour.length() == 1) ? "0" + hour : hour;
		min = (min.length() == 1) ? "0" + min : min;
		sec = (sec.length() == 1) ? "0" + sec : sec;
		return (year + mon + day + hour + min + sec);
	}

	// 判断号码格式
	public static boolean isMobileNo(String mobileNo) {
		boolean bflag = false;
		Pattern p = null;
		Matcher m = null;
		try {
			if (mobileNo != null && mobileNo.length() == 11) {
				p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
				m = p.matcher(mobileNo.trim());
				bflag = m.matches();
			} else {
				bflag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bflag;
	}

	// 验证邮箱
	public static boolean isEmail(String email) {
		boolean bflag = false;
		Pattern p = null;
		Matcher m = null;
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		try {
			if (email != null && !email.equals("")) {
				p = Pattern.compile(str); // 验证邮箱
				m = p.matcher(email.trim());
				bflag = m.matches();
			} else {
				bflag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bflag;
	}

	// 判断是否为纯正整数 正整数 T
	public static boolean isPureNumer(String str) {
		if (isNull(str) != null) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	// 处理空值空值
	public static String isNull(String codeValue) {
		String Str = null;
		try {
			if (codeValue != null) {
				if (codeValue.trim().equals("0000")) {
					Str = null;
				} else {
					if (codeValue.trim().toUpperCase().equals("NULL")) {
						Str = null;
					} else if (codeValue.trim().isEmpty()) {
						Str = null;
					} else {
						if (codeValue.trim().equals("")) {
							Str = null;
						} else {
							Str = codeValue.trim();
						}
					}
				}
			} else {
				Str = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Str;
	}

	// 字符串转日期 format yyyy-MM-dd HH:mm:ss
	public static Date stringToDate(String value, String format) {
		Date date = null;
		if (value == null) {
			return null;
		}
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 日期转字符串 yyyy-MM-dd HH:mm:ss
	public static String dateToString(Date date, String format) {
		String dateStr = null;
		if (date == null) {
			return null;
		}
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	// %%转
	public static String urlToStr(String str, String charset) {
		String strurl = "";
		try {
			strurl = URLDecoder.decode(str, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strurl;
	}

	// url车%%%%%
	public static String strToUrl(String strurl, String charset) {
		String str = "";
		try {
			str = URLEncoder.encode(strurl, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * 
	 * @param roundNum
	 *            double 要格式化的数据
	 * @param iFalg
	 *            int 保留小数的位数
	 * @return double
	 */
	public static double formatRoundDulbel(double roundNum, int iFalg) {
		BigDecimal bb = new BigDecimal(roundNum);
		double d1 = bb.setScale(iFalg, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d1;
	}

	/**
	 * 
	 * @param roundNum
	 *            float 要格式化的数据
	 * @param iFalg
	 *            int 保留小数的位数
	 * @return float
	 */
	public static float formatRoundFloat(float roundNum, int iFalg) {
		BigDecimal bb = new BigDecimal(roundNum);
		float f1 = bb.setScale(iFalg, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	// 字符串转INT
	public static int StrToInt(String str) {
		int n = 0;
		try {
			if (str != null && !str.isEmpty()) {
				n = Integer.parseInt(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	// 字符串转单精度
	public static float strToFloat(String str) {
		float f = 0.0f;
		try {
			f = Float.parseFloat(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// 字符串Long
	public static long strToLong(String str) {
		long lnum = 0;
		try {
			if (str != null && !str.isEmpty()) {
				lnum = Long.parseLong(str.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lnum;
	}

	// 计算百分比
	public static String bfb(String zi, String mu) {
		String bfb = "%";
		float nzi = 0.0f;
		float nmu = 0.0f;

		try {
			nzi = strToFloat(zi);
			nmu = strToFloat(mu);
			if (nmu == 0.0f) {
				nmu = 1.0f;
			}
			float fbfb = (nzi / nmu) * 100;
			bfb = "" + formatRoundFloat(fbfb, 2) + "%";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bfb;
	}

	// 字符串转INT
	public static String intToStr(int n) {
		String str = "";
		try {
			str = String.valueOf(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// 密码进行MD5加密
	public static String md5Passwd(String passwd) {
		String str = null;
		try {
			str = Md5Util.strToMD5(passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// 格式化日志产数字符长度
	public static String logStr(String para) {
		String str = null;
		int nLen = 50;
		try {
			if (para != null && !para.trim().isEmpty()) {
				nLen = para.length();
				if (nLen > 30) {
					nLen = 20;
				}
				str = para.substring(0, nLen);
			} else {
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// 格式化页数
	public static int fPage(String page) {
		int nPage = 1;
		try {
			if (F.isNull(page) != null && !page.trim().isEmpty()) {
				nPage = F.StrToInt(page);
				if (nPage <= 0) {
					nPage = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nPage;
	}

	// 格式化行数
	public static int fRow(String rowNum) {
		int nRows = 20;
		try {
			if (F.isNull(rowNum) != null && !rowNum.trim().isEmpty()) {
				nRows = F.StrToInt(rowNum);
				if (nRows <= 0) {
					nRows = 20;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nRows;
	}

	// 是否URL 是返回 T 不是返F
	public static boolean isUrl(String pInput) {
		if (pInput == null) {
			return false;
		}
		String regEx = "^(http|https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(pInput);
		L.p(pInput);
		// L.p(""+matcher.matches());
		return matcher.matches();
	}

	// 判断是否是数字或字母
	public static boolean isNumberOrLetter(String str) {
		Pattern pattern = Pattern.compile("[A-Z,a-z,0-9]*");
		Matcher isNumberOrLetter = pattern.matcher(str);
		if (!isNumberOrLetter.matches()) {
			return false;
		}
		return true;
	}

	// 获取时间戳毫秒
	public static String getMillis() {
		String millisStr = null;
		try {
			millisStr = "" + System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return millisStr;
	}

	// 生成序列
	public synchronized static String getSeqCode() {
		String strCode = null;
		try {
			strCode = getSysTime() + getSeq();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strCode;
	}

	private static final int SEQ_MAX_VALUE = 999; // 序列的最大值
	private static final int SEQ_MIN_VALUE = 100; // 序列的最小值
	private static Integer msgSEQ; // 消息的报文体序列号
	// 取得序列号

	public synchronized static int getSeq() {
		int nMsgSeq = 0;
		if (msgSEQ == null) {
			msgSEQ = new Integer(SEQ_MIN_VALUE);
		}
		synchronized (msgSEQ) {
			nMsgSeq = msgSEQ.intValue();
			if (nMsgSeq > SEQ_MAX_VALUE) {
				nMsgSeq = SEQ_MIN_VALUE;
			}
			msgSEQ = new Integer(nMsgSeq + 1);
			nMsgSeq = msgSEQ.intValue();
		}
		return nMsgSeq;
	}

	// 判断经度
	public static boolean isG_cn(String str) {
		Pattern pattern = Pattern.compile("^(-|\\+)?(180|(\\d{1,2}|1([0-7]\\d))\\.\\d{1,6})$");
		Matcher gd = pattern.matcher(str);
		if (gd.matches()) {
			float gdf = strToFloat(str);
			if (gdf >= 73.1 && gdf <= 135.1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void main(String arg[]) throws Exception {
		if (isGps_cn("116.39622,39.926002")) {
			L.p("正常");
		} else {
			L.p("错误");
		}
	}

	// 判断纬度
	public static boolean isW_cn(String str) {
		// ^-?(([1-9])|([1-8]\\d)|90)(\\.\\d{1,6})?$
		Pattern pattern = Pattern.compile("^-?(([1-9])|([1-8]\\d)|90)(\\.\\d{1,6})?$");
		Matcher isWd = pattern.matcher(str);
		L.p(str);
		if (isWd.matches()) {
			float wdf = strToFloat(str);
			if (wdf >= 18.1 && wdf <= 53.6) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// 判断经纬度是否正常 正确返回T
	public static boolean isGps_cn(String str) {
		Pattern pattern = Pattern.compile("^[+-]?\\d*.\\d*,[+-]?\\d*.\\d*$");
		Matcher isGps = pattern.matcher(str);
		String gpsArr[] = null;
		if (isGps.matches()) {
			gpsArr = str.split(",");
			if (isW_cn(gpsArr[1])) {
				if (isG_cn(gpsArr[0])) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
    //验证是否是yyyMMddHHmmss字符串
	public static boolean isValidDateTime(String str) {
		boolean bflag = true;
		SimpleDateFormat dateTimeformat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			if (str != null && !str.trim().isEmpty()) {
				if (str.length() == 14) {
					dateTimeformat.setLenient(false);
					dateTimeformat.parse(str);
				} else {
					bflag = false;
				}
			} else {
				bflag = false;
			}
		} catch (ParseException e) {
			bflag = false;
		}
		return bflag;
	}

    //验证是否是JSON字符串
	public static boolean isGoodJson(String json) {  
		 boolean bFlag=false;
		   try {    
			   if(json!=null && !json.trim().isEmpty()){
				 if(json.substring(0, 1).equals("{")){
				       new JsonParser().parse(json);  
				       bFlag=true;
				 }
			   }
		   } catch (JsonParseException e) {    
		       System.out.println("bad json: " + json);      
		   }
		   return bFlag;
	}
	//获取系统小时
	public static int getSysHour(){
		int hour=9;
		try{
			Calendar now = Calendar.getInstance();
			hour =  now.get(Calendar.HOUR_OF_DAY);
			//hour=9;
		}catch(Exception e){e.printStackTrace();}
		return hour; 
	}
	//取几天前的日期
	public static String getNDayAgo(int dayNum)
	{
		  String runDay=null;
		  try{
			  Calendar ndayAgo = Calendar.getInstance();
			  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			  ndayAgo.add(Calendar.DATE, -dayNum);
			  runDay = sdf1.format(ndayAgo.getTime());		  
		  }catch(Exception e){e.printStackTrace();}
	     if(runDay!=null && !runDay.trim().isEmpty()){

	    	 runDay=runDay+" 00:00:00"; 
	     }else{
	    	 runDay="2001-01-01 00:00:00"; 
	     }
	     return runDay;
	}
	
	//中文转区位码
	public static String gbkTo2Unicode(String str) {
	    StringBuffer ret = new StringBuffer();
	    String tmp = null;
	    try {
	        byte[] bb = str.getBytes("UTF-16");
	        for (int i = 3; i < bb.length; i += 2) {
	            //i++;
	            if (bb[i - 1] != 0) {
	                tmp = toHEXString(bb[i - 1]) + toHEXString(bb[i]);
	            } else {
	                tmp = "00" + toHEXString(bb[i]);
	            }
	            ret.append(".").append(tmp);
	        }
	        return ret.toString();
	    } catch (Exception e) {
	        // TODO: handle exception
	        System.out.println(str);
	        return str;
	    }
	}

	/**
	 * BYTE 转成哈稀字符串
	 * @param b byte
	 * @return String
	 */
	private static String toHEXString(byte b) {
	    return ("" + "0123456789ABCDEF".charAt(0xf & b >> 4) +
	            "0123456789ABCDEF"
	            .charAt(b & 0xF));
	}

	/**
	 * 把UTF-16转变成GBK
	 * @param str String 输入utf-16字符串
	 * @return String
	 */
	public static String unicode2TOgbk(String str) {
	    //String[] s = str.split("\\\\u");
	    String strtemp = str;
	    //String[] s = str.split("\\.");
	    String[] s = strtemp.split("\\.");
	    String strsend = "";
	    String strsendtmp = "";
	    StringBuffer ret = new StringBuffer();
	    ArrayList al = new ArrayList();
	    for (int i = 1; i < s.length; i++) {
	        ret.append((char) Integer.parseInt(s[i], 16));
	        strsendtmp = "a" + (char) Integer.parseInt(s[i], 16);
	        al.add(i - 1, strsendtmp.substring(1));
	    }
	    for (int i = 0; i < al.size(); i++) {
	        strsendtmp = (String) al.get(i);
	        strsend = strsend + strsendtmp;
	    }
	    //System.out.println(strsend);
	    return strsend;
	}
	//生成16位大写编码
	public static String get16Code(){
		 String cbCode=null;
		  try{
			  cbCode=MD5_16.md5(F.getMillis()+ F.getSeqCode());
		  }catch(Exception e){e.printStackTrace();}
		return 	cbCode.toUpperCase();
		}
	//取4位随机数
	public static String get4CharRandomNum(){
		String randomStr=null;
		try{
			randomStr=""+((Math.random()*9000)+1000);
		}catch(Exception e){e.printStackTrace();}
		return randomStr;
	}
	//取8位随机数
	public static String get8CharRandomNum(){
		String randomStr=null;
		try{
			randomStr=""+((Math.random()*90000000)+10000000);
		}catch(Exception e){e.printStackTrace();}
		return randomStr;
	}
	//去掉换行符
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	//分转成元
	public static String fromFenToYuan(String fen) {  
	    String yuan = "";  
	    int MULTIPLIER = 100;  
	    Pattern pattern = Pattern.compile("^[0-9][0-9]*{1}");  
	    Matcher matcher = pattern.matcher(fen);  
	    if (matcher.matches()) {  
	        yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();  
	    } else {  
	        System.out.println("参数格式不正确!");  
	    }  
	    return yuan;  
	}  
	//元转成分
	public static String fromYuanToFen(String yuan) {  
	    String fen = "";  
	    Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");  
	    Matcher matcher = pattern.matcher(yuan);  
	    if (matcher.matches()) {  
	        try {  
	            NumberFormat format = NumberFormat.getInstance();  
	            Number number = format.parse(yuan);  
	            double temp = number.doubleValue() * 100.0;  
	            // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
	            format.setGroupingUsed(false);  
	            // 设置返回数的小数部分所允许的最大位数  
	            format.setMaximumFractionDigits(0);  
	            fen = format.format(temp);  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        }  
	    }else{  
	        System.out.println("参数格式不正确!");  
	    }  
	    return fen;  
	} 
	//当前加天数
	public static String nowDatePlusDay(int num){
		String enddate =null;
		Date d=null;
		try{
			d = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE, num);
			d = ca.getTime();
			enddate = format.format(d);
		}catch(Exception e){e.printStackTrace();}
		return enddate;
	}
	//指定日期加天数
	public static String datePlusDay(String assignDate,int num){
		SimpleDateFormat format =null;
		String enddate=null;
		try{
			format = new SimpleDateFormat("yyyy-MM-dd");
			Date  currdate = format.parse(assignDate);
			Calendar ca = Calendar.getInstance();
			ca.setTime(currdate);
			ca.add(Calendar.DATE, num);
			currdate = ca.getTime();
			enddate = format.format(currdate);
		 }catch(Exception e){e.printStackTrace();}
		return enddate;
	}
	//删除最后字符
	public static String delLastChar(String str){
		String returnStr=null;
		try{
	         returnStr=str.substring(0, str.length()-1);		
		}catch(Exception e){e.printStackTrace();}
        return returnStr;
	}
	//获取UUID
	public static String getUUID(){
		String uuidStr=null;
		try{
			uuidStr= UUID.randomUUID().toString().replaceAll("-", "");
		}catch(Exception e){e.printStackTrace();}
		return uuidStr;
	}
	
	//计算二时间相差分钟
	public static int getDateDifferMin(Date endDate, Date nowDate) {
         // 获得两个时间的毫秒时间差异
		int min =0;
		if(endDate!=null && nowDate!=null){
		    long diff = endDate.getTime() - nowDate.getTime();
		    min=(int)(diff / (1000 * 60));			
		}
	    return min;
	}
	
}
