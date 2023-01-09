package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.ExchangeRate;
import HotDeal.HotDeal.Repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public ResponseEntity<Map<String, Object>> getExchangeRateByName(String name) {
        Map<String, Object> responseJson = new HashMap<>();
        if (exchangeRepository.findById(name).isEmpty()){
            responseJson.put("errorMessage", "DB에 환율 정보가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        }
        else {
            responseJson.put("result", exchangeRepository.findById(name));
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllExchangeRate() {

        Map<String, Object> responseJson = new HashMap<>();

        List<ExchangeRate> exchangeRates = exchangeRepository.findAll();

        if (exchangeRates.isEmpty()) {
            responseJson.put("errorMessage", "DB에 환율 정보가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else {
            responseJson.put("result", exchangeRates);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
    }

    public Double ObjectToDouble(Object object){
        String StringRate = (String) object;
        String regexText = StringRate.replaceAll(",", "");
        return Double.parseDouble(regexText);
    }
    public void APIReader(ExchangeRate exchangeRate) {
        JSONParser parser = new JSONParser();
        String AuthKey = "e4lofYKs4QdnWUW6eAoSHTAzJkNncSGf";
        String SearchDate = "20230103";
        String dataType = "AP01";
        String apiURL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + AuthKey + "&searchdate=" + SearchDate + "&data=" + dataType;
        //한국수출입은행 환율
        try {
            URL oracle = new URL(apiURL);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                for (Object o : a) {
                    JSONObject JSONRate = (JSONObject) o;
                    //System.out.println((String) JSONRate.get("cur_nm"));
                    if (("미국 달러").equals(JSONRate.get("cur_nm"))){
                        Double todayExchangeRate = ObjectToDouble(JSONRate.get("deal_bas_r"));
                        exchangeRate.setName("오늘의 환율");
                        exchangeRate.setExchangeRate(todayExchangeRate);
//                    환율 api 데이터 예시에 대한 것은 핫딜 노션 환율 API 관련 참조
//                    System.out.println("bkpr>>" + (String) JSONRate.get("bkpr"));
                        System.out.println("deal_bas_r>>" + JSONRate.get("deal_bas_r"));  //매매 기준율 이거 쓰면 될듯 함
                    }
                }
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
