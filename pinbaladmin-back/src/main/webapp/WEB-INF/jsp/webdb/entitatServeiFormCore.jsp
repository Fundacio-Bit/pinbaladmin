<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.NOM)}">
        <tr id="entitatServei_nom_rowid">
          <td id="entitatServei_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.NOM])?'entitatServei.nom':__theForm.labels[EntitatServeiFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatServeiFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatServeiFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="entitatServei_nom_columnvalueid">
            <form:errors path="entitatServei.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitatServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.DESCRIPCIO)}">
        <tr id="entitatServei_descripcio_rowid">
          <td id="entitatServei_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.DESCRIPCIO])?'entitatServei.descripcio':__theForm.labels[EntitatServeiFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatServeiFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="entitatServei_descripcio_columnvalueid">
              <form:errors path="entitatServei.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="entitatServei.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.BALEARS)}">
        <tr id="entitatServei_balears_rowid">
          <td id="entitatServei_balears_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.BALEARS])?'entitatServei.balears':__theForm.labels[EntitatServeiFields.BALEARS]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatServeiFields.BALEARS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatServeiFields.BALEARS]}" ></i>
              </c:if>
            </td>
          <td id="entitatServei_balears_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)}" >
              <form:errors path="entitatServei.balears" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)? 'false' : 'true'}" path="entitatServei.balears" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitatServei.balears}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
