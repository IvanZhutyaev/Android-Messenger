package com.example.android_messenger.data;

import com.example.android_messenger.network.APIClient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketManager {
    private final OkHttpClient client = new OkHttpClient();
    private WebSocket webSocket;
    public void connect(int userId, WebSocketListener listener){
        String wsUrl = APIClient.getBaseWsUrl() + "ws?user_id=" +userId;
        Request request = new Request.Builder().url(wsUrl).build();
        webSocket = client.newWebSocket(request, listener);
    }
    public void sendRaw(String json){
        if (webSocket!=null) webSocket.send(json);
    }

    public void sendMessage(int chatId, String text){
        String payload = "{"
                +"\"action\":\"send_message\","
                +"\"chat_id\":"+chatId+","
                +"\"text\":\"" + text.replace("\"", "\\\"")+"\""
                +"}";
        sendRaw(payload);
    }

    public void close(){
        if(webSocket!=null){
            webSocket.close(1000, "bye");
            webSocket=null;
        }
    }
}
