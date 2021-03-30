<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentCedentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.DOCUMENTCEDENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.DOCUMENTCEDENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.TITOL)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.TITOL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.ENTITATSERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.ENTITATSERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.DATACREACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentCedentFields.FITXERID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentCedentFields.FITXERID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

