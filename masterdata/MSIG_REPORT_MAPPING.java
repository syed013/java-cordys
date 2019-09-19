/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;


public class MSIG_REPORT_MAPPING extends MSIG_REPORT_MAPPINGBase
{
    public MSIG_REPORT_MAPPING()
    {
        this((BusObjectConfig)null);
    }

    public MSIG_REPORT_MAPPING(BusObjectConfig config)
    {
        super(config);
    }

public static BusObjectIterator<com.msig.masterdata.MSIG_REPORT_MAPPING> getReportsForCaseState(String APP, String PRODUCT, String RISK, String REPORT_TYPE, String ORIENTATION, String WORKFLOW_STATUS, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        final String APP_ID = APP;
		String queryText = "select RPT_FILE,ADDITIONAL_PARAMETERS,ADDITIONAL_PARAMETER_TYPE,ADDITIONAL_PARAMETERS_LABELS,ADDITIONAL_PARAMETER_ISREQUIRED,REPORT_NAME,REPORT_DATASOURCE,ADDITIONAL_PARAMETER_SELECT_FEILD_NAME,WORKFLOW_STATUS from \"MSIG_REPORT_MAPPING\" where ";
		
		// Handling to display only Bordereau related Reports
		if("Bordereau".equals(REPORT_TYPE)){
			
			queryText = queryText + " REPORT_TYPE LIKE :REPORT_TYPE AND ";
			REPORT_TYPE = "%" + REPORT_TYPE + "%";
			
			if(!"".equals(RISK)){
			  queryText = queryText + " (RISK LIKE 'ANY' or RISK LIKE :RISK) AND ";
			  RISK = "%" + RISK + "%";
			}
		}		
		else{
		
			if(!"".equals(PRODUCT)){
			  //queryText = queryText + " (PRODUCT LIKE 'ANY' or PRODUCT LIKE :PRODUCT) AND ";
			  //PRODUCT = "%" + PRODUCT + "%";
			 queryText = queryText + " (PRODUCT LIKE 'ANY' or PRODUCT =:PRODUCT) AND "; 
			}
			if(!"".equals(RISK)){
			   //queryText = queryText + " (RISK LIKE 'ANY' or RISK LIKE :RISK) AND ";
			   //RISK = "%" + RISK + "%";
			   queryText = queryText + " (RISK LIKE 'ANY' or RISK =:RISK) AND ";
			}
			if(!"".equals(REPORT_TYPE)){
			  queryText = queryText + " REPORT_TYPE = :REPORT_TYPE AND ";
			}
			if(!"".equals(ORIENTATION)){
			  queryText = queryText + " ORIENTATION = :ORIENTATION AND ";
			}
			if(!"".equals(WORKFLOW_STATUS)){
			  queryText = queryText + " (WORKFLOW_STATUS LIKE 'ANY' or WORKFLOW_STATUS LIKE :WORKFLOW_STATUS) AND ";
			  WORKFLOW_STATUS = "%" + WORKFLOW_STATUS + "%";
			  
			}
		}	
		
		queryText = queryText + "APP= :APP ORDER BY REPORT_NAME";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APP", "MSIG_REPORT_MAPPING.APP", QueryObject.PARAM_STRING, APP);//NOPMD
		query.addParameter("PRODUCT", "MSIG_REPORT_MAPPING.PRODUCT", QueryObject.PARAM_STRING, PRODUCT);//NOPMD
		query.addParameter("RISK", "MSIG_REPORT_MAPPING.RISK", QueryObject.PARAM_STRING, RISK);//NOPMD
		query.addParameter("REPORT_TYPE", "MSIG_REPORT_MAPPING.REPORT_TYPE", QueryObject.PARAM_STRING, REPORT_TYPE);//NOPMD
		query.addParameter("ORIENTATION", "MSIG_REPORT_MAPPING.ORIENTATION", QueryObject.PARAM_STRING, ORIENTATION);//NOPMD
		query.addParameter("WORKFLOW_STATUS", "MSIG_REPORT_MAPPING.WORKFLOW_STATUS", QueryObject.PARAM_STRING, WORKFLOW_STATUS);//NOPMD
		
		
		query.setResultClass(MSIG_REPORT_MAPPING.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

}