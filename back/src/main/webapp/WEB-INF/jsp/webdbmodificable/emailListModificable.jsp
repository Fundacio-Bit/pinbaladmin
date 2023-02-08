
<script>

    var itemIDCache;
    var tipus; // 1 incidencia     0 solucitud

    function myFunction() {
        var tramitador = document.getElementById("tramitador").value;
        var root = "<%=request.getContextPath()%>${contexte}/"
        window.location.href = root
                + ((tipus == 1) ? "incidencia" : "solicitud") + "/"
                + itemIDCache + "/" + tramitador;
    }

    function crearIncidencia(itemID) {
        itemIDCache = itemID;
        tipus = 1;
        $("#myModal").modal();
    }

    function crearSolicitud(itemID) {
        itemIDCache = itemID;
        tipus = 2;
        $("#myModal").modal();
    }
</script>


<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Selecciona Tramitador</h4>
            </div>
            <div class="modal-body">
            
                <%  request.setAttribute("currentuser", request.getRemoteUser()); %>
            
                Selecciona tramitador: <select id="tramitador">
                
                    <c:forEach items="${tramitadors}" var="tramitador">
                      <option value="${tramitador.key}"  ${(currentuser eq tramitador.key)?'selected':''}>${tramitador.value}</option>
                    </c:forEach>

                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onClick="myFunction()"
                    data-dismiss="modal">Ok</button>
            </div>
        </div>

    </div>
</div>

