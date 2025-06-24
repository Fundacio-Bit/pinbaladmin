<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[modificacioSolicitud.modsoliID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.MODSOLIID)}">
          <td>
          ${modificacioSolicitud.modsoliID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITUDID)}">
          <td>
          <c:set var="tmp">${modificacioSolicitud.solicitudID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfSolicitudForSolicitudID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTCODI)}">
          <td>
          ${modificacioSolicitud.procedimentCodi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.PROCEDIMENTNOM)}">
          <td>
          ${modificacioSolicitud.procedimentNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CODISIANOU)}">
          <td>
          ${modificacioSolicitud.codiSiaNou}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ESTATID)}">
          <td>
          <c:set var="tmp">${modificacioSolicitud.estatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${modificacioSolicitud.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${modificacioSolicitud.dataFi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.NOTES)}">
          <td>
          ${modificacioSolicitud.notes}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ORGANID)}">
          <td>
          <c:set var="tmp">${modificacioSolicitud.organID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfOrganForOrganID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCNOM)}">
          <td>
          ${modificacioSolicitud.responsableProcNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.RESPONSABLEPROCEMAIL)}">
          <td>
          ${modificacioSolicitud.responsableProceMail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.CONSENTIMENT)}">
          <td>
          <c:set var="tmp">${modificacioSolicitud.consentiment}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForConsentiment[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.DOCCONSENTIMENTID)}">
          <td>
            <c:if test="${not empty modificacioSolicitud.doCconsentiment}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(modificacioSolicitud.doCconsentiment)}"/>">${modificacioSolicitud.doCconsentiment.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNOM)}">
          <td>
          ${modificacioSolicitud.solicitantNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTNIF)}">
          <td>
          ${modificacioSolicitud.solicitantNif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTMAIL)}">
          <td>
          ${modificacioSolicitud.solicitantMail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.SOLICITANTUSERNAME)}">
          <td>
          ${modificacioSolicitud.solicitantUsername}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSolicitudFields.ESTATMODIFICACIO)}">
          <td>
          <c:set var="tmp">${modificacioSolicitud.estatModificacio}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstatModificacio[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[modificacioSolicitud.modsoliID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


