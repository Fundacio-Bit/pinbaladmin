<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusTiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TipusTiquetFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusTiquetFields.TIPUSTIQUETID)}">
        <tr id="tipusTiquet_tipusTiquetID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusTiquetFields.TIPUSTIQUETID])?'tipusTiquet.tipusTiquetID':__theForm.labels[TipusTiquetFields.TIPUSTIQUETID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusTiquetFields.TIPUSTIQUETID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusTiquetFields.TIPUSTIQUETID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusTiquet.tipusTiquetID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.TIPUSTIQUETID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.TIPUSTIQUETID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusTiquet.tipusTiquetID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusTiquetFields.NOM)}">
        <tr id="tipusTiquet_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusTiquetFields.NOM])?'tipusTiquet.nom':__theForm.labels[TipusTiquetFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusTiquetFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusTiquetFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusTiquet.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusTiquetFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tipusTiquet.nom"   />

           </td>
        </tr>
        </c:if>
        
