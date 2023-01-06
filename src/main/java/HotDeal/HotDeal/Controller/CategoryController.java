package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.Category;
import HotDeal.HotDeal.Service.CategoryService;
import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("test")
    public  ResponseEntity<String> test() {
        return ResponseEntity.ok("Deploy Success");
    }

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody @Valid Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("{categoryName}/{marketName}/list")
    public ResponseEntity<Map<String, Object>> getProductsByCategoryAndMarket(@PathVariable("categoryName")  String categoryName, @PathVariable("marketName") String marketName) {
        return productService.getProductsByCategoryAndMarket(categoryName, marketName);
    }

    @PostMapping("{categoryId}/click")
    public ResponseEntity<Map<String, Object>> clickCategory(@PathVariable String categoryId) {
        return categoryService.clickCategory(categoryId);
    }

}
