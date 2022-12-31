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
    //private ExchangeRate exchangeRate;

    public static void crawling(){
        ExchangeRate exchangeRate;
        final String crawlingUrl = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EB%8B%AC%EB%9F%AC+%ED%99%98%EC%9C%A8&oquery=%ED%99%98%EC%9C%A8&tqi=hI8yWsp0YihssdfcMcZssssssgN-226712";
        Connection conn = Jsoup.connect(crawlingUrl);

        try {
            Document doc = conn.get();
            Elements elements = doc.getElementsByClass("spt_con dw");
            Elements ele = elements.get(0).getElementsByTag("strong");
            String text = ele.text();
            System.out.println(text);
            //exchangeRate.setExchangeRate(Double.valueOf(text));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<Map<String, Object>> saveExchangeRate(ExchangeRate exchangeRate) {
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("Message", "DB에 환율 추가 성공");
        exchangeRepository.save(exchangeRate);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }


    public ResponseEntity<Map<String, Object>> getAllExchangeRate() {

        Map<String, Object> responseJson = new HashMap<>();

        List<ExchangeRate> exchangeRates = exchangeRepository.findAll();

        if (exchangeRates.isEmpty()) {
            responseJson.put("errorMessage", "DB에 환율 정보가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else {
            responseJson.put("result", exchangeRates); //Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
    }
}
