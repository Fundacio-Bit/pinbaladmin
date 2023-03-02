<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OperadorFields" className="org.fundaciobit.pinbaladmin.model.fields.OperadorFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.USERNAME)}">
        <tr id="operador_username_rowid">
          <td id="operador_username_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.USERNAME])?'operador.username':__theForm.labels[OperadorFields.USERNAME]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[OperadorFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OperadorFields.USERNAME]}" ></i>
              </c:if>
            </td>
          <td id="operador_username_columnvalueid">
            <form:errors path="operador.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.USERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OperadorFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="operador.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.NOM)}">
        <tr id="operador_nom_rowid">
          <td id="operador_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.NOM])?'operador.nom':__theForm.labels[OperadorFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[OperadorFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OperadorFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="operador_nom_columnvalueid">
            <form:errors path="operador.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OperadorFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="operador.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.EMAIL)}">
        <tr id="operador_email_rowid">
          <td id="operador_email_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.EMAIL])?'operador.email':__theForm.labels[OperadorFields.EMAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[OperadorFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OperadorFields.EMAIL]}" ></i>
              </c:if>
            </td>
          <td id="operador_email_columnvalueid">
            <form:errors path="operador.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.EMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OperadorFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="operador.email"   />

           </td>
        </tr>
        </c:if>
        
