<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatSolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
        <tr id="estatSolicitudServei_estatSolicitudServeiID_rowid">
          <td id="estatSolicitudServei_estatSolicitudServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID])?'estatSolicitudServei.estatSolicitudServeiID':__theForm.labels[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitudServei_estatSolicitudServeiID_columnvalueid">
            <form:errors path="estatSolicitudServei.estatSolicitudServeiID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)? ' uneditable-input' : ''}"  style=""  path="estatSolicitudServei.estatSolicitudServeiID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.NOM)}">
        <tr id="estatSolicitudServei_nom_rowid">
          <td id="estatSolicitudServei_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.NOM])?'estatSolicitudServei.nom':__theForm.labels[EstatSolicitudServeiFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudServeiFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitudServei_nom_columnvalueid">
            <form:errors path="estatSolicitudServei.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="estatSolicitudServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.DESCRIPCIO)}">
        <tr id="estatSolicitudServei_descripcio_rowid">
          <td id="estatSolicitudServei_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.DESCRIPCIO])?'estatSolicitudServei.descripcio':__theForm.labels[EstatSolicitudServeiFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatSolicitudServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="estatSolicitudServei_descripcio_columnvalueid">
              <form:errors path="estatSolicitudServei.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="estatSolicitudServei.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
