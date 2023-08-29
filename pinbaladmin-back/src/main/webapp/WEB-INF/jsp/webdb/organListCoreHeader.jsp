<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OrganFields" className="org.fundaciobit.pinbaladmin.model.fields.OrganFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganFields.ORGANID)}">
        <th>${pad:getSortIcons(__theFilterForm,OrganFields.ORGANID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,OrganFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganFields.DIR3)}">
        <th>${pad:getSortIcons(__theFilterForm,OrganFields.DIR3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganFields.DIR3PARE)}">
        <th>${pad:getSortIcons(__theFilterForm,OrganFields.DIR3PARE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganFields.ENTITATID)}">
        <th>${pad:getSortIcons(__theFilterForm,OrganFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

