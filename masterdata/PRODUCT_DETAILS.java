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



public class PRODUCT_DETAILS extends PRODUCT_DETAILSBase
{
    public PRODUCT_DETAILS()
    {
        this((BusObjectConfig)null);
    }

    public PRODUCT_DETAILS(BusObjectConfig config)
    {
        super(config);
    }

    public static BusObjectIterator<com.msig.masterdata.PRODUCT_DETAILS> getAllProductDetailsObjects()
    {
        String queryText = "select  distinct(PRODUCT_TYPE)  from \"PRODUCT_DETAILS\" order by \"PRODUCT_TYPE\" ASC";
        QueryObject query = new QueryObject(queryText);
      //  query.addParameter("ID", "PRODUCT_DETAILS.ID", QueryObject.PARAM_INT, new Integer(ID));//NOPMD
        query.setResultClass(PRODUCT_DETAILS.class);
    //    query.setCursor(cursor);
        return query.getObjects();
    }


}
