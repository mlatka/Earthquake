package learn.earthquake;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EarthquakeQuery {


    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02


    public static String  findEarthquakes(int lastDays, double minMagnitude){


        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate endDate= currentTime.toLocalDate();
        LocalDate startDate=endDate.minusDays(lastDays);

        String searchResult="";

        StringBuilder sb=generateURLBeginning(startDate,endDate, minMagnitude);

        return usgsQuery(sb).toString();
    }


    private  static StringBuilder generateURLBeginning(LocalDate startDate, LocalDate endDate, double minMagnitude){

        StringBuilder sb = new StringBuilder("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson");
        sb.append("&starttime=");
        sb.append(startDate);
        sb.append("&endtime=");
        sb.append(endDate);
        sb.append("&minmagnitude=");
        sb.append(minMagnitude);

        return sb;

    }

    private static StringBuffer usgsQuery(StringBuilder url){


        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL(url.toString());
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

        } catch (MalformedURLException ex) {

            System.out.println("bad url");
        } catch (IOException ex) {

            System.out.println("Connection failed");

        }


        return response;




    }



}
