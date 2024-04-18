<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitudServei.id" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="idDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="idFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.SOLICITUDID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitudServei.solicitudID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="solicitudIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="solicitudIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.SERVEIID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="solicitudServei.serveiID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="serveiIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="serveiIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="solicitudServei.estatSolicitudServeiID" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="soliservei_estatSolicitudServeiID_select" path="estatSolicitudServeiIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstatSolicitudServeiID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatSolicitudServeiIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#soliservei_estatSolicitudServeiID_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.enllazNormaLegal" var="enllazNormaLegal" />
              <fmt:message key="genapp.form.searchby" var="cercaperenllazNormaLegal" >                
                 <fmt:param value="${enllazNormaLegal}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enllazNormaLegal}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenllazNormaLegal}" path="enllazNormaLegal" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.tipusConsentiment" var="tipusConsentiment" />
              <fmt:message key="genapp.form.searchby" var="cercapertipusConsentiment" >                
                 <fmt:param value="${tipusConsentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${tipusConsentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertipusConsentiment}" path="tipusConsentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.CONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.consentiment" var="consentiment" />
              <fmt:message key="genapp.form.searchby" var="cercaperconsentiment" >                
                 <fmt:param value="${consentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${consentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperconsentiment}" path="consentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.enllazConsentiment" var="enllazConsentiment" />
              <fmt:message key="genapp.form.searchby" var="cercaperenllazConsentiment" >                
                 <fmt:param value="${enllazConsentiment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enllazConsentiment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenllazConsentiment}" path="enllazConsentiment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.NOTES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.notes" var="notes" />
              <fmt:message key="genapp.form.searchby" var="cercapernotes" >                
                 <fmt:param value="${notes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${notes}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernotes}" path="notes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.CADUCA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.caduca" var="caduca" />
              <fmt:message key="genapp.form.searchby" var="cercapercaduca" >                
                 <fmt:param value="${caduca}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${caduca}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercaduca}" path="caduca" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.FECHACADUCA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.fechaCaduca" var="fechaCaduca" />
              <fmt:message key="genapp.form.searchby" var="cercaperfechaCaduca" >                
                 <fmt:param value="${fechaCaduca}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${fechaCaduca}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfechaCaduca}" path="fechaCaduca" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.NORMALEGAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.normaLegal" var="normaLegal" />
              <fmt:message key="genapp.form.searchby" var="cercapernormaLegal" >                
                 <fmt:param value="${normaLegal}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${normaLegal}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernormaLegal}" path="normaLegal" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ARTICLES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.articles" var="articles" />
              <fmt:message key="genapp.form.searchby" var="cercaperarticles" >                
                 <fmt:param value="${articles}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${articles}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarticles}" path="articles" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.NORMA2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.norma2" var="norma2" />
              <fmt:message key="genapp.form.searchby" var="cercapernorma2" >                
                 <fmt:param value="${norma2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${norma2}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernorma2}" path="norma2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ARTICLES2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.articles2" var="articles2" />
              <fmt:message key="genapp.form.searchby" var="cercaperarticles2" >                
                 <fmt:param value="${articles2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${articles2}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarticles2}" path="articles2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.NORMA3)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.norma3" var="norma3" />
              <fmt:message key="genapp.form.searchby" var="cercapernorma3" >                
                 <fmt:param value="${norma3}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${norma3}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernorma3}" path="norma3" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,SolicitudServeiFields.ARTICLES3)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="solicitudServei.articles3" var="articles3" />
              <fmt:message key="genapp.form.searchby" var="cercaperarticles3" >                
                 <fmt:param value="${articles3}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${articles3}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarticles3}" path="articles3" />
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
  
