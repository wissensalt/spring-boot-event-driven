package com.wissensalt.rnd.sbed.oa.endpoint;

import com.wissensalt.rnd.sbed.sd.Greeting;
import com.wissensalt.rnd.sbed.oa.producer.GreetingProducer;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@RequiredArgsConstructor
@Slf4j
@RestController
public class GreetingController {
    private final GreetingProducer greetingProducer;

    @GetMapping("/greeting")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greeting(@RequestParam("message") String message) {
        Greeting greeting = Greeting.builder().message(message).timeStamp(System.currentTimeMillis()).build();
        try {
            greetingProducer.produceMessage(greeting);
        } catch (ProducerException e) {
            log.error("Error produce greeting message {}", e.toString());
        }
    }
}
