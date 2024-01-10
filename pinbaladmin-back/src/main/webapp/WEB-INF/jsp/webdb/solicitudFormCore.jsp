<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTCODI)}">
        <tr id="solicitud_procedimentCodi_rowid">
          <td id="solicitud_procedimentCodi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTCODI])?'solicitud.procedimentCodi':__theForm.labels[SolicitudFields.PROCEDIMENTCODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PROCEDIMENTCODI]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_procedimentCodi_columnvalueid">
            <form:errors path="solicitud.procedimentCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTCODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTCODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.procedimentCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CODIDESCRIPTIU)}">
        <tr id="solicitud_codiDescriptiu_rowid">
          <td id="solicitud_codiDescriptiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CODIDESCRIPTIU])?'solicitud.codiDescriptiu':__theForm.labels[SolicitudFields.CODIDESCRIPTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CODIDESCRIPTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CODIDESCRIPTIU]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_codiDescriptiu_columnvalueid">
            <form:errors path="solicitud.codiDescriptiu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODIDESCRIPTIU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODIDESCRIPTIU)? ' uneditable-input' : ''}"  style="" maxlength="256" path="solicitud.codiDescriptiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTNOM)}">
        <tr id="solicitud_procedimentNom_rowid">
          <td id="solicitud_procedimentNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTNOM])?'solicitud.procedimentNom':__theForm.labels[SolicitudFields.PROCEDIMENTNOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PROCEDIMENTNOM]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_procedimentNom_columnvalueid">
            <form:errors path="solicitud.procedimentNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="2000" path="solicitud.procedimentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PROCEDIMENTTIPUS)}">
        <tr id="solicitud_procedimentTipus_rowid">
          <td id="solicitud_procedimentTipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PROCEDIMENTTIPUS])?'solicitud.procedimentTipus':__theForm.labels[SolicitudFields.PROCEDIMENTTIPUS]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PROCEDIMENTTIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PROCEDIMENTTIPUS]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_procedimentTipus_columnvalueid">
          <form:errors path="solicitud.procedimentTipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTTIPUS)}" >
          <form:hidden path="solicitud.procedimentTipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.procedimentTipus,__theForm.listOfValuesForProcedimentTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.PROCEDIMENTTIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_procedimentTipus"  onchange="if(typeof onChangeProcedimentTipus == 'function') {  onChangeProcedimentTipus(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.procedimentTipus">
            <c:forEach items="${__theForm.listOfValuesForProcedimentTipus}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.procedimentTipus }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.procedimentTipus }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ESTATID)}">
        <tr id="solicitud_estatID_rowid">
          <td id="solicitud_estatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ESTATID])?'solicitud.estatID':__theForm.labels[SolicitudFields.ESTATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.ESTATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.ESTATID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_estatID_columnvalueid">
          <form:errors path="solicitud.estatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATID)}" >
          <form:hidden path="solicitud.estatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.estatID,__theForm.listOfValuesForEstatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_estatID"  onchange="if(typeof onChangeEstatID == 'function') {  onChangeEstatID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.estatID">
            <c:forEach items="${__theForm.listOfValuesForEstatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TICKETASSOCIAT)}">
        <tr id="solicitud_ticketAssociat_rowid">
          <td id="solicitud_ticketAssociat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TICKETASSOCIAT])?'solicitud.ticketAssociat':__theForm.labels[SolicitudFields.TICKETASSOCIAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TICKETASSOCIAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TICKETASSOCIAT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_ticketAssociat_columnvalueid">
            <form:errors path="solicitud.ticketAssociat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETASSOCIAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETASSOCIAT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.ticketAssociat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TICKETNUMEROSEGUIMENT)}">
        <tr id="solicitud_ticketNumeroSeguiment_rowid">
          <td id="solicitud_ticketNumeroSeguiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TICKETNUMEROSEGUIMENT])?'solicitud.ticketNumeroSeguiment':__theForm.labels[SolicitudFields.TICKETNUMEROSEGUIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TICKETNUMEROSEGUIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TICKETNUMEROSEGUIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_ticketNumeroSeguiment_columnvalueid">
            <form:errors path="solicitud.ticketNumeroSeguiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETNUMEROSEGUIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TICKETNUMEROSEGUIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.ticketNumeroSeguiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DEPARTAMENTID)}">
        <tr id="solicitud_departamentID_rowid">
          <td id="solicitud_departamentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DEPARTAMENTID])?'solicitud.departamentID':__theForm.labels[SolicitudFields.DEPARTAMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DEPARTAMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DEPARTAMENTID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_departamentID_columnvalueid">
          <form:errors path="solicitud.departamentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DEPARTAMENTID)}" >
          <form:hidden path="solicitud.departamentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.departamentID,__theForm.listOfDepartamentForDepartamentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DEPARTAMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_departamentID"  onchange="if(typeof onChangeDepartamentID == 'function') {  onChangeDepartamentID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.departamentID">
            <c:forEach items="${__theForm.listOfDepartamentForDepartamentID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.departamentID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.departamentID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ORGANID)}">
        <tr id="solicitud_organid_rowid">
          <td id="solicitud_organid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ORGANID])?'solicitud.organid':__theForm.labels[SolicitudFields.ORGANID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.ORGANID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.ORGANID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_organid_columnvalueid">
          <form:errors path="solicitud.organid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ORGANID)}" >
          <form:hidden path="solicitud.organid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.organid,__theForm.listOfOrganForOrganid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.ORGANID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_organid"  onchange="if(typeof onChangeOrganid == 'function') {  onChangeOrganid(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.organid">
            <c:forEach items="${__theForm.listOfOrganForOrganid}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.organid }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.organid }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ENTITATESTATAL)}">
        <tr id="solicitud_entitatEstatal_rowid">
          <td id="solicitud_entitatEstatal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ENTITATESTATAL])?'solicitud.entitatEstatal':__theForm.labels[SolicitudFields.ENTITATESTATAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.ENTITATESTATAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.ENTITATESTATAL]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_entitatEstatal_columnvalueid">
            <form:errors path="solicitud.entitatEstatal" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.ENTITATESTATAL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ENTITATESTATAL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.entitatEstatal"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PINFO)}">
        <tr id="solicitud_pinfo_rowid">
          <td id="solicitud_pinfo_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PINFO])?'solicitud.pinfo':__theForm.labels[SolicitudFields.PINFO]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PINFO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PINFO]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_pinfo_columnvalueid">
              <form:errors path="solicitud.pinfo" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PINFO)? 'true' : 'false'}" path="solicitud.pinfo"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_pinfo" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_pinfo" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitud.pinfo'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_pinfo').on('click', function(){
					var valor = ($('#dropdownMenuContainer_pinfo').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_pinfo').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DATAINICI)}">
        <tr id="solicitud_dataInici_rowid">
          <td id="solicitud_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DATAINICI])?'solicitud.dataInici':__theForm.labels[SolicitudFields.DATAINICI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_dataInici_columnvalueid">
    <form:errors path="solicitud.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="solicitud_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#solicitud_dataInici" path="solicitud.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#solicitud_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#solicitud_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DATAFI)}">
        <tr id="solicitud_dataFi_rowid">
          <td id="solicitud_dataFi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DATAFI])?'solicitud.dataFi':__theForm.labels[SolicitudFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_dataFi_columnvalueid">
    <form:errors path="solicitud.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="solicitud_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#solicitud_dataFi" path="solicitud.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#solicitud_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#solicitud_dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PERSONACONTACTE)}">
        <tr id="solicitud_personaContacte_rowid">
          <td id="solicitud_personaContacte_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PERSONACONTACTE])?'solicitud.personaContacte':__theForm.labels[SolicitudFields.PERSONACONTACTE]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PERSONACONTACTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PERSONACONTACTE]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_personaContacte_columnvalueid">
            <form:errors path="solicitud.personaContacte" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.personaContacte"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PERSONACONTACTEEMAIL)}">
        <tr id="solicitud_personaContacteEmail_rowid">
          <td id="solicitud_personaContacteEmail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PERSONACONTACTEEMAIL])?'solicitud.personaContacteEmail':__theForm.labels[SolicitudFields.PERSONACONTACTEEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PERSONACONTACTEEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PERSONACONTACTEEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_personaContacteEmail_columnvalueid">
            <form:errors path="solicitud.personaContacteEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTEEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PERSONACONTACTEEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="solicitud.personaContacteEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.RESPONSABLEPROCNOM)}">
        <tr id="solicitud_responsableProcNom_rowid">
          <td id="solicitud_responsableProcNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.RESPONSABLEPROCNOM])?'solicitud.responsableProcNom':__theForm.labels[SolicitudFields.RESPONSABLEPROCNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.RESPONSABLEPROCNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.RESPONSABLEPROCNOM]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_responsableProcNom_columnvalueid">
            <form:errors path="solicitud.responsableProcNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.responsableProcNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.RESPONSABLEPROCEMAIL)}">
        <tr id="solicitud_responsableProcEmail_rowid">
          <td id="solicitud_responsableProcEmail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.RESPONSABLEPROCEMAIL])?'solicitud.responsableProcEmail':__theForm.labels[SolicitudFields.RESPONSABLEPROCEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.RESPONSABLEPROCEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.RESPONSABLEPROCEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_responsableProcEmail_columnvalueid">
            <form:errors path="solicitud.responsableProcEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.RESPONSABLEPROCEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.responsableProcEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.NOTES)}">
        <tr id="solicitud_notes_rowid">
          <td id="solicitud_notes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.NOTES])?'solicitud.notes':__theForm.labels[SolicitudFields.NOTES]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.NOTES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.NOTES]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_notes_columnvalueid">
              <form:errors path="solicitud.notes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.NOTES)? 'true' : 'false'}" path="solicitud.notes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_notes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_notes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitud.notes'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DOCUMENTSOLICITUDID)}">
        <tr id="solicitud_documentSolicitudID_rowid">
          <td id="solicitud_documentSolicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DOCUMENTSOLICITUDID])?'solicitud.documentSolicitudID':__theForm.labels[SolicitudFields.DOCUMENTSOLICITUDID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DOCUMENTSOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DOCUMENTSOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_documentSolicitudID_columnvalueid">
              <form:errors path="solicitud.documentSolicitudID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.documentSolicitud)}"/>">${__theForm.solicitud.documentSolicitud.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DOCUMENTSOLICITUDID)? ' uneditable-input' : ''}"   path="documentSolicitudID" type="file" />
                  <label class="custom-file-label" for="documentSolicitudID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.solicitud.documentSolicitud}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.documentSolicitud)}"/>">${__theForm.solicitud.documentSolicitud.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="documentSolicitudIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="documentSolicitudID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#documentSolicitudID').on('change', function(){
						var ruta = $('#documentSolicitudID').val(); 
						var rutaArray = ruta.split('\\');
						$('#documentSolicitudID-custom-file-label').css('display','block');
						$('#documentSolicitudID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.SOLICITUDXMLID)}">
        <tr id="solicitud_solicitudXmlID_rowid">
          <td id="solicitud_solicitudXmlID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.SOLICITUDXMLID])?'solicitud.solicitudXmlID':__theForm.labels[SolicitudFields.SOLICITUDXMLID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.SOLICITUDXMLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.SOLICITUDXMLID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_solicitudXmlID_columnvalueid">
              <form:errors path="solicitud.solicitudXmlID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.solicitudXml)}"/>">${__theForm.solicitud.solicitudXml.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDXMLID)? ' uneditable-input' : ''}"   path="solicitudXmlID" type="file" />
                  <label class="custom-file-label" for="solicitudXmlID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.solicitud.solicitudXml}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.solicitudXml)}"/>">${__theForm.solicitud.solicitudXml.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="solicitudXmlIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="solicitudXmlID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#solicitudXmlID').on('change', function(){
						var ruta = $('#solicitudXmlID').val(); 
						var rutaArray = ruta.split('\\');
						$('#solicitudXmlID-custom-file-label').css('display','block');
						$('#solicitudXmlID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.FIRMATDOCSOLICITUD)}">
        <tr id="solicitud_firmatDocSolicitud_rowid">
          <td id="solicitud_firmatDocSolicitud_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.FIRMATDOCSOLICITUD])?'solicitud.firmatDocSolicitud':__theForm.labels[SolicitudFields.FIRMATDOCSOLICITUD]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.FIRMATDOCSOLICITUD]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.FIRMATDOCSOLICITUD]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_firmatDocSolicitud_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.FIRMATDOCSOLICITUD)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeFirmatDocSolicitud == 'function') {  onChangeFirmatDocSolicitud(this); };"  path="solicitud.firmatDocSolicitud">
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
          <td id="solicitud_produccio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PRODUCCIO])?'solicitud.produccio':__theForm.labels[SolicitudFields.PRODUCCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PRODUCCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PRODUCCIO]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_produccio_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.PRODUCCIO)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeProduccio == 'function') {  onChangeProduccio(this); };"  path="solicitud.produccio">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DENOMINACIO)}">
        <tr id="solicitud_denominacio_rowid">
          <td id="solicitud_denominacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DENOMINACIO])?'solicitud.denominacio':__theForm.labels[SolicitudFields.DENOMINACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DENOMINACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DENOMINACIO]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_denominacio_columnvalueid">
            <form:errors path="solicitud.denominacio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DENOMINACIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DENOMINACIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.denominacio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DIR3)}">
        <tr id="solicitud_dir3_rowid">
          <td id="solicitud_dir3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DIR3])?'solicitud.dir3':__theForm.labels[SolicitudFields.DIR3]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DIR3]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_dir3_columnvalueid">
            <form:errors path="solicitud.dir3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DIR3)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.DIR3)? ' uneditable-input' : ''}"  style="" maxlength="50" path="solicitud.dir3"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.NIF)}">
        <tr id="solicitud_nif_rowid">
          <td id="solicitud_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.NIF])?'solicitud.nif':__theForm.labels[SolicitudFields.NIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_nif_columnvalueid">
            <form:errors path="solicitud.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.NIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="40" path="solicitud.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CREADOR)}">
        <tr id="solicitud_creador_rowid">
          <td id="solicitud_creador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CREADOR])?'solicitud.creador':__theForm.labels[SolicitudFields.CREADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CREADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CREADOR]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_creador_columnvalueid">
          <form:errors path="solicitud.creador" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CREADOR)}" >
          <form:hidden path="solicitud.creador"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.creador,__theForm.listOfValuesForCreador)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CREADOR)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_creador"  onchange="if(typeof onChangeCreador == 'function') {  onChangeCreador(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.creador">
            <c:forEach items="${__theForm.listOfValuesForCreador}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.OPERADOR)}">
        <tr id="solicitud_operador_rowid">
          <td id="solicitud_operador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.OPERADOR])?'solicitud.operador':__theForm.labels[SolicitudFields.OPERADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.OPERADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.OPERADOR]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_operador_columnvalueid">
          <form:errors path="solicitud.operador" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.OPERADOR)}" >
          <form:hidden path="solicitud.operador"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.operador,__theForm.listOfValuesForOperador)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.OPERADOR)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_operador"  onchange="if(typeof onChangeOperador == 'function') {  onChangeOperador(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.operador">
            <c:forEach items="${__theForm.listOfValuesForOperador}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ESTATPINBAL)}">
        <tr id="solicitud_estatpinbal_rowid">
          <td id="solicitud_estatpinbal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ESTATPINBAL])?'solicitud.estatpinbal':__theForm.labels[SolicitudFields.ESTATPINBAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.ESTATPINBAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.ESTATPINBAL]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_estatpinbal_columnvalueid">
          <form:errors path="solicitud.estatpinbal" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATPINBAL)}" >
          <form:hidden path="solicitud.estatpinbal"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.estatpinbal,__theForm.listOfValuesForEstatpinbal)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATPINBAL)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_estatpinbal"  onchange="if(typeof onChangeEstatpinbal == 'function') {  onChangeEstatpinbal(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.estatpinbal">
            <c:forEach items="${__theForm.listOfValuesForEstatpinbal}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.estatpinbal }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.estatpinbal }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
