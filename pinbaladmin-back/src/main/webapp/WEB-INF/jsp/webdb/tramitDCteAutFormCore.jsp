<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitDCteAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.TRAMITID)}">
        <tr id="tramitDCteAut_tramitid_rowid">
          <td id="tramitDCteAut_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.TRAMITID])?'tramitDCteAut.tramitid':__theForm.labels[TramitDCteAutFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_tramitid_columnvalueid">
          <form:errors path="tramitDCteAut.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.TRAMITID)}" >
          <form:hidden path="tramitDCteAut.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitDCteAut.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitDCteAut_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitDCteAut.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.NIF)}">
        <tr id="tramitDCteAut_nif_rowid">
          <td id="tramitDCteAut_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.NIF])?'tramitDCteAut.nif':__theForm.labels[TramitDCteAutFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_nif_columnvalueid">
            <form:errors path="tramitDCteAut.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitDCteAut.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.NOM)}">
        <tr id="tramitDCteAut_nom_rowid">
          <td id="tramitDCteAut_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.NOM])?'tramitDCteAut.nom':__theForm.labels[TramitDCteAutFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_nom_columnvalueid">
            <form:errors path="tramitDCteAut.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitDCteAut.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.LLINATGE1)}">
        <tr id="tramitDCteAut_llinatge1_rowid">
          <td id="tramitDCteAut_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.LLINATGE1])?'tramitDCteAut.llinatge1':__theForm.labels[TramitDCteAutFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_llinatge1_columnvalueid">
            <form:errors path="tramitDCteAut.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitDCteAut.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.LLINATGE2)}">
        <tr id="tramitDCteAut_llinatge2_rowid">
          <td id="tramitDCteAut_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.LLINATGE2])?'tramitDCteAut.llinatge2':__theForm.labels[TramitDCteAutFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_llinatge2_columnvalueid">
            <form:errors path="tramitDCteAut.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitDCteAut.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.CARREC)}">
        <tr id="tramitDCteAut_carrec_rowid">
          <td id="tramitDCteAut_carrec_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.CARREC])?'tramitDCteAut.carrec':__theForm.labels[TramitDCteAutFields.CARREC]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_carrec_columnvalueid">
            <form:errors path="tramitDCteAut.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitDCteAut.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.TELEFON)}">
        <tr id="tramitDCteAut_telefon_rowid">
          <td id="tramitDCteAut_telefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.TELEFON])?'tramitDCteAut.telefon':__theForm.labels[TramitDCteAutFields.TELEFON]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.TELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.TELEFON]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_telefon_columnvalueid">
            <form:errors path="tramitDCteAut.telefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.TELEFON)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.TELEFON)? ' uneditable-input' : ''}"  style="" maxlength="10" path="tramitDCteAut.telefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitDCteAutFields.MAIL)}">
        <tr id="tramitDCteAut_mail_rowid">
          <td id="tramitDCteAut_mail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitDCteAutFields.MAIL])?'tramitDCteAut.mail':__theForm.labels[TramitDCteAutFields.MAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitDCteAutFields.MAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitDCteAutFields.MAIL]}" ></i>
              </c:if>
            </td>
          <td id="tramitDCteAut_mail_columnvalueid">
            <form:errors path="tramitDCteAut.mail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.MAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitDCteAutFields.MAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitDCteAut.mail"   />

           </td>
        </tr>
        </c:if>
        
