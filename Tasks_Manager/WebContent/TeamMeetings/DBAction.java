/*
 * Created on Jan 6, 2010
 *
 */
package icmsadmin.war;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import icmsadmin.DAO.DBDAO;
import icmsadmin.DAO.ServerShellScript;
import icmsadmin.DAO.TeamMeetingsDAO;
import icmsadmin.DTO.TeamMeetingsDTO;
import icmsadmin.common.DateTime;
import icmsadmin.common.IcmsadminFilter;
import icmsadmin.common.IcmsadminMessage;




/**
 * author dilip 
 */
public class DBAction 
{
	
	public static void getDBAction(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws Exception,ServletException, IOException 
    {
		String subAction = req.getParameter("subAction");
	 	
		if (subAction.compareTo("ProcedureLogCOIS") == 0)
		{
			getProcedureLogCOIS(session,req,resp,sc);
		}
		else if (subAction.compareTo("ProcedureLogCOISLinks") == 0)
		{
			getProcedureLogCOISLinks(session,req,resp,sc);
		}
		else if (subAction.compareTo("ProcedureLogCOISLinks2") == 0)
		{
			getProcedureLogCOISLinks2(session,req,resp,sc);
		}
		/*else if (subAction.compareTo("ProcedureLogCOIS_OLAP") == 0)
		{
			getProcedureLogCOIS_OLAP(session,req,resp,sc);
		}*/
		else if (subAction.compareTo("ProcedureLogPAM") == 0)
		{
			getProcedureLogPAM(session,req,resp,sc);
		}
		/*else if (subAction.compareTo("ProcedureLogPAMOLAP") == 0)
		{
			getProcedureLogPAMOLAP(session,req,resp,sc);
		}*/
		

		else if (subAction.compareTo("DBBrowser") == 0)
		{
			getDBBrowser(session,req,resp,sc);
		}
		
		else if (subAction.compareTo("DBdiskspace") == 0)
		{
			getDBdiskspace(session,req,resp,sc);
		}
		else if (subAction.compareTo("TableSpaceGrowth") == 0)
		{
			getTableSpaceGrowth(session,req,resp,sc);
		}
		/*else if (subAction.compareTo("TableSpaceGrowthOLAP") == 0)
		{
			getTableSpaceGrowthOLAP(session,req,resp,sc);
		}*/
		else if (subAction.compareTo("DBBackup") == 0)
		{
			getDBBackup(session,req,resp,sc);
		}
		else if (subAction.compareTo("RMANBackup") == 0)
		{
			getRMANBackup(session,req,resp,sc);
		}
		else if (subAction.compareTo("PurgingStatus") == 0)
		{
			getPurgingStatus(session,req,resp,sc);
		}
		//TablePartitionCheck
		
		else if (subAction.compareTo("TablePartitionCheck") == 0)
		{
			getTablePartitionCheck(session,req,resp,sc);
		}
		
		
		else if (subAction.compareTo("TrainRakeReversalPoints") == 0)
		{
			getTrainRakeReversalPoints(session,req,resp,sc);
		}
		
		else if (subAction.compareTo("RunningPosToPRS") == 0)
		{
			getRunningPosToPRS(session,req,resp,sc);
		}
		
		if (subAction.compareTo("DBAlertLog") == 0)
		{
			getDBAlertLog(session,req,resp,sc);
		}
		
		// Editted BY Indrajeet
		else if (subAction.compareTo("DBDiskCronJobsLogs") == 0)
		{
			getDBDiskCronJobs(session,req,resp,sc);
		}
		// Editted BY Indrajeet
		
		
		else if (subAction.compareTo("procedureSrc") == 0)
		{
			procedureSrc(session,req,resp,sc);
		}
		
	}
	/* Ends */
	
	
	
	public static void getProcedureLogCOIS(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("show"))
			{	
				Vector vProcCOIS = DBDAO.gerProcedureLogCOIS(hf, errmsg);	 		
				req.setAttribute("vProcCOIS",vProcCOIS);	
				hm.setReqDispatchPage("dbchecks/procedureCOIS.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				String strbuf1 = ServerShellScript.procedureServerLogCOIS(req.getParameter("logtype"),req.getParameter("ip"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	
	//DBAlertLog
	
	
	public static void getDBAlertLog(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();

		try
		{
			String event = req.getParameter("event");
			
			if(event.equals("drillLogs"))
			{	
				String strbuf1 = ServerShellScript.DBAlertLog(req.getParameter("ip"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}
	
	
	
	
	
	
	
	
	public static void getProcedureLogCOISLinks(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();

		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("main"))
			{	

				hm.setReqDispatchPage("dbchecks/ProcedureLink.jsp");
			}
			

			if(event.equals("show"))
			{	
				String strbuf1 = ServerShellScript.procedureServerLogCOISLink(req.getParameter("logtype"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
	
	
	
	
	
	
	
	public static void getProcedureLogCOISLinks2(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();

		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("main"))
			{	

				hm.setReqDispatchPage("dbchecks/ProcedureLink2.jsp");
			}
			

			if(event.equals("show"))
			{	
				String strbuf1 = ServerShellScript.procedureServerLogCOISLink2(req.getParameter("logtype"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
	
	
	
	
	
	
	
	/*public static void getProcedureLogCOIS_OLAP(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();

		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("show"))
			{	
				Vector vProcCOIS = DBDAO.gerProcedureLogCOIS_OLAP(hf);	 		
				req.setAttribute("vProcCOIS",vProcCOIS);	
				hm.setReqDispatchPage("dbchecks/procedureCOIS.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				String strbuf1 = ServerShellScript.procedureServerLogCOIS(req.getParameter("logtype"),req.getParameter("ip"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}*/
	
	
	public static void getProcedureLogPAM(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("show"))
			{	
				Vector vRecordList = DBDAO.getProcedureLogPAM(hf,errmsg);		
				req.setAttribute("vRecordList",vRecordList);	
				
				hm.setReqDispatchPage("dbchecks/procedurePAM.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				
				
				String strbuf1 = ServerShellScript.procedureServerLogPAM(req.getParameter("logtype"),req.getParameter("ip"));
				
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
	
	/*public static void getProcedureLogPAMOLAP(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		IcmsadminFilter hf = new IcmsadminFilter();

		try
		{
			String event = req.getParameter("event");
			
			hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
			
			if(event.equals("show"))
			{	
				Vector vRecordList = DBDAO.getProcedureLogPAMOLAP(hf);		
				req.setAttribute("vRecordList",vRecordList);	
				
				hm.setReqDispatchPage("dbchecks/procedurePAM.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				String strbuf1 = ServerShellScript.procedureServerLogPAM(req.getParameter("logtype"),req.getParameter("ip"));
				req.setAttribute("strbuf1",strbuf1);
				hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}

			req.setAttribute("IcmsadminFilter", hf);
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}
     Ends */
	
	public static void getDBdiskspace(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			Vector vDBspace = DBDAO.getDBdiskSpace(errmsg);
			req.setAttribute("vDBspace",vDBspace);	
			iam.setReqDispatchPage("dbchecks/DBdiskSpace.jsp");

		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	public static void getTableSpaceGrowth(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		final Vector<String> errmsg =new Vector<String>();
		
		try
		{
			Vector vTableGrowth   = DBDAO.getTableSpaceGrowth(errmsg);
			req.setAttribute("vTableGrowth",vTableGrowth);	
			iam.setReqDispatchPage("dbchecks/TableSpaceGrowth.jsp");

		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
	
	
	/*public static void getTableSpaceGrowthOLAP(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		
		try
		{
			
			Vector vTableGrowth   = DBDAO.getTableSpaceGrowthOLAP();
			req.setAttribute("vTableGrowth",vTableGrowth);	
			iam.setReqDispatchPage("dbchecks/TableSpaceGrowth.jsp");

		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}*/
	
	
	
	
    /* Ends */
	
	public static void getDBBackup(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			String event = req.getParameter("event");
			
			if(event.equals("show"))
			{
				Vector vDBbackup   = DBDAO.getDBBackup(errmsg);
				req.setAttribute("vDBbackup",vDBbackup);
				
				iam.setReqDispatchPage("dbchecks/DBbackup.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				String strbuf1= ServerShellScript.getDBBackupLogs();
				req.setAttribute("strbuf1",strbuf1);
					
				iam.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}
		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	public static void getRMANBackup(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage hm = new IcmsadminMessage();	
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			Vector vRMANBackup = DBDAO.getRMANBackup(errmsg);
			req.setAttribute("vRMANBackup",vRMANBackup);

			hm.setReqDispatchPage("dbchecks/RMANBackup.jsp");
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	public static void getPurgingStatus(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			String event = req.getParameter("event");
			
			if(event.equals("show"))
			{
				Vector vPurge   = DBDAO.getPurgingStatus(errmsg);
				req.setAttribute("vPurge",vPurge);
				
				iam.setReqDispatchPage("dbchecks/PurgingStatus.jsp");
			}
			else if(event.equals("drill"))
			{	
				Vector vPurgeDrill   = DBDAO.getPurgingStatusDrill();
				req.setAttribute("vPurgeDrill",vPurgeDrill);
					
				iam.setReqDispatchPage("dbchecks/PurgingStatusDrill.jsp");
			}
			else if(event.equals("drillLogs"))
			{	
				String strbuf1= ServerShellScript.getAcualPurgingLog();
				req.setAttribute("strbuf1",strbuf1);
					
				iam.setReqDispatchPage("commonchecks/ServerLog.jsp");
			}
		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	//TablePartitionCheck
	
	
	public static void getTablePartitionCheck(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	

		try
		{
			String event = req.getParameter("event");
			
			if(event.equals("show"))
			{
				Vector vTableSpace   = DBDAO.getTablePartitionCheck();
				req.setAttribute("vTableSpace",vTableSpace);				
				iam.setReqDispatchPage("dbchecks/TablePartition.jsp");
			}

		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
	
	public static void getTrainRakeReversalPoints(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws Exception 
	{
		IcmsadminMessage hm = new IcmsadminMessage();

		try
		{
			Vector vrakeReversal   = DBDAO.getTrainRakeReversalPoints();
			req.setAttribute("vrakeReversal",vrakeReversal);
			hm.setReqDispatchPage("dbchecks/TrainRakeReversalPoints.jsp");
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	
	
	
	public static void getRunningPosToPRS(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws Exception 
	{
		IcmsadminMessage hm = new IcmsadminMessage();
		final Vector<String> errmsg =new Vector<String>();
		try
		{
			//int count=2;
			int count   = DBDAO.getPRSRunningPOS(errmsg);	
			Vector vExceptionPRS   = DBDAO.getExceptionToPRS(errmsg);
			req.setAttribute("vExceptionPRS",vExceptionPRS);

			req.setAttribute("count",count);
			hm.setReqDispatchPage("dbchecks/RunningPosPRS.jsp");
		}
		catch(Exception e) // exception
		{  
			hm = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
			hm.setReqDispatchPage("error.jsp");
		}
		req.setAttribute("errmsg",errmsg);
		Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
		return;		
	}
	/* Ends */
	
	public static void getDBBrowser(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
	{
		IcmsadminMessage iam = new IcmsadminMessage();	
		IcmsadminFilter iaf = new IcmsadminFilter(); 

		try
		{
			String event = req.getParameter("event");
			
			if(event.equals("main"))
			{
				
			}
			else if(event.equals("GetTableList"))
			{
				iaf.setSchema(req.getParameter("schema"));
				Vector vTableList = DBDAO.getTableListInSchema(iaf.getSchema());
				req.setAttribute("vTableList",vTableList);
			}
			else if(event.equals("GetTableDetails"))
			{
				iaf.setTableName(req.getParameter("tableName"));
				Vector vPrivilegeDetail = DBDAO.getTablePrivilegeDetails(iaf.getTableName());
				req.setAttribute("vPrivilegeDetail",vPrivilegeDetail);
			}
			
			
			iam.setReqDispatchPage("dbchecks/DBBrowser.jsp");
			req.setAttribute("IcmsadminFilter", iaf);

		}
		catch(Exception e) // exception
		{  
			iam = new IcmsadminMessage("",e);
			req.setAttribute("IcmsadminMessage",iam );//Action ID,Exception object
			iam.setReqDispatchPage("error.jsp");
		}
		Utilities.requestDispatch(sc,req,resp,iam.getReqDispatchPage());
		return;		
	}	
    /* Ends */
	
	// Edited and Developed by Indrajeet
	
	
		public static void getDBDiskCronJobs(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
		{
			IcmsadminMessage hm = new IcmsadminMessage();	
			IcmsadminFilter hf = new IcmsadminFilter();

			try
			{
				String event = req.getParameter("event");
				
				hf.setFromDate(req.getParameter("fromDate") != null ? new DateTime(req.getParameter("fromDate"),"dd-MMM-yyyy"):new DateTime());
				
				hf.setJobId(req.getParameter("jobId") != null ? req.getParameter("jobId") : "0");
				
				if(event.equals("show"))
				{
					Vector vCronJobLog = DBDAO.getDBDiskCronJobsLog(hf);	 		
					req.setAttribute("vCronJobLog",vCronJobLog);
					hm.setReqDispatchPage("dbchecks/CronJobLogs.jsp");
				}else if(event.equals("drillLogs"))
				{	
					System.out.println("\n--------------------");
					String logPath = DBDAO.getDBDisksCronLogPtah(hf);
					System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+logPath);
					System.out.println("\n In Sheel Cript Method:"+req.getParameter("logtype")+","+req.getParameter("hostName")+","+hf.getJobId()+","+req.getParameter("selDate")+","+logPath+"\n");
					String strbuf1 = ServerShellScript.procedureServerCronJobs(req.getParameter("logtype"),req.getParameter("hostName"),hf.getJobId(),req.getParameter("selDate"), logPath);
					System.out.println("************************");
					req.setAttribute("strbuf1",strbuf1);
					hm.setReqDispatchPage("commonchecks/ServerLog.jsp");
				}
				
				req.setAttribute("IcmsadminFilter", hf);
			}
			catch(Exception e) // exception
			{  
				hm = new IcmsadminMessage("",e);
				req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
				hm.setReqDispatchPage("error.jsp");
			}
			Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
			return;		
		}	
	    /* Ends */
		
		// Editted BY Indrajeet
		
		public static void insertTeamMeetingsRecords(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
		{
			IcmsadminMessage hm = new IcmsadminMessage();	
			IcmsadminFilter hf = new IcmsadminFilter();
			TeamMeetingsDTO tmdto = new TeamMeetingsDTO();
			int insertFlag = 0;
			
			try
			{	
				String event = req.getParameter("event");
				
				// For Generating Meeting Id in the form of YYYYMMDD****(where * denotes integer random value)
				int yyyy = new DateTime().getFinancialYearFirstDate().getYYYY();
				int mm = new DateTime().getFinancialYearFirstDate().getMM();
				int dd =new DateTime().getFinancialYearFirstDate().getDD();
				
				// Below returns meetingId = YYYYMMDD
				String meetingId = String.valueOf(yyyy)+""+String.valueOf(mm <=9 ?"0"+mm : mm)+""+String.valueOf(dd <=9 ? "0"+dd : dd);
				try{
					
					float randomNumber = (float)Math.random();
					float finalValue = (randomNumber * 10000);
					String value = String.valueOf(finalValue);
					
					// Below returns meetingId = YYYYMMDD****
					meetingId = meetingId+""+value.substring(0,value.indexOf("."));
					
					// If Above returns meetingId = YYYYMMDD*** again below while is generating meeting id in the form of YYYYMMDD****
					while(value.substring(0,value.indexOf(".")).length()>4){
						randomNumber = (float)Math.random();
						finalValue = (randomNumber * 10000);
						value = String.valueOf(finalValue);
						meetingId = meetingId+""+value.substring(0,value.indexOf("."));
					}
					
				}catch(Exception e){}
				
				/*tmdto.setMeetingId(meetingId);
				
				tmdto.setMeetingTitle(req.getParameter("meetingTitle") != null ? req.getParameter("meetingTitle") : "In title");
				tmdto.setMeetingPlace(req.getParameter("meetingPlace") != null ? req.getParameter("meetingPlace") : "In place");
				
				tmdto.setFromDate(Utilities.getReportFromDate(req, session));
				tmdto.setToDate(Utilities.getReportToDate(req, session));
				
				tmdto.setAttendedBy(req.getParameter("attendedICMSErName") != null ? req.getParameter("attendedICMSErName") : "In attended by er name");
				tmdto.setOtherPerson(req.getParameter("otherPerson") != null ? req.getParameter("otherPerson") : "In other person");
				
				tmdto.setMeetingCalledBy(req.getParameter("meetingCalledBy") != null ? req.getParameter("meetingCalledBy"): "In meeting called by");
				tmdto.setMeetingCalledByPerson(req.getParameter("meetingCalledByPerson") != null ? req.getParameter("meetingCalledByPerson") : "In meeting called by person");
				
				tmdto.setMeetingReq(req.getParameter("meetingReq") != null ? req.getParameter("meetingReq") : "In meeting req");
				tmdto.setMeetingStartTime(req.getParameter("mStartTimeHr")+":"+req.getParameter("mStartTimeMn")+" "+req.getParameter("timeZone1"));
				
				tmdto.setMeetingEndTime(req.getParameter("mEndTimeHr")+":"+req.getParameter("mEndTimeMn")+" "+req.getParameter("timeZone2"));
				tmdto.setUtilizedTime(req.getParameter("mUtilizedTimeHr")+":"+req.getParameter("mUtilizedTimeMn")+" "+req.getParameter("timeZone3"));
				
				tmdto.setWastedTime(req.getParameter("mWastedTimeHr")+":"+req.getParameter("mWastedTimeMn")+" "+req.getParameter("timeZone4"));
				tmdto.setTravelTime(req.getParameter("mTravelHr")+":"+req.getParameter("mTravelMn")+" "+req.getParameter("timeZone5"));
				
				tmdto.setUpdateTime(req.getParameter("mEndTimeHr")+":"+req.getParameter("mEndTimeMn")+" "+req.getParameter("timeZone6"));
				tmdto.setDescription(req.getParameter("meetingDesc") != null ? req.getParameter("meetingDesc") : "" );
				
				System.out.println("\n"+tmdto.getMeetingId()+"\n"+tmdto.getMeetingTitle()+"\n"+tmdto.getMeetingPlace()+"\n"+tmdto.getFromDate()+"\n"+tmdto.getToDate()+
				"\n"+tmdto.getAttendedBy()+"\n"+tmdto.getOtherPerson()+"\n"+tmdto.getMeetingCalledBy()+"\n"+tmdto.getMeetingCalledByPerson()+"\n"+tmdto.getMeetingReq()
				+"\n"+tmdto.getMeetingStartTime()+"\n"+tmdto.getMeetingEndTime()+"\n"+tmdto.getUtilizedTime()+"\n"+tmdto.getWastedTime()+"\n"+tmdto.getTravelTime()
				+"\n"+tmdto.getUpdateTime()+"\n"+tmdto.getDescription()+"\n");*/
				
				if(event.equals("insert"))
				{			
					//insertFlag = TeamMeetingsDAO.insertTeamMeetingRecords(tmdto);
					
					//if(insertFlag > 0) hm.setMessageByID("SUCC24001"); else hm.setMessageByID("ERR10001");
					
					req.setAttribute("IcmsadminMessage",hm);
					hm.setReqDispatchPage("TeamMeetings/Add.jsp");
				}
				req.setAttribute("IcmsadminFilter", hf);
			}
			catch(Exception e) // exception
			{  
				hm = new IcmsadminMessage("",e);
				req.setAttribute("IcmsadminMessage",hm );//Action ID,Exception object
				hm.setReqDispatchPage("error.jsp");
			}
			//hm.setReqDispatchPage("TeamMeetings/Menu.jsp");
			Utilities.requestDispatch(sc,req,resp,hm.getReqDispatchPage());
			return;		
		}	
		
	   /* Ends */

		
			public static void procedureSrc(HttpSession session,HttpServletRequest req,HttpServletResponse resp,ServletContext sc) throws ServletException, IOException
			{
				
				Utilities.requestDispatch(sc,req,resp,"adminactivity/procedure.jsp");
				return;		
			}
}
