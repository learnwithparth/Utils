import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;


public class SMSGatewayCenter{

    public static void main(String args[]){
        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        // this will convert any number sequence into 6 character.
        System.out.printf( "%04d", number);
        System.out.println(sendOTPSms("VDJ1muqjMtm6puO4",
                "1234 is your One Time Password(OTP) for Merit Access. This OTP is valid till 120 sec - CHARUSAT",
                "CHRUST",
                "9925020358"));
    }

    public static String sendOTPSms(String api, String msg, String snd, String num) {
        try {
            // Construct data

            String apiKey = "apikey=" + api;
            String message = "&message=" + msg;
            String sender = "&senderid=" + snd;
            String numbers = "&number=" + num;

//            String apiKey = "apikey=" + "VDJ1muqjMtm6puO4";
//            String message = "&message=" + "is your One Time Password(OTP) for Merit Access. This OTP is valid till 120 sec - CHARUSAT";
//            String sender = "&senderid=" + "CHRUST";
//            String numbers = "&number=" + "9767080185";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://login.smsforyou.biz/V2/http-api.php?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}
