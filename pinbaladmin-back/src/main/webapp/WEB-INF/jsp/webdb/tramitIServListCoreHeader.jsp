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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.ARTICLES)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.ARTICLES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.CONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.CONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.URLNORMA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.URLNORMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.CONSENTIMENTPUBLICAT)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.CONSENTIMENTPUBLICAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitIServFields.URLCONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitIServFields.URLCONSENTIMENT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

