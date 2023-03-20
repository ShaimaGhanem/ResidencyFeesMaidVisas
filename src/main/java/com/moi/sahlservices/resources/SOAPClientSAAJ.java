/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi.sahlservices.resources;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 *
 * @author MOI
 */
public class SOAPClientSAAJ {

    static final FileParameters fileParameters = new FileParameters();

    static {
        try {
            fileParameters.readParameters();
        } catch (IOException ex) {
            Logger.getLogger(DeportationFineServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the ticketPrice
     */
    public String getTicketPrice() {
        return ticketPrice;
    }

    /**
     * @param ticketPrice the ticketPrice to set
     */
    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * @return the ResidentCivilId
     */
    public String getDeporteeNumber() {
        return deporteeNumber;
    }

    public void setDeporteeNumber(String deporteeNumber) {
        this.deporteeNumber = deporteeNumber;
    }

    private String civilId = "";
    private String ResidentNatNumber = "";

    private String deporteeNumber = "";
    private PostRequestStep1[] requestDataArray = null;
    private int stepNo = 0;
    private String amount = "";
    private String ticketPrice = "";

    public void createSoapEnvelopeGet(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeGet--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "def";
        String myNamespaceURI = "http://www.moi.gov.kw/Services/MoiDeportationFineServices/V1/Definitions";

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
        SOAPElement soapBodyElem = soapBody.addChildElement("InquireDeportationListData", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sponsorType");
        soapBodyElem1.addTextNode("1");
        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sponsorNumber");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem2.addTextNode(civilId);
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

    public void createSoapEnvelopeInitPayment(SOAPMessage soapMessage) throws SOAPException, IOException {
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
        soapHeaderElem1.addAttribute(new QName(myNamespaceURI3, "Id", "wsu"), fileParameters.getInitpaymentToken());

        //System.out.println("-----------------------------10------------2-----------");
        SOAPElement soapHeaderElem2 = soapHeaderElem1.addChildElement("Username", myNamespace2);
        //System.out.println("-----------------------------10---------------3--------");
        soapHeaderElem2.addTextNode(fileParameters.getInitpaymentUsername());
        //System.out.println("-----------------------------10-----------------4------");
        SOAPElement soapHeaderElem3 = soapHeaderElem1.addChildElement("Password", myNamespace2);
        soapHeaderElem3.addAttribute(new QName("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
        //System.out.println("-----------------------------10---------------5--------");
        soapHeaderElem3.addTextNode(fileParameters.getInitpaymentPassword());
        SOAPElement soapHeaderElem4 = soapHeaderElem1.addChildElement("Nonce", myNamespace2);
        soapHeaderElem4.addAttribute(new QName("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");

        //System.out.println("-----------------------------10--------6---------------");
        soapHeaderElem4.addTextNode(fileParameters.getInitpaymentNonce());
        SOAPElement soapHeaderElem5 = soapHeaderElem1.addChildElement("Created", myNamespace3);
        //System.out.println("-----------------------------10------7-----------------");
        soapHeaderElem5.addTextNode("2022-06-05T10:55:44.261Z");
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
        soapBodyElem4.addTextNode(" ");
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
        soapBodyElem10.addTextNode("7");
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("paymentList");

        SOAPElement soapBodyElem12 = soapBodyElem11.addChildElement("number");
        //soapBodyElem12.addTextNode("677800000");
        soapBodyElem12.addTextNode(getDeporteeNumber());
        SOAPElement soapBodyElem13 = soapBodyElem11.addChildElement("year");
        Integer year = Calendar.getInstance().get(Calendar.YEAR);

        soapBodyElem13.addTextNode(year.toString());
        SOAPElement soapBodyElem14 = soapBodyElem11.addChildElement("amount");
        soapBodyElem14.addTextNode(getAmount());
        SOAPElement soapBodyElem15;
        SOAPElement soapBodyElem16;

        soapBodyElem15 = soapBodyElem11.addChildElement("descriptionEn");
        soapBodyElem15.addTextNode("Deportee Ticket");
        soapBodyElem16 = soapBodyElem11.addChildElement("descriptionAr");
        soapBodyElem16.addTextNode("دفع تذكرة مبعد");

        SOAPElement soapBodyElem17 = soapBodyElem11.addChildElement("field1");
        //soapBodyElem17.addTextNode("677800000");
        soapBodyElem17.addTextNode("1");

    }

    public void createSoapEnvelopeCheckPaymentStatus(SOAPMessage soapMessage) throws SOAPException, IOException {
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
        soapHeaderElem1.addAttribute(new QName(myNamespaceURI3, "Id", "wsu"), fileParameters.getCheckpaymentToken());

        //System.out.println("-----------------------------10------------2-----------");
        SOAPElement soapHeaderElem2 = soapHeaderElem1.addChildElement("Username", myNamespace2);
        //System.out.println("-----------------------------10---------------3--------");
        soapHeaderElem2.addTextNode(fileParameters.getCheckpaymentUsername());
        //System.out.println("-----------------------------10-----------------4------");
        SOAPElement soapHeaderElem3 = soapHeaderElem1.addChildElement("Password", myNamespace2);
        soapHeaderElem3.addAttribute(new QName("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
        //System.out.println("-----------------------------10---------------5--------");
        soapHeaderElem3.addTextNode(fileParameters.getCheckpaymentPassword());
        SOAPElement soapHeaderElem4 = soapHeaderElem1.addChildElement("Nonce", myNamespace2);
        soapHeaderElem4.addAttribute(new QName("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");

        //System.out.println("-----------------------------10--------6---------------");
        soapHeaderElem4.addTextNode(fileParameters.getCheckpaymentNonce());
        SOAPElement soapHeaderElem5 = soapHeaderElem1.addChildElement("Created", myNamespace3);
        //System.out.println("-----------------------------10------7-----------------");
        soapHeaderElem5.addTextNode("2022-06-05T11:02:16.101Z");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("SahelPayView", myNamespace);
        //System.out.println("-----------------------------12-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("CIVIL_ID");
        soapBodyElem1.addTextNode(civilId);

    }

    public SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String userId, int stepNo, PostRequestStep1[] requestDataArray) {
        SOAPMessage soapResponse = null;
        try {
            // Create SOAP Connection
            //System.out.println("---------beginning of sending soap request 1---------------");

            setCivilId(userId);
            if (stepNo == 4) {
                setResidentNatNumber(userId);
            }
            if (requestDataArray != null) {
                for (PostRequestStep1 requestDataArray1 : requestDataArray) {
                    if (requestDataArray1.getKey().equals("deporteeNumber")) {

                        setDeporteeNumber(requestDataArray1.getValue());
                        //setDeporteeCivilId("119175104111");//TODO

                    }
                    if (requestDataArray1.getKey().equals("TicketPrice")) {

                        setTicketPrice(requestDataArray1.getValue());
                    }
                    if (requestDataArray1.getKey().equals("Amount")) {

                        setAmount(requestDataArray1.getValue());
                    }

                }
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

                createSoapEnvelopeGet(soapMessage);
                break;
            case 3:

                createSoapEnvelopeCheckPaymentStatus(soapMessage);
                break;
            case 2:

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
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

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

    /* public String readParamValue(String paramName) throws IOException {
        String paramValue = "-1";
        //Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        //System.out.println("-----1------------" + path.toString());
        String filePath = "//shared//lzcollect//members//SharedResources//AppVariables//";
        //System.out.println("-----1----filePath--------" + filePath);

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(filePath + "DeportationFineService.json"));

        envParam[] envParamData = gson.fromJson(reader, envParam[].class);
        for (int i = 0; i < envParamData.length; i++) {
            //System.out.println("-----66------------" + envParamData[i].getKey());
            if (envParamData[i].getKey().equals(paramName)) {
                paramValue = envParamData[i].getValue();
                //System.out.println("-----77------------" + envParamData[i].getValue());
            }
        }
        return paramValue;
    }*/
}
