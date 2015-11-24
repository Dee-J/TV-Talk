# json 받아올 node.js 예제

var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
	res.send('{List:[{"Type": "MessagePush", "Message": "test"},{"Type": "MessagePush", "Message": "test1"}, {"Type": "MessagePush", "Message": "test2"}]}');
});

io.on('connection', function(socket){
	console.log('a user connected');
	socket.on('disconnect', function(){
		console.log('user disconnected');
	    });
});

http.listen(8000, function(){
	console.log('listening on *:8000');
});
