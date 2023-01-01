package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.Translate;
import HotDeal.HotDeal.Service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("translate")
@RequiredArgsConstructor
public class TranslateController {

    private final TranslateService translateService;

    @PostMapping("/sentence")
    public ResponseEntity<Map<String, Object>> translateSentence(@Valid @RequestBody Translate translate) throws ParseException {
        return translateService.translateSentence(translate);
    }
}