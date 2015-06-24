desenhando = false;

function criaQuadro() {
    var largura = $("#contem_canvas").width();
    var altura = $("#contem_canvas").height();
    var canvas = document.getElementById("canvas");
    canvas.width = largura;
    canvas.height = altura;

    var margemL = (window.innerWidth - largura) / 2;
    var margemH = (window.innerHeight - altura) / 2;
    var context = canvas.getContext("2d");
    context.fillStyle = "rgb(240, 240, 200)";
    context.fillRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "rgb(0, 0, 0)";
    $("#canvas").on("mousedown", function (evt) {
        context.moveTo(evt.clientX - margemL, evt.clientY - margemH);
        desenhando = true;
    });
    $("#canvas").on("mouseup", function (evt) {
        desenhando = false;
    });
    $("#canvas").on("mousemove", function (evt) {
        if (desenhando) {
            context.lineTo(evt.clientX - margemL, evt.clientY - margemH);
            context.stroke();
        }

    });
}

function riscaQuadro() {
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");

}


