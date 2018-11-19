/*
 * Created on Jan 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package icmsadmin.common;

import java.io.Serializable;


/**
 * @author dilip
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IcmsadminFilter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String station = "";
	
	UserInfo userInfo = null;
	
	String userID = "";  
	String userPassword = "";
	

	String fbOption = "";

	String filecreateViewopt="";
	String repWindowType = "";
	
	DateTime fromDate = null;
	DateTime toDate = null;
	DateTime currentDate = null;
	
	String ID_TRAN_DEF = ""; 
	String Registration="";
	
	String gauge = "";
	String duration = "";
	String dept = "";
	String depot = "";
	String generation="";
	String trainType = "";
	String trainTypeGroup = "";
	
	String trainNo = "";
	String journeyStation = "";
	DateTime journeyDate = null;
	String journeyEvent = "";
	
	DateTime trainStartDate = null;
	DateTime inputDate = null;
	
	String caseType = "";
	String reportBasis = "";
	String reportCategory = "";
	String drillType = "";
	String listId="";
	String listName="";
	String deviation = "";
	
	String locoType = "";
	String classOfTravel = "";
	
	
	String distanceFrom = "";
	String distanceTo = "";
	
	String blockSection = "";
	
	String EXCEPTION_NUMBER = "";
	
	String OPTI = "";
	
	String fromStation ="";
	String toStation ="";
	
	String rakeType = "";
	
	String positionTime = "";
	String event = "";
	
	String idRakeLink = "";
	String dynRakeId = "";
	
	String reason = "";
	
	String acFlag = "";
	String vehicleType = "";
	String couplingType = "";
	
	String header = "";
	String schema = "";
	
	String reportLevel = "";
	String levelPAM = "";
	String location = "";
	
	int year = 0;
	
	String fYear = "";
	int	   year1 = 0;
	int    year2 = 0;
	int month = 0;
	
	int timeInHours = 0;
	
	int applicationReleaseID = 0;
	
	String name = "";
	
	boolean dataFound = false;
	
	String zone = "";
	String division = "";
	String userType="";
	
	String owningRly = "";
	String coachType = "";
	
	int DELAY_TO = 0;
	int DELAY_HO = 0;
	
	String DELAY_TO_CASE = "";
	String DELAY_HO_CASE = "";
	
	
	String locoNo = "";
	String domain = "";
	String traction = "";
	String service = "";
	String coachCategory="";
	
	String monthName = "";
	
	String coachFactory = "";
	
	//to remove in future
	String status="";
	int delay = 0;
	int delayFrom=0;
	int delayTo=0;
	DateTime monitorDateFrom=null;
	DateTime monitorDateTo=null;
	String delayMin="";
	
	String formatType = "";
	
	int idTrainDef = 0;
	String trainNumber="";
	String source="";
	String destination="";
	
	String causeCode="";
	String subcauseCode="";
	
	String startDate="";
	String fromHH="";
	String fromMM="";
	String toHH="";
	String toMM="";
	
	String maintenanceType = "";
	
	String PP_FROM = "";
	String PP_TO = "";
   
	
	  int avgDistance = 0;
	
	
	  
	String dClassFlag = "";
	String majorFlag = "";
	String icmsUpFlag = "";
	String junctionFlag = "";
	
	String tableName = "";
	String applicationName = "";
	
	// Edited by Indrajeet
	String jobId;
	String timeZone1 = "AM";
	String timeZone2 = "AM";
	String timeZone3 = "AM";
	String timeZone4 = "AM";
	String timeZone5 = "AM";
	String timeZone6 = "AM";
	String meetingId = "";
	
	public String getApplicationName() {
		return applicationName;
	}


	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}


	public int getApplicationReleaseID() {
		return applicationReleaseID;
	}


	public void setApplicationReleaseID(int applicationReleaseID) {
		this.applicationReleaseID = applicationReleaseID;
	}


	public String getTrainNo() {
		return trainNo;
	}


	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}


	public String getJourneyStation() {
		return journeyStation;
	}


	public void setJourneyStation(String journeyStation) {
		this.journeyStation = journeyStation;
	}


	public DateTime getJourneyDate() {
		return journeyDate;
	}


	public void setJourneyDate(DateTime journeyDate) {
		this.journeyDate = journeyDate;
	}


	public String getJourneyEvent() {
		return journeyEvent;
	}


	public void setJourneyEvent(String journeyEvent) {
		this.journeyEvent = journeyEvent;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getSchema() {
		return schema;
	}


	public void setSchema(String schema) {
		this.schema = schema;
	}


	public String getStation() {
		return station;
	}


	public void setStation(String station) {
		this.station = station;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getFbOption() {
		return fbOption;
	}


	public void setFbOption(String fbOption) {
		this.fbOption = fbOption;
	}


	public String getFilecreateViewopt() {
		return filecreateViewopt;
	}


	public void setFilecreateViewopt(String filecreateViewopt) {
		this.filecreateViewopt = filecreateViewopt;
	}


	public String getRepWindowType() {
		return repWindowType;
	}


	public void setRepWindowType(String repWindowType) {
		this.repWindowType = repWindowType;
	}


	public String getfYear() {
		return fYear;
	}


	public void setfYear(String fYear) {
		this.fYear = fYear;
	}


	public String getdClassFlag() {
		return dClassFlag;
	}


	public void setdClassFlag(String dClassFlag) {
		this.dClassFlag = dClassFlag;
	}




	public String getRegistration() {
		return Registration;
	}


	public void setRegistration(String registration) {
		Registration = registration;
	}
	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getID_TRAN_DEF() {
		return ID_TRAN_DEF;
	}


	public void setID_TRAN_DEF(String id_tran_def) {
		ID_TRAN_DEF = id_tran_def;
	}


	public String getDClassFlag() {
		return dClassFlag;
	}


	public void setDClassFlag(String classFlag) {
		dClassFlag = classFlag;
	}


	public String getMajorFlag() {
		return majorFlag;
	}


	public void setMajorFlag(String majorFlag) {
		this.majorFlag = majorFlag;
	}


	public String getIcmsUpFlag() {
		return icmsUpFlag;
	}


	public void setIcmsUpFlag(String icmsUpFlag) {
		this.icmsUpFlag = icmsUpFlag;
	}


	public String getJunctionFlag() {
		return junctionFlag;
	}


	public void setJunctionFlag(String junctionFlag) {
		this.junctionFlag = junctionFlag;
	}


	public String getUserID() {
		return userID;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getClassOfTravel() {
		return classOfTravel;
	}


	public void setClassOfTravel(String classOfTravel) {
		this.classOfTravel = classOfTravel;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getReportCategory() {
		return reportCategory;
	}


	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}


	public String getDistanceFrom() {
		return distanceFrom;
	}


	public void setDistanceFrom(String distanceFrom) {
		this.distanceFrom = distanceFrom;
	}


	public String getDistanceTo() {
		return distanceTo;
	}


	public void setDistanceTo(String distanceTo) {
		this.distanceTo = distanceTo;
	}


	public String getFormatType() {
		return formatType;
	}


	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}


	public String getPP_FROM() {
		return PP_FROM;
	}


	public void setPP_FROM(String pp_from) {
		PP_FROM = pp_from;
	}


	public String getPP_TO() {
		return PP_TO;
	}


	public void setPP_TO(String pp_to) {
		PP_TO = pp_to;
	}


	public String getFromStation() {
		return fromStation;
	}


	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}


	public String getToStation() {
		return toStation;
	}


	public void setToStation(String toStation) {
		this.toStation = toStation;
	}


	public String getIdRakeLink() {
		return idRakeLink;
	}


	public void setIdRakeLink(String idRakeLink) {
		this.idRakeLink = idRakeLink;
	}


	public String getDynRakeId() {
		return dynRakeId;
	}


	public void setDynRakeId(String dynRakeId) {
		this.dynRakeId = dynRakeId;
	}


	public String getMonthName() {
		return monthName;
	}


	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}


	public String getRakeType() {
		return rakeType;
	}


	public void setRakeType(String rakeType) {
		this.rakeType = rakeType;
	}


	public String getBlockSection() {
		return blockSection;
	}


	public void setBlockSection(String blockSection) {
		this.blockSection = blockSection;
	}


	public DateTime getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(DateTime currentDate) {
		this.currentDate = currentDate;
	}


	public int getAvgDistance() {
		return avgDistance;
	}


	public boolean isDataFound() {
		return dataFound;
	}


	public void setDataFound(boolean dataFound) {
		this.dataFound = dataFound;
	}


	public void setAvgDistance(int avgDistance) {
		this.avgDistance = avgDistance;
	}


	public IcmsadminFilter()
	{
	}
	
	
	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getMaintenanceType() {
		return maintenanceType;
	}


	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}


	public String getPositionTime() {
		return positionTime;
	}


	public void setPositionTime(String positionTime) {
		this.positionTime = positionTime;
	}


	public String getDepot() {
		return depot;
	}


	public void setDepot(String depot) {
		this.depot = depot;
	}


	public String getCouplingType() {
		return couplingType;
	}


	public void setCouplingType(String couplingType) {
		this.couplingType = couplingType;
	}


	public String getLocoType() {
		return locoType;
	}


	public void setLocoType(String locoType) {
		this.locoType = locoType;
	}


	public String getSubcauseCode() {
		return subcauseCode;
	}

	public void setSubcauseCode(String subcauseCode) {
		this.subcauseCode = subcauseCode;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public int getYear1() {
		return year1;
	}

	public void setYear1(int year1) {
		this.year1 = year1;
	}

	public int getYear2() {
		return year2;
	}

	public void setYear2(int year2) {
		this.year2 = year2;
	}



	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getFYear() {
		return fYear;
	}

	public void setFYear(String year) {
		fYear = year;
	}

	
	public int getTimeInHours() {
		return timeInHours;
	}


	public void setTimeInHours(int timeInHours) {
		this.timeInHours = timeInHours;
	}


	public DateTime getInputDate() {
		return inputDate;
	}
	public void setInputDate(DateTime inputDate) {
		this.inputDate = inputDate;
	}
	public String getReportLevel() {
		return reportLevel;
	}
	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}
	public int getDELAY_HO() {
		return DELAY_HO;
	}
	public void setDELAY_HO(int delay_ho) {
		DELAY_HO = delay_ho;
	}
	public String getDELAY_HO_CASE() {
		return DELAY_HO_CASE;
	}
	public void setDELAY_HO_CASE(String delay_ho_case) {
		DELAY_HO_CASE = delay_ho_case;
	}
	public int getDELAY_TO() {
		return DELAY_TO;
	}
	public void setDELAY_TO(int delay_to) {
		DELAY_TO = delay_to;
	}
	public String getDELAY_TO_CASE() {
		return DELAY_TO_CASE;
	}
	public void setDELAY_TO_CASE(String delay_to_case) {
		DELAY_TO_CASE = delay_to_case;
	}
	public String getDivision() {
		return division;
	}
	/**
	 * @return Returns the listId.
	 */
	public String getListId() {
		return listId;
	}
	/**
	 * @param listId The listId to set.
	 */
	public void setListId(String listId) {
		this.listId = listId;
	}
	/**
	 * @return Returns the listName.
	 */
	public String getListName() {
		return listName;
	}
	/**
	 * @param listName The listName to set.
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getCoachFactory() {
		return coachFactory;
	}
	public void setCoachFactory(String coachFactory) {
		this.coachFactory = coachFactory;
	}
	/**
	 * @return Returns the destination.
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination The destination to set.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return Returns the source.
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source The source to set.
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	
	
	public int getIdTrainDef() {
		return idTrainDef;
	}

	public void setIdTrainDef(int idTrainDef) {
		this.idTrainDef = idTrainDef;
	}

	public DateTime getTrainStartDate() {
		return trainStartDate;
	}
	public void setTrainStartDate(DateTime trainStartDate) {
		this.trainStartDate = trainStartDate;
	}
	public String getOPTI() {
		return OPTI;
	}
	public void setOPTI(String opti) {
		OPTI = opti;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	public String getEXCEPTION_NUMBER() {
		return EXCEPTION_NUMBER;
	}
	public void setEXCEPTION_NUMBER(String exception_number) {
		EXCEPTION_NUMBER = exception_number;
	}
	/**
	 * @return Returns the reportBasis.
	 */
	public String getReportBasis() {
		return reportBasis;
	}
	/**
	 * @param reportBasis The reportBasis to set.
	 */
	public void setReportBasis(String reportBasis) {
		this.reportBasis = reportBasis;
	}
	
	
	/**
	 * @return Returns the coachType.
	 */
	public String getCoachType() {
		return coachType;
	}
	/**
	 * @param coachType The coachType to set.
	 */
	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
	/**
	 * @return Returns the owningRly.
	 */
	public String getOwningRly() {
		return owningRly;
	}
	/**
	 * @param owningRly The owningRly to set.
	 */
	public void setOwningRly(String owningRly) {
		this.owningRly = owningRly;
	}
	/**
	 * @return Returns the acFlag.
	 */
	public String getAcFlag() {
		return acFlag;
	}
	/**
	 * @param acFlag The acFlag to set.
	 */
	public void setAcFlag(String acFlag) {
		this.acFlag = acFlag;
	}
	/**
	 * @return Returns the vehicleType.
	 */
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType The vehicleType to set.
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the coachCategory.
	 */
	public String getCoachCategory() {
		return coachCategory;
	}
	/**
	 * @param coachCategory The coachCategory to set.
	 */
	public void setCoachCategory(String coachCategory) {
		this.coachCategory = coachCategory;
	}
	/**
	 * @return Returns the userType.
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return Returns the locoNo.
	 */
	public String getLocoNo() {
		return locoNo;
	}
	/**
	 * @param locoNo The locoNo to set.
	 */
	public void setLocoNo(String locoNo) {
		this.locoNo = locoNo;
	}
	/**
	 * @return Returns the domain.
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain The domain to set.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @return Returns the traction.
	 */
	public String getTraction() {
		return traction;
	}
	/**
	 * @param traction The traction to set.
	 */
	public void setTraction(String traction) {
		this.traction = traction;
	}
	/**
	 * @return Returns the zone.
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * @param zone The zone to set.
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the train.
	 */
	
	/**
	 * @return Returns the deviation.
	 */
	public String getDeviation() {
		return deviation;
	}
	/**
	 * @param deviation The deviation to set.
	 */
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	/**
	 * @return Returns the drillType.
	 */
	public String getDrillType() {
		return drillType;
	}
	/**
	 * @param drillType The drillType to set.
	 */
	public void setDrillType(String drillType) {
		this.drillType = drillType;
	}
	
	/**
	 * @return Returns the trainTypeGroup.
	 */
	public String getTrainTypeGroup() {
		return trainTypeGroup;
	}
	/**
	 * @param trainTypeGroup The trainTypeGroup to set.
	 */
	public void setTrainTypeGroup(String trainTypeGroup) {
		this.trainTypeGroup = trainTypeGroup;
	}
	/**
	 * @return Returns the delayFrom.
	 */
	public int getDelayFrom() {
		return delayFrom;
	}
	/**
	 * @param delayFrom The delayFrom to set.
	 */
	public void setDelayFrom(int delayFrom) {
		this.delayFrom = delayFrom;
	}
	/**
	 * @return Returns the delayTo.
	 */
	public int getDelayTo() {
		return delayTo;
	}
	/**
	 * @param delayTo The delayTo to set.
	 */
	public void setDelayTo(int delayTo) {
		this.delayTo = delayTo;
	}
	/**
	 * @return Returns the fromHH.
	 */
	public String getFromHH() {
		return fromHH;
	}
	/**
	 * @param fromHH The fromHH to set.
	 */
	public void setFromHH(String fromHH) {
		this.fromHH = fromHH;
	}
	/**
	 * @return Returns the fromMM.
	 */
	public String getFromMM() {
		return fromMM;
	}
	/**
	 * @param fromMM The fromMM to set.
	 */
	public void setFromMM(String fromMM) {
		this.fromMM = fromMM;
	}
	/**
	 * @return Returns the toHH.
	 */
	public String getToHH() {
		return toHH;
	}
	/**
	 * @param toHH The toHH to set.
	 */
	public void setToHH(String toHH) {
		this.toHH = toHH;
	}
	/**
	 * @return Returns the toMM.
	 */
	public String getToMM() {
		return toMM;
	}
	/**
	 * @param toMM The toMM to set.
	 */
	public void setToMM(String toMM) {
		this.toMM = toMM;
	}
	/**
	 * @return Returns the delayMin.
	 */
	public String getDelayMin() {
		return delayMin;
	}
	/**
	 * @param delayMin The delayMin to set.
	 */
	public void setDelayMin(String delayMin) {
		this.delayMin = delayMin;
	}
	/**
	 * @return Returns the gaugeType.
	 */
	
	/**
	 * @return Returns the trainNumber.
	 */
	public String getTrainNumber() {
		return trainNumber;
	}
	/**
	 * @param trainNumber The trainNumber to set.
	 */
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	/**
	 * @return Returns the trainType.
	 */
	public String getTrainType() {
		return trainType;
	}
	/**
	 * @param trainType The trainType to set.
	 */
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	/**
	 * @return Returns the monitorDate.
	 */
	
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return Returns the levelPAM.
	 */
	public String getLevelPAM() {
		return levelPAM;
	}
	/**
	 * @param levelPAM The levelPAM to set.
	 */
	public void setLevelPAM(String levelPAM) {
		this.levelPAM = levelPAM;
	}
	
	/**
	 * @return Returns the delay.
	 */
	public int getDelay() {
		return delay;
	}
	/**
	 * @param delay The delay to set.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}
	/**
	 * @return Returns the caseType.
	 */
	public String getCaseType() {
		return caseType;
	}
	/**
	 * @param caseType The caseType to set.
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	/**
	 * @return Returns the fromDate.
	 */
	public DateTime getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the header.
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header The header to set.
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	/**
	 * @return Returns the toDate.
	 */
	public DateTime getToDate() {
		return toDate;
	}
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return Returns the gauge.
	 */
	public String getGauge() {
		return gauge;
	}
	/**
	 * @param gauge The gauge to set.
	 */
	public void setGauge(String gauge) {
		this.gauge = gauge;
	}

	public DateTime getMonitorDateFrom() {
		return monitorDateFrom;
	}
	public void setMonitorDateFrom(DateTime monitorDateFrom) {
		this.monitorDateFrom = monitorDateFrom;
	}
	public DateTime getMonitorDateTo() {
		return monitorDateTo;
	}
	public void setMonitorDateTo(DateTime monitorDateTo) {
		this.monitorDateTo = monitorDateTo;
	}
	
	
	public String getGeneration() {
		return generation;
	}


	public void setGeneration(String generation) {
		this.generation = generation;
	}


	/**
	 * @return Returns the causeCode.
	 */
	public String getCauseCode() {
		return causeCode;
	}
	/**
	 * @param causeCode The causeCode to set.
	 */
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	
	public String getJobId() {
		return jobId;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getTimeZone1() {
		return timeZone1;
	}


	public void setTimeZone1(String timeZone1) {
		this.timeZone1 = timeZone1;
	}


	public String getTimeZone2() {
		return timeZone2;
	}


	public void setTimeZone2(String timeZone2) {
		this.timeZone2 = timeZone2;
	}


	public String getTimeZone3() {
		return timeZone3;
	}


	public void setTimeZone3(String timeZone3) {
		this.timeZone3 = timeZone3;
	}


	public String getTimeZone4() {
		return timeZone4;
	}


	public void setTimeZone4(String timeZone4) {
		this.timeZone4 = timeZone4;
	}


	public String getTimeZone5() {
		return timeZone5;
	}


	public void setTimeZone5(String timeZone5) {
		this.timeZone5 = timeZone5;
	}


	public String getTimeZone6() {
		return timeZone6;
	}


	public void setTimeZone6(String timeZone6) {
		this.timeZone6 = timeZone6;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getMeetingId() {
		return meetingId;
	}


	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
}
