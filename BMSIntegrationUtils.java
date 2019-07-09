/*
  This class has been generated by the Code Generator
*/

package com.msig.bmsintegrationapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObject;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.soap.SOAPRequestObject;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Document;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.NodeSet;
import com.eibus.xml.xpath.ResultNode;
import com.eibus.xml.xpath.XPath;

public class BMSIntegrationUtils extends BMSIntegrationUtilsBase {
	public static CordysLogger cordysLogger = CordysLogger.getCordysLogger(BMSIntegrationUtils.class);

	public BMSIntegrationUtils() {
		this((BusObjectConfig) null);
	}

	public BMSIntegrationUtils(BusObjectConfig config) {
		super(config);
	}

	public static BusObjectIterator<com.msig.bmsintegrationapp.BMSIntegrationUtils> getBMSIntegrationUtilsObjects(
			com.cordys.cpc.bsf.query.Cursor cursor) {
		
		return null;
	}

	public void onInsert() {
	}

	public void onUpdate() {
	}

	public void onDelete() {
	}

	public static NodeSet modifyXMLAttributeData(NodeSet xmlData, String nodeXPath, String attributeName,
			String attributeValue) {
		cordysLogger.debug("Received Nodeset for removeXMLNSDataForS4845 - size is : " + xmlData.size());

		int iNode = 0;

		while (xmlData.hasNext()) {
			long currNode = xmlData.next();
			if (!ResultNode.isAttribute(currNode)) {
				iNode = Node.clone(ResultNode.getElementNode(currNode), true);
				cordysLogger.debug("Received Nodes Before updating data : " + Node.writeToString(iNode, true));
				for (int node : XPath.getMatchingNodes(nodeXPath, null, iNode)) {
					Node.setAttribute(node, attributeName, attributeValue);
				}
			}
		}
		cordysLogger.debug("XML Data after update: " + Node.writeToString(iNode, true));

		return XPath.getMatchingNodeSet("//FIR4846I_REC", null, iNode);
	}

	@SuppressWarnings("deprecation")
	public static int createFireVPMSRequest(String caseId, String riskno, String requestType, int agentInfoFromLOV, int vpmsReqId) {
		Document doc = null;

		int boNode = 0, faultNode = 0, vpmsReqNode = 0,localIndex = 0,localIndex1 = 0, currentRiskObj = 0, vpmsMapReq = 0;
		int vpmsMapReqs[], filedNodes[];
		
		String attrName, attrXpath, attrRepetable, attrMaxLimit, attrDefaultValue, attrFormatType,attrSubFieldXPath,attrSubFieldName,attrReadValObj,attrSubMaxLimit,
		attrSubDefaultValue,attrCoverItemNodes,attrCoverItemNodeSeqField,contentVal = "", tempattrName = "";
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

		doc = BSF.getXMLDocument();
		if ("".equalsIgnoreCase(caseId.toString()) || "".equalsIgnoreCase(riskno.toString())) {
			faultNode = doc.createElement("RESPONSE");
			doc.createTextElement("Validations", "Please fill all mandatory fields to proceed.", faultNode);
			return faultNode;
		}

		// Below code to fetch latest Business object by caseId.
		String queryText = "SELECT CASE_ID,BUSINESS_OBJECT FROM MSIG_BUSINESS_OBJECT WITH (NOLOCK) WHERE CASE_ID = :caseId";
		QueryObject query = new QueryObject(queryText);
		query.addParameter("caseId", "MSIG_BUSINESS_OBJECT.CASE_ID", QueryObject.PARAM_STRING, new String(caseId));
		query.setResultClass(MSIG_BUSINESS_OBJECT.class);
		BusObject boObject = (MSIG_BUSINESS_OBJECT) query.getObject();
		// End

		try {
			// To read Business object xml from boObject
			String businessObject = ((MSIG_BUSINESS_OBJECTBase) boObject).getBUSINESS_OBJECT();
			// Creating int Node from busienessObject string.
			boNode = doc.parseString(businessObject);			
			
			String[] paramNames = { "key" };
			String[] paramValues = { "com/msig/insurance/vpms/FireVPMSMapRequest.xml" };
			SOAPRequestObject sro = new SOAPRequestObject("http://schemas.cordys.com/1.0/xmlstore", "GetXMLObject",paramNames, paramValues);
			vpmsMapReq = sro.execute();
			vpmsMapReqs = XPath.getMatchingNodes(".//FIRE", null, vpmsMapReq);
			String attrObjReference = Node.getAttribute(vpmsMapReqs[vpmsReqId], "objReference");
			attrObjReference = (attrObjReference == null || "".equalsIgnoreCase(attrObjReference))?"fireIDC":attrObjReference;
			//cordysLogger.error("attrObjReference :: "+attrObjReference );
			// End
			
			//getting FireVPMS Request by VPMS requestId.
			int riskObjs[] = XPath.getMatchingNodes(".//"+attrObjReference, null,boNode);
			/*if(0 == vpmsReqId) {
				riskObjs = XPath.getMatchingNodes(".//fireIDC", null,boNode);
			}
			else if(1 == vpmsReqId) {
				riskObjs = XPath.getMatchingNodes(".//s4846", null,boNode);
			}*/
			
			currentRiskObj = 0;//XPath.getFirstMatch(".//businessObject/bms/newBusiness/risks/fireIDC", null, boNode);
			for(int eachRiskObj = 0;eachRiskObj < riskObjs.length; eachRiskObj++) {	
				if(riskno.equalsIgnoreCase(Node.getData(XPath.getFirstMatch("riskNumber", null, riskObjs[eachRiskObj])).toString())) {
					//cordysLogger.error(" 2 :: riskno -- "+riskno);
					currentRiskObj = riskObjs[eachRiskObj];
				}
			}
			
			
			filedNodes = XPath.getMatchingNodes(".//Fields", null, vpmsMapReqs[vpmsReqId]);
			//filedNodes = XPath.getMatchingNodes(".//Fields", null, vpmsMapReq[0]);
			//cordysLogger.error(" FIELD NODES >> "+Node.writeToString(vpmsMapReqs[vpmsReqId], true));
			//Node.delete(vpmsMapReq);
			
			Map<String, String> boMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < filedNodes.length; i++) {				
				try {
					//cordysLogger.error(" CURRENT FIELD NODES >> "+Node.writeToString(filedNodes[i], true));
					//tempattrName = (i==0)?Node.getAttribute(filedNodes[i], "Name"):Node.getAttribute(filedNodes[i-1], "Name");
					tempattrName = (i==0)?Node.getAttribute(filedNodes[i], "Name"):(i+1 >= filedNodes.length)?Node.getAttribute(filedNodes[i-1], "Name"):Node.getAttribute(filedNodes[i+1], "Name");
					//tempattrName = (i+1 >= filedNodes.length)?Node.getAttribute(filedNodes[i-1], "Name"):Node.getAttribute(filedNodes[i+1], "Name");
					attrName = Node.getAttribute(filedNodes[i], "Name");				
					attrXpath = Node.getAttribute(filedNodes[i], "XPath");				
					attrRepetable = Node.getAttribute(filedNodes[i], "Repetable");
					attrMaxLimit = Node.getAttribute(filedNodes[i], "MaxLimit");
					attrDefaultValue = Node.getAttribute(filedNodes[i], "DefaultValue");
					attrFormatType = Node.getAttribute(filedNodes[i], "formatType");
					attrSubFieldXPath = Node.getAttribute(filedNodes[i], "subFieldXPath");
					attrSubFieldName = Node.getAttribute(filedNodes[i], "subFieldName");
					attrReadValObj = Node.getAttribute(filedNodes[i], "readValObj");
					attrSubMaxLimit = Node.getAttribute(filedNodes[i], "subMaxLimit");
					attrSubDefaultValue = Node.getAttribute(filedNodes[i], "subDefaultValue");
					attrCoverItemNodes = Node.getAttribute(filedNodes[i], "itemNodes");
					attrCoverItemNodes = (attrCoverItemNodes != null && attrCoverItemNodes != "")?attrCoverItemNodes:".//fireItems/fireItems";
					attrCoverItemNodeSeqField = Node.getAttribute(filedNodes[i], "itemNodeSeqField");
					// fetching value from Business object xml by mathching XPath
					int maxLimitVal = Integer.parseInt(attrMaxLimit);
					//cordysLogger.error(" CURRENT FIELD NODES >> "+Node.writeToString(filedNodes[i], true)+" -- CASE ID :: "+caseId);
					//cordysLogger.error(" INSIDE FIELDS NODE ..."+attrName+" -- XPATH :: "+attrXpath + " -- CASE ID :: "+caseId);
					
					if ("Y".equalsIgnoreCase(attrRepetable)) {
						if ("".equalsIgnoreCase(attrXpath) || attrXpath == null) {
							for (int j = 0; j < maxLimitVal; j++) {
								contentVal = attrDefaultValue;
								boMap.put(attrName + "[" + j + "]", contentVal);
							}
							
						} else if("A_Fire_RateableClause".equalsIgnoreCase(attrName)) {					
							int tempFireCoverItemNodes[] = XPath.getMatchingNodes(attrCoverItemNodes, null, currentRiskObj); 
							//int tempFireCoverItemNodes[] = XPath.getMatchingNodes(".//fireItems/fireItems", null, currentRiskObj);
							int tempClauseNodes[] = XPath.getMatchingNodes(attrXpath.trim(), null, currentRiskObj);
							maxLimitVal = (tempClauseNodes.length > maxLimitVal) ? maxLimitVal : tempClauseNodes.length;
							int maxLen = Integer.parseInt(attrSubMaxLimit);//tempFireCoverItemNodes.length
							if(tempClauseNodes.length > 0) {
								for (int eachClause = 0; eachClause <= maxLimitVal; eachClause++) {						
									try {
										int selCoverItemsLen[] =  XPath.getMatchingNodes("descendant-or-self::coverItems", null, tempClauseNodes[eachClause]);
										for(int eachFireCoverItemNode = 0;eachFireCoverItemNode < maxLen ; eachFireCoverItemNode++) {									
											//cordysLogger.error(" INSIDE FOR EACH ITEM >> itemNo :: "+" tempFireCoverItemNodes[eachFireCoverItemNode]) :: "+tempFireCoverItemNodes[eachFireCoverItemNode]);
											String itemNo = "";
											try {
												itemNo = Node.getData(XPath.getFirstMatch(attrCoverItemNodeSeqField, null, tempFireCoverItemNodes[eachFireCoverItemNode]));
												//itemNo = Node.getData(XPath.getFirstMatch("./itemNo/text()", null, tempFireCoverItemNodes[eachFireCoverItemNode]));									
												if(selCoverItemsLen.length > 0 && itemNo != null && itemNo != "") { 
													//cordysLogger.error(" INSIDE FOR EACH COVER ITEM >> selCoverItemsLen.length :: "+selCoverItemsLen.length+" >> itemNo :: "+itemNo);
													for(int eachSelCoverItems = 0;eachSelCoverItems < selCoverItemsLen.length; eachSelCoverItems++) {
														String coverVal = Node.getData(XPath.getFirstMatch("descendant-or-self::coverItems/text()", null, selCoverItemsLen[eachSelCoverItems]));
														if( !"".equalsIgnoreCase(coverVal) && coverVal != null && itemNo.equals(coverVal)) {												
															//cordysLogger.error("Cover Value :: "+coverVal + " -- CLAUSE CODE :: "+Node.getData(XPath.getFirstMatch("./classCode/text()", null, tempClauseNodes[eachClause])));
															contentVal = coverVal;
															break;
														}
														else {
															contentVal = "0";
														}
													}
												} else {
													contentVal = "0";
												}
											} catch (Exception e) {
												itemNo = "";
												contentVal = "0";
												cordysLogger.error("ERROR OCCURED WHILE ITERATING FOR EACH FIRE ITEM for FIELD >> "+attrName+" >> ERROR :: "+e.getLocalizedMessage());
												//e.printStackTrace();
											}						
											boMap.put(attrName + eachFireCoverItemNode + attrSubFieldName+"[" + localIndex + "]", contentVal);
										}
										localIndex++;//using localIndex inorder to maintain sequence value for all Rateable, Perils, Warranty Clauses & FEA & Standard Clauses.
									} catch (Exception e) {
										cordysLogger.error("ERROR OCCURED WHILE ITERATING FOR EACH CLAUSE for FIELD >> "+attrName+" >> ERROR :: "+e.getLocalizedMessage()+" >> CASE ID :: "+caseId+" >> RISK NUMBER :: "+riskno+" >> vpmsReqId :: "+vpmsReqId);
									}
								}
							} else {
								boMap.put(attrName + "0" + attrSubFieldName+"[" + localIndex + "]", attrSubDefaultValue);
							}
						}
						else { 
							int tempNodes[] = XPath.getMatchingNodes(attrXpath.trim(), null, currentRiskObj);
							maxLimitVal = (tempNodes.length > maxLimitVal) ? maxLimitVal : tempNodes.length;
							if(tempNodes.length > 0) {
								for (int k = 0; k < maxLimitVal; k++) {
									//cordysLogger.error("INSIDE >> FOR >> FIELD_NAME >> "+attrName+" FIELD VALUE >> "+Node.getData(XPath.getFirstMatch(attrSubFieldXPath.trim(), null, tempNodes[k])));
									contentVal = ("".equalsIgnoreCase(attrSubFieldXPath) || attrSubFieldXPath == null)?attrDefaultValue:Node.getData(XPath.getFirstMatch(attrSubFieldXPath.trim(), null, tempNodes[k]));
									if((contentVal == "" || contentVal == null) && attrSubDefaultValue != null) {
										contentVal = attrSubDefaultValue;
									}
									boMap.put(attrName + "[" + localIndex1 + "]", contentVal);
									localIndex1++;
									if((k+1) == maxLimitVal) {
										boMap.put(attrName + "[" + localIndex1 + "]", attrSubDefaultValue);
									}
								}
							} else { // creating an empty nodes if any values are not present in BO.
								boMap.put(attrName + "[" + localIndex1 + "]", attrSubDefaultValue);
							}
							//cordysLogger.error("OUTSIDE LOCAL INDEX CLEAR.. >> attrName "+attrName+" >>  tempattrName >> "+tempattrName);
							if(tempattrName != "" && tempattrName != null && !tempattrName.equals(attrName)) {
								localIndex1 = 0;
								//cordysLogger.error("INSIDE LOCAL INDEX CLEAR.. >> attrName "+attrName+" >>  tempattrName >> "+tempattrName);
							}
						}

					}
					else { // Repetable == "N"
						if ("".equalsIgnoreCase(attrXpath) || attrXpath == null) {
							contentVal = attrDefaultValue;
						} 
						else if (!"".equalsIgnoreCase(attrFormatType) && attrFormatType != null) {
							df = new SimpleDateFormat(attrFormatType);
							contentVal = Node.getData(XPath.getFirstMatch(attrXpath.trim(), null, boNode));
							if ("".equalsIgnoreCase(contentVal) || contentVal == null) {
								if (!"".equalsIgnoreCase(attrDefaultValue) && attrDefaultValue != null
										&& "TransacationDate".equalsIgnoreCase(attrDefaultValue)) {
									contentVal = df.format(new Date());
								} else {
									contentVal = "";
								}
							} else {
								contentVal = df.format(df1.parse(contentVal));
							}

						} else if(!"".equalsIgnoreCase(attrReadValObj) && attrReadValObj != null && "riskObj".equals(attrReadValObj)) {
							contentVal = Node.getData(XPath.getFirstMatch(attrXpath.trim(), null, currentRiskObj ));							
						} else if(!"".equalsIgnoreCase(attrReadValObj) && attrReadValObj != null && "fromLOV".equals(attrReadValObj)) {
							contentVal = Node.getData(XPath.getFirstMatch(attrXpath.trim(), null, agentInfoFromLOV ));
						} else {
							contentVal = Node.getData(XPath.getFirstMatch(attrXpath.trim(), null, boNode));
						}
						if((contentVal == "" || contentVal == null) && attrSubDefaultValue != null) {
							contentVal = attrSubDefaultValue;
						}
						boMap.put(attrName, contentVal);
					}

					attrName = ""; attrXpath = ""; attrRepetable = ""; attrMaxLimit = "";
					attrDefaultValue = ""; attrFormatType = "";
					df = null;
				} catch (Exception e) {
					cordysLogger.error("ERROR OCCURED WHILE PROCESSING FOR EACH FIELDS INFO FROM FIELDS MAPPING NODE FROM CONFIGURATION... CASE ID :: "+caseId+" >> RISK NUMBER :: "+riskno+" >> vpmsReqId :: "+vpmsReqId);
					e.printStackTrace();
				} finally {
					attrName = ""; attrXpath = ""; attrRepetable = ""; attrMaxLimit = "";
					attrDefaultValue = ""; attrFormatType = "";
					df = null;
				}
			}

			// Prepare Header Level Data.
			vpmsReqNode = doc.createElement("processWsCommandSequence");
			int commandArrNode = doc.createElement("commandArray");
			Node.setAttribute(commandArrNode, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			Node.setAttribute(commandArrNode, "xsi:type", "ns2:wsCommandLoad");
			Node.setAttribute(commandArrNode, "xmlns", "");
			doc.createTextElement("vpmName", "/MSIG_FIRE_Integration.vpm", commandArrNode);
			Node.appendToChildren(commandArrNode, vpmsReqNode);
			commandArrNode = 0;
			for (Map.Entry<String, String> entry : boMap.entrySet()) {
				Node.appendToChildren(createCommandArrNode(entry.getKey(), "true", entry.getValue()), vpmsReqNode);
			}
			//cordysLogger.error("VPMS REQUEST :: " + Node.writeToString(vpmsReqNode, true) + " >> CASE ID :: "+caseId);
			boMap.clear();
			/*boNode = 0;
			currentRiskObj = 0;
			vpmsMapReq = 0;
			agentInfoFromLOV = 0;
			Node.delete(boNode);
			Node.delete(vpmsMapReq);
			Node.delete(currentRiskObj);
			Node.delete(agentInfoFromLOV);
			Node.delete(vpmsMapReq);*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boNode = 0;
			currentRiskObj = 0;
			vpmsMapReq = 0;
			agentInfoFromLOV = 0;
			
			/*//Node.delete(vpmsReqNode);
			Node.delete(boNode);
			Node.delete(vpmsMapReq);
			Node.delete(currentRiskObj);
			Node.delete(agentInfoFromLOV);
			Node.delete(vpmsMapReq);*/
		}

		return vpmsReqNode;
	}

	public static int createCommandArrNode(String computable, String setAttributeSuccessful, String value) {
		Document doc = null;
		int commandArrNode = 0;
		try {
			doc = BSF.getXMLDocument();

			//commandArrNode = doc.createElement("commandArray xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:wsCommandSetAttribute\"");
			commandArrNode = doc.createElement("commandArray");
			Node.setAttribute(commandArrNode, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			Node.setAttribute(commandArrNode, "xsi:type", "ns2:wsCommandSetAttribute");
			Node.setAttribute(commandArrNode, "xmlns", "");
			doc.createTextElement("sessionId", "a1", commandArrNode);
			doc.createTextElement("computable", computable, commandArrNode);
			doc.createTextElement("setAttributeSuccessful", setAttributeSuccessful, commandArrNode);
			doc.createTextElement("value", value, commandArrNode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Node.delete(commandArrNode);
		}

		return commandArrNode;
	}

}