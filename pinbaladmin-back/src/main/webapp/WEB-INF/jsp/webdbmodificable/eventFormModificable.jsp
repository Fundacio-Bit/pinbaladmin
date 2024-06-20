
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Configuracio"%>
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Constants"%>
<script>
	
<%--
	public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // PRIVAT_TRAMITADOR
	public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1; // PUBLIC_TRAMITADOR
	public static final int EVENT_TIPUS_COMENTARI_CONTACTE = 2; // PUBLIC_CONTACTE
	public static final int EVENT_TIPUS_COMENTARI_SUPORT = -2; // COMENTARI A SUPORT
	public static final int EVENT_TIPUS_CONSULTA_A_CEDENT = -3; // PRIVAT_TRAMITADOR CAP A CEDENT
	public static final int EVENT_TIPUS_CEDENT_RESPOSTA = 3; // PUBLIC_RESPOSTA DE CEDENT
--%>
	function onChangeTipus(select) {

		if (select === null) {
			return;
		}
		
		document.getElementById("event_caidIdentificadorConsulta_rowid").style.display = "none";
		document.getElementById("event_caidNumeroSeguiment_rowid").style.display = "none";


		//Si es un comentari de contacte, la persona es el contacte (qui fa la consulta), sino, l'operador
		if (select.value == 2) {
			$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Persona *";
	    	document.getElementById("event.persona").value = "${persona_contacte}";
		} else {
			$("#event_persona_columnlabelid")[0].children[0].innerHTML = "Operador *";
			document.getElementById("event.persona").value = "${persona_operador}";
		}

		// Si es una consulta a suport, el destinatari es suport, sino, el contacte
        if (select.value == -2) {
    	    document.getElementById("event.destinatari").value = "Suport CAIB";
    		document.getElementById("event.destinatarimail").value = "<%=Configuracio.getCorreuSuport()%>";
        } else if (select.value == 1 || select.value == -3) {
            document.getElementById("event.destinatari").value = "${persona_contacte}";
			document.getElementById("event.destinatarimail").value = "${persona_contacte_mail}";
        }else{
            document.getElementById("event.destinatari").value = null;
			document.getElementById("event.destinatarimail").value = null;
        }

		// Si no s'envia cap correu, amagar els camps de destinatari i asumpte
		if (select.value == 1 || select.value == -2) {
			document.getElementById("event_destinatari_rowid").style.display = "";
			document.getElementById("event_destinatarimail_rowid").style.display = "";
			document.getElementById("event_asumpte_rowid").style.display = "";
		} else {
			document.getElementById("event_destinatari_rowid").style.display = "none";
			document.getElementById("event_destinatarimail_rowid").style.display = "none";
			document.getElementById("event_asumpte_rowid").style.display = "none";
		}
	}

	onChangeTipus(document.getElementById("event_tipus"));
</script>



