package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.ExchangeRate;
import HotDeal.HotDeal.Repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public void crawling(ExchangeRate exchangeRate){
        final String crawlingUrl = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EB%8B%AC%EB%9F%AC+%ED%99%98%EC%9C%A8&oquery=%ED%99%98%EC%9C%A8&tqi=hI8yWsp0YihssdfcMcZssssssgN-226712";
        Connection conn = Jsoup.connect(crawlingUrl);

        try {
            Document doc = conn.get();
            Elements elements = doc.getElementsByClass("spt_con up");
//            Elements elements = doc.getElementsByClass("spt_con dw");
            Elements ele = elements.get(0).getElementsByTag("strong");
            Double todayExchangeRate = elementToDouble(ele);
            exchangeRate.setName("오늘의 환율");
            exchangeRate.setExchangeRate(todayExchangeRate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double elementToDouble(Elements ele){
        String text = ele.text();
        String regexText = text.replaceAll(",", "");
        return Double.parseDouble(regexText);
    }

    public ResponseEntity<Map<String, Object>> updateExchangeRate(ExchangeRate exchangeRate) {
        Map<String, Object> responseJson = new HashMap<>();
        crawling(exchangeRate);

        if (exchangeRepository.findByName("오늘의 환율").isEmpty()){
            exchangeRepository.save(exchangeRate);
        }
        else if(exchangeRepository.findByName("오늘의 환율").size()==1){
            ExchangeRate updateExchange = exchangeRepository.findByName("오늘의 환율").get(0);
            updateExchange.setExchangeRate(exchangeRate.getExchangeRate());
            //updateExchange.setExchangeRate(1000d); //테스트용
            exchangeRepository.save(updateExchange);
        }
        else{
            responseJson.put("errorMessage", "DB에 오늘의 환율 정보가 여러개 있습니다.");
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseJson);
        }
        responseJson.put("Message", "DB에 오늘의 환율 업데이트");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getTodayExchangeRate() {
        Map<String, Object> responseJson = new HashMap<>();
        if (exchangeRepository.findByName("오늘의 환율").isEmpty()){
            responseJson.put("errorMessage", "DB에 환율 정보가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        }
        else if(exchangeRepository.findByName("오늘의 환율").size()==1){
            ExchangeRate todayExchangeRate = exchangeRepository.findByName("오늘의 환율").get(0);
            responseJson.put("result", todayExchangeRate);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
        else{
            responseJson.put("errorMessage", "DB에 오늘의 환율 정보가 여러개 있습니다.");
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseJson);
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
}
