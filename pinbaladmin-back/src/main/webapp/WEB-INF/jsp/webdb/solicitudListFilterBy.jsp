<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.SOLICITUDID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.solicitudID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="solicitudIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="solicitudIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PROCEDIMENTCODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.procedimentCodi" var="procedimentCodi" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentCodi" >                
                 <fmt:param value="${procedimentCodi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentCodi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentCodi}" path="procedimentCodi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.CODIDESCRIPTIU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.codiDescriptiu" var="codiDescriptiu" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiDescriptiu" >                
                 <fmt:param value="${codiDescriptiu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiDescriptiu}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiDescriptiu}" path="codiDescriptiu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PROCEDIMENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.procedimentNom" var="procedimentNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentNom" >                
                 <fmt:param value="${procedimentNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentNom}" path="procedimentNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PROCEDIMENTTIPUS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.procedimentTipus" var="procedimentTipus" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentTipus" >                
                 <fmt:param value="${procedimentTipus}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentTipus}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentTipus}" path="procedimentTipus" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.EXPEDIENTPID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.expedientPid" var="expedientPid" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpedientPid" >                
                 <fmt:param value="${expedientPid}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expedientPid}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpedientPid}" path="expedientPid" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.ESTATID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="solicitud.estatID" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="solicitud_estatID_select" path="estatIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstatID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#solicitud_estatID_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.DEPARTAMENTID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.departamentID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="departamentIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="departamentIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.ORGANID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.organid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="organidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="organidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.ENTITATESTATAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.entitatEstatal" var="entitatEstatal" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatEstatal" >                
                 <fmt:param value="${entitatEstatal}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatEstatal}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatEstatal}" path="entitatEstatal" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PINFO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.pinfo" var="pinfo" />
              <fmt:message key="genapp.form.searchby" var="cercaperpinfo" >                
                 <fmt:param value="${pinfo}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${pinfo}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpinfo}" path="pinfo" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.DATAINICI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="solicitud.dataInici" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataIniciDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataIniciDesde" path="dataIniciDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataIniciDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataIniciDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataIniciFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataIniciFins" path="dataIniciFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataIniciFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataIniciFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.DATAFI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="solicitud.dataFi" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataFiDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFiDesde" path="dataFiDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFiDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataFiDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataFiFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFiFins" path="dataFiFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFiFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataFiFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PERSONACONTACTE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.personaContacte" var="personaContacte" />
              <fmt:message key="genapp.form.searchby" var="cercaperpersonaContacte" >                
                 <fmt:param value="${personaContacte}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${personaContacte}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpersonaContacte}" path="personaContacte" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PERSONACONTACTEEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.personaContacteEmail" var="personaContacteEmail" />
              <fmt:message key="genapp.form.searchby" var="cercaperpersonaContacteEmail" >                
                 <fmt:param value="${personaContacteEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${personaContacteEmail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpersonaContacteEmail}" path="personaContacteEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.RESPONSABLEPROCNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.responsableProcNom" var="responsableProcNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperresponsableProcNom" >                
                 <fmt:param value="${responsableProcNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${responsableProcNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresponsableProcNom}" path="responsableProcNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.RESPONSABLEPROCEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.responsableProcEmail" var="responsableProcEmail" />
              <fmt:message key="genapp.form.searchby" var="cercaperresponsableProcEmail" >                
                 <fmt:param value="${responsableProcEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${responsableProcEmail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresponsableProcEmail}" path="responsableProcEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.NOTES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.notes" var="notes" />
              <fmt:message key="genapp.form.searchby" var="cercapernotes" >                
                 <fmt:param value="${notes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${notes}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernotes}" path="notes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.FIRMATDOCSOLICITUD)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.firmatDocSolicitud" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmatDocSolicitudDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="firmatDocSolicitudFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PRODUCCIO)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.produccio" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="produccioDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="produccioFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.DENOMINACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.denominacio" var="denominacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdenominacio" >                
                 <fmt:param value="${denominacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${denominacio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdenominacio}" path="denominacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.DIR3)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.dir3" var="dir3" />
              <fmt:message key="genapp.form.searchby" var="cercaperdir3" >                
                 <fmt:param value="${dir3}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${dir3}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdir3}" path="dir3" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.NIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.nif" var="nif" />
              <fmt:message key="genapp.form.searchby" var="cercapernif" >                
                 <fmt:param value="${nif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernif}" path="nif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.CREADOR)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.creador" var="creador" />
              <fmt:message key="genapp.form.searchby" var="cercapercreador" >                
                 <fmt:param value="${creador}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${creador}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercreador}" path="creador" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.OPERADOR)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.operador" var="operador" />
              <fmt:message key="genapp.form.searchby" var="cercaperoperador" >                
                 <fmt:param value="${operador}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${operador}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperoperador}" path="operador" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.ESTATPINBAL)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="solicitud.estatpinbal" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="solicitud_estatpinbal_select" path="estatpinbalSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstatpinbal}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatpinbalSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#solicitud_estatpinbal_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.CONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.consentiment" var="consentiment" />
              <fmt:message key="genapp.form.searchby" var="cercaperconsentiment" >                
                 <fmt:param value="${consentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${consentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperconsentiment}" path="consentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.URLCONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.urlconsentiment" var="urlconsentiment" />
              <fmt:message key="genapp.form.searchby" var="cercaperurlconsentiment" >                
                 <fmt:param value="${urlconsentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${urlconsentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperurlconsentiment}" path="urlconsentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.CONSENTIMENTADJUNT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitud.consentimentadjunt" var="consentimentadjunt" />
              <fmt:message key="genapp.form.searchby" var="cercaperconsentimentadjunt" >                
                 <fmt:param value="${consentimentadjunt}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${consentimentadjunt}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperconsentimentadjunt}" path="consentimentadjunt" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudFields.PORTAFIBID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitud.portafibID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="portafibIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="portafibIDFins" />

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
  
