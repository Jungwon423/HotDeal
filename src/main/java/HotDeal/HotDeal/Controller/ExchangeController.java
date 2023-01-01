package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.ExchangeRate;
import HotDeal.HotDeal.Service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("update")
    public ResponseEntity<Map<String, Object>> updateExchangeRate(ExchangeRate exchangeRate) {
        return exchangeService.updateExchangeRate(exchangeRate);
    }

    @GetMapping("today")
    public ResponseEntity<Map<String, Object>> getTodayExchangeRate(){
        return exchangeService.getTodayExchangeRate();
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllExchangeRate() {
        return exchangeService.getAllExchangeRate();
    }
}
