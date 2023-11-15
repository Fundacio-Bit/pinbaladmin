<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.NOM)}">
        <tr id="document_nom_rowid">
          <td id="document_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.NOM])?'document.nom':__theForm.labels[DocumentFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="document_nom_columnvalueid">
              <form:errors path="document.nom" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.NOM)? 'true' : 'false'}" path="document.nom"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_nom" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_nom" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('document.nom'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_nom').on('click', function(){
					var valor = ($('#dropdownMenuContainer_nom').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_nom').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.FITXERORIGINALID)}">
        <tr id="document_fitxerOriginalID_rowid">
          <td id="document_fitxerOriginalID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.FITXERORIGINALID])?'document.fitxerOriginalID':__theForm.labels[DocumentFields.FITXERORIGINALID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentFields.FITXERORIGINALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentFields.FITXERORIGINALID]}" ></i>
              </c:if>
            </td>
          <td id="document_fitxerOriginalID_columnvalueid">
              <form:errors path="document.fitxerOriginalID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerOriginal)}"/>">${__theForm.document.fitxerOriginal.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERORIGINALID)? ' uneditable-input' : ''}"   path="fitxerOriginalID" type="file" />
                  <label class="custom-file-label" for="fitxerOriginalID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.document.fitxerOriginal}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerOriginal)}"/>">${__theForm.document.fitxerOriginal.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerOriginalID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerOriginalID').on('change', function(){
						var ruta = $('#fitxerOriginalID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerOriginalID-custom-file-label').css('display','block');
						$('#fitxerOriginalID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.FITXERFIRMATID)}">
        <tr id="document_fitxerFirmatID_rowid">
          <td id="document_fitxerFirmatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.FITXERFIRMATID])?'document.fitxerFirmatID':__theForm.labels[DocumentFields.FITXERFIRMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentFields.FITXERFIRMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentFields.FITXERFIRMATID]}" ></i>
              </c:if>
            </td>
          <td id="document_fitxerFirmatID_columnvalueid">
              <form:errors path="document.fitxerFirmatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerFirmat)}"/>">${__theForm.document.fitxerFirmat.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,DocumentFields.FITXERFIRMATID)? ' uneditable-input' : ''}"   path="fitxerFirmatID" type="file" />
                  <label class="custom-file-label" for="fitxerFirmatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.document.fitxerFirmat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.document.fitxerFirmat)}"/>">${__theForm.document.fitxerFirmat.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerFirmatIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerFirmatID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerFirmatID').on('change', function(){
						var ruta = $('#fitxerFirmatID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerFirmatID-custom-file-label').css('display','block');
						$('#fitxerFirmatID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.NOTES)}">
        <tr id="document_notes_rowid">
          <td id="document_notes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.NOTES])?'document.notes':__theForm.labels[DocumentFields.NOTES]}" />
             </label>
              <c:if test="${not empty __theForm.help[DocumentFields.NOTES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentFields.NOTES]}" ></i>
              </c:if>
            </td>
          <td id="document_notes_columnvalueid">
              <form:errors path="document.notes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,DocumentFields.NOTES)? 'true' : 'false'}" path="document.notes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_notes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_notes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('document.notes'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_notes').on('click', function(){
					var valor = ($('#dropdownMenuContainer_notes').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_notes').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,DocumentFields.TIPUS)}">
        <tr id="document_tipus_rowid">
          <td id="document_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[DocumentFields.TIPUS])?'document.tipus':__theForm.labels[DocumentFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[DocumentFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[DocumentFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="document_tipus_columnvalueid">
          <form:errors path="document.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,DocumentFields.TIPUS)}" >
          <form:hidden path="document.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.document.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,DocumentFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="document_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="document.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
