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
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TramitIServFields.NOM)}" >
          <form:hidden path="tramitIServ.nom"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tramitIServ.nom,__theForm.listOfValuesForNom)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitIServFields.NOM)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tramitIServ_nom"  onchange="if(typeof onChangeNom == 'function') {  onChangeNom(this); };"  cssClass="form-control col-md-9-optional" path="tramitIServ.nom">
            <c:forEach items="${__theForm.listOfValuesForNom}" var="tmp">
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
        
