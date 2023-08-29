<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitFCteTecFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.TRAMITID)}">
        <tr id="tramitFCteTec_tramitid_rowid">
          <td id="tramitFCteTec_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.TRAMITID])?'tramitFCteTec.tramitid':__theForm.labels[TramitFCteTecFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_tramitid_columnvalueid">
          <form:errors path="tramitFCteTec.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.TRAMITID)}" >
          <form:hidden path="tramitFCteTec.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitFCteTec.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitFCteTec_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitFCteTec.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.NIF)}">
        <tr id="tramitFCteTec_nif_rowid">
          <td id="tramitFCteTec_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.NIF])?'tramitFCteTec.nif':__theForm.labels[TramitFCteTecFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_nif_columnvalueid">
            <form:errors path="tramitFCteTec.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitFCteTec.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.NOM)}">
        <tr id="tramitFCteTec_nom_rowid">
          <td id="tramitFCteTec_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.NOM])?'tramitFCteTec.nom':__theForm.labels[TramitFCteTecFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_nom_columnvalueid">
            <form:errors path="tramitFCteTec.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitFCteTec.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.LLINATGE1)}">
        <tr id="tramitFCteTec_llinatge1_rowid">
          <td id="tramitFCteTec_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.LLINATGE1])?'tramitFCteTec.llinatge1':__theForm.labels[TramitFCteTecFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_llinatge1_columnvalueid">
            <form:errors path="tramitFCteTec.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitFCteTec.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.LLINATGE2)}">
        <tr id="tramitFCteTec_llinatge2_rowid">
          <td id="tramitFCteTec_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.LLINATGE2])?'tramitFCteTec.llinatge2':__theForm.labels[TramitFCteTecFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_llinatge2_columnvalueid">
            <form:errors path="tramitFCteTec.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitFCteTec.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.CARREC)}">
        <tr id="tramitFCteTec_carrec_rowid">
          <td id="tramitFCteTec_carrec_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.CARREC])?'tramitFCteTec.carrec':__theForm.labels[TramitFCteTecFields.CARREC]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_carrec_columnvalueid">
            <form:errors path="tramitFCteTec.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitFCteTec.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.TELEFON)}">
        <tr id="tramitFCteTec_telefon_rowid">
          <td id="tramitFCteTec_telefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.TELEFON])?'tramitFCteTec.telefon':__theForm.labels[TramitFCteTecFields.TELEFON]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.TELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.TELEFON]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_telefon_columnvalueid">
            <form:errors path="tramitFCteTec.telefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.TELEFON)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.TELEFON)? ' uneditable-input' : ''}"  style="" maxlength="10" path="tramitFCteTec.telefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitFCteTecFields.MAIL)}">
        <tr id="tramitFCteTec_mail_rowid">
          <td id="tramitFCteTec_mail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitFCteTecFields.MAIL])?'tramitFCteTec.mail':__theForm.labels[TramitFCteTecFields.MAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitFCteTecFields.MAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitFCteTecFields.MAIL]}" ></i>
              </c:if>
            </td>
          <td id="tramitFCteTec_mail_columnvalueid">
            <form:errors path="tramitFCteTec.mail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.MAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitFCteTecFields.MAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitFCteTec.mail"   />

           </td>
        </tr>
        </c:if>
        
