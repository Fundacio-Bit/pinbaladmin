<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IncidenciaTecnicaFields" className="org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.INCIDENCIATECNICAID)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.INCIDENCIATECNICAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.TITOL)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.TITOL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DESCRIPCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DATAINICI)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.DATAFI)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.ESTAT)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CREADOR)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CREADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.TIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.NOMENTITAT)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.NOMENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTENOM)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CONTACTENOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTEEMAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CONTACTEEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CONTACTETELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CONTACTETELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

