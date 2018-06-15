package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MessageDTO;
import json.customtypeadapter.DateTypeAdapter;

import java.util.GregorianCalendar;

public class JSONHandler {
    private static JSONHandler ourInstance = new JSONHandler();

    public static JSONHandler getInstance() {
        return ourInstance;
    }

    private final Gson gson = new GsonBuilder().registerTypeAdapter(GregorianCalendar.class, new DateTypeAdapter()).create();

    private JSONHandler() {

    }

    public String serializeMessageDTO(MessageDTO message) {
        return gson.toJson(message);
    }

    public MessageDTO deserializeMessageDTOJSON(String messageJSON) {
        return gson.fromJson(messageJSON, MessageDTO.class);
    }
}
