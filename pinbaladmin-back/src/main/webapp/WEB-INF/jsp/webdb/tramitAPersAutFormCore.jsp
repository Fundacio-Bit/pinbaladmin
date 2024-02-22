<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitAPersAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.TRAMITID)}">
        <tr id="tramitAPersAut_tramitid_rowid">
          <td id="tramitAPersAut_tramitid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.TRAMITID])?'tramitAPersAut.tramitid':__theForm.labels[TramitAPersAutFields.TRAMITID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.TRAMITID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.TRAMITID]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_tramitid_columnvalueid">
            <form:errors path="tramitAPersAut.tramitid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.TRAMITID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.TRAMITID)? ' uneditable-input' : ''}"  style=""  path="tramitAPersAut.tramitid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.DATATRAMIT)}">
        <tr id="tramitAPersAut_datatramit_rowid">
          <td id="tramitAPersAut_datatramit_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.DATATRAMIT])?'tramitAPersAut.datatramit':__theForm.labels[TramitAPersAutFields.DATATRAMIT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.DATATRAMIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.DATATRAMIT]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_datatramit_columnvalueid">
    <form:errors path="tramitAPersAut.datatramit" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="tramitAPersAut_datatramit" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.DATATRAMIT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#tramitAPersAut_datatramit" path="tramitAPersAut.datatramit" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.DATATRAMIT)}" >
                    <div class="input-group-append"  data-target="#tramitAPersAut_datatramit"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#tramitAPersAut_datatramit').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.NIF)}">
        <tr id="tramitAPersAut_nif_rowid">
          <td id="tramitAPersAut_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.NIF])?'tramitAPersAut.nif':__theForm.labels[TramitAPersAutFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_nif_columnvalueid">
            <form:errors path="tramitAPersAut.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.NIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="tramitAPersAut.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.MAIL)}">
        <tr id="tramitAPersAut_mail_rowid">
          <td id="tramitAPersAut_mail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.MAIL])?'tramitAPersAut.mail':__theForm.labels[TramitAPersAutFields.MAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.MAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.MAIL]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_mail_columnvalueid">
            <form:errors path="tramitAPersAut.mail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.MAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.MAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitAPersAut.mail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.TELEFON)}">
        <tr id="tramitAPersAut_telefon_rowid">
          <td id="tramitAPersAut_telefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.TELEFON])?'tramitAPersAut.telefon':__theForm.labels[TramitAPersAutFields.TELEFON]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.TELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.TELEFON]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_telefon_columnvalueid">
            <form:errors path="tramitAPersAut.telefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.TELEFON)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.TELEFON)? ' uneditable-input' : ''}"  style="" maxlength="10" path="tramitAPersAut.telefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.NOM)}">
        <tr id="tramitAPersAut_nom_rowid">
          <td id="tramitAPersAut_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.NOM])?'tramitAPersAut.nom':__theForm.labels[TramitAPersAutFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_nom_columnvalueid">
            <form:errors path="tramitAPersAut.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitAPersAut.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.LLINATGE1)}">
        <tr id="tramitAPersAut_llinatge1_rowid">
          <td id="tramitAPersAut_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.LLINATGE1])?'tramitAPersAut.llinatge1':__theForm.labels[TramitAPersAutFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_llinatge1_columnvalueid">
            <form:errors path="tramitAPersAut.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitAPersAut.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.LLINATGE2)}">
        <tr id="tramitAPersAut_llinatge2_rowid">
          <td id="tramitAPersAut_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.LLINATGE2])?'tramitAPersAut.llinatge2':__theForm.labels[TramitAPersAutFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_llinatge2_columnvalueid">
            <form:errors path="tramitAPersAut.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="40" path="tramitAPersAut.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.URLSISTRA)}">
        <tr id="tramitAPersAut_urlsistra_rowid">
          <td id="tramitAPersAut_urlsistra_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.URLSISTRA])?'tramitAPersAut.urlsistra':__theForm.labels[TramitAPersAutFields.URLSISTRA]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.URLSISTRA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.URLSISTRA]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_urlsistra_columnvalueid">
            <form:errors path="tramitAPersAut.urlsistra" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.URLSISTRA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.URLSISTRA)? ' uneditable-input' : ''}"  style="" maxlength="2147483647" path="tramitAPersAut.urlsistra"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TramitAPersAutFields.IDSESIONFORMULARIO)}">
        <tr id="tramitAPersAut_idsesionformulario_rowid">
          <td id="tramitAPersAut_idsesionformulario_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TramitAPersAutFields.IDSESIONFORMULARIO])?'tramitAPersAut.idsesionformulario':__theForm.labels[TramitAPersAutFields.IDSESIONFORMULARIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[TramitAPersAutFields.IDSESIONFORMULARIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TramitAPersAutFields.IDSESIONFORMULARIO]}" ></i>
              </c:if>
            </td>
          <td id="tramitAPersAut_idsesionformulario_columnvalueid">
            <form:errors path="tramitAPersAut.idsesionformulario" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.IDSESIONFORMULARIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TramitAPersAutFields.IDSESIONFORMULARIO)? ' uneditable-input' : ''}"  style="" maxlength="100" path="tramitAPersAut.idsesionformulario"   />

           </td>
        </tr>
        </c:if>
        
