package br.com.gilmario.multitelasoftware;

import javax.websocket.Session;

/**
 *
 * @author gilmario
 */
public class Usuario {

    private final Session session;
    private String nome;

    public Usuario(Session session, String nome) {
        this.session = session;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Session getSession() {
        return session;
    }

}
