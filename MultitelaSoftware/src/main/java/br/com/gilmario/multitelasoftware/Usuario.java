package br.com.gilmario.multitelasoftware;

import java.io.StringReader;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Session;

/**
 *
 * @author gilmario
 */
public class Usuario {

    private final Session session;
    private String nome;
    private String cor;

    public Usuario(Session session) {
        this.session = session;
    }

    public String getCor() {
        return cor;
    }

    public Usuario setCor(String cor) {
        if (!cor.contains("#")) {
            cor = "#" + cor;
        }
        this.cor = cor;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public String toJson() {
        return Json.createObjectBuilder().add("nome", this.getNome()).add("cor", this.getNome()).build().toString();
    }

    public Usuario jsonTo(String json) {
        JsonObject o = Json.createReader(new StringReader(json)).readObject();
        setNome(o.getString("nome"));
        setCor(o.getString("cor"));
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.nome, other.nome);
    }

}
