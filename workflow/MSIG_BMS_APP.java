/*
  This class has been generated by the Code Generator
*/

package com.bms.workflow;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.busobject.QueryParameter;
import com.cordys.cpc.bsf.query.Cursor;
import com.cordys.cpc.bsf.query.xqy.XqyException;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

@SuppressWarnings("deprecation")
public class MSIG_BMS_APP extends MSIG_BMS_APPBase
{
    public MSIG_BMS_APP()
    {
        this((BusObjectConfig)null);
    }

    public MSIG_BMS_APP(BusObjectConfig config)
    {
        super(config);
    }

    public void onInsert()
    {
    }

    public void onUpdate()
    {
    }

    public void onDelete()
    {
    }
    
	private static final com.eibus.util.logger.CordysLogger logger = CordysLogger.getCordysLogger(MSIG_BMS_APP.class);

	private static String errorMessage;
    

	public static BusObjectIterator<?> fetchCases(int viewDefinition, int searchDefinition, int sortDefinition,
			String formName, String viewName, Cursor cursor) throws MSIG_BMS_EXCEPTION {

		MSIG_BMS_UTILS utils = new MSIG_BMS_UTILS();

		String queryText = null, columns = null, searchClause = null, orderByClause = null, defaultQueryString = "";
		QueryObject query = null;
		Vector<QueryParameter> params = new Vector<QueryParameter>();
		QueryParameter qParam;
		
		String tableName = Node.getAttribute(viewDefinition, "view");
		errorMessage = "Invalid Table Name";
		if(StringUtils.isBlank(tableName)) {
			throw new MSIG_BMS_EXCEPTION(errorMessage);
		}
		
		String countIdentifier = Node.getAttribute(viewDefinition, "countIdentifier");
		if(StringUtils.isBlank(countIdentifier)) {
			countIdentifier = "1";
		}
		
		// Fetch User Id
		String userId = MSIG_BMS_UTILS.getUserName();

		errorMessage = "Invalid User";
		// User Check
		if (StringUtils.isBlank(userId)) {
			throw new MSIG_BMS_EXCEPTION(errorMessage);
		}

		int[] iFieldNodes = utils.fetchFieldsFromDefinition(viewDefinition);

		errorMessage = "Error Occured during query preparation";
		if (iFieldNodes != null) {
			columns = utils.frameColumnsInQuery(iFieldNodes); // prepare select column for the query
		} else {
			throw new MSIG_BMS_EXCEPTION(errorMessage);
		}

		errorMessage = "Error occured while framing query";
		defaultQueryString = utils.prepareDefaultQueryString(params, formName, viewName, userId);

		if (StringUtils.isBlank(defaultQueryString)) {
			throw new MSIG_BMS_EXCEPTION(errorMessage);
		}

		int[] isearchFieldNodes = XPath.getMatchingNodes(".//criteria", null, searchDefinition);

		// Prepare the order by clause
		orderByClause = utils.formOrderByClause(sortDefinition);

		try {
			if (isearchFieldNodes.length > 0 && isearchFieldNodes != null) {
				searchClause = utils.fetchSearchFields(params, isearchFieldNodes);
			}

			queryText = "SELECT COUNT(" + countIdentifier + ") OVER() totalRecords, * FROM" + " ( SELECT " + columns
					+ " FROM " + tableName + defaultQueryString + ") AS records ";

			if (StringUtils.isNotBlank(searchClause)) {
				queryText = queryText + "WHERE " + searchClause;
			}

			if (StringUtils.isNotBlank(orderByClause)) {
				queryText = queryText + orderByClause;
			}

			query = new QueryObject(queryText);
			query.setCursor(cursor);

			Iterator<QueryParameter> itr = params.iterator();
			while (itr.hasNext()) {
				qParam = itr.next();
				query.addParameter(qParam.getParameterName(), qParam.getColumnName(), qParam.getType(), qParam.getValue());
			}
			errorMessage = "Error occured while retrieving data from database.";
			return query.getObjects();

		} catch (NullPointerException e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-BMS", e);
			else
				logger.error(errorMessage + formName + "-CMS", e);
			throw new MSIG_BMS_EXCEPTION(errorMessage, e);
		} catch (XqyException e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-BMS", e);
			else
				logger.error(errorMessage + formName + "-CMS", e);
			throw new MSIG_BMS_EXCEPTION(errorMessage, e);
		} catch (Exception e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-BMS", e);
			else
				logger.error(errorMessage + formName + "-BMS", e);
			throw new MSIG_BMS_EXCEPTION(errorMessage, e);
		} finally {
			viewDefinition = 0;
			searchDefinition = 0;
			sortDefinition = 0;
			orderByClause = null;
			searchClause = null;
			errorMessage = null;
		}
	}
}