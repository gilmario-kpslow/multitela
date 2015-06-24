package br.com.gilmario.multitelasoftware;

import java.util.Map;
import javax.json.JsonObject;

/**
 *
 * @author gilmario
 */
public interface ProcessaMensagem {

    public void processarMensagem(JsonObject o, Map<String, Usuario> usuarios);

}
