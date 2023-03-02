<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.ServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.CODI)}">
        <tr id="servei_codi_rowid">
          <td id="servei_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.CODI])?'servei.codi':__theForm.labels[ServeiFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="servei_codi_columnvalueid">
            <form:errors path="servei.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.CODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ServeiFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="servei.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.NOM)}">
        <tr id="servei_nom_rowid">
          <td id="servei_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.NOM])?'servei.nom':__theForm.labels[ServeiFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="servei_nom_columnvalueid">
            <form:errors path="servei.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ServeiFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="1000" path="servei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.DESCRIPCIO)}">
        <tr id="servei_descripcio_rowid">
          <td id="servei_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.DESCRIPCIO])?'servei.descripcio':__theForm.labels[ServeiFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="servei_descripcio_columnvalueid">
              <form:errors path="servei.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="servei.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('servei.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.FORMULARIID)}">
        <tr id="servei_formulariID_rowid">
          <td id="servei_formulariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.FORMULARIID])?'servei.formulariID':__theForm.labels[ServeiFields.FORMULARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.FORMULARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.FORMULARIID]}" ></i>
              </c:if>
            </td>
          <td id="servei_formulariID_columnvalueid">
          <form:errors path="servei.formulariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.FORMULARIID)}" >
          <form:hidden path="servei.formulariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.servei.formulariID,__theForm.listOfFormulariForFormulariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.FORMULARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="servei_formulariID"  onchange="if(typeof onChangeFormulariID == 'function') {  onChangeFormulariID(this); };"  cssClass="form-control col-md-9-optional" path="servei.formulariID">
            <c:forEach items="${__theForm.listOfFormulariForFormulariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.servei.formulariID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.servei.formulariID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.ENTITATSERVEIID)}">
        <tr id="servei_entitatServeiID_rowid">
          <td id="servei_entitatServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.ENTITATSERVEIID])?'servei.entitatServeiID':__theForm.labels[ServeiFields.ENTITATSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.ENTITATSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.ENTITATSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="servei_entitatServeiID_columnvalueid">
          <form:errors path="servei.entitatServeiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.ENTITATSERVEIID)}" >
          <form:hidden path="servei.entitatServeiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.servei.entitatServeiID,__theForm.listOfEntitatServeiForEntitatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.ENTITATSERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="servei_entitatServeiID"  onchange="if(typeof onChangeEntitatServeiID == 'function') {  onChangeEntitatServeiID(this); };"  cssClass="form-control col-md-9-optional" path="servei.entitatServeiID">
            <c:forEach items="${__theForm.listOfEntitatServeiForEntitatServeiID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.ESTATSERVEIID)}">
        <tr id="servei_estatServeiID_rowid">
          <td id="servei_estatServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.ESTATSERVEIID])?'servei.estatServeiID':__theForm.labels[ServeiFields.ESTATSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.ESTATSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.ESTATSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="servei_estatServeiID_columnvalueid">
          <form:errors path="servei.estatServeiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.ESTATSERVEIID)}" >
          <form:hidden path="servei.estatServeiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.servei.estatServeiID,__theForm.listOfEstatServeiForEstatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.ESTATSERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="servei_estatServeiID"  onchange="if(typeof onChangeEstatServeiID == 'function') {  onChangeEstatServeiID(this); };"  cssClass="form-control col-md-9-optional" path="servei.estatServeiID">
            <c:forEach items="${__theForm.listOfEstatServeiForEstatServeiID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.TIPUSCONSENTIMENT)}">
        <tr id="servei_tipusConsentiment_rowid">
          <td id="servei_tipusConsentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.TIPUSCONSENTIMENT])?'servei.tipusConsentiment':__theForm.labels[ServeiFields.TIPUSCONSENTIMENT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.TIPUSCONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.TIPUSCONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="servei_tipusConsentiment_columnvalueid">
          <form:errors path="servei.tipusConsentiment" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.TIPUSCONSENTIMENT)}" >
          <form:hidden path="servei.tipusConsentiment"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.servei.tipusConsentiment,__theForm.listOfValuesForTipusConsentiment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.TIPUSCONSENTIMENT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="servei_tipusConsentiment"  onchange="if(typeof onChangeTipusConsentiment == 'function') {  onChangeTipusConsentiment(this); };"  cssClass="form-control col-md-9-optional" path="servei.tipusConsentiment">
            <c:forEach items="${__theForm.listOfValuesForTipusConsentiment}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ServeiFields.OCULT)}">
        <tr id="servei_ocult_rowid">
          <td id="servei_ocult_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ServeiFields.OCULT])?'servei.ocult':__theForm.labels[ServeiFields.OCULT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ServeiFields.OCULT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ServeiFields.OCULT]}" ></i>
              </c:if>
            </td>
          <td id="servei_ocult_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)}" >
              <form:errors path="servei.ocult" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)? 'false' : 'true'}" path="servei.ocult" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ServeiFields.OCULT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.servei.ocult}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
