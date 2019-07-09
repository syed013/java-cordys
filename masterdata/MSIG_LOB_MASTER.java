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
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

public class MSIG_LOB_MASTER extends MSIG_LOB_MASTERBase {
	private static final String STATUS_INACTIVE = "INACTIVE";
	private static final String STATUS_ACTIVE = "ACTIVE";

	public MSIG_LOB_MASTER() {
		this((BusObjectConfig) null);
	}

	public MSIG_LOB_MASTER(BusObjectConfig config) {
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
			String queryText = "UPDATE MSIG_BRANCH_LOB_MAPPING SET STATUS = '" + STATUS_INACTIVE + "' WHERE LOB_CODE = '" + this.getLOB_CODE() + "'";
			
			DMLStatement dmlObj = new DMLStatement(queryText);
			dmlObj.execute();
			
			queryText = "UPDATE MSIG_BRANCH_LOB_ROLE_MAPPING SET STATUS = '" + STATUS_INACTIVE + "' WHERE LOB_CODE = '" + this.getLOB_CODE() + "'";
			dmlObj = new DMLStatement(queryText);
			
			dmlObj.execute();
			
			queryText = "UPDATE MSIG_BRANCH_LOB_ROLE_USER_MAPPING SET STATUS = '" + STATUS_INACTIVE + "' WHERE LOB_CODE = '" + this.getLOB_CODE() + "'";
			dmlObj = new DMLStatement(queryText);
			
			dmlObj.execute();
		}
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_LOB_MASTER> searchLOBMaster(
			String LOB_CODE, String LOB_NAME) {
		LOB_CODE = "%" + LOB_CODE + "%";
		LOB_NAME = "%" + LOB_NAME + "%";

		String queryText = "SELECT * FROM MSIG_LOB_MASTER WHERE LOB_CODE LIKE :LOB_CODE AND LOB_NAME LIKE :LOB_NAME";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("LOB_CODE", "MSIG_LOB_MASTER.LOB_CODE",
				QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("LOB_NAME", "MSIG_LOB_MASTER.LOB_NAME",
				QueryObject.PARAM_STRING, LOB_NAME);

		query.setResultClass(MSIG_LOB_MASTER.class);
		return query.getObjects();
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_LOB_MASTER> searchLOBMasterByApplication(
			String APPLICATION, String LOB_CODE, String LOB_NAME) {
		
		APPLICATION = "%" + APPLICATION + "%";
		LOB_CODE = "%" + LOB_CODE + "%";
		LOB_NAME = "%" + LOB_NAME + "%";

		String queryText = "SELECT * FROM MSIG_LOB_MASTER WHERE APPLICATION LIKE :APPLICATION AND LOB_CODE LIKE :LOB_CODE AND LOB_NAME LIKE :LOB_NAME ORDER BY APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("APPLICATION", "MSIG_LOB_MASTER.APPLICATION",
				QueryObject.PARAM_STRING, APPLICATION);
		query.addParameter("LOB_CODE", "MSIG_LOB_MASTER.LOB_CODE",
				QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("LOB_NAME", "MSIG_LOB_MASTER.LOB_NAME",
				QueryObject.PARAM_STRING, LOB_NAME);

		query.setResultClass(MSIG_LOB_MASTER.class);
		return query.getObjects();
	}

	public static MSIG_LOB_MASTER getMsigLOBRecordForName(String APPLICATION, String LOB_NAME) {
		String queryText = "SELECT * FROM MSIG_LOB_MASTER WHERE LOB_NAME = :LOB_NAME AND APPLICATION = :APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("LOB_NAME", "MSIG_LOB_MASTER.LOB_NAME", QueryObject.PARAM_STRING, LOB_NAME);
		query.addParameter("APPLICATION", "MSIG_LOB_MASTER.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);

		query.setResultClass(MSIG_LOB_MASTER.class);
		return (MSIG_LOB_MASTER) query.getObject();
	}

	public static int generateLOBMasterReport(int timezoneOffset)
			throws Exception {

		String queryText = "SELECT APPLICATION, LOB_CODE, LOB_NAME, LOB_DESCRIPTION, LOB_LEVEL, STATUS, CREATED_ON, CREATED_BY, MODIFIED_ON, MODIFIED_BY FROM MSIG_LOB_MASTER A ORDER BY APPLICATION";

		QueryObject query = new QueryObject(queryText);
		query.setResultClass(MSIG_LOB_MASTER.class);

		BusObjectIterator<MSIG_LOB_MASTER> objects = query.getObjects();

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

		HSSFWorkbook workbookObject = GenerateReport
				.getWorkbookObjectNew(objects, "LOB Master Report",
						new String[] { "Application", "LOB Code",
								"LOB Name", "LOB Description", "Level",
								"Status", "Created By", "Created On",
								"Modified By", "Modified On" }, new String[] {
								"APPLICATION", "LOB_CODE", "LOB_NAME",
								"LOB_DESCRIPTION", "LOB_LEVEL", "STATUS",
								"CREATED_BY", "CREATED_ON", "MODIFIED_BY",
								"MODIFIED_ON" }, new String[] { "STRING",
								"STRING", "STRING", "STRING", "STRING",
								"STRING", "STRING", "DATE", "STRING", "DATE" },
						timezoneOffset);
		HSSFSheet sheet = workbookObject.getSheet("LOB Master Report");
		int columnIndex = 0;
		while (columnIndex < 10) {
			sheet.autoSizeColumn(columnIndex++);
		}

		return GenerateReport.writeExcelFile(workbookObject, sharedFilePath,
				"LOB Master Report");
	}
	
	public static BusObjectIterator<MSIG_BRANCH_LOB_MAPPING> checkMappingsForLOB(String APPLICATION, String LOB_CODE) {
		String queryText = "SELECT * FROM MSIG_BRANCH_LOB_MAPPING WHERE LOB_CODE = :LOB_CODE AND APPLICATION = :APPLICATION";
		
		QueryObject query = new QueryObject(queryText);
		query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
		query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
		
		query.setResultClass(MSIG_BRANCH_LOB_MAPPING.class);
		
		return query.getObjects();
	}
	
	public static MSIG_LOB_MASTER getLOBMasterObj(String APPLICATION, String LOB_CODE) {
		String queryText = "SELECT * FROM MSIG_LOB_MASTER WHERE BINARY_CHECKSUM(APPLICATION) = BINARY_CHECKSUM(:APPLICATION) AND BINARY_CHECKSUM(LOB_CODE) = BINARY_CHECKSUM(:LOB_CODE)";
		
		QueryObject query = new QueryObject(queryText);
		
		query.addParameter("APPLICATION", "MSIG_LOB_MASTER.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
		query.addParameter("LOB_CODE", "MSIG_LOB_MASTER.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
		
		query.setResultClass(MSIG_LOB_MASTER.class);
		
		return (MSIG_LOB_MASTER) query.getObject();
	}
}
