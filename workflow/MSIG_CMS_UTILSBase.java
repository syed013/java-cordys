/*
  This class has been generated by the Code Generator
*/

package com.cms.workflow;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.classinfo.ClassInfo;


public abstract class MSIG_CMS_UTILSBase extends com.cordys.cpc.bsf.busobject.CustomBusObject
{
    // tags used in the XML document
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_CMS_UTILS.class);
            s_classInfo.setUIDElements(new String[]{});
        }
        return s_classInfo;
    }

    public MSIG_CMS_UTILSBase(BusObjectConfig config)
    {
        super(config);
    }

}
