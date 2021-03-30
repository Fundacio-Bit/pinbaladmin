<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TiquetFields" className="org.fundaciobit.pinbaladmin.model.fields.TiquetFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TIQUETID)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.TIQUETID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TITOL)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.TITOL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.INFORMADOR)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.INFORMADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAALTA)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.DATAALTA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ESTATTIQUETID)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.ESTATTIQUETID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.TIPUSTIQUETID)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.TIPUSTIQUETID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.VERSIOPINBAL)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.VERSIOPINBAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAINICI)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAINCIDENCIA)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.DATAINCIDENCIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.SOLUCIONATPER)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.SOLUCIONATPER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.DATAFI)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.NOTES)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.NOTES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ENTORN)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.ENTORN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ADJUNT1ID)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.ADJUNT1ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TiquetFields.ADJUNT2ID)}">
        <th>${pad:getSortIcons(__theFilterForm,TiquetFields.ADJUNT2ID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

