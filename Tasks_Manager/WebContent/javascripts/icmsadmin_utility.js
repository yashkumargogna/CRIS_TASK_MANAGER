//report utility

var myChild = 0;

function openNewWindow(queryStr,winName,strSuffix)
{
	var childWinID = winName + removeSpecialChars(strSuffix);
	
	
	fnPleaseWait();
	
	if(myChild > 0) 
	{
		if (newwindow && newwindow.open && !newwindow.closed && (newwindow.name == winName)) 
		{
			newwindow.close(); 
			myChild = 0;
		}
	}
	myChild++;
	
	
	newwindow = window.open(queryStr,childWinID,'top=100,left=100,scrollbars=yes,resizable=yes,status=yes');
	newwindow.moveTo(100,100);

	fnHideWait(); 

	if (window.focus) 
		newwindow.focus();
	
	//return false;	
}
/* Ends */

function openNewWindowSPL(frm,queryStr,winName,strSuffix)
{
	var childWinID = winName + removeSpecialChars(strSuffix);
	
	fnPleaseWait();
	
	if(myChild > 0) 
	{
		if (newwindow && newwindow.open && !newwindow.closed && (newwindow.name == winName)) 
		{
			newwindow.close(); 
			myChild = 0;
		}
	}
	myChild++;
	
	frm.action = queryStr;
	newwindow = window.open("",childWinID,'top=100,left=100,scrollbars=yes,resizable=yes,status=yes');
	var set_timeout1 = setTimeout("document."+frm.name+".target='"+childWinID+"';",100);
	var set_timeout2 = setTimeout("document."+frm.name+".submit();",200);

	fnHideWait(); 

	if (window.focus) 
		newwindow.focus();
	
	//frm.target = "_parent";
	//alert('ggg')
	//return false;	
}
/* Ends */

/**
 * Method Desc :  Remove special characters from a string. 
 * 
 */
function removeSpecialChars(inputStr) 
{
	var returnStr = "";//alert(inputStr)
	
	if(isNaN(inputStr))
	{
		//pattern = /\$|,| |@|#|~|`|\%|\*|\^|\&|\(|\)|\+|\=|\[|\-|\_|\]|\[|\}|\{|\;|\:|\'|\"|\<|\>|\?|\||\/\\|\!|\$|\./g;
        // remove special characters like "$" and "," etc...
		//alert(inputStr.replace(pattern, ""))
		returnStr = inputStr.replace(/\s/g,'');
		
		returnStr = returnStr.replace(/[^a-zA-Z 0-9]+/g,'');
		//alert(returnStr)
	}
	else
	{
		returnStr = inputStr;
	}
	return returnStr;
}
/* Ends */


function submitForm(frm,queryStr,waitCnt)
{
	lockBackContent();
	fnPleaseWait(waitCnt); 
	frm.action = queryStr;
	frm.submit();
}
/* Ends */

function printFn()
{
	showBackContent();
	
	if(document.getElementById('headPrint') != null)
	{
		document.getElementById('headPrint').style.display = 'none';
	}
	
	if(document.getElementById('headReport') != null)
	{
		document.getElementById('headReport').style.display = 'none';
	}
	
	if(document.getElementById('divReportBottom') != null)
	{
		document.getElementById('divReportBottom').style.display = 'none';
	}
	if(document.getElementById('floatingHeader') != null)
	{
		document.getElementById('floatingHeader').style.display = 'none';
	}
	if(document.getElementById('splHeadReport') != null)
	{
		document.getElementById('splHeadReport').style.display = 'none';
	}
	if(document.getElementById('divRptBottom') != null)
	{
		document.getElementById('divRptBottom').style.display = 'none';
		document.getElementById('spfootspace').style.display = 'none';
	}
	if(document.getElementById('spfootspace') != null)
	{
		document.getElementById('spfootspace').style.display = 'none';
	}
	
	print();

	if(document.getElementById('headPrint') != null)
	{
		document.getElementById('headPrint').style.display = 'block';
	}
	
	if(document.getElementById('headReport') != null)
	{
		document.getElementById('headReport').style.display = 'block';
	}
	
	if(document.getElementById('divReportBottom') != null)
	{
		document.getElementById('divReportBottom').style.display = 'block';
	}
	if(document.getElementById('floatingHeader') != null)
	{
		document.getElementById('floatingHeader').style.display = 'block';
	}
	if(document.getElementById('divRptBottom') != null)
	{
		document.getElementById('divRptBottom').style.display = 'block';
	}
	if(document.getElementById('spfootspace') != null)
	{
		document.getElementById('spfootspace').style.display = 'block';
	}
	
}
/* Ends */

/*function switchStyleSheet(title,doc) 
{
	if(title != 'A4')
	{
		var c,i,t;
	
		if(!(c = doc.styleSheets)) 
		{
			return;
		}
	
		i = c.length;
		while(i--) 
		{
			if((t = c[i].title)) 
			{
				c[i].disabled = (title != t);
			}
		}
		printFn();
	
		i = c.length;
		title = 'A4';
		while(i--) 
		{
			if((t = c[i].title)) 
			{
				c[i].disabled = (title != t);
			}
		}
	}
	else
	{
		printFn();
	}
}
 Ends */

function switchStyleSheet(title,doc) 
{
	printFn();
}
/* Ends */


function setTRBackGround(obj) 
{
	obj.style.backgroundColor = "#CAF2F2"; //RGB
}
/* Ends */

function remTRBackGround(obj) 
{
	obj.style.backgroundColor = "";
}
/* Ends */

function toggleRowBgColor(elem)
{
	var style2 = elem.style;
    style2.backgroundColor = style2.backgroundColor? "":"#FFFF66";
}
/* Ends */


/*
include this file in each & every page u want to show please wait 
simply call this function from your code

copy paste following line of code inside <BODY> of your page

<!-- Please Wait include -->
include file="include/pleaseWait.html" 

function call : fnPleaseWait();
*/

var timerCount = 0;

function incrementTimer(timerCnt) 
{
	if(document.getElementById("timerCnt"))
		obj= document.getElementById("timerCnt");
	//else if(parent.detail.document.getElementById("timerCnt"))	
		//obj = parent.detail.document.getElementById("timerCnt");
	
	if(obj)
	{	
		if(isNaN(timerCount))
		{
			obj.innerHTML = "";
		}
		else
		{
			if(timerCount == 0) { timerCount = 10; }
			obj.innerHTML = timerCount;
		}
		timerCount--;
	}	
}
/* Ends */



function fnPleaseWait(inputTimerCnt) 
{
	var obj;
	if(document.getElementById("splashScreen"))
	{
		obj= document.getElementById("splashScreen");
	}
	/*else if(parent.detail.document.getElementById("splashScreen"))	
	{
		//obj = parent.detail.document.getElementById("splashScreen");
	}*/
	
	if(obj)
	{
		obj.style.visibility = "visible";
		
		timerCount = inputTimerCnt;
		setInterval("incrementTimer()",1000);
		incrementTimer();
	}
}
/* Ends */

function fnHideWait() 
{
	if(document.getElementById("splashScreen"))
	{
		var obj= document.getElementById("splashScreen");		
		obj.style.visibility = "hidden";
	}
}
/* Ends */

function showObject(obj) 
{
	obj.style.visibility = "visible";
}

function hideObject(obj) 
{
	obj.style.visibility = "hidden";
}
/* Ends */

function showIcmsadminHome()
{
	parent.location.href = "IcmsadminServlet?action=IcmsadminHome";
	
}
/* Ends */

function showPRSHistory(trainNo,startDate)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=PRSHistory&subAction=showDetail&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTRPHC',trainNo+startDate);
}
/* Ends */

function showTrainConsist(trainNo,startDate,event)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainConsist&subAction=show&trainNo="+trainNo+"&startDate="+startDate+"&event="+event;
	openNewWindow(queryStr,'winTRC',trainNo+startDate+event);
}
/* Ends */

function showTrainStandardConsistById(idTrainDef,trainNo,dayOfRun,event)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainStandardConsist&subAction=showConsist&idTrainDef="+idTrainDef+"&trainNo="+trainNo+"&dayOfRun="+dayOfRun+"&event="+event;
	openNewWindow(queryStr,'winTRSCE',idTrainDef+trainNo+dayOfRun+event);
}
/* Ends */
function showTrainStandardConsistByNo(trainNo,startDate,event)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainStandardConsist&subAction=showConsist&trainNo="+trainNo+"&startDate="+startDate+"&event="+event;
	openNewWindow(queryStr,'winTRSCE',trainNo+startDate+event);
}
/* Ends */

function showTrainStandardConsistByConsistNo(idTrainDef,consistNo)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainStandardConsist&subAction=showConsist&idTrainDef="+idTrainDef+"&consistNo="+consistNo;
	openNewWindow(queryStr,'winTRSCE',idTrainDef+consistNo);
}
/* Ends */

function showTrainSpeedBreakUp(idTrainDef,trainNo,reportLevel)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainSpeedBreakUp&subAction=showDetail&idTrainDef="+idTrainDef+"&trainNo="+trainNo+"&reportLevel="+reportLevel;
	openNewWindow(queryStr,'winTSBU',idTrainDef);
}
/* Ends */
function showTrainSchActSpeedBreakUp(trainNo,startDate)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainSpeedBreakUp&subAction=showSchActDetail&&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTSSACTBU',trainNo+startDate);
}
/* Ends */

function showTrainConsistHistory(trainNo,eventType)
{
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainConsistHistory&subAction=show&trainNo="+trainNo+"&eventType="+eventType;
	openNewWindow(queryStr,'winTCHHH',trainNo+eventType);
}
/* Ends */

function showTrainScheduleDistanceMismatch(idTrainDef,trainNo)
{
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=ScheduleDistanceMismatch&subAction=showDetail&idTrainDef="+idTrainDef+"&trainNo="+trainNo;
	openNewWindow(queryStr,'winTCHHH',idTrainDef+trainNo);
}
/* Ends */

function showRakeHistory(trainNo,startDate)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=RakeHistory&subAction=showDetail&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTRC',trainNo+startDate);
}
/* Ends */

function showRakeReversalLog(trainNo,startDate)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainRakeReversalLog&subAction=show&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTRC',trainNo+startDate);
}
/* Ends */

function showUserProfile(userID)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=General&type=UserSearch&subAction=searchByID&userID="+userID;
	openNewWindow(queryStr,'winUSRPRF',userID);
}
/* Ends */


function showTrainProfile(trainNo)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=TrainProfile&subAction=showDetail&trainNo="+trainNo;
	openNewWindow(queryStr,'winTPRF',trainNo);
}
/* Ends */

function showRakeLink(idRakeLink)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=RakeLink&subAction=showRakeLink&idRakeLink="+idRakeLink;
	openNewWindow(queryStr,'winRAKELINK',idRakeLink);
}
/* Ends */

function showRakeLinkBareReq(idRakeLink,rakeLinkBookletNo)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=RakeLinkBareReq&subAction=show&idRakeLink="+idRakeLink+"&rakeLinkBookletNo="+rakeLinkBookletNo;
	openNewWindow(queryStr,'winRAKELINKBARE',idRakeLink);
}
/* Ends */

function showTrainRunning(trainNo,startDate,caseType)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=TrainRunningPosition&subAction=showRunnPosition&trainNo="+trainNo+"&startDate="+startDate+"&caseType="+caseType;
	openNewWindow(queryStr,'winTRNRUNN',trainNo+startDate+caseType);
}
/* Ends */

function showTrainRunningWithDetention(trainNo,startDate)
{
	var queryStr = "ReportServlet?reportAction=Utility&reportType=TrainRunningPosition&subAction=showWithDetention&trainNo="+trainNo+"&startDate="+startDate;
    openNewWindow(queryStr,'winTRWITHDTE',trainNo+startDate);
}
/* Ends */

function showTrainDivertedRouteDetail(trainNo,startDate)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=TrainDivertedRouteDetail&subAction=show&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTDRDTL',trainNo+startDate);
}
/* Ends */

function showTrainDeLogging(trainNo,trainStartDate,zone)
{	
	var queryStr = "ReportServletPam?action=report&reportAction=DeLoggingHistory&subAction=drill&trainNo="+trainNo+"&trainStartDate="+trainStartDate+"&zone="+zone;
	openNewWindow(queryStr,'winTRNRUNN',trainNo+trainStartDate+zone);
}
/* Ends */

function showTrainStatusData(trainNo,startDate)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=TrainRunningStatusData&subAction=show&trainNo="+trainNo+"&startDate="+startDate;
	openNewWindow(queryStr,'winTRNRUNN',trainNo+startDate);
}
/* Ends */

function showSentToCOAHistory(trainNo)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=SentToCOAHistory&subAction=show&trainNo="+trainNo;
	openNewWindow(queryStr,'winTRNRUNN',trainNo);
}
/* Ends */


function searchUserFeedback(FBID)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=SearchFeedback&subAction=show&feedbackID="+FBID;
	openNewWindow(queryStr,'winSRCFBID',FBID);
}
/* Ends */

function showLocoTypePhoto(locoType)
{	
	var queryStr = "LocoTypePhoto.jsp?locoType="+locoType;
	openNewWindow(queryStr,'winLTTPH',locoType);
}
/* Ends */

function showCoachTypeDetail(coachType)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachTypes&subAction=coachTypeDetail&coachType="+coachType;	
	openNewWindow(queryStr,'wCHTYDTL',coachType);
}
/* Ends */

function showCoachStatus(ownRly,gauge,coachType,coachNo)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachStatus&subAction=show&owningRly="+ownRly+"&gauge="+gauge+"&coachType="+coachType+"&coachNo="+coachNo;
	openNewWindow(queryStr,'winCHSTS',ownRly+gauge+coachType+coachNo);
}
/* Ends */
function showCoachStatusByID(coachID)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachStatus&subAction=show&coachID="+coachID;
	openNewWindow(queryStr,'winCHSTS',coachID);
}
/* Ends */

function showCoachProfileByID(coachID)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachProfile&subAction=show&coachID="+coachID;
	openNewWindow(queryStr,'winCHPRF',coachID);
}
/* Ends */

function showCoachProfile(ownRly,gauge,coachType,coachNo)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachProfile&subAction=show&owningRly="+ownRly+"&gauge="+gauge+"&coachType="+coachType+"&coachNo="+coachNo;
	openNewWindow(queryStr,'winCHPRF',ownRly+gauge+coachType+coachNo);
}
/* Ends */

function showCoachHistoryByID(coachID)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=CoachHistory&subAction=show&coachID="+coachID;
	openNewWindow(queryStr,'winCHHSRT',coachID);
}
/* Ends */

function showLocoStatus(locoNo)
{	
	var queryStr = "ReportServlet?reportAction=Utility&reportType=LocoCurrStatus&subAction=showDtl&locoNo="+locoNo;
	openNewWindow(queryStr,'winLCST',locoNo);
}
/* Ends */

function showStationProfile(stationCode)
{	
	var queryStr = "ReportServlet?action=report&reportAction=Utility&reportType=General&type=StationSearch&subAction=showProfile&stationCode="+stationCode;
	openNewWindow(queryStr,'winUSRPRF',stationCode);
}
/* Ends */

function showZoneProfile(zoneCode)
{	
	var queryStr = "/icms_help/common/zone_help/"+zoneCode+".html";
	
	winZone = window.open(queryStr,'winZoneProfile','top=100,left=100,scrollbars=yes,resizable=yes,status=yes');
}
/* Ends */

function showAssetFailureDetentionCodes()
{	
	var queryStr = "ReportServlet?icmsReportNo=312&reportAction=Utility&reportType=AssetFailureDetentionCode";
	openNewWindow(queryStr,'winAFDCGE','');
}
/* Ends */



function getGaugeValue(frm)
{
	var gauge = "ALL";
	
	for(var i=0;i<frm.gauge.length;i++)
	{
		if(frm.gauge[i].checked)
		{
			gauge = frm.gauge[i].value;
			break;
		}
	}
	return gauge;
}	
/* Ends */

function resetGauge(frm)
{
	frm.gauge[0].checked = false;
	frm.gauge[1].checked = false;
	frm.gauge[2].checked = false;
}
/* Ends */


function onTrainType(frm,trainGroup)
{
	
	for(var i=0;i<frm.trainTypeGroup.length;i++)
	{
		if(frm.trainTypeGroup[i].value == trainGroup)
		{
			frm.trainTypeGroup[i].checked = true;
			break;
		}
	}
}

function onTrainMultiType(frm,o,indx)
{
    var preStat= o.checked;
   
    if(!frm.trainTypeGroup[indx].checked) {
		for(var i=0;i<frm.trainType.length;i++)
	{
		frm.trainType[i].checked = false;
	}
	}
	frm.trainTypeGroup[indx].checked = true;
	o.checked = preStat;
}
/* Ends */

function onTrainGroupType(frm)
{
	for(var i=0;i<frm.trainType.length;i++)
	{
		frm.trainType[i].checked = false;
	}
}

function onTrainGroupAnyTypeM(frm)
{ 
	for(var i=0;i<frm.trainTypeM.length;i++)
	{
		 frm.trainTypeM[i].checked = frm.trainTypeGroupM.checked;
	}
}

function onTrainGroupAnyTypeP(frm)
{ 
	for(var i=0;i<frm.trainTypeP.length;i++)
	{
		 frm.trainTypeP[i].checked = frm.trainTypeGroupP.checked;
	}
}

function onTrainGroupAnyTypeS(frm)
{ 
	
		 frm.trainTypeS.checked = frm.trainTypeGroupS.checked;
	
}
/* Ends */

function getTrainTypeGroup(frm)
{
	var valTrainTypeGroup = "";
	
	for(var i=0;i<frm.trainTypeGroup.length;i++)
	{
		if(frm.trainTypeGroup[i].checked)
		{
			valTrainTypeGroup = frm.trainTypeGroup[i].value;
			break;
		}
	}
	return valTrainTypeGroup;
}
/* Ends */

function getTrainType(frm)
{
	var valTrainType = "";
	
	for(var i=0;i<frm.trainType.length;i++)
	{
		if(frm.trainType[i].checked)
		{
			valTrainType = frm.trainType[i].value;
			break;
		}
	}
	return valTrainType;
}
/* Ends */

function getOPTIValue(frm)
{
	var valOPTI = "";
	
	for(var i=0;i<frm.optiType.length;i++)
	{
		if(frm.optiType[i].checked)
		{
			valOPTI = frm.optiType[i].value;
			break;
		}
	}
	return valOPTI;
}
/* Ends */

function getViewCount(reportNo,reportView)
{
	if(reportView == "M")
	{
		httpRequest = newXMLHttpRequest();
		var queryString = "ReportServlet?action=report&reportAction=Utility&reportType=ReportHitCount&reportNo="+reportNo;
		httpRequest.open("POST",queryString,false);
	  	httpRequest.send(null);
	  	
	  	if (httpRequest.readyState == 4 && httpRequest.status == 200)
		{
			if( (httpRequest.responseText != "") && document.getElementById("divViewCnt"))
			{
				document.getElementById("divViewCnt").innerHTML = "<span class=blueRow12NC>Viewed <b>"+httpRequest.responseText+"</b> times</span>";
			}	
		}
		else if ( (httpRequest.readyState != 0) && (httpRequest.readyState != 1) && (httpRequest.readyState != 2) && (httpRequest.readyState != 3) )
		{
			//alert("Your Browser does not Supprot AJAX.........")
		}
	}
}
/* Ends */

function CreateExcelSheet()
{
	var strCopy = "";
	
	if(document.getElementById("divReportContent"))
	{
		try
		{
			//strCopy = document.getElementById("divReportContent").innerHTML;
			//window.clipboardData.setData("Text", strCopy);
			//CopyHTMLToClipboard();
			
			//fnPleaseWait(20); 
			
			var htmlContent = document.getElementById('divReportContent');  
	        var controlRange;     
	        var range = document.body.createTextRange(); 
	        range.moveToElementText(htmlContent); 
	        //Uncomment the next line if you don't want the text in the div to be selected         
	        //range.select(); 
	        //controlRange = document.body.createControlRange();  
	        //controlRange.addElement(htmlContent);            //This line will copy the formatted text to the clipboard            
	        range.execCommand('Copy');                       
	        //alert('Your HTML has been copied\n\r\n\rGo to Word and press Ctrl+V');       
			
			
			var objExcel = new ActiveXObject ("Excel.Application");
			objExcel.visible = true;
		
			var objWorkbook = objExcel.Workbooks.Add;
			var objWorksheet = objWorkbook.Worksheets(1);
			objWorksheet.Paste;
			
			//range.select();
			
			//hideObject();
		}
		catch(ex)
		{
			window.open('/icms_help/reports_help/export_to_excel_help.htm');
		}
	}
	else
	{ 
		alert("Export to excel option for this report will be provided soon !!!");
	}
}	
/* Ends */



function CreateExcelSheet111()
{
	var strCopy = "";
	
	if(document.getElementById("divReportContent"))
	{
		try
		{
			 
			
	        
	        
			var wordApp = new ActiveXObject ("Word.Application");
	        wordApp.Visible = true;
	        var doc = wordApp.Documents.Add();
	        
	        var sel = wordApp.Selection;
	        sel.TypeText(strCopy);

	        
	        
	        //wordApp.Paste;
	        //var sel = wordApp.Selection;
	        //wordApp.Paste;
	        sel.TypeText(wordApp.Paste);
		}
		catch(ex)
		{
			window.open('/icms_help/reports_help/export_to_excel_help.htm');
		}
	}	
}
/* Ends */

/*
function CreateExcelSheet()
{
	var strCopy = "";
	
	if(document.getElementById("divReportContent"))
	{
		strCopy = document.getElementById("divReportContent").innerHTML;
		window.clipboardData.setData("Text", strCopy);
		var objExcel = new ActiveXObject ("Excel.Application");
		objExcel.visible = true;
	
		var objWorkbook = objExcel.Workbooks.Add;
		var objWorksheet = objWorkbook.Worksheets(1);
		objWorksheet.Paste;
	}	
}
/* Ends */

function copyReportContent() 
{	 	
	if(document.getElementById("divReportContent"))
	{
		var htmlContent = document.getElementById('divReportContent');  
	    var range = document.body.createTextRange(); 
	    range.moveToElementText(htmlContent); 
	    range.execCommand('Copy');
	    
	    if(document.getElementById("divSuccessCopy"))
	    {	
	    	showObject(document.getElementById("divSuccessCopy"));
	    	hideBackContent();
	    	window.setTimeout("hideObject(document.getElementById(\"divSuccessCopy\"));showBackContent();",2000);
	    }
	}
}
/* Ends */

function hideBackContent()
{
	var blurDiv = document.createElement("div"); 
	blurDiv.id = "blurDiv"; 
	blurDiv.style.cssText = "position:absolute; top:0; right:0; width:" + screen.width + "px; height:" + screen.height + "px; background-color: #000000; opacity:0.5; filter:alpha(opacity=50)"; 
  
	document.getElementsByTagName("body")[0].appendChild(blurDiv); 
}
/* Ends */

function lockBackContent()
{
	
	if(document.getElementById("blurDiv"))
	{
		var item = document.getElementById("divReportContent");
		item.removeChild(document.getElementById("blurDiv"));
	}
	
	if(document.getElementById("divReportContent"))
	{	
		var blurDiv = document.createElement("div"); 
		blurDiv.id = "blurDiv";
		
		//alert('www-->'+document.getElementById("divReportContent").offsetWidth);
		//alert('hhh-->'+document.getElementById("divReportContent").offsetHeight);
		
		var wid = document.getElementById("divReportContent").offsetWidth-5;
		
		//blurDiv.style.cssText = "position:absolute; width:" + wid+ "px; height:" + document.getElementById("divReportContent").offsetHeight + "px; background-color: #F8F8F8; opacity:0.4;";
		blurDiv.style.cssText = "position:absolute; left:0px;right:0px; height:" + document.getElementById("divReportContent").offsetHeight + "px; background-color: #000000; opacity:.10; filter:alpha(opacity=10)";
		//blurDiv.style.cssText = "position:absolute; width:" + wid+ "px; height:" + document.getElementById("divReportContent").offsetHeight + "px;";
		if(document.getElementById("divReportContent").childNodes.length > 0)
		{
			document.getElementById("divReportContent").insertBefore(blurDiv, document.getElementById("divReportContent").childNodes[0]);
		}
		
		$(blurDiv).on("click",function() {
			alert('Click on "Display"');
		});
	}
}
/* Ends */

function showBackContent()
{
	if(document.getElementById("blurDiv"))
	{	 
		var blurDiv = document.getElementById("blurDiv");	
		blurDiv.parentNode.removeChild(blurDiv);
	}	
}
/* Ends */

function showCris25()
{
	if(!document.getElementById("dvCris25"))
	{	
		var dv = document.createElement("div");
		dv.id = "dvCris25";
		document.getElementsByTagName("body")[0].appendChild(dv); 
		dv.innerHTML = "<img src=\"images/cris_25_years_big.jpg\" width=\"680\" height=\"324\" border=0>";
		dv.style.cssText = "position:absolute; top:100; left:" + (screen.width/4) + "px;"; 
		dv.style.visibility = "visible"; 
	}
	else
	{
		document.getElementById("dvCris25").style.visibility = "visible"; 
	}	
}
/* Ends */

function hideCris25()
{
	if(document.getElementById("dvCris25"))
	{	
		document.getElementById("dvCris25").style.visibility = "hidden"; 
	}
}	
/* Ends */
