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


public abstract class MSIG_BRANCH_MASTERBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_APPLICATION = "APPLICATION";
    public final static String ATTR_BRANCH_CODE = "BRANCH_CODE";
    public final static String ATTR_BRANCH_NAME = "BRANCH_NAME";
    public final static String ATTR_BRANCH_DESCRIPTION = "BRANCH_DESCRIPTION";
    public final static String ATTR_ATTRIBUTE1 = "ATTRIBUTE1";
    public final static String ATTR_ATTRIBUTE2 = "ATTRIBUTE2";
    public final static String ATTR_ATTRIBUTE3 = "ATTRIBUTE3";
    public final static String ATTR_SERVICING_BRANCH = "SERVICING_BRANCH";
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
            s_classInfo = newClassInfo(MSIG_BRANCH_MASTER.class);
            s_classInfo.setTableName("MSIG_BRANCH_MASTER");
            s_classInfo.setUIDElements(new String[]{ATTR_APPLICATION, ATTR_BRANCH_CODE});
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
                AttributeInfo ai = new AttributeInfo(ATTR_BRANCH_NAME);
                ai.setJavaName(ATTR_BRANCH_NAME);
                ai.setColumnName(ATTR_BRANCH_NAME);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BRANCH_NAME);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BRANCH_DESCRIPTION);
                ai.setJavaName(ATTR_BRANCH_DESCRIPTION);
                ai.setColumnName(ATTR_BRANCH_DESCRIPTION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BRANCH_DESCRIPTION);
                v.setMaxLength(150);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE1);
                ai.setJavaName(ATTR_ATTRIBUTE1);
                ai.setColumnName(ATTR_ATTRIBUTE1);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ATTRIBUTE1);
                v.setMaxLength(500);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE2);
                ai.setJavaName(ATTR_ATTRIBUTE2);
                ai.setColumnName(ATTR_ATTRIBUTE2);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ATTRIBUTE2);
                v.setMaxLength(500);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE3);
                ai.setJavaName(ATTR_ATTRIBUTE3);
                ai.setColumnName(ATTR_ATTRIBUTE3);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ATTRIBUTE3);
                v.setMaxLength(500);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_SERVICING_BRANCH);
                ai.setJavaName(ATTR_SERVICING_BRANCH);
                ai.setColumnName(ATTR_SERVICING_BRANCH);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_SERVICING_BRANCH);
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

    public MSIG_BRANCH_MASTERBase(BusObjectConfig config)
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

    public String getBRANCH_NAME()
    {
        return getStringProperty(ATTR_BRANCH_NAME);
    }

    public void setBRANCH_NAME(String value)
    {
        setProperty(ATTR_BRANCH_NAME, value, 0);
    }

    public String getBRANCH_DESCRIPTION()
    {
        return getStringProperty(ATTR_BRANCH_DESCRIPTION);
    }

    public void setBRANCH_DESCRIPTION(String value)
    {
        setProperty(ATTR_BRANCH_DESCRIPTION, value, 0);
    }

    public String getATTRIBUTE1()
    {
        return getStringProperty(ATTR_ATTRIBUTE1);
    }

    public void setATTRIBUTE1(String value)
    {
        setProperty(ATTR_ATTRIBUTE1, value, 0);
    }

    public String getATTRIBUTE2()
    {
        return getStringProperty(ATTR_ATTRIBUTE2);
    }

    public void setATTRIBUTE2(String value)
    {
        setProperty(ATTR_ATTRIBUTE2, value, 0);
    }

    public String getATTRIBUTE3()
    {
        return getStringProperty(ATTR_ATTRIBUTE3);
    }

    public void setATTRIBUTE3(String value)
    {
        setProperty(ATTR_ATTRIBUTE3, value, 0);
    }

    public String getSERVICING_BRANCH()
    {
        return getStringProperty(ATTR_SERVICING_BRANCH);
    }

    public void setSERVICING_BRANCH(String value)
    {
        setProperty(ATTR_SERVICING_BRANCH, value, 0);
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



    public static com.msig.masterdata.MSIG_BRANCH_MASTER getMsigBranchMasterObject(String APPLICATION, String BRANCH_CODE)
    {
        String queryText = "select * from \"MSIG_BRANCH_MASTER\" where \"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" = :BRANCH_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_MASTER.class);
        return (MSIG_BRANCH_MASTER)query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_MASTER> getMsigBranchMasterObjects(String fromAPPLICATION, String toAPPLICATION, String fromBRANCH_CODE, String toBRANCH_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_MASTER\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromBRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.setResultClass(MSIG_BRANCH_MASTER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_MASTER> getMsigBranchMasterObjects(String fromAPPLICATION, String toAPPLICATION, String fromBRANCH_CODE, String toBRANCH_CODE)
    {
        String queryText = "select * from \"MSIG_BRANCH_MASTER\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromBRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.setResultClass(MSIG_BRANCH_MASTER.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_MASTER> getNextMsigBranchMasterObjects(String APPLICATION, String BRANCH_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_MASTER\" where (\"APPLICATION\" > :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" > :BRANCH_CODE) order by \"APPLICATION\" asc, \"BRANCH_CODE\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_MASTER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_BRANCH_MASTER> getPreviousMsigBranchMasterObjects(String APPLICATION, String BRANCH_CODE, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_BRANCH_MASTER\" where (\"APPLICATION\" < :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"BRANCH_CODE\" < :BRANCH_CODE) order by \"APPLICATION\" desc, \"BRANCH_CODE\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_BRANCH_MASTER.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("BRANCH_CODE", "MSIG_BRANCH_MASTER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.setResultClass(MSIG_BRANCH_MASTER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }




}
