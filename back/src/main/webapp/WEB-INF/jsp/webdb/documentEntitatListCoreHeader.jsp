<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.DOCUMENTENTITATID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.DOCUMENTENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.TITOL)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.TITOL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.ENTITATID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.FITXERID)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,DocumentEntitatFields.DATAALTA)}">
        <th>${pad:getSortIcons(__theFilterForm,DocumentEntitatFields.DATAALTA)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

