<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PinfoDataFields" className="org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.PINFOID)}">
        <tr id="pinfoData_pinfoID_rowid">
          <td id="pinfoData_pinfoID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.PINFOID])?'pinfoData.pinfoID':__theForm.labels[PinfoDataFields.PINFOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.PINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.PINFOID]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_pinfoID_columnvalueid">
          <form:errors path="pinfoData.pinfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.PINFOID)}" >
          <form:hidden path="pinfoData.pinfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfoData.pinfoID,__theForm.listOfPINFOForPinfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoDataFields.PINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfoData_pinfoID"  onchange="if(typeof onChangePinfoID == 'function') {  onChangePinfoID(this); };"  cssClass="form-control col-md-9-optional" path="pinfoData.pinfoID">
            <c:forEach items="${__theForm.listOfPINFOForPinfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfoData.pinfoID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfoData.pinfoID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.ESTAT)}">
        <tr id="pinfoData_estat_rowid">
          <td id="pinfoData_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.ESTAT])?'pinfoData.estat':__theForm.labels[PinfoDataFields.ESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_estat_columnvalueid">
          <form:errors path="pinfoData.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.ESTAT)}" >
          <form:hidden path="pinfoData.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfoData.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoDataFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfoData_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="pinfoData.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfoData.estat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfoData.estat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.USUARIID)}">
        <tr id="pinfoData_usuariid_rowid">
          <td id="pinfoData_usuariid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.USUARIID])?'pinfoData.usuariid':__theForm.labels[PinfoDataFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_usuariid_columnvalueid">
            <form:errors path="pinfoData.usuariid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PinfoDataFields.USUARIID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.USUARIID)? ' uneditable-input' : ''}"  style="" maxlength="200" path="pinfoData.usuariid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.PROCEDIMENTID)}">
        <tr id="pinfoData_procedimentID_rowid">
          <td id="pinfoData_procedimentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.PROCEDIMENTID])?'pinfoData.procedimentID':__theForm.labels[PinfoDataFields.PROCEDIMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.PROCEDIMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.PROCEDIMENTID]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_procedimentID_columnvalueid">
          <form:errors path="pinfoData.procedimentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.PROCEDIMENTID)}" >
          <form:hidden path="pinfoData.procedimentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfoData.procedimentID,__theForm.listOfSolicitudForProcedimentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoDataFields.PROCEDIMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfoData_procedimentID"  onchange="if(typeof onChangeProcedimentID == 'function') {  onChangeProcedimentID(this); };"  cssClass="form-control col-md-9-optional" path="pinfoData.procedimentID">
            <c:forEach items="${__theForm.listOfSolicitudForProcedimentID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfoData.procedimentID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfoData.procedimentID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.SERVEIID)}">
        <tr id="pinfoData_serveiID_rowid">
          <td id="pinfoData_serveiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.SERVEIID])?'pinfoData.serveiID':__theForm.labels[PinfoDataFields.SERVEIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.SERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.SERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_serveiID_columnvalueid">
          <form:errors path="pinfoData.serveiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.SERVEIID)}" >
          <form:hidden path="pinfoData.serveiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfoData.serveiID,__theForm.listOfServeiForServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoDataFields.SERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfoData_serveiID"  onchange="if(typeof onChangeServeiID == 'function') {  onChangeServeiID(this); };"  cssClass="form-control col-md-9-optional" path="pinfoData.serveiID">
            <c:forEach items="${__theForm.listOfServeiForServeiID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfoData.serveiID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfoData.serveiID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PinfoDataFields.ALTA)}">
        <tr id="pinfoData_alta_rowid">
          <td id="pinfoData_alta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PinfoDataFields.ALTA])?'pinfoData.alta':__theForm.labels[PinfoDataFields.ALTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[PinfoDataFields.ALTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PinfoDataFields.ALTA]}" ></i>
              </c:if>
            </td>
          <td id="pinfoData_alta_columnvalueid">
          <form:errors path="pinfoData.alta" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PinfoDataFields.ALTA)}" >
          <form:hidden path="pinfoData.alta"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pinfoData.alta,__theForm.listOfValuesForAlta)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PinfoDataFields.ALTA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pinfoData_alta"  onchange="if(typeof onChangeAlta == 'function') {  onChangeAlta(this); };"  cssClass="form-control col-md-9-optional" path="pinfoData.alta">
            <c:forEach items="${__theForm.listOfValuesForAlta}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pinfoData.alta }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pinfoData.alta }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
