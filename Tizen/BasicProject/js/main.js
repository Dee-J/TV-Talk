var startTime;
var checkTime;
var showChatWindow;


function showChatWindow() {
	console.log("Show chat window");
	document.getElementById('ChatWindow').style.display = 'show';
	//$('ChatWindow').slideup();
	
}


//Initialize function
var init = function () {
    // TODO:: Do your initialization job
    console.log("init() called");
 
    // add eventListener for keydown
    document.addEventListener('keydown', function(e) {
    	console.log("Key code : " + e.keyCode);
    	switch(e.keyCode){
    	case 37: //LEFT arrow
    		console.log("Key code : " + e.keyCode + " : LEFT arrow");
    		break;
    	case 38: //UP arrow
    		console.log("Key code : " + e.keyCode + " : UP arrow");
    		break;
    	case 39: //RIGHT arrow
    		console.log("Key code : " + e.keyCode + " : RIGHT arrow");
    		break;
    	case 40: //DOWN arrow
    		console.log("Key code : " + e.keyCode + " : DOWN arrow");
    		break;
    	case 13: //OK button
    		console.log("Key code : " + e.keyCode + " : OK BUTTON");
    		break;
    	case 10009: //RETURN button
    		console.log("Key code : " + e.keyCode + " : RETURN BUTTON");
    		break;
    	case 10182: //Exit button
    		console.log("Key code : " + e.keyCode + " : Exit BUTTON");
    		break;
    		
    		
    	case 403: //A button
    		// Open Chat Window
    		console.log("Key code : " + e.keyCode + " : A BUTTON (RED)");
    		showChatWindow();
    		break;
    	case 404: //B button
    		console.log("Key code : " + e.keyCode + " : B BUTTON (GREEN)");
    		break;
    	case 405: //C button
    		console.log("Key code : " + e.keyCode + " : C BUTTON (YELLOW)");
    		break;
    	case 406: //D button
    		console.log("Key code : " + e.keyCode + " : D BUTTON (BLUE)");
    		break;
    		
    	default:
    		console.log("Key code : " + e.keyCode);
    		break;
    	}
    });
};
// window.onload can work without <body onload="">
window.onload = init;


function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('divbutton1').innerHTML="Current time: " + h + ":" + m + ":" + s;
    t = setTimeout(startTime, 250);
}

function checkTime(i) {
    if (i < 10) {
        i="0" + i;
    }
    return i;
}