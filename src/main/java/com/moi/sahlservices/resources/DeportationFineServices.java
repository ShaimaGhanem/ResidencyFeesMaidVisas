/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.moi.sahlservices.resources;

import com.google.gson.Gson;
import java.io.BufferedReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@WebServlet("/")
public class DeportationFineServices extends HttpServlet {

    /**
     * @return the trackid
     */
    public String getTrackid() {
        return trackid;
    }

    /**
     * @param trackid the trackid to set
     */
    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    static final FileParameters fileParameters = new FileParameters();

    static {
        try {
            fileParameters.readParameters();
        } catch (IOException ex) {
            Logger.getLogger(DeportationFineServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the deporteeCivilId
     */
    public String getDeporteeNumber() {
        return deporteeNumber;
    }

    public void setDeporteeNumber(String deporteeNumber) {
        this.deporteeNumber = deporteeNumber;
    }

    private String paymentURL = "";
    private String feesType = "";
    private String period = "";
    private String amount = "";
    private String returnCode = "";
    private String deporteeNumber = "";
    private String  trackid = "";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

           /* String civilId = request.getParameter("civilId");

            Gson gson = new Gson();

            String soapEndpointUrl;
            String soapAction;
            SOAPClientSAAJ mSOAPClientSAAJ;
            SOAPMessage soapResponse;
            Iterator itr;

            //System.out.println("value of request data is : " + data);
            soapEndpointUrl = fileParameters.getIp() + ":" + fileParameters.getPort() + "/services/MoiDeportationFineServices";

            //soapEndpointUrl = "http://10.10.1.1:7837/services/MoiDeportationFineServices";
            //System.out.println("soapEndpointUrl : " + soapEndpointUrl);
            soapAction = "http://www.moi.gov.kw/Services/MoiDeportationFineServices/V1/inquireDeportationListData/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();
            //System.out.println("before calling callSoapWebService");

            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, 0, null);

            //System.out.println("after calling callSoapWebService");
            // System.out.println("  Response SOAP Message:");
            //soapResponse.writeTo(System.out);
            //System.out.println();
            itr = soapResponse.getSOAPBody().getChildElements();
            GetResponse oResTrue = new GetResponse();
            //System.out.println("  before forming the output:");

            oResTrue = ReadXMLDataGet(itr);

            //System.out.println("  after forming the output:");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            gson = new Gson();
            String jsonData = gson.toJson(oResTrue);
            PrintWriter out = response.getWriter();

            out.println(jsonData);*/

             String civilId;
            int stepNo = -1;
            //System.out.println("start");
            //System.out.println(request.getParameter("civilId"));

           
            civilId = request.getParameter("civilId");
            //System.out.println("after getting civilid");

            GetResponse oResTrue = new GetResponse();
            List<ControlsModel> controlsModel = new ArrayList<>();
            ControlsModel tempControlModel = new ControlsModel();

            tempControlModel.setId("CivilID");
            tempControlModel.setType("Text");
            tempControlModel.setLabelAr("الرقم المدني");
            tempControlModel.setLabelEn("CivilID");

            tempControlModel.setIsRequired(true);
            controlsModel.add(tempControlModel);

            TrueResult result = new TrueResult();
            result.setNameAr("دفع تذاكر المبعدين");
            result.setNameEn("Deportation Fine Services");

            result.setStepNo(0);
            result.setButtonLabelAr("متابعة");
            result.setButtonLabelEn("Next");
            result.setControlsModels(controlsModel);
            oResTrue.setResult(result);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Gson gson = new Gson();
            String jsonData = gson.toJson(oResTrue);
            PrintWriter out = response.getWriter();

            out.println(jsonData);
        } catch (IOException | DOMException e) {
            Logger.getLogger(DeportationFineServices.class.getName()).log(Level.SEVERE, null, e);

            System.err.println("\nError occurred while getting the Data!\n");

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //get required data from the parameters
            String civilId = request.getParameter("civilId");
            String stepNoS = request.getParameter("stepNo");
            if (stepNoS == null) {
                stepNoS = request.getParameter("StepNo");
            }
            //System.out.println("value of stepNo is:" + stepNoS);
            if ((civilId != null && civilId.length() > 0) && (stepNoS != null && stepNoS.length() > 0)) {
                int stepNo = Integer.valueOf(stepNoS);
                //System.out.println("value of request civiId is : " + civilId);
                //System.out.println("value of request stepNo is : " + stepNo);
                GetResponse oResTrue;
                //get the required data from the body
                Gson gson = new Gson();
                Gson gson2 = new Gson();
                String soapEndpointUrl;
                String soapAction;
                SOAPClientSAAJ mSOAPClientSAAJ;
                SOAPMessage soapResponse;
                Iterator itr;

                String data;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = request.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                data = builder.toString();
                //System.out.println("value of request data is : " + data);

                PostRequestStep1[] requestDataArray = gson.fromJson(data, PostRequestStep1[].class);
                for (PostRequestStep1 requestDataArray1 : requestDataArray) {
                    if (requestDataArray1.getKey().equals("deporteeNumber")) {
                        setDeporteeNumber(requestDataArray1.getValue());
                        //setDeporteeCivilId("119175104111");//TODO
                    }
                    if (requestDataArray1.getKey().equals("CivilID")) {
                        //System.out.println("getting the civil id");
                        civilId = requestDataArray1.getValue();

                    }
                    if (requestDataArray1.getKey().equals("Amount")) {
                        //System.out.println("getting the civil id");
                        amount = requestDataArray1.getValue();

                    }
                     if (requestDataArray1.getKey().equals("trackid")) {

                        setTrackid(requestDataArray1.getValue());
                    }

                }

                switch (stepNo) {

                    case 0:
                          
             gson = new Gson();

            

            //System.out.println("value of request data is : " + data);
            soapEndpointUrl = fileParameters.getIp() + ":" + fileParameters.getPort() + "/services/MoiDeportationFineServices";

            //soapEndpointUrl = "http://10.10.1.1:7837/services/MoiDeportationFineServices";
            //System.out.println("soapEndpointUrl : " + soapEndpointUrl);
            soapAction = "http://www.moi.gov.kw/Services/MoiDeportationFineServices/V1/inquireDeportationListData/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();
            //System.out.println("before calling callSoapWebService");

            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, 0, null);

            //System.out.println("after calling callSoapWebService");
            // System.out.println("  Response SOAP Message:");
            //soapResponse.writeTo(System.out);
            //System.out.println();
            itr = soapResponse.getSOAPBody().getChildElements();
             oResTrue = new GetResponse();
            //System.out.println("  before forming the output:");

            oResTrue = ReadXMLDataGet(itr);

            //System.out.println("  after forming the output:");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            gson = new Gson();
            String jsonData = gson.toJson(oResTrue);
            PrintWriter out = response.getWriter();

            out.println(jsonData);
                    case 1:
                        soapEndpointUrl = fileParameters.getIp() + ":" + fileParameters.getPort() + "/services/MoiDeportationFineServices";
                        //soapEndpointUrl = "http://10.10.1.1:7837/services/MoiDeportationFineServices";
                        soapAction = "http://www.moi.gov.kw/Services/MoiDeportationFineServices/V1/inquireDeportationListData/";
                        mSOAPClientSAAJ = new SOAPClientSAAJ();
                        // System.out.println("before calling callSoapWebService");
                        soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, stepNo, requestDataArray);
                        // System.out.println("after calling callSoapWebService");
                        // System.out.println("  Response SOAP Message:");
                        //soapResponse.writeTo(System.out);
                        // System.out.println();

                        itr = soapResponse.getSOAPBody().getChildElements();

                        //System.out.println("  before forming the output:");
                        oResTrue = ReadXMLData(itr);
                        //System.out.println("  after forming the output:");
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        gson = new Gson();
                         jsonData = gson.toJson(oResTrue);
                         out = response.getWriter();

                        out.println(jsonData);

                        break;
                    case 2: //Step Number 1
                       /* soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getInitPaymentPort() + "/knet/services/PayGateService";

                        // soapEndpointUrl = "https://iservices.moi.gov.kw:5299/knet/services/PayGateService";
                        soapAction = "http://www.moi.gov.kw/Services/PayGateService/V1/initiatePayment";
                        mSOAPClientSAAJ = new SOAPClientSAAJ();
                        //System.out.println("before calling callSoapWebService init payment");
                        mSOAPClientSAAJ.setAmount(getAmount());
                        soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, 2, requestDataArray);
                        //System.out.println("after calling callSoapWebService");
                        //System.out.println("Response SOAP Message:");
                        //soapResponse.writeTo(System.out);
                        //System.out.println();
                        itr = soapResponse.getSOAPBody().getChildElements();
                        String valueNeeded = CheckXMLPaymentData(itr);*/
                        
                        //////////////////////add initiate of the new payment////////////////////////////////////////
                        
                        CloseableHttpClient client = HttpClientBuilder.create().build();
                        String responseJSON = "";
                        
                        InitiatePaymentResponse initiatePaymentResponse = new InitiatePaymentResponse();
                        HttpPost httpPost = new HttpPost("http://10.10.1.100:9080/knet/payment/initiate");
                        //HttpPost httpPost = new HttpPost(fileParameters.getDiyarServicesUrl() + "Authentication/");
                        
                        /*
                        {
    "paymentRequest": {
        "applicationId": "1",
        "gatewayLanguage": "EN",
        "userCivilId": "2650104001122",
        "payForCivilId": "275062301151",
        "serviceTypeId": "1",
        "totalAmount": 25
                        ,
    "itemList": [
        {
            "itemNumber": "123456789012345678",
            "amount": 5,
            "itemDetails": {
                "key1": "value1",
                "key2": "value2",
                "key3": "value3",
                "key4": "value4",
                "key5": "value5",
                "key6": "value6",
                "key7": "value7",
                "key8": "value8",
                "key9": "value9",
                "key10": "value10"
            }
        }
      
    }*/
                       String payForCivilId = getDeporteeNumber();
                       
System.out.println("-------------7---------amount is -----" + getAmount());
                        
                        String json = "{\"paymentRequest\":{   \"applicationId\": " + "\"" + "1" + "\"," +
                                "\"gatewayLanguage\": " + "\"" + "AR" + "\"," +
                                "\"userCivilId\": " + "\"" + civilId + "\"," +
                                "\"payForCivilId\": " + "\"" + payForCivilId + "\"," +
                                "\"serviceTypeId\": " + "\"" + "7" + "\"," +
                                "\"totalAmount\": " + "\"" + getAmount() + "\"" +
                                "},"+
                                
                                "\"itemList\":[{   \"itemNumber\": " + "\"" + payForCivilId + "\"," +
                                "\"amount\": " + "\"" + getAmount() + "\"," +
                                "\"itemDetails\":{ " +
                                "\"key1\": " + "\"" + "1" + "\""  +
                                "}}]}";
                                
                                
                                

                        System.out.println("-------------7---------json-----" + json);
                        StringEntity entity = new StringEntity(json);
                        httpPost.setEntity(entity);
                        System.out.println("-------------8--------------");
                        // set your POST request headers to accept json contents
                        httpPost.setHeader("Accept", "application/json");
                        httpPost.setHeader("Content-type", "application/json");
                        System.out.println("-------------8.5--------------");

                        //System.out.println("-------------8.8--------------");
                        // your closeablehttp response
                        CloseableHttpResponse response2 = client.execute(httpPost);
                        System.out.println("------------9--------------");
                        // print your status code from the response
                        System.out.println("------------9--------------:"+response2.getStatusLine().getStatusCode());
                        //System.out.println("-------------10--------------");
                        // take the response body as a json formatted string 
                        responseJSON = EntityUtils.toString(response2.getEntity());
                        System.out.println("--------------------------2.1----------------------------------" + responseJSON);
                        gson = new Gson();
                        //System.out.println("-------------11--------------");
                        // convert/parse the json formatted string to a json object
                        initiatePaymentResponse = gson.fromJson(responseJSON, InitiatePaymentResponse.class);
                        System.out.println("-------------11--------initiatePaymentResponse.getPaymentUrl()------"+initiatePaymentResponse.getPaymentUrl());
                        String valueNeeded = initiatePaymentResponse.getPaymentUrl();
                        ///////////////////////////////////////////////////////////////////////////////////////////

                        setPaymentURL(valueNeeded);
                        oResTrue = new GetResponse();

                        oResTrue.setIsSuccess(true);
                        oResTrue.setMessage("");
                        oResTrue.setMessageEn("");
                        TrueResult tempResult = new TrueResult();
                        tempResult.setNameAr("دفع غرامة المبعدين");
                        tempResult.setNameEn("Pay Deportation Fines");
                        tempResult.setButtonLabelAr("متابعه");
                        tempResult.setButtonLabelEn("Continue");
                        tempResult.setStepNo(3);
                        ControlsModel tempControlModel = null;
                        List<ControlsModel> tempControlModels = new ArrayList<ControlsModel>();
                        tempControlModel = new ControlsModel();
                        tempControlModel.setId("DeportationFine");
                        tempControlModel.setType("PaymentView");
                        tempControlModel.setDefaultValue(getPaymentURL());
                        tempControlModel.setIsRequired(false);
                        tempControlModels.add(tempControlModel);

                        tempControlModel = new ControlsModel();
                        tempControlModel.setId("field1");
                        tempControlModel.setType("Hidden");
                        tempControlModel.setDefaultValue("1");
                        tempControlModel.setIsRequired(false);
                        tempControlModels.add(tempControlModel);
                        
                          tempControlModel = new ControlsModel();

                                tempControlModel.setId("trackid");
                                tempControlModel.setType("Hidden");
                                tempControlModel.setDefaultValue(initiatePaymentResponse.getTrackId());
                                tempControlModel.setIsRequired(false);
                        tempControlModels.add(tempControlModel);
                                
                        tempResult.setControlsModels(tempControlModels);

                        oResTrue.setResult(tempResult);

                        /*   } else {
                             List<String> wantedErrorMessages = new ArrayList<>();
                             wantedErrorMessages = getErrorMessages(oResTrue.getMessageEn());
                            
                            oResTrue.setIsSuccess(false);
                            oResTrue.setMessage(wantedErrorMessages.get(0));
                            oResTrue.setMessageEn(wantedErrorMessages.get(1));
                            oResTrue.setResult(null);

                        }*/
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        jsonData = gson2.toJson(oResTrue);
                        out = response.getWriter();

                        out.println(jsonData);

                        break;
                    case 3://Step Number 2
                        /*soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getCheckPaymentPort() + "/SahelPayViewTest/services/SahelPayViewService";

                        // soapEndpointUrl = "https://iservices.moi.gov.kw:5302/SahelPayViewTest/services/SahelPayViewService";
                        soapAction = "urn:example/SahelPayView";
                        mSOAPClientSAAJ = new SOAPClientSAAJ();
                        //System.out.println("before calling callSoapWebService     case2");
                        soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, stepNo, requestDataArray);
                        //System.out.println("after calling callSoapWebService");
                        //System.out.println("Response SOAP Message:");
                        //soapResponse.writeTo(System.out);
                        //System.out.println();
                        itr = soapResponse.getSOAPBody().getChildElements();
                        //System.out.println("10101010101001");
                        valueNeeded = CheckXMLPaymentData(itr);*/
                        //System.out.println("23232323323");
                        /////////////////////////////////////////////////////////////////////////////////////
                         /////////////////////////////////using new payment///////////////////////////////////////////////
                         client = HttpClientBuilder.create().build();
                         responseJSON = "";
                         
                         oResTrue = new GetResponse();
                          initiatePaymentResponse = new InitiatePaymentResponse();
                          httpPost = new HttpPost("http://10.10.1.100:9080/knet/payment/checkPaymentStatus");
                           json = "{\"trackid\":  " + "\"" + getTrackid() + "\"}";

                        System.out.println("-------------7---------json-----" + json);
                         entity = new StringEntity(json);
                        httpPost.setEntity(entity);
                        System.out.println("-------------8--------------");
                        // set your POST request headers to accept json contents
                        httpPost.setHeader("Accept", "application/json");
                        httpPost.setHeader("Content-type", "application/json");
                        System.out.println("-------------8.5--------------");

                        //System.out.println("-------------8.8--------------");
                        // your closeablehttp response
                          response2 = client.execute(httpPost);
                        System.out.println("------------9--------------");
                        // print your status code from the response
                        System.out.println("------------9--------------:"+response2.getStatusLine().getStatusCode());
                        //System.out.println("-------------10--------------");
                        // take the response body as a json formatted string 
                        responseJSON = EntityUtils.toString(response2.getEntity());
                        System.out.println("--------------------------2.1----------------------------------" + responseJSON);
                        gson = new Gson();
                        //System.out.println("-------------11--------------");
                        // convert/parse the json formatted string to a json object
                        //initiatePaymentResponse = gson.fromJson(responseJSON, InitiatePaymentResponse.class);
                        //System.out.println("-------------11--------initiatePaymentResponse.getPaymentUrl()------"+initiatePaymentResponse.getPaymentUrl());
                         valueNeeded = responseJSON;
                        oResTrue = new GetResponse();
                        // String valueNeeded = "1";

                        if (valueNeeded.equals("1"))// payment success
                        {
                            /* soapEndpointUrl = "http://10.10.1.1:29084/RE004/PERSON_DETAILS_DISPLAY";
                                //soapEndpointUrl = "http://10.11.78.103:9080/RE004/PERSON_DETAILS_DISPLAY";
                                soapAction = "http://tempuri.org/PersonDetailsDisplaycall/";
                                mSOAPClientSAAJ = new SOAPClientSAAJ();
                                //System.out.println("before getting personal data");
                                soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, 4, null);
                                //System.out.println("after getting personal data");
                                //System.out.println("Response SOAP Message:");
                                //soapResponse.writeTo(System.out);
                                //System.out.println();

                                itr = soapResponse.getSOAPBody().getChildElements();
                                String[] personName = GetPersonName(itr);*/

                            oResTrue.setIsSuccess(true);
                            oResTrue.setMessage("لقد تم تحصيل المبلغ بنجاح   ");//+ personName[1]);
                            oResTrue.setMessageEn("Fees Collected Successfully ");// + personName[0]);
                            oResTrue.setAlertType("Success");
                            //TrueResult temp = new TrueResult();
                            // temp.setStepNo(3);
                            oResTrue.setResult(null);
                        } else {//payment failed
                            oResTrue.setIsSuccess(true);
                            //oResTrue.setMessage("نظرا لحدوث خطأ أثناء عملية الدفع\nيرجى معاودة المحاولة من جديد");
                            // oResTrue.setMessage("يرجى معاودة المحاولة من جديد نظرالحدوث خطأ في عملية الدفع" );
                            oResTrue.setMessage("لم يتم تنفيذ الخدمة\nنظرا لحدوث خطأ أثناء عملية الدفع\nيرجى معاودة المحاولة من جديد");
                            oResTrue.setMessageEn("We could not complete the service\n Due to an error occured during the payment process\nPlease try again");
                            oResTrue.setAlertType("error");
                            // TrueResult temp = new TrueResult();
                            //temp.setStepNo(3);
                            oResTrue.setResult(null);
                        }

                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        jsonData = gson.toJson(oResTrue);
                        out = response.getWriter();
                        out.println(jsonData);
                        /*  } else {
                            response.setContentType("application/json");
                            response.setCharacterEncoding("utf-8");

                            jsonData = "Data Enter in the body is not correct ,  need :ResidentNatNumber";
                            out = response.getWriter();

                            out.println(jsonData);
                        }*/

                        break;
                    default:
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        jsonData = "The Step No you entered is not supported";
                        out = response.getWriter();
                        out.println(jsonData);
                        break;

                    //handle wrong stepno value
                    }
            } else {

                System.err.println("\nCivil ID  and Step Number is required as a parameter\n");
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                String jsonData = "Civil ID and Step Number is required as a parameter";
                PrintWriter out = response.getWriter();

                out.println(jsonData);

            }

        } catch (SOAPException ex) {
            Logger.getLogger(DeportationFineServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GetResponse ReadXMLDataGet(Iterator itr) throws SOAPException, IOException {

        String statusCode = "";
        String messageAr = "";
        String messageEn = "";
        GetResponse oResTrue = new GetResponse();
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        //System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("NS2:InquireDeportationListDataResponse")) {
            NodeList childNodes = node.getChildNodes();
            int numberOfChilds = childNodes.getLength();
            //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
            for (int child = 0; child < numberOfChilds; child++) {
                //System.out.println("---4---- :: child name  :" + child + " is " + childNodes.item(child).getNodeName());
                switch (childNodes.item(child).getNodeName()) {
                    case "statusCode":
                        statusCode = childNodes.item(child).getTextContent();
                        //System.out.println("---6---- :: value of success is " + statusCode);
                        break;

                    default:
                        break;
                }
            }

            if (!statusCode.equals("1")) {

                oResTrue.setIsSuccess(false);
                oResTrue.setMessage("لا يوجد تذاكر مبعدين لهذا الكفيل");
                oResTrue.setMessageEn("No Active Tickets for Sponsor");
                oResTrue.setResult(null);

                return oResTrue;

            } else {// isSuccess is true

                String soapEndpointUrl;
                String soapAction;
                SOAPClientSAAJ mSOAPClientSAAJ;
                SOAPMessage soapResponse;

                oResTrue.setIsSuccess(true);
                oResTrue.setMessage("");
                oResTrue.setMessageEn("");
                TrueResult tempResult = new TrueResult();
                tempResult.setNameAr("دفع تذاكر المبعدين");
                tempResult.setNameEn("Deportation Fine Service");
                tempResult.setStepNo(1);
                tempResult.setButtonLabelAr("عرض التفاصيل");
                tempResult.setButtonLabelEn("Details");

                int numberOfArrayRows;
                List<ControlsModel> tempControlModelList = new ArrayList<>();
                ControlsModel temControlsModel = new ControlsModel();
                temControlsModel.setId("deporteeNumber");
                temControlsModel.setIsRequired(true);
                temControlsModel.setLabelAr("اختر المبعد");
                temControlsModel.setLabelEn("Select the deportee");
                temControlsModel.setType("Dropdown");
                OptionControlModel tempOptionControlModel = new OptionControlModel();
                List<OptionControlModel> mOptionControlModelList = new ArrayList<>();
                //TODO : if list not contain the requestor so add it 
                /* tempOptionControlModel = new OptionControlModel();
                 tempOptionControlModel.setTextAr(period);
                 tempOptionControlModel.setTextEn(period);
                 tempOptionControlModel.setValue(civilId);
                 mOptionControlModelList.add(tempOptionControlModel);*/
                for (int child2 = 0; child2 < numberOfChilds; child2++) {
                    //System.out.println("---7---- :: child name  :" + child2 + " is " + childNodes.item(child2).getNodeName());
                    NodeList childNodes4 = childNodes.item(child2).getChildNodes();
                    numberOfArrayRows = childNodes4.getLength();//we have 3 rows in the array

                    //System.out.println("---8---- :: number of numberOfChilds4:" + numberOfArrayRows + "value to be switched " + childNodes.item(child2).getNodeName());
                    // ControlsModel[] tempControlModel = new ControlsModel[numberOfArrayRows];
                    switch (childNodes.item(child2).getNodeName()) {
                        case "inquireDeportationListData":
                            tempOptionControlModel = new OptionControlModel();
                            NodeList resultChildNodes = childNodes.item(child2).getChildNodes();
                            int numberOfResultChildren = resultChildNodes.getLength();
                            //System.out.println("---7---- :: number of childs:" + numberOfResultChildren);

                            for (int resultChild = 0; resultChild < numberOfResultChildren; resultChild++) {

                                switch (resultChildNodes.item(resultChild).getNodeName()) {
                                    case "personName":

                                        ArabicShaping as = new ArabicShaping(10);
                                        try {
                                            tempOptionControlModel.setTextAr(as.shape(resultChildNodes.item(resultChild).getTextContent().trim()));
                                        } catch (ArabicShapingException e) {
                                            e.printStackTrace();
                                        }

                                        //System.out.println("---789---- :: value of NameAr is " + tempResult.getNameAr());
                                        break;

                                    case "personNumber":
                                        tempOptionControlModel.setValue(resultChildNodes.item(resultChild).getTextContent().trim());

                                        //System.out.println("---789---- :: value of NameEn is " + tempResult.getNameEn());
                                        break;

                                    default:
                                        break;
                                }
                                //System.out.println("---789---- :: value of tempOptionControlModel.getValue() is " + tempOptionControlModel.getValue());
                                soapEndpointUrl = fileParameters.getPersonDisplayIp() + ":" + fileParameters.getPersonDisplayPort() + "/RE004/PERSON_DETAILS_DISPLAY";

                                //soapEndpointUrl = "http://10.10.1.1:29084/RE004/PERSON_DETAILS_DISPLAY";
                                soapAction = "http://tempuri.org/PersonDetailsDisplaycall/";
                                mSOAPClientSAAJ = new SOAPClientSAAJ();
                                //System.out.println("before getting personal data");
                                PostRequestStep1[] requestDataArray = new PostRequestStep1[1];
                                requestDataArray[0] = new PostRequestStep1();
                                requestDataArray[0].setKey("civilId");
                                requestDataArray[0].setType("text");
                                requestDataArray[0].setValue(tempOptionControlModel.getValue());

                                soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, tempOptionControlModel.getValue(), 4, requestDataArray);
                                //System.out.println("after getting personal data");
                                //System.out.println("Response SOAP Message:");
                                //soapResponse.writeTo(System.out);
                                //System.out.println();

                                itr = soapResponse.getSOAPBody().getChildElements();
                                String[] personName = GetPersonName(itr);
                                tempOptionControlModel.setTextEn(personName[0]);

                            }
                            mOptionControlModelList.add(tempOptionControlModel);

                            break;

                        default:
                            break;
                    }//switch

                    temControlsModel.setOptionControlModels(mOptionControlModelList);
                }//for

                tempControlModelList.add(temControlsModel);

                tempResult.setControlsModels(tempControlModelList);
                oResTrue.setResult(tempResult);
            }
        }
        return oResTrue;
    }

    public GetResponse ReadXMLData(Iterator itr) {

        String statusCode = "";
        String messageAr = "";
        String messageEn = "";
        GetResponse oResTrue = new GetResponse();
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        //System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("NS2:InquireDeportationListDataResponse")) {
            NodeList childNodes = node.getChildNodes();
            int numberOfChilds = childNodes.getLength();
            //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
            for (int child = 0; child < numberOfChilds; child++) {
                //System.out.println("---4---- :: child name  :" + child + " is " + childNodes.item(child).getNodeName());
                switch (childNodes.item(child).getNodeName()) {
                    case "statusCode":
                        statusCode = childNodes.item(child).getTextContent();
                        //System.out.println("---6---- :: value of success is " + statusCode);
                        break;

                    default:
                        break;
                }
            }

            if (!statusCode.equals("1")) {

                oResTrue.setIsSuccess(false);
                oResTrue.setMessage("ليس لديك غرامة تذاكر مبعدين");
                oResTrue.setMessageEn("you don't have Deportation Fines");
                oResTrue.setResult(null);

                return oResTrue;

            } else {// isSuccess is true

                oResTrue.setIsSuccess(true);
                oResTrue.setMessage("");
                oResTrue.setMessageEn("");
                TrueResult tempResult = new TrueResult();
                tempResult.setNameAr("تفاصيل المبعد");
                tempResult.setNameEn("Deportee Details");
                tempResult.setStepNo(2);
                tempResult.setButtonLabelAr("دفع");
                tempResult.setButtonLabelEn("Pay");

                List<ControlsModel> tempControlModelList = new ArrayList<>();
                ControlsModel temControlsModel = new ControlsModel();

                String Amount = "";
                String personNumber = "";
                ControlsModel temControlsModel1 = new ControlsModel();
                ControlsModel temControlsModel2 = new ControlsModel();
                ControlsModel temControlsModel3 = new ControlsModel();
                ControlsModel temControlsModel4 = new ControlsModel();
                ControlsModel temControlsModel5 = new ControlsModel();
                for (int child2 = 0; child2 < numberOfChilds; child2++) {
                    //System.out.println("---7---- :: child name  :" + child2 + " is " + childNodes.item(child2).getNodeName());
                    NodeList childNodes4 = childNodes.item(child2).getChildNodes();
                    int numberOfArrayRows = childNodes4.getLength();//we have 3 rows in the array

                    //System.out.println("---8---- :: number of numberOfChilds4:" + numberOfArrayRows + "value to be switched " + childNodes.item(child2).getNodeName());
                    // ControlsModel[] tempControlModel = new ControlsModel[numberOfArrayRows];
                    switch (childNodes.item(child2).getNodeName()) {
                        case "inquireDeportationListData":

                            NodeList resultChildNodes = childNodes.item(child2).getChildNodes();
                            int numberOfResultChildren = resultChildNodes.getLength();
                            //System.out.println("---7---- :: number of childs:" + numberOfResultChildren);

                            for (int resultChild = 0; resultChild < numberOfResultChildren; resultChild++) {

                                switch (resultChildNodes.item(resultChild).getNodeName()) {
                                    case "personName":
                                        temControlsModel1 = new ControlsModel();
                                        temControlsModel1.setLabelAr("اسم المبعد");
                                        temControlsModel1.setLabelEn("Deportee Name");
                                        temControlsModel1.setId("DeporteeName");
                                        temControlsModel1.setType("Text");
                                        ArabicShaping as = new ArabicShaping(10);
                                        try {
                                            temControlsModel1.setDefaultValue(as.shape(resultChildNodes.item(resultChild).getTextContent().trim()));
                                        } catch (ArabicShapingException e) {
                                            e.printStackTrace();
                                        }

                                        temControlsModel1.setIsRequired(false);
                                        temControlsModel1.setIsDisabled(true);

                                        break;

                                    case "personNumber":
                                        temControlsModel2 = new ControlsModel();
                                        temControlsModel2.setLabelAr("الرقم المدني للمبعد");
                                        temControlsModel2.setLabelEn("Deportee ID");
                                        temControlsModel2.setId("DeporteeID");
                                        temControlsModel2.setType("Text");
                                        temControlsModel2.setDefaultValue(resultChildNodes.item(resultChild).getTextContent().trim());
                                        temControlsModel2.setIsRequired(false);
                                        temControlsModel2.setIsDisabled(true);

                                        personNumber = resultChildNodes.item(resultChild).getTextContent().trim();
                                        break;

                                    case "personSex":
                                        temControlsModel3 = new ControlsModel();
                                        temControlsModel3.setLabelAr("نوع المبعد");
                                        temControlsModel3.setLabelEn("Deportee Sex");
                                        temControlsModel3.setId("DeporteeSex");
                                        temControlsModel3.setType("Text");
                                        as = new ArabicShaping(10);
                                        try {
                                            temControlsModel3.setDefaultValue(as.shape(resultChildNodes.item(resultChild).getTextContent().trim()));
                                        } catch (ArabicShapingException e) {
                                            e.printStackTrace();
                                        }

                                        temControlsModel3.setIsRequired(false);
                                        temControlsModel3.setIsDisabled(true);

                                        break;
                                    case "personNationality":
                                        temControlsModel4 = new ControlsModel();
                                        temControlsModel4.setLabelAr("جنسية المبعد");
                                        temControlsModel4.setLabelEn("Deportee Nationality");
                                        temControlsModel4.setId("DeporteeNationality");
                                        temControlsModel4.setType("Text");
                                        as = new ArabicShaping(10);
                                        try {
                                            temControlsModel4.setDefaultValue(as.shape(resultChildNodes.item(resultChild).getTextContent().trim()));
                                        } catch (ArabicShapingException e) {
                                            e.printStackTrace();
                                        }

                                        temControlsModel4.setIsRequired(false);
                                        temControlsModel4.setIsDisabled(true);

                                        break;
                                    case "ticketPrice":
                                        temControlsModel5 = new ControlsModel();
                                        temControlsModel5.setLabelAr("سعر التذكرة");
                                        temControlsModel5.setLabelEn("Ticket Price");
                                        temControlsModel5.setId("TicketPrice");
                                        temControlsModel5.setType("Text");
                                        temControlsModel5.setDefaultValue(resultChildNodes.item(resultChild).getTextContent().trim());
                                        temControlsModel5.setIsRequired(false);
                                        temControlsModel5.setIsDisabled(true);

                                        break;

                                    default:
                                        break;
                                }

                            }
                            //System.out.println("------- :: personNumber:" + personNumber);
                            //System.out.println("------- :: getDeporteeCivilId:" + getDeporteeNumber());
                            if (personNumber.equals(getDeporteeNumber())) {
                                Amount = temControlsModel5.getDefaultValue();
                                tempControlModelList.add(temControlsModel1);
                                tempControlModelList.add(temControlsModel2);
                                tempControlModelList.add(temControlsModel3);
                                tempControlModelList.add(temControlsModel4);
                                tempControlModelList.add(temControlsModel5);
                            }

                            break;

                        default:
                            break;
                    }//switch

                }//for

                temControlsModel = new ControlsModel();
                temControlsModel.setId("Amount");
                temControlsModel.setType("Hidden");
                temControlsModel.setDefaultValue(Amount);
                temControlsModel.setIsRequired(false);
                tempControlModelList.add(temControlsModel);

                tempResult.setControlsModels(tempControlModelList);
                oResTrue.setResult(tempResult);
            }
        }
        return oResTrue;
    }

    public String CheckXMLPaymentData(Iterator itr) {

        String valueNeeded = "";
        String paymentId = "";
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        //System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("InitiatePaymentResponse") || FirstNodeName.equals("ns1:SahelPayViewResponse")) {
            NodeList childNodes = node.getChildNodes();
            int numberOfChilds = childNodes.getLength();
            //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
            for (int child = 0; child < numberOfChilds; child++) {
                //System.out.println("---4---- :: child name  :" + child + " is " + childNodes.item(child).getNodeName());
                if (childNodes.item(child).getNodeName().equals("SERVICE_PROVIDE_FLAG") || childNodes.item(child).getNodeName().equals("baseUrl")) {
                    valueNeeded = childNodes.item(child).getTextContent();

                }
                if (childNodes.item(child).getNodeName().equals("params")) {
                    NodeList paramschildNodes = childNodes.item(child).getChildNodes();
                    int paramsnumberOfChilds = paramschildNodes.getLength();
                    //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                    for (int paramChild = 0; paramChild < paramsnumberOfChilds; paramChild++) {
                        //System.out.println("---4---- :: child name  :" + child + " is " + paramschildNodes.item(paramChild).getNodeName());
                        if (paramschildNodes.item(paramChild).getNodeName().equals("paramValue")) {
                            paymentId = paramschildNodes.item(paramChild).getTextContent();
                            valueNeeded = valueNeeded + "PaymentID=" + paymentId;

                        }
                    }
                }
            }
        }
        return valueNeeded;
    }

    public String[] GetPersonName(Iterator itr) {

        String firstNameEn = "";
        String secondNameEn = "";
        String thirdNameEn = "";
        String familyNameEn = "";
        String firstNameAr = "";
        String secondNameAr = "";
        String thirdNameAr = "";
        String familyNameAr = "";
        String nationalityCode = "";
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        //System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("ns2:PersonDetailsDisplaycallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            //System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("PersonDetailsDisplayExport")) {
                Node node3 = node2.getFirstChild();
                String ThirdNodeName = node3.getNodeName();
                //System.out.println("---2---- :: node name:" + SecondNodeName);
                if (ThirdNodeName.equals("Response")) {
                    NodeList childNodes = node3.getChildNodes();
                    int numberOfChilds = childNodes.getLength();
                    //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                    for (int child = 0; child < numberOfChilds; child++) {
                        //System.out.println("---4---- :: child name  :" + child + " is " + childNodes.item(child).getNodeName());
                        switch (childNodes.item(child).getNodeName()) {
                            case "EnglishFirstName":
                                firstNameEn = childNodes.item(child).getTextContent().trim();
                                break;
                            case "EnglishSecondName":
                                secondNameEn = childNodes.item(child).getTextContent().trim();
                                break;
                            case "EnglishThirdName":
                                thirdNameEn = childNodes.item(child).getTextContent().trim();
                                break;
                            case "EnglishFamilyName":
                                familyNameEn = childNodes.item(child).getTextContent().trim();
                                break;
                            case "ArabicFirstName":
                                firstNameAr = childNodes.item(child).getTextContent().trim();
                                break;
                            case "ArabicSecondName":
                                secondNameAr = childNodes.item(child).getTextContent().trim();
                                break;
                            case "ArabicThirdName":
                                thirdNameAr = childNodes.item(child).getTextContent().trim();
                                break;
                            case "ArabicFamilyName":
                                familyNameAr = childNodes.item(child).getTextContent().trim();
                                break;
                            case "NationalityCode":
                                nationalityCode = childNodes.item(child).getTextContent().trim();
                                break;
                            default:
                                break;
                        }

                    }
                }
            }
        }
        String personName[] = new String[2];
        if (nationalityCode.equals("1")) {
            personName[0] = firstNameEn + " " + familyNameEn;
        } else {
            String firstNameFlipped = "";
            String familyNameFlipped = "";
            StringBuilder sb = new StringBuilder();
            sb.append(firstNameEn);
            sb.reverse();
            firstNameFlipped = sb.toString();
            sb = new StringBuilder();
            sb.append(familyNameEn);
            sb.reverse();
            familyNameFlipped = sb.toString();

            personName[0] = firstNameFlipped + " " + familyNameFlipped;

        }

        personName[1] = firstNameAr + " " + secondNameAr + " " + thirdNameAr + " " + familyNameAr;

        return personName;
    }

    /**
     * @return the paymentURL
     */
    public String getPaymentURL() {
        return paymentURL;
    }

    /**
     * @param paymentURL the paymentURL to set
     */
    public void setPaymentURL(String paymentURL) {
        this.paymentURL = paymentURL;
    }

    /**
     * @return the feesType
     */
    public String getFeesType() {
        return feesType;
    }

    /**
     * @param feesType the feesType to set
     */
    public void setFeesType(String feesType) {
        this.feesType = feesType;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
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
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /*  public String readParamValue(String paramName) throws IOException {
        String paramValue = "-1";
        // Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        // String filePath = path.toString() + "//AppVariables//";
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
