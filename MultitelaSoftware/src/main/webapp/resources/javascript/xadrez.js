tipoPeca = new Array("rei", "piao", "cavalo", "torre", "rainha", "bispo");

Peca = {
    tipo: "",
    status: "ok",
    posicao: "",
    cor: ""
}

var canvas;
var tamanho = 50;

function iniciaVariaveis() {
    canvas = document.getElementById("canvas");
    canvas.addEventListener("click", function (evt) {
        pegaClick(evt)
    });
}

function loop() {
    desenhaQuadro();
}

function desenhaQuadro() {
    var medianaW = (canvas.width - (tamanho * 8)) / 2;
    var medianaH = (canvas.height - (tamanho * 8)) / 2;
    context = canvas.getContext("2d");
    context.strokeRect(0, 0, canvas.width, canvas.height);
    context.strokeRect(medianaW, medianaH, tamanho * 8, tamanho * 8);
    context.strokeRect(0, 0, canvas.width, 100);

    for (var i = 0; i < 8; i++) {
        for (var j = 0; j < 8; j++) {
            if ((j + i) % 2 == 1) {
                context.fillStyle = "#FFF";
            } else {
                context.fillStyle = "#000";
            }
            context.fillRect((j * tamanho) + (canvas.width - (tamanho * 8)) / 2, (i * tamanho) + (canvas.height - (tamanho * 8)) / 2, tamanho, tamanho);
        }
    }
    context.strokeRect(0, 500, canvas.width, 100);
}

function pegaClick(evt) {
    desenhaQuadro();
    var pos1 = (((evt.layerX - ((canvas.width - (tamanho * 8))) / 2) - canvas.offsetLeft) / tamanho + 0.5).toFixed(0);
    var pos2 = (((evt.layerY - ((canvas.height - (tamanho * 8))) / 2) - canvas.offsetTop) / tamanho + 0.5).toFixed(0);
    if (pos1 >= 1 && pos2 >= 1 && pos1 <= 8 && pos2 <= 8) {
        context.strokeStyle = "#0FF";
        context.strokeRect(((pos1 - 1) * tamanho) + (canvas.width - (tamanho * 8)) / 2, ((pos2 - 1) * tamanho) + (canvas.height - (tamanho * 8)) / 2, tamanho, tamanho);
        context.strokeStyle = "#000";
        var img = document.getElementById("img");
        context.drawImage(img, ((pos1 - 1) * tamanho) + (canvas.width - (tamanho * 8)) / 2, ((pos2 - 1) * tamanho) + (canvas.height - (tamanho * 8)) / 2);
    }
}

function initGame() {
    iniciaVariaveis();
    loop();
//    setInterval(loop(), 5000);;
//    setTimeout(loop, 6000);
}