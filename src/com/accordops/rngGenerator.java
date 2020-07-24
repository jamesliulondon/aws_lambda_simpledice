package com.accordops;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class rngGenerator implements com.amazon.ask.dispatcher.request.handler.RequestHandler {
    private Random random = new Random(new Date().getTime());
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Number is " + random.nextInt(100) + 1;
        return handlerInput.getResponseBuilder().withSpeech(speechText).build();
    }
}
