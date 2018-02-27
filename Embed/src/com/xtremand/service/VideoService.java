package com.xtremand.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class VideoService
{
  private static final String USERAGENT = "Mozilla/5.0";
  
  public VideoService() {}
  
  public JSONObject getVideo(String shortenUrlAlias) throws java.io.IOException
  {
    String url = "https://aravindu.com/xtremand-rest/videos/video-by-shortenerurlalias?shortenUrlAlias=" + shortenUrlAlias;
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url);
    request.addHeader("User-Agent", "Mozilla/5.0");
    
    HttpResponse response = client.execute(request);
    return handleResponse(response);
  }
  
  public JSONObject handleResponse(HttpResponse response)
  {
    int status = response.getStatusLine().getStatusCode();
    JSONObject returnData = new JSONObject();
    JSONParser parser = new JSONParser();
    if ((status >= 200) && (status < 300)) {
      HttpEntity entity = response.getEntity();
      try
      {
        if (entity == null) {
          returnData.put("status_code", "1");
          returnData.put("error_message", "null Data Found");
        } else {
          returnData = (JSONObject)parser.parse(org.apache.http.util.EntityUtils.toString(entity));
        }
      } catch (Exception e) {
        e.printStackTrace();
        returnData.put("status_code", "1");
        returnData.put("error_message", e.getMessage());
      }
    } else {
      returnData.put("status_code", "1");
      returnData.put("error_message", "Unexpected response status: " + status);
    }
    
    return returnData;
  }
}
