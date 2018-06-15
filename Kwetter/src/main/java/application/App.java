package application;

import endpoint.MessagePushEndpoint;

import javax.websocket.server.ServerEndpointConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application {

}
