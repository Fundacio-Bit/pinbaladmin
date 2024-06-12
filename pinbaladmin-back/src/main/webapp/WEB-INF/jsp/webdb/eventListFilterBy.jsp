<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EventFields" className="org.fundaciobit.pinbaladmin.model.fields.EventFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="far fa-window-close"></i></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.EVENTID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="event.eventID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="eventIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="eventIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.SOLICITUDID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="event.solicitudID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="solicitudIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="solicitudIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.INCIDENCIATECNICAID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="event.incidenciaTecnicaID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="incidenciaTecnicaIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="incidenciaTecnicaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.DATAEVENT)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="event.dataEvent" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataEventDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataEventDesde" path="dataEventDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataEventDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataEventDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataEventFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataEventFins" path="dataEventFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataEventFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataEventFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.TIPUS)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="event.tipus" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="event_tipus_select" path="tipusSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipus}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#event_tipus_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.PERSONA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.persona" var="persona" />
              <fmt:message key="genapp.form.searchby" var="cercaperpersona" >                
                 <fmt:param value="${persona}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${persona}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpersona}" path="persona" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.DESTINATARI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.destinatari" var="destinatari" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatari" >                
                 <fmt:param value="${destinatari}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatari}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatari}" path="destinatari" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.DESTINATARIMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.destinatarimail" var="destinatarimail" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatarimail" >                
                 <fmt:param value="${destinatarimail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatarimail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatarimail}" path="destinatarimail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.ASUMPTE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.asumpte" var="asumpte" />
              <fmt:message key="genapp.form.searchby" var="cercaperasumpte" >                
                 <fmt:param value="${asumpte}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${asumpte}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperasumpte}" path="asumpte" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.COMENTARI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.comentari" var="comentari" />
              <fmt:message key="genapp.form.searchby" var="cercapercomentari" >                
                 <fmt:param value="${comentari}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${comentari}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercomentari}" path="comentari" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.NOLLEGIT)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="event.noLlegit" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="noLlegitDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="noLlegitFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.CAIDIDENTIFICADORCONSULTA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.caidIdentificadorConsulta" var="caidIdentificadorConsulta" />
              <fmt:message key="genapp.form.searchby" var="cercapercaidIdentificadorConsulta" >                
                 <fmt:param value="${caidIdentificadorConsulta}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${caidIdentificadorConsulta}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercaidIdentificadorConsulta}" path="caidIdentificadorConsulta" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EventFields.CAIDNUMEROSEGUIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="event.caidNumeroSeguiment" var="caidNumeroSeguiment" />
              <fmt:message key="genapp.form.searchby" var="cercapercaidNumeroSeguiment" >                
                 <fmt:param value="${caidNumeroSeguiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${caidNumeroSeguiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercaidNumeroSeguiment}" path="caidNumeroSeguiment" />
            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
