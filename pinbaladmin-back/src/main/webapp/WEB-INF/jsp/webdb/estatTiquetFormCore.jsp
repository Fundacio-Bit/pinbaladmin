<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatTiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatTiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatTiquetFields.ESTATTIQUETID)}">
        <tr id="estatTiquet_estatTiquetID_rowid">
          <td id="estatTiquet_estatTiquetID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatTiquetFields.ESTATTIQUETID])?'estatTiquet.estatTiquetID':__theForm.labels[EstatTiquetFields.ESTATTIQUETID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatTiquetFields.ESTATTIQUETID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatTiquetFields.ESTATTIQUETID]}" ></i>
              </c:if>
            </td>
          <td id="estatTiquet_estatTiquetID_columnvalueid">
            <form:errors path="estatTiquet.estatTiquetID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.ESTATTIQUETID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.ESTATTIQUETID)? ' uneditable-input' : ''}"  style=""  path="estatTiquet.estatTiquetID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatTiquetFields.NOM)}">
        <tr id="estatTiquet_nom_rowid">
          <td id="estatTiquet_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatTiquetFields.NOM])?'estatTiquet.nom':__theForm.labels[EstatTiquetFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatTiquetFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatTiquetFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="estatTiquet_nom_columnvalueid">
            <form:errors path="estatTiquet.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EstatTiquetFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="estatTiquet.nom"   />

           </td>
        </tr>
        </c:if>
        
