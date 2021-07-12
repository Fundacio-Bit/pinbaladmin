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
          <c:out value="${solicitud.solicitudID}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTCODI)}">
          <td>
          <c:out value="${solicitud.procedimentCodi}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.CODIDESCRIPTIU)}">
          <td>
          <c:out value="${solicitud.codiDescriptiu}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PROCEDIMENTNOM)}">
          <td>
          <c:out value="${solicitud.procedimentNom}" />
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
          <c:out value="${solicitud.ticketAssociat}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.TICKETNUMEROSEGUIMENT)}">
          <td>
          <c:out value="${solicitud.ticketNumeroSeguiment}" />
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
          <c:out value="${solicitud.entitatEstatal}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PINFO)}">
          <td>
          <c:out value="${solicitud.pinfo}" />
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
          <c:out value="${solicitud.personaContacte}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.PERSONACONTACTEEMAIL)}">
          <td>
          <c:out value="${solicitud.personaContacteEmail}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCNOM)}">
          <td>
          <c:out value="${solicitud.responsableProcNom}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.RESPONSABLEPROCEMAIL)}">
          <td>
          <c:out value="${solicitud.responsableProcEmail}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NOTES)}">
          <td>
          <c:out value="${solicitud.notes}" />
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
          <c:out value="${solicitud.creador}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DENOMINACIO)}">
          <td>
          <c:out value="${solicitud.denominacio}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.DIR3)}">
          <td>
          <c:out value="${solicitud.dir3}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudFields.NIF)}">
          <td>
          <c:out value="${solicitud.nif}" />
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


