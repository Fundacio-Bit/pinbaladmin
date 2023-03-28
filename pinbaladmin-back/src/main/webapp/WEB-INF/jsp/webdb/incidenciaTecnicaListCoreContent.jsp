<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IncidenciaTecnicaFields" className="org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[incidenciaTecnica.incidenciaTecnicaID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.INCIDENCIATECNICAID)}">
          <td>
          ${incidenciaTecnica.incidenciaTecnicaID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.TITOL)}">
          <td>
          ${incidenciaTecnica.titol}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DESCRIPCIO)}">
          <td>
          ${incidenciaTecnica.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${incidenciaTecnica.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${incidenciaTecnica.dataFi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.ESTAT)}">
          <td>
          <c:set var="tmp">${incidenciaTecnica.estat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstat[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.TIPUS)}">
          <td>
          <c:set var="tmp">${incidenciaTecnica.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.NOMENTITAT)}">
          <td>
          ${incidenciaTecnica.nomEntitat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTENOM)}">
          <td>
          ${incidenciaTecnica.contacteNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTEEMAIL)}">
          <td>
          ${incidenciaTecnica.contacteEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTETELEFON)}">
          <td>
          ${incidenciaTecnica.contacteTelefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)}">
          <td>
          ${incidenciaTecnica.caidIdentificadorConsulta}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)}">
          <td>
          ${incidenciaTecnica.caidNumeroSeguiment}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CREADOR)}">
          <td>
          <c:set var="tmp">${incidenciaTecnica.creador}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForCreador[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.OPERADOR)}">
          <td>
          <c:set var="tmp">${incidenciaTecnica.operador}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForOperador[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[incidenciaTecnica.incidenciaTecnicaID]}" />
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


