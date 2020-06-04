



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