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

    public ResponseEntity<Map<String, Object>> clickCategory(String categoryId) {

        Map<String, Object> responseJson = new HashMap<>();
        Category category;

        System.out.println(categoryRepository.findById(categoryId).getClass().getName());
        if (categoryRepository.findById(categoryId).isEmpty()) {
            responseJson.put("errorMessage", "categoryId = " + categoryId + "를 가지는 category가 없습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
        } else category = categoryRepository.findById(categoryId).get();
        plusCount(category);
        responseJson.put("result", category); //Product 페이지 정보를 가져온다. (link 가져오고 상품디테일)
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public void plusCount(Category category){
        category.setClickCount(category.getClickCount() + 1);
        categoryRepository.save(category);
    }
}
