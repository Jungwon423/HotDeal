package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Category;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Exception.CustomException;
import HotDeal.HotDeal.Exception.ErrorCode;
import HotDeal.HotDeal.Exception.UserNotFound;
import HotDeal.HotDeal.Repository.CategoryRepository;
import HotDeal.HotDeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static HotDeal.HotDeal.Exception.Validator.validateProductByCategoryId;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
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
            put("furniture_interior","가구/인테리어");
            put("digital_consumer","디지털/가전" );
            put("cosmetics_beauty","화장품/미용");
            put("fashion-accessories", "패션잡화");
            put("fashion-clothes", "패션의류");
        }
    };

    public ResponseEntity<Map<String, Object>> clickCategory(String categoryId) {
        CheckCategoryExist(categoryId); //위에 카테고리 맵을 설정해놓아서 IllegalArgumentException발생하는 것을 바꿔놓음
        String translatedCategory = categoryMap.get(categoryId);

        Map<String, Object> responseJson = new HashMap<>();
        Category category;
        boolean check = categoryRepository.findById(translatedCategory).isEmpty();
        validateProductByCategoryId(check);

        category = categoryRepository.findById(translatedCategory).get();
        plusCount(category);
        responseJson.put("result", category); //카테고리 객체
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> clickCategory(String categoryId, String userId) {
        CheckCategoryExist(categoryId); //위에 카테고리 맵을 설정해놓아서 IllegalArgumentException 발생하는 것을 바꿔놓음
        String translatedCategory = categoryMap.get(categoryId);

        Map<String, Object> responseJson = new HashMap<>();
        Category category;
        boolean check = categoryRepository.findById(translatedCategory).isEmpty();
        validateProductByCategoryId(check);

        category = categoryRepository.findById(translatedCategory).get();
        plusCount(category);

        User user = userRepository.findById(userId)   //유저에 카테고리 클릭 정보 전달
                .orElseThrow(UserNotFound::new);
        userPlusCount(categoryId, user);

        responseJson.put("result", category); //카테고리 객체
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public void userPlusCount(String id, User user){
        Map<String,Integer> userMap =  user.getProductCount();

        userMap.putIfAbsent(id, 0);  //없으면 categoryId에 put 0
        userMap.put(id, userMap.get(id)+1);
        user.setProductCount(userMap);
        userRepository.save(user);
    }

    public void plusCount(Category category){
        category.setClickCount(category.getClickCount() + 1);
        categoryRepository.save(category);
    }

    public void CheckCategoryExist(String categoryId){
        if(categoryMap.get(categoryId)==null){
            throw new CustomException(ErrorCode.CATEGORY_IS_NULL);
        }
    }
}
