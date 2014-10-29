
package com.bucs.virtualmuseumcurator.splashpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.bucs.virtualmuseumcurator.GenericSeeker;
import com.bucs.virtualmuseumcurator.datamodel.EdocentSplashPageData;

//import com.bucs.virtualmuseumcurator.datamodel.MuseumHomePageData;

public class MuseumSplashHttpClient extends GenericSeeker{
 

 private DefaultHttpClient client = new DefaultHttpClient();

 
//To call the Server API and accept the Json and return it in a string format 
 public EdocentSplashPageData retrieve(String url)
 {
  HttpGet getRequest=new HttpGet(url);
  EdocentSplashPageData  frontpageobj=new EdocentSplashPageData();
 
  try{
            Log.d(url, "before calling");
            HttpResponse getResponse =client.execute(getRequest); 
            Log.d(url, "after calling");
   final int statusCode=getResponse.getStatusLine().getStatusCode();
   
   if(statusCode!=HttpStatus.SC_OK){
    Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
    return null;
   }
   
   HttpEntity getResponseEntity=getResponse.getEntity();
   //Log.d("jsonresponse", EntityUtils.toString(getResponseEntity));
   JsonSplashPage jsondeserialze=new JsonSplashPage(EntityUtils.toString(getResponseEntity));
   frontpageobj=jsondeserialze.JsonParse();
   Log.d("finishedParse","finishedParse");
   Log.d("jsonAfterParse", frontpageobj.toString());
   return frontpageobj;
   
  }
  
  catch(IOException e ){
   
   getRequest.abort();
   Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
   
  }
  return null;
 }

 //To call the Server API and accept the Json and return it in a InputStream format
 public InputStream retrieveStream(String url)
 {
  HttpGet getRequest=new HttpGet();
  try{
   HttpResponse getResponse=client.execute(getRequest);
   int statusCode=getResponse.getStatusLine().getStatusCode();
   if(statusCode==HttpStatus.SC_OK)
   {
    Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
    return null;
   }
   
   HttpEntity getResponseEntity=getResponse.getEntity();
   return getResponseEntity.getContent();
  }
  catch(IOException e)
  {
   getRequest.abort();
   Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
  }
  
  return null;
 }
 
 
 public Bitmap retrieveBitmap(String encodedImage)
 {
  byte[] decodeString=Base64.decode(encodedImage, Base64.DEFAULT);
  Bitmap decodeByte=BitmapFactory.decodeByteArray(decodeString, 0,decodeString.length);
  return decodeByte;
 }
 
 //get Museum front page 
 
 
 public String getMuseumData(String Url) {
        HttpURLConnection con = null ;
        InputStream is = null;
 
        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + Url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
             
            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");
             
            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
 
        return null;
                 
    }
 
}
