<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AreaFields" className="org.fundaciobit.pinbaladmin.model.fields.AreaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AreaFields.NOM)}">
        <tr id="area_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AreaFields.NOM])?'area.nom':__theForm.labels[AreaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AreaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[AreaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="area.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AreaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AreaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="area.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AreaFields.ENTITATID)}">
        <tr id="area_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AreaFields.ENTITATID])?'area.entitatID':__theForm.labels[AreaFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AreaFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[AreaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="area.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AreaFields.ENTITATID)}" >
          <form:hidden path="area.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.area.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AreaFields.ENTITATID)}" >
          <form:select id="area_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="area.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
