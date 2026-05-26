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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CODISIACONV)}">
        <tr id="solicitud_codiSiaConv_rowid">
          <td id="solicitud_codiSiaConv_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CODISIACONV])?'solicitud.codiSiaConv':__theForm.labels[SolicitudFields.CODISIACONV]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CODISIACONV]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CODISIACONV]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_codiSiaConv_columnvalueid">
            <form:errors path="solicitud.codiSiaConv" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODISIACONV)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CODISIACONV)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.codiSiaConv"   />

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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.ESTATSOLICITUD)}">
        <tr id="solicitud_estatSolicitud_rowid">
          <td id="solicitud_estatSolicitud_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.ESTATSOLICITUD])?'solicitud.estatSolicitud':__theForm.labels[SolicitudFields.ESTATSOLICITUD]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.ESTATSOLICITUD]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.ESTATSOLICITUD]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_estatSolicitud_columnvalueid">
          <form:errors path="solicitud.estatSolicitud" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATSOLICITUD)}" >
          <form:hidden path="solicitud.estatSolicitud"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.estatSolicitud,__theForm.listOfValuesForEstatSolicitud)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.ESTATSOLICITUD)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_estatSolicitud"  onchange="if(typeof onChangeEstatSolicitud == 'function') {  onChangeEstatSolicitud(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.estatSolicitud">
            <c:forEach items="${__theForm.listOfValuesForEstatSolicitud}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.EXPEDIENTPID)}">
        <tr id="solicitud_expedientPid_rowid">
          <td id="solicitud_expedientPid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.EXPEDIENTPID])?'solicitud.expedientPid':__theForm.labels[SolicitudFields.EXPEDIENTPID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.EXPEDIENTPID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.EXPEDIENTPID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_expedientPid_columnvalueid">
            <form:errors path="solicitud.expedientPid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.EXPEDIENTPID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.EXPEDIENTPID)? ' uneditable-input' : ''}"  style="" maxlength="2147483647" path="solicitud.expedientPid"   />

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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONSENTIMENT)}">
        <tr id="solicitud_consentiment_rowid">
          <td id="solicitud_consentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONSENTIMENT])?'solicitud.consentiment':__theForm.labels[SolicitudFields.CONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_consentiment_columnvalueid">
          <form:errors path="solicitud.consentiment" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONSENTIMENT)}" >
          <form:hidden path="solicitud.consentiment"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.consentiment,__theForm.listOfValuesForConsentiment)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONSENTIMENT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_consentiment"  onchange="if(typeof onChangeConsentiment == 'function') {  onChangeConsentiment(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.consentiment">
            <c:forEach items="${__theForm.listOfValuesForConsentiment}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.consentiment }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.consentiment }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.URLCONSENTIMENT)}">
        <tr id="solicitud_urlconsentiment_rowid">
          <td id="solicitud_urlconsentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.URLCONSENTIMENT])?'solicitud.urlconsentiment':__theForm.labels[SolicitudFields.URLCONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.URLCONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.URLCONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_urlconsentiment_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.URLCONSENTIMENT)}">

             <c:if test="${ not empty __theForm.solicitud.urlconsentiment}">
               <a href="${__theForm.solicitud.urlconsentiment}" target="_blank">${__theForm.solicitud.urlconsentiment}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,SolicitudFields.URLCONSENTIMENT))}">

            <form:errors path="solicitud.urlconsentiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.URLCONSENTIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.URLCONSENTIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.urlconsentiment"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONSENTIMENTADJUNT)}">
        <tr id="solicitud_consentimentadjunt_rowid">
          <td id="solicitud_consentimentadjunt_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONSENTIMENTADJUNT])?'solicitud.consentimentadjunt':__theForm.labels[SolicitudFields.CONSENTIMENTADJUNT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONSENTIMENTADJUNT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONSENTIMENTADJUNT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_consentimentadjunt_columnvalueid">
          <form:errors path="solicitud.consentimentadjunt" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONSENTIMENTADJUNT)}" >
          <form:hidden path="solicitud.consentimentadjunt"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.consentimentadjunt,__theForm.listOfValuesForConsentimentadjunt)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONSENTIMENTADJUNT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_consentimentadjunt"  onchange="if(typeof onChangeConsentimentadjunt == 'function') {  onChangeConsentimentadjunt(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.consentimentadjunt">
            <c:forEach items="${__theForm.listOfValuesForConsentimentadjunt}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.consentimentadjunt }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.consentimentadjunt }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.PORTAFIBID)}">
        <tr id="solicitud_portafibID_rowid">
          <td id="solicitud_portafibID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.PORTAFIBID])?'solicitud.portafibID':__theForm.labels[SolicitudFields.PORTAFIBID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.PORTAFIBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.PORTAFIBID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_portafibID_columnvalueid">
            <form:errors path="solicitud.portafibID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.PORTAFIBID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.PORTAFIBID)? ' uneditable-input' : ''}"  style=""  path="solicitud.portafibID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.INFOMADRIDID)}">
        <tr id="solicitud_infomadridid_rowid">
          <td id="solicitud_infomadridid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.INFOMADRIDID])?'solicitud.infomadridid':__theForm.labels[SolicitudFields.INFOMADRIDID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.INFOMADRIDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.INFOMADRIDID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_infomadridid_columnvalueid">
          <form:errors path="solicitud.infomadridid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.INFOMADRIDID)}" >
          <form:hidden path="solicitud.infomadridid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.infomadridid,__theForm.listOfInfoMadridForInfomadridid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.INFOMADRIDID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_infomadridid"  onchange="if(typeof onChangeInfomadridid == 'function') {  onChangeInfomadridid(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.infomadridid">
            <c:forEach items="${__theForm.listOfInfoMadridForInfomadridid}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.infomadridid }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.infomadridid }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.DATACADUCITAT)}">
        <tr id="solicitud_dataCaducitat_rowid">
          <td id="solicitud_dataCaducitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.DATACADUCITAT])?'solicitud.dataCaducitat':__theForm.labels[SolicitudFields.DATACADUCITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.DATACADUCITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.DATACADUCITAT]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_dataCaducitat_columnvalueid">
    <form:errors path="solicitud.dataCaducitat" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="solicitud_dataCaducitat" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATACADUCITAT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#solicitud_dataCaducitat" path="solicitud.dataCaducitat" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.DATACADUCITAT)}" >
                    <div class="input-group-append"  data-target="#solicitud_dataCaducitat"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#solicitud_dataCaducitat').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.FITXERCONSENTIMENTID)}">
        <tr id="solicitud_fitxerConsentimentID_rowid">
          <td id="solicitud_fitxerConsentimentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.FITXERCONSENTIMENTID])?'solicitud.fitxerConsentimentID':__theForm.labels[SolicitudFields.FITXERCONSENTIMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.FITXERCONSENTIMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.FITXERCONSENTIMENTID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_fitxerConsentimentID_columnvalueid">
              <form:errors path="solicitud.fitxerConsentimentID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.FITXERCONSENTIMENTID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.fitxerConsentiment)}"/>">${__theForm.solicitud.fitxerConsentiment.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.FITXERCONSENTIMENTID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.FITXERCONSENTIMENTID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.FITXERCONSENTIMENTID)? ' uneditable-input' : ''}"   path="fitxerConsentimentID" type="file" />
                  <label class="custom-file-label" for="fitxerConsentimentID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.solicitud.fitxerConsentiment}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.solicitud.fitxerConsentiment)}"/>">${__theForm.solicitud.fitxerConsentiment.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerConsentimentIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerConsentimentID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerConsentimentID').on('change', function(){
						var ruta = $('#fitxerConsentimentID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerConsentimentID-custom-file-label').css('display','block');
						$('#fitxerConsentimentID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTETITULARID)}">
        <tr id="solicitud_contacteTitularID_rowid">
          <td id="solicitud_contacteTitularID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTETITULARID])?'solicitud.contacteTitularID':__theForm.labels[SolicitudFields.CONTACTETITULARID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTETITULARID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTETITULARID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteTitularID_columnvalueid">
          <form:errors path="solicitud.contacteTitularID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTETITULARID)}" >
          <form:hidden path="solicitud.contacteTitularID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteTitularID,__theForm.listOfContacteForContacteTitularID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTETITULARID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteTitularID"  onchange="if(typeof onChangeContacteTitularID == 'function') {  onChangeContacteTitularID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteTitularID">
            <c:forEach items="${__theForm.listOfContacteForContacteTitularID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteTitularID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteTitularID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TITULARFIRMANIF)}">
        <tr id="solicitud_titularFirmaNif_rowid">
          <td id="solicitud_titularFirmaNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TITULARFIRMANIF])?'solicitud.titularFirmaNif':__theForm.labels[SolicitudFields.TITULARFIRMANIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TITULARFIRMANIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TITULARFIRMANIF]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_titularFirmaNif_columnvalueid">
            <form:errors path="solicitud.titularFirmaNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMANIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMANIF)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.titularFirmaNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TITULARFIRMANOM)}">
        <tr id="solicitud_titularFirmaNom_rowid">
          <td id="solicitud_titularFirmaNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TITULARFIRMANOM])?'solicitud.titularFirmaNom':__theForm.labels[SolicitudFields.TITULARFIRMANOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TITULARFIRMANOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TITULARFIRMANOM]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_titularFirmaNom_columnvalueid">
            <form:errors path="solicitud.titularFirmaNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMANOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMANOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.titularFirmaNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TITULARFIRMALLINATGES)}">
        <tr id="solicitud_titularFirmaLlinatges_rowid">
          <td id="solicitud_titularFirmaLlinatges_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TITULARFIRMALLINATGES])?'solicitud.titularFirmaLlinatges':__theForm.labels[SolicitudFields.TITULARFIRMALLINATGES]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TITULARFIRMALLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TITULARFIRMALLINATGES]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_titularFirmaLlinatges_columnvalueid">
            <form:errors path="solicitud.titularFirmaLlinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMALLINATGES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMALLINATGES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.titularFirmaLlinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.TITULARFIRMAEMAIL)}">
        <tr id="solicitud_titularFirmaEmail_rowid">
          <td id="solicitud_titularFirmaEmail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.TITULARFIRMAEMAIL])?'solicitud.titularFirmaEmail':__theForm.labels[SolicitudFields.TITULARFIRMAEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.TITULARFIRMAEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.TITULARFIRMAEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_titularFirmaEmail_columnvalueid">
            <form:errors path="solicitud.titularFirmaEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMAEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.TITULARFIRMAEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitud.titularFirmaEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.SOLICITUDFUSIONADAID)}">
        <tr id="solicitud_solicitudFusionadaID_rowid">
          <td id="solicitud_solicitudFusionadaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.SOLICITUDFUSIONADAID])?'solicitud.solicitudFusionadaID':__theForm.labels[SolicitudFields.SOLICITUDFUSIONADAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.SOLICITUDFUSIONADAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.SOLICITUDFUSIONADAID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_solicitudFusionadaID_columnvalueid">
            <form:errors path="solicitud.solicitudFusionadaID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDFUSIONADAID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudFields.SOLICITUDFUSIONADAID)? ' uneditable-input' : ''}"  style=""  path="solicitud.solicitudFusionadaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTEPERSONAID)}">
        <tr id="solicitud_contactePersonaID_rowid">
          <td id="solicitud_contactePersonaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTEPERSONAID])?'solicitud.contactePersonaID':__theForm.labels[SolicitudFields.CONTACTEPERSONAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTEPERSONAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTEPERSONAID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contactePersonaID_columnvalueid">
          <form:errors path="solicitud.contactePersonaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEPERSONAID)}" >
          <form:hidden path="solicitud.contactePersonaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contactePersonaID,__theForm.listOfContacteForContactePersonaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEPERSONAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contactePersonaID"  onchange="if(typeof onChangeContactePersonaID == 'function') {  onChangeContactePersonaID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contactePersonaID">
            <c:forEach items="${__theForm.listOfContacteForContactePersonaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contactePersonaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contactePersonaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTERESPONSABLEID)}">
        <tr id="solicitud_contacteResponsableID_rowid">
          <td id="solicitud_contacteResponsableID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTERESPONSABLEID])?'solicitud.contacteResponsableID':__theForm.labels[SolicitudFields.CONTACTERESPONSABLEID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTERESPONSABLEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTERESPONSABLEID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteResponsableID_columnvalueid">
          <form:errors path="solicitud.contacteResponsableID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTERESPONSABLEID)}" >
          <form:hidden path="solicitud.contacteResponsableID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteResponsableID,__theForm.listOfContacteForContacteResponsableID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTERESPONSABLEID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteResponsableID"  onchange="if(typeof onChangeContacteResponsableID == 'function') {  onChangeContacteResponsableID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteResponsableID">
            <c:forEach items="${__theForm.listOfContacteForContacteResponsableID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteResponsableID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteResponsableID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTESOLICITANTID)}">
        <tr id="solicitud_contacteSolicitantID_rowid">
          <td id="solicitud_contacteSolicitantID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTESOLICITANTID])?'solicitud.contacteSolicitantID':__theForm.labels[SolicitudFields.CONTACTESOLICITANTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTESOLICITANTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTESOLICITANTID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteSolicitantID_columnvalueid">
          <form:errors path="solicitud.contacteSolicitantID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTESOLICITANTID)}" >
          <form:hidden path="solicitud.contacteSolicitantID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteSolicitantID,__theForm.listOfContacteForContacteSolicitantID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTESOLICITANTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteSolicitantID"  onchange="if(typeof onChangeContacteSolicitantID == 'function') {  onChangeContacteSolicitantID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteSolicitantID">
            <c:forEach items="${__theForm.listOfContacteForContacteSolicitantID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteSolicitantID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteSolicitantID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTEGESTAUTID)}">
        <tr id="solicitud_contacteGestAutID_rowid">
          <td id="solicitud_contacteGestAutID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTEGESTAUTID])?'solicitud.contacteGestAutID':__theForm.labels[SolicitudFields.CONTACTEGESTAUTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTEGESTAUTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTEGESTAUTID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteGestAutID_columnvalueid">
          <form:errors path="solicitud.contacteGestAutID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEGESTAUTID)}" >
          <form:hidden path="solicitud.contacteGestAutID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteGestAutID,__theForm.listOfContacteForContacteGestAutID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEGESTAUTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteGestAutID"  onchange="if(typeof onChangeContacteGestAutID == 'function') {  onChangeContacteGestAutID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteGestAutID">
            <c:forEach items="${__theForm.listOfContacteForContacteGestAutID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteGestAutID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteGestAutID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTEAUDITORIAID)}">
        <tr id="solicitud_contacteAuditoriaID_rowid">
          <td id="solicitud_contacteAuditoriaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTEAUDITORIAID])?'solicitud.contacteAuditoriaID':__theForm.labels[SolicitudFields.CONTACTEAUDITORIAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTEAUDITORIAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTEAUDITORIAID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteAuditoriaID_columnvalueid">
          <form:errors path="solicitud.contacteAuditoriaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEAUDITORIAID)}" >
          <form:hidden path="solicitud.contacteAuditoriaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteAuditoriaID,__theForm.listOfContacteForContacteAuditoriaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTEAUDITORIAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteAuditoriaID"  onchange="if(typeof onChangeContacteAuditoriaID == 'function') {  onChangeContacteAuditoriaID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteAuditoriaID">
            <c:forEach items="${__theForm.listOfContacteForContacteAuditoriaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteAuditoriaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteAuditoriaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudFields.CONTACTETECNICID)}">
        <tr id="solicitud_contacteTecnicID_rowid">
          <td id="solicitud_contacteTecnicID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudFields.CONTACTETECNICID])?'solicitud.contacteTecnicID':__theForm.labels[SolicitudFields.CONTACTETECNICID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudFields.CONTACTETECNICID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudFields.CONTACTETECNICID]}" ></i>
              </c:if>
            </td>
          <td id="solicitud_contacteTecnicID_columnvalueid">
          <form:errors path="solicitud.contacteTecnicID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTETECNICID)}" >
          <form:hidden path="solicitud.contacteTecnicID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitud.contacteTecnicID,__theForm.listOfContacteForContacteTecnicID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudFields.CONTACTETECNICID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitud_contacteTecnicID"  onchange="if(typeof onChangeContacteTecnicID == 'function') {  onChangeContacteTecnicID(this); };"  cssClass="form-control col-md-9-optional" path="solicitud.contacteTecnicID">
            <c:forEach items="${__theForm.listOfContacteForContacteTecnicID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.solicitud.contacteTecnicID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.solicitud.contacteTecnicID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
