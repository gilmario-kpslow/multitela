package br.com.gilmario.multitelasoftware;

import java.io.StringReader;
import java.util.Map;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
@ServerEndpoint("/servidor/{usuario}/{cor}")
public class ServidorWebsocket {

    @Inject
    private ContainnerUsuario containnerUsuario;

    @OnMessage
    public void onMessage(Session session, String msg) {
        JsonObject o = Json.createReader(new StringReader(msg)).readObject();
        Mensagem.Acao acao = Mensagem.Acao.valueOf(o.getString("acao"));
        acao.getProcessaMensagem().processarMensagem(o, containnerUsuario.getUsuarios());

    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("usuario") String nome, @PathParam("cor") String cor) {
        if (!containnerUsuario.contemUsuario(nome)) {
            containnerUsuario.addUsuario(new Usuario(session).setNome(nome).setCor(cor));
            // Jogar para todos os os nomes dos usuario logados
//            enviaMensagemATodos(new Mensagem(Mensagem.Acao.Execucao, "usuarios_logados", ).jsonToJson());
            enviaMensagemATodos(Json.createObjectBuilder().add("acao", Mensagem.Acao.Execucao.toString()).add("parametro", "usuarios_logados").add("mensagem", JSonUtil.criaArrayJson(containnerUsuario.getListaNomesUsuario()).build()).build().toString());
        } else {
            enviaTexto(session, "Escolha outro nome esse usuario.");
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        containnerUsuario.removeUsuario(session);
        enviaMensagemATodos(Json.createObjectBuilder().add("acao", Mensagem.Acao.Execucao.toString()).add("parametro", "usuarios_logados").add("mensagem", JSonUtil.criaArrayJson(containnerUsuario.getListaNomesUsuario()).build()).build().toString());
    }

    private void enviaTexto(Session s, String texto) {
        try {
            s.getBasicRemote().sendText(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviaMensagemATodos(String mensagem) {
        System.out.println(mensagem);
        for (Map.Entry<String, Usuario> entry : containnerUsuario.getUsuarios().entrySet()) {
            Usuario usuario = entry.getValue();
            enviaTexto(usuario.getSession(), mensagem);
        }
    }

}
