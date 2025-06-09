const socket = new WebSocket('ws://localhost:8080/bidsystem/bid_update_web_socket_admin');

socket.onmessage = function(event) {
    const toast1 = new bootstrap.Toast(document.getElementById('toast1'));
    document.getElementById('toastMessage').innerHTML = event.data;
    toast1.show();
    setTimeout(() => {
        toast2.show();
    }, 1000);
};

socket.onopen = () => console.log("WebSocket connected");
socket.onclose = () => console.log("WebSocket disconnected");