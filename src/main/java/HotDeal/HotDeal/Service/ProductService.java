package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity<Map<String, Object>> getAllProducts() { // 모두 all
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("result", productRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String,Object>> getProductsByCategoryAndMarket(String categoryName, String marketName){
        Map<String, Object> responseJson = new HashMap<>();
        if (categoryName.equals("all")){
            return getProductsByMarketName(marketName);
        }
        else{
            if(marketName.equals("all")){
                return getProductsByCategoryName(categoryName);
            }
            else {
                List<Product> productList = productRepository.findByCategoryNameAndMarketName(categoryName, marketName);
                if (productList == null) {
                    responseJson.put("result", "CategoryName = " + categoryName +" 와 MarketName = " + marketName + "를 가지는 product가 없습니다");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
                }
                else responseJson.put("result", productList);
                return ResponseEntity.status(HttpStatus.OK).body(responseJson);
            }
        }
    }

    public ResponseEntity<Map<String,Object>> getProductsByMarketName(String marketName){ // categoryname이 all인 경우
        Map<String, Object> responseJson = new HashMap<>();
        if (marketName.equals("all")){
            return getAllProducts();
        }
        List<Product> productList = productRepository.findByMarketName(marketName);
        if (productList == null) {
            responseJson.put("result", "MarketName = " + marketName + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        }
        else responseJson.put("result", productList);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public ResponseEntity<Map<String, Object>> getProductsByCategoryName(String categoryName) { // marketname이 all, cateogyrname이 all이 아닌 경우
        Map<String, Object> responseJson = new HashMap<>();
        List<Product> productList = productRepository.findByCategoryName(categoryName);
        if (productList == null) {
            responseJson.put("result", "categoryName = " + categoryName + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        }
        else responseJson.put("result", productList);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> clickProduct(String productId) {

        Map<String, Object> responseJson = new HashMap<>();
        Product product;

        if (productRepository.findById(productId).isEmpty()) {
            responseJson.put("errorMessage", "productId = " + productId + "를 가지는 product가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else product = productRepository.findById(productId).get();
        plusCount(product);
        responseJson.put("result", product); // Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public void plusCount(Product product){
        product.setClickCount(product.getClickCount() + 1);
        productRepository.save(product);
    }
}
