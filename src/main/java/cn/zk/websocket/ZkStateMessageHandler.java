package cn.zk.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <br/>
 * Created on 2018/6/28 14:47.
 *
 * @author zhubenle
 */
@Slf4j
@Component
public class ZkStateMessageHandler extends AbstractWebSocketHandler {

    private final static ConcurrentHashMap<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        SESSION_MAP.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        SESSION_MAP.remove(session.getId());
    }

    public void sendMessage(String jsonData) {
        SESSION_MAP.forEach((s, webSocketSession) -> {
            try {
                webSocketSession.sendMessage(new TextMessage(jsonData));
            } catch (IOException e) {
                log.error("WebSocket发送数据给{}异常: {}", s, e.getMessage());
            }
        });
    }
}
