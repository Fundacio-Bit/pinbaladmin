<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EmailFields" className="org.fundaciobit.pinbaladmin.model.fields.EmailFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.EMAILID)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.EMAILID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.DESTINATARIS)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.DESTINATARIS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.SUBJECT)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.SUBJECT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.MESSAGE)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.MESSAGE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.DATAENVIAMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.DATAENVIAMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EmailFields.ENVIADOR)}">
        <th>${pad:getSortIcons(__theFilterForm,EmailFields.ENVIADOR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

