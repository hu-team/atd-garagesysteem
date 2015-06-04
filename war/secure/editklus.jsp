<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.KlantService" %>
<%@page import="nl.atd.service.AutoService" %>
<%@page import="nl.atd.service.MonteurService" %>
<%@page import="nl.atd.service.KlusService" %>
<%@page import="nl.atd.service.ArtikelService" %>
<%@page import="nl.atd.service.ServiceProvider" %>
<%@page import="nl.atd.model.Klant" %>
<%@page import="nl.atd.model.Auto" %>
<%@page import="nl.atd.model.Monteur" %>
<%@page import="nl.atd.model.Klus" %>
<%@page import="nl.atd.model.Artikel" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.io.IOException" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isAdmin(session) ) response.sendRedirect(application.getContextPath() + "/secure/"); 
if(request.getParameter("id") == null || request.getParameter("id").trim().isEmpty()) {
	response.sendRedirect(application.getContextPath()+"/secure/index.jsp"); 
	return;
	}


// Integer maken van Strnig id
int id = 0;
try{
	id = Integer.parseInt(request.getParameter("id"));
}catch(NumberFormatException nfe){
	response.sendRedirect(application.getContextPath()+"/secure/index.jsp"); 
	return;
}

// Klus ophalen
Klus klus = ServiceProvider.getKlusService().getKlusOpId(id);

if(klus == null){
	response.sendRedirect(application.getContextPath()+"/secure/index.jsp"); 
	return;
}

request.getSession().setAttribute("klusid", id);

if(klus.getMonteur() == null) {
	pageContext.setAttribute("huidigeMonteur", null);
}else{
	pageContext.setAttribute("huidigeMonteur", klus.getMonteur().getGebruikersnaam());
}

pageContext.setAttribute("klaar", klus.isKlaar());

%>

            <!-- start: Content -->
            <div id="content" class="span10">
                <ul class="breadcrumb">
                </ul>
                <div class="row-fluid sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title>
                            <h2><i class="halflings-icon white edit"></i><span class="break"></span>Klus aanpassen</h2>
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
		
                        
                            <form class="form-horizontal" method="post" action="editklus">
                                <fieldset>
                                    <div class="control-group">
                                    	<label class="control-label">Klant: </label>
                                    	<div class="controls">
                                    		<p class="form-control-static"><c:out value="<%=klus.getKlant().getNaam() %>"></c:out></p>
                                    	</div>
                                    </div>
                                    
                                    <div class="control-group">
                                    	<label class="control-label">Auto: </label>
                                    	<div class="controls">
                                    		<p class="form-control-static"><c:out value="<%=klus.getAuto().getModel() + \" - \" 
                                    	+ klus.getAuto().getMerk() %>"></c:out>
                                    		<span class="kenteken kenteken-xl"><c:out value="<%=klus.getAuto().getKenteken().toUpperCase() %>"></c:out></span>
                                    		</p>
                                    	</div>
                                    </div>
                                    
                                    <div class="control-group">
                                    	<label class="control-label">Monteur: </label>
                                    	<div class="controls">
                                    		<select name="monteur">
                                    			<c:choose>
                                    				<c:when test="${empty huidigeMonteur }">
                                    					<option value="" selected>Geen</option>
                                    				</c:when>
                                    				<c:otherwise>
                                    					<option value="">Geen</option>	                                    				
                                    				</c:otherwise>
                                    			</c:choose>
                                    			
                                    			<c:forEach var="monteur" items="${ServiceProvider.getMonteurService().getAlleMonteuren() }">
                                    				<c:choose>
                                    					<c:when test="${huidigeMonteur eq monteur.gebruikersnaam }">
                                    						<option value="${monteur.gebruikersnaam }" selected>${monteur.naam }</option>
                                    					</c:when>
                                    					<c:otherwise>
                                    						<option value="${monteur.gebruikersnaam }">${monteur.naam }</option>
                                    					</c:otherwise>
                                    				</c:choose>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">Type: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 " name="type" value="<%=klus.getType() %>">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Uren: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 " name="uren" value="<%=klus.getUren() %>">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">Datum: </label>
                                        <div class="controls">
                                            <p class="form-control-static"><fmt:formatDate type="DATE" value="<%=klus.getCalendar().getTime() %>"/></p>
                                        </div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">Tijdstip: </label>
                                        <div class="controls">
                                            <p class="form-control-static"><fmt:formatDate type="TIME" value="<%=klus.getCalendar().getTime() %>"/></p>
                                        </div>
                                    </div>
        							
        							<div class="control-group">
                                        <label class="control-label">Klaar: </label>
                                        <div class="controls">
                                        	<c:choose>
                                        		<c:when test="${klaar }">
	                                        		<input type="checkbox" class="span6 " name="klaar" value="true" checked>
                                        		</c:when>
                                        		<c:otherwise>
                                        			<input type="checkbox" class="span6 " name="klaar" value="true">
                                        		</c:otherwise>
                                        	</c:choose>
                                        </div>
                                    </div>
        							                            
                                    <div class="control-group">
                                        <label class="control-label">Omschrijving: </label>
                                        <div class="controls">
                                            <textarea class="cleditor" name="omschrijving"><%=klus.getOmschrijving() %></textarea>
                                        </div>
                                    </div>
                                </fieldset>
                               <div class="form-actions">
                    			<button type="submit" class="btn btn-primary">Klus aanpassen</button>
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