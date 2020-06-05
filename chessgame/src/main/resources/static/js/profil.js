


setInterval(function() {
	var promise = $.ajax({
		url : '/api/profil/update'
	});
	promise.done(function(reponse) {
		console.log("recu");
		var cList = $("#tableInvitationPartie").empty();
		var cList2 = $("#inviteAmi").empty();
		var cList3 = $("#Ami").empty();
		$.each(reponse.invitationPartie, function(i) {
			var li = $('<li/>').addClass('list-group-item').text(
					reponse.invitationPartie[i].versus);

			$(
					'<button/>',
					{
						text : 'Rejoindre', // set text 1 to 10
						id : reponse.invitationPartie[i].id,
						click : function() {

							console.log("/Invitation/"
									+ reponse.invitationPartie[i].id
									+ "/Accept");
							var promise = $.ajax({
								url : '/Invitation/'
										+ reponse.invitationPartie[i].id
										+ '/Accept'
							});
							promise.done(function(reponse) {
								console.log(reponse);
							});
						}
					}).appendTo(li);

			li.appendTo(cList);

		});

		$.each(reponse.inviteFriends, function(i) {
			var li2 = $('<li/>').addClass('list-group-item').text(
					reponse.inviteFriends[i]);

			$(
					'<button/>',
					{
						text : 'Accepter', // set text 1 to 10
						id : reponse.inviteFriends[i]+'Accepter',
						click : function() {

							console.log("/friends/accepte/" + reponse.inviteFriends[i]);
							var promise = $.ajax({
								url : '/friends/accepte/'+ reponse.inviteFriends[i]});
							promise.done(function(reponse) {
								console.log(reponse);
							});
						}
					}).appendTo(li2);

			li2.appendTo(cList2);
		});

	
	
	$.each(reponse.friends, function(i) {
		var li3 = $('<li/>').addClass('list-group-item');
		
		var spa = $('<i/>').addClass('fa fa-user').attr("aria-hidden","true").appendTo(li3);
		var iy =  $('<i/>').text(" "+reponse.friends[i]).appendTo(li3);

		$(
				'<button/>',
				{
					
					text : 'Défier', // set text 1 to 10
					id : reponse.friends[i]+':Defier',
					click : function() {
						console.log("/Invitation/" + reponse.friends[i]);
						var promise = $.ajax({
							url : '/Invitation/' + reponse.friends[i]
						});
						promise.done(function(reponse) {
							console.log(reponse);
							if (reponse.includes("Erreur")) {
								alert(reponse.msg);

							}
							if (reponse.includes("ok")) {
								alert("Demande de partie envoyé");
							}
						});
					}
				
				}).attr("class","btn btn-secondary")
				.attr("style","float:right;")
				.appendTo(li3);

		li3.appendTo(cList3);
	});

	});

}, 2000);
