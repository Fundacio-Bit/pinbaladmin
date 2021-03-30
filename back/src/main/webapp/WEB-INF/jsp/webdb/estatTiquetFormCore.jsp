<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatTiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatTiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatTiquetFields.ESTATTIQUETID)}">
        <tr id="estatTiquet_estatTiquetID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatTiquetFields.ESTATTIQUETID])?'estatTiquet.estatTiquetID':__theForm.labels[EstatTiquetFields.ESTATTIQUETID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatTiquetFields.ESTATTIQUETID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatTiquetFields.ESTATTIQUETID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatTiquet.estatTiquetID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.ESTATTIQUETID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.ESTATTIQUETID)? 'input-large uneditable-input' : 'input-large'}"   path="estatTiquet.estatTiquetID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatTiquetFields.NOM)}">
        <tr id="estatTiquet_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatTiquetFields.NOM])?'estatTiquet.nom':__theForm.labels[EstatTiquetFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatTiquetFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatTiquetFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatTiquet.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="estatTiquet.nom"   />

           </td>
        </tr>
        </c:if>
        
