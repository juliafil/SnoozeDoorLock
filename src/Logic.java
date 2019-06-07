import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Logic {

    public static void main(String[] args) throws IOException{
        try {

            URL url = new URL("https://platania.info:3000/api/Capsules/2/bookings?access_token=TCmDmaQGQt76PArgLl6BbAaEsgQvPX3vqwG82Pzq4DXujexKguwnXXwghGgKQVsF");
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();



        } catch (ProtocolException e) {
            //
        }

    }
}

