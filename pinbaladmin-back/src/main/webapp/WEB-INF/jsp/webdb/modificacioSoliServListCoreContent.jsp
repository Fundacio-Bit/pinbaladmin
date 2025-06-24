<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSoliServFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[modificacioSoliServ.modsoliservid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.MODSOLISERVID)}">
          <td>
          ${modificacioSoliServ.modsoliservid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.SOLISERVID)}">
          <td>
          <c:set var="tmp">${modificacioSoliServ.soliServID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfSolicitudServeiForSoliServID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.MODSOLIID)}">
          <td>
          <c:set var="tmp">${modificacioSoliServ.modSoliID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfModificacioSolicitudForModSoliID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ESTAT)}">
          <td>
          ${modificacioSoliServ.estat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA1)}">
          <td>
          ${modificacioSoliServ.norma1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES1)}">
          <td>
          ${modificacioSoliServ.articles1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA1ID)}">
          <td>
            <c:if test="${not empty modificacioSoliServ.fitxerNorma1}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(modificacioSoliServ.fitxerNorma1)}"/>">${modificacioSoliServ.fitxerNorma1.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA2)}">
          <td>
          ${modificacioSoliServ.norma2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES2)}">
          <td>
          ${modificacioSoliServ.articles2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA2ID)}">
          <td>
            <c:if test="${not empty modificacioSoliServ.fitxerNorma2}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(modificacioSoliServ.fitxerNorma2)}"/>">${modificacioSoliServ.fitxerNorma2.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA3)}">
          <td>
          ${modificacioSoliServ.norma3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES3)}">
          <td>
          ${modificacioSoliServ.articles3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA3ID)}">
          <td>
            <c:if test="${not empty modificacioSoliServ.fitxerNorma3}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(modificacioSoliServ.fitxerNorma3)}"/>">${modificacioSoliServ.fitxerNorma3.nom}</a>
            </c:if>
           </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[modificacioSoliServ.modsoliservid]}" />
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


