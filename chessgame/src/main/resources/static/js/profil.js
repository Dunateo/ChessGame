

function AccepterPartiInvit(){
	
	console.log("/Accept");
	
	
}

$(".Defier").click(function(){
	var amiId="";
	var amiName="";
	
	amiId=this.getAttribute('id');
	amiName  = amiId.split(":");
	console.log("/Invitation/"+amiName[0]);
	var promise =$.ajax({ url:'/Invitation/'+amiName[0]});
	promise.done(function (reponse){
	console.log(reponse);
		if(reponse.includes("Erreur")){
			alert(reponse.msg);
			
		}if(reponse.includes("ok")){
			alert("Demande de partie envoyé");
		}
	});
	
});

setInterval(function(){ 
	var promise =$.ajax({ url:'/api/profil/update'});
	promise.done(function (reponse){
	console.log("recu");
	var cList = $("#tableInvitationPartie").empty();
	$.each(reponse.invitationPartie, function(i)
			{
			    var li = $('<li/>')
			        .addClass('list-group-item')
			        .text(reponse.invitationPartie[i].versus);
			        
			    $('<button/>', {
			        text: 'Rejoindre', //set text 1 to 10
			        id: reponse.invitationPartie[i].id, 
			        click: function () { 
			        	
			        	console.log("/Invitation/"+reponse.invitationPartie[i].id + "/Accept");
			        	var promise =$.ajax({ url:'/Invitation/'+ reponse.invitationPartie[i].id +'/Accept'});
			        	promise.done(function (reponse){
			        	console.log(reponse);});
			        	 }
			    }).appendTo(li);
			    
			    li.appendTo(cList);
			    
			});
	});
	
}, 2000);




