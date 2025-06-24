<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModificacioSoliServFields" className="org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.SOLISERVID)}">
        <tr id="modificacioSoliServ_soliServID_rowid">
          <td id="modificacioSoliServ_soliServID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.SOLISERVID])?'modificacioSoliServ.soliServID':__theForm.labels[ModificacioSoliServFields.SOLISERVID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.SOLISERVID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.SOLISERVID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_soliServID_columnvalueid">
          <form:errors path="modificacioSoliServ.soliServID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.SOLISERVID)}" >
          <form:hidden path="modificacioSoliServ.soliServID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSoliServ.soliServID,__theForm.listOfSolicitudServeiForSoliServID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.SOLISERVID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSoliServ_soliServID"  onchange="if(typeof onChangeSoliServID == 'function') {  onChangeSoliServID(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSoliServ.soliServID">
            <c:forEach items="${__theForm.listOfSolicitudServeiForSoliServID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.MODSOLIID)}">
        <tr id="modificacioSoliServ_modSoliID_rowid">
          <td id="modificacioSoliServ_modSoliID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.MODSOLIID])?'modificacioSoliServ.modSoliID':__theForm.labels[ModificacioSoliServFields.MODSOLIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.MODSOLIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.MODSOLIID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_modSoliID_columnvalueid">
          <form:errors path="modificacioSoliServ.modSoliID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.MODSOLIID)}" >
          <form:hidden path="modificacioSoliServ.modSoliID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modificacioSoliServ.modSoliID,__theForm.listOfModificacioSolicitudForModSoliID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.MODSOLIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="modificacioSoliServ_modSoliID"  onchange="if(typeof onChangeModSoliID == 'function') {  onChangeModSoliID(this); };"  cssClass="form-control col-md-9-optional" path="modificacioSoliServ.modSoliID">
            <c:forEach items="${__theForm.listOfModificacioSolicitudForModSoliID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.ESTAT)}">
        <tr id="modificacioSoliServ_estat_rowid">
          <td id="modificacioSoliServ_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.ESTAT])?'modificacioSoliServ.estat':__theForm.labels[ModificacioSoliServFields.ESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_estat_columnvalueid">
              <form:errors path="modificacioSoliServ.estat" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ESTAT)? 'true' : 'false'}" path="modificacioSoliServ.estat"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_estat" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_estat" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.estat'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.estat'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.estat'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_estat').on('click', function(){
					var valor = ($('#dropdownMenuContainer_estat').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_estat').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.NORMA1)}">
        <tr id="modificacioSoliServ_norma1_rowid">
          <td id="modificacioSoliServ_norma1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.NORMA1])?'modificacioSoliServ.norma1':__theForm.labels[ModificacioSoliServFields.NORMA1]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.NORMA1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.NORMA1]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_norma1_columnvalueid">
            <form:errors path="modificacioSoliServ.norma1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA1)? ' uneditable-input' : ''}"  style="" maxlength="240" path="modificacioSoliServ.norma1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.ARTICLES1)}">
        <tr id="modificacioSoliServ_articles1_rowid">
          <td id="modificacioSoliServ_articles1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.ARTICLES1])?'modificacioSoliServ.articles1':__theForm.labels[ModificacioSoliServFields.ARTICLES1]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.ARTICLES1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.ARTICLES1]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_articles1_columnvalueid">
              <form:errors path="modificacioSoliServ.articles1" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ARTICLES1)? 'true' : 'false'}" path="modificacioSoliServ.articles1"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_articles1" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_articles1" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.articles1'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.articles1'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('modificacioSoliServ.articles1'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_articles1').on('click', function(){
					var valor = ($('#dropdownMenuContainer_articles1').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_articles1').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA1ID)}">
        <tr id="modificacioSoliServ_fitxerNorma1ID_rowid">
          <td id="modificacioSoliServ_fitxerNorma1ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.FITXERNORMA1ID])?'modificacioSoliServ.fitxerNorma1ID':__theForm.labels[ModificacioSoliServFields.FITXERNORMA1ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.FITXERNORMA1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.FITXERNORMA1ID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_fitxerNorma1ID_columnvalueid">
              <form:errors path="modificacioSoliServ.fitxerNorma1ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA1ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma1)}"/>">${__theForm.modificacioSoliServ.fitxerNorma1.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA1ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA1ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA1ID)? ' uneditable-input' : ''}"   path="fitxerNorma1ID" type="file" />
                  <label class="custom-file-label" for="fitxerNorma1ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.modificacioSoliServ.fitxerNorma1}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma1)}"/>">${__theForm.modificacioSoliServ.fitxerNorma1.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerNorma1IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerNorma1ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerNorma1ID').on('change', function(){
						var ruta = $('#fitxerNorma1ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerNorma1ID-custom-file-label').css('display','block');
						$('#fitxerNorma1ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.NORMA2)}">
        <tr id="modificacioSoliServ_norma2_rowid">
          <td id="modificacioSoliServ_norma2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.NORMA2])?'modificacioSoliServ.norma2':__theForm.labels[ModificacioSoliServFields.NORMA2]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.NORMA2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.NORMA2]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_norma2_columnvalueid">
            <form:errors path="modificacioSoliServ.norma2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA2)? ' uneditable-input' : ''}"  style="" maxlength="240" path="modificacioSoliServ.norma2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.ARTICLES2)}">
        <tr id="modificacioSoliServ_articles2_rowid">
          <td id="modificacioSoliServ_articles2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.ARTICLES2])?'modificacioSoliServ.articles2':__theForm.labels[ModificacioSoliServFields.ARTICLES2]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.ARTICLES2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.ARTICLES2]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_articles2_columnvalueid">
            <form:errors path="modificacioSoliServ.articles2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ARTICLES2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ARTICLES2)? ' uneditable-input' : ''}"  style="" maxlength="60" path="modificacioSoliServ.articles2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA2ID)}">
        <tr id="modificacioSoliServ_fitxerNorma2ID_rowid">
          <td id="modificacioSoliServ_fitxerNorma2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.FITXERNORMA2ID])?'modificacioSoliServ.fitxerNorma2ID':__theForm.labels[ModificacioSoliServFields.FITXERNORMA2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.FITXERNORMA2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.FITXERNORMA2ID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_fitxerNorma2ID_columnvalueid">
              <form:errors path="modificacioSoliServ.fitxerNorma2ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA2ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma2)}"/>">${__theForm.modificacioSoliServ.fitxerNorma2.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA2ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA2ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA2ID)? ' uneditable-input' : ''}"   path="fitxerNorma2ID" type="file" />
                  <label class="custom-file-label" for="fitxerNorma2ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.modificacioSoliServ.fitxerNorma2}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma2)}"/>">${__theForm.modificacioSoliServ.fitxerNorma2.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerNorma2IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerNorma2ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerNorma2ID').on('change', function(){
						var ruta = $('#fitxerNorma2ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerNorma2ID-custom-file-label').css('display','block');
						$('#fitxerNorma2ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.NORMA3)}">
        <tr id="modificacioSoliServ_norma3_rowid">
          <td id="modificacioSoliServ_norma3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.NORMA3])?'modificacioSoliServ.norma3':__theForm.labels[ModificacioSoliServFields.NORMA3]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.NORMA3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.NORMA3]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_norma3_columnvalueid">
            <form:errors path="modificacioSoliServ.norma3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA3)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.NORMA3)? ' uneditable-input' : ''}"  style="" maxlength="240" path="modificacioSoliServ.norma3"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.ARTICLES3)}">
        <tr id="modificacioSoliServ_articles3_rowid">
          <td id="modificacioSoliServ_articles3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.ARTICLES3])?'modificacioSoliServ.articles3':__theForm.labels[ModificacioSoliServFields.ARTICLES3]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.ARTICLES3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.ARTICLES3]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_articles3_columnvalueid">
            <form:errors path="modificacioSoliServ.articles3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ARTICLES3)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.ARTICLES3)? ' uneditable-input' : ''}"  style="" maxlength="60" path="modificacioSoliServ.articles3"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModificacioSoliServFields.FITXERNORMA3ID)}">
        <tr id="modificacioSoliServ_fitxerNorma3ID_rowid">
          <td id="modificacioSoliServ_fitxerNorma3ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ModificacioSoliServFields.FITXERNORMA3ID])?'modificacioSoliServ.fitxerNorma3ID':__theForm.labels[ModificacioSoliServFields.FITXERNORMA3ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ModificacioSoliServFields.FITXERNORMA3ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModificacioSoliServFields.FITXERNORMA3ID]}" ></i>
              </c:if>
            </td>
          <td id="modificacioSoliServ_fitxerNorma3ID_columnvalueid">
              <form:errors path="modificacioSoliServ.fitxerNorma3ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA3ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma3)}"/>">${__theForm.modificacioSoliServ.fitxerNorma3.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA3ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA3ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ModificacioSoliServFields.FITXERNORMA3ID)? ' uneditable-input' : ''}"   path="fitxerNorma3ID" type="file" />
                  <label class="custom-file-label" for="fitxerNorma3ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.modificacioSoliServ.fitxerNorma3}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.modificacioSoliServ.fitxerNorma3)}"/>">${__theForm.modificacioSoliServ.fitxerNorma3.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerNorma3IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerNorma3ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerNorma3ID').on('change', function(){
						var ruta = $('#fitxerNorma3ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerNorma3ID-custom-file-label').css('display','block');
						$('#fitxerNorma3ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
