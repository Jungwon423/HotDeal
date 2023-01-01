package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.Translate;
import HotDeal.HotDeal.Service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("translate")
@RequiredArgsConstructor
public class TranslateController {

    private final TranslateService translateService;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> translateSentence(@RequestBody @Valid Translate translate) {
        return translateService.translateSentence(translate);
    }
}