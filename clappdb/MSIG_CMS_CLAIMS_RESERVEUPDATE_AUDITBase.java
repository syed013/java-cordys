/*
  This class has been generated by the Code Generator
*/

package com.msig.clintegrations.clappdb;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.classinfo.AttributeInfo;
import com.cordys.cpc.bsf.classinfo.ClassInfo;
import com.cordys.cpc.bsf.listeners.constraint.NumberValidator;
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;


public abstract class MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDITBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_ID = "ID";
    public final static String ATTR_INPUTFROM = "INPUTFROM";
    public final static String ATTR_INPUTTO = "INPUTTO";
    public final static String ATTR_CREATED_BY = "CREATED_BY";
    public final static String ATTR_CREATED_ON = "CREATED_ON";
    public final static String ATTR_CLAIMNO = "CLAIMNO";
    public final static String ATTR_CASEID = "CASEID";
    public final static String ATTR_TRANSACTIONNO = "TRANSACTIONNO";
    public final static String ATTR_REVIEWERID = "REVIEWERID";
    public final static String ATTR_REVIEWDATE = "REVIEWDATE";
    public final static String ATTR_MOVEMENTIND = "MOVEMENTIND";
    public final static String ATTR_REVIEWCOMMENT1 = "REVIEWCOMMENT1";
    public final static String ATTR_REVIEWCOMMENT2 = "REVIEWCOMMENT2";
    public final static String ATTR_PREMCLASS = "PREMCLASS";
    public final static String ATTR_RESERVECODE = "RESERVECODE";
    public final static String ATTR_AMOUNT = "AMOUNT";
    public final static String ATTR_BEFOREAMOUNT = "BEFOREAMOUNT";
    public final static String ATTR_MOVEMENTAMOUNT = "MOVEMENTAMOUNT";
    public final static String ATTR_STATUS = "STATUS";
    public final static String ATTR_UPDATEACTIONSTATUS = "UPDATEACTIONSTATUS";
    public final static String ATTR_UPDATEACTIONRESPONSE = "UPDATEACTIONRESPONSE";
    public final static String ATTR_BUSINESSSTATUS = "BUSINESSSTATUS";
    public final static String ATTR_LOB = "LOB";
    public final static String ATTR_CLIAMHANDLER = "CLIAMHANDLER";
    public final static String ATTR_HANDLINGBRANCH = "HANDLINGBRANCH";
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
            s_classInfo.setTableName("MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT");
            s_classInfo.setUIDElements(new String[]{ATTR_ID});
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ID);
                ai.setJavaName(ATTR_ID);
                ai.setColumnName(ATTR_ID);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ID);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_INPUTFROM);
                ai.setJavaName(ATTR_INPUTFROM);
                ai.setColumnName(ATTR_INPUTFROM);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_INPUTTO);
                ai.setJavaName(ATTR_INPUTTO);
                ai.setColumnName(ATTR_INPUTTO);
                ai.setAttributeClass(java.util.Date.class);
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
                AttributeInfo ai = new AttributeInfo(ATTR_CLAIMNO);
                ai.setJavaName(ATTR_CLAIMNO);
                ai.setColumnName(ATTR_CLAIMNO);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_CLAIMNO);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CASEID);
                ai.setJavaName(ATTR_CASEID);
                ai.setColumnName(ATTR_CASEID);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_CASEID);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_TRANSACTIONNO);
                ai.setJavaName(ATTR_TRANSACTIONNO);
                ai.setColumnName(ATTR_TRANSACTIONNO);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_TRANSACTIONNO);
                v.setMaxLength(15);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_REVIEWERID);
                ai.setJavaName(ATTR_REVIEWERID);
                ai.setColumnName(ATTR_REVIEWERID);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_REVIEWERID);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_REVIEWDATE);
                ai.setJavaName(ATTR_REVIEWDATE);
                ai.setColumnName(ATTR_REVIEWDATE);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MOVEMENTIND);
                ai.setJavaName(ATTR_MOVEMENTIND);
                ai.setColumnName(ATTR_MOVEMENTIND);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_MOVEMENTIND);
                v.setMaxLength(2);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_REVIEWCOMMENT1);
                ai.setJavaName(ATTR_REVIEWCOMMENT1);
                ai.setColumnName(ATTR_REVIEWCOMMENT1);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_REVIEWCOMMENT1);
                v.setMaxLength(2147483647);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_REVIEWCOMMENT2);
                ai.setJavaName(ATTR_REVIEWCOMMENT2);
                ai.setColumnName(ATTR_REVIEWCOMMENT2);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_REVIEWCOMMENT2);
                v.setMaxLength(2147483647);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_PREMCLASS);
                ai.setJavaName(ATTR_PREMCLASS);
                ai.setColumnName(ATTR_PREMCLASS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_PREMCLASS);
                v.setMaxLength(3);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_RESERVECODE);
                ai.setJavaName(ATTR_RESERVECODE);
                ai.setColumnName(ATTR_RESERVECODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_RESERVECODE);
                v.setMaxLength(2);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_AMOUNT);
                ai.setJavaName(ATTR_AMOUNT);
                ai.setColumnName(ATTR_AMOUNT);
                ai.setAttributeClass(double.class);
                NumberValidator v = new NumberValidator(ATTR_AMOUNT);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BEFOREAMOUNT);
                ai.setJavaName(ATTR_BEFOREAMOUNT);
                ai.setColumnName(ATTR_BEFOREAMOUNT);
                ai.setAttributeClass(double.class);
                NumberValidator v = new NumberValidator(ATTR_BEFOREAMOUNT);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MOVEMENTAMOUNT);
                ai.setJavaName(ATTR_MOVEMENTAMOUNT);
                ai.setColumnName(ATTR_MOVEMENTAMOUNT);
                ai.setAttributeClass(double.class);
                NumberValidator v = new NumberValidator(ATTR_MOVEMENTAMOUNT);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_STATUS);
                ai.setJavaName(ATTR_STATUS);
                ai.setColumnName(ATTR_STATUS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_STATUS);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_UPDATEACTIONSTATUS);
                ai.setJavaName(ATTR_UPDATEACTIONSTATUS);
                ai.setColumnName(ATTR_UPDATEACTIONSTATUS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_UPDATEACTIONSTATUS);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_UPDATEACTIONRESPONSE);
                ai.setJavaName(ATTR_UPDATEACTIONRESPONSE);
                ai.setColumnName(ATTR_UPDATEACTIONRESPONSE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_UPDATEACTIONRESPONSE);
                v.setMaxLength(2147483647);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BUSINESSSTATUS);
                ai.setJavaName(ATTR_BUSINESSSTATUS);
                ai.setColumnName(ATTR_BUSINESSSTATUS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BUSINESSSTATUS);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_LOB);
                ai.setJavaName(ATTR_LOB);
                ai.setColumnName(ATTR_LOB);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_LOB);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CLIAMHANDLER);
                ai.setJavaName(ATTR_CLIAMHANDLER);
                ai.setColumnName(ATTR_CLIAMHANDLER);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_CLIAMHANDLER);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_HANDLINGBRANCH);
                ai.setJavaName(ATTR_HANDLINGBRANCH);
                ai.setColumnName(ATTR_HANDLINGBRANCH);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_HANDLINGBRANCH);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
        }
        return s_classInfo;
    }

    public MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDITBase(BusObjectConfig config)
    {
        super(config);
    }

    public String getID()
    {
        return getStringProperty(ATTR_ID);
    }

    public void setID(String value)
    {
        setProperty(ATTR_ID, value, 0);
    }

    public java.util.Date getINPUTFROM()
    {
        return getDateTimestampProperty(ATTR_INPUTFROM);
    }

    public void setINPUTFROM(java.util.Date value)
    {
        setProperty(ATTR_INPUTFROM, value, 0);
    }

    public java.util.Date getINPUTTO()
    {
        return getDateTimestampProperty(ATTR_INPUTTO);
    }

    public void setINPUTTO(java.util.Date value)
    {
        setProperty(ATTR_INPUTTO, value, 0);
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

    public String getCLAIMNO()
    {
        return getStringProperty(ATTR_CLAIMNO);
    }

    public void setCLAIMNO(String value)
    {
        setProperty(ATTR_CLAIMNO, value, 0);
    }

    public String getCASEID()
    {
        return getStringProperty(ATTR_CASEID);
    }

    public void setCASEID(String value)
    {
        setProperty(ATTR_CASEID, value, 0);
    }

    public String getTRANSACTIONNO()
    {
        return getStringProperty(ATTR_TRANSACTIONNO);
    }

    public void setTRANSACTIONNO(String value)
    {
        setProperty(ATTR_TRANSACTIONNO, value, 0);
    }

    public String getREVIEWERID()
    {
        return getStringProperty(ATTR_REVIEWERID);
    }

    public void setREVIEWERID(String value)
    {
        setProperty(ATTR_REVIEWERID, value, 0);
    }

    public java.util.Date getREVIEWDATE()
    {
        return getDateTimestampProperty(ATTR_REVIEWDATE);
    }

    public void setREVIEWDATE(java.util.Date value)
    {
        setProperty(ATTR_REVIEWDATE, value, 0);
    }

    public String getMOVEMENTIND()
    {
        return getStringProperty(ATTR_MOVEMENTIND);
    }

    public void setMOVEMENTIND(String value)
    {
        setProperty(ATTR_MOVEMENTIND, value, 0);
    }

    public String getREVIEWCOMMENT1()
    {
        return getStringProperty(ATTR_REVIEWCOMMENT1);
    }

    public void setREVIEWCOMMENT1(String value)
    {
        setProperty(ATTR_REVIEWCOMMENT1, value, 0);
    }

    public String getREVIEWCOMMENT2()
    {
        return getStringProperty(ATTR_REVIEWCOMMENT2);
    }

    public void setREVIEWCOMMENT2(String value)
    {
        setProperty(ATTR_REVIEWCOMMENT2, value, 0);
    }

    public String getPREMCLASS()
    {
        return getStringProperty(ATTR_PREMCLASS);
    }

    public void setPREMCLASS(String value)
    {
        setProperty(ATTR_PREMCLASS, value, 0);
    }

    public String getRESERVECODE()
    {
        return getStringProperty(ATTR_RESERVECODE);
    }

    public void setRESERVECODE(String value)
    {
        setProperty(ATTR_RESERVECODE, value, 0);
    }

    public double getAMOUNT()
    {
        return getDoubleProperty(ATTR_AMOUNT);
    }

    public void setAMOUNT(double value)
    {
        setProperty(ATTR_AMOUNT, value, 0);
    }

    public double getBEFOREAMOUNT()
    {
        return getDoubleProperty(ATTR_BEFOREAMOUNT);
    }

    public void setBEFOREAMOUNT(double value)
    {
        setProperty(ATTR_BEFOREAMOUNT, value, 0);
    }

    public double getMOVEMENTAMOUNT()
    {
        return getDoubleProperty(ATTR_MOVEMENTAMOUNT);
    }

    public void setMOVEMENTAMOUNT(double value)
    {
        setProperty(ATTR_MOVEMENTAMOUNT, value, 0);
    }

    public String getSTATUS()
    {
        return getStringProperty(ATTR_STATUS);
    }

    public void setSTATUS(String value)
    {
        setProperty(ATTR_STATUS, value, 0);
    }

    public String getUPDATEACTIONSTATUS()
    {
        return getStringProperty(ATTR_UPDATEACTIONSTATUS);
    }

    public void setUPDATEACTIONSTATUS(String value)
    {
        setProperty(ATTR_UPDATEACTIONSTATUS, value, 0);
    }

    public String getUPDATEACTIONRESPONSE()
    {
        return getStringProperty(ATTR_UPDATEACTIONRESPONSE);
    }

    public void setUPDATEACTIONRESPONSE(String value)
    {
        setProperty(ATTR_UPDATEACTIONRESPONSE, value, 0);
    }

    public String getBUSINESSSTATUS()
    {
        return getStringProperty(ATTR_BUSINESSSTATUS);
    }

    public void setBUSINESSSTATUS(String value)
    {
        setProperty(ATTR_BUSINESSSTATUS, value, 0);
    }

    public String getLOB()
    {
        return getStringProperty(ATTR_LOB);
    }

    public void setLOB(String value)
    {
        setProperty(ATTR_LOB, value, 0);
    }

    public String getCLIAMHANDLER()
    {
        return getStringProperty(ATTR_CLIAMHANDLER);
    }

    public void setCLIAMHANDLER(String value)
    {
        setProperty(ATTR_CLIAMHANDLER, value, 0);
    }

    public String getHANDLINGBRANCH()
    {
        return getStringProperty(ATTR_HANDLINGBRANCH);
    }

    public void setHANDLINGBRANCH(String value)
    {
        setProperty(ATTR_HANDLINGBRANCH, value, 0);
    }

    public static com.msig.clintegrations.clappdb.MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT getMsigCmsClaimsReserveupdateAuditObject(String ID)
    {
        String queryText = "select * from \"MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT\" where \"ID\" = :ID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("ID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, ID);//NOPMD
        query.setResultClass(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
        return (MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT)query.getObject();
    }

    public static BusObjectIterator<com.msig.clintegrations.clappdb.MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT> getMsigCmsClaimsReserveupdateAuditObjects(String fromID, String toID, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT\" where \"ID\" between :fromID and :toID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, fromID);
        query.addParameter("toID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, toID);
        query.setResultClass(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.clintegrations.clappdb.MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT> getMsigCmsClaimsReserveupdateAuditObjects(String fromID, String toID)
    {
        String queryText = "select * from \"MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT\" where \"ID\" between :fromID and :toID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, fromID);
        query.addParameter("toID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, toID);
        query.setResultClass(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.clintegrations.clappdb.MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT> getNextMsigCmsClaimsReserveupdateAuditObjects(String ID, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT\" where (\"ID\" > :ID) order by \"ID\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("ID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, ID);//NOPMD
        query.setResultClass(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.clintegrations.clappdb.MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT> getPreviousMsigCmsClaimsReserveupdateAuditObjects(String ID, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT\" where (\"ID\" < :ID) order by \"ID\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("ID", "MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.ID", QueryObject.PARAM_STRING, ID);//NOPMD
        query.setResultClass(MSIG_CMS_CLAIMS_RESERVEUPDATE_AUDIT.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

}
