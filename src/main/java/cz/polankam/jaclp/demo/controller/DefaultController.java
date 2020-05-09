package cz.polankam.jaclp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@RestController
@RequiredArgsConstructor
public class DefaultController {

    @GetMapping
    public String all() {
        return "I am alive!";
    }
}
