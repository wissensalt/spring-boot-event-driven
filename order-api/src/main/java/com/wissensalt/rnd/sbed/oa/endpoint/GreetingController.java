package com.wissensalt.rnd.sbed.oa.endpoint;

import com.wissensalt.rnd.sbed.sd.Greeting;
import com.wissensalt.rnd.sbed.oa.service.GreetingService;
import lombok.RequiredArgsConstructor;
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
@RestController
public class GreetingController {
    private final GreetingService greetingService;

    @GetMapping("/greeting")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greeting(@RequestParam("message") String message) {
        Greeting greeting = Greeting.builder().message(message).timeStamp(System.currentTimeMillis()).build();
        greetingService.sendGreeting(greeting);

    }
}
