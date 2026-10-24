package com.swaglabs.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DataDriven {

    public static JsonObject jsonReader() {

        InputStream stream = DataDriven.class
                .getClassLoader()
                .getResourceAsStream("testData.json");

        if (stream == null) {
            throw new IllegalStateException(
                    "testData.json was not found in src/test/resources");
        }

        try (InputStreamReader reader =
                     new InputStreamReader(
                             stream, StandardCharsets.UTF_8)) {

            return JsonParser.parseReader(reader)
                    .getAsJsonObject();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Could not read testData.json", e);
        }
    }
}
