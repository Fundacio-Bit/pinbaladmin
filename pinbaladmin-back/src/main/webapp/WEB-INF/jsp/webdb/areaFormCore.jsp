<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AreaFields" className="org.fundaciobit.pinbaladmin.model.fields.AreaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AreaFields.NOM)}">
        <tr id="area_nom_rowid">
          <td id="area_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AreaFields.NOM])?'area.nom':__theForm.labels[AreaFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AreaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AreaFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="area_nom_columnvalueid">
            <form:errors path="area.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AreaFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AreaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="area.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AreaFields.ENTITATID)}">
        <tr id="area_entitatID_rowid">
          <td id="area_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AreaFields.ENTITATID])?'area.entitatID':__theForm.labels[AreaFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AreaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AreaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="area_entitatID_columnvalueid">
          <form:errors path="area.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AreaFields.ENTITATID)}" >
          <form:hidden path="area.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.area.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AreaFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="area_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="area.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
