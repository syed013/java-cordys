/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.soap.SOAPRequestObject;
import com.cordys.cpc.notification.utils.LDAPUtility;
import com.cordys.notification.internal.cache.user.ContactInfo;
import com.eibus.xml.nom.Document;
import com.eibus.xml.nom.Node;
import com.eibus.xml.nom.XMLException;
import com.eibus.xml.xpath.XPath;


public class EmailUtil extends EmailUtilBase
{
    public EmailUtil()
    {
        this((BusObjectConfig)null);
    }

    public EmailUtil(BusObjectConfig config)
    {
        super(config);
    }

    public static BusObjectIterator<com.msig.masterdata.EmailUtil> getEmailUtilObjects(com.cordys.cpc.bsf.query.Cursor cursor)
    {
        return null;
    }

    public static int sendEmailNotification(int request) throws XMLException, UnsupportedEncodingException
    {
    	if (request == 0) {
    		return 0;
    	}
    	
    	String senderEmailId = Node.getData(XPath.getFirstMatch("descendant-or-self::SENDER_EMAIL_ID", null, request));
    	String senderDisplayName = Node.getData(XPath.getFirstMatch("descendant-or-self::SENDER_DISPLAY_NAME", null, request));
    	String userName = Node.getData(XPath.getFirstMatch("descendant-or-self::USER_NAME", null, request));
    	String emailBody = Node.getData(XPath.getFirstMatch("descendant-or-self::EMAIL_BODY", null, request));
    	String type = Node.getData(XPath.getFirstMatch("descendant-or-self::TYPE", null, request));
    	String dn = Node.getData(XPath.getFirstMatch("descendant-or-self::DN", null, request));
    	String subject = Node.getData(XPath.getFirstMatch("descendant-or-self::SUBJECT", null, request));
    	String isAttachment = Node.getData(XPath.getFirstMatch("descendant-or-self::IS_ATTACHMENT", null, request));
    	String attachmentName = Node.getData(XPath.getFirstMatch("descendant-or-self::ATTACHMENT_NAME", null, request));
    	String attachmentContent = Node.getData(XPath.getFirstMatch("descendant-or-self::ATTACHMENT_CONTENT", null, request));
    	String adminUserDN = Node.getData(XPath.getFirstMatch("descendant-or-self::ADMIN_DN", null, request));

		// Get all user email addresses from Role.
		HashMap<String, String> emailAddrList = new HashMap<>();
		HashSet<String> emails = new HashSet<>();
		ArrayList<String> usersList = null;
		
    	String organizationDN = BSF.getOrganization();
    	
    	if (type.equals("ROLE")) {
    		usersList = (ArrayList<String>) LDAPUtility
    				.getAllUsersForRole(dn, organizationDN);
    		for (String user : usersList) {
    			EmailUtil.getEmailInfoFromUser(user, emailAddrList, emails);
    		}
    	} else {
    		EmailUtil.getEmailInfoFromUser(dn, emailAddrList, emails);
    	}

		if (emails.isEmpty()) {
			return EmailUtil.createResponse("No emails for the users", false);
		}

		SOAPRequestObject sendMailSRO = new SOAPRequestObject(
				"http://schemas.cordys.com/1.0/email", "SendMail",
				new String[] { "subject" }, new String[] { subject });

		Document doc = BSF.getXMLDocument();

		StringBuffer mailContent = new StringBuffer();
		mailContent.append("Dear " + userName + ",<br/><br/>");
		mailContent.append(emailBody + "<br/><br/><br/>");
		mailContent.append("Thanks,<br/>MSIG Team<br/>");

		int toNode = 0;
		int bodyNode = 0;
		int fromNode = 0;
		int attachmentsNode = 0;
		int attachmentNode = 0;

		try {
			// Apppend all 'to' addresses
			toNode = doc.createElement("to");
			int addressNode = 0;
			for (String key : emailAddrList.keySet()) {
				addressNode = Node.createElement("address", toNode);
				Node.createTextElement("emailAddress", key, addressNode);
				Node.createTextElement("displayName", emailAddrList.get(key)
						.toString(), addressNode);
			}
			sendMailSRO.addParameterAsXml(toNode);

			// Append body node
			bodyNode = doc.createCDataElement("body", mailContent.toString());
			Node.setAttribute(bodyNode, "type", "html");
			sendMailSRO.addParameterAsXml(bodyNode);

			if (isAttachment.equals("Y") && !attachmentContent.equals("") && !attachmentName.equals("")) {
				// Append attachment node
				attachmentsNode = doc
						.parseString("<attachments></attachments>");
				attachmentNode = Node.createCDataElement("attachment",
						attachmentContent, attachmentsNode);
				Node.setAttribute(attachmentNode, "name", attachmentName);
				Node.setAttribute(attachmentNode, "encoded", "true");
				sendMailSRO.addParameterAsXml(attachmentsNode);
			}
			// Append 'from' node
			fromNode = doc.createElement("from");
			addressNode = Node.createElement("address", fromNode);
			Node.createTextElement("emailAddress", senderEmailId, addressNode);
			Node.createTextElement("displayName", senderDisplayName,
					addressNode);
			sendMailSRO.addParameterAsXml(fromNode);
			// Admin Dn
			sendMailSRO.setUser(adminUserDN);

			sendMailSRO.execute();
		} finally {
			if (Node.isValidNode(attachmentNode)) {
				Node.delete(attachmentNode);
				attachmentNode = 0;
			}
			if (Node.isValidNode(attachmentsNode)) {
				Node.delete(attachmentsNode);
				attachmentsNode = 0;
			}
			if (Node.isValidNode(fromNode)) {
				Node.delete(fromNode);
				fromNode = 0;
			}
			if (Node.isValidNode(bodyNode)) {
				Node.delete(bodyNode);
				bodyNode = 0;
			}
			if (Node.isValidNode(toNode)) {
				Node.delete(toNode);
				toNode = 0;
			}
		}
		String x = emails.toString();
    	
    	return EmailUtil.createResponse(x, true);
    }
    
    private static void getEmailInfoFromUser(String user, HashMap<String, String> emailAddrList, HashSet<String> emails) {
    	ContactInfo cInfo = LDAPUtility.getUserContactInfo(user);
		String eAddr = cInfo.getEmailAddress();
		String name = cInfo.getDisplayName();

		SOAPRequestObject sro = new SOAPRequestObject(
				"http://schemas.cordys.com/notification/workflow/1.0",
				"GetUserInboxPreferences", new String[] { "UserDN" },
				new String[] { user });

		int response = sro.execute();

		String defaultDelivery = Node.getData(XPath.getFirstMatch(
				"descendant-or-self::DEFAULT_DELIVERY", null, response));

		if (!defaultDelivery.equals("BOTH")) {
			return;
		}

		if (emails.contains(eAddr)) {
			return;
		} else {
			emails.add(eAddr);
		}
		if (!eAddr.equals(""))
			emailAddrList.put(eAddr, name);
    }
    
    private static int createResponse(String response, boolean isSuccess) {
    	Document doc = BSF.getXMLDocument();
    	
    	int rootNode = doc.createElement("RESPONSE");
    	if (isSuccess) {
    		Node.createTextElement("SUCCESS", response, rootNode);
    	} else {
    		Node.createTextElement("FAULT", response, rootNode);
    	}
    	return rootNode;
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

}
