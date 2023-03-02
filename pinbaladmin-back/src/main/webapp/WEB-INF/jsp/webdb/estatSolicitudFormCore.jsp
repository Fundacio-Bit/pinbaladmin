<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.ESTATSOLICITUDID)}">
        <tr id="estatSolicitud_estatSolicitudID_rowid">
          <td id="estatSolicitud_estatSolicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.ESTATSOLICITUDID])?'estatSolicitud.estatSolicitudID':__theForm.labels[EstatSolicitudFields.ESTATSOLICITUDID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.ESTATSOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudFields.ESTATSOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitud_estatSolicitudID_columnvalueid">
            <form:errors path="estatSolicitud.estatSolicitudID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.ESTATSOLICITUDID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.ESTATSOLICITUDID)? ' uneditable-input' : ''}"  style=""  path="estatSolicitud.estatSolicitudID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.NOM)}">
        <tr id="estatSolicitud_nom_rowid">
          <td id="estatSolicitud_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.NOM])?'estatSolicitud.nom':__theForm.labels[EstatSolicitudFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitud_nom_columnvalueid">
            <form:errors path="estatSolicitud.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="estatSolicitud.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.DESCRIPCIO)}">
        <tr id="estatSolicitud_descripcio_rowid">
          <td id="estatSolicitud_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.DESCRIPCIO])?'estatSolicitud.descripcio':__theForm.labels[EstatSolicitudFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitud_descripcio_columnvalueid">
              <form:errors path="estatSolicitud.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.DESCRIPCIO)? 'true' : 'false'}" path="estatSolicitud.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
