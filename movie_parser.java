import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject obj = readJsonFromUrl("https://api.themoviedb.org/3/movie/now_playing?api_key=d5cb409394cf931c2fea8fef9c3aa05f&page=1");
        System.out.println(obj.toString());
        //JSONParser parser = new JSONParser();
        JSONArray jsonarray = (JSONArray)obj.get("results");
        //JSONObject obj1 = (JSONObject)obj.get("results");
        //System.out.println(obj1);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String name = jsonobject.getString("overview");
            //String url = jsonobject.getString("url");
            //System.out.println(name);
        }
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabaseNames().forEach(System.out::println);
        
    }
}