<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PinfoFields" className="org.fundaciobit.pinbaladmin.model.fields.PinfoFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[pinfo.pinfoID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.PINFOID)}">
          <td>
          ${pinfo.pinfoID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.INCIDENCIAID)}">
          <td>
          <c:set var="tmp">${pinfo.incidenciaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfIncidenciaTecnicaForIncidenciaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.ENTITAT)}">
          <td>
          <c:set var="tmp">${pinfo.entitat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEntitat[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.SOLICITANTNIF)}">
          <td>
          ${pinfo.solicitantNIF}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.ESTAT)}">
          <td>
          <c:set var="tmp">${pinfo.estat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstat[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.FITXERID)}">
          <td>
            <c:if test="${not empty pinfo.fitxer}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(pinfo.fitxer)}"/>">${pinfo.fitxer.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.FITXERFIRMATID)}">
          <td>
            <c:if test="${not empty pinfo.fitxerfirmat}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(pinfo.fitxerfirmat)}"/>">${pinfo.fitxerfirmat.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.PORTAFIBID)}">
          <td>
          ${pinfo.portafibid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.DESTINATARINIF)}">
          <td>
          ${pinfo.destinatariNIF}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.DESTINATARINOM)}">
          <td>
          ${pinfo.destinatariNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.MISSATGEPINBAL)}">
          <td>
          ${pinfo.missatgePinbal}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.LOGPPNBAL)}">
          <td>
          ${pinfo.logpPnbal}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.MISSATGESOLICITANT)}">
          <td>
          ${pinfo.missatgeSolicitant}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[pinfo.pinfoID]}" />
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


