package br.com.gilmario.multitelasoftware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author gilmario
 */
@ServerEndpoint("/servidor/{usuario}")
public class ServidorWebsocket {

    @Inject
    private ContainnerUsuario containnerUsuario;

    @OnMessage
    public void onMessage(Session session, String msg) {
        Mensagem men = Mensagem.objectToJson(msg);

    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("usuario") String nome) {
        if (!containnerUsuario.contemUsuario(nome)) {
            containnerUsuario.addUsuario(new Usuario(session, nome));
        } else {
            enviaTexto(session, "Escolha outro nome esse usuario.");
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        containnerUsuario.removeUsuario(session);
    }

    private void enviaTexto(Session s, String texto) {
        try {
            s.getBasicRemote().sendText(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
