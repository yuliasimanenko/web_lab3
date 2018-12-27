"use strict";

const AreaViewComponent = function(areaColor, initialRadius) {
    let plot = null;
    let radius = initialRadius;
    let clickHandler = null;

    const addListeners = function(document) {
        document.addEventListener("DOMContentLoaded", function() {
            plot = AreaViewDrawer("AreaViewCanvas");
            plot.draw(radius);
        });

        document.getElementById("AreaViewCanvas").addEventListener("click", function(e) {
            let canvas = e.target;
            let rect = canvas.getBoundingClientRect();
            let clickX = (e.clientX - rect.left) / (canvas.clientWidth / canvas.width);
            let clickY = (e.clientY - rect.top) / (canvas.clientHeight / canvas.height);

            let pointX = (clickX - 200) / 120.0 * radius;
            let pointY = (200 - clickY) / 120.0 * radius;

            clickHandler(pointX, pointY, radius);
        });
    };

    const setClickHandler = function(handler) {
        clickHandler = handler;
    };

    const setRadius = function(r) {
        radius = r;
        plot.clear();
        plot.draw(radius);
    };

    const setPoint = function(x, y, color) {
        plot.clear();
        plot.draw(radius);
        plot.placePoint(x, y, radius, color);
    };

    return { addListeners, setRadius, setPoint, setClickHandler };
};
