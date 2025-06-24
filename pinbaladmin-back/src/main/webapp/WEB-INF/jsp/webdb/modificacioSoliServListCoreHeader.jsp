<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSoliServFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.MODSOLISERVID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.MODSOLISERVID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.SOLISERVID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.SOLISERVID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.MODSOLIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.MODSOLIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ESTAT)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA1)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.NORMA1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES1)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.ARTICLES1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA1ID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.FITXERNORMA1ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA2)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.NORMA2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES2)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.ARTICLES2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA2ID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.FITXERNORMA2ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.NORMA3)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.NORMA3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.ARTICLES3)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.ARTICLES3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA3ID)}">
        <th>${pad:getSortIcons(__theFilterForm,ModificacioSoliServFields.FITXERNORMA3ID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

