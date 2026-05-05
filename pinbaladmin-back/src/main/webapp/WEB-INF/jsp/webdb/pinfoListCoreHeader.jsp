<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PinfoFields" className="org.fundaciobit.pinbaladmin.model.fields.PinfoFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.PINFOID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.PINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.INCIDENCIAID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.INCIDENCIAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.ENTITAT)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.ENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.SOLICITANTNIF)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.SOLICITANTNIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.SOLICITANTNOM)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.SOLICITANTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.ESTAT)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.FITXERID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.FITXERFIRMATID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.FITXERFIRMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.PORTAFIBID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.PORTAFIBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.DESTINATARINIF)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.DESTINATARINIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.DESTINATARINOM)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.DESTINATARINOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.MISSATGEPINBAL)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.MISSATGEPINBAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.LOGPPNBAL)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.LOGPPNBAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoFields.MISSATGESOLICITANT)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoFields.MISSATGESOLICITANT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

