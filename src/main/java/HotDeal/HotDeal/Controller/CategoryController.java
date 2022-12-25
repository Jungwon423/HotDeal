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
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Deploy success");
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{categoryName}/list")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody @Valid Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("names")
    public ResponseEntity<Map<String, Object>> getCategoryNames() {
        return categoryService.getAllCategory();
    }
}
