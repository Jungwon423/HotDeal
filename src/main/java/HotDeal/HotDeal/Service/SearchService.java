package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Repository.ProductRepository;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AliexpressAffiliateProductQueryRequest;
import com.taobao.api.response.AliexpressAffiliateProductQueryResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ProductRepository productRepository;

    public ResponseEntity<Map<String, Object>> searchProductAli(String keyword, Integer pageNumber) throws ApiException, ParseException {
        Map<String, Object> responseJson = new HashMap<>();
        TranslateService translateService = new TranslateService();
        String translatedKeyword = translateService.translateKoToEn(keyword);
        TaobaoClient client = new DefaultTaobaoClient("http://api.taobao.com/router/rest", "34272625", "9eb7f31401f66e9f636acbcc5e02e1d5");
        AliexpressAffiliateProductQueryRequest req = new AliexpressAffiliateProductQueryRequest();
        req.setKeywords(translatedKeyword);
        req.setTargetCurrency("KRW");
        req.setTargetLanguage("KO");
        req.setPageSize(10L);
        AliexpressAffiliateProductQueryResponse response = client.execute(req);
        List<AliexpressAffiliateProductQueryResponse.Product> products = new ArrayList<>(response.getRespResult().getResult().getProducts());
        Validator.validateNullEmptyList(products);

        List<Product> productList = new ArrayList<>();
        PriceComparisonService priceComparisonService = new PriceComparisonService();
        for (AliexpressAffiliateProductQueryResponse.Product now : products) {
            Product product = priceComparisonService.comparePrice(convert(now));
            if (product != null) productList.add(product);
        }
        responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
        responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
        responseJson.put("productCount", productList.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public Product convert(AliexpressAffiliateProductQueryResponse.Product now) {
        Product product = new Product();
        product.setName(now.getProductTitle());
        product.setImageUrl(now.getProductMainImageUrl());
        product.setLocale("kr");
        product.setPrice(Double.parseDouble(now.getTargetAppSalePrice()));
        product.setSubImageUrl(now.getProductSmallImageUrls());
        product.setCurrency("KRW");
        String rating = now.getEvaluateRate();
        if (rating != null) {
            rating = rating.substring(0, rating.length() - 1);
            double a = Double.parseDouble(rating);
            double b = 20d;
            a = a / b;
            a = (double) Math.round(a * 10d) / 10d;
            product.setRating(a);
        } else product.setRating(-1d);
        product.setMarketName("AliExpress");
        product.setLink(now.getPromotionLink());
        product.setDirect_shippingFee(0d);
        return product;
    }

    @Deprecated
    public ResponseEntity<Map<String, Object>> searchProduct(String keyword, Integer pageNumber) {
        Map<String, Object> responseJson = new HashMap<>();
        List<Product> products1 = productRepository.findByCategoryNameContaining(keyword);
        List<Product> products2 = productRepository.findByCategoryName2Containing(keyword);
        List<Product> products3 = productRepository.findByNameContaining(keyword);
        Set<Product> productSet = new HashSet<>(products1);
        productSet.addAll(products2);
        productSet.addAll(products3);

        if (Pattern.matches("^[a-zA-Z0-9]*$", keyword)) {    //영숫자
            TranslateService translateService = new TranslateService();
            String translatedKeyword = translateService.translate(keyword);
            List<Product> products4 = productRepository.findByCategoryNameContaining(translatedKeyword);
            List<Product> products5 = productRepository.findByCategoryName2Containing(translatedKeyword);
            List<Product> products6 = productRepository.findByNameContaining(translatedKeyword);
            productSet.addAll(products4);
            productSet.addAll(products5);
            productSet.addAll(products6);
        }
        List<Product> totalProduct = new ArrayList<>(productSet);
        List<Product> productList = sortProduct(totalProduct);
        responseJson.put("result", productList.subList((pageNumber - 1) * 10, Math.min(productList.size(), pageNumber * 10)));
        responseJson.put("totalPage", (productList.size() % 10 == 0) ? productList.size() / 10 : productList.size() / 10 + 1);
        responseJson.put("productCount", productSet.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public List<Product> sortProduct(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingInt(
                        Product::getReview).reversed())
                .collect(Collectors.toList());
    }
}
