
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Configuracio"%>
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Constants"%>



<select id="event.persona.select" class="form-control" style="display: none;" onchange="onChangeCedent(this, 3)">

	<c:forEach items="${cedents}" var="cedent">
		<option mail="${cedent[2]}" value="cedent${cedent[0]}">${cedent[1]}</option>	
	</c:forEach>


<!-- 	<option mail="ptrias@fundaciobit.org" value="cedente1">Cedent N1</option>
	<option mail="pautrias2@gmail.com" value="cedente2">Cedent N2</option>
	<option mail="ptrias2705@gmail.com" value="cedente3">Cedent N3</option> -->
</select>

<select id="event.destinatari.select" class="form-control" style="display: none;" onchange="onChangeCedent(this, -3)">
	<c:forEach items="${cedents}" var="cedent">
		<option mail="${cedent[2]}" value="${cedent[0]}">${cedent[1]}</option>	
	</c:forEach>

<!-- 	<option mail="ptrias@fundaciobit.org" value="cedente1">Cedent N1</option>
	<option mail="pautrias2@gmail.com" value="cedente2">Cedent N2</option>
	<option mail="ptrias2705@gmail.com" value="cedente3">Cedent N3</option> -->
</select>

<!--
	EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // PRIVAT_TRAMITADOR

	EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1; // PUBLIC_TRAMITADOR
	EVENT_TIPUS_COMENTARI_SUPORT = -2; // COMENTARI A SUPORT
	EVENT_TIPUS_CONSULTA_A_CEDENT = -3; // CONSULTA CEDENT CEDENT

	EVENT_TIPUS_CEDENT_RESPOSTA = 3; // RESPOSTA DE CEDENT
	EVENT_TIPUS_COMENTARI_CONTACTE = 2; // CONTACTE
  -->
<script>
	//Afegir selects just despres de l'input, i depenent del tipus, amagar l'input i mostrar el select.
	var eventPersonaInput = document.getElementById('event.persona');
	var selectCedentsPersona = document.getElementById('event.persona.select');
	eventPersonaInput.parentNode.appendChild(selectCedentsPersona);

	var eventDestinatariInput = document.getElementById('event.destinatari');
	var selectCedentsDestinatari = document.getElementById('event.destinatari.select');
	eventDestinatariInput.parentNode.appendChild(selectCedentsDestinatari);

	var tipus = document.getElementById("event_tipus").value;
	console.log("Tipus: " + tipus);

	onChangeTipus(document.getElementById("event_tipus"));
	
	function onChangeTipus(select) {
		if (select === null) {
			return;
		}
		var tipus = select.value;

		// Persona es sempre qui escriu el missatge. Si es una resposta de cedents, la persona es cedent. Si es comentari de contacte, el destinatari del correu original. Sino, l'operador
		if (tipus == 3) {
			$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Cedent *";

			eventPersonaInput.style.display = "none";
			selectCedentsPersona.style.display = "";

			onChangeCedent(selectCedentsPersona, tipus);
		} else {
			eventPersonaInput.style.display = "";
			selectCedentsPersona.style.display = "none";

			var personaEvt = document.getElementById("event.persona");
			if (tipus == 2) {
				$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Persona *";
				personaEvt.value = "${persona_contacte}";
			} else {
				$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Operador *";
				personaEvt.value = "${persona_operador}";
			}
		}

		// Si es una consulta a suport, el destinatari es suport. -2
		// Si es una consulta a cedent, el destinatari es cedent. -3
		// Si es un comentari tramitador public, el destinatari es el contacte. 1
		// Si es un comentari tramitador privat, el destinatari es null. -1
		// Si es resposta de cedent o contacte, no posam destinatari

		var destinatari = document.getElementById("event.destinatari");
		var destinatariMail = document.getElementById("event.destinatarimail");

		if (tipus == -3) {
			$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Operador *";
			eventPersonaInput.value = "${persona_operador}";

			$("#event_destinatari_columnlabelid")[0].children[0].innerHTML = "Cedent *";
			$("#event_destinatarimail_columnlabelid")[0].children[0].innerHTML = "Correu cedent *";

			eventDestinatariInput.style.display = "none";
			selectCedentsDestinatari.style.display = "";

			onChangeCedent(selectCedentsDestinatari, tipus);
		} else {
			eventDestinatariInput.style.display = "";
			selectCedentsDestinatari.style.display = "none";

			$("#event_destinatari_columnlabelid")[0].children[0].innerHTML = "Destinatari *";
			$("#event_destinatarimail_columnlabelid")[0].children[0].innerHTML = "Correu destinatari *";

			var destinatariEvt = document.getElementById("event.destinatari");
			if (tipus == -2) {
				//destinatari.value = "Soporte Intermediación";
				//destinatariMail.value = "<%-- <%=Configuracio.getCorreoSoporteEstatal()%> --%>";
	    	
	 			destinatariEvt.value = "Suport CAIB";
	    		destinatariMail.value = "<%=Configuracio.getCorreuSuportCAIB()%>";
	    		
	        } else if (tipus == 1) {
	        	destinatariEvt.value = "${persona_contacte}";
				destinatariMail.value = "${persona_contacte_mail}";
	        }else{
	        	destinatariEvt.value = null;
				destinatariMail.value = null;
	        }
		}

		// Si no s'envia cap correu, amagar els camps de destinatari i asumpte.
		// Enviam correus si es una consulta a suport, a cedent o un comentari tramitador public
		if (tipus == 1 || tipus == -2 || tipus == -3) {
			document.getElementById("event_destinatari_rowid").style.display = "";
			document.getElementById("event_destinatarimail_rowid").style.display = "";
			document.getElementById("event_asumpte_rowid").style.display = "";
		} else {
			document.getElementById("event_destinatari_rowid").style.display = "none";
			document.getElementById("event_destinatarimail_rowid").style.display = "none";
			document.getElementById("event_asumpte_rowid").style.display = "none";
		}
	}

	//Crear una funcion on change per aquest select, per a que depenent del valor, canvi de correu i nom
	function onChangeCedent(selectCedent, tipus) {
		
		console.log(selectCedent.id + ": " + selectCedent.value);
 		var cedent = selectCedent.selectedOptions[0];
 		
 		var personaActual = document.getElementById('event.persona').value;
		console.log("Persona actual: " + personaActual);
 		//Cercar si la persona actual es una de les alternatives del select de cedent.
 		var trobat = false;
 		
		for (var i = 0; i < selectCedent.options.length; i++) {
			if (selectCedent.options[i].textContent == personaActual) {
				trobat = true;
				break;
			}
		}

		console.log(cedent);
		if (cedent) {
			var nom = cedent.text;
			var mail = cedent.getAttribute("mail");
			if (tipus == -3) {
				document.getElementById('event.destinatari').value = nom;
				document.getElementById('event.destinatarimail').value = mail;
			} else if (tipus == 3) {
				console.log("trobat: " + trobat);
				if (trobat) {
                    document.getElementById('event.persona').value = personaActual;
                }else{
					document.getElementById('event.persona').value = nom;
                }
			}
		}
	}
</script>

<c:if test="${isPublic}">
	<script type="text/javascript">
		
		//Si es de tipus resposta cedent, llevar el select i posar input (que te el nom del cedent desencriptat), i deixar-ho readonly
	    var tipus = document.getElementById("event_tipus").value;
		if (tipus == 3 || tipus == 2) {
			document.getElementById("event.persona.select").style.display = "none";
			document.getElementById("event.persona").style.display = "";
			document.getElementById("event.persona").readOnly = true;
		}

		document.getElementById("event_tipus_rowid").style.display = "none";

		//		document.getElementById("event.persona.select").disabled = "true";
		//		document.getElementById("event.persona").disabled = "true";
	</script>
</c:if>

<!-- Validacio missatge buit -->
<script>
	//onsubmit="return validateForm()"
	
	document.getElementById("eventForm").onsubmit = function() {
		return validateForm();
	};

	function validateForm() {
		var missatge = document.getElementById("event.comentari").value;
		if (missatge.trim() == "") {
			alert("El missatge no pot estar buit");
			return false;
		}
		return true;
	}
</script>
