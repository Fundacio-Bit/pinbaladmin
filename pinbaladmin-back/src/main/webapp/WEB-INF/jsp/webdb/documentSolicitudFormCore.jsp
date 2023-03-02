<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentSolicitudFields.DOCUMENTID)}">
        <tr id="documentSolicitud_documentID_rowid">
          <td id="documentSolicitud_documentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentSolicitudFields.DOCUMENTID])?'documentSolicitud.documentID':__theForm.labels[DocumentSolicitudFields.DOCUMENTID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentSolicitudFields.DOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentSolicitudFields.DOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="documentSolicitud_documentID_columnvalueid">
          <form:errors path="documentSolicitud.documentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.DOCUMENTID)}" >
          <form:hidden path="documentSolicitud.documentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.documentSolicitud.documentID,__theForm.listOfDocumentForDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.DOCUMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="documentSolicitud_documentID"  onchange="if(typeof onChangeDocumentID == 'function') {  onChangeDocumentID(this); };"  cssClass="form-control col-md-9-optional" path="documentSolicitud.documentID">
            <c:forEach items="${__theForm.listOfDocumentForDocumentID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentSolicitudFields.SOLICITUDID)}">
        <tr id="documentSolicitud_solicitudID_rowid">
          <td id="documentSolicitud_solicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentSolicitudFields.SOLICITUDID])?'documentSolicitud.solicitudID':__theForm.labels[DocumentSolicitudFields.SOLICITUDID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentSolicitudFields.SOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentSolicitudFields.SOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="documentSolicitud_solicitudID_columnvalueid">
          <form:errors path="documentSolicitud.solicitudID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.SOLICITUDID)}" >
          <form:hidden path="documentSolicitud.solicitudID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.documentSolicitud.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentSolicitudFields.SOLICITUDID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="documentSolicitud_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="form-control col-md-9-optional" path="documentSolicitud.solicitudID">
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
