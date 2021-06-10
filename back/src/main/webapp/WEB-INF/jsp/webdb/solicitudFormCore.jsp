<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTCODI)}">
        <tr id="solicitud_procedimentCodi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTCODI])?'solicitud.procedimentCodi':__theForm.labels[SolicitudFields.PROCEDIMENTCODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTCODI]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PROCEDIMENTCODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.procedimentCodi" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTCODI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTCODI)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.procedimentCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CODIDESCRIPTIU)}">
        <tr id="solicitud_codiDescriptiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CODIDESCRIPTIU])?'solicitud.codiDescriptiu':__theForm.labels[SolicitudFields.CODIDESCRIPTIU]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.CODIDESCRIPTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.CODIDESCRIPTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.codiDescriptiu" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODIDESCRIPTIU)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODIDESCRIPTIU)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="solicitud.codiDescriptiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTNOM)}">
        <tr id="solicitud_procedimentNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTNOM])?'solicitud.procedimentNom':__theForm.labels[SolicitudFields.PROCEDIMENTNOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTNOM]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PROCEDIMENTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.procedimentNom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTNOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTNOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="2000" path="solicitud.procedimentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTTIPUS)}">
        <tr id="solicitud_procedimentTipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTTIPUS])?'solicitud.procedimentTipus':__theForm.labels[SolicitudFields.PROCEDIMENTTIPUS]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTTIPUS]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PROCEDIMENTTIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitud.procedimentTipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTTIPUS)}" >
          <form:hidden path="solicitud.procedimentTipus"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitud.procedimentTipus,__theForm.listOfValuesForProcedimentTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTTIPUS)}" >
          <form:select id="solicitud_procedimentTipus"  onchange="if(typeof onChangeProcedimentTipus == 'function') {  onChangeProcedimentTipus(this); };"  cssClass="input-xxlarge" path="solicitud.procedimentTipus">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForProcedimentTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ESTATID)}">
        <tr id="solicitud_estatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ESTATID])?'solicitud.estatID':__theForm.labels[SolicitudFields.ESTATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudFields.ESTATID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.ESTATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitud.estatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATID)}" >
          <form:hidden path="solicitud.estatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitud.estatID,__theForm.listOfEstatSolicitudForEstatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATID)}" >
          <form:select id="solicitud_estatID"  onchange="if(typeof onChangeEstatID == 'function') {  onChangeEstatID(this); };"  cssClass="input-xxlarge" path="solicitud.estatID">
            <c:forEach items="${__theForm.listOfEstatSolicitudForEstatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TICKETASSOCIAT)}">
        <tr id="solicitud_ticketAssociat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TICKETASSOCIAT])?'solicitud.ticketAssociat':__theForm.labels[SolicitudFields.TICKETASSOCIAT]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.TICKETASSOCIAT]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.TICKETASSOCIAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.ticketAssociat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETASSOCIAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETASSOCIAT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.ticketAssociat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TICKETNUMEROSEGUIMENT)}">
        <tr id="solicitud_ticketNumeroSeguiment_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TICKETNUMEROSEGUIMENT])?'solicitud.ticketNumeroSeguiment':__theForm.labels[SolicitudFields.TICKETNUMEROSEGUIMENT]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.TICKETNUMEROSEGUIMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.TICKETNUMEROSEGUIMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.ticketNumeroSeguiment" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETNUMEROSEGUIMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETNUMEROSEGUIMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.ticketNumeroSeguiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DEPARTAMENTID)}">
        <tr id="solicitud_departamentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DEPARTAMENTID])?'solicitud.departamentID':__theForm.labels[SolicitudFields.DEPARTAMENTID]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.DEPARTAMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.DEPARTAMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitud.departamentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DEPARTAMENTID)}" >
          <form:hidden path="solicitud.departamentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitud.departamentID,__theForm.listOfDepartamentForDepartamentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DEPARTAMENTID)}" >
          <form:select id="solicitud_departamentID"  onchange="if(typeof onChangeDepartamentID == 'function') {  onChangeDepartamentID(this); };"  cssClass="input-xxlarge" path="solicitud.departamentID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfDepartamentForDepartamentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ENTITATESTATAL)}">
        <tr id="solicitud_entitatEstatal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ENTITATESTATAL])?'solicitud.entitatEstatal':__theForm.labels[SolicitudFields.ENTITATESTATAL]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.ENTITATESTATAL]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.ENTITATESTATAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.entitatEstatal" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.ENTITATESTATAL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ENTITATESTATAL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.entitatEstatal"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PINFO)}">
        <tr id="solicitud_pinfo_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PINFO])?'solicitud.pinfo':__theForm.labels[SolicitudFields.PINFO]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.PINFO]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PINFO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.pinfo" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PINFO)? 'true' : 'false'}" path="solicitud.pinfo"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DATAINICI)}">
        <tr id="solicitud_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DATAINICI])?'solicitud.dataInici':__theForm.labels[SolicitudFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudFields.DATAINICI]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.dataInici" cssClass="errorField alert alert-error" />
              <div id="dataInici" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAINICI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAINICI)? 'input-medium uneditable-input' : 'input-medium'}"  path="solicitud.dataInici" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAINICI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataInici').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DATAFI)}">
        <tr id="solicitud_dataFi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DATAFI])?'solicitud.dataFi':__theForm.labels[SolicitudFields.DATAFI]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.DATAFI]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.DATAFI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.dataFi" cssClass="errorField alert alert-error" />
              <div id="dataFi" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAFI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAFI)? 'input-medium uneditable-input' : 'input-medium'}"  path="solicitud.dataFi" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAFI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFi').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PERSONACONTACTE)}">
        <tr id="solicitud_personaContacte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PERSONACONTACTE])?'solicitud.personaContacte':__theForm.labels[SolicitudFields.PERSONACONTACTE]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.PERSONACONTACTE]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PERSONACONTACTE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.personaContacte" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTE)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.personaContacte"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PERSONACONTACTEEMAIL)}">
        <tr id="solicitud_personaContacteEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PERSONACONTACTEEMAIL])?'solicitud.personaContacteEmail':__theForm.labels[SolicitudFields.PERSONACONTACTEEMAIL]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.PERSONACONTACTEEMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PERSONACONTACTEEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.personaContacteEmail" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTEEMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTEEMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="solicitud.personaContacteEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.RESPONSABLEPROCNOM)}">
        <tr id="solicitud_responsableProcNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.RESPONSABLEPROCNOM])?'solicitud.responsableProcNom':__theForm.labels[SolicitudFields.RESPONSABLEPROCNOM]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.RESPONSABLEPROCNOM]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.RESPONSABLEPROCNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.responsableProcNom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCNOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCNOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.responsableProcNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.RESPONSABLEPROCEMAIL)}">
        <tr id="solicitud_responsableProcEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.RESPONSABLEPROCEMAIL])?'solicitud.responsableProcEmail':__theForm.labels[SolicitudFields.RESPONSABLEPROCEMAIL]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.RESPONSABLEPROCEMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.RESPONSABLEPROCEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.responsableProcEmail" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCEMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCEMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitud.responsableProcEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.NOTES)}">
        <tr id="solicitud_notes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.NOTES])?'solicitud.notes':__theForm.labels[SolicitudFields.NOTES]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.NOTES]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.NOTES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.notes" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.NOTES)? 'true' : 'false'}" path="solicitud.notes"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DOCUMENTSOLICITUDID)}">
        <tr id="solicitud_documentSolicitudID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DOCUMENTSOLICITUDID])?'solicitud.documentSolicitudID':__theForm.labels[SolicitudFields.DOCUMENTSOLICITUDID]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.DOCUMENTSOLICITUDID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.DOCUMENTSOLICITUDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.documentSolicitudID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)? 'input uneditable-input' : 'input'}"  path="documentSolicitudID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.solicitud.documentSolicitud}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)}" >
                    <span class="add-on">
                        <form:checkbox path="documentSolicitudIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.documentSolicitud)}"/>">${__theForm.solicitud.documentSolicitud.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.SOLICITUDXMLID)}">
        <tr id="solicitud_solicitudXmlID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.SOLICITUDXMLID])?'solicitud.solicitudXmlID':__theForm.labels[SolicitudFields.SOLICITUDXMLID]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.SOLICITUDXMLID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.SOLICITUDXMLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitud.solicitudXmlID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)? 'input uneditable-input' : 'input'}"  path="solicitudXmlID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.solicitud.solicitudXml}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)}" >
                    <span class="add-on">
                        <form:checkbox path="solicitudXmlIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.solicitudXml)}"/>">${__theForm.solicitud.solicitudXml.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.FIRMATDOCSOLICITUD)}">
        <tr id="solicitud_firmatDocSolicitud_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.FIRMATDOCSOLICITUD])?'solicitud.firmatDocSolicitud':__theForm.labels[SolicitudFields.FIRMATDOCSOLICITUD]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.FIRMATDOCSOLICITUD]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.FIRMATDOCSOLICITUD]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.FIRMATDOCSOLICITUD)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeFirmatDocSolicitud == 'function') {  onChangeFirmatDocSolicitud(this); };"  path="solicitud.firmatDocSolicitud">
                <form:option value="true" ><fmt:message key="solicitud.firmatdocsolicitud.true" /></form:option>
                <form:option value="false" ><fmt:message key="solicitud.firmatdocsolicitud.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.FIRMATDOCSOLICITUD)}" >
                <fmt:message key="solicitud.firmatdocsolicitud.${__theForm.solicitud.firmatDocSolicitud}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PRODUCCIO)}">
        <tr id="solicitud_produccio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PRODUCCIO])?'solicitud.produccio':__theForm.labels[SolicitudFields.PRODUCCIO]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.PRODUCCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.PRODUCCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.PRODUCCIO)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeProduccio == 'function') {  onChangeProduccio(this); };"  path="solicitud.produccio">
                <form:option value="true" ><fmt:message key="solicitud.produccio.true" /></form:option>
                <form:option value="false" ><fmt:message key="solicitud.produccio.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PRODUCCIO)}" >
                <fmt:message key="solicitud.produccio.${__theForm.solicitud.produccio}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CREADOR)}">
        <tr id="solicitud_creador_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CREADOR])?'solicitud.creador':__theForm.labels[SolicitudFields.CREADOR]}" />
              <c:if test="${not empty __theForm.help[SolicitudFields.CREADOR]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudFields.CREADOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitud.creador" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.CREADOR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CREADOR)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="solicitud.creador"   />

           </td>
        </tr>
        </c:if>
        
