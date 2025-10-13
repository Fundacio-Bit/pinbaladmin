<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.MODSOLIID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="modificacioSolicitud.modsoliID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="modsoliIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="modsoliIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.SOLICITUDID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="modificacioSolicitud.solicitudID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="solicitudIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="solicitudIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.PROCEDIMENTCODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.procedimentCodi" var="procedimentCodi" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentCodi" >                
                 <fmt:param value="${procedimentCodi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentCodi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentCodi}" path="procedimentCodi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.PROCEDIMENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.procedimentNom" var="procedimentNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentNom" >                
                 <fmt:param value="${procedimentNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentNom}" path="procedimentNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.CODISIANOU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.codiSiaNou" var="codiSiaNou" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiSiaNou" >                
                 <fmt:param value="${codiSiaNou}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiSiaNou}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiSiaNou}" path="codiSiaNou" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.ESTATID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="modificacioSolicitud.estatID" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="modsoli_estatID_select" path="estatIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstatID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#modsoli_estatID_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.DATAINICI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="modificacioSolicitud.dataInici" />:</span>
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.DATAFI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="modificacioSolicitud.dataFi" />:</span>
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.PROCEDIMENTTIPUS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.procedimentTipus" var="procedimentTipus" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocedimentTipus" >                
                 <fmt:param value="${procedimentTipus}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procedimentTipus}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocedimentTipus}" path="procedimentTipus" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.NOTES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.notes" var="notes" />
              <fmt:message key="genapp.form.searchby" var="cercapernotes" >                
                 <fmt:param value="${notes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${notes}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernotes}" path="notes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.ORGANID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="modificacioSolicitud.organID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="organIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="organIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.RESPONSABLEPROCNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.responsableProcNom" var="responsableProcNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperresponsableProcNom" >                
                 <fmt:param value="${responsableProcNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${responsableProcNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresponsableProcNom}" path="responsableProcNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.responsableProceMail" var="responsableProceMail" />
              <fmt:message key="genapp.form.searchby" var="cercaperresponsableProceMail" >                
                 <fmt:param value="${responsableProceMail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${responsableProceMail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresponsableProceMail}" path="responsableProceMail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.CONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.consentiment" var="consentiment" />
              <fmt:message key="genapp.form.searchby" var="cercaperconsentiment" >                
                 <fmt:param value="${consentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${consentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperconsentiment}" path="consentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.SOLICITANTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.solicitantNom" var="solicitantNom" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantNom" >                
                 <fmt:param value="${solicitantNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantNom}" path="solicitantNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.SOLICITANTNIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.solicitantNif" var="solicitantNif" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantNif" >                
                 <fmt:param value="${solicitantNif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantNif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantNif}" path="solicitantNif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.SOLICITANTMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.solicitantMail" var="solicitantMail" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantMail" >                
                 <fmt:param value="${solicitantMail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantMail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantMail}" path="solicitantMail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.SOLICITANTUSERNAME)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.solicitantUsername" var="solicitantUsername" />
              <fmt:message key="genapp.form.searchby" var="cercapersolicitantUsername" >                
                 <fmt:param value="${solicitantUsername}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${solicitantUsername}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersolicitantUsername}" path="solicitantUsername" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.ESTATMODIFICACIO)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="modificacioSolicitud.estatModificacio" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="modsoli_estatModificacio_select" path="estatModificacioSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstatModificacio}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatModificacioSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#modsoli_estatModificacio_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.CONTACTENOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.contactenom" var="contactenom" />
              <fmt:message key="genapp.form.searchby" var="cercapercontactenom" >                
                 <fmt:param value="${contactenom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${contactenom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontactenom}" path="contactenom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.CONTACTEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="modificacioSolicitud.contactemail" var="contactemail" />
              <fmt:message key="genapp.form.searchby" var="cercapercontactemail" >                
                 <fmt:param value="${contactemail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${contactemail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontactemail}" path="contactemail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ModificacioSolicitudFields.ESMENA)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="modificacioSolicitud.esmena" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="esmenaDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="esmenaFins" />

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
  
