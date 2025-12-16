<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="modal-header" style="display: block;">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">Permisos de l'usuari: ${usuariCodi} <small>(${entitatCodi})</small></h4>
</div>
<div class="modal-body">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    
    <c:if test="${empty error}">
        <c:choose>
            <c:when test="${not empty permisos}">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-filter"></i></span>
                                <input type="text" class="form-control" id="filterProcediment" placeholder="Filtrar per procediment...">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-filter"></i></span>
                                <input type="text" class="form-control" id="filterServei" placeholder="Filtrar per servei...">
                            </div>
                        </div>
                    </div>
                </div>
                
                <table class="table table-striped table-bordered" id="tablaPermisos">
                    <thead>
                        <tr>
                            <th>Procediment</th>
                            <th>Servei</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${permisos}" var="permis">
                            <tr>
                                <td>${permis.procedimentCodi}</td>
                                <td>${permis.serveiCodi}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <script>
                    $(document).ready(function() {
                        function filterTable() {
                            var serveiValue = $("#filterServei").val().toLowerCase();
                            var procedimentValue = $("#filterProcediment").val().toLowerCase();
                            
                            $("#tablaPermisos tbody tr").filter(function() {
                                var serveiText = $(this).find("td:eq(0)").text().toLowerCase();
                                var procedimentText = $(this).find("td:eq(1)").text().toLowerCase();
                                
                                var showServei = serveiText.indexOf(serveiValue) > -1;
                                var showProcediment = procedimentText.indexOf(procedimentValue) > -1;
                                
                                $(this).toggle(showServei && showProcediment);
                            });
                        }

                        $("#filterServei, #filterProcediment").on("keyup", filterTable);
                    });
                </script>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">Aquest usuari no té permisos assignats.</div>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Tancar</button>
</div>
