package endpoint.encoder;

import dto.MessageDTO;
import json.JSONHandler;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageTextEncoder implements Encoder.Text<MessageDTO> {

    private final JSONHandler jsonHandler = JSONHandler.getInstance();

    @Override
    public String encode(MessageDTO message) throws EncodeException {
        return this.jsonHandler.serializeMessageDTO(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
