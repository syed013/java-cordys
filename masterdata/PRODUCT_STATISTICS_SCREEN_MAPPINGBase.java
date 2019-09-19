/*
  This class has been generated by the Code Generator
 */

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.classinfo.AttributeInfo;
import com.cordys.cpc.bsf.classinfo.ClassInfo;
import com.cordys.cpc.bsf.listeners.constraint.NumberValidator;
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;

public abstract class PRODUCT_STATISTICS_SCREEN_MAPPINGBase extends
		com.cordys.cpc.bsf.busobject.StateBusObject {
	// tags used in the XML document
	public final static String ATTR_ID = "ID";
	public final static String ATTR_PRODUCT_TYPE = "PRODUCT_TYPE";
	public final static String ATTR_PRODUCT_NAME = "PRODUCT_NAME";
	public final static String ATTR_RISK_TYPE = "RISK_TYPE";
	public final static String ATTR_RISK_NAME = "RISK_NAME";
	public final static String ATTR_RISK_GROUPING = "RISK_GROUPING";
	public final static String ATTR_PREMIUM_CLASS = "PREMIUM_CLASS";
	public final static String ATTR_RISK_SCREEN = "RISK_SCREEN";
	public final static String ATTR_STATISTICS_SCREEN = "STATISTICS_SCREEN";
	public final static String ATTR_LOB = "LOB";
	public final static String ATTR_ATTRIBUTE1 = "ATTRIBUTE1";
	public final static String ATTR_ATTRIBUTE2 = "ATTRIBUTE2";
	public final static String ATTR_ATTRIBUTE3 = "ATTRIBUTE3";
	private static ClassInfo s_classInfo = null;

	public static ClassInfo _getClassInfo()// NOPMD framework ensures this is
											// thread safe
	{
		if (s_classInfo == null)// NOPMD
		{
			s_classInfo = newClassInfo(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
			s_classInfo.setTableName("PRODUCT_STATISTICS_SCREEN_MAPPING");
			s_classInfo.setUIDElements(new String[] { ATTR_ID });
			{
				AttributeInfo ai = new AttributeInfo(ATTR_ID);
				ai.setJavaName(ATTR_ID);
				ai.setColumnName(ATTR_ID);
				ai.setAttributeClass(int.class);
				NumberValidator v = new NumberValidator(ATTR_ID);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_PRODUCT_TYPE);
				ai.setJavaName(ATTR_PRODUCT_TYPE);
				ai.setColumnName(ATTR_PRODUCT_TYPE);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_PRODUCT_TYPE);
				v.setMaxLength(10);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_PRODUCT_NAME);
				ai.setJavaName(ATTR_PRODUCT_NAME);
				ai.setColumnName(ATTR_PRODUCT_NAME);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_PRODUCT_NAME);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_RISK_TYPE);
				ai.setJavaName(ATTR_RISK_TYPE);
				ai.setColumnName(ATTR_RISK_TYPE);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_RISK_TYPE);
				v.setMaxLength(10);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_RISK_NAME);
				ai.setJavaName(ATTR_RISK_NAME);
				ai.setColumnName(ATTR_RISK_NAME);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_RISK_NAME);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_RISK_GROUPING);
				ai.setJavaName(ATTR_RISK_GROUPING);
				ai.setColumnName(ATTR_RISK_GROUPING);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_RISK_GROUPING);
				v.setMaxLength(10);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_PREMIUM_CLASS);
				ai.setJavaName(ATTR_PREMIUM_CLASS);
				ai.setColumnName(ATTR_PREMIUM_CLASS);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_PREMIUM_CLASS);
				v.setMaxLength(10);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_RISK_SCREEN);
				ai.setJavaName(ATTR_RISK_SCREEN);
				ai.setColumnName(ATTR_RISK_SCREEN);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_RISK_SCREEN);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_STATISTICS_SCREEN);
				ai.setJavaName(ATTR_STATISTICS_SCREEN);
				ai.setColumnName(ATTR_STATISTICS_SCREEN);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_STATISTICS_SCREEN);
				v.setMaxLength(2147483647);
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
				AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE1);
				ai.setJavaName(ATTR_ATTRIBUTE1);
				ai.setColumnName(ATTR_ATTRIBUTE1);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_ATTRIBUTE1);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE2);
				ai.setJavaName(ATTR_ATTRIBUTE2);
				ai.setColumnName(ATTR_ATTRIBUTE2);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_ATTRIBUTE2);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
			{
				AttributeInfo ai = new AttributeInfo(ATTR_ATTRIBUTE3);
				ai.setJavaName(ATTR_ATTRIBUTE3);
				ai.setColumnName(ATTR_ATTRIBUTE3);
				ai.setAttributeClass(String.class);
				StringValidator v = new StringValidator(ATTR_ATTRIBUTE3);
				v.setMaxLength(2147483647);
				ai.addConstraintHandler(v);
				s_classInfo.addAttributeInfo(ai);
			}
		}
		return s_classInfo;
	}

	public PRODUCT_STATISTICS_SCREEN_MAPPINGBase(BusObjectConfig config) {
		super(config);
	}

	public int getID() {
		return getIntProperty(ATTR_ID);
	}

	public void setID(int value) {
		setProperty(ATTR_ID, value, 0);
	}

	public String getPRODUCT_TYPE() {
		return getStringProperty(ATTR_PRODUCT_TYPE);
	}

	public void setPRODUCT_TYPE(String value) {
		setProperty(ATTR_PRODUCT_TYPE, value, 0);
	}

	public String getPRODUCT_NAME() {
		return getStringProperty(ATTR_PRODUCT_NAME);
	}

	public void setPRODUCT_NAME(String value) {
		setProperty(ATTR_PRODUCT_NAME, value, 0);
	}

	public String getRISK_TYPE() {
		return getStringProperty(ATTR_RISK_TYPE);
	}

	public void setRISK_TYPE(String value) {
		setProperty(ATTR_RISK_TYPE, value, 0);
	}

	public String getRISK_NAME() {
		return getStringProperty(ATTR_RISK_NAME);
	}

	public void setRISK_NAME(String value) {
		setProperty(ATTR_RISK_NAME, value, 0);
	}

	public String getRISK_GROUPING() {
		return getStringProperty(ATTR_RISK_GROUPING);
	}

	public void setRISK_GROUPING(String value) {
		setProperty(ATTR_RISK_GROUPING, value, 0);
	}

	public String getPREMIUM_CLASS() {
		return getStringProperty(ATTR_PREMIUM_CLASS);
	}

	public void setPREMIUM_CLASS(String value) {
		setProperty(ATTR_PREMIUM_CLASS, value, 0);
	}

	public String getRISK_SCREEN() {
		return getStringProperty(ATTR_RISK_SCREEN);
	}

	public void setRISK_SCREEN(String value) {
		setProperty(ATTR_RISK_SCREEN, value, 0);
	}

	public String getSTATISTICS_SCREEN() {
		return getStringProperty(ATTR_STATISTICS_SCREEN);
	}

	public void setSTATISTICS_SCREEN(String value) {
		setProperty(ATTR_STATISTICS_SCREEN, value, 0);
	}

	public String getLOB() {
		return getStringProperty(ATTR_LOB);
	}

	public void setLOB(String value) {
		setProperty(ATTR_LOB, value, 0);
	}

	public String getATTRIBUTE1() {
		return getStringProperty(ATTR_ATTRIBUTE1);
	}

	public void setATTRIBUTE1(String value) {
		setProperty(ATTR_ATTRIBUTE1, value, 0);
	}

	public String getATTRIBUTE2() {
		return getStringProperty(ATTR_ATTRIBUTE2);
	}

	public void setATTRIBUTE2(String value) {
		setProperty(ATTR_ATTRIBUTE2, value, 0);
	}

	public String getATTRIBUTE3() {
		return getStringProperty(ATTR_ATTRIBUTE3);
	}

	public void setATTRIBUTE3(String value) {
		setProperty(ATTR_ATTRIBUTE3, value, 0);
	}

	public static BusObjectIterator<com.msig.masterdata.PRODUCT_STATISTICS_SCREEN_MAPPING> getNextProductStatisticsScreenMappingObjects(
			int ID, com.cordys.cpc.bsf.query.Cursor cursor) {
		String queryText = "select * from \"PRODUCT_STATISTICS_SCREEN_MAPPING\" where (\"ID\" > :ID) order by \"ID\" asc";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(ID));// NOPMD
		query.setResultClass(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
		query.setCursor(cursor);
		return query.getObjects();
	}

	public static BusObjectIterator<com.msig.masterdata.PRODUCT_STATISTICS_SCREEN_MAPPING> getPreviousProductStatisticsScreenMappingObjects(
			int ID, com.cordys.cpc.bsf.query.Cursor cursor) {
		String queryText = "select * from \"PRODUCT_STATISTICS_SCREEN_MAPPING\" where (\"ID\" < :ID) order by \"ID\" desc";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(ID));// NOPMD
		query.setResultClass(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
		query.setCursor(cursor);
		return query.getObjects();
	}

	public static com.msig.masterdata.PRODUCT_STATISTICS_SCREEN_MAPPING getProductStatisticsScreenMappingObject(
			int ID) {
		String queryText = "select * from \"PRODUCT_STATISTICS_SCREEN_MAPPING\" where \"ID\" = :ID";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("ID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(ID));// NOPMD
		query.setResultClass(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
		return (PRODUCT_STATISTICS_SCREEN_MAPPING) query.getObject();
	}

	public static BusObjectIterator<com.msig.masterdata.PRODUCT_STATISTICS_SCREEN_MAPPING> getProductStatisticsScreenMappingObjects(
			int fromID, int toID, com.cordys.cpc.bsf.query.Cursor cursor) {
		String queryText = "select * from \"PRODUCT_STATISTICS_SCREEN_MAPPING\" where \"ID\" between :fromID and :toID";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("fromID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(fromID));
		query.addParameter("toID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(toID));
		query.setResultClass(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
		query.setCursor(cursor);
		return query.getObjects();
	}

	public static BusObjectIterator<com.msig.masterdata.PRODUCT_STATISTICS_SCREEN_MAPPING> getProductStatisticsScreenMappingObjects(
			int fromID, int toID) {
		String queryText = "select * from \"PRODUCT_STATISTICS_SCREEN_MAPPING\" where \"ID\" between :fromID and :toID";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("fromID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(fromID));
		query.addParameter("toID", "PRODUCT_STATISTICS_SCREEN_MAPPING.ID",
				QueryObject.PARAM_INT, new Integer(toID));
		query.setResultClass(PRODUCT_STATISTICS_SCREEN_MAPPING.class);
		return query.getObjects();
	}

}