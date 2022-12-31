package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.ExchangeRate;
import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("save")
    public ResponseEntity<Map<String, Object>> saveExchangeRate(ExchangeRate exchangeRate) {
        return exchangeService.saveExchangeRate(exchangeRate);
    }
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllExchangeRate() {
        return exchangeService.getAllExchangeRate();
    }
}
