<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.NOM)}">
        <tr id="document_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.NOM])?'document.nom':__theForm.labels[DocumentFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="document.nom" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.NOM)? 'true' : 'false'}" path="document.nom"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.FITXERORIGINALID)}">
        <tr id="document_fitxerOriginalID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.FITXERORIGINALID])?'document.fitxerOriginalID':__theForm.labels[DocumentFields.FITXERORIGINALID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[DocumentFields.FITXERORIGINALID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentFields.FITXERORIGINALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="document.fitxerOriginalID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)? 'input uneditable-input' : 'input'}"  path="fitxerOriginalID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.document.fitxerOriginal}">
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerOriginal)}"/>">${__theForm.document.fitxerOriginal.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.FITXERFIRMATID)}">
        <tr id="document_fitxerFirmatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.FITXERFIRMATID])?'document.fitxerFirmatID':__theForm.labels[DocumentFields.FITXERFIRMATID]}" />
              <c:if test="${not empty __theForm.help[DocumentFields.FITXERFIRMATID]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentFields.FITXERFIRMATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="document.fitxerFirmatID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)? 'input uneditable-input' : 'input'}"  path="fitxerFirmatID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.document.fitxerFirmat}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerFirmatIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerFirmat)}"/>">${__theForm.document.fitxerFirmat.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.NOTES)}">
        <tr id="document_notes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.NOTES])?'document.notes':__theForm.labels[DocumentFields.NOTES]}" />
              <c:if test="${not empty __theForm.help[DocumentFields.NOTES]}">
              <i class="icon-info-sign" title="${__theForm.help[DocumentFields.NOTES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="document.notes" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.NOTES)? 'true' : 'false'}" path="document.notes"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
