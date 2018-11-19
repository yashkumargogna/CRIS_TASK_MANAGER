
/**
 * Method Desc : Trim leading or trailing whitespace/extra spaces  Optimised Function
 * 
 */
function trim(str)
{ 
    return (str.replace(/^\s*/, '').replace(/\s*$/, ''));
} 
/* Ends */

String.prototype.startsWith = function(str) 
{
	return (this.match("^"+str)==str)
} 
/* Ends */






/**
 * Method Desc : for Setting focus to next control 
 * 
 */
function tabNext(tabLength,cnt1, cnt2) 
{    
	var cntLength = cnt1.value.length;	
    if(tabLength == cntLength)
    {
       cnt2.focus();
    }   		
}
/* Ends */



/*######################################### AJAX ################################## STARTS ###########*/

/* to make an HttpRequest S */
function newXMLHttpRequest() 
{
		var xmlreq = false;

  		// Create XMLHttpRequest object in non-Microsoft browsers
  		if (window.XMLHttpRequest) 
  		{
    		xmlreq = new XMLHttpRequest();
  		} 
  		else if (window.ActiveXObject) 
  		{//else if S
    	try 
    	{
  			// Try to create XMLHttpRequest in later versions
  			// of Internet Explorer

  			xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
  
		} 
		catch (e1) 
		{//catch S

  			// Failed to create required ActiveXObject
  
  			try 
  			{
    			// Try version supported by older versions
    			// of Internet Explorer
  
    			xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

  			} 
  			catch (e2) 
  			{
    			// Unable to create an XMLHttpRequest by any means
       			xmlreq = false;
   			}
		}//catch E
    		
  		}//else if E

	return xmlreq;	
}
/* to make an HttpRequest E */

/* getReadyStateHandler S */
function getReadyStateHandler(req, responseXmlHandler) {

   // Return an anonymous function that listens to the XMLHttpRequest instance
   return function () {

     // If the request's status is "complete"
     if (req.readyState == 4) {
       
       // Check that we received a successful response from the server
       if (req.status == 200) {

         // Pass the XML payload of the response to the handler function.
            responseXmlHandler(req.responseXML);
          
       } else {

         // An HTTP problem has occurred
         alert("HTTP error "+req.status+": "+req.statusText);
       }
     }
   }
 }
/* getReadyStateHandler E */
	
/*######################################### AJAX ################################## ENDS ################################*/



/*######################################### DATE TIME ######################################### STARTS ###########*/

/**
 * Method Desc : Compare two dates
 *  
 *        IF (data1 > date 2)  				: true
 *      ELSE   
 *
 * @author dilip
 * Apr 02, 2008
 */
function compareTwoDates(date1,date2)
{
    //date1 23/12/2009 15:35
    var dateObj1 = new Date();
    dateObj1.setMilliseconds(0);
    dateObj1.setSeconds(0);
    dateObj1.setFullYear(date1.substring(6,10));
	dateObj1.setMonth(parseInt(date1.substring(3,5),10)-1);
	dateObj1.setDate(date1.substring(0,2));
	dateObj1.setHours(date1.substring(11,13));
	dateObj1.setMinutes(date1.substring(14,16));
      
    //date2 23/12/2009 12:30
    var dateObj2 = new Date();
    dateObj2.setMilliseconds(0);
    dateObj2.setSeconds(0);
    dateObj2.setFullYear(date2.substring(6,10));
	dateObj2.setMonth(parseInt(date2.substring(3,5),10)-1);
	dateObj2.setDate(date2.substring(0,2));
	dateObj2.setHours(date2.substring(11,13));
	dateObj2.setMinutes(date2.substring(14,16));
	
	//alert("1-->"+dateObj1);
	//alert("2-->"+dateObj2);
		
    if( parseInt(dateObj1 - dateObj2) >= 0)
    {
		 return true;     	
    }
    else 
    {
		 return false;     	
    }
}
/* Ends */


/**
 * Method Desc : Check where i/p two dates are within given date range or not
 * i/p date format: 
 * 
 *        IF (date1 >  date2)  	    : false
 *	 ELSE IF (date1 == date2)       : true   
 *   ELSE IF ((date1-date2) > range): false  
 *      ELSE   						: true	
 *
 * @author dilip
 * Apr 02, 2010
 */
function checkDateRange(date1,date2,range)
{
    if(trim(date1) == "")
	{
		alert("Please select From Date...");
		return false;
	}
	else if(trim(date2) == "")
	{
		alert("Please select To Date...");
		return false;
	}
    
    var one_day = 1000*60*60*24;
         
    //date1 01/12/2009 
    var dateObj1 = new Date();
    dateObj1.setMilliseconds(0);
    dateObj1.setSeconds(0);
    dateObj1.setFullYear(date1.substring(6,10));
	dateObj1.setMonth(parseInt(date1.substring(3,5),10)-1);
	dateObj1.setDate(date1.substring(0,2));
	  
    //date2 31/12/2010
    var dateObj2 = new Date();
    dateObj2.setMilliseconds(0);
    dateObj2.setSeconds(0);
    dateObj2.setFullYear(date2.substring(6,10));
	dateObj2.setMonth(parseInt(date2.substring(3,5),10)-1);
	dateObj2.setDate(date2.substring(0,2));
	
	//alert("1-->"+dateObj1);
	//alert("2-->"+dateObj2);
	
	var dayDiff = Math.ceil((dateObj2.getTime()-dateObj1.getTime())/(one_day));
	//alert(dayDiff);
	
	if(dayDiff < 0)
	{
		alert("From date is greater than To date......")
		return false;
	}
	else if(dayDiff == 0)
	{
		return true;
	}
	else if( (dayDiff+1) > range)
	{
		alert("Date range is exceeding limit of "+range+" days...");
		return false;
	}
	return true;
}
function checkDateRangeForMonthYear(date1,date2)
{
    if(trim(date1) == "")
	{
		alert("Please select From Date...");
		return false;
	}
	else if(trim(date2) == "")
	{
		alert("Please select To Date...");
		return false;
	}
    
    var one_day = 1000*60*60*24;
         
    //date1 01/12/2009 
    var dateObj1 = new Date();
    dateObj1.setMilliseconds(0);
    dateObj1.setSeconds(0);
    dateObj1.setFullYear(date1.substring(6,10));
	dateObj1.setMonth(parseInt(date1.substring(3,5),10)-1);
	dateObj1.setDate(date1.substring(0,2));
	  
    //date2 31/12/2010
    var dateObj2 = new Date();
    dateObj2.setMilliseconds(0);
    dateObj2.setSeconds(0);
    dateObj2.setFullYear(date2.substring(6,10));
	dateObj2.setMonth(parseInt(date2.substring(3,5),10)-1);
	dateObj2.setDate(date2.substring(0,2));
	
	//alert("1-->"+dateObj1);
	//alert("2-->"+dateObj2);
	
	var dayDiff = Math.ceil((dateObj2.getTime()-dateObj1.getTime())/(one_day));
	//alert(dayDiff);
	
	if(dayDiff < 0)
	{
		alert("From date is greater than To date......")
		return false;
	}
	else if(dayDiff == 0)
	{
		return true;
	}
	
	else if(date1.substring(6,10)!=date2.substring(6,10))
	{  
		alert("Date Range Should be of Same Year and Month.")
		return false;
	}
	else if(date1.substring(3,5)!=date2.substring(3,5))
	{
		alert("Date Range Should be of Same Month of a Year.")
		return false;
	}
	return true;
}
/* Ends */





/*######################################### DATE TIME ######################################### ENDS #############*/




/*######################################### ARRAY FUNCTIONS ################################## STARTS ###########*/
/*######################################### ARRAY FUNCTIONS ################################## ENDS #############*/





