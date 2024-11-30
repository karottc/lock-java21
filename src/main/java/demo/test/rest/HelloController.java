package demo.test.rest;

import demo.test.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author
 * @date 2024-11-29 22:35
 */
@RestController
@RequestMapping("/demo/")
public class HelloController {

    private static final AtomicInteger count = new AtomicInteger(0);

    private Logger logger = Logger.getLogger(HelloController.class.getName());


    @Autowired
    private TestMapper mapper;


    @GetMapping(value = "sleep")
    public String sleep() {
        int seq = count.getAndIncrement();
        logger.info("get sleep req: " + seq);
        mapper.sleep(ThreadLocalRandom.current().nextInt(1, 3));
        return "sleep-%s-%s, %s".formatted(seq, mapper.query(), Thread.currentThread());
    }

    @GetMapping(value = "hello")
    public String hello() {
        int seq = count.getAndIncrement();
        logger.info("get hello req: " + seq);
        return "hello-%s-%s, %s".formatted(seq, mapper.query2(), Thread.currentThread());
    }

}
