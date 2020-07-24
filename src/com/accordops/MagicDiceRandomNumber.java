package com.accordops;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.util.JacksonSerializer;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;
//import sun.nio.cs.StandardCharsets;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MagicDiceRandomNumber implements RequestStreamHandler {
    
    private Skill skill;
    private JacksonSerializer serializer;

    public MagicDiceRandomNumber() {
        skill = new StandardSkillBuilder()
                .addRequestHandler(new rngGenerator())
                .build();
        serializer = new JacksonSerializer();
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = IOUtils.toString(inputStream);
        RequestEnvelope deserializedRequest = serializer.deserialize(request, RequestEnvelope.class);
        ResponseEnvelope deserializedResponse = skill.invoke(deserializedRequest);
        byte[] response = serializer.serialize(deserializedResponse).getBytes(StandardCharsets.UTF_8);
        outputStream.write(response);
    }
}
