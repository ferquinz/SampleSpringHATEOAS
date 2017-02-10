package com.example.hello;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping(path="/greeting", method= RequestMethod.GET)
    public HttpEntity<Greeting> greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name){

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).buy(name)).withRel("Buy"));
        greeting.add(linkTo(methodOn(GreetingController.class).sell(name)).withRel("Sell"));
        return new ResponseEntity<>(greeting, HttpStatus.OK);

    }

    @RequestMapping(path="/greeting/{name}/buy", method= RequestMethod.GET)
    public HttpEntity<Greeting> buy(@PathVariable(value = "name") String name){

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).buy(name)).withSelfRel());
        return new ResponseEntity<>(greeting, HttpStatus.OK);

    }

    @RequestMapping(path="/greeting/{name}/sell", method= RequestMethod.GET)
    public HttpEntity<Greeting> sell(@PathVariable(value = "name") String name){

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).sell("Default")).withRel("Default"));
        return new ResponseEntity<>(greeting, HttpStatus.OK);

    }


}
