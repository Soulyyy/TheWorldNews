var websocket = {

    createWebsocket: function() {
        var socketAddr = window.location.origin.replace("http", "ws") + "/feed";
        var websocket = new WebSocket(socketAddr);
        console.log(websocket);
        websocket.onopen = function(event) { console.log("socket up!"); };
        websocket.onclose = function(event) { console.log("socket closed!"); };
        websocket.onmessage = function(event) {
            console.log("ws received " + event.data);
        };
        websocket.onerror = function(event) {console.log(event.data)};
    }
};

$(function() {
    // websocket.createWebsocket();
});
