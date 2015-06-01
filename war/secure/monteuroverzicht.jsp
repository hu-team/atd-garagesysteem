<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.MonteurService"%>
<%@page import="nl.atd.model.Monteur"%>
<%@page import="nl.atd.service.ServiceProvider" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% if(!AuthHelper.isAdmin(session)) response.sendRedirect(application.getContextPath() + "/secure/"); %>

<%@ include file="_header.jsp" %>

            <div id="content" class="span10">
                <div class="row-fluid sortable ui-sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title="">
                            <h2><i class="halflings-icon white th-list"></i><span class="break"></span>Monteurs overzicht</h2>
                        </div>
                        
                        <div class="box-content">
                        
        	                <c:if test="${not empty param.done}">
								<div class="alert alert-success">
									De nieuwe monteur is succesvol toegevoegd!
								</div>
							</c:if>
                        
                            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
                                <thead>
                                    <tr role="row">
                                        <td>Naam</td>
                                        <td>Gebruikersnaam</td>
                                        <td>Salarisnummer</td>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                	<c:forEach var="monteur" items="${ServiceProvider.getMonteurService().getAlleMonteuren()}"> 
                                		<tr>
                                			<td>${monteur.naam}</td>
                                			<td>${monteur.gebruikersnaam}</td>
                                			<td>${monteur.salarisnummer}</td>
                                		</tr>
                                	</c:forEach>
                                	
                                </tbody>
                            </table>
                            
                            <% if(AuthHelper.isAdmin(session)) { %>
                            	<a href="<%=application.getContextPath()%>/secure/addmonteur.jsp" class="btn btn-primary">Monteur toevoegen</a>
                            <% } %>
                            
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
<%@ include file="_footer.jsp" %>