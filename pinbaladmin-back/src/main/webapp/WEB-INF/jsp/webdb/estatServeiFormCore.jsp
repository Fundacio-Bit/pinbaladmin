<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.ESTATSERVEIID)}">
        <tr id="estatServei_estatServeiID_rowid">
          <td id="estatServei_estatServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.ESTATSERVEIID])?'estatServei.estatServeiID':__theForm.labels[EstatServeiFields.ESTATSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatServeiFields.ESTATSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatServeiFields.ESTATSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="estatServei_estatServeiID_columnvalueid">
            <form:errors path="estatServei.estatServeiID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.ESTATSERVEIID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatServeiFields.ESTATSERVEIID)? ' uneditable-input' : ''}"  style=""  path="estatServei.estatServeiID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.NOM)}">
        <tr id="estatServei_nom_rowid">
          <td id="estatServei_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.NOM])?'estatServei.nom':__theForm.labels[EstatServeiFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatServeiFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatServeiFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="estatServei_nom_columnvalueid">
            <form:errors path="estatServei.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatServeiFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="estatServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.DESCRIPCIO)}">
        <tr id="estatServei_descripcio_rowid">
          <td id="estatServei_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.DESCRIPCIO])?'estatServei.descripcio':__theForm.labels[EstatServeiFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatServeiFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="estatServei_descripcio_columnvalueid">
              <form:errors path="estatServei.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="estatServei.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
