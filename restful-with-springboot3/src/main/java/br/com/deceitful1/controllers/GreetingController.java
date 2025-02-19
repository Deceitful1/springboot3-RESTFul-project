package br.com.deceitful1.controllers;

import br.com.deceitful1.models.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController
{
    private static final String template = "Hello, I'm %s and I am %s years old!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Gabriel") String name, @RequestParam(value = "name2", defaultValue = "19") String name2)
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name, name2));
    }

}
