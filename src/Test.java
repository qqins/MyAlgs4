import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class.getName());

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("ingo");
        logger.warn("warn");
        logger.error("error");
    }
}
