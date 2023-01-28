package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Category;
import HotDeal.HotDeal.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final HashMap<String, String> categoryMap = new HashMap<>() {
        {
            put("all", "홈");
            put("life_health", "생활/건강");
            put("duty-free", "면세점");
            put("travel_culture", "여행/문화");
            put("sports_leisure", "스포츠/레저");
            put("food", "식품");
            put("childbirth_parenting", "출산/육아");
            put("furniture_interior","가구/인테리어");
            put("digital_consumer","디지털/가전" );
            put("cosmetics_beauty","화장품/미용");
            put("fashion-accessories", "패션잡화");
            put("fashion-clothes", "패션의류");
        }
    };

    public ResponseEntity<Map<String, Object>> clickCategory(String categoryId) {
        String translatedCategory = categoryMap.get(categoryId);

        Map<String, Object> responseJson = new HashMap<>();
        Category category;

        if (categoryRepository.findById(translatedCategory).isEmpty()) {
            responseJson.put("errorMessage", "categoryId = " + translatedCategory + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else category = categoryRepository.findById(translatedCategory).get();
        plusCount(category);
        responseJson.put("result", category); //Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public void plusCount(Category category){
        category.setClickCount(category.getClickCount() + 1);
        categoryRepository.save(category);
    }
}
