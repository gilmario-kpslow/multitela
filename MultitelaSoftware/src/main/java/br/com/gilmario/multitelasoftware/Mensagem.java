package br.com.gilmario.multitelasoftware;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author gilmario
 */
public class Mensagem {

    private Acao acao;
    private String parametro;
    private String mensagem;

    public Mensagem() {

    }

    public Mensagem(Acao acao, String parametro, String mensagem) {
        this.acao = acao;
        this.parametro = parametro;
        this.mensagem = mensagem;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String jsonToObject() {
        return Json.createObjectBuilder().add("acao", this.getAcao().toString()).add("parametro", this.getParametro()).add("mensagem", this.getMensagem()).build().toString();
    }

    public static Mensagem objectToJson(String json) {
        JsonObject o = Json.createReader(new StringReader(json)).readObject();
        return new Mensagem(Acao.valueOf(o.getString("acao")), o.getString("parametro"), o.getString("mensagem"));
    }

    public enum Acao {

        Conversassao(new Conversasao());

        private final ProcessaMensagem processaMensagem;

        Acao(ProcessaMensagem processaMensagem) {
            this.processaMensagem = processaMensagem;
        }

        public ProcessaMensagem getProcessaMensagem() {
            return processaMensagem;
        }
    }

}
