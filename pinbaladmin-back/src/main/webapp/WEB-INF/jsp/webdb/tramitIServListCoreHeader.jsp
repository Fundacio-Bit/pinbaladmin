<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitIServFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitIServFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.SERVID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.SERVID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.CODI)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.NORMA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.NORMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.FITXERNORMAID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.FITXERNORMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.URLNORMA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.URLNORMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.ARTICLES)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.ARTICLES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.NORMA2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.NORMA2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.FITXERNORMA2ID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.FITXERNORMA2ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.ARTICLES2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.ARTICLES2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.NORMA3)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.NORMA3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.FITXERNORMA3ID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.FITXERNORMA3ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.ARTICLES3)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.ARTICLES3)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

