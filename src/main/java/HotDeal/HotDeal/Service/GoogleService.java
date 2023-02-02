package HotDeal.HotDeal.Service;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@RequiredArgsConstructor
@Service
public class GoogleService {
    HttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new GsonFactory();
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList("862259299846-ham1sak40i9ampguua9m9mrnqqdonfa8.apps.googleusercontent.com"))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

    public static String getAccessToken(String authorize_code) {
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

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //System.out.println("response body : " + result);

            JsonObject JsonObject = JsonParser.parseString(result).getAsJsonObject();
            String access_Token = JsonObject.get("access_token").getAsString();
            //String id_Token = JsonObject.get("id_token").getAsString();

            br.close();
            return access_Token;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    /*
    public String getUserInfoByIDToken (String code) {  //구글은 액세스 토큰이 아닌 id 토큰을 발급 받아서 프로필 정보 받는다고 하는데 모르겠음.
        String id_token = getAccessToken(code);
        String reqURL = "https://oauth2.googleapis.com/tokeninfo?id_token="+id_token;
        String email="";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonObject JsonObject = JsonParser.parseString(result).getAsJsonObject();
            System.out.println("id_Token에서 받아온 정보 값 : " + JsonObject);
            email = JsonObject.get("email").getAsString();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return email;
    }

    public Object getIdTokenByIDToken2 (String id_Token) {    //with GoogleIdToken Verifier
        try{
        GoogleIdToken idToken = verifier.verify(id_Token);
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            // Print user identifier
            System.out.println(payload);
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            Map<String, String> payloadObj = new HashMap<>();
            payloadObj.put("name",name);
            payloadObj.put("pictureUrl",pictureUrl);
            payloadObj.put("locale",locale);
            return payloadObj;
        } else {
            System.out.println("Invalid ID token.");
            return new Object();
        }
        }catch(Exception e){
            e.printStackTrace();
            return new Object();
        }
    }
    */
}
