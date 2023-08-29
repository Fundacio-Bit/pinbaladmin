<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitCDadesCesiFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.TRAMITID)}">
        <tr id="tramitCDadesCesi_tramitid_rowid">
          <td id="tramitCDadesCesi_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.TRAMITID])?'tramitCDadesCesi.tramitid':__theForm.labels[TramitCDadesCesiFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_tramitid_columnvalueid">
          <form:errors path="tramitCDadesCesi.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.TRAMITID)}" >
          <form:hidden path="tramitCDadesCesi.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitCDadesCesi.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitCDadesCesi_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitCDadesCesi.tramitid">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.DENOMINACIO)}">
        <tr id="tramitCDadesCesi_denominacio_rowid">
          <td id="tramitCDadesCesi_denominacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.DENOMINACIO])?'tramitCDadesCesi.denominacio':__theForm.labels[TramitCDadesCesiFields.DENOMINACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.DENOMINACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.DENOMINACIO]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_denominacio_columnvalueid">
          <form:errors path="tramitCDadesCesi.denominacio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DENOMINACIO)}" >
          <form:hidden path="tramitCDadesCesi.denominacio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitCDadesCesi.denominacio,__theForm.listOfValuesForDenominacio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DENOMINACIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitCDadesCesi_denominacio"  onchange="if(typeof onChangeDenominacio == 'function') {  onChangeDenominacio(this); };"  cssClass="form-control col-md-9-optional" path="tramitCDadesCesi.denominacio">
            <c:forEach items="${__theForm.listOfValuesForDenominacio}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.NIF)}">
        <tr id="tramitCDadesCesi_nif_rowid">
          <td id="tramitCDadesCesi_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.NIF])?'tramitCDadesCesi.nif':__theForm.labels[TramitCDadesCesiFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_nif_columnvalueid">
            <form:errors path="tramitCDadesCesi.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitCDadesCesi.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.RESPONSABLE)}">
        <tr id="tramitCDadesCesi_responsable_rowid">
          <td id="tramitCDadesCesi_responsable_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.RESPONSABLE])?'tramitCDadesCesi.responsable':__theForm.labels[TramitCDadesCesiFields.RESPONSABLE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.RESPONSABLE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.RESPONSABLE]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_responsable_columnvalueid">
            <form:errors path="tramitCDadesCesi.responsable" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.RESPONSABLE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.RESPONSABLE)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitCDadesCesi.responsable"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.DIR3RESPONSABLE)}">
        <tr id="tramitCDadesCesi_dir3responsable_rowid">
          <td id="tramitCDadesCesi_dir3responsable_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.DIR3RESPONSABLE])?'tramitCDadesCesi.dir3responsable':__theForm.labels[TramitCDadesCesiFields.DIR3RESPONSABLE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.DIR3RESPONSABLE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.DIR3RESPONSABLE]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_dir3responsable_columnvalueid">
            <form:errors path="tramitCDadesCesi.dir3responsable" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIR3RESPONSABLE)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIR3RESPONSABLE)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitCDadesCesi.dir3responsable"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.DIR3ARREL)}">
        <tr id="tramitCDadesCesi_dir3arrel_rowid">
          <td id="tramitCDadesCesi_dir3arrel_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.DIR3ARREL])?'tramitCDadesCesi.dir3arrel':__theForm.labels[TramitCDadesCesiFields.DIR3ARREL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.DIR3ARREL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.DIR3ARREL]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_dir3arrel_columnvalueid">
            <form:errors path="tramitCDadesCesi.dir3arrel" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIR3ARREL)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIR3ARREL)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitCDadesCesi.dir3arrel"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.DIRECCIO)}">
        <tr id="tramitCDadesCesi_direccio_rowid">
          <td id="tramitCDadesCesi_direccio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.DIRECCIO])?'tramitCDadesCesi.direccio':__theForm.labels[TramitCDadesCesiFields.DIRECCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.DIRECCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.DIRECCIO]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_direccio_columnvalueid">
            <form:errors path="tramitCDadesCesi.direccio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIRECCIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.DIRECCIO)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitCDadesCesi.direccio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.CODIPOSTAL)}">
        <tr id="tramitCDadesCesi_codipostal_rowid">
          <td id="tramitCDadesCesi_codipostal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.CODIPOSTAL])?'tramitCDadesCesi.codipostal':__theForm.labels[TramitCDadesCesiFields.CODIPOSTAL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.CODIPOSTAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.CODIPOSTAL]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_codipostal_columnvalueid">
            <form:errors path="tramitCDadesCesi.codipostal" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.CODIPOSTAL)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.CODIPOSTAL)? ' uneditable-input' : ''}"  style="" maxlength="10" path="tramitCDadesCesi.codipostal"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitCDadesCesiFields.MUNICIPI)}">
        <tr id="tramitCDadesCesi_municipi_rowid">
          <td id="tramitCDadesCesi_municipi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitCDadesCesiFields.MUNICIPI])?'tramitCDadesCesi.municipi':__theForm.labels[TramitCDadesCesiFields.MUNICIPI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitCDadesCesiFields.MUNICIPI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitCDadesCesiFields.MUNICIPI]}" ></i>
              </c:if>
            </td>
          <td id="tramitCDadesCesi_municipi_columnvalueid">
          <form:errors path="tramitCDadesCesi.municipi" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.MUNICIPI)}" >
          <form:hidden path="tramitCDadesCesi.municipi"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitCDadesCesi.municipi,__theForm.listOfValuesForMunicipi)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitCDadesCesiFields.MUNICIPI)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitCDadesCesi_municipi"  onchange="if(typeof onChangeMunicipi == 'function') {  onChangeMunicipi(this); };"  cssClass="form-control col-md-9-optional" path="tramitCDadesCesi.municipi">
            <c:forEach items="${__theForm.listOfValuesForMunicipi}" var="tmp">
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
        
