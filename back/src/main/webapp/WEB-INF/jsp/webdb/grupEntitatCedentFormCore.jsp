<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatCedentFields" className="org.fundaciobit.pinbaladmin.model.fields.GrupEntitatCedentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatCedentFields.GRUPENTITATID)}">
        <tr id="grupEntitatCedent_grupEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatCedentFields.GRUPENTITATID])?'grupEntitatCedent.grupEntitatID':__theForm.labels[GrupEntitatCedentFields.GRUPENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GrupEntitatCedentFields.GRUPENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatCedentFields.GRUPENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="grupEntitatCedent.grupEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatCedentFields.GRUPENTITATID)}" >
          <form:hidden path="grupEntitatCedent.grupEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.grupEntitatCedent.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatCedentFields.GRUPENTITATID)}" >
          <form:select id="grupEntitatCedent_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="input-xxlarge" path="grupEntitatCedent.grupEntitatID">
            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatCedentFields.CEDENTID)}">
        <tr id="grupEntitatCedent_cedentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatCedentFields.CEDENTID])?'grupEntitatCedent.cedentID':__theForm.labels[GrupEntitatCedentFields.CEDENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GrupEntitatCedentFields.CEDENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatCedentFields.CEDENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="grupEntitatCedent.cedentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupEntitatCedentFields.CEDENTID)}" >
          <form:hidden path="grupEntitatCedent.cedentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.grupEntitatCedent.cedentID,__theForm.listOfEntitatServeiForCedentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupEntitatCedentFields.CEDENTID)}" >
          <form:select id="grupEntitatCedent_cedentID"  onchange="if(typeof onChangeCedentID == 'function') {  onChangeCedentID(this); };"  cssClass="input-xxlarge" path="grupEntitatCedent.cedentID">
            <c:forEach items="${__theForm.listOfEntitatServeiForCedentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
