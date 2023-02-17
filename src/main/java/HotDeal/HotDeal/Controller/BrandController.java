package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    @GetMapping("brand1")
    public ResponseEntity<Map<String, Object>> brand1() {
        return brandService.Brand1();
    }
    @GetMapping("brand2")
    public ResponseEntity<Map<String, Object>> brand2() {
        return brandService.Brand2();
    }
}
