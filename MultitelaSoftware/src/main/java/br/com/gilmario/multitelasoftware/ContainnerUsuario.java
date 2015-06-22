package br.com.gilmario.multitelasoftware;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import javax.websocket.Session;

/**
 *
 * @author gilmario
 */
@Singleton
public class ContainnerUsuario {

    private final Map<String, Usuario> usuarios = new HashMap<>();

    public boolean contemUsuario(String nome) {
        return usuarios.containsKey(nome);
    }

    public Usuario getUsuario(String nome) {
        return usuarios.get(nome);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    public String getNomeUsuarioPorSessao(Session s) {
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            String nome = entry.getKey();
            Usuario usuario = entry.getValue();
            if (usuario.equals(s)) {
                return nome;
            }
        }
        return null;
    }

    public void removeUsuario(Session session) {
        usuarios.remove(getNomeUsuarioPorSessao(session));
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

}
