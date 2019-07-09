/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.classinfo.AttributeInfo;
import com.cordys.cpc.bsf.classinfo.ClassInfo;
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;


public abstract class MSIG_BRANCH_LOB_ROLE_MAPPINGBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_APPLICATION = "APPLICATION";
    public final static String ATTR_BRANCH_CODE = "BRANCH_CODE";
    public final static String ATTR_LOB_CODE = "LOB_CODE";
    public final static String ATTR_ROLE_CODE = "ROLE_CODE";
    public final static String ATTR_STATUS = "STATUS";
    public final static String ATTR_CREATED_BY = "CREATED_BY";
    public final static String ATTR_CREATED_ON = "CREATED_ON";
    public final static String ATTR_MODIFIED_BY = "MODIFIED_BY";
    public final static String ATTR_MODIFIED_ON = "MODIFIED_ON";
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
            s_classInfo.setTableName("MSIG_BRANCH_LOB_ROLE_MAPPING");
            s_classInfo.setUIDElements(new String[]{ATTR_APPLICATION, ATTR_BRANCH_CODE, ATTR_LOB_CODE, ATTR_ROLE_CODE});
            {
                AttributeInfo ai = new AttributeInfo(ATTR_APPLICATION);
                ai.setJavaName(ATTR_APPLICATION);
                ai.setColumnName(ATTR_APPLICATION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_APPLICATION);
                v.setMaxLength(10);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BRANCH_CODE);
                ai.setJavaName(ATTR_BRANCH_CODE);
                ai.setColumnName(ATTR_BRANCH_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BRANCH_CODE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_LOB_CODE);
                ai.setJavaName(ATTR_LOB_CODE);
                ai.setColumnName(ATTR_LOB_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_LOB_CODE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ROLE_CODE);
                ai.setJavaName(ATTR_ROLE_CODE);
                ai.setColumnName(ATTR_ROLE_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ROLE_CODE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_STATUS);
                ai.setJavaName(ATTR_STATUS);
                ai.setColumnName(ATTR_STATUS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_STATUS);
                v.setMaxLength(10);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CREATED_BY);
                ai.setJavaName(ATTR_CREATED_BY);
                ai.setColumnName(ATTR_CREATED_BY);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_CREATED_BY);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CREATED_ON);
                ai.setJavaName(ATTR_CREATED_ON);
                ai.setColumnName(ATTR_CREATED_ON);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MODIFIED_BY);
                ai.setJavaName(ATTR_MODIFIED_BY);
                ai.setColumnName(ATTR_MODIFIED_BY);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_MODIFIED_BY);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MODIFIED_ON);
                ai.setJavaName(ATTR_MODIFIED_ON);
                ai.setColumnName(ATTR_MODIFIED_ON);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
        }
        return s_classInfo;
    }

    public MSIG_BRANCH_LOB_ROLE_MAPPINGBase(BusObjectConfig config)
    {
        super(config);
    }

    public String getAPPLICATION()
    {
        return getStringProperty(ATTR_APPLICATION);
    }

    public void setAPPLICATION(String value)
    {
        setProperty(ATTR_APPLICATION, value, 0);
    }

    public String getBRANCH_CODE()
    {
        return getStringProperty(ATTR_BRANCH_CODE);
    }

    public void setBRANCH_CODE(String value)
    {
        setProperty(ATTR_BRANCH_CODE, value, 0);
    }

    public String getLOB_CODE()
    {
        return getStringProperty(ATTR_LOB_CODE);
    }

    public void setLOB_CODE(String value)
    {
        setProperty(ATTR_LOB_CODE, value, 0);
    }

    public String getROLE_CODE()
    {
        return getStringProperty(ATTR_ROLE_CODE);
    }

    public void setROLE_CODE(String value)
    {
        setProperty(ATTR_ROLE_CODE, value, 0);
    }

    public String getSTATUS()
    {
        return getStringProperty(ATTR_STATUS);
    }

    public void setSTATUS(String value)
    {
        setProperty(ATTR_STATUS, value, 0);
    }

    public String getCREATED_BY()
    {
        return getStringProperty(ATTR_CREATED_BY);
    }

    public void setCREATED_BY(String value)
    {
        setProperty(ATTR_CREATED_BY, value, 0);
    }

    public java.util.Date getCREATED_ON()
    {
        return getDateTimestampProperty(ATTR_CREATED_ON);
    }

    public void setCREATED_ON(java.util.Date value)
    {
        setProperty(ATTR_CREATED_ON, value, 0);
    }

    public String getMODIFIED_BY()
    {
        return getStringProperty(ATTR_MODIFIED_BY);
    }

    public void setMODIFIED_BY(String value)
    {
        setProperty(ATTR_MODIFIED_BY, value, 0);
    }

    public java.util.Date getMODIFIED_ON()
    {
        return getDateTimestampProperty(ATTR_MODIFIED_ON);
    }

    public void setMODIFIED_ON(java.util.Date value)
    {
        setProperty(ATTR_MODIFIED_ON, value, 0);
    }





    public static com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING getMsigBranchLobRoleMappingObject(String APPLICATION, String BRANCH_CODE, String LOB_CODE, String ROLE_CODE)
    {
        String queryText = "select * from \"MSIG_BRANCH_LOB_ROLE_MAPPING\" where \"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"ROLE_CODE\" = :ROLE_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
        return (MSIG_BRANCH_LOB_ROLE_MAPPING)query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getMsigBranchLobRoleMappingObjects(String fromAPPLICATION, String toAPPLICATION, String fromBRANCH_CODE, String toBRANCH_CODE, String fromLOB_CODE, String toLOB_CODE, String fromROLE_CODE, String toROLE_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_LOB_ROLE_MAPPING\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE and \"LOB_CODE\" between :fromLOB_CODE and :toLOB_CODE and \"ROLE_CODE\" between :fromROLE_CODE and :toROLE_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromBRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.addParameter("fromLOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, fromLOB_CODE);
        query.addParameter("toLOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, toLOB_CODE);
        query.addParameter("fromROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, fromROLE_CODE);
        query.addParameter("toROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, toROLE_CODE);
        query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getMsigBranchLobRoleMappingObjects(String fromAPPLICATION, String toAPPLICATION, String fromBRANCH_CODE, String toBRANCH_CODE, String fromLOB_CODE, String toLOB_CODE, String fromROLE_CODE, String toROLE_CODE)
    {
        String queryText = "select * from \"MSIG_BRANCH_LOB_ROLE_MAPPING\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE and \"LOB_CODE\" between :fromLOB_CODE and :toLOB_CODE and \"ROLE_CODE\" between :fromROLE_CODE and :toROLE_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromBRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.addParameter("fromLOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, fromLOB_CODE);
        query.addParameter("toLOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, toLOB_CODE);
        query.addParameter("fromROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, fromROLE_CODE);
        query.addParameter("toROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, toROLE_CODE);
        query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getNextMsigBranchLobRoleMappingObjects(String APPLICATION, String BRANCH_CODE, String LOB_CODE, String ROLE_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_LOB_ROLE_MAPPING\" where (\"APPLICATION\" > :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" > :BRANCH_CODE) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" > :LOB_CODE) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"ROLE_CODE\" > :ROLE_CODE) order by \"APPLICATION\" asc, \"BRANCH_CODE\" asc, \"LOB_CODE\" asc, \"ROLE_CODE\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_LOB_ROLE_MAPPING> getPreviousMsigBranchLobRoleMappingObjects(String APPLICATION, String BRANCH_CODE, String LOB_CODE, String ROLE_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_LOB_ROLE_MAPPING\" where (\"APPLICATION\" < :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" < :BRANCH_CODE) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" < :LOB_CODE) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"ROLE_CODE\" < :ROLE_CODE) order by \"APPLICATION\" desc, \"BRANCH_CODE\" desc, \"LOB_CODE\" desc, \"ROLE_CODE\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_LOB_ROLE_MAPPING.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_BRANCH_LOB_ROLE_MAPPING.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_LOB_ROLE_MAPPING.class);
        query.setCursor(cursor);
        return query.getObjects();
    }




}
