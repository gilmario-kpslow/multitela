package br.com.gilmario.multitelasoftware;

import java.util.Map;
import javax.inject.Inject;
import javax.json.JsonObject;
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
    public void processarMensagem(JsonObject o, Map<String, Usuario> usuarios) {
        Mensagem mensagem = new Mensagem(Mensagem.Acao.valueOf(o.getString("acao")), o.getString("parametro"), o.getString("mensagem"));
        if (mensagem.getParametro().equals("todos")) {
            for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
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
            session.getBasicRemote().sendText(mensagem.toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
