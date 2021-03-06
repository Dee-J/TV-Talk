var express 	= require('express'),
	app			= express(),
    server  	= require('http').createServer(app),
    io      	= require('socket.io').listen(server),
    port    	= 3000;

// listening to port...
server.listen(port);

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});

// usernames which are currently connected to the chat
var usernames = {};
var usercount=0;
// rooms which are currently available in chat
var rooms = ['MBC','SBS','KBS1','KBS2','EBS'];

io.sockets.on('connection', function (socket) {
	
	// when the client emits 'adduser', this listens and executes
	socket.on('adduser', function(server){
		// store the username in the socket session for this client
		socket.username = 'guest'+usercount;
		// store the rojsom name in the socket session for this client
		socket.room = server;
		// add the client's username to the global list
		usernames['guest'+usercount] = 'guest'+usercount;
		// send client to room 1
		socket.join(server);
		// echo to client they've connected
		socket.emit('updatechat', 'SERVER', 'you have connected to '+server);
		// echo to room 1 that a person has connected to their room
		socket.broadcast.to(server).emit('updatechat', 'SERVER', 'guest'+usercount + ' has connected to this room');
		socket.emit('updaterooms', rooms, server);
		usercount++;
	});
	
	// when the client emits 'sendchat', this listens and executes
	socket.on('sendchat', function (data) {
		// we tell the client to execute 'updatechat' with 2 parameters
		io.sockets.in(socket.room).emit('updatechat', socket.username, data);
//io.sockets.in(socket.room).emit("chat", people[socket.id], msg);
		//socket.broadcast.to(socket.room).emit('update_chat', socket.username, data);
		//	io.sockets.in(ds);
		//io.sockets.in(sokcet.room).emit(ev);
		//socket.broadcast.to('room1').emit('updatechat', socket.username, data);
		//io.sockets.emit('updatechat', socket.username, data);
		//io.sockets.
	});
	
	socket.on('switchRoom', function(newroom){
		socket.leave(socket.room);
		socket.join(newroom);
		socket.emit('updatechat', 'SERVER', 'you have connected to '+ newroom);
		// sent message to OLD room
		socket.broadcast.to(socket.room).emit('updatechat', 'SERVER', socket.username+' has left this room');
		// update socket session room title
		socket.room = newroom;
		socket.broadcast.to(newroom).emit('updatechat', 'SERVER', socket.username+' has joined this room');
		socket.emit('updaterooms', rooms, newroom);
	});
	

	// when the user disconnects.. perform this
	socket.on('disconnect', function(){
		// remove the username from global usernames list
		delete usernames[socket.username];
		// update list of users in chat, client-side
		io.sockets.emit('updateusers', usernames);
		// echo globally that this client has left
		socket.broadcast.emit('updatechat', 'SERVER', socket.username + ' has disconnected');
		socket.leave(socket.room);
	});
});