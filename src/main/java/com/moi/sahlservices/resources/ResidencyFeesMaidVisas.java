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

@WebServlet("/")
public class ResidencyFeesMaidVisas extends HttpServlet {

    private String paymentURL = "";
    private String feesType = "";
    private String period = "";
    private String amount = "";
    private String returnCode = "";
    private String residentCivilId = "";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String civilId;
            int stepNo = -1;
            //System.out.println("start");
            //System.out.println(request.getParameter("civilId"));

            /*SG: get the civil id from the request*/
            civilId = request.getParameter("civilId");
            //System.out.println("after getting civilid");
            if (civilId != null && civilId.length() > 0) {
                /*SG: call the SOAP webservice */
                String soapEndpointUrl = "http://10.10.1.1:29084/RE002/RESIDENCY_FEES_MAID_VISAS_SHL";
                //String soapEndpointUrl = "http://10.11.78.103:9080/RE002/RESIDENCY_FEES_MAID_VISAS_SHL";
                String soapAction = "http://tempuri.org/ResidencyFeesMaidVisasShlcall/";
                SOAPClientSAAJ mSOAPClientSAAJ = new SOAPClientSAAJ();
                SOAPMessage soapResponse;
                //System.out.println("before calling callSoapWebService");
                soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, stepNo, null);
                //System.out.println("after calling callSoapWebService");
                //System.out.println("  Response SOAP Message:");
                //soapResponse.writeTo(System.out);
                //System.out.println();
                
               /* String faultCode = soapResponse.getSOAPPart().getEnvelope().getBody().getFault().getFaultCode();
                System.out.println("  value of falut code is :"+faultCode);
                if(faultCode.equals("OK"))
                {*/
                Iterator itr = soapResponse.getSOAPBody().getChildElements();
                GetResponse oResTrue;
                oResTrue = ReadXMLData(itr);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                Gson gson = new Gson();
                String jsonData = gson.toJson(oResTrue);
                PrintWriter out = response.getWriter();

                out.println(jsonData);
               /* }
                else
                {
                   
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                Gson gson = new Gson();
                String jsonData = "Error while calling the web service  : "+ soapResponse.getSOAPPart().getEnvelope().getBody().addFault();
                PrintWriter out = response.getWriter();

                out.println(jsonData);
                }*/
            } else {
                System.err.println("\nCivil ID is required as a parameter\n");
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                String jsonData = "Civil ID is required as a parameter";
                PrintWriter out = response.getWriter();

                out.println(jsonData);

            }

        } catch (SOAPException ex) {

            Logger.getLogger(ResidencyFeesMaidVisas.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");

        } catch (IOException | DOMException e) {
            Logger.getLogger(ResidencyFeesMaidVisas.class.getName()).log(Level.SEVERE, null, e);

            System.err.println("\nError occurred while getting the Data!\n");

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //get required data from the parameters
            String civilId = request.getParameter("civilId");
            String stepNoS = request.getParameter("stepNo");
            if(stepNoS == null)
            {
                 stepNoS = request.getParameter("StepNo");
            }
            //System.out.println("value of stepNo is:" + stepNoS);
            if ((civilId != null && civilId.length() > 0) && (stepNoS != null && stepNoS.length() > 0)) {
                int stepNo = Integer.valueOf(stepNoS);
               //System.out.println("value of request civiId is : " + civilId);
                //System.out.println("value of request stepNo is : " + stepNo);

                //get the required data from the body
                Gson gson = new Gson();
                Gson gson2 = new Gson();
                String soapEndpointUrl ;
                String soapAction ;
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
               /* for (PostRequestStep1 requestDataArray1 : requestDataArray) {
                    System.out.println("value of requestDataArray1.getKey() is : " + requestDataArray1.getKey());
                    System.out.println("value of requestDataArray1.getValue() is : " + requestDataArray1.getValue());
                    System.out.println("value of requestDataArray1.getType() is : " + requestDataArray1.getType());
                }*/

                switch (stepNo) {
                    case 1: //Step Number 1
                        //use the data to call SOAP Display to get the required output
                        /*SG: call the SOAP webservice */
                        if (requestDataArray.length >= 3) {
                            soapEndpointUrl = "http://10.10.1.1:29084/RE002/RESIDENCY_FEES_DISPLAY_SHL";
                            //soapEndpointUrl = "http://10.11.78.103:9080/RE002/RESIDENCY_FEES_DISPLAY_SHL";
                            soapAction = "http://tempuri.org/ResidencyFeesDisplayShlcall/";
                            mSOAPClientSAAJ = new SOAPClientSAAJ();

                            //System.out.println("before calling callSoapWebService");
                            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, stepNo, requestDataArray);
                            //System.out.println("after calling callSoapWebService");
                            //System.out.println(" Response SOAP Message:");
                            //soapResponse.writeTo(System.out);
                            //System.out.println();

                            itr = soapResponse.getSOAPBody().getChildElements();
                            GetResponse oResTrue;
                            oResTrue = ReadXMLData(itr);
                            //System.out.println(" Value of return code is "+getReturnCode());
                            if (getReturnCode().equals("1") && oResTrue.isIsSuccess()==true) {
                                soapEndpointUrl = "https://iservices.moi.gov.kw:5299/knet/services/PayGateService";
                                soapAction = "http://www.moi.gov.kw/Services/PayGateService/V1/initiatePayment";
                                mSOAPClientSAAJ = new SOAPClientSAAJ();
                                //System.out.println("before calling callSoapWebService");
                                mSOAPClientSAAJ.setAmount(getAmount());
                                soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, 3, requestDataArray);
                                //System.out.println("after calling callSoapWebService");
                                //System.out.println("Response SOAP Message:");
                                //soapResponse.writeTo(System.out);
                                //System.out.println();
                                itr = soapResponse.getSOAPBody().getChildElements();
                                String valueNeeded = CheckXMLPaymentData(itr);
                                // valueNeeded = valueNeeded + "PaymentID=" + getAmount();
                                setPaymentURL(valueNeeded);
                                TrueResult tempResult = oResTrue.getResult();
                                List<ControlsModel> tControlModelist = tempResult.getControlsModels();
                                for (int nArows = 0; nArows < tControlModelist.size(); nArows++) {
                                    //System.out.println("---10---- :: id to compare " + tControlModelist.get(nArows).getId());

                                    if (tControlModelist.get(nArows).getId().equals("ResidencyPayFee")) {
                                        //System.out.println("---10---- ::id found in  " + nArows);
                                        ControlsModel tControlModel = tControlModelist.get(nArows);
                                        tControlModel.setDefaultValue(getPaymentURL());
                                        tControlModelist.set(nArows, tControlModel);
                                        tempResult.setControlsModels(tControlModelist);
                                        oResTrue.setResult(tempResult);
                                        break;
                                    }

                                } } else {
                                 oResTrue.setIsSuccess(false);
                            oResTrue.setMessage(oResTrue.getMessage());
                            oResTrue.setMessageEn(oResTrue.getMessageEn());
                            oResTrue.setResult(null);

                           
                               
                            }

                                response.setContentType("application/json");
                                response.setCharacterEncoding("utf-8");
                                String jsonData = gson2.toJson(oResTrue);
                                PrintWriter out = response.getWriter();

                                out.println(jsonData);
                           
                        } else {
                            response.setContentType("application/json");
                                response.setCharacterEncoding("utf-8");

                                String jsonData = "Data Enter in the body is missing need :ResidentCivilID , FeesPeriod and FeesType";
                                PrintWriter out = response.getWriter();

                                out.println(jsonData);
                        }

                        break;
                    case 2://Step Number 2
                        if (requestDataArray[0].getKey().equals("ResidentNatNumber")) {
                            soapEndpointUrl = "https://iservices.moi.gov.kw:5302/SahelPayViewTest/services/SahelPayViewService";
                            soapAction = "urn:example/SahelPayView";
                            mSOAPClientSAAJ = new SOAPClientSAAJ();
                            //System.out.println("before calling callSoapWebService     case2");
                            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, civilId, stepNo, requestDataArray);
                            //System.out.println("after calling callSoapWebService");
                            //System.out.println("Response SOAP Message:");
                            //soapResponse.writeTo(System.out);
                            //System.out.println();
                            itr = soapResponse.getSOAPBody().getChildElements();
                            String valueNeeded = CheckXMLPaymentData(itr);
                            GetResponse oResTrue = new GetResponse();
                            // String valueNeeded = "1";

                            if (valueNeeded.equals("1"))// payment success
                            {
                                soapEndpointUrl = "http://10.10.1.1:29084/RE004/PERSON_DETAILS_DISPLAY";
                                //soapEndpointUrl = "http://10.11.78.103:9080/RE004/PERSON_DETAILS_DISPLAY";
                                soapAction = "http://tempuri.org/PersonDetailsDisplaycall/";
                                mSOAPClientSAAJ = new SOAPClientSAAJ();
                                //System.out.println("before getting personal data");
                                soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, requestDataArray[0].getValue(), 4, null);
                                //System.out.println("after getting personal data");
                                //System.out.println("Response SOAP Message:");
                                //soapResponse.writeTo(System.out);
                                //System.out.println();

                                itr = soapResponse.getSOAPBody().getChildElements();
                                String[] personName = GetPersonName(itr);

                                oResTrue.setIsSuccess(true);
                                oResTrue.setMessage("تم  تحصيل الرسوم\nاسم المقيم  : " + personName[1]);
                                oResTrue.setMessageEn("Fees Collected \n Resident Name: " + personName[0]);
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
                                oResTrue.setAlertType("Error");
                            // TrueResult temp = new TrueResult();
                             //temp.setStepNo(3);
                                oResTrue.setResult(null);
                            }

                            response.setContentType("application/json");
                            response.setCharacterEncoding("utf-8");
                           /* ExclusionStrategy strategy = new ExclusionStrategy() {
                               @Override
    public boolean shouldSkipField(FieldAttributes field) {
        if (field.getDeclaringClass() == TrueResultEmpty.class && field.getName().equals("nameAr")) {
            return true;
        }
        if (field.getDeclaringClass() == TrueResultEmpty.class && field.getName().equals("nameEn")) {
            return true;
        }
        if (field.getDeclaringClass() == TrueResultEmpty.class && field.getName().equals("stepNo")) {
            return true;
        }
        if (field.getDeclaringClass() == TrueResultEmpty.class && field.getName().equals("buttonLabelAr")) {
            return true;
        }
        if (field.getDeclaringClass() == TrueResultEmpty.class && field.getName().equals("buttonLabelEn")) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
                            };*/
                          //  Gson gson3 = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
                           // String jsonString = gson.toJson(source);



                            String jsonData = gson.toJson(oResTrue);
                            PrintWriter out = response.getWriter();
                            out.println(jsonData);
                        } else {
                            response.setContentType("application/json");
                            response.setCharacterEncoding("utf-8");

                            String jsonData = "Data Enter in the body is not correct ,  need :ResidentNatNumber";
                            PrintWriter out = response.getWriter();

                            out.println(jsonData);
                        }

                        break;
                    default:
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        String jsonData = "The Step No you entered is not supported";
                        PrintWriter out = response.getWriter();
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
            Logger.getLogger(ResidencyFeesMaidVisas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GetResponse ReadXMLData(Iterator itr) {
        
        String isSuccess = "";
        String messageAr = "";
        String messageEn = "";
        GetResponse oResTrue = new GetResponse();
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        //System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("ns2:ResidencyFeesDisplayShlcallResponse") || FirstNodeName.equals("ns2:ResidencyFeesMaidVisasShlcallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            //System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("ResidencyFeesDisplayShlExport") || SecondNodeName.equals("ResidencyFeesMaidVisasShlExport")) {
                NodeList childNodes = node2.getChildNodes();
                int numberOfChilds = childNodes.getLength();
                //System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                for (int child = 0; child < numberOfChilds; child++) {
                    //System.out.println("---4---- :: child name  :" + child + " is " + childNodes.item(child).getNodeName());
                    if (childNodes.item(child).getNodeName().equals("OutputParameters")) {
                        NodeList outputParameterNodes = childNodes.item(child).getChildNodes();
                        int numberOfOPChilds = outputParameterNodes.getLength();
                        //System.out.println("---5---- :: number of childs:" + numberOfOPChilds);

                        for (int oPChild = 0; oPChild < numberOfOPChilds; oPChild++) {
                            //System.out.println("---6---- :: child name  :" + oPChild + " is " + outputParameterNodes.item(oPChild).getNodeName());

                            switch (outputParameterNodes.item(oPChild).getNodeName()) {
                                case "IsSuccess":
                                    isSuccess = outputParameterNodes.item(oPChild).getTextContent();
                                    //System.out.println("---6---- :: value of success is " + isSuccess);
                                    break;
                                case "Message":
                                    messageAr = outputParameterNodes.item(oPChild).getTextContent();
                                    //System.out.println("---6---- :: value of messageAr is " + messageAr);
                                    break;
                                case "MessageEn":
                                    messageEn = outputParameterNodes.item(oPChild).getTextContent();
                                    //System.out.println("---6---- :: value of messageEn is " + messageEn);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    if (isSuccess.equals("false")) {

                        oResTrue.setIsSuccess(false);
                        oResTrue.setMessage(messageAr);
                        oResTrue.setMessageEn(messageEn);
                        oResTrue.setResult(null);

                        return oResTrue;

                    } else {// isSuccess is true

                        oResTrue.setIsSuccess(true);
                        oResTrue.setMessage(messageAr);
                        oResTrue.setMessageEn(messageEn);
                        TrueResult tempResult = new TrueResult();
                        int numberOfArrayRows;
                        for (int child2 = 0; child2 < numberOfChilds; child2++) {
                            //System.out.println("---7---- :: child name  :" + child2 + " is " + childNodes.item(child2).getNodeName());
                            NodeList childNodes4 = childNodes.item(child2).getChildNodes();
                            numberOfArrayRows = childNodes4.getLength();//we have 3 rows in the array
                            List<ControlsModel> tempControlModelList = new ArrayList<>();

                            //System.out.println("---8---- :: number of numberOfChilds4:" + numberOfArrayRows + "value to be switched " + childNodes.item(child2).getNodeName());

                            ControlsModel[] tempControlModel = new ControlsModel[numberOfArrayRows];
                            switch (childNodes.item(child2).getNodeName()) {
                                case "Result":
                                    NodeList resultChildNodes = childNodes.item(child2).getChildNodes();
                                    int numberOfResultChildren = resultChildNodes.getLength();
                                    //System.out.println("---7---- :: number of childs:" + numberOfResultChildren);

                                    for (int resultChild = 0; resultChild < numberOfResultChildren; resultChild++) {

                                        switch (resultChildNodes.item(resultChild).getNodeName()) {
                                            case "NameAr":
                                                tempResult.setNameAr(resultChildNodes.item(resultChild).getTextContent());
                                                //System.out.println("---7---- :: value of NameAr is " + tempResult.getNameAr());
                                                break;
                                            case "NameEn":
                                                tempResult.setNameEn(resultChildNodes.item(resultChild).getTextContent());

                                                //System.out.println("---7---- :: value of NameEn is " + tempResult.getNameEn());
                                                break;
                                            case "StepNo":
                                            case "Stepno":
                                            case "stepNo":
                                            case "stepno":
                                                tempResult.setStepNo(Integer.valueOf(resultChildNodes.item(resultChild).getTextContent()));

                                                //System.out.println("---7---- :: value of StepNo is " + tempResult.getStepNo());
                                                break;
                                            case "ButtonLabelAr":
                                                tempResult.setButtonLabelAr(resultChildNodes.item(resultChild).getTextContent());

                                                //System.out.println("---7---- :: value of ButtonLabelAr is " + tempResult.getButtonLabelAr());
                                                break;
                                            case "ButtonLabelEn":
                                                tempResult.setButtonLabelEn(resultChildNodes.item(resultChild).getTextContent());
                                                //System.out.println("---7---- :: value of ButtonLabelEn is " + tempResult.getButtonLabelEn());
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    break;
                                case "Arrays":
                                case "Array":
                                    //System.out.println("---8---- :: in Array Case");
                                    for (int nArows = 0; nArows < numberOfArrayRows; nArows++) {//loop on the rows of the array
                                        tempControlModel[nArows] = new ControlsModel();
                                        Node controlModelsNode = childNodes4.item(nArows).getFirstChild();
                                        //System.out.println("---8---- :: VALUE of tempNode:" + controlModelsNode.getNodeName());
                                        NodeList controlModelsFieldNodeList = controlModelsNode.getChildNodes();
                                        int NumberOfControlModelsChilds = controlModelsFieldNodeList.getLength();//there is 5 parameters in controlmodels
                                        //System.out.println("---8---- :: number of childs:" + NumberOfControlModelsChilds);

                                        for (int nCMFields = 0; nCMFields < NumberOfControlModelsChilds; nCMFields++) {//loop on the fields of control models

                                            switch (controlModelsFieldNodeList.item(nCMFields).getNodeName()) {
                                                case "Id":

                                                    tempControlModel[nArows].setId(controlModelsFieldNodeList.item(nCMFields).getTextContent());

                                                    //System.out.println("---8---- :: value of Id is " + tempControlModel[nArows].getId());
                                                    break;
                                                case "Type":
                                                    tempControlModel[nArows].setType(controlModelsFieldNodeList.item(nCMFields).getTextContent());

                                                    //System.out.println("---8---- :: value of Type is " + tempControlModel[nArows].getType());
                                                    break;
                                                case "LabelAr":
                                                    tempControlModel[nArows].setLabelAr(controlModelsFieldNodeList.item(nCMFields).getTextContent());

                                                    //System.out.println("---8---- :: value of LabelAr is " + tempControlModel[nArows].getLabelAr());
                                                    break;
                                                case "LabelEn":
                                                    tempControlModel[nArows].setLabelEn(controlModelsFieldNodeList.item(nCMFields).getTextContent());

                                                    //System.out.println("---8---- :: value of LabelEn is " + tempControlModel[nArows].getLabelEn());
                                                    break;
                                                case "IsRequired":
                                                    tempControlModel[nArows].setIsRequired(Boolean.valueOf(controlModelsFieldNodeList.item(nCMFields).getTextContent()));
                                                    //System.out.println("---8---- :: value of IsRequired is " + tempControlModel[nArows].getIsRequired());
                                                    break;
                                                case "DefaultValue":
                                                    tempControlModel[nArows].setDefaultValue(controlModelsFieldNodeList.item(nCMFields).getTextContent());
                                                    if (controlModelsFieldNodeList.item(nCMFields).getTextContent().equals("ResidencyPayFee")) {
                                                        tempControlModel[nArows].setDefaultValue(getPaymentURL());
                                                    }
                                                    //System.out.println("---8---- :: value of DefaultValue is " + tempControlModel[nArows].getDefaultValue());
                                                    break;

                                                default:
                                                    break;

                                            }

                                        }
                                        //System.out.println("---8---- ::add the row no " + nArows + "with data " + tempControlModel[nArows].getId());
                                        tempControlModelList.add(nArows, tempControlModel[nArows]);
                                    }
                                    tempResult.setControlsModels(tempControlModelList);

                                    break;
                                case "ResidencyFeesDetails":
                                    NodeList residencyFeesChildNodes = childNodes.item(child2).getChildNodes();
                                    int numberResidencyFeesChildren = residencyFeesChildNodes.getLength();
                                    //System.out.println("---7---- :: number of childs:" + numberResidencyFeesChildren);

                                    for (int residencyFeesChild = 0; residencyFeesChild < numberResidencyFeesChildren; residencyFeesChild++) {

                                        switch (residencyFeesChildNodes.item(residencyFeesChild).getNodeName()) {
                                            case "ResidentCivilId":
                                                setResidentCivilId(residencyFeesChildNodes.item(residencyFeesChild).getTextContent());
                                                //System.out.println("---55---- :: value of ResidentCivilId is " + getResidentCivilId());
                                                break;
                                            case "FeesType":
                                                setFeesType(residencyFeesChildNodes.item(residencyFeesChild).getTextContent());
                                                //System.out.println("---55---- :: value of FeesType is " + getFeesType());
                                                break;
                                            case "Period":
                                                setPeriod(residencyFeesChildNodes.item(residencyFeesChild).getTextContent());
                                                //System.out.println("---55---- :: value of Period is " + getPeriod());
                                                break;
                                            case "Amount":
                                                setAmount(residencyFeesChildNodes.item(residencyFeesChild).getTextContent());
                                                //System.out.println("---55---- :: value of Amount is " + getAmount());
                                                break;
                                            default:
                                                break;
                                        }
                                    }

                                    break;
                                case "Response":
                                    Node tempNode = childNodes.item(child2).getFirstChild();

                                    String tempNodeName = tempNode.getNodeName();
                                    //System.out.println("---551---- :: ReturnCode:" + tempNodeName);
                                    if (tempNodeName.equals("ReturnCode")) {
                                        setReturnCode(tempNode.getTextContent());

                                        //System.out.println("---551---- :: ReturnCode value:" + tempNode.getTextContent());
                                    }
                                    break;
                                case "ResidentArray":
                                case "PeriodArray":
                                    NodeList ResidentArrayNodes = childNodes.item(child2).getChildNodes();
                                    int numberOfResArrayRows = ResidentArrayNodes.getLength();//we have 3 rows in the array
                                    List<OptionControlModel> tempResControlModelList = new ArrayList<>();

                                    //System.out.println("---8---- :: number of numberOfChilds4:" + numberOfResArrayRows);
                                    OptionControlModel[] tempResControlModel = new OptionControlModel[numberOfResArrayRows];
                                    for (int nRArows = 0; nRArows < numberOfResArrayRows; nRArows++) {//loop on the rows of the array
                                        tempResControlModel[nRArows] = new OptionControlModel();
                                        Node resControlModelsNode = ResidentArrayNodes.item(nRArows).getFirstChild();
                                        //System.out.println("---8---- :: VALUE of tempNode:" + resControlModelsNode.getNodeName());
                                        NodeList resControlModelsFieldNodeList = resControlModelsNode.getChildNodes();
                                        int NumberOfResControlModelsChilds = resControlModelsFieldNodeList.getLength();//there is 5 parameters in controlmodels
                                        //System.out.println("---8---- :: number of childs:" + NumberOfResControlModelsChilds);

                                        for (int nCMFields = 0; nCMFields < NumberOfResControlModelsChilds; nCMFields++) {//loop on the fields of control models

                                            switch (resControlModelsFieldNodeList.item(nCMFields).getNodeName()) {
                                                case "Value":

                                                    tempResControlModel[nRArows].setValue(resControlModelsFieldNodeList.item(nCMFields).getTextContent());
                                                    //System.out.println("---8---- :: value of Id is " + tempResControlModel[nRArows].getValue());
                                                    break;
                                                case "TextAr":
                                                    tempResControlModel[nRArows].setTextAr(resControlModelsFieldNodeList.item(nCMFields).getTextContent().trim());

                                                    //System.out.println("---8---- :: value of Type is " + tempResControlModel[nRArows].getTextAr());
                                                    break;
                                                case "TextEn":
                                                    tempResControlModel[nRArows].setTextEn(resControlModelsFieldNodeList.item(nCMFields).getTextContent().trim());

                                                    //System.out.println("---8---- :: value of LabelAr is " + tempResControlModel[nRArows].getTextEn());
                                                    break;

                                                default:
                                                    break;

                                            }

                                        }
                                        tempResControlModelList.add(nRArows, tempResControlModel[nRArows]);

                                    }
                                    List<ControlsModel> tControlModelist = tempResult.getControlsModels();
                                    for (int nArows = 0; nArows < numberOfArrayRows; nArows++) {
                                        //System.out.println("---10---- :: id to compare " + tControlModelist.get(nArows).getId() + "compare it with   " + numberOfArrayRows);

                                        if (tControlModelist.get(nArows).getId().equals("ResidentCivilID") && childNodes.item(child2).getNodeName().equals("ResidentArray")) {
                                            //System.out.println("---10---- ::id found in  " + nArows);
                                            ControlsModel tControlModel = tControlModelist.get(nArows);
                                            tControlModel.setOptionControlModels(tempResControlModelList);
                                            tControlModelist.set(nArows, tControlModel);
                                            tempResult.setControlsModels(tControlModelist);
                                            break;
                                        }
                                        if (tControlModelist.get(nArows).getId().equals("FeesPeriod") && childNodes.item(child2).getNodeName().equals("PeriodArray")) {
                                            //System.out.println("---10---- ::id found in  " + nArows);
                                            ControlsModel tControlModel = tControlModelist.get(nArows);
                                            tControlModel.setOptionControlModels(tempResControlModelList);
                                            tControlModelist.set(nArows, tControlModel);
                                            tempResult.setControlsModels(tControlModelist);
                                            break;
                                        }
                                    }

                                    break;

                                default:
                                    break;
                            }
                        }
                        //System.out.println("---00---- ::before writing the data  ");
                        oResTrue.setResult(tempResult);
                        return oResTrue;
                    }

                }

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
                            default:
                                break;
                        }

                    }
                }
            }
        }
        String personName[] = new String[2];
        personName[0] = firstNameEn + " " + secondNameEn + " " + thirdNameEn + " " + familyNameEn;
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

    /**
     * @return the residentCivilId
     */
    public String getResidentCivilId() {
        return residentCivilId;
    }

    /**
     * @param residentCivilId the residentCivilId to set
     */
    public void setResidentCivilId(String residentCivilId) {
        this.residentCivilId = residentCivilId;
    }

}
