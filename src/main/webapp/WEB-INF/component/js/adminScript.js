/*var stompClient = null;

$(document).ready(function(){
	
	if(stompClient!=null)
		{stompClient.disconnect();}

	 var socket = new SockJS('/workerdash');
	 
	 console.log(socket);
	 stompClient = Stomp.over(socket);
	
	console.log(stompClient);
	
	
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/', function(response) {
			console.log(response);
			console.log(JSON.parse(response.body));
		});
	});
	
	
    $("#btn").click(function(){
        
    	sendData2Socket();
    	
    });
});

function sendData2Socket() {
	
	
	var totalRuns = $("#totalRuns").val();
	var name = $("#name").val();
	
	stompClient.send("/app/livescore", {}, JSON.stringify({ 'totalRuns': totalRuns,'name': name}));

}*/