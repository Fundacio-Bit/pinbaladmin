<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusTiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TipusTiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusTiquetFields.TIPUSTIQUETID)}">
        <tr id="tipusTiquet_tipusTiquetID_rowid">
          <td id="tipusTiquet_tipusTiquetID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusTiquetFields.TIPUSTIQUETID])?'tipusTiquet.tipusTiquetID':__theForm.labels[TipusTiquetFields.TIPUSTIQUETID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusTiquetFields.TIPUSTIQUETID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusTiquetFields.TIPUSTIQUETID]}" ></i>
              </c:if>
            </td>
          <td id="tipusTiquet_tipusTiquetID_columnvalueid">
            <form:errors path="tipusTiquet.tipusTiquetID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.TIPUSTIQUETID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.TIPUSTIQUETID)? ' uneditable-input' : ''}"  style=""  path="tipusTiquet.tipusTiquetID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusTiquetFields.NOM)}">
        <tr id="tipusTiquet_nom_rowid">
          <td id="tipusTiquet_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusTiquetFields.NOM])?'tipusTiquet.nom':__theForm.labels[TipusTiquetFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusTiquetFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusTiquetFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tipusTiquet_nom_columnvalueid">
            <form:errors path="tipusTiquet.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tipusTiquet.nom"   />

           </td>
        </tr>
        </c:if>
        
