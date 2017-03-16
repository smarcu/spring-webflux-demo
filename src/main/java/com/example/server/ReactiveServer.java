package com.example.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
public class ReactiveServer {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveServer.class);

    @GetMapping(path = "/orientation", produces = "text/event-stream")
    public Flux<OrientationData> getOrientation() {
        return Flux.generate(synchronousSink -> {
            Random random = new Random();
            synchronousSink.next(new OrientationData(random.nextInt(100),random.nextInt(100),random.nextInt(100)));
        });
    }

    @PutMapping(path = "/orientation")
    public void addOrientation(Flux<OrientationData> orientationDataFlux) {

        LOG.info("add orientation {}", orientationDataFlux);

        //return Mono.just(new OrientationData(0,0,0));
    }

}
