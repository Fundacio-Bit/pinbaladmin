<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampFormulariFields" className="org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampFormulariFields.CAMPFORMULARIID)}">
        <th>${pad:getSortIcons(__theFilterForm,CampFormulariFields.CAMPFORMULARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampFormulariFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,CampFormulariFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampFormulariFields.CAMPPDF)}">
        <th>${pad:getSortIcons(__theFilterForm,CampFormulariFields.CAMPPDF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampFormulariFields.FORMULARIID)}">
        <th>${pad:getSortIcons(__theFilterForm,CampFormulariFields.FORMULARIID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

