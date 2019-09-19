/*
  This class has been generated by the Code Generator
 */

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.cordys.cpc.bsf.busobject.DMLStatement;

public class MSIG_AWUS extends MSIG_AWUSBase {
	public MSIG_AWUS() {
		this((BusObjectConfig) null);
	}

	public MSIG_AWUS(BusObjectConfig config) {
		super(config);
	}

	static int CONDITION_ID;
	
	

	public static com.msig.masterdata.MSIG_AWUS getAWUSResult(String BRANCH,
			String AMOUNT, String LOB, String ITEM_GROUP, String TYPE)
			throws ScriptException {
		CONDITION_ID = 0;
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		if ("".equals(ITEM_GROUP)) // No conditional check on amount for round
									// robin
		{
			final BusObjectIterator<MSIG_AWUS_CONDITIONS> AWUSConditionsList = MSIG_AWUS_CONDITIONS
					.getMsigAwusConditionsObjects(0, 99999);
			while (AWUSConditionsList.hasMoreElements()) {
				MSIG_AWUS_CONDITIONS AWUSConditionObj = (MSIG_AWUS_CONDITIONS) AWUSConditionsList
						.nextElement();
				String condition = AMOUNT + AWUSConditionObj.getCONDITION();
				if ((boolean) engine.eval(condition)) {
					CONDITION_ID = AWUSConditionObj.getCONDITION_ID();
					break;
				}
			}
		}

		String queryText = "SELECT TOP 1 AWUS_ID,ITEM_ID,ITEM_NAME,BRANCH,LOB,ITEM_GROUP,EMAIL_ID FROM MSIG_AWUS  WHERE ";

		if (!"".equals(ITEM_GROUP)) {
			queryText = queryText + " ITEM_GROUP = :ITEM_GROUP AND ";
			queryText = queryText + " IS_ROUND_ROBIN = 'True' AND ";
			queryText = queryText + " ITEM_ID in (select USER_ID from MSIG_USERS_MASTER where STATUS='ACTIVE') AND ";
		}

		if (CONDITION_ID != 0) {
			queryText = queryText + " CONDITION_ID = " + "'" + CONDITION_ID
					+ "'" + " AND ";
		}

		queryText = queryText
				+ "BRANCH LIKE :BRANCH AND LOB LIKE :LOB AND IS_LAST_SERVED_ITEM != 'True' AND TYPE = :TYPE ORDER BY DIFF ASC";
		BRANCH = "%" + BRANCH + "%";
		LOB = "%" + LOB + "%";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH", "MSIG_AWUS.BRANCH",
				QueryObject.PARAM_STRING, BRANCH);
		query.addParameter("LOB", "MSIG_AWUS.LOB", QueryObject.PARAM_STRING,
				LOB);
		query.addParameter("ITEM_GROUP", "MSIG_AWUS.ITEM_GROUP",
				QueryObject.PARAM_STRING, ITEM_GROUP);
		query.addParameter("TYPE", "MSIG_AWUS.TYPE", QueryObject.PARAM_STRING,
				TYPE);
		query.setResultClass(MSIG_AWUS.class);
		return (MSIG_AWUS) query.getObject();
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_AWUS> getAllAWUSResults(
			String BRANCH, String AMOUNT, String LOB, String ITEM_GROUP,
			String TYPE) throws ScriptException {
		CONDITION_ID = 0;
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		if ("".equals(ITEM_GROUP)) // No conditional check on amount for round
									// robin
		{
			final BusObjectIterator<MSIG_AWUS_CONDITIONS> AWUSConditionsList = MSIG_AWUS_CONDITIONS
					.getMsigAwusConditionsObjects(0, 99999);
			while (AWUSConditionsList.hasMoreElements()) {
				MSIG_AWUS_CONDITIONS AWUSConditionObj = (MSIG_AWUS_CONDITIONS) AWUSConditionsList
						.nextElement();
				String condition = AMOUNT + AWUSConditionObj.getCONDITION();
				if ((boolean) engine.eval(condition)) {
					CONDITION_ID = AWUSConditionObj.getCONDITION_ID();
					break;
				}
			}
		}

		String queryText = "SELECT * FROM MSIG_AWUS  WHERE ";

		if (!"".equals(ITEM_GROUP)) {
			queryText = queryText + " ITEM_GROUP = :ITEM_GROUP AND ";
			queryText = queryText + " IS_ROUND_ROBIN = 'True' AND ";
		}

		if (CONDITION_ID != 0) {
			queryText = queryText + " CONDITION_ID = " + "'" + CONDITION_ID
					+ "'" + " AND ";
		}

		queryText = queryText
				+ "BRANCH LIKE :BRANCH AND LOB LIKE :LOB AND TYPE = :TYPE";
		BRANCH = "%" + BRANCH + "%";
		LOB = "%" + LOB + "%";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("BRANCH", "MSIG_AWUS.BRANCH",
				QueryObject.PARAM_STRING, BRANCH);
		query.addParameter("LOB", "MSIG_AWUS.LOB", QueryObject.PARAM_STRING,
				LOB);
		query.addParameter("ITEM_GROUP", "MSIG_AWUS.ITEM_GROUP",
				QueryObject.PARAM_STRING, ITEM_GROUP);
		query.addParameter("TYPE", "MSIG_AWUS.TYPE", QueryObject.PARAM_STRING,
				TYPE);
		query.setResultClass(MSIG_AWUS.class);
		return query.getObjects();
	}

	public static String resetAWUS() {
		final BusObjectIterator<MSIG_AWUS> AWUSList = MSIG_AWUS.getMsigAwusObjects(0, 99999);
		while (AWUSList.hasMoreElements()) { 
			MSIG_AWUS AWUSObj = (MSIG_AWUS) AWUSList.nextElement();
			if("Adjuster".equalsIgnoreCase(AWUSObj.getTYPE())){ // new code added condition inorder to reset only Adjuster data.
				AWUSObj.setPROBABILITY(0);
				AWUSObj.setNO_OF_TASKS(0);
				AWUSObj.setDIFF(-AWUSObj.getITEM_WEIGHT());
				AWUSObj.setIS_LAST_SERVED_ITEM(false);
				AWUSObj.setTOTAL_TASKS(0);
				//AWUSObj.setTYPE("Adjuster");
				AWUSObj.update();
				AWUSObj.getObjectManager()._commitTransactionDirect(true);
			}
		}
		return "success";
	}



	public static String updateAWUS(int AWUS_ID, String BRANCH, String AMOUNT,
			String LOB, String ITEM_GROUP, String TYPE) throws ScriptException {
		final BusObjectIterator<MSIG_AWUS> AWUSList = MSIG_AWUS
				.getAllAWUSResults(BRANCH, AMOUNT, LOB, ITEM_GROUP, TYPE);
		while (AWUSList.hasMoreElements()) {
			MSIG_AWUS AWUSObj = (MSIG_AWUS) AWUSList.nextElement();
			Double newProbability = AWUSObj.getPROBABILITY()
					+ AWUSObj.getITEM_WEIGHT();
			Long newTotalNoOfTasks = AWUSObj.getTOTAL_TASKS() + 1;
			AWUSObj.setPROBABILITY(newProbability);
			AWUSObj.setTOTAL_TASKS(newTotalNoOfTasks);

			if (AWUSObj.getAWUS_ID() == AWUS_ID) {
				AWUSObj.setNO_OF_TASKS(AWUSObj.getNO_OF_TASKS() + 1);
				Long newTaskCount = AWUSObj.getNO_OF_TASKS();
				AWUSObj.setDIFF(newTaskCount - newProbability);
				AWUSObj.setIS_LAST_SERVED_ITEM(true);
			} else {
				AWUSObj.setDIFF(AWUSObj.getNO_OF_TASKS() - newProbability);
				AWUSObj.setIS_LAST_SERVED_ITEM(false);
			}
			AWUSObj.update();
			AWUSObj.getObjectManager()._commitTransactionDirect(true);
		}

		return "success";
	}
	
	public static String revokeAWUS(int AWUS_ID, String BRANCH, String AMOUNT,
			String LOB, String ITEM_GROUP, String TYPE) throws ScriptException {
		final BusObjectIterator<MSIG_AWUS> AWUSList = MSIG_AWUS
				.getAllAWUSResults(BRANCH, AMOUNT, LOB, ITEM_GROUP, TYPE);
		while (AWUSList.hasMoreElements()) {
			MSIG_AWUS AWUSObj = (MSIG_AWUS) AWUSList.nextElement();
			Double newProbability = AWUSObj.getPROBABILITY()
					- AWUSObj.getITEM_WEIGHT();
			Long newTotalNoOfTasks = AWUSObj.getTOTAL_TASKS() - 1;
			AWUSObj.setPROBABILITY(newProbability);
			AWUSObj.setTOTAL_TASKS(newTotalNoOfTasks);

			if (AWUSObj.getAWUS_ID() == AWUS_ID) {
				AWUSObj.setNO_OF_TASKS(AWUSObj.getNO_OF_TASKS() - 1);
				Long newTaskCount = AWUSObj.getNO_OF_TASKS();
				AWUSObj.setDIFF(newTaskCount - newProbability);
				AWUSObj.setIS_LAST_SERVED_ITEM(true);
			} else {
				AWUSObj.setDIFF(AWUSObj.getNO_OF_TASKS() - newProbability);
				AWUSObj.setIS_LAST_SERVED_ITEM(false);
			}
			AWUSObj.update();
			AWUSObj.getObjectManager()._commitTransactionDirect(true);
		}

		return "success";
	}

public static BusObjectIterator<MSIG_AWUS> getRecordsForRoleCode(
			String ITEM_GROUP) {
		

		String queryText = "SELECT * FROM MSIG_AWUS WHERE MSIG_AWUS.ITEM_GROUP = :ITEM_GROUP";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ITEM_GROUP", "MSIG_AWUS.ITEM_GROUP",
				QueryObject.PARAM_STRING, ITEM_GROUP);

		query.setResultClass(MSIG_AWUS.class);

		return query.getObjects();
	}
	
	public static int  getCountOfRecordsForRoleCode(String ITEM_GROUP) {
		

		String queryText = "SELECT COUNT (MSIG_AWUS.AWUS_ID) AS COUNT FROM MSIG_AWUS WHERE MSIG_AWUS.ITEM_GROUP = :ITEM_GROUP";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ITEM_GROUP", "MSIG_AWUS.ITEM_GROUP",
				QueryObject.PARAM_STRING, ITEM_GROUP);

		query.setResultClass(MSIG_AWUS.class);
		int resultNode = query.execute().getDatasetNode();
    	String count = Node.getData(XPath.getFirstMatch(".//COUNT", null,resultNode));
    	Node.delete(resultNode);
    	resultNode = 0;  
    	return Integer.parseInt(count);
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteRecords(String ITEM_GROUP,String ITEM_ID){
		
		 
		
        

		String queryText = "DELETE FROM MSIG_AWUS WHERE MSIG_AWUS.ITEM_GROUP=:ITEM_GROUP AND MSIG_AWUS.ITEM_ID =:ITEM_ID";
		DMLStatement dml = new DMLStatement(queryText );
		dml.addParameter("ITEM_GROUP", "MSIG_AWUS.ITEM_GROUP",ITEM_GROUP );
		dml.addParameter("ITEM_ID", "MSIG_AWUS.ITEM_ID",ITEM_ID );
        dml.execute();
    }
 public static com.msig.masterdata.MSIG_AWUS getAwusIdForSelectedAdjuster(String ITEM_ID, String BRANCH, String LOB, String AMNT)throws ScriptException
    {
		CONDITION_ID = 0;
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		final BusObjectIterator<MSIG_AWUS_CONDITIONS> AWUSConditionsList = MSIG_AWUS_CONDITIONS
					.getMsigAwusConditionsObjects(0, 99999);
			while (AWUSConditionsList.hasMoreElements()) {
				MSIG_AWUS_CONDITIONS AWUSConditionObj = (MSIG_AWUS_CONDITIONS) AWUSConditionsList
						.nextElement();
				String condition = AMNT + AWUSConditionObj.getCONDITION();
				if ((boolean) engine.eval(condition)) {
					CONDITION_ID = AWUSConditionObj.getCONDITION_ID();
					break;
				}
			}
        String queryText = "SELECT AWUS_ID FROM MSIG_AWUS  WHERE ITEM_ID = :ITEM_ID AND BRANCH = :BRANCH AND LOB LIKE :LOB";
		LOB = "%" + LOB + "%";
		if (CONDITION_ID != 0) {
			queryText = queryText + " AND CONDITION_ID = " + "'" + CONDITION_ID
					+ "'";
		}
		
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ITEM_ID", "MSIG_AWUS.ITEM_ID",QueryObject.PARAM_STRING, ITEM_ID);
		query.addParameter("BRANCH", "MSIG_AWUS.BRANCH",QueryObject.PARAM_STRING, BRANCH);
		query.addParameter("LOB", "MSIG_AWUS.LOB",QueryObject.PARAM_STRING, LOB);
		

		query.setResultClass(MSIG_AWUS.class);
		return (MSIG_AWUS) query.getObject();
    }

public static String resetAWUSForClaimsHandler() {
		final BusObjectIterator<MSIG_AWUS> AWUSList = MSIG_AWUS.getMsigAwusObjects(0, 99999);
		while (AWUSList.hasMoreElements()) { 
			MSIG_AWUS AWUSObj = (MSIG_AWUS) AWUSList.nextElement();
			if("claimsHandler".equalsIgnoreCase(AWUSObj.getTYPE())){ // new code added condition inorder to reset only claimshandler data.
				AWUSObj.setPROBABILITY(0);
				AWUSObj.setNO_OF_TASKS(0);
				AWUSObj.update();
				AWUSObj.getObjectManager()._commitTransactionDirect(true);
			}
		}
		return "success";
	}



}