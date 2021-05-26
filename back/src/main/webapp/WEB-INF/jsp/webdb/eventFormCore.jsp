<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EventFields" className="org.fundaciobit.pinbaladmin.model.fields.EventFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.SOLICITUDID)}">
        <tr id="event_solicitudID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.SOLICITUDID])?'event.solicitudID':__theForm.labels[EventFields.SOLICITUDID]}" />
              <c:if test="${not empty __theForm.help[EventFields.SOLICITUDID]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.SOLICITUDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="event.solicitudID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.SOLICITUDID)}" >
          <form:hidden path="event.solicitudID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.event.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.SOLICITUDID)}" >
          <form:select id="event_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="input-xxlarge" path="event.solicitudID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.TASCATECNICAID)}">
        <tr id="event_tascaTecnicaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.TASCATECNICAID])?'event.tascaTecnicaID':__theForm.labels[EventFields.TASCATECNICAID]}" />
              <c:if test="${not empty __theForm.help[EventFields.TASCATECNICAID]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.TASCATECNICAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="event.tascaTecnicaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.TASCATECNICAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EventFields.TASCATECNICAID)? 'input-mini uneditable-input' : 'input-mini'}"   path="event.tascaTecnicaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.DATAEVENT)}">
        <tr id="event_dataEvent_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.DATAEVENT])?'event.dataEvent':__theForm.labels[EventFields.DATAEVENT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EventFields.DATAEVENT]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.DATAEVENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="event.dataEvent" cssClass="errorField alert alert-error" />
              <div id="dataEvent" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.DATAEVENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EventFields.DATAEVENT)? 'input-medium uneditable-input' : 'input-medium'}"  path="event.dataEvent" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.DATAEVENT)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataEvent').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.TIPUS)}">
        <tr id="event_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.TIPUS])?'event.tipus':__theForm.labels[EventFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EventFields.TIPUS]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="event.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.TIPUS)}" >
          <form:hidden path="event.tipus"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.event.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.TIPUS)}" >
          <form:select id="event_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="input-xxlarge" path="event.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.PERSONA)}">
        <tr id="event_persona_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.PERSONA])?'event.persona':__theForm.labels[EventFields.PERSONA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EventFields.PERSONA]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.PERSONA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="event.persona" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.PERSONA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EventFields.PERSONA)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="event.persona"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.COMENTARI)}">
        <tr id="event_comentari_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.COMENTARI])?'event.comentari':__theForm.labels[EventFields.COMENTARI]}" />
              <c:if test="${not empty __theForm.help[EventFields.COMENTARI]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.COMENTARI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="event.comentari" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,EventFields.COMENTARI)? 'mceEditorReadOnly':'mceEditor'}" path="event.comentari"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.FITXERID)}">
        <tr id="event_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.FITXERID])?'event.fitxerID':__theForm.labels[EventFields.FITXERID]}" />
              <c:if test="${not empty __theForm.help[EventFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="event.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.event.fitxer}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.FITXERID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.event.fitxer)}"/>">${__theForm.event.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EventFields.NOLLEGIT)}">
        <tr id="event_noLlegit_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EventFields.NOLLEGIT])?'event.noLlegit':__theForm.labels[EventFields.NOLLEGIT]}" />
              <c:if test="${not empty __theForm.help[EventFields.NOLLEGIT]}">
              <i class="icon-info-sign" title="${__theForm.help[EventFields.NOLLEGIT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)}" >
              <form:errors path="event.noLlegit" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)? 'false' : 'true'}" path="event.noLlegit" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EventFields.NOLLEGIT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.event.noLlegit}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
