/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.classinfo.ClassInfo;


public abstract class MasterDataUtilBase extends com.cordys.cpc.bsf.busobject.CustomBusObject
{
    // tags used in the XML document
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MasterDataUtil.class);
            s_classInfo.setUIDElements(new String[]{});
        }
        return s_classInfo;
    }

    public MasterDataUtilBase(BusObjectConfig config)
    {
        super(config);
    }











}
