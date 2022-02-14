/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi.sahlservices.resources;

import java.util.Calendar;
import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 *
 * @author MOI
 */
public class SOAPClientSAAJ {

    private String civilId = "";
    private String ResidentNatNumber = "";
    private PostRequestStep1[] requestDataArray = null;
    private int stepNo = 0;
    private String amount = "";

    public void createSoapEnvelopeGet(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeGet--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "res";
        String myNamespaceURI = "http://tempuri.org/ResidencyFeesMaidVisasShl/";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        /*
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:res="http://tempuri.org/ResidencyFeesMaidVisasShl/">
   <soapenv:Header/>
   <soapenv:Body>
      <res:ResidencyFeesMaidVisasShlcall>
         <!--Optional:-->
         <ResidencyFeesMaidVisasShlImport command="" clientId="" clientPassword="" nextLocation="" exitState="0" dialect="">
            <!--Optional:-->
            <InputParameters>
               <!--Optional:-->
               <CivilId>282120305536</CivilId>
            </InputParameters>
         </ResidencyFeesMaidVisasShlImport>
      </res:ResidencyFeesMaidVisasShlcall>
   </soapenv:Body>
</soapenv:Envelope>
         */
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("ResidencyFeesMaidVisasShlcall", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ResidencyFeesMaidVisasShlImport");
        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("InputParameters");
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("CivilId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem3.addTextNode(civilId);
        //System.out.println("-----------------------------16-----------------------");
    }

    
    
     public void createSoapEnvelopePersonalDetails(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopePersonalDetails--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "per";
        String myNamespaceURI = "http://tempuri.org/PersonDetailsDisplay/";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        /*
           <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:per="http://tempuri.org/PersonDetailsDisplay/">
   <soapenv:Header/>
   <soapenv:Body>
      <per:PersonDetailsDisplaycall>
         <!--Optional:-->
         <PersonDetailsDisplayImport command="?" clientId="?" clientPassword="?" nextLocation="?" exitState="0" dialect="?">
            <!--Optional:-->
            <InputParameters>
               <!--Optional:-->
               <CivilId>240011300044</CivilId>
               <ResidentNatNumber>?</ResidentNatNumber>
            </InputParameters>
         </PersonDetailsDisplayImport>
      </per:PersonDetailsDisplaycall>
   </soapenv:Body>
</soapenv:Envelope>
         */
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("PersonDetailsDisplaycall", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("PersonDetailsDisplayImport");
        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("InputParameters");
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("ResidentNatNumber");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem3.addTextNode(ResidentNatNumber);
        //System.out.println("-----------------------------16-----------------------");
    }
    public void createSoapEnvelopeInitPayment(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeInitPayment--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "def";
        String myNamespaceURI = "http://www.moi.gov.kw/Services/PayGateService/V1/Definitions";
        String myNamespace1 = "moih";
        String myNamespaceURI1 = "http://www.moi.gov.kw/Common/V1/MoiHeader";
        String myNamespace2 = "wsse";
        String myNamespaceURI2 = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        String myNamespace3 = "wsu";
        String myNamespaceURI3 = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");
        envelope.addNamespaceDeclaration(myNamespace1, myNamespaceURI1);
        //System.out.println("-----------------------------10---------0--------------");
        SOAPHeader soapHeader = envelope.getHeader();
        //System.out.println("-----------------------------10--------00---------------");
       
        SOAPHeaderElement security = soapHeader.addHeaderElement(new QName(myNamespaceURI2, "Security", myNamespace2));
        security.addAttribute(new QName(myNamespaceURI2, "mustUnderstand", "soapenv"), "0");

        security.addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        security.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        //System.out.println("-----------------------------10------------1-----------");
        SOAPElement soapHeaderElem1 = security.addChildElement("UsernameToken", myNamespace2);
        soapHeaderElem1.addAttribute(new QName(myNamespaceURI3, "Id", "wsu"), "UsernameToken-6E7F82F20833FD165316396362254181");

        //System.out.println("-----------------------------10------------2-----------");
        SOAPElement soapHeaderElem2 = soapHeaderElem1.addChildElement("Username", myNamespace2);
        //System.out.println("-----------------------------10---------------3--------");
        soapHeaderElem2.addTextNode("MOISAHELTESTWSUser");
        //System.out.println("-----------------------------10-----------------4------");
        SOAPElement soapHeaderElem3 = soapHeaderElem1.addChildElement("Password", myNamespace2);
        soapHeaderElem3.addAttribute(new QName("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
        //System.out.println("-----------------------------10---------------5--------");
        soapHeaderElem3.addTextNode("ey+hn0nb9mpEbhhZ7M0qZk7u3zY=");
        SOAPElement soapHeaderElem4 = soapHeaderElem1.addChildElement("Nonce", myNamespace2);
        soapHeaderElem4.addAttribute(new QName("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");

        //System.out.println("-----------------------------10--------6---------------");
        soapHeaderElem4.addTextNode("cMlDW0fnanjXbcLKNiJc0Q==");
        SOAPElement soapHeaderElem5 = soapHeaderElem1.addChildElement("Created", myNamespace3);
        //System.out.println("-----------------------------10------7-----------------");
        soapHeaderElem5.addTextNode("2021-12-16T06:30:25.414Z");
        SOAPElement soapHeaderElem6 = soapHeader.addChildElement("MoiHeader", myNamespace1);
        SOAPElement soapHeaderElem7 = soapHeaderElem6.addChildElement("Entity");
        soapHeaderElem7.addTextNode("MOI");
        SOAPElement soapHeaderElem8 = soapHeaderElem6.addChildElement("Department");
        soapHeaderElem8.addTextNode("IT");
        SOAPElement soapHeaderElem9 = soapHeaderElem6.addChildElement("Application");
        soapHeaderElem9.addTextNode("SHLPAYVIEW");
        SOAPElement soapHeaderElem10 = soapHeaderElem6.addChildElement("Requestor");
        soapHeaderElem10.addTextNode(civilId);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("InitiatePaymentRequest", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("civilId");
        soapBodyElem1.addTextNode(civilId);
        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("totalPayAmt");
        soapBodyElem2.addTextNode(getAmount());
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("contactInfo");
        //System.out.println("-----------------------------15-----------------------");
        SOAPElement soapBodyElem4 = soapBodyElem3.addChildElement("emailId");
        SOAPElement soapBodyElem5 = soapBodyElem3.addChildElement("mobile");
        soapBodyElem5.addTextNode("00000000");
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("language");
        soapBodyElem6.addTextNode("AR");
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("currency");
        soapBodyElem7.addTextNode("KWD");
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("channelID");
        soapBodyElem8.addTextNode("4");
        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("paymentMethod");
        soapBodyElem9.addTextNode("1");
        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("serviceType");
        soapBodyElem10.addTextNode("6");
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("paymentList");

        SOAPElement soapBodyElem12 = soapBodyElem11.addChildElement("number");
        soapBodyElem12.addTextNode(requestDataArray[0].getValue());
        SOAPElement soapBodyElem13 = soapBodyElem11.addChildElement("year");
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
       
        soapBodyElem13.addTextNode(year.toString());
        SOAPElement soapBodyElem14 = soapBodyElem11.addChildElement("amount");
        soapBodyElem14.addTextNode(getAmount());
        SOAPElement soapBodyElem15 = soapBodyElem11.addChildElement("descriptionEn");
        soapBodyElem15.addTextNode("Domestic Workers Fees");
        SOAPElement soapBodyElem16 = soapBodyElem11.addChildElement("descriptionAr");
        soapBodyElem16.addTextNode("رسوم العمالة المنزلية");
        SOAPElement soapBodyElem17 = soapBodyElem11.addChildElement("field1");
        soapBodyElem17.addTextNode(requestDataArray[1].getValue());
        SOAPElement soapBodyElem18 = soapBodyElem11.addChildElement("field2");
        soapBodyElem18.addTextNode(requestDataArray[2].getValue());

    }

    public void createSoapEnvelopeCheckPaymentStatus(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeCheckPaymentStatus--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "urn";
        String myNamespaceURI = "urn:example";

        String myNamespace2 = "wsse";
        String myNamespaceURI2 = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        String myNamespace3 = "wsu";
        String myNamespaceURI3 = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        //System.out.println("-----------------------------10---------0--------------");
        SOAPHeader soapHeader = envelope.getHeader();
        //System.out.println("-----------------------------10--------00---------------");
       
        SOAPHeaderElement security = soapHeader.addHeaderElement(new QName(myNamespaceURI2, "Security", myNamespace2));
        security.addAttribute(new QName(myNamespaceURI2, "mustUnderstand", "soapenv"), "0");

        security.addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        security.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        //System.out.println("-----------------------------10------------1-----------");
        SOAPElement soapHeaderElem1 = security.addChildElement("UsernameToken", myNamespace2);
        soapHeaderElem1.addAttribute(new QName(myNamespaceURI3, "Id", "wsu"), "UsernameToken-46BBB499B7F114933D16407740520662");

        //System.out.println("-----------------------------10------------2-----------");
        SOAPElement soapHeaderElem2 = soapHeaderElem1.addChildElement("Username", myNamespace2);
        //System.out.println("-----------------------------10---------------3--------");
        soapHeaderElem2.addTextNode("MOIPACITESTWSUser");
        //System.out.println("-----------------------------10-----------------4------");
        SOAPElement soapHeaderElem3 = soapHeaderElem1.addChildElement("Password", myNamespace2);
        soapHeaderElem3.addAttribute(new QName("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
        //System.out.println("-----------------------------10---------------5--------");
        soapHeaderElem3.addTextNode("ZYyAwMo0KAWs1xX788RwIQQXKtw=");
        SOAPElement soapHeaderElem4 = soapHeaderElem1.addChildElement("Nonce", myNamespace2);
        soapHeaderElem4.addAttribute(new QName("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");

        //System.out.println("-----------------------------10--------6---------------");
        soapHeaderElem4.addTextNode("i8W+N7VU/wEB8eKPluCd5g==");
        SOAPElement soapHeaderElem5 = soapHeaderElem1.addChildElement("Created", myNamespace3);
        //System.out.println("-----------------------------10------7-----------------");
        soapHeaderElem5.addTextNode("2021-12-29T10:34:12.066Z");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("SahelPayView", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("CIVIL_ID");
        soapBodyElem1.addTextNode(civilId);

    }

  
    public void createSoapEnvelopePost(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7----------------createSoapEnvelopePost-------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "res";
        String myNamespaceURI = "http://tempuri.org/ResidencyFeesDisplayShl/";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");
       
 

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("ResidencyFeesDisplayShlcall", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ResidencyFeesDisplayShlImport");
        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("InputParameters");
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("CivilId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem3.addTextNode(civilId);
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem4 = soapBodyElem1.addChildElement("Array");
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem5 = soapBodyElem4.addChildElement("row");
        SOAPElement soapBodyElem17 = soapBodyElem5.addChildElement("InputItemModels");
        //System.out.println("-----------------------------15-----------------------");
        SOAPElement soapBodyElem6 = soapBodyElem17.addChildElement("Key");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem6.addTextNode(requestDataArray[0].getKey());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem7 = soapBodyElem17.addChildElement("Value");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem7.addTextNode(requestDataArray[0].getValue());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem8 = soapBodyElem17.addChildElement("Type");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem8.addTextNode(requestDataArray[0].getType());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem9 = soapBodyElem4.addChildElement("row");
        SOAPElement soapBodyElem18 = soapBodyElem9.addChildElement("InputItemModels");
        //System.out.println("-----------------------------15-----------------------");
        SOAPElement soapBodyElem10 = soapBodyElem18.addChildElement("Key");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem10.addTextNode(requestDataArray[1].getKey());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem11 = soapBodyElem18.addChildElement("Value");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem11.addTextNode(requestDataArray[1].getValue());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem12 = soapBodyElem18.addChildElement("Type");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem12.addTextNode(requestDataArray[1].getType());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem13 = soapBodyElem4.addChildElement("row");
        SOAPElement soapBodyElem19 = soapBodyElem13.addChildElement("InputItemModels");
        //System.out.println("-----------------------------15-----------------------");
        SOAPElement soapBodyElem14 = soapBodyElem19.addChildElement("Key");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem14.addTextNode(requestDataArray[2].getKey());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem15 = soapBodyElem19.addChildElement("Value");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem15.addTextNode(requestDataArray[2].getValue());
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem16 = soapBodyElem19.addChildElement("Type");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem16.addTextNode(requestDataArray[2].getType());
        //System.out.println("-----------------------------16-----------------------");
    }

    public SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String userId, int stepNo, PostRequestStep1[] requestDataArray) {
        SOAPMessage soapResponse = null;
        try {
            // Create SOAP Connection
            //System.out.println("---------beginning of sending soap request 1---------------");
            setCivilId(userId);
            if(stepNo==4)
            {
            setResidentNatNumber(userId);
            }
            //System.out.println("---------beginning of sending soap request 2---------------");
            if (stepNo != -1) {
                setStepNo(stepNo);
            }
            //System.out.println("---------beginning of sending soap request 3---------------");
            if (requestDataArray != null) {
                setRequestDataArray(requestDataArray);

            }
            //System.out.println("---------beginning of sending soap request 4---------------");
            //System.out.println("-----------------------------1-----------------------");
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            //System.out.println("-----------------------------2-----------------------");
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            //System.out.println("-----------------------------3-----------------------");
            // Send SOAP Message to SOAP Server
            soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
            soapConnection.close();
            

        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
        }
        return soapResponse;
    }

    private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        //System.out.println("-----------------------------4-----------------------");
        MessageFactory messageFactory = MessageFactory.newInstance();
       // System.out.println("-----------------------------5-----------------------");
        SOAPMessage soapMessage = messageFactory.createMessage();
        //System.out.println("-----------------------------6-----------------------");
        switch (stepNo) {
            case 0:

                createSoapEnvelopeGet(soapMessage);
                break;
            case 1:

                createSoapEnvelopePost(soapMessage);
                break;
            case 2:

                createSoapEnvelopeCheckPaymentStatus(soapMessage);
                break;
            case 3:

                createSoapEnvelopeInitPayment(soapMessage);
                break;
            case 4:

                createSoapEnvelopePersonalDetails(soapMessage);
                break;
            default:
                break;
        }

        //System.out.println("-----------------------------17-----------------------");
        MimeHeaders headers = soapMessage.getMimeHeaders();
        //System.out.println("-----------------------------18-----------------------");
        headers.addHeader("SOAPAction", soapAction);
        //System.out.println("-----------------------------19-----------------------");

        soapMessage.saveChanges();
        //System.out.println("-----------------------------20-----------------------");
        /* Print the request message, just for debugging purposes */
        //System.out.println("Request SOAP Message:");
        //soapMessage.writeTo(System.out);
        //System.out.println("\n");

        return soapMessage;
    }

    /**
     * @return the civilId
     */
    public String getCivilId() {
        return civilId;
    }

    /**
     * @param civilId the civilId to set
     */
    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    /**
     * @return the requestDataArray
     */
    public PostRequestStep1[] getRequestDataArray() {
        return requestDataArray;
    }

    /**
     * @param requestDataArray the requestDataArray to set
     */
    public void setRequestDataArray(PostRequestStep1[] requestDataArray) {
        this.requestDataArray = requestDataArray;
    }

    /**
     * @return the stepNo
     */
    public int getStepNo() {
        return stepNo;
    }

    /**
     * @param stepNo the stepNo to set
     */
    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the ResidentNatNumber
     */
    public String getResidentNatNumber() {
        return ResidentNatNumber;
    }

    /**
     * @param ResidentNatNumber the ResidentNatNumber to set
     */
    public void setResidentNatNumber(String ResidentNatNumber) {
        this.ResidentNatNumber = ResidentNatNumber;
    }

}
