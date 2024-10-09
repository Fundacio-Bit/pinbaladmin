<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.SOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.SOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTCODI)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PROCEDIMENTCODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CODIDESCRIPTIU)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.CODIDESCRIPTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PROCEDIMENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTTIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PROCEDIMENTTIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.EXPEDIENTPID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.EXPEDIENTPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ESTATID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.ESTATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ORGANID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.ORGANID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ENTITATESTATAL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.ENTITATESTATAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PINFO)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PINFO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DATAINICI)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DATAFI)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PERSONACONTACTE)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PERSONACONTACTE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PERSONACONTACTEEMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PERSONACONTACTEEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.RESPONSABLEPROCNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCEMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.RESPONSABLEPROCEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NOTES)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.NOTES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DOCUMENTSOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.DOCUMENTSOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.SOLICITUDXMLID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.SOLICITUDXMLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.FIRMATDOCSOLICITUD)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.FIRMATDOCSOLICITUD)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PRODUCCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PRODUCCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DENOMINACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.DENOMINACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DIR3)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.DIR3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CREADOR)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.CREADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.OPERADOR)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.OPERADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ESTATPINBAL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.ESTATPINBAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.CONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.URLCONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.URLCONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CONSENTIMENTADJUNT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.CONSENTIMENTADJUNT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PORTAFIBID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudFields.PORTAFIBID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

