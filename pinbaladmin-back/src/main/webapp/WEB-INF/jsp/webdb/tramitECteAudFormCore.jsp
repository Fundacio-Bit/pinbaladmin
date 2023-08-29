<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitECteAudFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.TRAMITID)}">
        <tr id="tramitECteAud_tramitid_rowid">
          <td id="tramitECteAud_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.TRAMITID])?'tramitECteAud.tramitid':__theForm.labels[TramitECteAudFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_tramitid_columnvalueid">
          <form:errors path="tramitECteAud.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.TRAMITID)}" >
          <form:hidden path="tramitECteAud.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitECteAud.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitECteAud_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitECteAud.tramitid">
            <c:forEach items="${__theForm.listOfTramitAPersAutForTramitid}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.NIF)}">
        <tr id="tramitECteAud_nif_rowid">
          <td id="tramitECteAud_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.NIF])?'tramitECteAud.nif':__theForm.labels[TramitECteAudFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_nif_columnvalueid">
            <form:errors path="tramitECteAud.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitECteAud.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.NOM)}">
        <tr id="tramitECteAud_nom_rowid">
          <td id="tramitECteAud_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.NOM])?'tramitECteAud.nom':__theForm.labels[TramitECteAudFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_nom_columnvalueid">
            <form:errors path="tramitECteAud.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitECteAud.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.LLINATGE1)}">
        <tr id="tramitECteAud_llinatge1_rowid">
          <td id="tramitECteAud_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.LLINATGE1])?'tramitECteAud.llinatge1':__theForm.labels[TramitECteAudFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_llinatge1_columnvalueid">
            <form:errors path="tramitECteAud.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitECteAud.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.LLINATGE2)}">
        <tr id="tramitECteAud_llinatge2_rowid">
          <td id="tramitECteAud_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.LLINATGE2])?'tramitECteAud.llinatge2':__theForm.labels[TramitECteAudFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_llinatge2_columnvalueid">
            <form:errors path="tramitECteAud.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitECteAud.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.CARREC)}">
        <tr id="tramitECteAud_carrec_rowid">
          <td id="tramitECteAud_carrec_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.CARREC])?'tramitECteAud.carrec':__theForm.labels[TramitECteAudFields.CARREC]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_carrec_columnvalueid">
            <form:errors path="tramitECteAud.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitECteAud.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.TELEFON)}">
        <tr id="tramitECteAud_telefon_rowid">
          <td id="tramitECteAud_telefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.TELEFON])?'tramitECteAud.telefon':__theForm.labels[TramitECteAudFields.TELEFON]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.TELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.TELEFON]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_telefon_columnvalueid">
            <form:errors path="tramitECteAud.telefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.TELEFON)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.TELEFON)? ' uneditable-input' : ''}"  style="" maxlength="10" path="tramitECteAud.telefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitECteAudFields.MAIL)}">
        <tr id="tramitECteAud_mail_rowid">
          <td id="tramitECteAud_mail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitECteAudFields.MAIL])?'tramitECteAud.mail':__theForm.labels[TramitECteAudFields.MAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitECteAudFields.MAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitECteAudFields.MAIL]}" ></i>
              </c:if>
            </td>
          <td id="tramitECteAud_mail_columnvalueid">
            <form:errors path="tramitECteAud.mail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.MAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitECteAudFields.MAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitECteAud.mail"   />

           </td>
        </tr>
        </c:if>
        
