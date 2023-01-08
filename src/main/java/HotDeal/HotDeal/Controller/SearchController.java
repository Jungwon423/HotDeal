package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/all")
    public ResponseEntity<Map<String, Object>> searchAll(@RequestBody Map<String,String> searchMap) throws ParseException {
        return searchService.Search(searchMap.get("name"));
    }
}
