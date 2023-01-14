package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LogController {

    private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");

    @Autowired
    private LogService logService;

    @GetMapping("/log")
    public void log() {
        logService.log();
    }
}