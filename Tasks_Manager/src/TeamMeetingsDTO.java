package icmsadmin.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import icmsadmin.common.DateTime;

public class TeamMeetingsDTO implements Serializable
{
	private long meetingId = 1; 			// MEETING_ID  
	private String meetingTitle;  		// MEETING_TITLE
	private String meetingPlace;		// MEETING_PLACE 
	
	private DateTime fromDate;			// FROM_DATE
	private DateTime toDate;			// TO_DATE
	private String fromDate1 = "";
	
	private String toDate1 = "";
	private ArrayList attendedBy;			// ATTENDED_BY
	private String otherPerson;			// OTHER_PERSON
	
	private String meetingCalledBy;		//MEETING_CALLED_BY
	private String meetingCalledByPerson;//MEETING_CALLED_BY_PERSON
	private String meetingReq;			// MEETING_REQ
	
	private int utilizedTime;		// UTILIZED_TIME
	private int wastedTime;			// WASTED_TIME
	private DateTime updateTime;	// UPDATE_TIME
	
	private int travelTime;			// TRAVEL_TIME
	private String description;		// DESCRIPTION
	
	public TeamMeetingsDTO(){
		this.meetingId = 1;
		this.meetingTitle = "";
		this.otherPerson = "";
		this.meetingCalledByPerson = "";
		this.meetingReq = "";
		this.utilizedTime = 0;
		this.wastedTime = 0;
		this.travelTime = 0;
		this.description = "";
		this.fromDate = null;
		this.toDate = null;
		this.fromDate1 = "";
		this.toDate1 = "";
	}
	
	public long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public DateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}

	public DateTime getToDate() {
		return toDate;
	}

	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}

	public String getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(String otherPerson) {
		this.otherPerson = otherPerson;
	}

	public String getMeetingCalledByPerson() {
		return meetingCalledByPerson;
	}

	public void setMeetingCalledByPerson(String meetingCalledByPerson) {
		this.meetingCalledByPerson = meetingCalledByPerson;
	}

	public String getMeetingReq() {
		return meetingReq;
	}

	public void setMeetingReq(String meetingReq) {
		this.meetingReq = meetingReq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingCalledBy() {
		return meetingCalledBy;
	}

	public void setMeetingCalledBy(String meetingCalledBy) {
		this.meetingCalledBy = meetingCalledBy;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public DateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

	public ArrayList getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(ArrayList attendedBy) {
		this.attendedBy = attendedBy;
	}

	public int getUtilizedTime() {
		return utilizedTime;
	}

	public void setUtilizedTime(int utilizedTime) {
		this.utilizedTime = utilizedTime;
	}

	public int getWastedTime() {
		return wastedTime;
	}

	public void setWastedTime(int wastedTime) {
		this.wastedTime = wastedTime;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public String getFromDate1() {
		return fromDate1;
	}

	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}

	public String getToDate1() {
		return toDate1;
	}

	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}

	
}
  
