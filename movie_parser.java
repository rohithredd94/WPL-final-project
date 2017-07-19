import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.*;
import com.mongodb.*;
//import org.json.simple.parser.JSONParser;

public class movie_parser{

    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
    
    public static JSONArray concatArray(JSONArray arr1, JSONArray arr2)
            throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < arr1.length(); i++) {
            result.put(arr1.get(i));
        }
        for (int i = 0; i < arr2.length(); i++) {
            result.put(arr2.get(i));
        }
        return result;
    }

    public static void main(String[] args) throws IOException, JSONException {
    	JSONArray jsonarray = new JSONArray();
    	JSONArray json_cast = new JSONArray();
    	JSONArray json_crew = new JSONArray();
    	for(int i=1;i<=5;i++) {
        JSONObject obj = readJsonFromUrl("https://api.themoviedb.org/3/discover/movie?api_key=d5cb409394cf931c2fea8fef9c3aa05f&year=2017&sort_by=popularity.desc&page="+i);
        JSONArray jsonarray1 = (JSONArray)obj.get("results");
        for(int j=0;j<jsonarray1.length();j++) {
        	JSONObject temp = (JSONObject) jsonarray1.get(j);
        	int movie_id=temp.getInt("id");
        	JSONObject obj2 = readJsonFromUrl("https://api.themoviedb.org/3/movie/"+movie_id+"/credits?api_key=d5cb409394cf931c2fea8fef9c3aa05f");
        	JSONObject cast = new JSONObject();
        	cast.put("id", movie_id);
        	JSONArray jsonarray2 = (JSONArray)obj2.get("cast");
        	cast.append("cast", jsonarray2);
        	json_cast.put(cast);
        	try        
        	{
        	    Thread.sleep(200);
        	} 
        	catch(InterruptedException ex) 
        	{
        	    Thread.currentThread().interrupt();
        	}
        }
        
        jsonarray = concatArray(jsonarray, jsonarray1);
        /*for (int j = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(j);
            String name = jsonobject.getString("overview");
            //String url = jsonobject.getString("url");
            //System.out.println(name);
        }*/
        
    	}
    	System.out.println(json_cast);
    	/*MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabaseNames().forEach(System.out::println);
        DB db = mongoClient.getDB("vidzy");
        Set<String> names = db.getCollectionNames();*/
        //System.out.println(names);
        //System.out.println(jsonarray);
    	}
}