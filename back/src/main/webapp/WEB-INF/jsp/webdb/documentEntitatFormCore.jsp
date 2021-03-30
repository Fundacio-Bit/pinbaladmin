<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.TITOL)}">
        <tr id="documentEntitat_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.TITOL])?'documentEntitat.titol':__theForm.labels[DocumentEntitatFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.TITOL]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentEntitatFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="documentEntitat.titol" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.TITOL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.TITOL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="documentEntitat.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.DESCRIPCIO)}">
        <tr id="documentEntitat_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.DESCRIPCIO])?'documentEntitat.descripcio':__theForm.labels[DocumentEntitatFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentEntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentEntitat.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="documentEntitat.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.ENTITATID)}">
        <tr id="documentEntitat_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.ENTITATID])?'documentEntitat.entitatID':__theForm.labels[DocumentEntitatFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentEntitatFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="documentEntitat.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.ENTITATID)}" >
          <form:hidden path="documentEntitat.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.documentEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.ENTITATID)}" >
          <form:select id="documentEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="documentEntitat.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.FITXERID)}">
        <tr id="documentEntitat_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.FITXERID])?'documentEntitat.fitxerID':__theForm.labels[DocumentEntitatFields.FITXERID]}" />
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentEntitatFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentEntitat.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.documentEntitat.fitxer}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentEntitat.fitxer)}"/>">${__theForm.documentEntitat.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.DATAALTA)}">
        <tr id="documentEntitat_dataAlta_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.DATAALTA])?'documentEntitat.dataAlta':__theForm.labels[DocumentEntitatFields.DATAALTA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.DATAALTA]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentEntitatFields.DATAALTA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="documentEntitat.dataAlta" cssClass="errorField alert alert-error" />
              <div id="dataAlta" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DATAALTA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DATAALTA)? 'input-medium uneditable-input' : 'input-medium'}"  path="documentEntitat.dataAlta" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DATAALTA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataAlta').datetimepicker({
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
        
