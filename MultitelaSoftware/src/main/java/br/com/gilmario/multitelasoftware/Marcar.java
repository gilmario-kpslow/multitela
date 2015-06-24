package br.com.gilmario.multitelasoftware;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author gilmario
 */
public class Marcar implements ProcessaMensagem {

    @Override
    public void processarMensagem(JsonObject o, Map<String, Usuario> usuarios) {
        Ponto p = new Ponto();
        p.jsonTo(o.getJsonObject("mensagem"));
        String resposta = Json.createObjectBuilder().add("acao", Mensagem.Acao.Marcar.toString()).add("parametro", "marcar").add("mensagem", p.toJson()).build().toString();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario usuario = entry.getValue();
            try {
                usuario.getSession().getBasicRemote().sendText(resposta);
            } catch (IOException ex) {
                Logger.getLogger(Marcar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
