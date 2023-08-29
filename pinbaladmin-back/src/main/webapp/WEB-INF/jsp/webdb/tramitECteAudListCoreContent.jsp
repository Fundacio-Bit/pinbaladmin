<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitECteAudFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitECteAud.cteaudid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.CTEAUDID)}">
          <td>
          ${tramitECteAud.cteaudid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.TRAMITID)}">
          <td>
          <c:set var="tmp">${tramitECteAud.tramitid}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTramitAPersAutForTramitid[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.NIF)}">
          <td>
          ${tramitECteAud.nif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.NOM)}">
          <td>
          ${tramitECteAud.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.LLINATGE1)}">
          <td>
          ${tramitECteAud.llinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.LLINATGE2)}">
          <td>
          ${tramitECteAud.llinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.CARREC)}">
          <td>
          ${tramitECteAud.carrec}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.TELEFON)}">
          <td>
          ${tramitECteAud.telefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.MAIL)}">
          <td>
          ${tramitECteAud.mail}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitECteAud.cteaudid]}" />
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


