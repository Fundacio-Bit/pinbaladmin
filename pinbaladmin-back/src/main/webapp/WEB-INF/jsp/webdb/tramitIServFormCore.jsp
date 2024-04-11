<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitIServFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitIServFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.TRAMITID)}">
        <tr id="tramitIServ_tramitid_rowid">
          <td id="tramitIServ_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.TRAMITID])?'tramitIServ.tramitid':__theForm.labels[TramitIServFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_tramitid_columnvalueid">
          <form:errors path="tramitIServ.tramitid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitIServFields.TRAMITID)}" >
          <form:hidden path="tramitIServ.tramitid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitIServ.tramitid,__theForm.listOfTramitAPersAutForTramitid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitIServFields.TRAMITID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitIServ_tramitid"  onchange="if(typeof onChangeTramitid == 'function') {  onChangeTramitid(this); };"  cssClass="form-control col-md-9-optional" path="tramitIServ.tramitid">
            <c:forEach items="${__theForm.listOfTramitAPersAutForTramitid}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.NOM)}">
        <tr id="tramitIServ_nom_rowid">
          <td id="tramitIServ_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.NOM])?'tramitIServ.nom':__theForm.labels[TramitIServFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_nom_columnvalueid">
            <form:errors path="tramitIServ.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitIServ.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.CODI)}">
        <tr id="tramitIServ_codi_rowid">
          <td id="tramitIServ_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.CODI])?'tramitIServ.codi':__theForm.labels[TramitIServFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_codi_columnvalueid">
            <form:errors path="tramitIServ.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.CODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitIServ.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.NORMA)}">
        <tr id="tramitIServ_norma_rowid">
          <td id="tramitIServ_norma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.NORMA])?'tramitIServ.norma':__theForm.labels[TramitIServFields.NORMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.NORMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.NORMA]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_norma_columnvalueid">
            <form:errors path="tramitIServ.norma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.NORMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.NORMA)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitIServ.norma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.URLNORMA)}">
        <tr id="tramitIServ_urlnorma_rowid">
          <td id="tramitIServ_urlnorma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.URLNORMA])?'tramitIServ.urlnorma':__theForm.labels[TramitIServFields.URLNORMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.URLNORMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.URLNORMA]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_urlnorma_columnvalueid">
            <form:errors path="tramitIServ.urlnorma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.URLNORMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.URLNORMA)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitIServ.urlnorma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.ARTICLES)}">
        <tr id="tramitIServ_articles_rowid">
          <td id="tramitIServ_articles_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.ARTICLES])?'tramitIServ.articles':__theForm.labels[TramitIServFields.ARTICLES]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.ARTICLES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.ARTICLES]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_articles_columnvalueid">
            <form:errors path="tramitIServ.articles" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.ARTICLES)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.ARTICLES)? ' uneditable-input' : ''}"  style="" maxlength="60" path="tramitIServ.articles"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.NORMA2)}">
        <tr id="tramitIServ_norma2_rowid">
          <td id="tramitIServ_norma2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.NORMA2])?'tramitIServ.norma2':__theForm.labels[TramitIServFields.NORMA2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.NORMA2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.NORMA2]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_norma2_columnvalueid">
            <form:errors path="tramitIServ.norma2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.NORMA2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.NORMA2)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitIServ.norma2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.URLNORMA2)}">
        <tr id="tramitIServ_urlnorma2_rowid">
          <td id="tramitIServ_urlnorma2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.URLNORMA2])?'tramitIServ.urlnorma2':__theForm.labels[TramitIServFields.URLNORMA2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.URLNORMA2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.URLNORMA2]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_urlnorma2_columnvalueid">
            <form:errors path="tramitIServ.urlnorma2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.URLNORMA2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.URLNORMA2)? ' uneditable-input' : ''}"  style="" maxlength="240" path="tramitIServ.urlnorma2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.ARTICLES2)}">
        <tr id="tramitIServ_articles2_rowid">
          <td id="tramitIServ_articles2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.ARTICLES2])?'tramitIServ.articles2':__theForm.labels[TramitIServFields.ARTICLES2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.ARTICLES2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.ARTICLES2]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_articles2_columnvalueid">
            <form:errors path="tramitIServ.articles2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.ARTICLES2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.ARTICLES2)? ' uneditable-input' : ''}"  style="" maxlength="60" path="tramitIServ.articles2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.FITXERNORMAID)}">
        <tr id="tramitIServ_fitxernormaID_rowid">
          <td id="tramitIServ_fitxernormaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.FITXERNORMAID])?'tramitIServ.fitxernormaID':__theForm.labels[TramitIServFields.FITXERNORMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.FITXERNORMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.FITXERNORMAID]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_fitxernormaID_columnvalueid">
              <form:errors path="tramitIServ.fitxernormaID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMAID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitIServ.fitxernorma)}"/>">${__theForm.tramitIServ.fitxernorma.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMAID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMAID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMAID)? ' uneditable-input' : ''}"   path="fitxernormaID" type="file" />
                  <label class="custom-file-label" for="fitxernormaID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.tramitIServ.fitxernorma}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitIServ.fitxernorma)}"/>">${__theForm.tramitIServ.fitxernorma.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxernormaID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxernormaID').on('change', function(){
						var ruta = $('#fitxernormaID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxernormaID-custom-file-label').css('display','block');
						$('#fitxernormaID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitIServFields.FITXERNORMA2ID)}">
        <tr id="tramitIServ_fitxernorma2ID_rowid">
          <td id="tramitIServ_fitxernorma2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitIServFields.FITXERNORMA2ID])?'tramitIServ.fitxernorma2ID':__theForm.labels[TramitIServFields.FITXERNORMA2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitIServFields.FITXERNORMA2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitIServFields.FITXERNORMA2ID]}" ></i>
              </c:if>
            </td>
          <td id="tramitIServ_fitxernorma2ID_columnvalueid">
              <form:errors path="tramitIServ.fitxernorma2ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMA2ID)}" >
              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitIServ.fitxernorma2)}"/>">${__theForm.tramitIServ.fitxernorma2.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMA2ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMA2ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,TramitIServFields.FITXERNORMA2ID)? ' uneditable-input' : ''}"   path="fitxernorma2ID" type="file" />
                  <label class="custom-file-label" for="fitxernorma2ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.tramitIServ.fitxernorma2}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.tramitIServ.fitxernorma2)}"/>">${__theForm.tramitIServ.fitxernorma2.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxernorma2IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxernorma2ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxernorma2ID').on('change', function(){
						var ruta = $('#fitxernorma2ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxernorma2ID-custom-file-label').css('display','block');
						$('#fitxernorma2ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
