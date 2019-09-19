/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;


public class MSIG_ROLES_PRIORITY_SEQ extends MSIG_ROLES_PRIORITY_SEQBase
{
    public MSIG_ROLES_PRIORITY_SEQ()
    {
        this((BusObjectConfig)null);
    }

    public MSIG_ROLES_PRIORITY_SEQ(BusObjectConfig config)
    {
        super(config);
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_ROLES_PRIORITY_SEQ> getRolesPriorityForABL(String APPLICATION, String BRANCH, String LOB_CODE, String BUSINESS_FUNCTION)
    {
        LOB_CODE = "%" + LOB_CODE + "%";
        BRANCH = "%" + BRANCH + "%";
    	
    	String queryText = "SELECT ROLE_CODE FROM MSIG_ROLES_PRIORITY_SEQ WHERE APPLICATION = :APPLICATION AND (BRANCH LIKE :BRANCH OR BRANCH = 'ANY') AND LOB_CODE LIKE :LOB_CODE AND BUSINESS_FUNCTION LIKE :BUSINESS_FUNCTION ORDER BY SEQUENCE";
        
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_ROLES_PRIORITY_SEQ.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
        query.addParameter("LOB_CODE", "MSIG_ROLES_PRIORITY_SEQ.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
        
        query.setResultClass(MSIG_ROLES_PRIORITY_SEQ.class);
        
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_ROLES_PRIORITY_SEQ> getNextRoleCodeByPriority(String APPLICATION, String BRANCH, String LOB_CODE, String BUSINESS_FUNCTION,String ROLE_CODE){
         LOB_CODE = "%" + LOB_CODE + "%";
         BRANCH = "%" + BRANCH + "%";
         
         String queryText = "SELECT ROLE_CODE FROM MSIG_ROLES_PRIORITY_SEQ WHERE SEQUENCE = (SELECT (SEQUENCE+1) FROM MSIG_ROLES_PRIORITY_SEQ WHERE APPLICATION = :APPLICATION AND (BRANCH LIKE :BRANCH OR BRANCH = 'ANY') AND LOB_CODE LIKE :LOB_CODE AND BUSINESS_FUNCTION= :BUSINESS_FUNCTION AND ROLE_CODE = :ROLE_CODE) AND APPLICATION = :APPLICATION AND (BRANCH LIKE :BRANCH OR BRANCH = 'ANY') AND LOB_CODE LIKE :LOB_CODE AND BUSINESS_FUNCTION= :BUSINESS_FUNCTION";
 
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_ROLES_PRIORITY_SEQ.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);
        query.addParameter("LOB_CODE", "MSIG_ROLES_PRIORITY_SEQ.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);
         query.addParameter("ROLE_CODE", "MSIG_ROLES_PRIORITY_SEQ.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);
 query.addParameter("BRANCH", "MSIG_ROLES_PRIORITY_SEQ.BRANCH", QueryObject.PARAM_STRING, BRANCH);
 query.addParameter("BUSINESS_FUNCTION", "MSIG_ROLES_PRIORITY_SEQ.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, BUSINESS_FUNCTION);
        query.setResultClass(MSIG_ROLES_PRIORITY_SEQ.class);
        return query.getObjects();
        
         
    }

}