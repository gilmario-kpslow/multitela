package br.com.gilmario.multitelasoftware;

import java.io.StringReader;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author gilmario
 */
public class Ponto {

    private Integer x;
    private Integer y;
    private String cor;
    private String teste;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public void setCor(String cor) {
        if (!cor.contains("#")) {
            cor = "#" + cor;
        }
        this.cor = cor;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder().add("x", getX()).add("y", getY()).add("cor", getCor()).build();
    }

    public Ponto jsonTo(JsonObject o) {
        setX(o.getInt("x"));
        setY(o.getInt("y"));
        setCor(o.getString("cor"));
        return this;
    }

}
