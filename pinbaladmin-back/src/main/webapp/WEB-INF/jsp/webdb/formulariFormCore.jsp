<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FormulariFields" className="org.fundaciobit.pinbaladmin.model.fields.FormulariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.NOM)}">
        <tr id="formulari_nom_rowid">
          <td id="formulari_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.NOM])?'formulari.nom':__theForm.labels[FormulariFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FormulariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FormulariFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="formulari_nom_columnvalueid">
            <form:errors path="formulari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FormulariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="formulari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.DESCRIPCIO)}">
        <tr id="formulari_descripcio_rowid">
          <td id="formulari_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.DESCRIPCIO])?'formulari.descripcio':__theForm.labels[FormulariFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[FormulariFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FormulariFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="formulari_descripcio_columnvalueid">
              <form:errors path="formulari.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.DESCRIPCIO)? 'true' : 'false'}" path="formulari.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.FITXERID)}">
        <tr id="formulari_fitxerID_rowid">
          <td id="formulari_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.FITXERID])?'formulari.fitxerID':__theForm.labels[FormulariFields.FITXERID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FormulariFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FormulariFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="formulari_fitxerID_columnvalueid">
              <form:errors path="formulari.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.formulari.fitxer)}"/>">${__theForm.formulari.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.formulari.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.formulari.fitxer)}"/>">${__theForm.formulari.fitxer.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
