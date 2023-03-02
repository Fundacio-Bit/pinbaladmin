<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.ServeiFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.SERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.SERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.CODI)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.FORMULARIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.FORMULARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.ENTITATSERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.ENTITATSERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.ESTATSERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.ESTATSERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.TIPUSCONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.TIPUSCONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ServeiFields.OCULT)}">
        <th>${pad:getSortIcons(__theFilterForm,ServeiFields.OCULT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

