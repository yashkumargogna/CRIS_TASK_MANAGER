/*
 * Created on Sep 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package icmsadmin.common;

import java.io.Serializable;
import java.rmi.MarshalException;
import java.sql.SQLException;



/**
 * @author dilip
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IcmsadminMessage implements Serializable 
{
	
	/* Message Types : 1 - Error
	 *                 2 - Success
	 *                 3 - Warning
	 *  			   4 - Exception
	 * */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean messageFlag = false;
	IcmsadminReportsMessage icmsadminreportsmessage = null;
	int messageType = 0;
	String messageID = "";
	String message = "";
	
	String reqDispatchPage = "";
	
	public IcmsadminMessage()
	{
	}
	
	/* Method to get Standard Exception message for all type of Exception  S 
	 * 
	 * param  
	 * @actionId - ID of eaach action(ex.login,movement) 
	 * @exception object
	 * */
	public IcmsadminMessage(Exception ex)
	{
		this.messageType = Message.EXCEPTION;
		this.messageFlag = true;		
		
		
		if(ex instanceof SQLException)
		{
			this.messageID = "SQL00000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		else if (ex instanceof StringIndexOutOfBoundsException)
		{
			this.messageID = "STR00000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		else if(ex instanceof NullPointerException)
		{
			this.messageID = "NPE00000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		
		else if(ex instanceof MarshalException)
		{
			this.messageID = "MSE00000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		else if (ex instanceof RuntimeException)
		{
			this.messageID = "RUN00000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		
		else
		{
			this.messageID = "EXP00000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		IcmsadminException.printSystemOut(this.messageID+": "+this.message);
	}
	/* Method to get Standard Exception message for all type of Exception  E */
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns the messageFlag.
	 */
	public boolean isMessageFlag() {
		return messageFlag;
	}
	/**
	 * @param messageFlag The messageFlag to set.
	 */
	public void setMessageFlag(boolean messageFlag) {
		this.messageFlag = messageFlag;
	}
	/**
	 * @return Returns the messageID.
	 */
	public String getMessageID() {
		return messageID;
	}
	/**
	 * @param messageID The messageID to set.
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	/**
	 * @return Returns the messageType.
	 */
	public int getMessageType() {
		return messageType;
	}
	/**
	 * @param messageType The messageType to set.
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	/* Method to get Complete Message from COIS Standard Message  S */ 
	public IcmsadminMessage(int messageType, String messageID) throws Exception
	{
		super();
		
		if( (messageID.compareToIgnoreCase("")!=0) && (messageID != null))
		{//if I S
			
			this.messageType = messageType;
			this.messageFlag = true;	
			this.messageID = messageID;
			this.message = BundleUtility.getValue("icmsadmin.common.icmsadmin_messages",messageID);
						
			if( (this.message == null) || (this.message.equals("")) )
				this.message = "Message not Found in Message properties file ";
						
			IcmsadminException.printSystemOut(this.messageID+": "+this.message);
		}//if I S
	
	}
	/* Method to get Complete Message from COIS Standard Message  E */
	
	
	/* Method to get Customised Message from COIS Standard + Custom Message S */
	/**
	 * @param int messageType
	 * @param String messageID
	 * @param String message
	 * @throws Exception
	 */
	public IcmsadminMessage(int messageType,String messageID,String message) throws Exception
	{
		super();
		
		if( (messageID.compareToIgnoreCase("")!=0) && (messageID != null))
		{//if I S
			
			this.messageType = messageType;
			this.messageFlag = true;	
			this.messageID = messageID;
			this.message = BundleUtility.getValue("icmsadmin.common.icmsadmin_messages",messageID);
			
			/*if( (this.message == null) || (this.message.equals("")) )
				this.message = "Message not Found in COIS Message properties file ";*/
			
			if( (this.message != null) && (this.message.indexOf('@') != -1) )
			{
			    this.message = this.message.replaceAll("@",message);
			}
			else
			    this.message += message;
			
			IcmsadminException.printSystemOut(this.messageID+": "+this.message);
		}//if I S
		
	}
	/* Method to get Customised Message from COIS Standard + Custom Message E */
	
	
	/* Method to get Customised Message from COIS Standard + Custom Message S */
	/**
	 * @param int messageType
	 * @param String messageID
	 * @param int message
	 * @throws Exception
	 */
	public IcmsadminMessage(int messageType,String messageID,int message) throws Exception
	{
		super();
		
		if( (messageID.compareToIgnoreCase("")!=0) && (messageID != null))
		{//if I S
			
			this.messageType = messageType;
			this.messageFlag = true;	
			this.messageID = messageID;
			this.message = BundleUtility.getValue("icmsadmin.common.icmsadmin_messages",messageID);
			
			if( (this.message == null) || (this.message.equals("")) )
				this.message = "Message not Found in Message properties file ";
			
			if( (this.message != null) && (this.message.indexOf('@') != -1) )
			{
			    this.message = this.message.replaceAll("@",""+message);
			}
			else
			    this.message += message;
			
			IcmsadminException.printSystemOut(this.messageID+": "+this.message);
		}//if I S
		
	}
	/* Method to get Customised Message from COIS Standard + Custom Message E */
	
	
	public IcmsadminMessage(String actionID,int messageType,String message)
	{
		super();
		
		this.messageType = messageType;
		this.messageFlag = true;
			
		switch(messageType)
		{
			case 1	:	this.messageID = "ERR"+actionID+"000";
						break;
			case 2	:	this.messageID = "MSG"+actionID+"000";
						break;
			case 3	:	this.messageID = "WRN"+actionID+"000";
						break;
			case 4	:	this.messageID = "EXP"+actionID+"000";
						break;
		}
			
		this.message = message;
						
		IcmsadminException.printSystemOut(this.messageID+": "+this.message);
	}
	
	
	/* Method to get Standard Exception message for all type of Exception  S 
	 * 
	 * param  
	 * @actionId - ID of each action(ex.login,movement) 
	 * @exception object
	 * */
	public IcmsadminMessage(String actionID,Exception ex)
	{
		this.messageType = Message.EXCEPTION;
		this.messageFlag = true;		
		
		
		if(ex instanceof SQLException)
		{
			this.messageID = "SQL"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		else if (ex instanceof StringIndexOutOfBoundsException)
		{
			this.messageID = "STR"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		else if(ex instanceof NullPointerException)
		{
			this.messageID = "NPE"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex);
		}
		
		else if(ex instanceof MarshalException)
		{
			this.messageID = "MSE"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		else if (ex instanceof RuntimeException)
		{
			this.messageID = "RUN"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		
		else
		{
			this.messageID = "EXP"+actionID+"000";
			this.message = LibraryUtilities.getExceptionMessage(ex); 
		}
		IcmsadminException.printSystemOut(this.messageID+": "+this.message);
	}
	/* Method to get Standard Exception message for all type of Exception  E */
	
	
	/**
	 * @return Returns the reqDispatchPage.
	 */
	public String getReqDispatchPage() {
		return reqDispatchPage;
	}
	/**
	 * @param reqDispatchPage The reqDispatchPage to set.
	 */
	public void setReqDispatchPage(String reqDispatchPage) {
		this.reqDispatchPage = reqDispatchPage;
	}
	
	public void setMessageByID(String messageID) throws Exception 
	{
		this.message = BundleUtility.getValue("icmsadmin.common.icmsadmin_messages",messageID);
		
		if( (this.message == null) || (this.message.equals("")) )
			this.message = "Message not Found in Message properties file ";
		
		IcmsadminException.printSystemOut(this.messageID+": "+this.message);
	}

	public IcmsadminReportsMessage getIcmsadminreportsmessage() {
		return icmsadminreportsmessage;
	}

	public void setIcmsadminreportsmessage(IcmsadminReportsMessage icmsadminreportsmessage) {
		this.icmsadminreportsmessage = icmsadminreportsmessage;
	}
	
	
}
