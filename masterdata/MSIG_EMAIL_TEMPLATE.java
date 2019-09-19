/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;


public class MSIG_EMAIL_TEMPLATE extends MSIG_EMAIL_TEMPLATEBase
{
    public MSIG_EMAIL_TEMPLATE()
    {
        this((BusObjectConfig)null);
    }

    public MSIG_EMAIL_TEMPLATE(BusObjectConfig config)
    {
        super(config);
    }

    public static com.msig.masterdata.MSIG_EMAIL_TEMPLATE GetEmailTemplateObject(String TEMPLATE_NAME)
    {
		 String queryText = "select * from \"MSIG_EMAIL_TEMPLATE\" where TEMPLATE_NAME= :TEMPLATE_NAME ";
		 QueryObject query = new QueryObject(queryText);
		 query.addParameter("TEMPLATE_NAME", "MSIG_EMAIL_TEMPLATE.TEMPLATE_NAME", QueryObject.PARAM_STRING, TEMPLATE_NAME);//NOPMD
		 query.setResultClass(MSIG_EMAIL_TEMPLATE.class);
         return (MSIG_EMAIL_TEMPLATE) query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_EMAIL_TEMPLATE> getEmailTemplatesForCase(String TEMPLATE_NAME, String WORKFLOW_STATUS, String APP, String PRODUCT, String RISK, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_EMAIL_TEMPLATE\" where ";
		
		if(!"".equals(PRODUCT)){
	      queryText = queryText + " (PRODUCT LIKE 'ANY' or PRODUCT LIKE :PRODUCT) AND ";
		  PRODUCT = "%" + PRODUCT + "%";
	    }
		if(!"".equals(RISK)){
	      queryText = queryText + " (RISK LIKE 'ANY' or RISK LIKE :RISK) AND ";
		  RISK = "%" + RISK + "%";
	    }
		if(!"".equals(TEMPLATE_NAME)){
	      queryText = queryText + " TEMPLATE_NAME = :TEMPLATE_NAME AND ";
	    }
		
		if(!"".equals(WORKFLOW_STATUS)){
	      queryText = queryText + " (WORKFLOW_STATUS LIKE 'ANY' or WORKFLOW_STATUS LIKE :WORKFLOW_STATUS) AND ";
		  WORKFLOW_STATUS = "%" + WORKFLOW_STATUS + "%";
	    }
		
		
		queryText = queryText + "APP= :APP";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APP", "MSIG_EMAIL_TEMPLATE.APP", QueryObject.PARAM_STRING, APP);//NOPMD
		query.addParameter("PRODUCT", "MSIG_EMAIL_TEMPLATE.PRODUCT", QueryObject.PARAM_STRING, PRODUCT);//NOPMD
		query.addParameter("RISK", "MSIG_EMAIL_TEMPLATE.RISK", QueryObject.PARAM_STRING, RISK);//NOPMD
		query.addParameter("TEMPLATE_NAME", "MSIG_EMAIL_TEMPLATE.TEMPLATE_NAME", QueryObject.PARAM_STRING, TEMPLATE_NAME);//NOPMD
		query.addParameter("WORKFLOW_STATUS", "MSIG_EMAIL_TEMPLATE.WORKFLOW_STATUS", QueryObject.PARAM_STRING, WORKFLOW_STATUS);//NOPMD
		
		
		query.setResultClass(MSIG_EMAIL_TEMPLATE.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

}