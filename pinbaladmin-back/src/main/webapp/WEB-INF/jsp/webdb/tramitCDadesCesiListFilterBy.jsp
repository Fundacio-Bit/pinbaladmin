<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitCDadesCesiFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.DADESCESIID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitCDadesCesi.dadescesiid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="dadescesiidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="dadescesiidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.TRAMITID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitCDadesCesi.tramitid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tramitidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tramitidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.ORGANID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitCDadesCesi.organID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="organIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="organIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.ORGANARRELID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitCDadesCesi.organArrelID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="organArrelIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="organArrelIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.DENOMINACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.denominacio" var="denominacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdenominacio" >                
                 <fmt:param value="${denominacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${denominacio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdenominacio}" path="denominacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.NIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.nif" var="nif" />
              <fmt:message key="genapp.form.searchby" var="cercapernif" >                
                 <fmt:param value="${nif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernif}" path="nif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.RESPONSABLE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.responsable" var="responsable" />
              <fmt:message key="genapp.form.searchby" var="cercaperresponsable" >                
                 <fmt:param value="${responsable}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${responsable}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresponsable}" path="responsable" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.DIR3RESPONSABLE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.dir3responsable" var="dir3responsable" />
              <fmt:message key="genapp.form.searchby" var="cercaperdir3responsable" >                
                 <fmt:param value="${dir3responsable}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${dir3responsable}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdir3responsable}" path="dir3responsable" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.DIR3ARREL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.dir3arrel" var="dir3arrel" />
              <fmt:message key="genapp.form.searchby" var="cercaperdir3arrel" >                
                 <fmt:param value="${dir3arrel}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${dir3arrel}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdir3arrel}" path="dir3arrel" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.DIRECCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.direccio" var="direccio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdireccio" >                
                 <fmt:param value="${direccio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${direccio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdireccio}" path="direccio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.CODIPOSTAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.codipostal" var="codipostal" />
              <fmt:message key="genapp.form.searchby" var="cercapercodipostal" >                
                 <fmt:param value="${codipostal}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codipostal}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodipostal}" path="codipostal" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitCDadesCesiFields.MUNICIPI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitCDadesCesi.municipi" var="municipi" />
              <fmt:message key="genapp.form.searchby" var="cercapermunicipi" >                
                 <fmt:param value="${municipi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${municipi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermunicipi}" path="municipi" />
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
  
