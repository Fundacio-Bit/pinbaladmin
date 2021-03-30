<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOM)}">
        <tr id="entitat_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOM])?'entitat.nom':__theForm.labels[EntitatFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="500" path="entitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PERSONACONTACTE)}">
        <tr id="entitat_personaContacte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PERSONACONTACTE])?'entitat.personaContacte':__theForm.labels[EntitatFields.PERSONACONTACTE]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PERSONACONTACTE]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatFields.PERSONACONTACTE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.personaContacte" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.PERSONACONTACTE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PERSONACONTACTE)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="entitat.personaContacte"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CIF)}">
        <tr id="entitat_CIF_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CIF])?'entitat.CIF':__theForm.labels[EntitatFields.CIF]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.CIF]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatFields.CIF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.CIF" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CIF)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EntitatFields.CIF)? 'input-small uneditable-input' : 'input-small'}"  maxlength="10" path="entitat.CIF"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.GRUPENTITATID)}">
        <tr id="entitat_grupEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.GRUPENTITATID])?'entitat.grupEntitatID':__theForm.labels[EntitatFields.GRUPENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.GRUPENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatFields.GRUPENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.grupEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.GRUPENTITATID)}" >
          <form:hidden path="entitat.grupEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.entitat.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.GRUPENTITATID)}" >
          <form:select id="entitat_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="input-xxlarge" path="entitat.grupEntitatID">
            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
