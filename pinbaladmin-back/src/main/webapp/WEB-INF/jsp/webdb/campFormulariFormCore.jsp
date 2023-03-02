<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampFormulariFields" className="org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.NOM)}">
        <tr id="campFormulari_nom_rowid">
          <td id="campFormulari_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.NOM])?'campFormulari.nom':__theForm.labels[CampFormulariFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampFormulariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampFormulariFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="campFormulari_nom_columnvalueid">
            <form:errors path="campFormulari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CampFormulariFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="campFormulari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.CAMPPDF)}">
        <tr id="campFormulari_campPDF_rowid">
          <td id="campFormulari_campPDF_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.CAMPPDF])?'campFormulari.campPDF':__theForm.labels[CampFormulariFields.CAMPPDF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampFormulariFields.CAMPPDF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampFormulariFields.CAMPPDF]}" ></i>
              </c:if>
            </td>
          <td id="campFormulari_campPDF_columnvalueid">
            <form:errors path="campFormulari.campPDF" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CampFormulariFields.CAMPPDF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.CAMPPDF)? ' uneditable-input' : ''}"  style="" maxlength="100" path="campFormulari.campPDF"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CampFormulariFields.FORMULARIID)}">
        <tr id="campFormulari_formulariID_rowid">
          <td id="campFormulari_formulariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CampFormulariFields.FORMULARIID])?'campFormulari.formulariID':__theForm.labels[CampFormulariFields.FORMULARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CampFormulariFields.FORMULARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CampFormulariFields.FORMULARIID]}" ></i>
              </c:if>
            </td>
          <td id="campFormulari_formulariID_columnvalueid">
          <form:errors path="campFormulari.formulariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CampFormulariFields.FORMULARIID)}" >
          <form:hidden path="campFormulari.formulariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.campFormulari.formulariID,__theForm.listOfFormulariForFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CampFormulariFields.FORMULARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="campFormulari_formulariID"  onchange="if(typeof onChangeFormulariID == 'function') {  onChangeFormulariID(this); };"  cssClass="form-control col-md-9-optional" path="campFormulari.formulariID">
            <c:forEach items="${__theForm.listOfFormulariForFormulariID}" var="tmp">
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
        
