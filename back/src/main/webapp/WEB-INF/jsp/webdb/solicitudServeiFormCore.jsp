<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.SOLICITUDID)}">
        <tr id="solicitudServei_solicitudID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.SOLICITUDID])?'solicitudServei.solicitudID':__theForm.labels[SolicitudServeiFields.SOLICITUDID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.SOLICITUDID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.SOLICITUDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitudServei.solicitudID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SOLICITUDID)}" >
          <form:hidden path="solicitudServei.solicitudID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitudServei.solicitudID,__theForm.listOfSolicitudForSolicitudID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SOLICITUDID)}" >
          <form:select id="solicitudServei_solicitudID"  onchange="if(typeof onChangeSolicitudID == 'function') {  onChangeSolicitudID(this); };"  cssClass="input-xxlarge" path="solicitudServei.solicitudID">
            <c:forEach items="${__theForm.listOfSolicitudForSolicitudID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.SERVEIID)}">
        <tr id="solicitudServei_serveiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.SERVEIID])?'solicitudServei.serveiID':__theForm.labels[SolicitudServeiFields.SERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.SERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.SERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitudServei.serveiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SERVEIID)}" >
          <form:hidden path="solicitudServei.serveiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitudServei.serveiID,__theForm.listOfServeiForServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.SERVEIID)}" >
          <form:select id="solicitudServei_serveiID"  onchange="if(typeof onChangeServeiID == 'function') {  onChangeServeiID(this); };"  cssClass="input-xxlarge" path="solicitudServei.serveiID">
            <c:forEach items="${__theForm.listOfServeiForServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
        <tr id="solicitudServei_estatSolicitudServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ESTATSOLICITUDSERVEIID])?'solicitudServei.estatSolicitudServeiID':__theForm.labels[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="solicitudServei.estatSolicitudServeiID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}" >
          <form:hidden path="solicitudServei.estatSolicitudServeiID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.solicitudServei.estatSolicitudServeiID,__theForm.listOfEstatSolicitudServeiForEstatSolicitudServeiID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}" >
          <form:select id="solicitudServei_estatSolicitudServeiID"  onchange="if(typeof onChangeEstatSolicitudServeiID == 'function') {  onChangeEstatSolicitudServeiID(this); };"  cssClass="input-xxlarge" path="solicitudServei.estatSolicitudServeiID">
            <c:forEach items="${__theForm.listOfEstatSolicitudServeiForEstatSolicitudServeiID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.NORMALEGAL)}">
        <tr id="solicitudServei_normaLegal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.NORMALEGAL])?'solicitudServei.normaLegal':__theForm.labels[SolicitudServeiFields.NORMALEGAL]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.NORMALEGAL]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.NORMALEGAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitudServei.normaLegal" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.NORMALEGAL)? 'true' : 'false'}" path="solicitudServei.normaLegal"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.normaLegal'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
        <tr id="solicitudServei_enllazNormaLegal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ENLLAZNORMALEGAL])?'solicitudServei.enllazNormaLegal':__theForm.labels[SolicitudServeiFields.ENLLAZNORMALEGAL]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ENLLAZNORMALEGAL]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.ENLLAZNORMALEGAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)}">

             <c:if test="${ not empty __theForm.solicitudServei.enllazNormaLegal}">
               <a href="${__theForm.solicitudServei.enllazNormaLegal}" target="_blank">${__theForm.solicitudServei.enllazNormaLegal}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL))}">

            <form:errors path="solicitudServei.enllazNormaLegal" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZNORMALEGAL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitudServei.enllazNormaLegal"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ARTICLES)}">
        <tr id="solicitudServei_articles_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ARTICLES])?'solicitudServei.articles':__theForm.labels[SolicitudServeiFields.ARTICLES]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ARTICLES]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.ARTICLES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitudServei.articles" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ARTICLES)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ARTICLES)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitudServei.articles"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
        <tr id="solicitudServei_tipusConsentiment_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.TIPUSCONSENTIMENT])?'solicitudServei.tipusConsentiment':__theForm.labels[SolicitudServeiFields.TIPUSCONSENTIMENT]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.TIPUSCONSENTIMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.TIPUSCONSENTIMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitudServei.tipusConsentiment" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.TIPUSCONSENTIMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.TIPUSCONSENTIMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitudServei.tipusConsentiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.CONSENTIMENT)}">
        <tr id="solicitudServei_consentiment_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.CONSENTIMENT])?'solicitudServei.consentiment':__theForm.labels[SolicitudServeiFields.CONSENTIMENT]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.CONSENTIMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.CONSENTIMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="solicitudServei.consentiment" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CONSENTIMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.CONSENTIMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitudServei.consentiment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
        <tr id="solicitudServei_enllazConsentiment_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.ENLLAZCONSENTIMENT])?'solicitudServei.enllazConsentiment':__theForm.labels[SolicitudServeiFields.ENLLAZCONSENTIMENT]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.ENLLAZCONSENTIMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.ENLLAZCONSENTIMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">

             <c:if test="${ not empty __theForm.solicitudServei.enllazConsentiment}">
               <a href="${__theForm.solicitudServei.enllazConsentiment}" target="_blank">${__theForm.solicitudServei.enllazConsentiment}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT))}">

            <form:errors path="solicitudServei.enllazConsentiment" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.ENLLAZCONSENTIMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="solicitudServei.enllazConsentiment"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SolicitudServeiFields.NOTES)}">
        <tr id="solicitudServei_notes_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SolicitudServeiFields.NOTES])?'solicitudServei.notes':__theForm.labels[SolicitudServeiFields.NOTES]}" />
              <c:if test="${not empty __theForm.help[SolicitudServeiFields.NOTES]}">
              <i class="icon-info-sign" title="${__theForm.help[SolicitudServeiFields.NOTES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="solicitudServei.notes" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,SolicitudServeiFields.NOTES)? 'true' : 'false'}" path="solicitudServei.notes"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('solicitudServei.notes'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
