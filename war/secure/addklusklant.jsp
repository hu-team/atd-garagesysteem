<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.KlantService" %>
<%@page import="nl.atd.service.AutoService" %>
<%@page import="nl.atd.service.MonteurService" %>
<%@page import="nl.atd.service.ArtikelService" %>
<%@page import="nl.atd.service.ServiceProvider" %>
<%@page import="nl.atd.model.Klant" %>
<%@page import="nl.atd.model.Auto" %>
<%@page import="nl.atd.model.Monteur" %>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isKlant(session) ) response.sendRedirect(application.getContextPath() + "/secure/"); %>

            <!-- start: Content -->
            <div id="content" class="span10">
                <ul class="breadcrumb">
                </ul>
                <div class="row-fluid sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title>
                            <h2><i class="halflings-icon white edit"></i><span class="break"></span>Nieuwe klus toevoegen</h2>
                        </div>
                        <div class="box-content">
                        
                        	<c:if test="${error == true}">
								<div class="alert alert-danger">
									<c:out value="${errorString }" escapeXml="false"></c:out>
								</div>
								</c:if>
								<c:if test="${not empty param.done}">
									<div class="alert alert-success">
										Opslaan gelukt!
									</div>
							</c:if>
		
                        
                            <form class="form-horizontal" method="post" action="addklusklant">
                                <fieldset>
                                    <div class="control-group">
                                    	<label class="control-label">*Auto: </label>
                                    	<div class="controls">
                                    		<select name="auto">
                                    			<c:forEach var="auto" items="${ServiceProvider.getAutoService().getAutosVanKlant(session.getSession()) }" >
                                    				<option value="${auto.kenteken }">${auto.merk } - ${auto.model }</option>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    </div>
                                                                        
                                    <div class="control-group">
                                        <label class="control-label">*Type: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 " name="type">
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">*Datum: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 datepicker" name="datum">
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">*Tijdstip: </label>
                                        <div class="controls">
                                            <input type="time" class="span2" name="tijdstip"> <span class="text-muted">24-uurs notatie</span>
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">*Omschrijving: </label>
                                        <div class="controls">
                                            <textarea id="" class="cleditor" name="omschrijving">
                                            	Beschrijf hier het probleem met uw auto.
                                            </textarea>
                                        </div>
                                    </div>
                                </fieldset>
                               <div class="form-actions">
                    			<button type="submit" class="btn btn-primary">Klus inplannen</button>
                    			<a href="<%=application.getContextPath()%>/secure/index.jsp" class="btn btn-danger promise-me">Annuleren</a>
                				</div>
                				</form>
                        </div>
                    </div>
                    <!--/span-->                    
                </div>
                <!--/row-->
            </div>
            <!--/.fluid-container-->
            <!-- end: Content -->
        </div>
        <!--/#content.span10-->
    </div>
<%@ include file="_footer.jsp" %>