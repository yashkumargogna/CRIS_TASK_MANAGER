/*
 * Created on May 15, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DateTime implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int DD;
	private int MM;
	private int YYYY;
	private int HH;
	private int MI;
	private int SECONDS;
	private String format;
	private Calendar calendar;
	
	/**
	 * 
	 */
	public DateTime() {
		super();
		calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MILLISECOND,0);
		DD = calendar.get(Calendar.DATE);
		MM = calendar.get(Calendar.MONTH)+1;
		YYYY = calendar.get(Calendar.YEAR);
		HH = calendar.get(Calendar.HOUR_OF_DAY);
		MI = calendar.get(Calendar.MINUTE);
		SECONDS = calendar.get(Calendar.SECOND);
		format = "dd-MMM-yyyy";
	}
	/**
	 * 
	 */
	/*public DateTime(String format) {
		super();
		calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MILLISECOND,0);
		DD = calendar.get(Calendar.DATE);
		MM = calendar.get(Calendar.MONTH)+1;
		YYYY = calendar.get(Calendar.YEAR);
		HH = calendar.get(Calendar.HOUR_OF_DAY);
		MI = calendar.get(Calendar.MINUTE);
		SECONDS = calendar.get(Calendar.SECOND);
		this.format = format;
	}*/
	
	/**
	 * @param dd
	 * @param mm
	 * @param yyyy
	 * @param hh
	 * @param mi
	 * @param seconds
	 */
	public DateTime(int dd, int mm, int yyyy, int hh, int mi, int seconds) {
		super();
		DD = dd;
		MM = mm;
		YYYY = yyyy;
		HH = hh;
		MI = mi;
		SECONDS = seconds;
		calendar = new GregorianCalendar();
		calendar.set(YYYY,MM-1,DD,HH,MI,SECONDS);
		calendar.set(Calendar.MILLISECOND,0);
		format = "dd-MMM-yyyy";
	}
	
	/**
	 * @param dd
	 * @param mm
	 * @param yyyy
	 * @param hh
	 * @param mi
	 */
	public DateTime(int dd, int mm, int yyyy, int hh, int mi) {
		super();
		DD = dd;
		MM = mm;
		YYYY = yyyy;
		HH = hh;
		MI = mi;
		SECONDS = 0;
		calendar = new GregorianCalendar();
		calendar.set(YYYY,MM-1,DD,HH,MI,SECONDS);
		calendar.set(Calendar.MILLISECOND,0);
		format = "dd-MMM-yyyy";
	}
	
	public DateTime(DateTime dateTime) {
		super();
		DD = dateTime.getDD();
		MM = dateTime.getMM();
		YYYY = dateTime.getYYYY();
		HH = dateTime.getHH();
		MI = dateTime.getMI();
		SECONDS = 0;
		calendar = new GregorianCalendar();
		calendar.set(YYYY,MM-1,DD,HH,MI,SECONDS);
		calendar.set(Calendar.MILLISECOND,0);
		format = "dd-MMM-yyyy";
	}
	
	/**
	 * @param dd
	 * @param mm
	 * @param yyyy
	 */
	public DateTime(int dd, int mm, int yyyy) {
		super();
		DD = dd;
		MM = mm;
		YYYY = yyyy;
		HH = 0;
		MI = 0;
		SECONDS = 0;
		calendar = new GregorianCalendar();
		calendar.set(YYYY,MM-1,DD,HH,MI,SECONDS);
		calendar.set(Calendar.MILLISECOND,0);
		format = "dd-MMM-yyyy";
	}
	
	
	public DateTime(String date) throws ParseException {
		super();
		this.format = "dd-MMM-yyyy";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.MILLISECOND,0);
		updateFields();
	}
	/**
	 * @param date
	 * @param format
	 * @throws ParseException
	 */
	public DateTime(String date, String format) throws ParseException {
		super();
		this.format = format;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.MILLISECOND,0);
		updateFields();
	}
	
	public DateTime(String date, String hh, String mi, String format) throws ParseException {
		super();
		this.format = format;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE), Integer.parseInt(hh), Integer.parseInt(mi));
		calendar.set(Calendar.MILLISECOND,0);
		updateFields();
	}
	public DateTime(Timestamp time)
	{
		super();
		calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(time.getTime());
		format = "dd-MMM-yyyy";
		updateFields();
	}
	public Timestamp getTimeStamp()
	{
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	
	public String getDateTime()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MMM-yyyy");
		return formatter.format(calendar.getTime());
	}
	
	/**
	 * @param format The format to set.
	 * <br><b>String						===========					Result</b>
	 * <br>"dd/MM/yyyy"													23/12/2009
	 * <br>"dd/MM/yyyy HH:mm"											23/12/2009 15:24
	 * <br>"yyyy.MM.dd G 'at' HH:mm:ss z"	===========					2001.07.04 AD at 12:08:56 PDT
	 * <br>"EEE, MMM d, ''yy"				===========					Wed, Jul 4, '01
	 * <br>"h:mm a"							===========					12:08 PM
	 * <br>"hh 'o''clock' a, zzzz"	===========							12 o'clock PM, Pacific Daylight Time
	 * <br>"K:mm a, z"			===========								0:08 PM, PDT
	 * <br>"yyyyy.MMMMM.dd GGG hh:mm aaa"===========						02001.July.04 AD 12:08 PM
	 * <br>"EEE, d MMM yyyy HH:mm:ss Z"	===========						Wed, 4 Jul 2001 12:08:56 -0700
	 * <br>"yyMMddHHmmssZ"			===========							010704120856-0700
	 */
	public String getDateTime(String format)
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		return formatter.format(calendar.getTime());
	}
	
	public void setDateTime(String date, String format) throws ParseException
	{
		this.format = format;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		calendar.setTime(formatter.parse(date));
		updateFields();
	}
	
	public void addDate(int dd)
	{
		calendar.add(Calendar.DATE,dd);
		updateFields();
	}
	public void addMonth(int mm)
	{
		calendar.add(Calendar.MONTH,mm);
		updateFields();
	}
	public void addYear(int yy)
	{
		calendar.add(Calendar.YEAR,yy);
		updateFields();
	}
	public void addHour(int hh)
	{
		calendar.add(Calendar.HOUR_OF_DAY,hh);
		updateFields();
	}
	public void addMinutes(int mi)
	{
		calendar.add(Calendar.MINUTE,mi);
		updateFields();
	}
	public void addSeconds(int sec)
	{
		calendar.add(Calendar.SECOND,sec);
		updateFields();
	}
	private void updateCalendar()
	{
		calendar.set(YYYY,MM-1,DD,HH,MI,SECONDS);
		calendar.getTime();
		updateFields();
	}
	private void updateFields()
	{
		DD = calendar.get(Calendar.DATE);
		MM = calendar.get(Calendar.MONTH)+1;
		YYYY = calendar.get(Calendar.YEAR);
		HH = calendar.get(Calendar.HOUR_OF_DAY);
		MI = calendar.get(Calendar.MINUTE);
		SECONDS = calendar.get(Calendar.SECOND);
	}
	/**
	 * @return Returns the calendar.
	 */
	public Calendar getCalendar() {
		return calendar;
	}
	/**
	 * @return Returns the dD.
	 */
	public int getDD() {
		return DD;
	}
	/**
	 * @param dd The dD to set.
	 */
	public void setDD(int dd) {
		DD = dd;
		updateCalendar();
	}
	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format The format to set.
	 * <br><b>String					===========						Result</b>
	 * <br>"yyyy.MM.dd G 'at' HH:mm:ss z"	===========					2001.07.04 AD at 12:08:56 PDT
	 * <br>"EEE, MMM d, ''yy"	===========								Wed, Jul 4, '01
	 * <br>"h:mm a"				===========								12:08 PM
	 * <br>"hh 'o''clock' a, zzzz"	===========							12 o'clock PM, Pacific Daylight Time
	 * <br>"K:mm a, z"			===========								0:08 PM, PDT
	 * <br>"yyyyy.MMMMM.dd GGG hh:mm aaa"===========						02001.July.04 AD 12:08 PM
	 * <br>"EEE, d MMM yyyy HH:mm:ss Z"	===========						Wed, 4 Jul 2001 12:08:56 -0700
	 * <br>"yyMMddHHmmssZ"			===========							010704120856-0700 
	 * <br>"dd/MM/yyyy HH:mm"  ==========================				23/08/2009 15:45 
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return Returns the HH.
	 */
	public int getHH() {
		return HH;
	}
	/**
	 * @param hh The HH to set.
	 */
	public void setHH(int hh) {
		HH = hh;
		updateCalendar();
	}
	/**
	 * @return Returns the mI.
	 */
	public int getMI() {
		return MI;
	}
	/**
	 * @param mi The mI to set.
	 */
	public void setMI(int mi) {
		MI = mi;
		updateCalendar();
	}
	/**
	 * @return Returns the mM.
	 */
	public int getMM() {
		return MM;
	}
	/**
	 * @param mm The mM to set.
	 */
	public void setMM(int mm) {
		MM = mm;
		updateCalendar();
	}
	/**
	 * @return Returns the sECONDS.
	 */
	public int getSECONDS() {
		return SECONDS;
	}
	/**
	 * @param seconds The sECONDS to set.
	 */
	public void setSECONDS(int seconds) {
		SECONDS = seconds;
		updateCalendar();
	}
	/**
	 * @return Returns the YYYY.
	 */
	public int getYYYY() {
		return YYYY;
	}
	/**
	 * @param yyyy The YYYY to set.
	 */
	public void setYYYY(int yyyy) {
		YYYY = yyyy;
		updateCalendar();
	}
	public String toString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		return formatter.format(calendar.getTime());
	}
	
	public int getDaysInMonth()
	{
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	public String getDaysInMonthStr()
	{
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < 10 ? "0"+calendar.getActualMaximum(Calendar.DAY_OF_MONTH):Integer.toString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		return getTimeStamp().compareTo(((DateTime)o).getTimeStamp());
	}
	/*
	 * return d1 > d2: 1, d1 < d2: -1, d1 == d2: 0
	 */
	public int compareTo(DateTime dateTime2) 
	{
		long diff = getTimeStamp().getTime()-dateTime2.getTimeStamp().getTime();
		
		return (diff >0 ? 1:diff < 0 ? -1:0);
	}
	
	public boolean equals(DateTime d2) 
	{
		return this.getDateTime("dd-MMM-yyyy HH:mm").equals(d2.getDateTime("dd-MMM-yyyy HH:mm")) ? true:false;
	}
	
	public DateTime getFinancialYearFirstDate()
	{ 
		int year = 0;
		if(this.getMM() < 4)
			year=this.getYYYY()-1;
		else
			year=this.getYYYY();
		return new DateTime(1,4,year);		
	}
	
	public DateTime getFinancialYearLastDate()
	{ 
		int year = 0;
		if(this.getMM() > 3)
			year=this.getYYYY()+1;
		else
			year=this.getYYYY();
		return new DateTime(31,3,year);		
	}
	
	public static int getDiffInDate(DateTime date1,DateTime date2)
	{ 
	    long diff = 0;
		diff = date1.getTimeStamp().getTime()-date2.getTimeStamp().getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    return (int)diffDays;
	}
	
	public static int getDiffInHr(DateTime date1,DateTime date2)
	{ 
		long diff=0;
		int  diffHr=0;
		diff = date1.getTimeStamp().getTime()-date2.getTimeStamp().getTime();
		if(diff !=0)
		{
			diffHr=(int)(diff/3600000);
		}
		return diffHr;
	}
	
	public static int getDiffInHr(Timestamp date1,Timestamp date2)
	{ 
		long diff=0;
		int  diffHr=0;
		diff = date1.getTime()-date2.getTime();
		if(diff !=0)
		{
			diffHr=(int)(diff/3600000);
		}
		return diffHr;
	}
	
	public static int getDiffInMinute(DateTime date1,DateTime date2)
	{ 
		long diff=0;
		int  diffHr=0;
		diff = date1.getTimeStamp().getTime()-date2.getTimeStamp().getTime();
		if(diff!=0)
		{
			diffHr=(int)(diff/60000);
		}
		return diffHr;
	}
	
	public static int getDiffInMinute(Timestamp date1,Timestamp date2)
	{ 
		long diff=0;
		int  diffHr=0;
		diff = date1.getTime()-date2.getTime();
		if(diff!=0)
		{
			diffHr=(int)(diff/60000);
		}
		return diffHr;
	}
	
	public static DateTime getFinancialYearFirstDate(DateTime date)
	{ 
		int year = 0;
		if(date.getMM() < 4)
			year=date.getYYYY()-1;
		else
			year=date.getYYYY();
		return new DateTime(1,4,year);		
	}
	
	public static DateTime getFinancialYearLastDate(DateTime date)
	{ 
		int year = 0;
		if(date.getMM() > 3)
			year=date.getYYYY()+1;
		else
			year=date.getYYYY();
		return new DateTime(31,3,year);		
	}
	
	public static String getMonthName(int month)
	{
		String name = "";

		switch(month)
		{
			case 1: name = "Jan";
					break;
			case 2: name = "Feb";
					break;
			case 3: name = "Mar";
					break;
			case 4: name = "Apr";
					break;
			case 5: name = "May";
					break;
			case 6: name = "Jun";
					break;
			case 7: name = "Jul";
					break;
			case 8: name = "Aug";
					break;
			case 9: name = "Sep";
					break;
			case 10:name = "Oct";
			        break;
			case 11:name = "Nov";
			        break;
			case 12:name = "Dec";
	                break;
		}
		return name;
	
	}
	/* Ends */
	
	public String getDayOfWeek()
	{
		//String[] strDays = new String[]{ "Sunday","Monday","Tuesday","Wednesday","Thusday","Friday","Saturday"};
		String[] strDays = new String[]{ "SUN","MON","TUE","WED","THU","FRI","SAT"};
		// Day_OF_WEEK starts from 1 while array index starts from 0
		
		return strDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}
	/* Ends */
	
	public static String getHHMM(String mins)
	{
        if(!mins.equals("")){
		int temp1 = Integer.parseInt(mins);
		int temp=temp1%1440;
		if (temp >=60){
			int hh = temp / 60;
			int mm = temp % 60;
			String hhs = "", mms = "";
			if(hh<10) hhs="0"+hh; else hhs=""+hh;
			if(mm<10) mms="0"+mm; else mms=""+mm;
			mins = hhs+":"+mms;
		}else
		{
		if(temp<10)mins="00:0"+temp; else mins="00:"+temp;	
		}
        }
		return mins;
	}	
	
	public int getMinutes()
	{
		return (HH*60) + MI; 
		
	}

}
