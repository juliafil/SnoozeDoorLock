import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.URL;


/**
 * @author Edouard
 * Class that connects to the API via SSL and gets the PIN code from the actual booking
 * When starting the program the AccessToken is empty. It logs in to the API and gets the Access Token. It is then used
 * for further requests. If a GET request receives a Login Error, then the login method is called to get the new AccessToken
 * If no current booking
 */
public class CodeChecker {

    public String capsuleCode;
    private String actualAccessToken;
    //public boolean internet;
/*
    public CodeChecker(){
        internet = InternetCheck.isInternetAvailable();
        getCurrentCode();
    }*/

private String login(){
    try{

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
        conn1.addRequestProperty("content-type","application/json");
        OutputStream os = conn1.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
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
        //result3 = buf.toString();

        //System.out.println(buf.toString());

        JSONObject login = new JSONObject(buf.toString().replaceAll("[\\[\\]]", ""));
        actualAccessToken = login.getString("id");
        //System.out.println(actualAccessToken);


    } catch (IOException e){
        e.printStackTrace();
    }
    return actualAccessToken;
}

    /**
     * Gets the "Pin" from the JSON got in the GET request. Returns it.
     * @return
     */
    public String getCurrentCode(){
        try {
//TODO mit connection.getErrorStream() get the JSON error content (this is only for beauty purposes, actual code works)

            URL baseURL = new URL("https://platania.info:3000/api/Capsules/getCurrentBooking?access_token=");
            URL url = new URL(baseURL+actualAccessToken);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            conn.setSSLSocketFactory(socketFactory);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() == 404){
                    capsuleCode = "No booking";
            } else if (conn.getResponseCode() == 401){
                capsuleCode = "Login Error";
                login();
                getCurrentCode();
                    System.out.println("after login error, login then get new access token: "+actualAccessToken);
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
                }
                conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("request response: " + capsuleCode);
        return capsuleCode;

    }
}

