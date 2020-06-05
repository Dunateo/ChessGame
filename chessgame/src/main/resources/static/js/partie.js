var requette="";
var c1="";
var c2="";
var selectEtat="0";
var Tour="-1";

function Refresh(rep){
	for(j=0;j<8;j++){
		for(i=0; i<8;i++){
			var  v= 7-j; 
			$("#"+i+j).attr('src', rep.image[i+(j*8)]).css('position','fixed').css('margin-top',89*v).css('margin-left',89*i);
			
		}
	}
	
}
function undo(){
	c1="";
	c2="";								//Fonction de retour en arririère init tout a Zéro ainsi que l'etat
	selectEtat="0";
		$(".piece").css('background-image', 'none');
		console.log("undo" +selectEtat )
}

//On initialise la partie avec un nouveaux plateau
if(Tour==="-1"){
	var str = window.location.href;
	var res = str.split("/");
	console.log("/partie/"+res[4]+"/UPDATE");
	var promise =$.ajax({ url:'/partie/'+res[4]+'/UPDATE' });
	promise.done(function (reponse){
		console.log(reponse.tour);
		Refresh(reponse);
		Tour=reponse.tour;
		if(Tour==="joueur"){
			
			$("#finDeTour").show();
			$("#j1").css('background-color','#345e90');
			$("#j2").css('background-color','transparent');
		}
		if(Tour==="adverse"){
			$("#finDeTour").hide();
			$("#j1").css('background-color','transparent');
			$("#j2").css('background-color','#345e90');
		}
	});
	
}



// Le joueur peux selectionnner un piece et joueur c'est son tour 

	
	
	
		//Recupère les coordoné des 2 case à jouer   (piontchoisi)/(destination)   ex: 0/7/0/8
	$("#undo").click(function(){
		if(Tour==="joueur"){
			undo();
			}
		});
		

		$(".piece").click(function()
		{
		if(selectEtat==="0" && Tour==="joueur"){ 
			if($(this).attr('src')!="/images/Vide.png"){
				
				c1=this.getAttribute('id');//Selectionne un piont
				
				console.log("Case1 "+c1);
				selectEtat="1";
				$(this).css('background-image', 'linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 255, 0, 0.7))');  
				}
			
		}
		
		
  		if(selectEtat==="1" && this.getAttribute('id')!=c1 && Tour==="joueur"){
	  	console.log("Case2 "+this.getAttribute('id'));				//Selection de sa destinaion
	  
	  	if(c2!=""){
		  	console.log("#"+ c2);
		  		$("#"+c2).css('background-image', 'none'); 
	  	}
	  	c2=this.getAttribute('id');
	  	
	  	$(this).css('background-image', 'linear-gradient(rgba(0, 0, 0, 0.5), rgba(249,170, 92, 0.7))');
	  
  	}
		});
//Lance la commande ajax quand on appui sur fin de Tour 
		$("#finDeTour").click(function(){
			
			
			
			
			if(Tour==="joueur"){
				c1=c1[0]+"/"+c1[1];		//Rajout du slash entre absicesses et ordonneé pour la requête ajax
				c2=c2[0]+"/"+c2[1];
				var str = window.location.href;
				var res = str.split("/");
				console.log("/partie/"+res[4]+"/Tour/"+c1+"/"+c2);
				var promise =$.ajax({ url:'/partie/'+res[4]+'/Tour/'+c1+'/'+c2 });
				undo();	//On remet tous à zero dans toute les cas
				//Refresh(reponse);
				promise.done(function (reponse){
				console.log(reponse.msg);
				Tour=reponse.tour
					if(reponse.msg.includes("Erreur")){
						alert(reponse.msg);
						
					}
				});
			}
		});




//Une commande ajax est envoyé toute les seconde pour savoir si le joueur adverse à joué si oui le joueur peut jouer à son tour sinon rien
setInterval(function(){ 
	if(Tour=="adverse"){
		
		
		$("#finDeTour").hide();
		$("#j1").css('background-color','transparent');
		$("#j2").css('background-color','#345e90');
		var str = window.location.href;
		var res = str.split("/");
		console.log("/partie/"+res[4]+"/UPDATE");
		var promise =$.ajax({ url:'/partie/'+res[4]+'/UPDATE' });
		promise.done(function (reponse){
			console.log(reponse.tour);
			Refresh(reponse);
			if(reponse.tour==="joueur"){
				
				Tour=reponse.tour;
				$("#finDeTour").show();
				$("#j1").css('background-color','#345e90');
				$("#j2").css('background-color','transparent');
			}
			
			
		});
	}
	
}, 2000);

$("#etat").click(function(){
	
	console.log("Tour="+Tour);
	
});


