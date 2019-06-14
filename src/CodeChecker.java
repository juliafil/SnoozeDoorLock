import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;


/**
 * @author Edouard
 * Class that connects to the API via SSL and gets the PIN code from the actual booking
 * If no booking, empty JSON is received. TODO handle an empty booking
 */
public class CodeChecker {

    private String capsuleCode;

    /**
     * Takes the userPin and compares to the capsulePin. Returns true if the entered code is correct
     * @param s
     * @return
     */
    public boolean checkValidity(String s) {
        return s.equals(getCurrentCode());
    }

    /**
     * Gets the "Pin" from the JSON got in the GET request. Returns it.
     * @return
     */
    public String getCurrentCode(){
        try {
            URL url = new URL("https://platania.info:3000/api/Capsules/2/bookings?access_token=GN0tME3nUBa6auETCDju80cAzMSMDaDY791UafudXydp6AwwLfVjEJDDxJTjHEg3");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            conn.setSSLSocketFactory(socketFactory);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

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
                conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return capsuleCode;

    }
}

