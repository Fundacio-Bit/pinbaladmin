<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitDCteAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitDCteAut.cteautid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.CTEAUTID)}">
          <td>
          ${tramitDCteAut.cteautid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.TRAMITID)}">
          <td>
          <c:set var="tmp">${tramitDCteAut.tramitid}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTramitAPersAutForTramitid[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.NIF)}">
          <td>
          ${tramitDCteAut.nif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.NOM)}">
          <td>
          ${tramitDCteAut.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.LLINATGE1)}">
          <td>
          ${tramitDCteAut.llinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.LLINATGE2)}">
          <td>
          ${tramitDCteAut.llinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.CARREC)}">
          <td>
          ${tramitDCteAut.carrec}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.TELEFON)}">
          <td>
          ${tramitDCteAut.telefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.MAIL)}">
          <td>
          ${tramitDCteAut.mail}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitDCteAut.cteautid]}" />
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


