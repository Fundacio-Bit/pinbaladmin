<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitGDadesTitFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.TRAMITID)}">
        <tr id="tramitGDadesTit_tramitid_rowid">
          <td id="tramitGDadesTit_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.TRAMITID])?'tramitGDadesTit.tramitid':__theForm.labels[TramitGDadesTitFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_tramitid_columnvalueid">
          <form:errors path="tramitGDadesTit.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.TRAMITID)}" >
          <form:hidden path="tramitGDadesTit.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitGDadesTit.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitGDadesTit_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitGDadesTit.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.NIF)}">
        <tr id="tramitGDadesTit_nif_rowid">
          <td id="tramitGDadesTit_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.NIF])?'tramitGDadesTit.nif':__theForm.labels[TramitGDadesTitFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_nif_columnvalueid">
            <form:errors path="tramitGDadesTit.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitGDadesTit.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.NOM)}">
        <tr id="tramitGDadesTit_nom_rowid">
          <td id="tramitGDadesTit_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.NOM])?'tramitGDadesTit.nom':__theForm.labels[TramitGDadesTitFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_nom_columnvalueid">
            <form:errors path="tramitGDadesTit.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitGDadesTit.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.LLINATGE1)}">
        <tr id="tramitGDadesTit_llinatge1_rowid">
          <td id="tramitGDadesTit_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.LLINATGE1])?'tramitGDadesTit.llinatge1':__theForm.labels[TramitGDadesTitFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_llinatge1_columnvalueid">
            <form:errors path="tramitGDadesTit.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitGDadesTit.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.LLINATGE2)}">
        <tr id="tramitGDadesTit_llinatge2_rowid">
          <td id="tramitGDadesTit_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.LLINATGE2])?'tramitGDadesTit.llinatge2':__theForm.labels[TramitGDadesTitFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_llinatge2_columnvalueid">
            <form:errors path="tramitGDadesTit.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitGDadesTit.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitGDadesTitFields.CARREC)}">
        <tr id="tramitGDadesTit_carrec_rowid">
          <td id="tramitGDadesTit_carrec_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitGDadesTitFields.CARREC])?'tramitGDadesTit.carrec':__theForm.labels[TramitGDadesTitFields.CARREC]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitGDadesTitFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitGDadesTitFields.CARREC]}" ></i>
              </c:if>
            </td>
          <td id="tramitGDadesTit_carrec_columnvalueid">
            <form:errors path="tramitGDadesTit.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitGDadesTitFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitGDadesTit.carrec"   />

           </td>
        </tr>
        </c:if>
        
