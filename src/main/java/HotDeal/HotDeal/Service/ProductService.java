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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static HotDeal.HotDeal.Exception.Validator.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final HashMap<String, String> categoryMap = new HashMap<>() {
        {
            put("all", "전체");
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
        validateNullObject(product);

        responseJson.put("result", product);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getTop3ProductsByMarketName(String marketName) {
        Map<String, Object> responseJson = new HashMap<>();
        if (marketName.equals("all")) {
            List<Product> Top3Products = sortProduct(productRepository.findAll()).subList(0,3);
            responseJson.put("result", Top3Products);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }

        List<Product> productList = sortProduct(productRepository.findByMarketName(marketName));
        //validateNullEmptyList(productList);
        responseJson.put("result", productList.subList(0, Math.min(productList.size(), 3)));
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getAllProducts(Integer pageNumber) { // 모두 all
        Map<String, Object> responseJson = new HashMap<>();

        List<Product> productList = sortProduct(productRepository.findAll());
        //validateNullEmptyList(productList);
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
                List<Product> productList = sortProduct(productRepository.findByCategoryNameAndMarketName(categoryNameKr, marketName));
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

    public ResponseEntity<Map<String, Object>> getProductsByCategoryAndMarkets(String categoryName, Integer pageNumber, Map<String,Boolean> marketMap) {
        Map<String, Object> responseJson = new HashMap<>();
        validateNullObject(marketMap);
        String categoryNameKr = categoryMap.get(categoryName);
        List<Product> newProductList;
        if (categoryName.equals("all"))
            newProductList = sortProduct(getProductListsByMarketCheck("전체", marketMap));
        else
            newProductList = sortProduct(getProductListsByMarketCheck(categoryNameKr, marketMap));
        //validateNullEmptyList(newProductList);
        responseJson.put("result", newProductList.subList((pageNumber - 1) * 10, Math.min(newProductList.size(), pageNumber * 10)));
        responseJson.put("totalPage", (newProductList.size() % 10 == 0) ? newProductList.size() / 10 : newProductList.size() / 10 + 1);
        responseJson.put("productCount", newProductList.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public List<Product> getProductListsByMarketCheck(String categoryNameKr, Map<String,Boolean> marketMap){
        Set<String> marketList = marketMap.keySet();   //marketList는 set임
        Set<Product> productSet = new HashSet<>();
        if (categoryNameKr.equals("전체")) {
            for (String market : marketList) {
                if (marketMap.get(market)) {
                    List<Product> productList = productRepository.findByMarketName(market);
                    productSet.addAll(productList);
                }
            }
        }
        else {
            for (String market : marketList) {
                if (marketMap.get(market)) {
                    List<Product> productList = productRepository.findByCategoryNameAndMarketName(categoryNameKr, market);
                    productSet.addAll(productList);
                }
            }
        }
        return new ArrayList<>(productSet);
    }

    public ResponseEntity<Map<String, Object>> getProductsByMarketName(String marketName, Integer pageNumber) { // categoryname이 all인 경우
        Map<String, Object> responseJson = new HashMap<>();
        if (marketName.equals("all")) {
            return getAllProducts(pageNumber);
        }
        List<Product> productList = sortProduct(productRepository.findByMarketName(marketName));
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
        List<Product> productList = sortProduct(productRepository.findByCategoryName(categoryName));
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
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);

        plusCount(product);
        responseJson.put("result", product); // Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public ResponseEntity<Map<String, Object>> clickProduct(String productId, String userId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        plusCount(product);

        User user = userRepository.findById(userId)   //유저에 제품 클릭 정보 전달
                .orElseThrow(UserNotFound::new);
        userPlusCount(productId, user);

        responseJson.put("result", product); // Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public void userPlusCount(String id, User user){
        Map<String,Integer> userMap =  user.getProductCount();

        userMap.putIfAbsent(id, 0);  //없으면 productId에 put 0
        userMap.put(id, userMap.get(id)+1);
        user.setProductCount(userMap);
        userRepository.save(user);
    }

    public void plusCount(Product product) {
        product.setClickCount(product.getClickCount() + 1);
        productRepository.save(product);
    }

    public ResponseEntity<Map<String, Object>> setProductToWishlist(String userId, String productId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        boolean checkIfWish = product.getWishUserList().contains(userId);
        if (!checkIfWish){
            wish(userId,product);
            User user = userRepository.findById(userId)
                    .orElseThrow(UserNotFound::new);
            responseJson.put("wishList", user.getWishLists());
            responseJson.put("users",product.getWishUserList());
            responseJson.put("checked", true);
            responseJson.put("message", "찜목록이 user 객체에 저장되었습니다.");
            responseJson.put("message2", "userID가 product 객체에 저장되었습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
        else{                  //추가되어 있으므로 삭제 진행
            unwish(userId,product);
            User user = userRepository.findById(userId)
                    .orElseThrow(UserNotFound::new);
            responseJson.put("wishList", user.getWishLists());
            responseJson.put("users",product.getWishUserList());
            responseJson.put("checked", false);
            responseJson.put("message","유저정보에서 찜목록이 삭제되었습니다");
            responseJson.put("message2","제품정보에서 찜목록이 삭제되었습니다");
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
        }
    }
    public void wish(String userId, Product product) {
        WishListDto wishListDto = WishListDto.from(product);
        //validateNullList(product.getWishUserList());   //객체의 list값은 초기화 안하면 null값이 참조됨
        if (!product.getWishUserList().contains(userId)){ //제품 객체가 userId 안 갖고있으면
            product.getWishUserList().add(userId);  //제품객체에 userId 저장
        }
        productRepository.save(product);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        if (!user.getWishLists().contains(wishListDto)){ //유저 객체가 wishList객체 안 갖고있으면
            user.getWishLists().add(wishListDto);  //유저 객체에 wishList객체 저장
        }
        userRepository.save(user);
    }

    public void unwish(String userId, Product product) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        List<WishListDto> wishlists = user.getWishLists();
        wishlists.removeIf(wishlist -> Objects.equals(wishlist.getName(), product.getName()));

        user.setWishLists(wishlists);
        userRepository.save(user);

        product.getWishUserList().remove(userId);
        productRepository.save(product);
    }

    public ResponseEntity<Map<String, Object>> getCommentsByProduct(String productId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        responseJson.put("result", product.getComments());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> recommendProduct(String userId, String productId) {
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        product.setName(encoding(product.getName()));
        boolean checkIfGood = product.getGood().contains(userId);
        if (!checkIfGood) {
            recommend(userId,product);
            responseJson.put("message", "추천 객체가 user 객체에 저장되었습니다.");
            responseJson.put("message2", "추천 유저 ID가 product 객체에 저장되었습니다.");
        }
        else{                    //good일 때는 추가되어 있으므로 삭제 진행
            disrecommend(userId,product);
            responseJson.put("message", "유저정보에서 추천목록 삭제되었습니다");
            responseJson.put("message2", "제품정보에서 추천목록이 삭제되었습니다");
        }
        responseJson.put("users",product.getGood());
        responseJson.put("recommendChecked", !checkIfGood);
        responseJson.put("product",product);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public String encoding(String beforeEncode){
        String AfterEncode;
        AfterEncode = beforeEncode.replaceAll("\\.","%2E");
        AfterEncode = AfterEncode.replaceAll("/","%2E");
        return AfterEncode;
    }
    public void recommend(String userId, Product product){
        GoodDto goodProduct = GoodDto.from(product);

        if (!product.getGood().contains(userId)) {
            product.getGood().add(userId); //제품 good에 userId 추가
        }
        productRepository.save(product);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        List<GoodDto> goods = user.getGoods();
        if (!goods.contains(goodProduct)) {
            goods.add(goodProduct);  //유저 goods에 good 추가
        }
        userRepository.save(user);
    }

    public void disrecommend(String userId, Product product){
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        List<GoodDto> goods = user.getGoods();
        goods.removeIf(good -> Objects.equals(good.getName(), product.getName()));
        user.setGoods(goods);
        userRepository.save(user);

        product.getGood().remove(userId);
        productRepository.save(product);
    }
    public ResponseEntity<Map<String, Object>> disrecommendProduct(String userId, String productId){
        Map<String, Object> responseJson = new HashMap<>();
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        boolean checkIfBad = product.getBad().contains(userId);
        if (!checkIfBad) {
            product.getBad().add(userId); //제품 Bad에 userId 추가
            responseJson.put("message","유저 id를 제품 비추천목록에 추가");
        }else {
            product.getBad().remove(userId);
            responseJson.put("message","유저 id를 제품 비추천목록에서 삭제");
        }
        productRepository.save(product);
        responseJson.put("users",product.getBad());
        responseJson.put("disrecommendChecked",!checkIfBad);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> searchProduct(String keyword, Integer pageNumber){
        Map<String, Object> responseJson = new HashMap<>();
        List<Product> products1= productRepository.findByCategoryNameContaining(keyword);
        List<Product> products2 = productRepository.findByCategoryName2Containing(keyword);
        List<Product> products3= productRepository.findByNameContaining(keyword);
        Set<Product> productSet = new HashSet<>(products1);
        productSet.addAll(products2);
        productSet.addAll(products3);

        if(Pattern.matches("^[a-zA-Z0-9]*$", keyword)){    //영숫자
            TranslateService translateService = new TranslateService();
            String translatedKeyword = translateService.translate(keyword);
            List<Product> products4= productRepository.findByCategoryNameContaining(translatedKeyword);
            List<Product> products5 = productRepository.findByCategoryName2Containing(translatedKeyword);
            List<Product> products6= productRepository.findByNameContaining(translatedKeyword);
            productSet.addAll(products4);
            productSet.addAll(products5);
            productSet.addAll(products6);
        }
        List<Product> totalProduct = new ArrayList<>(productSet);
        List<Product> productList = sortProduct(totalProduct);
        responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
        responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
        responseJson.put("productCount",productSet.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public List<Product> sortProduct(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingInt(
                        Product::getReview).reversed())
                .collect(Collectors.toList());
    }
}
