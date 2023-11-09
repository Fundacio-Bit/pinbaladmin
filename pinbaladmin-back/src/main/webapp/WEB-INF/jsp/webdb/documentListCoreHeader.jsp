<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.DOCUMENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.DOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.FITXERORIGINALID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.FITXERORIGINALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.FITXERFIRMATID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.FITXERFIRMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.NOTES)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.NOTES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentFields.TIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentFields.TIPUS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

