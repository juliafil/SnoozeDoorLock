import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;


/**
 * @author Edouard
 * Class that connects to the API via SSL and gets the PIN code from the actual booking
 * If no booking, 404. TODO handle an empty booking
 */
public class CodeChecker {

    public String capsuleCode;
    //public boolean internet;
/*
    public CodeChecker(){
        internet = InternetCheck.isInternetAvailable();
        getCurrentCode();
    }*/


    /**
     * Gets the "Pin" from the JSON got in the GET request. Returns it.
     * @return
     */
    public String getCurrentCode(){
        try {
/*
            //TODO check with Fabian for authentication when already connected. For now it throws an error because already authenticated
            //TODO goal here is to authenticate each time in order to get the actual AccessToken
            //DAS HIER IST MIT DEM POST DINGS
            URL auth = new URL("https://platania.info:3000/api/SnoozeUsers/login");

            JSONObject post = new JSONObject();
            post.put("username","Capsule1User");
            post.put("password","0000");

            HttpsURLConnection conn1 = (HttpsURLConnection) auth.openConnection();
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            conn1.setSSLSocketFactory(socketFactory);
            conn1.setDoOutput(true);
            conn1.setRequestMethod("POST");
            OutputStream os = conn1.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            conn1.addRequestProperty("content-type","application/json");
            String params = post.toString();
            osw.write(params);
            osw.flush();
            osw.close();
            os.close();
            conn1.connect();

            String result3;
            BufferedInputStream bis = new BufferedInputStream(conn1.getInputStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result2 = bis.read();
            while(result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();
            }
            result3 = buf.toString();
            System.out.println(result3);

*/

//TODO mit connection.getErrorStream() get the JSON error content (this is only for beauty purposes, actual code works)


            //URL url = new URL("https://platania.info:3000/api/Capsules/2/bookings?access_token=GN0tME3nUBa6auETCDju80cAzMSMDaDY791UafudXydp6AwwLfVjEJDDxJTjHEg3");
            URL url = new URL("https://platania.info:3000/api/Capsules/getCurrentBooking?access_token=zKBMRgGb6o7Fskq9wiy9nzKT3xgYqpI9BGjJTspxkOrPAJohVaS4iaCHas1AS7IY");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            conn.setSSLSocketFactory(socketFactory);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() == 404){
                    capsuleCode = "No booking";
            } else if (conn.getResponseCode() == 401){
                capsuleCode = "Login Error";
            } else if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            } else {

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String line;
                    StringBuilder responseStrBuilder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        responseStrBuilder.append(line);
                    }

                    JSONObject result = new JSONObject(responseStrBuilder.toString().replaceAll("[\\[\\]]", ""));
                    int pin = result.getInt("Pin");
                    capsuleCode = String.valueOf(pin);
                    System.out.println(capsuleCode);
                }
                conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("request response: " + capsuleCode);
        return capsuleCode;

    }
}

