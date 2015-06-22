var conexao;
var nomesistema = "";
function iniciaConexao() {
    conexao = new WebSocket("ws://10.100.0.48:8081/multview/servidor/" + $("#nome_usuario").val());
    mensagemInfo("Tentando se Conectar.", "Informação");
    conexao.onmessage = (function (evt) {
        processaMensagem(criaObjeto(evt.data));
    });
    conexao.onerror = (function (evt) {
//        mensagemErro("Erro ao tentar se conectar.", "Erro");
    });
    conexao.onopen = (function () {
//        mensagemInfo("Conectado.", "Informação");
        abreQuadro();
    });
}

function abreQuadro() {
    carregarPagina("quadro", function (data) {
        $("#conteudo").html(data);
        criaQuadro();
    });
}

function criaObjeto(obj) {
    return obj = JSON.parse(obj);
}

function processaMensagem(mensagem) {

}

function enviaMensagem(mensagem) {
    var resp = JSON.stringify(message);
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
    abreQuadro();
}

$(document).ready(function () {
    iniciarLogin();
});

