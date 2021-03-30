<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.EntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
        <th>${pad:getSortIcons(__theFilterForm,EntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,EntitatFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PERSONACONTACTE)}">
        <th>${pad:getSortIcons(__theFilterForm,EntitatFields.PERSONACONTACTE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CIF)}">
        <th>${pad:getSortIcons(__theFilterForm,EntitatFields.CIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.GRUPENTITATID)}">
        <th>${pad:getSortIcons(__theFilterForm,EntitatFields.GRUPENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

