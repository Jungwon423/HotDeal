package HotDeal.HotDeal.Service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranslateService {

    public String translate(String keyword) {
        try{
            String clientId = "lFJbCyQgGPhrSLj2qOdX";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "ifU2pLAcrc";//애플리케이션 클라이언트 시크릿값";

            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            String text = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);

            String responseBody = post(apiURL, requestHeaders, text);

            Map<String, Object> responseMap = (Map<String, Object>) StringToObj(responseBody);
            String translatedKeyword = (String) responseMap.get("translatedText");
            return translatedKeyword;
        }catch(Exception e){
            return "Exception Occured";
        }
    }

    public String translateKoToEn(String keyword){
        try{
            String clientId = "lFJbCyQgGPhrSLj2qOdX";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "ifU2pLAcrc";//애플리케이션 클라이언트 시크릿값";

            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            String text = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);

            String responseBody = post2(apiURL, requestHeaders, text);

            Map<String, Object> responseMap = (Map<String, Object>) StringToObj(responseBody);
            String translatedKeyword = (String) responseMap.get("translatedText");
            return translatedKeyword;
        }catch(Exception e){
            return "Exception Occured";
        }
    }
    private String post2(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; // ko -> en
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    private String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=en&target=ko&text=" + text; // en -> ko
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body) {
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
    private Object StringToObj(String responseBody) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject JsonBodyFirstLevel = (JSONObject) parser.parse(responseBody);
        JSONObject JsonBodySecondLevel = (JSONObject) JsonBodyFirstLevel.get("message");
        return JsonBodySecondLevel.get("result");
    }
}

