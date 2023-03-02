<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.GrupEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatFields.NOM)}">
        <tr id="grupEntitat_nom_rowid">
          <td id="grupEntitat_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatFields.NOM])?'grupEntitat.nom':__theForm.labels[GrupEntitatFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitat_nom_columnvalueid">
            <form:errors path="grupEntitat.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="256" path="grupEntitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatFields.DESCRIPCIO)}">
        <tr id="grupEntitat_descripcio_rowid">
          <td id="grupEntitat_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatFields.DESCRIPCIO])?'grupEntitat.descripcio':__theForm.labels[GrupEntitatFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[GrupEntitatFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupEntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="grupEntitat_descripcio_columnvalueid">
              <form:errors path="grupEntitat.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="grupEntitat.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
