<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OperadorFields" className="org.fundaciobit.pinbaladmin.model.fields.OperadorFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.USERNAME)}">
        <tr id="operador_username_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.USERNAME])?'operador.username':__theForm.labels[OperadorFields.USERNAME]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[OperadorFields.USERNAME]}">
              <i class="icon-info-sign" title="${__theForm.help[OperadorFields.USERNAME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="operador.username" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.USERNAME)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,OperadorFields.USERNAME)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="operador.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.NOM)}">
        <tr id="operador_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.NOM])?'operador.nom':__theForm.labels[OperadorFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[OperadorFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[OperadorFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="operador.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,OperadorFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="operador.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OperadorFields.EMAIL)}">
        <tr id="operador_email_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[OperadorFields.EMAIL])?'operador.email':__theForm.labels[OperadorFields.EMAIL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[OperadorFields.EMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[OperadorFields.EMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="operador.email" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OperadorFields.EMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,OperadorFields.EMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="operador.email"   />

           </td>
        </tr>
        </c:if>
        
