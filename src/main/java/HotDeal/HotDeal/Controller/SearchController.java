package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/name")
    public ResponseEntity<Map<String, Object>> searchByName(@RequestBody Map<String,String> searchMap) throws ParseException {
        return searchService.SearchByName(searchMap.get("name"));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> searchAll() throws ParseException {
        return searchService.SearchAll();
    }
}
