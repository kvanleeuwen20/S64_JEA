package endpoint;

import dto.MessageDTO;
import endpoint.decoder.MessageTextDecoder;
import endpoint.encoder.MessageTextEncoder;
import service.MessagePushService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/messagepush/{userID}", encoders = { MessageTextEncoder.class }, decoders = { MessageTextDecoder.class })
public class MessagePushEndpoint {

    @Inject
    private MessagePushService messagePushService;

    @OnOpen
    public void onOpen(@PathParam("userID") int userID, Session session, EndpointConfig conf) {
        System.out.println("THIS SHIT OPEN");
        this.messagePushService.addSession(session, userID);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("THIS SHIT CLOSED");
        this.messagePushService.removeSession(session);
    }

    @OnMessage
    public void onMessage(@PathParam("userID") int userID, Session session, MessageDTO msg) {
        this.messagePushService.sendUpdate(userID, msg);
    }

    @OnError
    public void onError(Session session, Throwable thr) {
        thr.printStackTrace();
    }
}
