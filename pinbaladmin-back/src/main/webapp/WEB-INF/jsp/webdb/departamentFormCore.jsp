<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DepartamentFields" className="org.fundaciobit.pinbaladmin.model.fields.DepartamentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DepartamentFields.NOM)}">
        <tr id="departament_nom_rowid">
          <td id="departament_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DepartamentFields.NOM])?'departament.nom':__theForm.labels[DepartamentFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DepartamentFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DepartamentFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="departament_nom_columnvalueid">
            <form:errors path="departament.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DepartamentFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,DepartamentFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="departament.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DepartamentFields.AREAID)}">
        <tr id="departament_areaID_rowid">
          <td id="departament_areaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DepartamentFields.AREAID])?'departament.areaID':__theForm.labels[DepartamentFields.AREAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DepartamentFields.AREAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DepartamentFields.AREAID]}" ></i>
              </c:if>
            </td>
          <td id="departament_areaID_columnvalueid">
          <form:errors path="departament.areaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DepartamentFields.AREAID)}" >
          <form:hidden path="departament.areaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.departament.areaID,__theForm.listOfAreaForAreaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DepartamentFields.AREAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="departament_areaID"  onchange="if(typeof onChangeAreaID == 'function') {  onChangeAreaID(this); };"  cssClass="form-control col-md-9-optional" path="departament.areaID">
            <c:forEach items="${__theForm.listOfAreaForAreaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
