package br.com.gilmario.multitelasoftware;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

/**
 *
 * @author gilmario
 */
@ApplicationScoped
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

    public String[] getListaNomesUsuario() {
        String[] usuariosL = new String[usuarios.size()];
        int i = 0;
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            usuariosL[i] = entry.getKey();
            i++;
        }
        return usuariosL;
    }

    public void removeUsuario(Session session) {
        usuarios.remove(getNomeUsuarioPorSessao(session));
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

}
