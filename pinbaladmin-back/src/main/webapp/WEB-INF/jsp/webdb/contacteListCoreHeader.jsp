<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ContacteFields" className="org.fundaciobit.pinbaladmin.model.fields.ContacteFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.CONTACTEID)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.CONTACTEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.CARREC)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.CARREC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.TELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.TELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ContacteFields.MAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,ContacteFields.MAIL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

