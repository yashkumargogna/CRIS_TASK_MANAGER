package icmsadmin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import icmsadmin.DTO.TeamMeetingsDTO;
import icmsadmin.common.BundleUtility;
import icmsadmin.common.DBConnection;
import icmsadmin.common.DateTime;
import icmsadmin.common.IcmsadminException;
import icmsadmin.common.IcmsadminFilter;
import icmsadmin.common.IcmsadminUtilities;
import icmsadmin.common.LibraryUtilities;
import icmsadmin.DTO.DBTable;

public class TeamMeetingsDAO {
	
public static int getTeamMeetingID() throws Exception
	{
		Statement st = null;  
		Connection con = null;
		ResultSet rs = null;
		int getRowId = 0;

		try
		{	    
			con = DBConnection.getConnection();		
			st = con.createStatement();
			rs = st.executeQuery(BundleUtility.getValue("icmsadmin.common.transaction_query","GET_MEETING_ID"));
			if(rs.next())
				getRowId = rs.getInt(1);
		}
		catch(Exception e)
		{  
			IcmsadminException.throwException(e); 			
		}
		finally
		{
			try{DBConnection.closeDBResources(rs,st,con);}catch(Exception ex){}
		} 
		return getRowId;
	}

	/* END 	*/


public static int insertTeamMeetingRecords(TeamMeetingsDTO TeamMeetingsDTO) throws Exception
{
	PreparedStatement ps = null;  
	Connection con = null;
	ResultSet rs = null;
	int insertFlag = 0;

	try
	{	    
		
		
		con = DBConnection.getConnection();		
		ps = con.prepareStatement(BundleUtility.getValue("icmsadmin.common.transaction_query","INSERT_REC_TEAM_MEETINGS_ADD"));
		
		ps.setString(1, TeamMeetingsDTO.getMeetingTitle());
		ps.setString(2, TeamMeetingsDTO.getMeetingPlace());
		
		ps.setTimestamp(3,TeamMeetingsDTO.getFromDate().getTimeStamp());
		ps.setTimestamp(4, TeamMeetingsDTO.getToDate().getTimeStamp());
		
		ps.setString(5, LibraryUtilities.getCommaSepString(TeamMeetingsDTO.getAttendedBy(),","));
		ps.setString(6, TeamMeetingsDTO.getOtherPerson());
		
		ps.setString(7, TeamMeetingsDTO.getMeetingCalledBy());
		ps.setString(8, TeamMeetingsDTO.getMeetingCalledByPerson());
		
		ps.setString(9, TeamMeetingsDTO.getMeetingReq());
		ps.setInt(10, TeamMeetingsDTO.getUtilizedTime());
		
		ps.setInt(11, TeamMeetingsDTO.getWastedTime());
		ps.setString(12, TeamMeetingsDTO.getDescription());
		
		ps.setString(13, new DateTime(TeamMeetingsDTO.getUpdateTime()).getDateTime());
		ps.setInt(14, TeamMeetingsDTO.getTravelTime());
		
		ps.setLong(15, TeamMeetingsDTO.getMeetingId());
		insertFlag = ps.executeUpdate();
	}
	catch(Exception e)
	{  
		IcmsadminException.throwException(e); 			
	}
	finally
	{
		try{DBConnection.closeDBResources(rs,ps,con);}catch(Exception ex){}
	} 
	return insertFlag;
}

/* END */


public static ArrayList<TeamMeetingsDTO> editTeamMeetingRecord(IcmsadminFilter hf) throws Exception
{
	PreparedStatement ps = null;  
	Connection con = null;
	ResultSet rs = null;
	ArrayList vRecordList = new ArrayList<TeamMeetingsDTO>();
	TeamMeetingsDTO TeamMeetingsDTO = null;
	
	try
	{	    
		con = DBConnection.getConnection();		
		ps = con.prepareStatement(BundleUtility.getValue("icmsadmin.common.transaction_query","EDIT_REC_TEAM_MEETINGS"));
		
		
		ps.setString(1,hf.getFromDate().getDateTime());
		ps.setString(2,hf.getToDate().getDateTime());
		
		rs = ps.executeQuery();
		while(rs.next()){
			TeamMeetingsDTO = new TeamMeetingsDTO();
			
			TeamMeetingsDTO.setFromDate1(new DateTime(rs.getTimestamp(1)).getDateTime1() != null ? new DateTime(rs.getTimestamp(1)).getDateTime1() : "");
			TeamMeetingsDTO.setToDate1(new DateTime(rs.getTimestamp(2)).getDateTime1() != null ? new DateTime(rs.getTimestamp(2)).getDateTime1() : "");
			
			TeamMeetingsDTO.setMeetingId(rs.getInt(3));
			TeamMeetingsDTO.setMeetingTitle(rs.getString(4)!= null ? rs.getString(4) : "");
			
			TeamMeetingsDTO.setMeetingPlace(rs.getString(5));
			TeamMeetingsDTO.setMeetingCalledBy(rs.getString(6)!= null ? rs.getString(6) : "");
			
			vRecordList.add(TeamMeetingsDTO);
		}
	}
	catch(Exception e)
	{  
		IcmsadminException.throwException(e); 			
	}
	finally
	{
		try{DBConnection.closeDBResources(rs,ps,con);}catch(Exception ex){}
	} 
	return vRecordList;
}

/* Ends */

public static ArrayList<TeamMeetingsDTO> getEditTeamMeetingRecord(IcmsadminFilter hf) throws Exception
{
	PreparedStatement ps = null;  
	Connection con = null;
	ResultSet rs = null;
	ArrayList<String> attendedBy =new ArrayList<String>();
	ArrayList vRecordList = new ArrayList<TeamMeetingsDTO>();
	TeamMeetingsDTO TeamMeetingsDTO = null;
	
	try
	{	    
		con = DBConnection.getConnection();		
		ps = con.prepareStatement(BundleUtility.getValue("icmsadmin.common.transaction_query","GET_EDIT_REPORTS_TEAM_MEETINGS"));
		System.out.println("\nMeeting Id"+hf.getMeetingId()+"\n");
		ps.setString(1, hf.getMeetingId());
		rs = ps.executeQuery();
		
		while(rs.next()){
			TeamMeetingsDTO = new TeamMeetingsDTO();                       

			TeamMeetingsDTO.setMeetingTitle(rs.getString(1));
			TeamMeetingsDTO.setMeetingPlace(rs.getString(2));
			
			TeamMeetingsDTO.setFromDate1(new DateTime(rs.getTimestamp(3)).getDateTime1() != null ? new DateTime(rs.getTimestamp(3)).getDateTime1() : "");
			TeamMeetingsDTO.setToDate1(new DateTime(rs.getTimestamp(4)).getDateTime1() != null ? new DateTime(rs.getTimestamp(4)).getDateTime1() : "");
			
			attendedBy.add(rs.getString(5));
			
			TeamMeetingsDTO.setAttendedBy(attendedBy);
			TeamMeetingsDTO.setOtherPerson(rs.getString(6)!= null ? rs.getString(6) : "");
			TeamMeetingsDTO.setMeetingCalledBy(rs.getString(7)!= null ? rs.getString(7) : "");
			
			TeamMeetingsDTO.setMeetingCalledByPerson(rs.getString(8)!= null ? rs.getString(8) : "");
			TeamMeetingsDTO.setMeetingReq(Integer.parseInt(rs.getString(9)) != 1 ? "No" : "Yes");
			TeamMeetingsDTO.setUtilizedTime(rs.getInt(10)> 0 ? rs.getInt(10) : 0);
			
			TeamMeetingsDTO.setWastedTime(rs.getInt(11) > 0 ? rs.getInt(11) : 0);
			TeamMeetingsDTO.setDescription(rs.getString(12) != null ? rs.getString(12) : "");
			TeamMeetingsDTO.setUpdateTime(new DateTime(rs.getTimestamp(13)) != null ? new DateTime(rs.getTimestamp(13)) : new DateTime());
			
			TeamMeetingsDTO.setTravelTime(rs.getInt(14) > 0 ? rs.getInt(14) : 0);
			TeamMeetingsDTO.setMeetingId(rs.getLong(15) > 0 ? rs.getLong(15) : 0);
			
			vRecordList.add(TeamMeetingsDTO);
		}
		System.out.println("Record Size\n"+vRecordList.size());
	}
	catch(Exception e)
	{  
		IcmsadminException.throwException(e); 			
	}
	finally
	{
		try{DBConnection.closeDBResources(rs,ps,con);}catch(Exception ex){}
	} 
	return vRecordList;
}

/* Ends */


public static ArrayList<TeamMeetingsDTO> setEditTeamMeetingRecord(IcmsadminFilter hf) throws Exception
{
	PreparedStatement ps = null;  
	Connection con = null;
	ResultSet rs = null;
	ArrayList<String> attendedBy =new ArrayList<String>();
	ArrayList vRecordList = new ArrayList<TeamMeetingsDTO>();
	TeamMeetingsDTO TeamMeetingsDTO = null;
	
	try
	{	    
		con = DBConnection.getConnection();		
		ps = con.prepareStatement(BundleUtility.getValue("icmsadmin.common.transaction_query","GET_EDIT_REPORTS_TEAM_MEETINGS"));
		ps.setString(1,hf.getMeetingId());
		rs = ps.executeQuery();
		
		while(rs.next()){
			TeamMeetingsDTO = new TeamMeetingsDTO();                       

			TeamMeetingsDTO.setMeetingTitle(rs.getString(1));
			TeamMeetingsDTO.setMeetingPlace(rs.getString(2));
			
			TeamMeetingsDTO.setFromDate1(new DateTime(rs.getTimestamp(3)).getDateTime1() != null ? new DateTime(rs.getTimestamp(3)).getDateTime1() : "");
			TeamMeetingsDTO.setToDate1(new DateTime(rs.getTimestamp(4)).getDateTime1() != null ? new DateTime(rs.getTimestamp(4)).getDateTime1() : "");
			
			attendedBy.add(rs.getString(5));
			
			TeamMeetingsDTO.setAttendedBy(attendedBy);
			TeamMeetingsDTO.setOtherPerson(rs.getString(6)!= null ? rs.getString(6) : "");
			TeamMeetingsDTO.setMeetingCalledBy(rs.getString(7)!= null ? rs.getString(7) : "");
			
			TeamMeetingsDTO.setMeetingCalledByPerson(rs.getString(8)!= null ? rs.getString(8) : "");
			TeamMeetingsDTO.setMeetingReq(Integer.parseInt(rs.getString(9)) != 1 ? "No" : "Yes");
			TeamMeetingsDTO.setUtilizedTime(rs.getInt(10)> 0 ? rs.getInt(10) : 0);
			
			TeamMeetingsDTO.setWastedTime(rs.getInt(11) > 0 ? rs.getInt(11) : 0);
			TeamMeetingsDTO.setDescription(rs.getString(12) != null ? rs.getString(12) : "");
			TeamMeetingsDTO.setUpdateTime(new DateTime(rs.getTimestamp(13)) != null ? new DateTime(rs.getTimestamp(13)) : new DateTime());
			
			TeamMeetingsDTO.setTravelTime(rs.getInt(14) > 0 ? rs.getInt(14) : 0);
			TeamMeetingsDTO.setMeetingId(rs.getLong(15) > 0 ? rs.getLong(15) : 0);
			
			vRecordList.add(TeamMeetingsDTO);
		}
		System.out.println("Record Size\n"+vRecordList.size());
	}
	catch(Exception e)
	{  
		IcmsadminException.throwException(e); 			
	}
	finally
	{
		try{DBConnection.closeDBResources(rs,ps,con);}catch(Exception ex){}
	} 
	return vRecordList;
}

/* Ends */



public static ArrayList<TeamMeetingsDTO> getTeamMeetingRecord(IcmsadminFilter hf) throws Exception
{
	PreparedStatement ps = null;  
	Connection con = null;
	ResultSet rs = null;
	ArrayList<String> attendedBy =new ArrayList<String>();
	ArrayList<TeamMeetingsDTO> vRecordList = new ArrayList<TeamMeetingsDTO>();
	TeamMeetingsDTO TeamMeetingsDTO = null;
	try
	{	    
		
		con = DBConnection.getConnection();		
		ps = con.prepareStatement(BundleUtility.getValue("icmsadmin.common.transaction_query","GET_TEAM_MEETINGS_REPORT"));
		
		ps.setString(1,hf.getFromDate().getDateTime());
		ps.setString(2,hf.getToDate().getDateTime());

		rs = ps.executeQuery();
		while(rs.next()){
			TeamMeetingsDTO = new TeamMeetingsDTO();
			
			TeamMeetingsDTO.setMeetingTitle(rs.getString(1));
			TeamMeetingsDTO.setMeetingPlace(rs.getString(2));
			
			TeamMeetingsDTO.setFromDate1(new DateTime(rs.getTimestamp(3)).getDateTime1() != null ? new DateTime(rs.getTimestamp(3)).getDateTime1() : "");
			TeamMeetingsDTO.setToDate1(new DateTime(rs.getTimestamp(4)).getDateTime1() != null ? new DateTime(rs.getTimestamp(4)).getDateTime1() : "");
			
			attendedBy.add(rs.getString(5));
			
			TeamMeetingsDTO.setAttendedBy(attendedBy);
			TeamMeetingsDTO.setOtherPerson(rs.getString(6)!= null ? rs.getString(6) : "");
			TeamMeetingsDTO.setMeetingCalledBy(rs.getString(7)!= null ? rs.getString(7) : "");
			
			TeamMeetingsDTO.setMeetingCalledByPerson(rs.getString(8)!= null ? rs.getString(8) : "");
			TeamMeetingsDTO.setMeetingReq(rs.getString(9));
			TeamMeetingsDTO.setUtilizedTime(rs.getInt(10) > 0 ? rs.getInt(10) : 0);
			
			TeamMeetingsDTO.setWastedTime(rs.getInt(11) > 0 ? rs.getInt(11) : 0);
			TeamMeetingsDTO.setDescription(rs.getString(12) != null ? rs.getString(12) : "");
			TeamMeetingsDTO.setUpdateTime(new DateTime(rs.getTimestamp(13)) != null ? new DateTime(rs.getTimestamp(13)) : new DateTime());
			
			TeamMeetingsDTO.setTravelTime(rs.getInt(14) > 0 ? rs.getInt(14) : 0);
			TeamMeetingsDTO.setMeetingId(rs.getLong(15) > 0 ? rs.getLong(15) : 0);
			
			vRecordList.add(TeamMeetingsDTO);
			/*System.out.println("\n"+TeamMeetingsDTO.getMeetingTitle()+"\n"+TeamMeetingsDTO.getMeetingPlace()+"\n"+TeamMeetingsDTO.getFromDate1()+"\n"+TeamMeetingsDTO.getToDate1()
			+"\n"+TeamMeetingsDTO.getAttendedBy()+"\n"+TeamMeetingsDTO.getOtherPerson()+"\n"+TeamMeetingsDTO.getMeetingCalledBy()+"\n"+TeamMeetingsDTO.getMeetingCalledByPerson()
			+"\n"+TeamMeetingsDTO.getMeetingReq()+"\n"+TeamMeetingsDTO.getUtilizedTime()+"\n"+TeamMeetingsDTO.getWastedTime()+"\n"+TeamMeetingsDTO.getDescription()
			+"\n"+TeamMeetingsDTO.getUpdateTime()+"\n"+TeamMeetingsDTO.getTravelTime()+"\n"+TeamMeetingsDTO.getMeetingId());*/
			
		}
		System.out.println("Size of Record In DAO:"+vRecordList.size());
		
	}
	catch(Exception e)
	{  
		IcmsadminException.throwException(e); 			
	}
	finally
	{
		try{DBConnection.closeDBResources(rs,ps,con);}catch(Exception ex){}
	} 
	return vRecordList;
}

/* END */
}