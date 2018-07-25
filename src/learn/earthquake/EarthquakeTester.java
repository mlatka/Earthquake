package learn.earthquake;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EarthquakeTester {

    public static void main(String[] args) {


        String searchResults=EarthquakeQuery.findEarthquakes(1,5 );

        System.out.println(searchResults);

        JSONParser parser= new JSONParser();


        try {
            Object obj= parser.parse(searchResults);
            System.out.println("\n" + obj + "\n");
            JSONObject json= (JSONObject) obj;
           // System.out.println(((JSONObject)json.get("main")).get("temp"));
           // JSONArray array = (JSONArray)obj;

            PrintWriter printWriter = new PrintWriter("test.txt");
            printWriter.println(obj);
            printWriter.close();

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
