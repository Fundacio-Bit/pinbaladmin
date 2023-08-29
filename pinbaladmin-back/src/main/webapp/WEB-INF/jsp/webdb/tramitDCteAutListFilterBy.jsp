<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitDCteAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.CTEAUTID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitDCteAut.cteautid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="cteautidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="cteautidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.TRAMITID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="tramitDCteAut.tramitid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tramitidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tramitidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.NIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.nif" var="nif" />
              <fmt:message key="genapp.form.searchby" var="cercapernif" >                
                 <fmt:param value="${nif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernif}" path="nif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.LLINATGE1)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.llinatge1" var="llinatge1" />
              <fmt:message key="genapp.form.searchby" var="cercaperllinatge1" >                
                 <fmt:param value="${llinatge1}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${llinatge1}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperllinatge1}" path="llinatge1" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.LLINATGE2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.llinatge2" var="llinatge2" />
              <fmt:message key="genapp.form.searchby" var="cercaperllinatge2" >                
                 <fmt:param value="${llinatge2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${llinatge2}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperllinatge2}" path="llinatge2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.CARREC)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.carrec" var="carrec" />
              <fmt:message key="genapp.form.searchby" var="cercapercarrec" >                
                 <fmt:param value="${carrec}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${carrec}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercarrec}" path="carrec" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.TELEFON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.telefon" var="telefon" />
              <fmt:message key="genapp.form.searchby" var="cercapertelefon" >                
                 <fmt:param value="${telefon}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${telefon}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertelefon}" path="telefon" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,TramitDCteAutFields.MAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="tramitDCteAut.mail" var="mail" />
              <fmt:message key="genapp.form.searchby" var="cercapermail" >                
                 <fmt:param value="${mail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${mail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermail}" path="mail" />
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
  
