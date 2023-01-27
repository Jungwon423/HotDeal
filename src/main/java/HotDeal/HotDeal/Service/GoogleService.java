package HotDeal.HotDeal.Service;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class GoogleService {
    public String getAccessToken(String authorize_code) {   //구글은 액세스 토큰이 아닌 id 토큰을 발급 받아서 프로필 정보 받음.
        String access_Token = "";
        String id_Token = "";
        String reqURL = "https://oauth2.googleapis.com/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=862259299846-ham1sak40i9ampguua9m9mrnqqdonfa8.apps.googleusercontent.com"); //수정 할것
            sb.append("&redirect_uri=http://localhost:8081/google"); //수정 할것
            sb.append("&client_secret=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"); //수정 할것
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //System.out.println("response body : " + result);

            //JsonParser parser = new JsonParser();
            //JsonElement element = parser.parse(result);
            JsonObject JsonObject = JsonParser.parseString(result).getAsJsonObject();
            access_Token = JsonObject.get("access_token").getAsString();
            id_Token = JsonObject.get("id_token").getAsString();
            System.out.println("access_token : " + access_Token);

            br.close();
            return id_Token;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error";
        }
    }

    public String getUserInfo (String code) {
        String id_token = getAccessToken(code);
        String reqURL = "https://oauth2.googleapis.com/tokeninfo?id_token="+id_token;
        String email="";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //System.out.println("response body : " + result);

            JsonObject JsonObject = JsonParser.parseString(result).getAsJsonObject();
            System.out.println("id_Token에서 받아온 정보 값 : " + JsonObject);
            email = JsonObject.get("email").getAsString();

            br.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return email;
    }
}
