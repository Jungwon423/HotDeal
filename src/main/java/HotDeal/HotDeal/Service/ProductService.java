package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Dto.GoodDto;
import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Dto.WishListDto;
import HotDeal.HotDeal.Exception.*;
import HotDeal.HotDeal.Repository.ProductRepository;
import HotDeal.HotDeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final HashMap<String, String> categoryMap = new HashMap<>() {
        {
            put("all", "홈");
            put("life_health", "생활/건강");
            put("duty-free", "면세점");
            put("travel_culture", "여행/문화");
            put("sports_leisure", "스포츠/레저");
            put("food", "식품");
            put("childbirth_parenting", "출산/육아");
            put("furniture_interior", "가구/인테리어");
            put("digital_consumer", "디지털/가전");
            put("cosmetics_beauty", "화장품/미용");
            put("fashion-accessories", "패션잡화");
            put("fashion-clothes", "패션의류");
        }
    };

    public ResponseEntity<Map<String, Object>> getProductDetail(String name) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findByName(name);

        if (product == null) {
            responseJson.put("result", "product not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
        } else {
            responseJson.put("result", product);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
    }

    public ResponseEntity<Map<String, Object>> getTop3ProductsByMarketName(String marketName) {
        Map<String, Object> responseJson = new HashMap<>();
        if (marketName.equals("all")) {
            responseJson.put("result", productRepository.findAll().subList(0, 3));
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }

        List<Product> productList = productRepository.findByMarketName(marketName);
        validateList(productList, marketName);
        if (productList == null) {
            responseJson.put("result", "MarketName = " + marketName + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else responseJson.put("result", productList.subList(0, Math.min(productList.size(), 3)));
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getAllProducts(Integer pageNumber) { // 모두 all
        Map<String, Object> responseJson = new HashMap<>();

        List<Product> productList = productRepository.findAll();
        responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
        responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
        responseJson.put("productCount", productList.size());

        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getProductsByCategoryAndMarket(String categoryName, String marketName, Integer pageNumber) {
        Map<String, Object> responseJson = new HashMap<>();
        String categoryNameKr = categoryMap.get(categoryName);
        if (categoryName.equals("all")) {
            return getProductsByMarketName(marketName, pageNumber);
        } else {
            if (marketName.equals("all")) {
                return getProductsByCategoryName(categoryNameKr, pageNumber);
            } else {
                List<Product> productList = productRepository.findByCategoryNameAndMarketName(categoryNameKr, marketName);
                if (productList == null) {
                    responseJson.put("result", "CategoryName = " + categoryNameKr + " 와 MarketName = " + marketName + "를 가지는 product가 없습니다");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
                } else {
                    responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
                    responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
                    responseJson.put("productCount", productList.size());
                }
                return ResponseEntity.status(HttpStatus.OK).body(responseJson);
            }
        }
    }

    public ResponseEntity<Map<String, Object>> getProductsByMarketName(String marketName, Integer pageNumber) { // categoryname이 all인 경우
        Map<String, Object> responseJson = new HashMap<>();
        if (marketName.equals("all")) {
            return getAllProducts(pageNumber);
        }
        List<Product> productList = productRepository.findByMarketName(marketName);
        if (productList == null) {
            responseJson.put("result", "MarketName = " + marketName + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else {
            responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
            responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
            responseJson.put("productCount", productList.size());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getProductsByCategoryName(String categoryName, Integer pageNumber) { // marketname이 all, categoryname이 all이 아닌 경우
        Map<String, Object> responseJson = new HashMap<>();
        List<Product> productList = productRepository.findByCategoryName(categoryName);
        if (productList == null) {
            responseJson.put("result", "categoryName = " + categoryName + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else {
            responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
            responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
            responseJson.put("productCount", productList.size());
        }
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

    public void plusCount(Product product) {
        product.setClickCount(product.getClickCount() + 1);
        productRepository.save(product);
    }

    public ResponseEntity<Map<String, Object>> setProductToWishlist(String userId, String productId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(IdNotFoundException::new);
        validateObject(product.getWishUserList());   //객체의 list값은 초기화 안하면 null값이 참조됨
        if (!product.getWishUserList().contains(userId)){ //제품 객체가 userId 안 갖고있으면
            product.getWishUserList().add(userId);  //제품객체에 userId 저장
        }
        productRepository.save(product);

        WishListDto wishListDto = WishListDto.from(product);
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        validateObject(user.getWishLists());

        if (!user.getWishLists().contains(wishListDto)){ //유저 객체가 wishList객체 안 갖고있으면
            user.getWishLists().add(wishListDto);  //유저 객체에 wishList객체 저장
        }
        userRepository.save(user);
        responseJson.put("message", "찜목록이 user 객체에 저장되었습니다.");
        responseJson.put("message2", "userID가 product 객체에 저장되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getCommentsByProduct(String productId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundException::new);
        validateObject(product.getComments());
        responseJson.put("result", product.getComments());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> recommendProduct(String userId, String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(IdNotFoundException::new);
        GoodDto good = GoodDto.from(product);
        validateObject(product.getGood());
        if (!product.getGood().contains(userId)){
            product.getGood().add(userId); //제품 good에 userId 추가
        }
        productRepository.save(product);

        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        validateObject(user.getGoods());

        if (!user.getGoods().contains(good)){
            user.getGoods().add(good);  //유저 goods에 good 추가
        }
        userRepository.save(user);
        responseJson.put("message", "추천 객체가 user 객체에 저장되었습니다.");
        responseJson.put("message2", "추천 유저 ID가 product 객체에 저장되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public void validateList(List<?> exList, String customString) {
        if (exList == null) {
            throw new CustomException(ErrorCode.LIST_IS_NULL);
        }
        if (exList.isEmpty()) {
            throw new CustomException(ErrorCode.LIST_IS_EMPTY);
        }
    }
    public void validateObject(Object exObject){
        if(exObject == null){
            throw new CustomException(ErrorCode.OBJECT_IS_NULL);
        }
    }
}
