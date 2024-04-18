<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitudServei.id]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ID)}">
          <td>
          ${solicitudServei.id}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SOLICITUDID)}">
          <td>
          <c:set var="tmp">${solicitudServei.solicitudID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfSolicitudForSolicitudID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SERVEIID)}">
          <td>
          <c:set var="tmp">${solicitudServei.serveiID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfServeiForServeiID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
          <td>
          <c:set var="tmp">${solicitudServei.estatSolicitudServeiID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstatSolicitudServeiID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
          <td>
                       <c:if test="${ not empty solicitudServei.enllazNormaLegal}">
               <a href="${solicitudServei.enllazNormaLegal}" target="_blank">${solicitudServei.enllazNormaLegal}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
          <td>
          <c:set var="tmp">${solicitudServei.tipusConsentiment}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusConsentiment[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.CONSENTIMENT)}">
          <td>
          <c:set var="tmp">${solicitudServei.consentiment}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForConsentiment[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
          <td>
                       <c:if test="${ not empty solicitudServei.enllazConsentiment}">
               <a href="${solicitudServei.enllazConsentiment}" target="_blank">${solicitudServei.enllazConsentiment}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NOTES)}">
          <td>
          ${solicitudServei.notes}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.CADUCA)}">
          <td>
          ${solicitudServei.caduca}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.FECHACADUCA)}">
          <td>
          ${solicitudServei.fechaCaduca}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NORMALEGAL)}">
          <td>
          ${solicitudServei.normaLegal}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.FITXERNORMAID)}">
          <td>
            <c:if test="${not empty solicitudServei.fitxernorma}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(solicitudServei.fitxernorma)}"/>">${solicitudServei.fitxernorma.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ARTICLES)}">
          <td>
          ${solicitudServei.articles}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NORMA2)}">
          <td>
          ${solicitudServei.norma2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.FITXERNORMA2ID)}">
          <td>
            <c:if test="${not empty solicitudServei.fitxernorma2}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(solicitudServei.fitxernorma2)}"/>">${solicitudServei.fitxernorma2.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ARTICLES2)}">
          <td>
          ${solicitudServei.articles2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NORMA3)}">
          <td>
          ${solicitudServei.norma3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.FITXERNORMA3ID)}">
          <td>
            <c:if test="${not empty solicitudServei.fitxernorma3}">
              <a target="_blank" href="<c:url value="${pad:fileUrl(solicitudServei.fitxernorma3)}"/>">${solicitudServei.fitxernorma3.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ARTICLES3)}">
          <td>
          ${solicitudServei.articles3}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[solicitudServei.id]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


