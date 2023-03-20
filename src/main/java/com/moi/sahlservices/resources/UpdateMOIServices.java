
package com.moi.sahlservices.resources;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet("/updateMOIServices")
public class UpdateMOIServices extends HttpServlet {
    
     static final FileParameters fileParameters = new FileParameters();

    static {
        try {
            fileParameters.readParameters();
        } catch (IOException ex) {
            // Logger.getLogger(InitiatePayment.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(UpdateMOIServices.class.getName() + ex.getMessage());
        }
    }

   
       public String checkXMLStatus(Iterator itr) {

        String returnMessageCode = "";
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("a:ResidencyFeesConfirmPaymentcallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("ResidencyFeesConfirmPaymentExport")) {

                NodeList childNodes = node2.getChildNodes();
                int numberOfChilds = childNodes.getLength();
                // System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                for (int child = 0; child < numberOfChilds; child++) {
                    System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                    switch (childNodes.item(child).getNodeName()) {
                        case "Response":
                            // System.out.println("---6---- :: value of success is " + messageCode);
                            NodeList childNodes2 = childNodes.item(child).getChildNodes();
                            int numberOfChilds2 = childNodes2.getLength();
                            for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                // System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                                switch (childNodes2.item(child2).getNodeName()) {

                                    case "ReturnCode":
                                        returnMessageCode = childNodes2.item(child2).getTextContent();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                      

                    }
                }

            }

        }
        return returnMessageCode;

    }
 @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            
            
            //get data
            //auth="+getPaymentData().getAuthCode()+"&postdat="+mDate+"&ref="+getPaymentData().getRefID()+"&trackid="+getPaymentData().getmInitResponse().getTrackId()+"&amt="+getPaymentData().getAmount()+"&paymentid="+getPaymentData().getPaymentID()+"&tranid="+getPaymentData().getTransID()+"&result="+getPaymentData().getResult()+"&udf1="+udf1+"&udf2="+udf2+"&udf3="+udf3+"&udf4="+udf4+"&udf5="+udf5);
            //String ResPaymentId,ResResult,ResErrorText,ResPosdate,ResTranId,ResAuth,ResAmount,ResErrorNo,ResTrackID,ResRef,ResAVR,Resudf1,Resudf2,Resudf3,Resudf4,Resudf5,trandata;
            String auth =request.getParameter("auth");
            System.out.println("----------from update moi services --------auth------------"+auth);
            String postdate =request.getParameter("postdate");
            System.out.println("----------from update moi services --------postdat------------"+postdate);
            String ref =request.getParameter("ref");
            System.out.println("----------from update moi services --------ref------------"+ref);
            String trackid =request.getParameter("trackid");
            System.out.println("----------from update moi services --------trackid------------"+trackid);
            String amt =request.getParameter("amt");	
            System.out.println("----------from update moi services --------amt------------"+amt);
            String paymentid =request.getParameter("paymentid");
            System.out.println("----------from update moi services --------paymentid------------"+paymentid);
            String tranid =request.getParameter("tranid");	
            System.out.println("----------from update moi services --------tranid------------"+tranid);
            String result =request.getParameter("result");
            System.out.println("----------from update moi services --------result------------"+result);
            String udf1 =request.getParameter("udf1");//application id
            System.out.println("----------from update moi services --------udf1------------"+udf1);
            String udf2 =request.getParameter("udf2");//user civil id
            System.out.println("----------from update moi services --------udf2------------"+udf2);
            String udf3 =request.getParameter("udf3");// (pay for civi id)
            System.out.println("----------from update moi services --------udf3------------"+udf3);
            String udf4 =request.getParameter("udf4");//penalty number
            System.out.println("----------from update moi services --------udf4------------"+udf4);
            String udf5 =request.getParameter("udf5");//reciept number
            System.out.println("----------from update moi services --------udf5------------"+udf5);

            String soapEndpointUrl;
            String soapAction;
            SOAPClientSAAJ mSOAPClientSAAJ;
            SOAPMessage soapResponse;
               List<String> extraData = new ArrayList<String>();
               
                Iterator itr;
            soapEndpointUrl = fileParameters.getDeportationMoiIP()+ ":" + fileParameters.getDeportationMoiPort()+ "/RE002/RESIDENCY_FEES_CONFIRM_PAYMENT";
            
            soapAction = "http://tempuri.org/ResidencyFeesConfirmPaymentcall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();
            
            System.out.println("after getting the data --fileParameters.getDeportationMoiPort()--"+fileParameters.getDeportationMoiPort());
           
            
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String logDate = dtf.format(now);
            System.out.println("logDate:------------:"+logDate);
            String[] splited = logDate.split("\\s+");
            System.out.println("splited[0]"+splited[0]);
            System.out.println("splited[1]"+splited[1]);
            
            //System.out.println("before calling callSoapWebService");
            extraData = new ArrayList<String>();
            extraData.add(0,udf2 );//sponser civil id
            extraData.add(1,udf3 );//resident civil id
            extraData.add(2,udf5);//fees period
            extraData.add(3,udf4 );//fees type
            extraData.add(4, ref);//TransactionID
            extraData.add(5,splited[0] );//date
            extraData.add(6, splited[1]);//time
           
           
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction,udf2, 7,null);//, extraData);
            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            itr = soapResponse.getSOAPBody().getChildElements();
            String status = checkXMLStatus(itr);
            String ServiceExecStatus = "0";
            //System.out.println("  before forming the output:status" + status);
            if (status.equals("1")) {
                ServiceExecStatus = "1";
            }else
            {
                ServiceExecStatus = "2";
                
               
            }
             //update payment success exec with sucess or fail
                         CloseableHttpClient client = HttpClientBuilder.create().build();
                        String responseJSON = "";
                        
                        InitiatePaymentResponse initiatePaymentResponse = new InitiatePaymentResponse();
                        HttpPost httpPost = new HttpPost("http://10.10.1.100:9080/knet/payment/updateServiceStatus");
            
                       
                        String json = "{\"serviceExecStatus\":  " + "\"" + ServiceExecStatus + "\"" + " , \"trackid\": " + "\"" + trackid + "\"" + "}";

                         
                                
                                

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
                        
                       

        } catch (SOAPException ex) {
            Logger.getLogger(UpdateMOIServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}

