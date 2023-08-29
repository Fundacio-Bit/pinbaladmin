<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitGDadesTitFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.DADESTITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.DADESTITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitGDadesTitFields.CARREC)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitGDadesTitFields.CARREC)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

