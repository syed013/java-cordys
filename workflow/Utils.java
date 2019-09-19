/*
  This class has been generated by the Code Generator
*/

package com.cms.workflow;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObject;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.soap.SOAPRequestObject;
import com.eibus.util.logger.Severity;
import com.eibus.xml.nom.Node;
import com.eibus.util.logger.CordysLogger;
import com.eibus.util.logger.Severity;
import com.eibus.xml.xpath.XPath;
import com.eibus.xml.xpath.XPathMetaInfo;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;



public class Utils extends UtilsBase
{
	private static CordysLogger Logger = CordysLogger.getCordysLogger(Utils.class);
        private static XPathMetaInfo meta = new XPathMetaInfo();


    public Utils ()
    {
        this((BusObjectConfig)null);
    }

    public Utils (BusObjectConfig config)
    {
        super(config);
    }

    private static int executeSOAPRequest(int SOAPRequest, String userDN) throws Exception
    {
		int mnode = Node.clone(SOAPRequest, true);
		String methodName = null;
		try {
	        methodName = Node.getName(mnode);
	        String METHODSET_NAMESPACE = Node.getAttribute(mnode, "xmlns");
	        int paramnode = Node.getFirstChild(mnode);
	        SOAPRequestObject soapRequestObject = new SOAPRequestObject(METHODSET_NAMESPACE, methodName, null, null);
	        soapRequestObject.setUser(userDN);
	        for (int j = 0; j < Node.getNumChildren(mnode); j++) {
	            soapRequestObject.addParameterAsXml(paramnode);
	            int tempnode = Node.getNextElement(paramnode);
	            paramnode = tempnode;
	        }
            return soapRequestObject.execute();
        } catch (Exception e) 
		{
        	throw new Exception( methodName + "Wrapper call failed" , e);
        }
		finally
		{
			Node.delete(mnode);
            mnode = 0;
		}
    }

	 public static int executeCMSWorkflowHelper(String workFlowButtonAction, String caseId, String userDN) throws Exception
    {
        String soapXMLStr = "<CMSWorkflowHelper xmlns=\"http://schemas.insurance.com/businessobject/1.0/\">	<caseId>"+caseId+"</caseId><workFlowButtonAction>"+workFlowButtonAction+"</workFlowButtonAction></CMSWorkflowHelper>";

			int reqXML = BSF.getXMLDocument().parseString(soapXMLStr);
	        return executeSOAPRequest(reqXML, userDN);
    }
public static String validateInvoice(String inputXML) 
   {
     String responseMsg = "";
HashMap<String,String[]> lovValues=null;
HashMap<String,String> claimRefIdValidation=null,lovFields=null;
int reqXML=0,invoiceDetailNode=0;
try{


		reqXML = BSF.getXMLDocument().parseString(inputXML);
                invoiceDetailNode = Node.getElement(reqXML,"invoiceDetails");
		String claimRefId = Node.getDataElement(reqXML,"caseId","");
		String invoiceId = Node.getDataElement(reqXML,"invoiceId","");
		String invoiceDate = Node.getDataElement(reqXML,"invoiceDate","");
                String totalAmountInclusiveGST = Node.getDataElement(invoiceDetailNode,"totalAmountInclusiveGST","");
                lovValues = new HashMap<String,String[]>();
		lovValues.put("Payee Code",new String[]{"1","2","3","4","5"});
		lovValues.put("Letter to",new String[]{"A","B","C","D"});
		lovValues.put("Tax Invoice Type",new String[]{"L"});
		lovValues.put("Invoice in MSIGâ€™s Name",new String[]{"Y","N"});
		lovValues.put("Is Payee equal to Insured?",new String[]{"Y","N"});
		lovValues.put("GST Payable",new String[]{"Y","N"});
		lovValues.put("KFX",new String[]{"Y","N"});
		lovValues.put("Ex-gratia",new String[]{"Y","N"});

                lovFields = new HashMap<String,String>();
                lovFields.put("Payee Code","payeeCode");
		lovFields.put("Letter to","letterTo");
		lovFields.put("Tax Invoice Type","taxInvoiceType");
		lovFields.put("Invoice in MSIGâ€™s Name","invoiceInMSIGName");
		lovFields.put("Is Payee equal to Insured?","isPayeeInsured");
		lovFields.put("GST Payable","GSTPayable");
		lovFields.put("KFX","KFX");
		lovFields.put("Ex-gratia","exgratia");
                lovFields.put("Premium Class","premiumClass");

		 claimRefIdValidation =   validateClaimRefId(claimRefId,totalAmountInclusiveGST);
		if(claimRefIdValidation.get("Premium Class").equals("No Values for Premium Class."))
		lovValues.put("Premium Class",null);
		else
		lovValues.put("Premium Class",claimRefIdValidation.get("Premium Class").split(","));

		if(!(claimRefIdValidation.get("Claim Reference Id").equals("Success")))
		responseMsg = responseMsg+" "+claimRefIdValidation.get("Claim Reference Id");
		if(claimRefIdValidation.containsKey("Total Amount"))
		responseMsg = responseMsg+" "+claimRefIdValidation.get("Total Amount");


		claimRefIdValidation.clear();
      		responseMsg = responseMsg+validateLovs(reqXML,lovValues,lovFields);

                responseMsg = responseMsg+checkInvoiceIdInvoiceDate(claimRefId,invoiceId,invoiceDate);



}
catch(Exception e)
{
responseMsg =e.getMessage();
}
finally
{
Node.delete(reqXML);
  lovValues.clear();
                lovFields.clear();



}

return  ("").equals(responseMsg)?"Success":responseMsg;
   }
 public static HashMap<String,String> validateClaimRefId(String claimRefId,String totalAmountInclusiveGST) throws Exception
  {
       HashMap<String,String> validationResult = new HashMap<String,String>();
	   float reserveAmount = 0,totalGSTAmount=0;
	   try{

       totalGSTAmount = Float.parseFloat(totalAmountInclusiveGST);	   
	   }
	   catch(NumberFormatException e)
	   {

	   validationResult.put("Total Amount","Invalid data in Total Invoice Amount.");
	   }
       //Fire GetBusinessobject for Claim Ref Id
       String soapXMLStr = "<GetBusinessObjectRequest  xmlns=\"http://schemas.insurance.com/businessobject/1.0/\"><caseId>"+claimRefId+"</caseId><withDescriptions>false</withDescriptions></GetBusinessObjectRequest >";

	   int reqXML = BSF.getXMLDocument().parseString(soapXMLStr);
	   int getBusinessObjectResponse = executeSOAPRequest(reqXML);


	 if(Node.isValidNode(getBusinessObjectResponse)==false||((Node.isValidNode(getBusinessObjectResponse)==true)&&((!(("No Fault").equals(Node.getDataElement(getBusinessObjectResponse ,"fault","No Fault"))))||(!(("Claim").equals(Node.getDataElement(getBusinessObjectResponse ,"requestType",""))))||(("").equals(Node.getDataElement(XPath.getFirstMatch("//GetBusinessObjectResponse/success/ApplicationBusinessObject/caseInfo/claimNumber", meta, getBusinessObjectResponse),"claimNumber",""))||(Node.isValidNode(XPath.getFirstMatch("//GetBusinessObjectResponse/success/ApplicationBusinessObject/businessObject/cms/claim/claimReserveAnalysis/claimReserveItems/claimReserveItem", meta, getBusinessObjectResponse))==false)))))
	   {
	   validationResult.put("Claim Reference Id","Invalid Claim Reference Id.");
	   validationResult.put("Premium Class","No Values for Premium Class.");

	   }
	   else
	   {
	   XPathMetaInfo meta = new XPathMetaInfo();
	   int []claimReserveItems = XPath.getMatchingNodes("//claimHeader/claimReserveAnalysis/claimReserveItems/claimReserveItem",meta,reqXML);
	   boolean reserveAmountAvailable = false;
	   HashSet<String> premiumCodeValues = new HashSet<String>();
	   for(int item:claimReserveItems)
	   {
	   String premiumCode = Node.getDataElement(item,"premiumClass","");
	   if(!"".equals(premiumCode ))
	   premiumCodeValues.add(premiumCode);
	   if("FS".equals(Node.getDataElement(item,"reserveCode",""))&&Float.parseFloat(Node.getDataElement(item,"estimateAmount","0"))>0)
	   {
	   reserveAmount =Float.parseFloat(Node.getDataElement(item,"estimateAmount","0"));
	   reserveAmountAvailable = true;
	   }
	   }
	   if(reserveAmountAvailable==false||reserveAmount == 0)
	   {
	   validationResult.put("Claim Reference Id","Invalid Claim Reference Id.");
	   validationResult.put("Premium Class","No Values for Premium Class.");

	   }
	   else
	   {
	   validationResult.put("Claim Reference Id","Success");
	   String premiumValues = "";
	   for(String code:premiumCodeValues)
	   {
	   if("".equals(premiumValues))
	   premiumValues = code;
	   else
	   premiumValues = code+","+premiumValues;
	   }
	   validationResult.put("Premium Class",premiumValues);
	   }	   
	   if(validationResult.get("Total Amount")==null)
	   {
	   if(totalGSTAmount == 0)
	   validationResult.put("Total Amount","Total Invoice Amount should be greater than 0.");
	   else if(totalGSTAmount>reserveAmount)
	   validationResult.put("Total Amount","Total Invoice Amount should be less than Reserve Amount.");
	   }   
	   }
          Node.delete(getBusinessObjectResponse);
          Node.delete(reqXML);

	   return validationResult;

  }
 public static String validateLovs(int reqXML,HashMap<String,String[]> lovValues,HashMap<String,String> lovFields) throws Exception
   {     
        String responseMsg = "",dataElement="";

        for(String field:lovFields.keySet())
		{
                 //Logger.error("Field is :"+field);
		dataElement = Node.getDataElement(reqXML,lovFields.get(field),"");

		if(field.equals("Payee Code")&&dataElement.equals(""))
		responseMsg= responseMsg+"  Payee Code cannot be empty.";
		else if(!("".equals(dataElement))&&isValuePresentInLov(lovValues.get(field),dataElement)==false)
		{
		responseMsg= responseMsg+"  The value "+dataElement+" is not present in its list of values of "+field+".";
		}



		}


  return responseMsg;
  }
  public static boolean isValuePresentInLov(String lovs[],String value)
  {
  for(String v:lovs)
  {
  if(value.equals(v))
  return true;
  }
  return false;
  }

   private static int executeSOAPRequest(int SOAPRequest) throws Exception
    {
		int mnode = Node.clone(SOAPRequest, true);
		String methodName = null;
		try {
	        methodName = Node.getName(mnode);

	        String METHODSET_NAMESPACE = Node.getAttribute(mnode, "xmlns");
	        int paramnode = Node.getFirstChild(mnode);
	        SOAPRequestObject soapRequestObject = new SOAPRequestObject(METHODSET_NAMESPACE, methodName, null, null);
	            for (int j = 0; j < Node.getNumChildren(mnode); j++) {
	            soapRequestObject.addParameterAsXml(paramnode);
	            int tempnode = Node.getNextElement(paramnode);
	            paramnode = tempnode;
	        }
            return soapRequestObject.execute();
        } catch (Exception e) 
		{
        	throw new Exception( methodName + "Wrapper call failed" , e);
        }
		finally
		{
			Node.delete(mnode);
            mnode = 0;
		}
    }
 public static String checkInvoiceIdInvoiceDate(String claimRefId,String invoiceId,String invoiceDate)
   {
   String responseMsg="";
   if("".equals(invoiceId))
   {
   responseMsg=responseMsg+" Invoice Id cannot be empty.";
   }
   else
   {
   int req= 0,claimRefIdInvoiceIdComb=0;
 try{
   req=BSF.getXMLDocument().parseString(	"<GetLOVData xmlns='http://schemas.opentext.com/lovhandler/v1.0' ><BRANCH>ALL</BRANCH><LOB>ALL</LOB><BUSINESS_FUNCTION>CLAIMS</BUSINESS_FUNCTION><PRODUCT>ALL</PRODUCT><OPERATION>ALL</OPERATION><FORM_NAME>INVOICE</FORM_NAME><FORM_FIELD_NAME>Claim Ref Invoice Combination</FORM_FIELD_NAME><FIELD_TYPE>LOV</FIELD_TYPE><ADVANCE_CONFIG_XML><FILTERS><FILTER FIELD_NAME=\"CLAIM_REFRENCE_ID\" FIELD_VALUE=\""+claimRefId+"\" OPERATION=\"EQ\" CONDITION=\"AND\"/><FILTER FIELD_NAME=\"INVOICE_ID\"  FIELD_VALUE=\""+invoiceId+"\" OPERATION=\"EQ\" CONDITION=\"AND\"/></FILTERS></ADVANCE_CONFIG_XML></GetLOVData>");


 claimRefIdInvoiceIdComb =executeSOAPRequest(req);

	if(Node.isValidNode(claimRefIdInvoiceIdComb)&&Node.isValidNode(Node.getElement(claimRefIdInvoiceIdComb,"tuple"))==false)
      {
//Logger.error("Calim Invoice Comb Msg:"+Node.writeToString(claimRefIdInvoiceIdComb,true));
}
else
	{
	responseMsg=responseMsg+" Combination of Claim Reference Id and Invoice Id is already Present.";
	}
}
catch(Exception e)
{
Logger.error("Exception check Invoice Id "+e.getMessage());
responseMsg=responseMsg+" Combination of Claim Reference Id and Invoice Id is already Present.";
}
        Node.delete(claimRefIdInvoiceIdComb);
	Node.delete(req);

   }
   if("".equals(invoiceDate))
   {
   responseMsg=responseMsg+" Invoice Date cannot be empty";
   }
   else{
   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	sdf.setLenient(false);

	try {

		//if not valid, it will throw ParseException
		Date date = sdf.parse(invoiceDate);
		Date currentDate = new Date();
		if(currentDate.getTime()<date.getTime())
		responseMsg = responseMsg+" Invoice Date cannot be greater than current date.";

		} catch (ParseException e) {

		responseMsg=responseMsg+" Invalid Invoice Date format.Please enter proper date less than equal to current date.";

	}
   }
//Logger.error("CLaim Ref Invoice Id COmb response msg: "+responseMsg);
   return responseMsg;
   }

   public static String checkValidInvoiceDate(String invoiceDate)
   {
    if("".equals(invoiceDate))
    return "Invoice Date cannot be empty";
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	sdf.setLenient(false);

	try {

		//if not valid, it will throw ParseException
		Date date = sdf.parse(invoiceDate);
		Date currentDate = new Date();
		if(currentDate.getTime()<date.getTime())
		return " Invoice Date cannot be greater than current date.";

		} catch (ParseException e) {

		return " Invalid Invoice Date format.Please enter proper date less than equal to current date.";

	}
           return "";
   }

 public static String isValidDate(String dataString)
   {

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	sdf.setLenient(false);

	try {

		//if not valid, it will throw ParseException
		Date date = sdf.parse(dataString);


		} catch (ParseException e) {

		return "Invalid Date";

	}
           return "";
   }

  public static String isValidDateWithFormat(String dataString, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dataString);
            //System.out.println(date);

        } catch (ParseException e) {

            return "Invalid Date";

        }
        return "";
    }

    public static String convertDateToTimestampFormat(String dataString, String format,String withSpace) {
        String outDate = "";
        String outFormat;
        if(withSpace.equals("true")){
        	outFormat = "yyyy-MM-dd 'T' HH:mm:SS.s";
        }
        else{
        	outFormat = "yyyy-MM-dd'T'HH:mm:SS.s";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        SimpleDateFormat sdf2 = new SimpleDateFormat(outFormat);
        try {
            Date date = sdf.parse(dataString);
            //System.out.println(date);
            outDate = sdf2.format(date);
        } catch (ParseException e) {
            outDate = "Invalid Date";
        }
        return outDate;
    }

   public static String doPadding(String source, String subString, int paddingPosition) {
		String resultString = "";
		resultString = source+subString;
		resultString = StringUtils.rightPad(resultString, paddingPosition, " ");
		return resultString;
   }
   
  public static int wordCount(String string, String seperator){
		
		StringTokenizer tokenizer = new StringTokenizer(string,seperator);
		return tokenizer.countTokens();
		
	}

 public static double roundToDecimal(int decimalPoints,String number){
    	BigDecimal temp = new BigDecimal(String.valueOf(number)).setScale(decimalPoints, RoundingMode.HALF_UP);
    	double retval = temp.doubleValue();
    	return retval;
    }

public static String formatNumber(int number){
		DecimalFormat df = new DecimalFormat("#.00");
		String formatted = df.format(number);
		return formatted;
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

    public static String callCMSWorkflowHelperForUser(String userDN,String CaseID,String buttonAction) throws Exception{

        String[] paramNames = {"caseId","workFlowButtonAction"};
        String[] paramValues = {CaseID,buttonAction};
        SOAPRequestObject sro = new SOAPRequestObject("http://schemas.insurance.com/businessobject/1.0/", "CMSWorkflowHelper", paramNames,paramValues);
	sro.setUser(userDN);
	int userList = sro.execute();
              
	return   Node.writeToString(userList,true);
    } 

   public static boolean testRegex(String input,String pattern){
		if(input.matches(pattern)){
			return true;
		}
		return false;
   }

}
																