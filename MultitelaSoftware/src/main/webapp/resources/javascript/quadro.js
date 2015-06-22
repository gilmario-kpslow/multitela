function criaQuadro() {
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");
    context.fillStyle = "rgb(240, 240, 240)";
    context.fillRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "rgb(250, 50,50)";
    context.font = "24px";
    context.fillText("Gilmário", 10, 25);
    riscaQuadro();
}

function riscaQuadro() {
    $("#canvas").on("mouseclick", function (evt) {
        alert(evt);
    });


}


