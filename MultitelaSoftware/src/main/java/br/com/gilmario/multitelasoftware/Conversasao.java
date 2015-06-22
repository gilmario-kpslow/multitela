package br.com.gilmario.multitelasoftware;

import java.util.Map;
import javax.inject.Inject;
import javax.websocket.Session;

/**
 *
 * @author gilmario
 */
public class Conversasao implements ProcessaMensagem {

    @Inject
    private ContainnerUsuario containnerUsuario;

    public Conversasao() {
    }

    @Override
    public void processarMensagem(Mensagem mensagem) {
        if (mensagem.getParametro().equals("todos")) {
            for (Map.Entry<String, Usuario> entry : containnerUsuario.getUsuarios().entrySet()) {
                Usuario usuario = entry.getValue();
                enviarMensagem(usuario.getSession(), mensagem);
            }
        } else {
            Usuario usuario = containnerUsuario.getUsuario(mensagem.getParametro());
            enviarMensagem(usuario.getSession(), mensagem);
        }
    }

    private void enviarMensagem(Session session, Mensagem mensagem) {
        try {
            session.getBasicRemote().sendText(mensagem.jsonToObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
