package endpoint.decoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MessageDTO;
import json.JSONHandler;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageTextDecoder implements Decoder.Text<MessageDTO> {

    private final JSONHandler jsonHandler = JSONHandler.getInstance();

    @Override
    public MessageDTO decode(String s) throws DecodeException {
        return this.jsonHandler.deserializeMessageDTOJSON(s);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean willDecode(String s) {
        try {
            this.jsonHandler.deserializeMessageDTOJSON(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
