<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.ServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.CODI)}">
        <tr id="servei_codi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.CODI])?'servei.codi':__theForm.labels[ServeiFields.CODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ServeiFields.CODI]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.CODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="servei.codi" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.CODI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ServeiFields.CODI)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="servei.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.NOM)}">
        <tr id="servei_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.NOM])?'servei.nom':__theForm.labels[ServeiFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ServeiFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="servei.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ServeiFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="1000" path="servei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.DESCRIPCIO)}">
        <tr id="servei_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.DESCRIPCIO])?'servei.descripcio':__theForm.labels[ServeiFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[ServeiFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="servei.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="servei.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.FORMULARIID)}">
        <tr id="servei_formulariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.FORMULARIID])?'servei.formulariID':__theForm.labels[ServeiFields.FORMULARIID]}" />
              <c:if test="${not empty __theForm.help[ServeiFields.FORMULARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.FORMULARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="servei.formulariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.FORMULARIID)}" >
          <form:hidden path="servei.formulariID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.servei.formulariID,__theForm.listOfFormulariForFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.FORMULARIID)}" >
          <form:select id="servei_formulariID"  onchange="if(typeof onChangeFormulariID == 'function') {  onChangeFormulariID(this); };"  cssClass="input-xxlarge" path="servei.formulariID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfFormulariForFormulariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.ENTITATSERVEIID)}">
        <tr id="servei_entitatServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.ENTITATSERVEIID])?'servei.entitatServeiID':__theForm.labels[ServeiFields.ENTITATSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ServeiFields.ENTITATSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.ENTITATSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="servei.entitatServeiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.ENTITATSERVEIID)}" >
          <form:hidden path="servei.entitatServeiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.servei.entitatServeiID,__theForm.listOfEntitatServeiForEntitatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.ENTITATSERVEIID)}" >
          <form:select id="servei_entitatServeiID"  onchange="if(typeof onChangeEntitatServeiID == 'function') {  onChangeEntitatServeiID(this); };"  cssClass="input-xxlarge" path="servei.entitatServeiID">
            <c:forEach items="${__theForm.listOfEntitatServeiForEntitatServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.ESTATSERVEIID)}">
        <tr id="servei_estatServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.ESTATSERVEIID])?'servei.estatServeiID':__theForm.labels[ServeiFields.ESTATSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ServeiFields.ESTATSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.ESTATSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="servei.estatServeiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.ESTATSERVEIID)}" >
          <form:hidden path="servei.estatServeiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.servei.estatServeiID,__theForm.listOfEstatServeiForEstatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.ESTATSERVEIID)}" >
          <form:select id="servei_estatServeiID"  onchange="if(typeof onChangeEstatServeiID == 'function') {  onChangeEstatServeiID(this); };"  cssClass="input-xxlarge" path="servei.estatServeiID">
            <c:forEach items="${__theForm.listOfEstatServeiForEstatServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.TIPUSCONSENTIMENT)}">
        <tr id="servei_tipusConsentiment_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.TIPUSCONSENTIMENT])?'servei.tipusConsentiment':__theForm.labels[ServeiFields.TIPUSCONSENTIMENT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ServeiFields.TIPUSCONSENTIMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.TIPUSCONSENTIMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="servei.tipusConsentiment" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.TIPUSCONSENTIMENT)}" >
          <form:hidden path="servei.tipusConsentiment"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.servei.tipusConsentiment,__theForm.listOfValuesForTipusConsentiment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.TIPUSCONSENTIMENT)}" >
          <form:select id="servei_tipusConsentiment"  onchange="if(typeof onChangeTipusConsentiment == 'function') {  onChangeTipusConsentiment(this); };"  cssClass="input-xxlarge" path="servei.tipusConsentiment">
            <c:forEach items="${__theForm.listOfValuesForTipusConsentiment}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.OCULT)}">
        <tr id="servei_ocult_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.OCULT])?'servei.ocult':__theForm.labels[ServeiFields.OCULT]}" />
              <c:if test="${not empty __theForm.help[ServeiFields.OCULT]}">
              <i class="icon-info-sign" title="${__theForm.help[ServeiFields.OCULT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)}" >
              <form:errors path="servei.ocult" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)? 'false' : 'true'}" path="servei.ocult" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.servei.ocult}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
