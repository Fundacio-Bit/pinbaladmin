<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ContacteFields" className="org.fundaciobit.pinbaladmin.model.fields.ContacteFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.NIF)}">
        <tr id="contacte_nif_rowid">
          <td id="contacte_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.NIF])?'contacte.nif':__theForm.labels[ContacteFields.NIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="contacte_nif_columnvalueid">
            <form:errors path="contacte.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="contacte.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.NOM)}">
        <tr id="contacte_nom_rowid">
          <td id="contacte_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.NOM])?'contacte.nom':__theForm.labels[ContacteFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="contacte_nom_columnvalueid">
            <form:errors path="contacte.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="60" path="contacte.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.LLINATGE1)}">
        <tr id="contacte_llinatge1_rowid">
          <td id="contacte_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.LLINATGE1])?'contacte.llinatge1':__theForm.labels[ContacteFields.LLINATGE1]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="contacte_llinatge1_columnvalueid">
            <form:errors path="contacte.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="60" path="contacte.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.LLINATGE2)}">
        <tr id="contacte_llinatge2_rowid">
          <td id="contacte_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.LLINATGE2])?'contacte.llinatge2':__theForm.labels[ContacteFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="contacte_llinatge2_columnvalueid">
            <form:errors path="contacte.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="60" path="contacte.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.CARREC)}">
        <tr id="contacte_carrec_rowid">
          <td id="contacte_carrec_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.CARREC])?'contacte.carrec':__theForm.labels[ContacteFields.CARREC]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="contacte_carrec_columnvalueid">
            <form:errors path="contacte.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="120" path="contacte.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.TELEFON)}">
        <tr id="contacte_telefon_rowid">
          <td id="contacte_telefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.TELEFON])?'contacte.telefon':__theForm.labels[ContacteFields.TELEFON]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.TELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.TELEFON]}" ></i>
              </c:if>
            </td>
          <td id="contacte_telefon_columnvalueid">
            <form:errors path="contacte.telefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.TELEFON)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.TELEFON)? ' uneditable-input' : ''}"  style="" maxlength="12" path="contacte.telefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.MAIL)}">
        <tr id="contacte_mail_rowid">
          <td id="contacte_mail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.MAIL])?'contacte.mail':__theForm.labels[ContacteFields.MAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.MAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.MAIL]}" ></i>
              </c:if>
            </td>
          <td id="contacte_mail_columnvalueid">
            <form:errors path="contacte.mail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.MAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.MAIL)? ' uneditable-input' : ''}"  style="" maxlength="120" path="contacte.mail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.USERNAME)}">
        <tr id="contacte_username_rowid">
          <td id="contacte_username_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.USERNAME])?'contacte.username':__theForm.labels[ContacteFields.USERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.USERNAME]}" ></i>
              </c:if>
            </td>
          <td id="contacte_username_columnvalueid">
            <form:errors path="contacte.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.USERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ContacteFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="200" path="contacte.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ContacteFields.NOMBRECOMPLETO)}">
        <tr id="contacte_nombrecompleto_rowid">
          <td id="contacte_nombrecompleto_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ContacteFields.NOMBRECOMPLETO])?'contacte.nombrecompleto':__theForm.labels[ContacteFields.NOMBRECOMPLETO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ContacteFields.NOMBRECOMPLETO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ContacteFields.NOMBRECOMPLETO]}" ></i>
              </c:if>
            </td>
          <td id="contacte_nombrecompleto_columnvalueid">
              <form:errors path="contacte.nombrecompleto" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ContacteFields.NOMBRECOMPLETO)? 'true' : 'false'}" path="contacte.nombrecompleto"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_nombrecompleto" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_nombrecompleto" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('contacte.nombrecompleto'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('contacte.nombrecompleto'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('contacte.nombrecompleto'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_nombrecompleto').on('click', function(){
					var valor = ($('#dropdownMenuContainer_nombrecompleto').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_nombrecompleto').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
