<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOM)}">
        <tr id="entitat_nom_rowid">
          <td id="entitat_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOM])?'entitat.nom':__theForm.labels[EntitatFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="entitat_nom_columnvalueid">
            <form:errors path="entitat.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="500" path="entitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PERSONACONTACTE)}">
        <tr id="entitat_personaContacte_rowid">
          <td id="entitat_personaContacte_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PERSONACONTACTE])?'entitat.personaContacte':__theForm.labels[EntitatFields.PERSONACONTACTE]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.PERSONACONTACTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PERSONACONTACTE]}" ></i>
              </c:if>
            </td>
          <td id="entitat_personaContacte_columnvalueid">
            <form:errors path="entitat.personaContacte" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.PERSONACONTACTE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.PERSONACONTACTE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.personaContacte"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CIF)}">
        <tr id="entitat_CIF_rowid">
          <td id="entitat_CIF_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CIF])?'entitat.CIF':__theForm.labels[EntitatFields.CIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.CIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CIF]}" ></i>
              </c:if>
            </td>
          <td id="entitat_CIF_columnvalueid">
            <form:errors path="entitat.CIF" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CIF)? ' uneditable-input' : ''}"  style="" maxlength="10" path="entitat.CIF"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.GRUPENTITATID)}">
        <tr id="entitat_grupEntitatID_rowid">
          <td id="entitat_grupEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.GRUPENTITATID])?'entitat.grupEntitatID':__theForm.labels[EntitatFields.GRUPENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.GRUPENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.GRUPENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_grupEntitatID_columnvalueid">
          <form:errors path="entitat.grupEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.GRUPENTITATID)}" >
          <form:hidden path="entitat.grupEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.grupEntitatID,__theForm.listOfGrupEntitatForGrupEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.GRUPENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_grupEntitatID"  onchange="if(typeof onChangeGrupEntitatID == 'function') {  onChangeGrupEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.grupEntitatID">
            <c:forEach items="${__theForm.listOfGrupEntitatForGrupEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CONVENIPMSBAE)}">
        <tr id="entitat_convenipmsbae_rowid">
          <td id="entitat_convenipmsbae_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CONVENIPMSBAE])?'entitat.convenipmsbae':__theForm.labels[EntitatFields.CONVENIPMSBAE]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.CONVENIPMSBAE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CONVENIPMSBAE]}" ></i>
              </c:if>
            </td>
          <td id="entitat_convenipmsbae_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.CONVENIPMSBAE)}" >
              <form:errors path="entitat.convenipmsbae" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CONVENIPMSBAE)? 'false' : 'true'}" path="entitat.convenipmsbae" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.CONVENIPMSBAE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.convenipmsbae}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.DIR3)}">
        <tr id="entitat_dir3_rowid">
          <td id="entitat_dir3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.DIR3])?'entitat.dir3':__theForm.labels[EntitatFields.DIR3]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.DIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.DIR3]}" ></i>
              </c:if>
            </td>
          <td id="entitat_dir3_columnvalueid">
            <form:errors path="entitat.dir3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.DIR3)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.DIR3)? ' uneditable-input' : ''}"  style="" maxlength="30" path="entitat.dir3"   />

           </td>
        </tr>
        </c:if>
        
