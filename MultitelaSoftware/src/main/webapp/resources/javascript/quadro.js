desenhando = false;

function criaQuadro() {
    var largura = window.innerWidth;
    var altura = window.innerHeight;
    var canvasw = largura * 0.8;
    var canvash = altura * 0.8;

    var canvas = document.getElementById("canvas");
    canvas.width = canvasw;
    canvas.height = canvash;

    var context = canvas.getContext("2d");
    context.fillStyle = "rgb(240, 240, 200)";
    context.fillRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "rgb(0, 0, 0)";
    $("#canvas").on("mousedown", function (evt) {
        context.moveTo(evt.clientX - 370, evt.clientY - 100);
        desenhando = true;
    });
    $("#canvas").on("mouseup", function (evt) {
        desenhando = false;
    });
    $("#canvas").on("mousemove", function (evt) {
        if (desenhando) {
            context.lineTo(evt.clientX - 370, evt.clientY - 100);
            context.stroke();
        }

    });
}

function riscaQuadro() {
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");
    dsd


}


