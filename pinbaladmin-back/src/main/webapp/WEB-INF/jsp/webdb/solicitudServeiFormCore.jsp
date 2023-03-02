<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.SOLICITUDID)}">
        <tr id="solicitudServei_solicitudID_rowid">
          <td id="solicitudServei_solicitudID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.SOLICITUDID])?'solicitudServei.solicitudID':__theForm.labels[SolicitudServeiFields.SOLICITUDID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.SOLICITUDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.SOLICITUDID]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_solicitudID_columnvalueid">
          <form:errors path="solicitudServei.solicitudID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SOLICITUDID)}" >
          <form:hidden path="solicitudServei.solicitudID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitudServei.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SOLICITUDID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitudServei_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="form-control col-md-9-optional" path="solicitudServei.solicitudID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.SERVEIID)}">
        <tr id="solicitudServei_serveiID_rowid">
          <td id="solicitudServei_serveiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.SERVEIID])?'solicitudServei.serveiID':__theForm.labels[SolicitudServeiFields.SERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.SERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.SERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_serveiID_columnvalueid">
          <form:errors path="solicitudServei.serveiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SERVEIID)}" >
          <form:hidden path="solicitudServei.serveiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitudServei.serveiID,__theForm.listOfServeiForServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitudServei_serveiID"  onchange="if(typeof onChangeServeiID == 'function') {  onChangeServeiID(this); };"  cssClass="form-control col-md-9-optional" path="solicitudServei.serveiID">
            <c:forEach items="${__theForm.listOfServeiForServeiID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
        <tr id="solicitudServei_estatSolicitudServeiID_rowid">
          <td id="solicitudServei_estatSolicitudServeiID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ESTATSOLICITUDSERVEIID])?'solicitudServei.estatSolicitudServeiID':__theForm.labels[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_estatSolicitudServeiID_columnvalueid">
          <form:errors path="solicitudServei.estatSolicitudServeiID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}" >
          <form:hidden path="solicitudServei.estatSolicitudServeiID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.solicitudServei.estatSolicitudServeiID,__theForm.listOfEstatSolicitudServeiForEstatSolicitudServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="solicitudServei_estatSolicitudServeiID"  onchange="if(typeof onChangeEstatSolicitudServeiID == 'function') {  onChangeEstatSolicitudServeiID(this); };"  cssClass="form-control col-md-9-optional" path="solicitudServei.estatSolicitudServeiID">
            <c:forEach items="${__theForm.listOfEstatSolicitudServeiForEstatSolicitudServeiID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.NORMALEGAL)}">
        <tr id="solicitudServei_normaLegal_rowid">
          <td id="solicitudServei_normaLegal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.NORMALEGAL])?'solicitudServei.normaLegal':__theForm.labels[SolicitudServeiFields.NORMALEGAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.NORMALEGAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.NORMALEGAL]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_normaLegal_columnvalueid">
              <form:errors path="solicitudServei.normaLegal" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.NORMALEGAL)? 'true' : 'false'}" path="solicitudServei.normaLegal"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_normaLegal" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_normaLegal" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_normaLegal').on('click', function(){
					var valor = ($('#dropdownMenuContainer_normaLegal').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_normaLegal').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
        <tr id="solicitudServei_enllazNormaLegal_rowid">
          <td id="solicitudServei_enllazNormaLegal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ENLLAZNORMALEGAL])?'solicitudServei.enllazNormaLegal':__theForm.labels[SolicitudServeiFields.ENLLAZNORMALEGAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ENLLAZNORMALEGAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.ENLLAZNORMALEGAL]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_enllazNormaLegal_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)}">

             <c:if test="${ not empty __theForm.solicitudServei.enllazNormaLegal}">
               <a href="${__theForm.solicitudServei.enllazNormaLegal}" target="_blank">${__theForm.solicitudServei.enllazNormaLegal}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL))}">

            <form:errors path="solicitudServei.enllazNormaLegal" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.enllazNormaLegal"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ARTICLES)}">
        <tr id="solicitudServei_articles_rowid">
          <td id="solicitudServei_articles_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ARTICLES])?'solicitudServei.articles':__theForm.labels[SolicitudServeiFields.ARTICLES]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ARTICLES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.ARTICLES]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_articles_columnvalueid">
            <form:errors path="solicitudServei.articles" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ARTICLES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ARTICLES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.articles"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
        <tr id="solicitudServei_tipusConsentiment_rowid">
          <td id="solicitudServei_tipusConsentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.TIPUSCONSENTIMENT])?'solicitudServei.tipusConsentiment':__theForm.labels[SolicitudServeiFields.TIPUSCONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.TIPUSCONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.TIPUSCONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_tipusConsentiment_columnvalueid">
            <form:errors path="solicitudServei.tipusConsentiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.TIPUSCONSENTIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.TIPUSCONSENTIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.tipusConsentiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.CONSENTIMENT)}">
        <tr id="solicitudServei_consentiment_rowid">
          <td id="solicitudServei_consentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.CONSENTIMENT])?'solicitudServei.consentiment':__theForm.labels[SolicitudServeiFields.CONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.CONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.CONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_consentiment_columnvalueid">
            <form:errors path="solicitudServei.consentiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CONSENTIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CONSENTIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.consentiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
        <tr id="solicitudServei_enllazConsentiment_rowid">
          <td id="solicitudServei_enllazConsentiment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ENLLAZCONSENTIMENT])?'solicitudServei.enllazConsentiment':__theForm.labels[SolicitudServeiFields.ENLLAZCONSENTIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ENLLAZCONSENTIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.ENLLAZCONSENTIMENT]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_enllazConsentiment_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">

             <c:if test="${ not empty __theForm.solicitudServei.enllazConsentiment}">
               <a href="${__theForm.solicitudServei.enllazConsentiment}" target="_blank">${__theForm.solicitudServei.enllazConsentiment}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT))}">

            <form:errors path="solicitudServei.enllazConsentiment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.enllazConsentiment"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.NOTES)}">
        <tr id="solicitudServei_notes_rowid">
          <td id="solicitudServei_notes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.NOTES])?'solicitudServei.notes':__theForm.labels[SolicitudServeiFields.NOTES]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.NOTES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.NOTES]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_notes_columnvalueid">
              <form:errors path="solicitudServei.notes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.NOTES)? 'true' : 'false'}" path="solicitudServei.notes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_notes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_notes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.CADUCA)}">
        <tr id="solicitudServei_caduca_rowid">
          <td id="solicitudServei_caduca_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.CADUCA])?'solicitudServei.caduca':__theForm.labels[SolicitudServeiFields.CADUCA]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.CADUCA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.CADUCA]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_caduca_columnvalueid">
            <form:errors path="solicitudServei.caduca" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CADUCA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CADUCA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.caduca"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.FECHACADUCA)}">
        <tr id="solicitudServei_fechaCaduca_rowid">
          <td id="solicitudServei_fechaCaduca_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.FECHACADUCA])?'solicitudServei.fechaCaduca':__theForm.labels[SolicitudServeiFields.FECHACADUCA]}" />
             </label>
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.FECHACADUCA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SolicitudServeiFields.FECHACADUCA]}" ></i>
              </c:if>
            </td>
          <td id="solicitudServei_fechaCaduca_columnvalueid">
            <form:errors path="solicitudServei.fechaCaduca" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.FECHACADUCA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.FECHACADUCA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="solicitudServei.fechaCaduca"   />

           </td>
        </tr>
        </c:if>
        
