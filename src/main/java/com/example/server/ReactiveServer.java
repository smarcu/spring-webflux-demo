package com.example.server;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
public class ReactiveServer {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveServer.class);

    private SubscribableChannel outDataChannel = MessageChannels.publishSubscribe().get();
    private SubscribableChannel inDataChannel = MessageChannels.publishSubscribe().get();
    
    @PostConstruct
    public void init() {
    	LOG.info("IN CHANNEL: {} OUT CHANNEL: {}", inDataChannel, outDataChannel);
    	
    	// SETUP FLUX in -> out
    	
    	Flux<OrientationData> flux = Flux.create(emitter -> {
    		inDataChannel.subscribe(msg -> emitter.next( OrientationData.class.cast( msg.getPayload() )));
    	}, FluxSink.OverflowStrategy.LATEST);

    	//flux = flux.buffer(50).map(OrientationData::average);  
    	
    	ConnectableFlux<OrientationData> hot = flux.publish();
    	
    	hot.subscribe(orientationData -> outDataChannel.send(new GenericMessage<>(orientationData)));
    	
    	hot.connect();
    	
    }
    
    
    @GetMapping(path = "/orientation", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OrientationData> getOrientation() {

        LOG.info("get orientation - HTTP GET CALLED");

        return Flux.create( sink -> {
            FluxSink<OrientationData> fsink = sink.serialize();
            MessageHandler handler = msg -> fsink.next(OrientationData.class.cast(msg.getPayload()));
            fsink.setCancellation(() -> outDataChannel.unsubscribe(handler));
            outDataChannel.subscribe(handler);
        });

    }

    @PutMapping(path = "/orientation")
    public void addOrientation(@RequestBody OrientationData orientationData) {

        LOG.info("add orientation - HTTP PUT CALLED {}", orientationData);

        inDataChannel.send(new GenericMessage<>(orientationData));

    }


}
