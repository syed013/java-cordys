/*
  This class has been generated by the Code Generator
*/

package com.bms.workflow;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.classinfo.ClassInfo;


public abstract class MSIG_BMS_APPBase extends com.cordys.cpc.bsf.busobject.CustomBusObject
{
    // tags used in the XML document
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_BMS_APP.class);
            s_classInfo.setUIDElements(new String[]{});
        }
        return s_classInfo;
    }

    public MSIG_BMS_APPBase(BusObjectConfig config)
    {
        super(config);
    }

}
