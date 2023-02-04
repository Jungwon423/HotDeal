package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.CategoryService;
import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("{categoryName}/{marketName}/{pageNumber}/list")
    public ResponseEntity<Map<String, Object>> getProductsByCategoryAndMarket(@PathVariable("categoryName") String categoryName, @PathVariable("marketName") String marketName, @PathVariable("pageNumber") Integer pageNumber) {
        return productService.getProductsByCategoryAndMarket(categoryName, marketName, pageNumber);
    }

    @PostMapping("{categoryName}/{pageNumber}/list")
    public ResponseEntity<Map<String, Object>> getProductsByCategoryAndMarkets(@PathVariable("categoryName") String categoryName,@PathVariable("pageNumber") Integer pageNumber, @RequestBody Map<String, Boolean> marketMap) {
        return productService.getProductsByCategoryAndMarkets(categoryName, pageNumber, marketMap);
    }

    @GetMapping("{marketName}/top3")
    public ResponseEntity<Map<String, Object>> getTop3ProductsByMarket(@PathVariable("marketName") String marketName) {
        return productService.getTop3ProductsByMarketName(marketName);
    }

    @PostMapping("{categoryId}/click")
    public ResponseEntity<Map<String, Object>> clickCategory(HttpServletRequest request, @PathVariable String categoryId) {
        String userId = (String) request.getAttribute("userId");
        if (userId==null){
            return categoryService.clickCategory(categoryId);
        }else return categoryService.clickCategory(categoryId, userId);
    }

}
