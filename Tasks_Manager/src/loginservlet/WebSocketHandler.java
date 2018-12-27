package loginservlet;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Websocket Endpoint implementation class WebSocketHandler */

@ServerEndpoint("/WebSocketHandler")

public class WebSocketHandler {
    
    public WebSocketHandler() {
        super();
        // TODO Auto-generated constructor stub
    }
@OnOpen
public void onOpen(Session s,String a,EndpointConfig end)
{
	
	
}
@OnMessage
public void onMessage(Session s,String msg)
{
	
		
}
@OnClose
public void onClose(Session s,CloseReason reason)
{
	
	
}








}
