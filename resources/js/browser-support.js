/* Ensures that ES6 is supported. */
try {
    new Function("(a = 0) => a");
    new Function("let x = 6, y = 3; [x,y] = [y,x];")
}
catch (err) {
    document.body.innerHTML = "<h1 style=\"text-align: center;\">" +
        "Your browser is not supported.</h1>";
}
