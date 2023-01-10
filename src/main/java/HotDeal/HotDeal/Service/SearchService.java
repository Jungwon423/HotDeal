package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Repository.ExchangeRepository;
import HotDeal.HotDeal.Repository.ProductRepository;
import HotDeal.HotDeal.Util.Validator;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ProductRepository productRepository;
    private final ExchangeRepository exchangeRepository;

    public ResponseEntity<Map<String,Object>> SearchByName(String searchText) throws ParseException {
        String responseBody = SearchAPI(searchText);
        List<?> objectList = responseStrToList(responseBody);
        Map<String, Object> responseJson = new HashMap<>();

        double price = productRepository.findByName(searchText).getPrice();  //제품이름 가격 매칭
        double dollarToWon = exchangeRepository.findByName("dollar").getExchangeRate(); //환율
        double krPrice = dollarToWon * price* 10;
        boolean hot = true;
        System.out.println(krPrice);
        //double krPrice = 5;
        //Validator.ListIsEmpty(objectList);
        if (objectList.isEmpty()){
            responseJson.put("errorMessage","네이버 쇼핑 검색 결과가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        }
        List<Integer> lpriceList = new ArrayList<>(); //결과 리스트로 출력용 (테스트용)
        for (Object rawObj : objectList) {
            int lowestPrice = ObjectToInt(rawObj);
            lpriceList.add(lowestPrice);
            hot = CheckIfHot(lowestPrice,krPrice);
        }
        System.out.println(lpriceList);
        if(hot){
            System.out.println(searchText);
            responseJson.put("result", searchText); //핫딜이면 제품이름 그대로 출력
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
    }
    private int ObjectToInt(Object rawObj){
        JSONObject JsonLi = (JSONObject) rawObj;
        Object objLi = JsonLi.get("lprice");
        return Integer.parseInt((String) objLi);
    }
    private boolean CheckIfHot(int lowestPrice, Double krPrice){
        return !(krPrice > (double) lowestPrice);
    }
    private List<?> responseStrToList(String responseBody) throws ParseException {
        List<?> objectList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONObject JsonBodyFirstLevel = (JSONObject) parser.parse(responseBody);
        Object obj1 = JsonBodyFirstLevel.get("items");
        if (obj1.getClass().isArray()) {
            objectList = Arrays.asList((Object[]) obj1);
        } else if (obj1 instanceof Collection) {
            objectList = new ArrayList<>((Collection<?>) obj1);
        }
        return objectList;
    }
    public String SearchAPI(String searchText){
        String clientId = "KhJZhJGtfYYqxV5srTNB"; //애플리케이션 클라이언트 아이디
        String clientSecret = "VKYxFd5kWN"; //애플리케이션 클라이언트 시크릿

        String text = "";
        try {
            text = URLEncoder.encode(searchText, "UTF-8");  // 이 부분을 변경해야함
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text + "&display=5&sort=asc";    // JSON 결과 (shop 결과)
        //String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query="+ text; // XML 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);
        return responseBody;
    }

    public ResponseEntity<Map<String,Object>> SearchAll() throws ParseException {
        Map<String, Object> responseJson = new HashMap<>();

        List<String> hotProductList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product productEx : productList) {
            String productName = productEx.getName();
            String responseBody = SearchAPI(productName);
            List<?> objectList = responseStrToList(responseBody);

            double price = productEx.getPrice(); //제품이름 가격 매칭
            double dollarToWon = exchangeRepository.findByName("dollar").getExchangeRate(); //환율
            double krPrice = dollarToWon * price;
            boolean hot = true;
            System.out.println(krPrice);
            //double krPrice = 5;
            if (objectList.isEmpty()){
                hot = false;
                System.out.println("값이 없음!");
            }
            for (Object li : objectList) {
                int lowestPrice = ObjectToInt(li);
                hot = CheckIfHot(lowestPrice,krPrice);
            }
            if (hot) {
                hotProductList.add(productName);
            }
        }
        responseJson.put("result", hotProductList);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    private String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
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
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
