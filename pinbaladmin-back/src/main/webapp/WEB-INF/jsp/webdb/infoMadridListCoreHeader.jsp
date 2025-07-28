<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoMadridFields" className="org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.INFOMADRIDID)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.INFOMADRIDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.CODI)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.ESTATPROCEDIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.ESTATPROCEDIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.ESTATAUTORITZACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.ESTATAUTORITZACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.MISSATGE)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.MISSATGE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.CONSULTA)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.CONSULTA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.TITULARNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.TITULARNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.TITULARNIF)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.TITULARNIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.DATAAUTORITZACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.DATAAUTORITZACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.DATAENVIAMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.DATAENVIAMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoMadridFields.INTENTS)}">
        <th>${pad:getSortIcons(__theFilterForm,InfoMadridFields.INTENTS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

