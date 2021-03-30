<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentCedentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.TITOL)}">
        <tr id="documentCedent_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.TITOL])?'documentCedent.titol':__theForm.labels[DocumentCedentFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentCedentFields.TITOL]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentCedentFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="documentCedent.titol" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.TITOL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.TITOL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="documentCedent.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.DESCRIPCIO)}">
        <tr id="documentCedent_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.DESCRIPCIO])?'documentCedent.descripcio':__theForm.labels[DocumentCedentFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[DocumentCedentFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentCedentFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentCedent.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}" path="documentCedent.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.ENTITATSERVEIID)}">
        <tr id="documentCedent_entitatServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.ENTITATSERVEIID])?'documentCedent.entitatServeiID':__theForm.labels[DocumentCedentFields.ENTITATSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentCedentFields.ENTITATSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentCedentFields.ENTITATSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="documentCedent.entitatServeiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.ENTITATSERVEIID)}" >
          <form:hidden path="documentCedent.entitatServeiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.documentCedent.entitatServeiID,__theForm.listOfEntitatServeiForEntitatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.ENTITATSERVEIID)}" >
          <form:select id="documentCedent_entitatServeiID"  onchange="if(typeof onChangeEntitatServeiID == 'function') {  onChangeEntitatServeiID(this); };"  cssClass="input-xxlarge" path="documentCedent.entitatServeiID">
            <c:forEach items="${__theForm.listOfEntitatServeiForEntitatServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.DATACREACIO)}">
        <tr id="documentCedent_dataCreacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.DATACREACIO])?'documentCedent.dataCreacio':__theForm.labels[DocumentCedentFields.DATACREACIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentCedentFields.DATACREACIO]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentCedentFields.DATACREACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentCedent.dataCreacio" cssClass="errorField alert alert-error" />
              <div id="dataCreacio" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DATACREACIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DATACREACIO)? 'input-medium uneditable-input' : 'input-medium'}"  path="documentCedent.dataCreacio" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DATACREACIO)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCreacio').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.FITXERID)}">
        <tr id="documentCedent_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.FITXERID])?'documentCedent.fitxerID':__theForm.labels[DocumentCedentFields.FITXERID]}" />
              <c:if test="${not empty __theForm.help[DocumentCedentFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentCedentFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentCedent.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.documentCedent.fitxer}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentCedent.fitxer)}"/>">${__theForm.documentCedent.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
