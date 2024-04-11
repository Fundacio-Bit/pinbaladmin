<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitAPersAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.PERSAUTID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.PERSAUTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.DATATRAMIT)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.DATATRAMIT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.MAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.MAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.TELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.TELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.URLSISTRA)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.URLSISTRA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.IDSESIONFORMULARIO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.IDSESIONFORMULARIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitAPersAutFields.IDSESIONTRAMITE)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitAPersAutFields.IDSESIONTRAMITE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

