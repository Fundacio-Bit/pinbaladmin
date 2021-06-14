<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitud.solicitudID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.SOLICITUDID)}">
          <td>
          ${solicitud.solicitudID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTCODI)}">
          <td>
          ${solicitud.procedimentCodi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CODIDESCRIPTIU)}">
          <td>
          ${solicitud.codiDescriptiu}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTNOM)}">
          <td>
          ${solicitud.procedimentNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTTIPUS)}">
          <td>
          <c:set var="tmp">${solicitud.procedimentTipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForProcedimentTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ESTATID)}">
          <td>
          <c:set var="tmp">${solicitud.estatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEstatSolicitudForEstatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.TICKETASSOCIAT)}">
          <td>
          ${solicitud.ticketAssociat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.TICKETNUMEROSEGUIMENT)}">
          <td>
          ${solicitud.ticketNumeroSeguiment}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DEPARTAMENTID)}">
          <td>
          <c:set var="tmp">${solicitud.departamentID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfDepartamentForDepartamentID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.ENTITATESTATAL)}">
          <td>
          ${solicitud.entitatEstatal}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PINFO)}">
          <td>
          ${solicitud.pinfo}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${solicitud.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${solicitud.dataFi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PERSONACONTACTE)}">
          <td>
          ${solicitud.personaContacte}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PERSONACONTACTEEMAIL)}">
          <td>
          ${solicitud.personaContacteEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCNOM)}">
          <td>
          ${solicitud.responsableProcNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCEMAIL)}">
          <td>
          ${solicitud.responsableProcEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NOTES)}">
          <td>
          ${solicitud.notes}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DOCUMENTSOLICITUDID)}">
          <td>
            <c:if test="${not empty solicitud.documentSolicitud}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(solicitud.documentSolicitud)}"/>">${solicitud.documentSolicitud.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.SOLICITUDXMLID)}">
          <td>
            <c:if test="${not empty solicitud.solicitudXml}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(solicitud.solicitudXml)}"/>">${solicitud.solicitudXml.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.FIRMATDOCSOLICITUD)}">
          <td>
            <fmt:message key="solicitud.firmatdocsolicitud.${solicitud.firmatDocSolicitud}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PRODUCCIO)}">
          <td>
            <fmt:message key="solicitud.produccio.${solicitud.produccio}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CREADOR)}">
          <td>
          ${solicitud.creador}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DENOMINACIO)}">
          <td>
          ${solicitud.denominacio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DIR3)}">
          <td>
          ${solicitud.dir3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NIF)}">
          <td>
          ${solicitud.nif}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitud.solicitudID]}" />
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


