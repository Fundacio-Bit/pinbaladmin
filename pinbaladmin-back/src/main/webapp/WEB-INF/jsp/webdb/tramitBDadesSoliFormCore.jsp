<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitBDadesSoliFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitBDadesSoliFields.TRAMITID)}">
        <tr id="tramitBDadesSoli_tramitid_rowid">
          <td id="tramitBDadesSoli_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitBDadesSoliFields.TRAMITID])?'tramitBDadesSoli.tramitid':__theForm.labels[TramitBDadesSoliFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitBDadesSoliFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitBDadesSoliFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitBDadesSoli_tramitid_columnvalueid">
          <form:errors path="tramitBDadesSoli.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.TRAMITID)}" >
          <form:hidden path="tramitBDadesSoli.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitBDadesSoli.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitBDadesSoli_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitBDadesSoli.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitBDadesSoliFields.TIPUSSOLICITUD)}">
        <tr id="tramitBDadesSoli_tipussolicitud_rowid">
          <td id="tramitBDadesSoli_tipussolicitud_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitBDadesSoliFields.TIPUSSOLICITUD])?'tramitBDadesSoli.tipussolicitud':__theForm.labels[TramitBDadesSoliFields.TIPUSSOLICITUD]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitBDadesSoliFields.TIPUSSOLICITUD]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitBDadesSoliFields.TIPUSSOLICITUD]}" ></i>
              </c:if>
            </td>
          <td id="tramitBDadesSoli_tipussolicitud_columnvalueid">
          <form:errors path="tramitBDadesSoli.tipussolicitud" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.TIPUSSOLICITUD)}" >
          <form:hidden path="tramitBDadesSoli.tipussolicitud"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitBDadesSoli.tipussolicitud,__theForm.listOfValuesForTipussolicitud)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.TIPUSSOLICITUD)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitBDadesSoli_tipussolicitud"  onchange="if(typeof onChangeTipussolicitud == 'function') {  onChangeTipussolicitud(this); };"  cssClass="form-control col-md-9-optional" path="tramitBDadesSoli.tipussolicitud">
            <c:forEach items="${__theForm.listOfValuesForTipussolicitud}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitBDadesSoliFields.ENTORN)}">
        <tr id="tramitBDadesSoli_entorn_rowid">
          <td id="tramitBDadesSoli_entorn_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitBDadesSoliFields.ENTORN])?'tramitBDadesSoli.entorn':__theForm.labels[TramitBDadesSoliFields.ENTORN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitBDadesSoliFields.ENTORN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitBDadesSoliFields.ENTORN]}" ></i>
              </c:if>
            </td>
          <td id="tramitBDadesSoli_entorn_columnvalueid">
          <form:errors path="tramitBDadesSoli.entorn" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.ENTORN)}" >
          <form:hidden path="tramitBDadesSoli.entorn"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitBDadesSoli.entorn,__theForm.listOfValuesForEntorn)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitBDadesSoliFields.ENTORN)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitBDadesSoli_entorn"  onchange="if(typeof onChangeEntorn == 'function') {  onChangeEntorn(this); };"  cssClass="form-control col-md-9-optional" path="tramitBDadesSoli.entorn">
            <c:forEach items="${__theForm.listOfValuesForEntorn}" var="tmp">
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
        
