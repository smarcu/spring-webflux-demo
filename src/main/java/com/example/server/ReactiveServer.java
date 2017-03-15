package com.example.server;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveServer {

    @GetMapping(path = "/orientation", produces = "text/event-stream")
    public Mono<OrientationData> getOrientation() {

        return Mono.just(new OrientationData(0,0,0));
    }

}
