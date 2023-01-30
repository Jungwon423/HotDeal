package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.CategoryService;
import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{marketName}/top3")
    public ResponseEntity<Map<String, Object>> getTop3ProductsByMarket(@PathVariable("marketName") String marketName) {
        return productService.getTop3ProductsByMarketName(marketName);
    }

    @PostMapping("{categoryId}/click")
    public ResponseEntity<Map<String, Object>> clickCategory(@PathVariable String categoryId) {
        return categoryService.clickCategory(categoryId);
    }

}
