<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EventFields" className="org.fundaciobit.pinbaladmin.model.fields.EventFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.SOLICITUDID)}">
        <tr id="event_solicitudID_rowid">
          <td id="event_solicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.SOLICITUDID])?'event.solicitudID':__theForm.labels[EventFields.SOLICITUDID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.SOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.SOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="event_solicitudID_columnvalueid">
          <form:errors path="event.solicitudID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.SOLICITUDID)}" >
          <form:hidden path="event.solicitudID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.event.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.SOLICITUDID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="event_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="form-control col-md-9-optional" path="event.solicitudID">
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.event.solicitudID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.event.solicitudID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.INCIDENCIATECNICAID)}">
        <tr id="event_incidenciaTecnicaID_rowid">
          <td id="event_incidenciaTecnicaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.INCIDENCIATECNICAID])?'event.incidenciaTecnicaID':__theForm.labels[EventFields.INCIDENCIATECNICAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.INCIDENCIATECNICAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.INCIDENCIATECNICAID]}" ></i>
              </c:if>
            </td>
          <td id="event_incidenciaTecnicaID_columnvalueid">
          <form:errors path="event.incidenciaTecnicaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.INCIDENCIATECNICAID)}" >
          <form:hidden path="event.incidenciaTecnicaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.event.incidenciaTecnicaID,__theForm.listOfIncidenciaTecnicaForIncidenciaTecnicaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.INCIDENCIATECNICAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="event_incidenciaTecnicaID"  onchange="if(typeof onChangeIncidenciaTecnicaID == 'function') {  onChangeIncidenciaTecnicaID(this); };"  cssClass="form-control col-md-9-optional" path="event.incidenciaTecnicaID">
            <c:forEach items="${__theForm.listOfIncidenciaTecnicaForIncidenciaTecnicaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.event.incidenciaTecnicaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.event.incidenciaTecnicaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.DATAEVENT)}">
        <tr id="event_dataEvent_rowid">
          <td id="event_dataEvent_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.DATAEVENT])?'event.dataEvent':__theForm.labels[EventFields.DATAEVENT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EventFields.DATAEVENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.DATAEVENT]}" ></i>
              </c:if>
            </td>
          <td id="event_dataEvent_columnvalueid">
    <form:errors path="event.dataEvent" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="event_dataEvent" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.DATAEVENT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#event_dataEvent" path="event.dataEvent" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.DATAEVENT)}" >
                    <div class="input-group-append"  data-target="#event_dataEvent"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#event_dataEvent').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.TIPUS)}">
        <tr id="event_tipus_rowid">
          <td id="event_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.TIPUS])?'event.tipus':__theForm.labels[EventFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EventFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="event_tipus_columnvalueid">
          <form:errors path="event.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.TIPUS)}" >
          <form:hidden path="event.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.event.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="event_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="event.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.PERSONA)}">
        <tr id="event_persona_rowid">
          <td id="event_persona_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.PERSONA])?'event.persona':__theForm.labels[EventFields.PERSONA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EventFields.PERSONA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.PERSONA]}" ></i>
              </c:if>
            </td>
          <td id="event_persona_columnvalueid">
            <form:errors path="event.persona" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.PERSONA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EventFields.PERSONA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="event.persona"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.COMENTARI)}">
        <tr id="event_comentari_rowid">
          <td id="event_comentari_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.COMENTARI])?'event.comentari':__theForm.labels[EventFields.COMENTARI]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.COMENTARI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.COMENTARI]}" ></i>
              </c:if>
            </td>
          <td id="event_comentari_columnvalueid">
              <form:errors path="event.comentari" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.COMENTARI)? 'true' : 'false'}" path="event.comentari"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_comentari" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_comentari" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('event.comentari'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('event.comentari'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('event.comentari'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_comentari').on('click', function(){
					var valor = ($('#dropdownMenuContainer_comentari').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_comentari').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.FITXERID)}">
        <tr id="event_fitxerID_rowid">
          <td id="event_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.FITXERID])?'event.fitxerID':__theForm.labels[EventFields.FITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="event_fitxerID_columnvalueid">
              <form:errors path="event.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.event.fitxer)}"/>">${__theForm.event.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.event.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.event.fitxer)}"/>">${__theForm.event.fitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.NOLLEGIT)}">
        <tr id="event_noLlegit_rowid">
          <td id="event_noLlegit_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.NOLLEGIT])?'event.noLlegit':__theForm.labels[EventFields.NOLLEGIT]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.NOLLEGIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.NOLLEGIT]}" ></i>
              </c:if>
            </td>
          <td id="event_noLlegit_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)}" >
              <form:errors path="event.noLlegit" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)? 'false' : 'true'}" path="event.noLlegit" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.event.noLlegit}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.CAIDIDENTIFICADORCONSULTA)}">
        <tr id="event_caidIdentificadorConsulta_rowid">
          <td id="event_caidIdentificadorConsulta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.CAIDIDENTIFICADORCONSULTA])?'event.caidIdentificadorConsulta':__theForm.labels[EventFields.CAIDIDENTIFICADORCONSULTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.CAIDIDENTIFICADORCONSULTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.CAIDIDENTIFICADORCONSULTA]}" ></i>
              </c:if>
            </td>
          <td id="event_caidIdentificadorConsulta_columnvalueid">
            <form:errors path="event.caidIdentificadorConsulta" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.CAIDIDENTIFICADORCONSULTA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EventFields.CAIDIDENTIFICADORCONSULTA)? ' uneditable-input' : ''}"  style="" maxlength="100" path="event.caidIdentificadorConsulta"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.CAIDNUMEROSEGUIMENT)}">
        <tr id="event_caidNumeroSeguiment_rowid">
          <td id="event_caidNumeroSeguiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.CAIDNUMEROSEGUIMENT])?'event.caidNumeroSeguiment':__theForm.labels[EventFields.CAIDNUMEROSEGUIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[EventFields.CAIDNUMEROSEGUIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EventFields.CAIDNUMEROSEGUIMENT]}" ></i>
              </c:if>
            </td>
          <td id="event_caidNumeroSeguiment_columnvalueid">
            <form:errors path="event.caidNumeroSeguiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.CAIDNUMEROSEGUIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EventFields.CAIDNUMEROSEGUIMENT)? ' uneditable-input' : ''}"  style="" maxlength="100" path="event.caidNumeroSeguiment"   />

           </td>
        </tr>
        </c:if>
        
