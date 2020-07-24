package com.accordops;

import static org.junit.jupiter.api.Assertions.*;
//import org.apache.onami.logging.log4j2.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.util.StringInputStream;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MagicDiceHandlerTest {

    @org.junit.Test
    public void handleRequest() {
        MagicDiceRandomNumber handler = new MagicDiceRandomNumber();

        try {
            StringInputStream inputStream = new StringInputStream(TestData.getRequestContent());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            handler.handleRequest(inputStream, outputStream, null);
            String respond = outputStream.toString();
            Assert.assertNotNull(respond);
            System.out.println(respond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}