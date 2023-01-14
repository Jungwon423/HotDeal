package HotDeal.HotDeal.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void log() {
        String name = "spring";

        //  {}는 쉼표 뒤에 파라미터가 치환되는 것
        logger.error("error log={}",name);
        logger.warn("warn log={}",name);
        logger.info("info log={}",name);
        logger.debug("debug log={}",name);
        logger.trace("trace log={}",name);
        logger.trace("trace log " + name);
    }
}