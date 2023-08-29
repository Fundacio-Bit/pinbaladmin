<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitFCteTecFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitFCteTec.ctetecid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.CTETECID)}">
          <td>
          ${tramitFCteTec.ctetecid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.TRAMITID)}">
          <td>
          <c:set var="tmp">${tramitFCteTec.tramitid}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTramitAPersAutForTramitid[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.NIF)}">
          <td>
          ${tramitFCteTec.nif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.NOM)}">
          <td>
          ${tramitFCteTec.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.LLINATGE1)}">
          <td>
          ${tramitFCteTec.llinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.LLINATGE2)}">
          <td>
          ${tramitFCteTec.llinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.CARREC)}">
          <td>
          ${tramitFCteTec.carrec}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.TELEFON)}">
          <td>
          ${tramitFCteTec.telefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.MAIL)}">
          <td>
          ${tramitFCteTec.mail}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tramitFCteTec.ctetecid]}" />
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


