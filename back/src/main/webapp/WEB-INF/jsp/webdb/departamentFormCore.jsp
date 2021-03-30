<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DepartamentFields" className="org.fundaciobit.pinbaladmin.model.fields.DepartamentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DepartamentFields.NOM)}">
        <tr id="departament_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DepartamentFields.NOM])?'departament.nom':__theForm.labels[DepartamentFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DepartamentFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[DepartamentFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="departament.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DepartamentFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DepartamentFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="departament.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DepartamentFields.AREAID)}">
        <tr id="departament_areaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DepartamentFields.AREAID])?'departament.areaID':__theForm.labels[DepartamentFields.AREAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DepartamentFields.AREAID]}">
              <i class="icon-info-sign" title="${__theForm.help[DepartamentFields.AREAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="departament.areaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DepartamentFields.AREAID)}" >
          <form:hidden path="departament.areaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.departament.areaID,__theForm.listOfAreaForAreaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DepartamentFields.AREAID)}" >
          <form:select id="departament_areaID"  onchange="if(typeof onChangeAreaID == 'function') {  onChangeAreaID(this); };"  cssClass="input-xxlarge" path="departament.areaID">
            <c:forEach items="${__theForm.listOfAreaForAreaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
