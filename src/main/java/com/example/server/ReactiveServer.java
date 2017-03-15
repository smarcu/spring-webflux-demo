package com.example.server;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
public class ReactiveServer {

    @GetMapping(path = "/orientation", produces = "text/event-stream")
    public Flux<OrientationData> getOrientation() {

        return Flux.just(new OrientationData(0,0,0),
                new OrientationData(1,0,0),
                new OrientationData(2, 0,0));
    }

}
