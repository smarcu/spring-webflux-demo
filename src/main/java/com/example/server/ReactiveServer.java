package com.example.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Random;

@RestController
public class ReactiveServer {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveServer.class);

    @Bean
    private SubscribableChannel dataChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @GetMapping(path = "/orientation", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OrientationData> getOrientation() {

        LOG.info("get orientation - HTTP GET CALLED {}");

        return Flux.create( sink -> {
            FluxSink<OrientationData> fsink = sink.serialize();
            MessageHandler handler = msg -> {
                LOG.info("MESSAGE HANDLER {}", msg);
                fsink.next(OrientationData.class.cast(msg.getPayload()));
            };
            fsink.setCancellation(() -> dataChannel().unsubscribe(handler));
            dataChannel().subscribe(handler);
        });

    }

    @PutMapping(path = "/orientation")
    public void addOrientation(OrientationData orientationData) {

        LOG.info("add orientation - HTTP PUT CALLED {}", orientationData);

        dataChannel().send(new GenericMessage<>(orientationData));

    }

    @PutMapping(path = "/orientation-flux")
    public void addOrientationFlux(Flux<OrientationData> orientationDataFlux) {

        LOG.info("add orientation - HTTP PUT CALLED {}", orientationDataFlux);

        orientationDataFlux.doOnEach(msg -> {
            LOG.info("add orientation - MSG RECEIVED {}", msg);
            dataChannel().send(new GenericMessage<>(msg.get()));
        });

    }

}
