<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.TITOL)}">
        <tr id="documentEntitat_titol_rowid">
          <td id="documentEntitat_titol_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.TITOL])?'documentEntitat.titol':__theForm.labels[DocumentEntitatFields.TITOL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.TITOL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentEntitatFields.TITOL]}" ></i>
              </c:if>
            </td>
          <td id="documentEntitat_titol_columnvalueid">
            <form:errors path="documentEntitat.titol" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.TITOL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.TITOL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="documentEntitat.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.DESCRIPCIO)}">
        <tr id="documentEntitat_descripcio_rowid">
          <td id="documentEntitat_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.DESCRIPCIO])?'documentEntitat.descripcio':__theForm.labels[DocumentEntitatFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentEntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="documentEntitat_descripcio_columnvalueid">
              <form:errors path="documentEntitat.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="documentEntitat.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('documentEntitat.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.ENTITATID)}">
        <tr id="documentEntitat_entitatID_rowid">
          <td id="documentEntitat_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.ENTITATID])?'documentEntitat.entitatID':__theForm.labels[DocumentEntitatFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="documentEntitat_entitatID_columnvalueid">
          <form:errors path="documentEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.ENTITATID)}" >
          <form:hidden path="documentEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.documentEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="documentEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="documentEntitat.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.FITXERID)}">
        <tr id="documentEntitat_fitxerID_rowid">
          <td id="documentEntitat_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.FITXERID])?'documentEntitat.fitxerID':__theForm.labels[DocumentEntitatFields.FITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentEntitatFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="documentEntitat_fitxerID_columnvalueid">
              <form:errors path="documentEntitat.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentEntitat.fitxer)}"/>">${__theForm.documentEntitat.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.documentEntitat.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.documentEntitat.fitxer)}"/>">${__theForm.documentEntitat.fitxer.nom}</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentEntitatFields.DATAALTA)}">
        <tr id="documentEntitat_dataAlta_rowid">
          <td id="documentEntitat_dataAlta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentEntitatFields.DATAALTA])?'documentEntitat.dataAlta':__theForm.labels[DocumentEntitatFields.DATAALTA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentEntitatFields.DATAALTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentEntitatFields.DATAALTA]}" ></i>
              </c:if>
            </td>
          <td id="documentEntitat_dataAlta_columnvalueid">
    <form:errors path="documentEntitat.dataAlta" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="documentEntitat_dataAlta" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DATAALTA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#documentEntitat_dataAlta" path="documentEntitat.dataAlta" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentEntitatFields.DATAALTA)}" >
                    <div class="input-group-append"  data-target="#documentEntitat_dataAlta"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#documentEntitat_dataAlta').datetimepicker({
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
        
