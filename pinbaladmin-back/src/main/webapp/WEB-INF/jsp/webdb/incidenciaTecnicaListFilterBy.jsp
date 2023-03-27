<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IncidenciaTecnicaFields" className="org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.INCIDENCIATECNICAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="incidenciaTecnica.incidenciaTecnicaID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="incidenciaTecnicaIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="incidenciaTecnicaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.TITOL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.titol" var="titol" />
              <fmt:message key="genapp.form.searchby" var="cercapertitol" >                
                 <fmt:param value="${titol}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${titol}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertitol}" path="titol" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.DATAINICI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="incidenciaTecnica.dataInici" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group">
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
            <div class="form-group">
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.DATAFI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="incidenciaTecnica.dataFi" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group">
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
            <div class="form-group">
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.ESTAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="incidenciaTecnica.estat" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="estatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="estatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CREADOR)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.creador" var="creador" />
              <fmt:message key="genapp.form.searchby" var="cercapercreador" >                
                 <fmt:param value="${creador}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${creador}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercreador}" path="creador" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.TIPUS)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="incidenciaTecnica.tipus" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.NOMENTITAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.nomEntitat" var="nomEntitat" />
              <fmt:message key="genapp.form.searchby" var="cercapernomEntitat" >                
                 <fmt:param value="${nomEntitat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nomEntitat}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernomEntitat}" path="nomEntitat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CONTACTENOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.contacteNom" var="contacteNom" />
              <fmt:message key="genapp.form.searchby" var="cercapercontacteNom" >                
                 <fmt:param value="${contacteNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${contacteNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontacteNom}" path="contacteNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CONTACTEEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.contacteEmail" var="contacteEmail" />
              <fmt:message key="genapp.form.searchby" var="cercapercontacteEmail" >                
                 <fmt:param value="${contacteEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${contacteEmail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontacteEmail}" path="contacteEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CONTACTETELEFON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.contacteTelefon" var="contacteTelefon" />
              <fmt:message key="genapp.form.searchby" var="cercapercontacteTelefon" >                
                 <fmt:param value="${contacteTelefon}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${contacteTelefon}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontacteTelefon}" path="contacteTelefon" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.caidIdentificadorConsulta" var="caidIdentificadorConsulta" />
              <fmt:message key="genapp.form.searchby" var="cercapercaidIdentificadorConsulta" >                
                 <fmt:param value="${caidIdentificadorConsulta}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${caidIdentificadorConsulta}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercaidIdentificadorConsulta}" path="caidIdentificadorConsulta" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="incidenciaTecnica.caidNumeroSeguiment" var="caidNumeroSeguiment" />
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
  
