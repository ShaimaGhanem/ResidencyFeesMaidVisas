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
import javax.servlet.ServletException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@WebServlet("/")
public class HandleRequest extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the data from the body (url and json input data )
        System.out.println("-------------start----------------");
        String data;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        data = builder.toString();

        if (!data.isEmpty()) {
            Gson gson = new Gson();
            PostRequest requestData = gson.fromJson(data, PostRequest.class);
            if (requestData.getUrl() != null && requestData.getUrl().length() > 0 && requestData.getJson() != null && requestData.getJson().length() > 0) {
                System.out.println("---------------1----------------");
                CloseableHttpClient client = HttpClientBuilder.create().build();
                String responseJSON = "";
                System.out.println("-------------2--------------");
                //  AuthenticationData authenticationData = new AuthenticationData();
                System.out.println("-----3-------requestData.getUrl()-------------"+requestData.getUrl());
                HttpPost httpPost = new HttpPost(requestData.getUrl());
                System.out.println("--------4--------------");
                System.out.println("-------------5--------------");
                String json = requestData.getJson();

                System.out.println("-------------6--------requestData.getJson()----" + json);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                System.out.println("-------------7--------------");
                // set your POST request headers to accept json contents
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                System.out.println("-------------8--------------");

                System.out.println("-------------9--------------");
                // your closeablehttp response

                CloseableHttpResponse response2 = client.execute(httpPost);
                System.out.println("------------10--------------");
                // print your status code from the response
                System.out.println(response2.getStatusLine().getStatusCode());
                System.out.println("-------------11--------------");
                // take the response body as a json formatted string 
                responseJSON = EntityUtils.toString(response2.getEntity());
                System.out.println("--------------------------12----------------------------------" + responseJSON);
                
                
                
                String requestType = requestData.getRequestType();
                switch (requestType){
                    case "Login":
                        String location = "";
                        String title = "";
                        //call the soap webservice that return the location and title
                        //reomve the qoutes from the end to add the part then return it
                        responseJSON = responseJSON.substring(responseJSON.length()-1);
                        responseJSON = responseJSON +"\"location\":"+location+",\"title\":"+title+"}";
                        break;
                    case "FingerPrint":
                        //call the soap to log the data in db
                        //define class same as data that will be stored in log
                        break;
                        case "ListData":
                            //define variable from prev class
                            //call the list soap to get data in varaible
                            //turn data to json
                            //put in responseJson
                        break;
                    case "PassDetails":
                        //define class with passDetails
                        //call soap to get data and fill variable with it
                        //turn var to json to send it
                        break;
                    default :
                        break;
                }
                
                
                
                
                
                
                
                

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                gson = new Gson();
                String jsonData = gson.toJson(responseJSON);
                PrintWriter out = response.getWriter();

                out.println(responseJSON);

            } else {
                System.err.println("\n'url' and 'json'  is required as a parameters\n");
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                String jsonData = "'url' and 'json'  is required as a parameters";
                PrintWriter out = response.getWriter();

                out.println(jsonData);
            }
        } else {
            System.err.println("\n body required with 'url' and 'json'  as a parameters\n");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            String jsonData = "body required with 'url' and 'json'  as a parameters";
            PrintWriter out = response.getWriter();

            out.println(jsonData);
        }
    }
}
