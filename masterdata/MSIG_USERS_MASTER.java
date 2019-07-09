/*
  This class has been generated by the Code Generator

Change History	:

	No                    Date                             Description                                                         Changed By
    ====    ==========                  ===========                                            ==========
    AL001           17/07/2017                Redmine #1660 - Account handler by branch 
	                                                                                              and application type     ALA
*/

package com.msig.masterdata;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObject;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.DMLStatement;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;
import com.msig.persistancedb.MSIG_BO_ACTIVITIES;
import com.opentext.integration.services.OTDSIntegration;

@SuppressWarnings("deprecation")
public class MSIG_USERS_MASTER extends MSIG_USERS_MASTERBase {
	private static CordysLogger logger = CordysLogger
			.getCordysLogger(MSIG_USERS_MASTER.class);
	private static final String STATUS_ACTIVE = "ACTIVE";

	public MSIG_USERS_MASTER() {
		this((BusObjectConfig) null);
	}

	public MSIG_USERS_MASTER(BusObjectConfig config) {
		super(config);
	}

	@Override
	public void onBeforeInsert() {
		super.onBeforeInsert();

		if (this.getSTATUS() == null
				|| (this.getSTATUS() != null && this.getSTATUS().equals(""))) {
			this.setSTATUS(STATUS_ACTIVE);
		}
		else if(this.getSTATUS().equalsIgnoreCase("INACTIVE") && this.getOUT_FROM_DATE()==null){
			    Calendar cal = Calendar.getInstance();		    
			    this.setOUT_FROM_DATE(cal.getTime());
			    cal.set(9999,11,31);
			    this.setOUT_TO_DATE(cal.getTime());										
		}

		this.setCREATED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setCREATED_ON(new Date());
		this.setMODIFIED_ON(new Date());
	}

	@Override
	public void onBeforeUpdate() {
		try{
		super.onBeforeUpdate();

		/*MSIG_USERS_MASTER userObj = MSIG_USERS_MASTER.getMsigUsersMasterObject(this.getUSER_ID());
		logger.error("USEROBJ: " + this.getUSER_ID() + "," + userObj.getUSER_FULL_NAME() + "," + userObj.getUSER_EMAIL() + "," + userObj.getDESIGNATION());
		if (userObj != null) {
			if (this.getUSER_FULL_NAME() == null) {
				this.setUSER_FULL_NAME(userObj.getUSER_FULL_NAME());
			}
			if (this.getUSER_EMAIL() == null) {
				this.setUSER_EMAIL(userObj.getUSER_EMAIL());
			}
			if (this.getDESIGNATION() == null) {
				this.setDESIGNATION(userObj.getDESIGNATION());
			}
		}*/
		if(this.getSTATUS()!=null && this.getSTATUS().equalsIgnoreCase("INACTIVE")){
			if(this.getOUT_FROM_DATE()== null){ //Resigned staff 
		    Calendar cal = Calendar.getInstance();		    
		    this.setOUT_FROM_DATE(cal.getTime());
		    cal.set(9999,11,31);
		    this.setOUT_TO_DATE(cal.getTime());	
		    BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_USER_MAPPING> bitr = MSIG_BRANCH_LOB_ROLE_USER_MAPPING.getMsigBranchLobRoleUserMappingObjectsForUserId(this.getUSER_ID());
	         while(bitr.hasMoreElements()){
	        	MSIG_BRANCH_LOB_ROLE_USER_MAPPING userObj = bitr.nextElement();
	        	try{
	        	userObj.delete();
	        	}
	        	catch(Exception e){
	        		
	        	}
	         }
			}
		}
		else{
			this.setOUT_TO_DATE(null);
			this.setOUT_FROM_DATE(null);
		 }
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_ON(new Date());
		}
		catch(Exception e){}
	}

	@Override
	public void onAfterInsert() {
		super.onAfterInsert();

		String id = this.getUSER_ID();
		String name = this.getUSER_FULL_NAME();
		String email = this.getUSER_EMAIL();
		String designation = this.getDESIGNATION();
		String orgDN = BSF.getOrganization();

		try {
			String returnPassword = OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);

			if (!"".equals(returnPassword)) {
				MasterDataUtil.sendUsernameAndPassword(id, name, email, returnPassword);
			}
			Thread.sleep(1000);
			MasterDataUtil.savePreference(id, name, "");
		} catch (Exception e) {
			logger.error(e.toString());
			try {
				Thread.sleep(1000);
				String returnPassword = OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);
				if (!"".equals(returnPassword)) {
					MasterDataUtil.sendUsernameAndPassword(id, name, email, returnPassword);
				}
			} catch (Exception e1) {
				logger.error(e1.toString());
				try {
					Thread.sleep(1000);
					String returnPassword = OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);
					if (!"".equals(returnPassword)) {
						MasterDataUtil.sendUsernameAndPassword(id, name, email, returnPassword);
					}
				} catch (Exception e2) {
					logger.error(e2.toString());
				}
			}
		}
	}

	@Override
	public void onAfterUpdate() {
		super.onAfterUpdate();

		String id = this.getUSER_ID();
		String name = this.getUSER_FULL_NAME();
		String email = this.getUSER_EMAIL();
		String designation = this.getDESIGNATION();
		String orgDN = BSF.getOrganization();

		MasterDataUtil.savePreference(id, name, "");

		try {
			OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);
		} catch (Exception e) {
			logger.error(e.toString());
			try {
				Thread.sleep(1000);
				OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);
			} catch (Exception e1) {
				logger.error(e1.toString());
				try {
					Thread.sleep(1000);
					OTDSIntegration.createOrUpdateUserInPartitionRESTful(id, name, email, designation, orgDN);
				} catch (Exception e2) {
					logger.error(e2.toString());
				}
			}
		}
	}

	@Override
	public void onBeforeDelete() {
		super.onBeforeDelete();

		try {
			OTDSIntegration.deleteUserInPartition(this.getUSER_ID());
		} catch (Exception e) {
			logger.error(e.toString());
			try {
				Thread.sleep(5000);
				OTDSIntegration.deleteUserInPartition(this.getUSER_ID());
			} catch (Exception e1) {
				logger.error(e1.toString());
				try {
					Thread.sleep(5000);
					OTDSIntegration.deleteUserInPartition(this.getUSER_ID());
				} catch (Exception e2) {
					logger.error(e2.toString());
				}
			}
		}
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_USERS_MASTER> searchUsersMaster(
			String USER_ID, String USER_FULL_NAME) {
		USER_ID = "%" + USER_ID + "%";
		USER_FULL_NAME = "%" + USER_FULL_NAME + "%";

		String queryText = "SELECT * FROM MSIG_USERS_MASTER WHERE USER_ID LIKE :USER_ID AND USER_FULL_NAME LIKE :USER_FULL_NAME";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID",
				QueryObject.PARAM_STRING, USER_ID);
		query.addParameter("USER_FULL_NAME",
				"MSIG_USERS_MASTER.USER_FULL_NAME", QueryObject.PARAM_STRING,
				USER_FULL_NAME);

		query.setResultClass(MSIG_USERS_MASTER.class);
		return query.getObjects();
	}

	public static MSIG_USERS_MASTER getMsigUserRecordForName(String USER_ID) {
		String queryText = "SELECT * FROM MSIG_USERS_MASTER WHERE USER_ID = '" + USER_ID + "'";

		QueryObject query = new QueryObject(queryText);
		/*query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID",
				QueryObject.PARAM_STRING, USER_ID);*/

		//logger.error("GET USER ID QUERY::: " + query.getQueryText() + "\t");

		query.setResultClass(MSIG_USERS_MASTER.class);
		return (MSIG_USERS_MASTER) query.getObject();
	}

	public static MSIG_USERS_MASTER getUserFromProducerCode(String PRODUCER_CODE) {
		String queryText = "SELECT USER_ID, USER_FULL_NAME FROM MSIG_USERS_MASTER WHERE PRODUCER_CODE = :PRODUCER_CODE AND STATUS = 'ACTIVE'";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("PRODUCER_CODE", "MSIG_USERS_MASTER.PRODUCER_CODE",
				QueryObject.PARAM_STRING, PRODUCER_CODE);

		query.setResultClass(MSIG_USERS_MASTER.class);

		return (MSIG_USERS_MASTER) query.getObject();
	}

	public static MSIG_USERS_MASTER getUserNameForUserId(String USER_ID) {
		String queryText = "SELECT  USER_FULL_NAME FROM MSIG_USERS_MASTER WHERE USER_ID = :USER_ID";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID",
				QueryObject.PARAM_STRING, USER_ID);

		query.setResultClass(MSIG_USERS_MASTER.class);

		return (MSIG_USERS_MASTER) query.getObject();
	}

	public static BusObjectIterator<MSIG_BO_ACTIVITIES> getUsersWorkedOnCase(
			String CASE_ID) {
		String userId = MasterDataUtil.getUserName();

		String queryText = "SELECT DISTINCT A.USER_ID, B.USER_FULL_NAME FROM MSIG_BO_ACTIVITIES A, MSIG_USERS_MASTER B"
				+ " WHERE A.CASE_ID = :CASE_ID AND A.USER_ID = B.USER_ID AND A.USER_ID != '"
				+ userId
				+ "' AND A.MARK_FOR_DELETE IS NULL AND B.STATUS = 'ACTIVE'";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("CASE_ID", "MSIG_BO_ACTIVITIES.CASE_ID",
				QueryObject.PARAM_STRING, CASE_ID);

		query.setResultClass(MSIG_BO_ACTIVITIES.class);

		return query.getObjects();
	}

	public static BusObjectIterator<MSIG_BRANCH_LOB_ROLE_USER_MAPPING> getAccountHandlersFromBranch(
			String BRANCH_CODE) {

		String queryText = "SELECT DISTINCT A.USER_ID, B.USER_FULL_NAME, B.PRODUCER_CODE"
				+ " FROM MSIG_BRANCH_LOB_ROLE_USER_MAPPING A, MSIG_USERS_MASTER B "
				+ "WHERE A.USER_ID = B.USER_ID "
				+ "AND (B.PRODUCER_CODE != NULL OR B.PRODUCER_CODE != '') "
				+ "AND BRANCH_CODE = :BRANCH_CODE ORDER BY B.USER_FULL_NAME";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH_CODE",
				"MSIG_BRANCH_LOB_ROLE_USER_MAPPING.BRANCH_CODE",
				QueryObject.PARAM_STRING, BRANCH_CODE);
		query.setResultClass(MSIG_BRANCH_LOB_ROLE_USER_MAPPING.class);

		return query.getObjects();
	}

	public static int generateUsersMasterReport(int timezoneOffset)
			throws Exception {

		String queryText = "SELECT USER_ID,USER_FULL_NAME,USER_EMAIL,PRODUCER_CODE,STATUS,DESIGNATION,DID,AGENT_CODE,AGENCY_NAME,LAST_LOGON_DATE,FAX_NO,CREATED_BY,CREATED_ON,MODIFIED_BY,MODIFIED_ON FROM MSIG_USERS_MASTER";

		QueryObject query = new QueryObject(queryText);
		query.setResultClass(MSIG_USERS_MASTER.class);

		BusObjectIterator<MSIG_USERS_MASTER> userObjects = query.getObjects();

		if (userObjects == null || (userObjects != null && !userObjects.hasMoreElements())) {
			return MasterDataUtil
					.createFaultMessage("No records in DB to download excel file");
		}

		String[] paramNames = { "key" };
		String[] paramValues = { "/MSIG/configurations/shared-path-config.xml" };

		int response = MasterDataUtil.executeSOAPRequest(
				"http://schemas.cordys.com/1.0/xmlstore", "GetXMLObject",
				paramNames, paramValues);

		if (response == 0) {
			return MasterDataUtil
					.createFaultMessage("Shared Path Configuration file is missing");
		}

		String sharedFilePath = Node
				.getData(XPath
						.getFirstMatch(
								"descendant-or-self::Configuration/descendant-or-self::SharedPath",
								null, response));

		HSSFWorkbook workbookObject = GenerateReport.getWorkbookObjectNew(
				userObjects, "Users Master Report", new String[] { "User ID",
						"User Name", "User Email", "Producer Code", "Status", "Designation", "DID","Agent Code","Agency Name","Last Logged On", "Fax No", 
						"Created By", "Created On", "Modified By", "Modified On" },
						new String[] { "USER_ID", "USER_FULL_NAME", "USER_EMAIL",
						"PRODUCER_CODE", "STATUS","DESIGNATION", "DID","AGENT_CODE","AGENCY_NAME","LAST_LOGON_DATE", "FAXNO","CREATED_BY", "CREATED_ON",
						"MODIFIED_BY", "MODIFIED_ON" },
						new String[] { "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING",
						"STRING","DATE","STRING","STRING", "DATE", "STRING", "DATE" }, timezoneOffset);
		HSSFSheet sheet = workbookObject.getSheet("Users Master Report");
		int columnIndex = 0;
		while (columnIndex < 12) {
			sheet.autoSizeColumn(columnIndex++);
		}

		return GenerateReport.writeExcelFile(workbookObject, sharedFilePath, "Users Master Report");
	}

	public static boolean isProducerCodeExists(String PRODUCER_CODE, String USER_ID) {
		String queryText = "SELECT COUNT(*) AS TOTAL FROM MSIG_USERS_MASTER WHERE "
				+ "PRODUCER_CODE IS NOT NULL AND PRODUCER_CODE = :PRODUCER_CODE AND PRODUCER_CODE != '' AND USER_ID != :USER_ID";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("PRODUCER_CODE", "MSIG_USERS_MASTER.PRODUCER_CODE", QueryObject.PARAM_STRING, PRODUCER_CODE);
		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID", QueryObject.PARAM_STRING, USER_ID);

		query.setResultClass(MSIG_USERS_MASTER.class);

		BusObject respObj = query.getObject();
		int count = respObj.getIntProperty("TOTAL");

		return (count > 0);
	}

public static boolean isUserAndChannelExists(String DESIGNATION, String USER_ID) {
		String queryText = "SELECT COUNT(*) AS TOTAL FROM MSIG_USERS_MASTER WHERE "
				+ "  DESIGNATION= :DESIGNATION AND USER_ID = :USER_ID";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("DESIGNATION", "MSIG_USERS_MASTER.DESIGNATION", QueryObject.PARAM_STRING, DESIGNATION);
		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID", QueryObject.PARAM_STRING, USER_ID);

		query.setResultClass(MSIG_USERS_MASTER.class);

		BusObject respObj = query.getObject();
		int count = respObj.getIntProperty("TOTAL");

		return (count > 0);
	}


	public static MSIG_USERS_MASTER getUsersMasterObj(String USER_ID) {
		String queryText = "SELECT * FROM MSIG_USERS_MASTER WHERE BINARY_CHECKSUM(USER_ID) = BINARY_CHECKSUM(:USER_ID)";

		QueryObject query = new QueryObject(queryText);

		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID", QueryObject.PARAM_STRING, USER_ID);

		query.setResultClass(MSIG_USERS_MASTER.class);

		return (MSIG_USERS_MASTER) query.getObject();
	}

	public static BusObjectIterator<MSIG_USERS_MASTER> getUsersByApplicationName(String applicationName){
		String queryText = "SELECT DISTINCT MUM.USER_ID as code, MUM.USER_FULL_NAME as description FROM MSIG_USERS_MASTER MUM " +
				" INNER JOIN MSIG_BRANCH_LOB_ROLE_USER_MAPPING MBM ON MUM.USER_ID = MBM.USER_ID " + 
				" AND MBM.APPLICATION =:applicationName";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("applicationName", "MSIG_BRANCH_LOB_ROLE_USER_MAPPING.APPLICATION", QueryObject.PARAM_STRING, applicationName);
		query.setResultClass(MSIG_USERS_MASTER.class);
		query.setResultMapping(QueryObject.MAP_FLATTEN, null);
		return query.getObjects();
	}
	
	public static int updateAgentInfo(String USER_ID, String PARAM, boolean isPassword) throws Exception {
		
		String queryText = "UPDATE MSIG_USERS_MASTER SET ";
		if (isPassword) {
			queryText += "PASSWORD = :PARAM ";
		} else {
			queryText += "ACCEPTED_DATE = :PARAM ";
		}
		queryText += "WHERE USER_ID = :USER_ID";
		
		DMLStatement stmt = new DMLStatement(queryText);
		if (isPassword) {
			stmt.addParameter("PARAM", "MSIG_USERS_MASTER.PASSWORD", PARAM);
		} else {
			stmt.addParameter("PARAM", "MSIG_USERS_MASTER.ACCEPTED_DATE", PARAM);
		}
		stmt.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID", USER_ID);
		
		return stmt.executeDML();
	}

        // created method to fix redmine #1778 issue
	public static BusObjectIterator<com.msig.masterdata.MSIG_USERS_MASTER> GetEmailIdByUserId(
			String USER_ID, String USER_FULL_NAME) {
		//USER_ID = "%" + USER_ID + "%";
		USER_FULL_NAME = "%" + USER_FULL_NAME + "%";

		String queryText = "SELECT * FROM MSIG_USERS_MASTER WHERE USER_ID = :USER_ID AND USER_FULL_NAME LIKE :USER_FULL_NAME";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("USER_ID", "MSIG_USERS_MASTER.USER_ID",
				QueryObject.PARAM_STRING, USER_ID);
		query.addParameter("USER_FULL_NAME",
				"MSIG_USERS_MASTER.USER_FULL_NAME", QueryObject.PARAM_STRING,
				USER_FULL_NAME);

		query.setResultClass(MSIG_USERS_MASTER.class);
		return query.getObjects();
	}

        // created below method for issue 811 - outofoffice functionality.
	public static BusObjectIterator<com.msig.masterdata.MSIG_USERS_MASTER> getAllInactiveUsersList() {
		String queryText = "SELECT * FROM MSIG_USERS_MASTER WHERE STATUS = 'INACTIVE' AND OUT_FROM_DATE IS NOT NULL AND OUT_TO_DATE IS NOT NULL AND CONVERT(DATE,OUT_TO_DATE,102) < CONVERT(DATE,GETDATE(),102)";

		QueryObject query = new QueryObject(queryText);
		
		query.setResultClass(MSIG_USERS_MASTER.class);
		return query.getObjects();
	}

//Created below method for Redmine #1660 - Account handler by branch and application type 
public static BusObjectIterator<MSIG_BRANCH_LOB_ROLE_USER_MAPPING> getAccountHandlersByBranchAndApplication(
			String BRANCH_CODE,String APP_TYPE) {

		String queryText = "SELECT DISTINCT A.USER_ID, B.USER_FULL_NAME, B.PRODUCER_CODE"
				+ " FROM MSIG_BRANCH_LOB_ROLE_USER_MAPPING A, MSIG_USERS_MASTER B "
				+ "WHERE A.USER_ID = B.USER_ID "
				+ "AND (B.PRODUCER_CODE != NULL OR B.PRODUCER_CODE != '') "
				+ "AND A.BRANCH_CODE = :BRANCH_CODE  AND  A.APPLICATION= :APP_TYPE  ORDER BY B.USER_FULL_NAME";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH_CODE",
				"MSIG_BRANCH_LOB_ROLE_USER_MAPPING.BRANCH_CODE",
				QueryObject.PARAM_STRING, BRANCH_CODE);
               query.addParameter("APP_TYPE ",
				"MSIG_BRANCH_LOB_ROLE_USER_MAPPING.APPLICATION",
				QueryObject.PARAM_STRING, APP_TYPE);
		query.setResultClass(MSIG_BRANCH_LOB_ROLE_USER_MAPPING.class);

		return query.getObjects();
	}

}
