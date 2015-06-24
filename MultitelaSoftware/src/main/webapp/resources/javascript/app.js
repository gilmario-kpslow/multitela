var conexao;
var logados = new Array();
var usuario;
function iniciaConexao() {
    var nome = $("#nome_usuario").val();
    var cor = new String($("#cor_usuario").val()).replace("#", '');
    conexao = new WebSocket("ws://10.100.0.48:8081/multview/servidor/" + nome + "/" + cor);
//    mensagemInfo("Tentando se Conectar.", "Informação");
    conexao.onmessage = (function (evt) {
        processaMensagem(criaObjeto(evt.data));
    });
    conexao.onerror = (function (evt) {
        alert('ERRO');
    });
    conexao.onopen = (function () {
        criarUsuario();
        $(document).on("click", function (evt) {
            console.log("click");
            marcar(evt.clientX, evt.clientY);
        });
        carregarPagina("logado");
    });
}

function criarUsuario() {
    usuario = {
        nome: $("#nome_usuario").val(),
        cor: $("#cor_usuario").val()
    };
}

function abreQuadro() {
    carregarPagina("quadro", function (data) {
        $("#conteudo").html(data);
        criaQuadro();
    });
}

function criaObjeto(obj) {
    try {
        console.log(obj);
        return obj = JSON.parse(obj);
    } catch (e) {
        alert(e);
    }
    return null;
}

function processaMensagem(mensagem) {
    if (mensagem.parametro == "usuarios_logados") {
        logados = mensagem.mensagem;
        $("#usuarios_logados_select").html("<option value='todos'>Todos</option>");

        for (var i = 0; i < logados.length; i++) {
            $("#usuarios_logados_select").append("<option value='" + logados[i].usuario + "'>" + logados[i].usuario + "</option>")
            $("<div style='width: 20px; height: 20px; border: 1px solid black;position: absolute; '></div>");
        }
    }
    if (mensagem.parametro == "conversa") {
        $("#conversa").append(mensagem.mensagem);
    }
    if (mensagem.parametro == "marcar") {
        var ponto = mensagem.mensagem;
        ponto.teste();
        efeitoCirculo(ponto.x, ponto.y, ponto.cor);
    }
    if (mensagem.parametro == "move") {
        var quadro = mensagem.mensagem;

    }
}

function enviaMensagem(mensagem) {
    var resp = JSON.stringify(mensagem);
    conexao.send(resp);
}

function carregarPagina(pagina, evento) {
    $.get(document.location.href + "/paginas/" + pagina + ".html", "", function (data) {
        $("#conteudo").html(data);
        if (evento != undefined) {
            evento();
        }
    });
}


function iniciarLogin() {
    carregarPagina("login", function () {
        $("#btnlogar").on("click", login);
    });
}

function login() {
    iniciaConexao();
}

function moveQuadro(evt, quadro) {
    if (quadro in logados) {
        $(quadro).css("top", evt.clientY - 10);
        $(quadro).css("left", evt.clientX - 10);
    }
}
function efeitoCirculo(x, y, cor) {
    var c = $("<div style='width: 20px; height: 20px; border: 1px solid black; position: absolute; border-radius: 10px; background: " + cor + "' ></div>");
    $(c).css("top", y - 10);
    $(c).css("left", x - 10);
    $("body").append(c);
    c.fadeOut(5000, function () {
        c.remove();
    });

}

function marcar(x, y) {
    var ponto = {
        x: x,
        y: y,
        cor: usuario.cor
    };
    enviaMensagem(criarMensagem('Marcar', 'marcar', ponto));
}

function criarMensagem(acao, parametro, mensagem) {
    var mensagem = {
        acao: acao,
        parametro: parametro,
        mensagem: mensagem,
        teste: function () {
            alert("hi");
        }
    };
    return mensagem;
}

$(document).ready(function () {
    iniciarLogin();
    $(document).on("mousemove", function (evt) {
        moveQuadro(evt);
    });

});




function panelUsuarios() {
    $("usuarios_modal").modal("show");
}
