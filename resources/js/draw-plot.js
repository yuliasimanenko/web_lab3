"use strict";
let AreaViewDrawer = function(canvasName) {
    // Constants
    const CANVAS_NAME = canvasName;
    const CANVAS_ELEMENT = document.getElementById(canvasName);

    // Drawing plot
    // ------------

    // Draws point on a plot. Assumes x, y, and r are valid numbers.
    function drawPoint(x, y, r, color) {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = color;
        ctx.beginPath();
        ctx.arc(200 + 120 * x / r, 200 - 120 * y / r, 5, 0, 2 * Math.PI);
        ctx.fill();
    }

    function drawPlotLines() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.strokeStyle = "#000000";
        ctx.lineWidth = 1;

        ctx.beginPath();

        // Y
        ctx.moveTo(200, 375);
        ctx.lineTo(200, 25);
        ctx.stroke();

        ctx.lineTo(205, 30);
        ctx.stroke();

        ctx.moveTo(200, 25);
        ctx.lineTo(195, 30);
        ctx.stroke();

        // X
        ctx.moveTo(25, 200);
        ctx.lineTo(375, 200);
        ctx.stroke();

        ctx.lineTo(370, 195);
        ctx.stroke();

        ctx.moveTo(375, 200);
        ctx.lineTo(370, 205);
        ctx.stroke();
    }

    function drawRectangle(color) {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = color;
        // R/2 = 60 px
        ctx.fillRect(200, 200, 60, 120);
    }

    function drawTriangle(color) {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = color;
        ctx.strokeStyle = color;
        ctx.beginPath();
        ctx.moveTo(320, 200);
        ctx.lineTo(200, 200);
        ctx.lineTo(200, 80);
        ctx.fill();
    }

    function drawQuarterCircle(color) {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = color;

        ctx.beginPath();
        ctx.moveTo(200, 200);
        ctx.arc(200, 200, 60, Math.PI, 1.5 * Math.PI);
        ctx.fill();
        ctx.closePath();
    }

    function drawLabels(r = "R") {
        if (isNaN(r) && r !== "R")
            throw "Invalid argument 'r'.";

        const halfR = (r === "R") ? "R/2" : parseFloat(r) / 2.0;

        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.strokeStyle = "#000000";
        ctx.font = "14px Monospace";
        ctx.fillStyle = "#000000";

        ctx.beginPath();

        // X
        ctx.moveTo(80, 196);
        ctx.lineTo(80, 204);
        ctx.stroke();

        ctx.fillText("-" + r, 72, 190);

        ctx.moveTo(140, 196);
        ctx.lineTo(140, 204);
        ctx.stroke();

        ctx.fillText("-" + halfR, 125, 190);

        ctx.moveTo(260, 196);
        ctx.lineTo(260, 204);
        ctx.stroke();

        ctx.fillText(halfR, 250, 190);

        ctx.moveTo(320, 196);
        ctx.lineTo(320, 204);
        ctx.stroke();

        ctx.fillText(r, 316, 190);

        ctx.fillText("X", 365, 190);

        // Y
        ctx.moveTo(196, 320);
        ctx.lineTo(204, 320);
        ctx.stroke();

        ctx.fillText("-" + r, 206, 324);

        ctx.moveTo(196, 260);
        ctx.lineTo(204, 260);
        ctx.stroke();

        ctx.fillText("-" + halfR, 206, 264);

        ctx.moveTo(196, 140);
        ctx.lineTo(204, 140);
        ctx.stroke();

        ctx.fillText(halfR, 206, 144);

        ctx.moveTo(196, 80);
        ctx.lineTo(204, 80);
        ctx.stroke();

        ctx.fillText(r, 206, 84);
        ctx.fillText("Y", 206, 29);
    }

    function drawPlot(color) {
        drawRectangle(color);
        drawTriangle(color);
        drawQuarterCircle(color);
        drawPlotLines();
    }

    // ---------------------------------------
    // Export
    // ---------------------------------------

    const draw = function(r, areaColor = "#3399FF") {
        if (isNaN(r))
            throw "Invalid argument 'r'.";
        if (areaColor === null || areaColor === "")
            throw "Invalid argument 'areaColor'.";

        drawPlot(areaColor);
        drawLabels(r);
    };

    const placePoint = function(x, y, r, color){
        if (isNaN(x))
            throw "Invalid argument 'x'.";
        if (isNaN(y))
            throw "Invalid argument 'y'.";
        if (isNaN(r))
            throw "Invalid argument 'r'.";
        if (color === null || color === "")
            throw "Invalid argument 'color'.";

        drawPoint(x, y, r, color);
    };

    const clear = function() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.clearRect(0, 0, CANVAS_ELEMENT.width, CANVAS_ELEMENT.height);
    };

    return { draw, placePoint, clear };
};
