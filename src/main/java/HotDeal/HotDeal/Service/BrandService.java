package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Brand;
import HotDeal.HotDeal.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    public ResponseEntity<Map<String, Object>> Brand1(){
        Map<String, Object> responseJson = new HashMap<>();
        List<Brand> brandList = brandRepository.findByType(1);
        responseJson.put("result", brandList);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public ResponseEntity<Map<String, Object>> Brand2(){
        Map<String, Object> responseJson = new HashMap<>();
        List<Brand> brandList = brandRepository.findByType(2);
        responseJson.put("result", brandList);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
}
