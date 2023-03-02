<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentCedentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.TITOL)}">
        <tr id="documentCedent_titol_rowid">
          <td id="documentCedent_titol_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.TITOL])?'documentCedent.titol':__theForm.labels[DocumentCedentFields.TITOL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentCedentFields.TITOL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentCedentFields.TITOL]}" ></i>
              </c:if>
            </td>
          <td id="documentCedent_titol_columnvalueid">
            <form:errors path="documentCedent.titol" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.TITOL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.TITOL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="documentCedent.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.DESCRIPCIO)}">
        <tr id="documentCedent_descripcio_rowid">
          <td id="documentCedent_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.DESCRIPCIO])?'documentCedent.descripcio':__theForm.labels[DocumentCedentFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentCedentFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentCedentFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="documentCedent_descripcio_columnvalueid">
              <form:errors path="documentCedent.descripcio" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}"  path="documentCedent.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.ENTITATSERVEIID)}">
        <tr id="documentCedent_entitatServeiID_rowid">
          <td id="documentCedent_entitatServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.ENTITATSERVEIID])?'documentCedent.entitatServeiID':__theForm.labels[DocumentCedentFields.ENTITATSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentCedentFields.ENTITATSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentCedentFields.ENTITATSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="documentCedent_entitatServeiID_columnvalueid">
          <form:errors path="documentCedent.entitatServeiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.ENTITATSERVEIID)}" >
          <form:hidden path="documentCedent.entitatServeiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.documentCedent.entitatServeiID,__theForm.listOfEntitatServeiForEntitatServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.ENTITATSERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="documentCedent_entitatServeiID"  onchange="if(typeof onChangeEntitatServeiID == 'function') {  onChangeEntitatServeiID(this); };"  cssClass="form-control col-md-9-optional" path="documentCedent.entitatServeiID">
            <c:forEach items="${__theForm.listOfEntitatServeiForEntitatServeiID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.DATACREACIO)}">
        <tr id="documentCedent_dataCreacio_rowid">
          <td id="documentCedent_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.DATACREACIO])?'documentCedent.dataCreacio':__theForm.labels[DocumentCedentFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentCedentFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentCedentFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="documentCedent_dataCreacio_columnvalueid">
    <form:errors path="documentCedent.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="documentCedent_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#documentCedent_dataCreacio" path="documentCedent.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#documentCedent_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#documentCedent_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentCedentFields.FITXERID)}">
        <tr id="documentCedent_fitxerID_rowid">
          <td id="documentCedent_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentCedentFields.FITXERID])?'documentCedent.fitxerID':__theForm.labels[DocumentCedentFields.FITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentCedentFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentCedentFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="documentCedent_fitxerID_columnvalueid">
              <form:errors path="documentCedent.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentCedent.fitxer)}"/>">${__theForm.documentCedent.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentCedentFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.documentCedent.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentCedent.fitxer)}"/>">${__theForm.documentCedent.fitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
