/*
  This class has been generated by the Code Generator
 */

package com.msig.masterdata;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.DMLStatement;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.util.TupleHandling;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

public class MSIG_BRANCH_LOB_ROLE_MAPPING extends
MSIG_BRANCH_LOB_ROLE_MAPPINGBase {
	private static final CordysLogger logger = CordysLogger
			.getCordysLogger(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
	private static final String STATUS_INACTIVE = "INACTIVE";
	private static final String STATUS_ACTIVE = "ACTIVE";

	public MSIG_BRANCH_LOB_ROLE_MAPPING() {
		this((BusObjectConfig) null);
	}

	public MSIG_BRANCH_LOB_ROLE_MAPPING(BusObjectConfig config) {
		super(config);
	}

	@Override
	public void onBeforeInsert() {
		super.onBeforeInsert();

		if (this.getSTATUS() == null
				|| (this.getSTATUS() != null && this.getSTATUS().equals(""))) {
			this.setSTATUS(STATUS_ACTIVE);
		}

		this.setCREATED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setCREATED_ON(new Date());
		this.setMODIFIED_ON(new Date());
	}

	@Override
	public void onBeforeUpdate() {
		super.onBeforeUpdate();
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_ON(new Date());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onAfterUpdate() {
		super.onAfterUpdate();

		String status = this.getSTATUS();
		if (status.equals(STATUS_INACTIVE)) {
			String queryText = "UPDATE MSIG_BRANCH_LOB_ROLE_USER_MAPPING SET STATUS = '" + STATUS_INACTIVE + "' "
					+ "WHERE BRANCH_CODE = '" + this.getBRANCH_CODE() + "' "
					+ "AND LOB_CODE = '" + this.getLOB_CODE() + "'"
					+ " AND ROLE_CODE = '" + this.getROLE_CODE() + "'";
			DMLStatement dmlObj = new DMLStatement(queryText);

			dmlObj.execute();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onAfterDelete() {
		super.onAfterDelete();

		String roleCode = this.getBRANCH_CODE() + "_" + this.getLOB_CODE()
				+ "_" + this.getROLE_CODE();
		try {
			MasterDataUtil.removeRoleFromLDAP(roleCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// TODO store this info somewhere in table for further use
		}
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getRolesForBranchAndLOBCode(
			String BRANCH_CODE, String LOB_CODE, String APPLICATION) {
		String queryText = "SELECT DISTINCT A.*, B.BRANCH_NAME, C.LOB_NAME, D.ROLE_NAME FROM MSIG_BRANCH_LOB_ROLE_MAPPING A, MSIG_BRANCH_MASTER B, MSIG_LOB_MASTER C, MSIG_ROLES_MASTER D WHERE A.BRANCH_CODE = :BRANCH_CODE AND A.LOB_CODE = :LOB_CODE";
		queryText += " AND A.BRANCH_CODE = B.BRANCH_CODE AND A.LOB_CODE = C.LOB_CODE AND A.ROLE_CODE = D.ROLE_CODE AND A.APPLICATION = :APPLICATION AND A.APPLICATION = B.APPLICATION AND A.APPLICATION = C.APPLICATION AND A.APPLICATION = D.APPLICATION AND A.APPLICATION = B.APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
		query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
		return query.getObjects();
	}

	// getMappingObejctsForRoleCode
	public static BusObjectIterator<MSIG_BRANCH_LOB_ROLE_MAPPING> getMappingObejctsForRoleCode(
			String ROLE_CODE) {
		String queryText = "SELECT * FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE ROLE_CODE = :ROLE_CODE";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("ROLE_CODE",
				"MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE",
				QueryObject.PARAM_STRING, ROLE_CODE);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);

		return query.getObjects();
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_USER_MAPPING> checkMappedUsersForBranchLobAndRole(
			String BRANCH_CODE, String LOB_CODE, String ROLE_CODE, String APPLICATION) {

		String queryText = "SELECT * FROM MSIG_BRANCH_LOB_ROLE_USER_MAPPING WHERE BRANCH_CODE = :BRANCH_CODE AND LOB_CODE = :LOB_CODE AND ROLE_CODE = :ROLE_CODE AND APPLICATION = :APPLICATION";

		QueryObject query = new QueryObject(queryText);

		query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_USER_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_USER_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("ROLE_CODE", "MSIG_BRANCH_LOB_ROLE_USER_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);
		query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_USER_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_USER_MAPPING.class);

		return query.getObjects();
	}

	public static MSIG_BRANCH_LOB_ROLE_MAPPING getMsigBranchLOBRoleObject(
			String BRANCH_CODE, String LOB_CODE, String ROLE_CODE) {
		String queryText = "SELECT * FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE BRANCH_CODE = :BRANCH_CODE AND LOB_CODE = :LOB_CODE AND ROLE_CODE = :ROLE_CODE";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH_CODE",
				"MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE",
				QueryObject.PARAM_STRING, BRANCH_CODE);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE",
				QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("ROLE_CODE",
				"MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE",
				QueryObject.PARAM_STRING, ROLE_CODE);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);

		return (MSIG_BRANCH_LOB_ROLE_MAPPING) query.getObject();
	}

	public static BusObjectIterator<MSIG_BRANCH_LOB_ROLE_MAPPING> getRolesForBranchLobRoleByApplication(String BRANCH_CODE, String LOB_CODE) {

		String queryText = "SELECT APPLICATION FROM MSIG_LOB_MASTER WHERE LOB_CODE = :LOB_CODE";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("LOB_CODE", "MSIG_LOB_MASTER.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);

		query.setResultClass(MSIG_LOB_MASTER.class);
		MSIG_LOB_MASTER lobbObject = (MSIG_LOB_MASTER) query.getObject();

		StringBuffer lobApplication = new StringBuffer();
		if (lobbObject.getAPPLICATION().equals("BOTH")) {
			lobApplication.append("'BOTH','CMS','BMS'");
		} else {
			lobApplication.append("'" + lobbObject.getAPPLICATION() + "'");
		}

		queryText = "SELECT A.BRANCH_CODE, A.LOB_CODE, A.ROLE_CODE, D.APPLICATION FROM MSIG_BRANCH_LOB_ROLE_MAPPING A, "
				+ "MSIG_BRANCH_MASTER B, MSIG_LOB_MASTER C, MSIG_ROLES_MASTER D "
				+ "WHERE A.BRANCH_CODE = :BRANCH_CODE AND A.LOB_CODE = :LOB_CODE AND A.BRANCH_CODE = B.BRANCH_CODE AND "
				+ "A.LOB_CODE = C.LOB_CODE AND A.ROLE_CODE = D.ROLE_CODE AND D.APPLICATION IN (" + lobApplication.toString() + ")";

		query = new QueryObject(queryText);
		query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE",
				QueryObject.PARAM_STRING, BRANCH_CODE);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE",
				QueryObject.PARAM_STRING, LOB_CODE);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);

		return query.getObjects();
	}

	public static int generateBranchLOBRoleReport(int timezoneOffset)
			throws Exception {

		String queryText = "SELECT DISTINCT A.*, B.BRANCH_NAME, C.LOB_NAME, D.ROLE_NAME FROM MSIG_BRANCH_LOB_ROLE_MAPPING A, MSIG_BRANCH_MASTER B, MSIG_LOB_MASTER C, MSIG_ROLES_MASTER D WHERE A.BRANCH_CODE = B.BRANCH_CODE AND A.LOB_CODE = C.LOB_CODE AND A.ROLE_CODE = D.ROLE_CODE AND A.APPLICATION = B.APPLICATION AND A.APPLICATION = C.APPLICATION AND A.APPLICATION = D.APPLICATION AND A.APPLICATION = B.APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);

		BusObjectIterator<MSIG_BRANCH_LOB_ROLE_MAPPING> objects = query
				.getObjects();

		if (objects == null) {
			return MasterDataUtil.createFaultMessage("No records in DB to download excel file");
		}

		String[] paramNames = { "key" };
		String[] paramValues = { "/MSIG/configurations/shared-path-config.xml" };

		int response = MasterDataUtil.executeSOAPRequest("http://schemas.cordys.com/1.0/xmlstore", "GetXMLObject", paramNames, paramValues);

		if (response == 0) {
			return MasterDataUtil.createFaultMessage("Shared Path Configuration file is missing");
		}

		String sharedFilePath = Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::SharedPath", null, response));

		HSSFWorkbook workbookObject = GenerateReport.getWorkbookObjectNew(
				objects, "Branch LOB Role Report", new String[] { "Application",
						"Branch Code", "Branch Name", "LOB Code", "LOB Name",
						"Role Code", "Role Name", "Status", "Created By",
						"Created On", "Modified By", "Modified On" },
						new String[] { "APPLICATION", "BRANCH_CODE", "BRANCH_NAME", "LOB_CODE",
						"LOB_NAME", "ROLE_CODE", "ROLE_NAME", "STATUS",
						"CREATED_BY", "CREATED_ON", "MODIFIED_BY",
				"MODIFIED_ON" }, new String[] { "STRING", "STRING", "STRING",
						"STRING", "STRING", "STRING", "STRING", "STRING",
						"STRING", "DATE", "STRING", "DATE" }, timezoneOffset);
		HSSFSheet sheet = workbookObject.getSheet("Branch LOB Role Report");
		int columnIndex = 0;
		while (columnIndex < 12) {
			sheet.autoSizeColumn(columnIndex++);
		}

		return GenerateReport.writeExcelFile(workbookObject, sharedFilePath,
				"Branch LOB Role Report");
	}

	public static MSIG_BRANCH_LOB_ROLE_MAPPING checkIfRoleExists(String BRANCH_CODE, String LOB_CODE, String ROLE_CODE, String APPLICATION) {
		String queryText = "SELECT * FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE BRANCH_CODE = :BRANCH_CODE AND LOB_CODE = :LOB_CODE AND ROLE_CODE = :ROLE_CODE AND APPLICATION = :APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("ROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);
		query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);

		query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);

		return (MSIG_BRANCH_LOB_ROLE_MAPPING) query.getObject();
	}
	
	public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getRolesForAppBranchAndLOBCode(String APPLICATION, String BRANCH_CODE, String LOB_CODE) {
		
		if ("CMS".equals(APPLICATION)) {
			
			String queryText = "SELECT DISTINCT " +
			"CASE " +
			"	WHEN BRANCH LIKE '%,%' THEN :BRANCH_CODE " +
			"	ELSE BRANCH " + 
			"END AS BRANCH_CODE, " +
			"CASE " + 
			"	WHEN A.LOB_CODE LIKE '%FIR%' THEN :LOB_CODE " +
			"	ELSE A.LOB_CODE " +
			"END AS LOB_CODE," +
			"A.ROLE_CODE, ROLE_NAME, SEQUENCE " +
			"FROM MSIG_ROLES_PRIORITY_SEQ A " +
			"JOIN MSIG_BRANCH_LOB_ROLE_MAPPING B ON A.APPLICATION = B.APPLICATION " +
			"JOIN MSIG_ROLES_MASTER C ON A.ROLE_CODE = C.ROLE_CODE " +
			"WHERE (BRANCH LIKE :BRANCH OR BRANCH = 'ANY') " +
			"AND A.APPLICATION = 'CMS' AND BUSINESS_FUNCTION = 'CLAIMS_SETTLEMENT' " +
			"AND A.LOB_CODE LIKE '%FIR%' " +
			"ORDER BY SEQUENCE";
			
			QueryObject query = new QueryObject(queryText);
			query.addParameter("BRANCH", "MSIG_ROLES_PRIORITY_SEQ.BRANCH", QueryObject.PARAM_STRING, "%" + BRANCH_CODE + "%");
			//query.addParameter("LOB", "MSIG_ROLES_PRIORITY_SEQ.LOB", QueryObject.PARAM_STRING, "%" + LOB_CODE + "%");
			
			query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
			query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
			
			query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
			
			return query.getObjects();
		} else {
			String queryText = "SELECT DISTINCT A.APPLICATION, A.BRANCH_CODE, A.LOB_CODE, A.ROLE_CODE, B.ROLE_NAME FROM MSIG_BRANCH_LOB_ROLE_MAPPING A JOIN MSIG_ROLES_MASTER B ON A.ROLE_CODE = B.ROLE_CODE AND A.APPLICATION = B.APPLICATION WHERE A.BRANCH_CODE = :BRANCH_CODE AND A.LOB_CODE = :LOB_CODE AND A.APPLICATION = :APPLICATION AND A.ROLE_CODE NOT IN ('BR','CINQ','CLN','CLNF')";
	
			QueryObject query = new QueryObject(queryText);
			query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
			query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
			query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
	
			query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
			return query.getObjects();
		}
	}
	public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getRolesForBranchlob(String BRANCH_CODE, String MRPS_BRANCH, String LOB_CODE, String APPLICATION, String CURRENT_ROLE){
		BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> bitr = null;  
        String queryText = "";
        String sequence = "";
		if ("BMS".equals(APPLICATION)) {
			 sequence = "(SELECT SEQUENCE FROM MSIG_ROLES_PRIORITY_SEQ WHERE APPLICATION = 'BMS' AND "
		    		+ " BRANCH LIKE :BRANCH AND LOB_CODE LIKE :MRPS_LOB_CODE AND ROLE_CODE = :CURRENT_ROLE_CODE)";	
			if(CURRENT_ROLE!= null && (CURRENT_ROLE.startsWith("BH") || CURRENT_ROLE.equals("AHRN")))
				  sequence = "0";
			
		    queryText =  "select DISTINCT MBLRM.BRANCH_CODE,MBLRM.LOB_CODE,MBLRM.ROLE_CODE,MBLRM.BRANCH_CODE+'_'+MBLRM.LOB_CODE+'_'+MBLRM.ROLE_CODE AS ROLE,MRPS.SEQUENCE from MSIG_BRANCH_LOB_ROLE_MAPPING MBLRM,MSIG_ROLES_PRIORITY_SEQ MRPS WHERE "
		    		+ " MBLRM.APPLICATION = 'BMS'  AND MBLRM.LOB_CODE = :LOB_CODE AND MBLRM.BRANCH_CODE = :BRANCH_CODE  "
		    		+ " AND MRPS.APPLICATION = 'BMS'  AND MRPS.BRANCH LIKE :BRANCH AND MRPS.LOB_CODE LIKE :MRPS_LOB_CODE AND MRPS.ROLE_CODE = MBLRM.ROLE_CODE AND  "
		    		+ " MRPS.SEQUENCE > "+sequence+" ";	 			
	        
		   if(CURRENT_ROLE!= null && CURRENT_ROLE.equalsIgnoreCase("CEO"))// CEO can transfer to DCEO
		        queryText += " UNION SELECT BRANCH_CODE,LOB_CODE,ROLE_CODE,BRANCH_CODE+'_'+LOB_CODE+'_'+ROLE_CODE AS ROLE,998 "
		        		+ "FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE ROLE_CODE = 'DCEO' AND APPLICATION = 'BMS' AND LOB_CODE = :LOB_CODE AND BRANCH_CODE = :BRANCH_CODE";	
		  
		    if(CURRENT_ROLE!= null && !CURRENT_ROLE.equalsIgnoreCase("RHCREP"))//To Add RHCREP which is the highest role
	        queryText += " UNION SELECT BRANCH_CODE,LOB_CODE,ROLE_CODE,BRANCH_CODE+'_'+LOB_CODE+'_'+ROLE_CODE AS ROLE,999 "
	        		+ "FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE ROLE_CODE = 'RHCREP' AND APPLICATION = 'BMS' AND LOB_CODE = :LOB_CODE AND BRANCH_CODE = :BRANCH_CODE";	
	    
	        queryText += " ORDER BY MRPS.SEQUENCE";        	
			}
			
			else if("CMS".equals(APPLICATION)){
			 queryText = "SELECT DISTINCT BRANCH_CODE, LOB_CODE, ROLE_CODE,BRANCH_CODE+'_'+LOB_CODE+'_'+ROLE_CODE AS ROLE  FROM MSIG_BRANCH_LOB_ROLE_MAPPING WHERE "
			 		+ "BRANCH_CODE = :BRANCH_CODE AND LOB_CODE = :LOB_CODE AND APPLICATION = :APPLICATION AND ROLE_CODE IN  ('SCH', 'LCH', 'CORP')";
			}
		    
			QueryObject query = new QueryObject(queryText);		
			query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);
			query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
			query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
			query.addParameter("CURRENT_ROLE_CODE", "MSIG_ROLES_PRIORITY_SEQ.ROLE_CODE", QueryObject.PARAM_STRING, CURRENT_ROLE);
			query.addParameter("MRPS_LOB_CODE", "MSIG_ROLES_PRIORITY_SEQ.LOB_CODE", QueryObject.PARAM_STRING, "%"+LOB_CODE+"%");
			query.addParameter("BRANCH", "MSIG_ROLES_PRIORITY_SEQ.BRANCH", QueryObject.PARAM_STRING, "%"+MRPS_BRANCH+"%");
	                                       				
			query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);		
			bitr =  query.getObjects();
			return bitr;
	}
	
	  public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getAppRolesForBranchAndLob(String BRANCH_CODE, String LOB_CODE, String APPLICATION, String CURRENT_ROLE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
		BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> bitr = null;  
        String queryText = "";
        String sequence = "";
		if(CURRENT_ROLE!=null && !CURRENT_ROLE.equals("")){
			CURRENT_ROLE = CURRENT_ROLE.substring(CURRENT_ROLE.lastIndexOf("_")+1, CURRENT_ROLE.length());
		}		
		bitr = getRolesForBranchlob(BRANCH_CODE,BRANCH_CODE, LOB_CODE, APPLICATION, CURRENT_ROLE);
		boolean resultempty = false;
		boolean onlyRHCREPDCEO = true;
		if("BMS".equals(APPLICATION) && !bitr.hasMoreElements()){
			resultempty = true;
		}
		else if("BMS".equals(APPLICATION)){
			while(bitr.hasMoreElements()){
			MSIG_BRANCH_LOB_ROLE_MAPPING branchRoleObj = bitr.nextElement();	
			if(branchRoleObj.getROLE_CODE()!=null && !branchRoleObj.getROLE_CODE().equalsIgnoreCase("RHCREP") && (!CURRENT_ROLE.equalsIgnoreCase("CEO") && !branchRoleObj.getROLE_CODE().equalsIgnoreCase("DCEO")))// RHCREP and DCEO(WHEN CURRENT ROLE IS CEO) DEFAULT RESULT			  
				onlyRHCREPDCEO = false;
			}
		}		
		
		//logger.error("singleRHCREP"+singleRHCREP+" resultempty" +resultempty);
			
		if("BMS".equals(APPLICATION)){ 
		  if(resultempty||onlyRHCREPDCEO)//ANY Branch
			bitr = getRolesForBranchlob(BRANCH_CODE,"ANY", LOB_CODE, APPLICATION, CURRENT_ROLE);		
		 else
			bitr = getRolesForBranchlob(BRANCH_CODE, BRANCH_CODE, LOB_CODE, APPLICATION, CURRENT_ROLE);
		}
		return bitr;
    }

	
}

