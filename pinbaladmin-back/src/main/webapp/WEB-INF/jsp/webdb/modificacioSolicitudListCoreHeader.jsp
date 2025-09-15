<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.MODSOLIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.MODSOLIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.SOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTCODI)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.PROCEDIMENTCODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.PROCEDIMENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CODISIANOU)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.CODISIANOU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ESTATID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.ESTATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DATAINICI)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DATAFI)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTTIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.PROCEDIMENTTIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.NOTES)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.NOTES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ORGANID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.ORGANID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.RESPONSABLEPROCNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.CONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DOCCONSENTIMENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.DOCCONSENTIMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.SOLICITANTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNIF)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.SOLICITANTNIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.SOLICITANTMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTUSERNAME)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.SOLICITANTUSERNAME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ESTATMODIFICACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.ESTATMODIFICACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CONTACTENOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.CONTACTENOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CONTACTEMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSolicitudFields.CONTACTEMAIL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

