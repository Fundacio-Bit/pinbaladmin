
<script>

<%--

  public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // PRIVAT - TRAMITADOR
  public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1;  // PUBLIC - TRAMITADOR
  public static final int EVENT_TIPUS_COMENTARI_CONTACTE = 2; // PUBLIC - CONTACTE
  public static final int EVENT_TIPUS_TIQUET_MINHAP = -2; // PRIVAT - TRAMITADOR
--%>
function onChangeTipus(select) {
   if(select.value == 2) {
       <c:if test="${empty persona_contacte}">
           alert("Persona de Contacte Buida");
           select.value = -1;
       </c:if>
       <c:if test="${not empty persona_contacte}">
           document.getElementById("event.persona").value='${persona_contacte}';
       </c:if>
   } else {
       document.getElementById("event.persona").value='${persona_tramitador}';
   }

   if (select.value == -2) {
       /*
       tinymce.get("event.comentari").setContent(          
       '<p0>correu=gd.pinbal@fundaciobit.org</p>'
          + '<p>IdentificadorConsulta=ESCRIU AQUI EL Identificador de consulta</p>'
          + '<p>NumeroSeguimiento=ESCRIU AQUI EL N&uacute;mero de seguimiento</p>');

       */
       document.getElementById("event_fitxerID_rowid").style.display = "none";
       
       document.getElementById("event_caidIdentificadorConsulta_rowid").style.display = "";
       document.getElementById("event_caidNumeroSeguiment_rowid").style.display = "";

   } else {
       document.getElementById("event_fitxerID_rowid").style.display = "";
       //tinymce.get("event.comentari").setContent("");
       
       document.getElementById("event_caidIdentificadorConsulta_rowid").style.display = "none";
       document.getElementById("event_caidNumeroSeguiment_rowid").style.display = "none";
   }

}


onChangeTipus(document.getElementById("event_tipus"));
 
</script>



