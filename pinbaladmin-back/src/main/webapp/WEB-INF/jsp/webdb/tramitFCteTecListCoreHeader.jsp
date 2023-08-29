<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitFCteTecFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.CTETECID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.CTETECID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.CARREC)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.CARREC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.TELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.TELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitFCteTecFields.MAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitFCteTecFields.MAIL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

