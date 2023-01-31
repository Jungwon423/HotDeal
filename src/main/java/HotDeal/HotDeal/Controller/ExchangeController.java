package HotDeal.HotDeal.Controller;

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

    @GetMapping("{name}")
    public ResponseEntity<Map<String, Object>> getTodayExchangeRate(@PathVariable String name) {
        return exchangeService.getExchangeRateByName(name);
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> getAllExchangeRate() {
        return exchangeService.getAllExchangeRate();
    }
}
