<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TiquetFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tiquet.tiquetID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TIQUETID)}">
          <td>
          <c:out value="${tiquet.tiquetID}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TITOL)}">
          <td>
          <c:out value="${tiquet.titol}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DESCRIPCIO)}">
          <td>
          <c:out value="${tiquet.descripcio}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.INFORMADOR)}">
          <td>
          <c:out value="${tiquet.informador}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAALTA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${tiquet.dataAlta}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ESTATTIQUETID)}">
          <td>
          <c:set var="tmp">${tiquet.estatTiquetID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEstatTiquetForEstatTiquetID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TIPUSTIQUETID)}">
          <td>
          <c:set var="tmp">${tiquet.tipusTiquetID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusTiquetForTipusTiquetID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.VERSIOPINBAL)}">
          <td>
          <c:out value="${tiquet.versioPinbal}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${tiquet.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAINCIDENCIA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${tiquet.dataIncidencia}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.SOLUCIONATPER)}">
          <td>
          <c:out value="${tiquet.solucionatPer}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${tiquet.datafi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.NOTES)}">
          <td>
          <c:out value="${tiquet.notes}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ENTORN)}">
          <td>
          <c:set var="tmp">${tiquet.entorn}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEntorn[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ADJUNT1ID)}">
          <td>
            <c:if test="${not empty tiquet.adjunt1}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(tiquet.adjunt1)}"/>">${tiquet.adjunt1.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ADJUNT2ID)}">
          <td>
            <c:if test="${not empty tiquet.adjunt2}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(tiquet.adjunt2)}"/>">${tiquet.adjunt2.nom}</a>
            </c:if>
           </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[tiquet.tiquetID]}" />
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


