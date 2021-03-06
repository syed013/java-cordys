/*
  This class has been generated by the Code Generator
 */

package com.msig.masterdata;

import java.util.Date;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.eibus.util.system.Native;

public class MSIG_APPROVAL_MATRIX_BO_FIELDS extends
		MSIG_APPROVAL_MATRIX_BO_FIELDSBase {
	public MSIG_APPROVAL_MATRIX_BO_FIELDS() {
		this((BusObjectConfig) null);
	}

	public MSIG_APPROVAL_MATRIX_BO_FIELDS(BusObjectConfig config) {
		super(config);
	}

	@Override
	public void onBeforeInsert() {
		super.onBeforeInsert();
		this.setAPR_MATRIX_FILED_ID(Native.createGuid());
		this.setCREATED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setCREATED_ON(new Date());
		this.setMODIFIED_ON(new Date());
	}

	@Override
	public void onBeforeUpdate() {
		super.onBeforeUpdate();
		this.setMODIFIED_BY(MasterDataUtil.getUserName());
		this.setMODIFIED_ON(new Date());
	}

	public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_BO_FIELDS> getBOFieldsForProcessType(
			String APPLICATION_TYPE, String PROCESS_TYPE) {
		String queryText = "SELECT * FROM MSIG_APPROVAL_MATRIX_BO_FIELDS WHERE PROCESS_TYPE LIKE :PROCESS_TYPE AND APPLICATION_TYPE LIKE :APPLICATION_TYPE";

		QueryObject query = new QueryObject(queryText);
		query.addParameter("PROCESS_TYPE",
				"MSIG_APPROVAL_MATRIX_BO_FIELDS.PROCESS_TYPE",
				QueryObject.PARAM_STRING, PROCESS_TYPE);
		query.addParameter("APPLICATION_TYPE",
				"MSIG_APPROVAL_MATRIX_BO_FIELDS.APPLICATION_TYPE",
				QueryObject.PARAM_STRING, APPLICATION_TYPE);
		query.setResultClass(MSIG_APPROVAL_MATRIX_BO_FIELDS.class);

		return query.getObjects();
	}
}
