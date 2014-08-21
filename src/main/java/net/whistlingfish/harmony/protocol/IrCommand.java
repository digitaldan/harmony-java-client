package net.whistlingfish.harmony.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;

import static net.whistlingfish.harmony.Jackson.OBJECT_MAPPER;

public abstract class IrCommand extends OA {
    public IrCommand(String mimeType) {
        super(mimeType);
    }

    public String generateAction(String deviceId, String button) {
        try {
            return OBJECT_MAPPER.writeValueAsString(ImmutableMap.<String, Object> builder() //
                    .put("type", "IRCommand")
                    .put("deviceId", deviceId)
                    .put("command", button)
                    .build()).replaceAll(":", "::");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}