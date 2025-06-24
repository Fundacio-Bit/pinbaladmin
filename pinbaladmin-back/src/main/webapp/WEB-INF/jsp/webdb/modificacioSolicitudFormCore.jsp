<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.SOLICITUDID)}">
        <tr id="modificacioSolicitud_solicitudID_rowid">
          <td id="modificacioSolicitud_solicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.SOLICITUDID])?'modificacioSolicitud.solicitudID':__theForm.labels[ModificacioSolicitudFields.SOLICITUDID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.SOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.SOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_solicitudID_columnvalueid">
          <form:errors path="modificacioSolicitud.solicitudID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITUDID)}" >
          <form:hidden path="modificacioSolicitud.solicitudID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSolicitud.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITUDID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSolicitud_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSolicitud.solicitudID">
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTCODI)}">
        <tr id="modificacioSolicitud_procedimentCodi_rowid">
          <td id="modificacioSolicitud_procedimentCodi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.PROCEDIMENTCODI])?'modificacioSolicitud.procedimentCodi':__theForm.labels[ModificacioSolicitudFields.PROCEDIMENTCODI]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.PROCEDIMENTCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.PROCEDIMENTCODI]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_procedimentCodi_columnvalueid">
            <form:errors path="modificacioSolicitud.procedimentCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.PROCEDIMENTCODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.PROCEDIMENTCODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.procedimentCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTNOM)}">
        <tr id="modificacioSolicitud_procedimentNom_rowid">
          <td id="modificacioSolicitud_procedimentNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.PROCEDIMENTNOM])?'modificacioSolicitud.procedimentNom':__theForm.labels[ModificacioSolicitudFields.PROCEDIMENTNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.PROCEDIMENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.PROCEDIMENTNOM]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_procedimentNom_columnvalueid">
            <form:errors path="modificacioSolicitud.procedimentNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.PROCEDIMENTNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.PROCEDIMENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="2000" path="modificacioSolicitud.procedimentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.CODISIANOU)}">
        <tr id="modificacioSolicitud_codiSiaNou_rowid">
          <td id="modificacioSolicitud_codiSiaNou_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.CODISIANOU])?'modificacioSolicitud.codiSiaNou':__theForm.labels[ModificacioSolicitudFields.CODISIANOU]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.CODISIANOU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.CODISIANOU]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_codiSiaNou_columnvalueid">
            <form:errors path="modificacioSolicitud.codiSiaNou" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.CODISIANOU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.CODISIANOU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.codiSiaNou"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.ESTATID)}">
        <tr id="modificacioSolicitud_estatID_rowid">
          <td id="modificacioSolicitud_estatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.ESTATID])?'modificacioSolicitud.estatID':__theForm.labels[ModificacioSolicitudFields.ESTATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.ESTATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.ESTATID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_estatID_columnvalueid">
          <form:errors path="modificacioSolicitud.estatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ESTATID)}" >
          <form:hidden path="modificacioSolicitud.estatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSolicitud.estatID,__theForm.listOfValuesForEstatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ESTATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSolicitud_estatID"  onchange="if(typeof onChangeEstatID == 'function') {  onChangeEstatID(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSolicitud.estatID">
            <c:forEach items="${__theForm.listOfValuesForEstatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.modificacioSolicitud.estatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.modificacioSolicitud.estatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.DATAINICI)}">
        <tr id="modificacioSolicitud_dataInici_rowid">
          <td id="modificacioSolicitud_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.DATAINICI])?'modificacioSolicitud.dataInici':__theForm.labels[ModificacioSolicitudFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_dataInici_columnvalueid">
    <form:errors path="modificacioSolicitud.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="modificacioSolicitud_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#modificacioSolicitud_dataInici" path="modificacioSolicitud.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#modificacioSolicitud_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#modificacioSolicitud_dataInici').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.DATAFI)}">
        <tr id="modificacioSolicitud_dataFi_rowid">
          <td id="modificacioSolicitud_dataFi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.DATAFI])?'modificacioSolicitud.dataFi':__theForm.labels[ModificacioSolicitudFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_dataFi_columnvalueid">
    <form:errors path="modificacioSolicitud.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="modificacioSolicitud_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#modificacioSolicitud_dataFi" path="modificacioSolicitud.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#modificacioSolicitud_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#modificacioSolicitud_dataFi').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.NOTES)}">
        <tr id="modificacioSolicitud_notes_rowid">
          <td id="modificacioSolicitud_notes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.NOTES])?'modificacioSolicitud.notes':__theForm.labels[ModificacioSolicitudFields.NOTES]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.NOTES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.NOTES]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_notes_columnvalueid">
              <form:errors path="modificacioSolicitud.notes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.NOTES)? 'true' : 'false'}" path="modificacioSolicitud.notes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_notes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_notes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSolicitud.notes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('modificacioSolicitud.notes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSolicitud.notes'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_notes').on('click', function(){
					var valor = ($('#dropdownMenuContainer_notes').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_notes').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.ORGANID)}">
        <tr id="modificacioSolicitud_organID_rowid">
          <td id="modificacioSolicitud_organID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.ORGANID])?'modificacioSolicitud.organID':__theForm.labels[ModificacioSolicitudFields.ORGANID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.ORGANID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.ORGANID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_organID_columnvalueid">
          <form:errors path="modificacioSolicitud.organID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ORGANID)}" >
          <form:hidden path="modificacioSolicitud.organID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSolicitud.organID,__theForm.listOfOrganForOrganID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ORGANID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSolicitud_organID"  onchange="if(typeof onChangeOrganID == 'function') {  onChangeOrganID(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSolicitud.organID">
            <c:forEach items="${__theForm.listOfOrganForOrganID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.modificacioSolicitud.organID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.modificacioSolicitud.organID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCNOM)}">
        <tr id="modificacioSolicitud_responsableProcNom_rowid">
          <td id="modificacioSolicitud_responsableProcNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.RESPONSABLEPROCNOM])?'modificacioSolicitud.responsableProcNom':__theForm.labels[ModificacioSolicitudFields.RESPONSABLEPROCNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.RESPONSABLEPROCNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.RESPONSABLEPROCNOM]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_responsableProcNom_columnvalueid">
            <form:errors path="modificacioSolicitud.responsableProcNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.RESPONSABLEPROCNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.RESPONSABLEPROCNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.responsableProcNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)}">
        <tr id="modificacioSolicitud_responsableProceMail_rowid">
          <td id="modificacioSolicitud_responsableProceMail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.RESPONSABLEPROCEMAIL])?'modificacioSolicitud.responsableProceMail':__theForm.labels[ModificacioSolicitudFields.RESPONSABLEPROCEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.RESPONSABLEPROCEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.RESPONSABLEPROCEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_responsableProceMail_columnvalueid">
            <form:errors path="modificacioSolicitud.responsableProceMail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.responsableProceMail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.CONSENTIMENT)}">
        <tr id="modificacioSolicitud_consentiment_rowid">
          <td id="modificacioSolicitud_consentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.CONSENTIMENT])?'modificacioSolicitud.consentiment':__theForm.labels[ModificacioSolicitudFields.CONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.CONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.CONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_consentiment_columnvalueid">
          <form:errors path="modificacioSolicitud.consentiment" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.CONSENTIMENT)}" >
          <form:hidden path="modificacioSolicitud.consentiment"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSolicitud.consentiment,__theForm.listOfValuesForConsentiment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.CONSENTIMENT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSolicitud_consentiment"  onchange="if(typeof onChangeConsentiment == 'function') {  onChangeConsentiment(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSolicitud.consentiment">
            <c:forEach items="${__theForm.listOfValuesForConsentiment}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.modificacioSolicitud.consentiment }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.modificacioSolicitud.consentiment }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.DOCCONSENTIMENTID)}">
        <tr id="modificacioSolicitud_doCconsentimentID_rowid">
          <td id="modificacioSolicitud_doCconsentimentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.DOCCONSENTIMENTID])?'modificacioSolicitud.doCconsentimentID':__theForm.labels[ModificacioSolicitudFields.DOCCONSENTIMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.DOCCONSENTIMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.DOCCONSENTIMENTID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_doCconsentimentID_columnvalueid">
              <form:errors path="modificacioSolicitud.doCconsentimentID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DOCCONSENTIMENTID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSolicitud.doCconsentiment)}"/>">${__theForm.modificacioSolicitud.doCconsentiment.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DOCCONSENTIMENTID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DOCCONSENTIMENTID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.DOCCONSENTIMENTID)? ' uneditable-input' : ''}"   path="doCconsentimentID" type="file" />
                  <label class="custom-file-label" for="doCconsentimentID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.modificacioSolicitud.doCconsentiment}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSolicitud.doCconsentiment)}"/>">${__theForm.modificacioSolicitud.doCconsentiment.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="doCconsentimentIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="doCconsentimentID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#doCconsentimentID').on('change', function(){
						var ruta = $('#doCconsentimentID').val(); 
						var rutaArray = ruta.split('\\');
						$('#doCconsentimentID-custom-file-label').css('display','block');
						$('#doCconsentimentID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNOM)}">
        <tr id="modificacioSolicitud_solicitantNom_rowid">
          <td id="modificacioSolicitud_solicitantNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.SOLICITANTNOM])?'modificacioSolicitud.solicitantNom':__theForm.labels[ModificacioSolicitudFields.SOLICITANTNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.SOLICITANTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.SOLICITANTNOM]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_solicitantNom_columnvalueid">
            <form:errors path="modificacioSolicitud.solicitantNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.solicitantNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNIF)}">
        <tr id="modificacioSolicitud_solicitantNif_rowid">
          <td id="modificacioSolicitud_solicitantNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.SOLICITANTNIF])?'modificacioSolicitud.solicitantNif':__theForm.labels[ModificacioSolicitudFields.SOLICITANTNIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.SOLICITANTNIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.SOLICITANTNIF]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_solicitantNif_columnvalueid">
            <form:errors path="modificacioSolicitud.solicitantNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTNIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTNIF)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.solicitantNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTMAIL)}">
        <tr id="modificacioSolicitud_solicitantMail_rowid">
          <td id="modificacioSolicitud_solicitantMail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.SOLICITANTMAIL])?'modificacioSolicitud.solicitantMail':__theForm.labels[ModificacioSolicitudFields.SOLICITANTMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.SOLICITANTMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.SOLICITANTMAIL]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_solicitantMail_columnvalueid">
            <form:errors path="modificacioSolicitud.solicitantMail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.solicitantMail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTUSERNAME)}">
        <tr id="modificacioSolicitud_solicitantUsername_rowid">
          <td id="modificacioSolicitud_solicitantUsername_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.SOLICITANTUSERNAME])?'modificacioSolicitud.solicitantUsername':__theForm.labels[ModificacioSolicitudFields.SOLICITANTUSERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.SOLICITANTUSERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.SOLICITANTUSERNAME]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_solicitantUsername_columnvalueid">
            <form:errors path="modificacioSolicitud.solicitantUsername" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTUSERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.SOLICITANTUSERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="modificacioSolicitud.solicitantUsername"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSolicitudFields.ESTATMODIFICACIO)}">
        <tr id="modificacioSolicitud_estatModificacio_rowid">
          <td id="modificacioSolicitud_estatModificacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSolicitudFields.ESTATMODIFICACIO])?'modificacioSolicitud.estatModificacio':__theForm.labels[ModificacioSolicitudFields.ESTATMODIFICACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSolicitudFields.ESTATMODIFICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSolicitudFields.ESTATMODIFICACIO]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSolicitud_estatModificacio_columnvalueid">
          <form:errors path="modificacioSolicitud.estatModificacio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ESTATMODIFICACIO)}" >
          <form:hidden path="modificacioSolicitud.estatModificacio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSolicitud.estatModificacio,__theForm.listOfValuesForEstatModificacio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSolicitudFields.ESTATMODIFICACIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSolicitud_estatModificacio"  onchange="if(typeof onChangeEstatModificacio == 'function') {  onChangeEstatModificacio(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSolicitud.estatModificacio">
            <c:forEach items="${__theForm.listOfValuesForEstatModificacio}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.modificacioSolicitud.estatModificacio }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.modificacioSolicitud.estatModificacio }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
