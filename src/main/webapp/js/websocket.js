var websocket = {

    createWebsocket: function() {
        var socketAddr = window.location.origin.replace("http", "ws") + "/feed";
        var websocket = new WebSocket(socketAddr);
        console.log(socketAddr);
        websocket.onopen = function() { console.log("socket up!"); };
        websocket.onclose = function() { console.log("socket closed!"); };
        websocket.onmessage = function(evt) {

        };
    }
};

$(function() {

   
    websocket.createWebsocket();

});
