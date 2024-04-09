<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitHProcFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.PROCID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.PROCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.CODI)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.TIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.URLSEU)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.URLSEU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.CADUCITAT)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.CADUCITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.CADUCITATDATA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.CADUCITATDATA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.PETICIONSALDIA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.PETICIONSALDIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.PETICIONSALMES)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.PETICIONSALMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.PERIODICO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.PERIODICO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitHProcFields.AUTOMATIZADO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitHProcFields.AUTOMATIZADO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

