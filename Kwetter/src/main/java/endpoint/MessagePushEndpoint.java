package endpoint;

import domain.Message;
import dto.MessageDTO;
import endpoint.decoder.MessageTextDecoder;
import endpoint.encoder.MessageTextEncoder;
import service.MessagePushService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/messagepush", encoders = { MessageTextEncoder.class }, decoders = { MessageTextDecoder.class })
public class MessagePushEndpoint {

    @Inject
    private MessagePushService messagePushService;

    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        this.messagePushService.addSession(session);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        this.messagePushService.removeSession(session);
    }

    @OnMessage
    public void onMessage(Session session, MessageDTO msg) {
        System.out.println("MIAUW");
        this.messagePushService.sendUpdate((int) session.getUserProperties().get("userID"), msg);
    }

    @OnError
    public void onError(Session session, Throwable thr) {
        thr.printStackTrace();
    }
}
