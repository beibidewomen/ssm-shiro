$(function(){
	var htmlStr = "<tr><td>userId</td><td>username</td><td>password</td><td>operate</td></tr>";
	for(var i in user){
		htmlStr += "</tr><td>";
		htmlStr += user[i].userId+"</td>"
		
		htmlStr += "<td>";
		htmlStr += user[i].username+"</td>"
		
		htmlStr += "<td>";
		htmlStr += user[i].password+"</td>"
		
		htmlStr += "<td>"+"<a>update</a>"+"<a>del</a>"+"</td></tr>";
	}
	$("#tab").html(htmlStr);
});