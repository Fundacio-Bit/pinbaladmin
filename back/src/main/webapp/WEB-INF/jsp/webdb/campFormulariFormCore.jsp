<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampFormulariFields" className="org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.NOM)}">
        <tr id="campFormulari_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.NOM])?'campFormulari.nom':__theForm.labels[CampFormulariFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampFormulariFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[CampFormulariFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="campFormulari.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CampFormulariFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="campFormulari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.CAMPPDF)}">
        <tr id="campFormulari_campPDF_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.CAMPPDF])?'campFormulari.campPDF':__theForm.labels[CampFormulariFields.CAMPPDF]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampFormulariFields.CAMPPDF]}">
              <i class="icon-info-sign" title="${__theForm.help[CampFormulariFields.CAMPPDF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="campFormulari.campPDF" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CampFormulariFields.CAMPPDF)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.CAMPPDF)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="campFormulari.campPDF"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.FORMULARIID)}">
        <tr id="campFormulari_formulariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.FORMULARIID])?'campFormulari.formulariID':__theForm.labels[CampFormulariFields.FORMULARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CampFormulariFields.FORMULARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[CampFormulariFields.FORMULARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="campFormulari.formulariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.FORMULARIID)}" >
          <form:hidden path="campFormulari.formulariID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.campFormulari.formulariID,__theForm.listOfFormulariForFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampFormulariFields.FORMULARIID)}" >
          <form:select id="campFormulari_formulariID"  onchange="if(typeof onChangeFormulariID == 'function') {  onChangeFormulariID(this); };"  cssClass="input-xxlarge" path="campFormulari.formulariID">
            <c:forEach items="${__theForm.listOfFormulariForFormulariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
