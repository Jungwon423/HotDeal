package HotDeal.HotDeal.Util;

import HotDeal.HotDeal.Domain.GoogleUserDto;
import HotDeal.HotDeal.Service.GoogleService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GoogleAuthUtils {

    public static GoogleUserDto getUserInfoByAccessToken(String code) {
        String access_token = GoogleService.getAccessToken(code);
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        String apiURL = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + access_token;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL, requestHeaders);
        //System.out.println(responseBody);
        try{
            JSONParser parser = new JSONParser();
            JSONObject responseJson = (JSONObject) parser.parse(responseBody);
            String id = String.valueOf(responseJson.get("id"));
            String email = String.valueOf(responseJson.get("email"));
            String imageUrl = String.valueOf(responseJson.get("picture"));
            return new GoogleUserDto(id, email, imageUrl);
        }catch(Exception e){
            throw new IllegalArgumentException();
        }
    }

    private static JSONObject StringToObj(String responseBody) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject JsonBodyFirstLevel = (JSONObject) parser.parse(responseBody);
            return (JSONObject) JsonBodyFirstLevel.get("response");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
