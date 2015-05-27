<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.KlantService"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.service.ServiceProvider" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="_header.jsp" %>

            <div id="content" class="span10">
                <div class="row-fluid sortable ui-sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title="">
                            <h2><i class="halflings-icon white tags"></i><span class="break"></span>Artikel overzicht</h2>
                        </div>
                        
                        <div class="box-content">
                        
        	                <c:if test="${not empty param.done}">
								<div class="alert alert-success">
									Opslaan gelukt!
								</div>
							</c:if>
                        
                            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
                                <thead>
                                    <tr role="row">
                                        <td>Naam</td>
                                        <td>Gebruikersnaam</td>
                                        <td>E-mail</td>
                                        <td>Laatste bezoek</td>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                	<c:forEach var="klant" items="${ServiceProvider.getKlantService().getAlleKlantenAuto()}"> 
                                		<tr>
                                			<td>${klant.naam}</td>
                                			<td>${klant.gebruikersnaam}</td>
                                			<td>${klant.email}</td>
                                			<td><fmt:formatDate pattern="dd-MM-yyyy"  value="${klant.laatsteBezoek.time}" /></td>
                                			<td>
	                                            <a class="btn btn-success" href="autooverzicht.jsp?klant=${klant.gebruikersnaam}">
	                                                <i class="fa fa-car"></i>
	                                            </a>
	                                        </td>
                                		</tr>
                                	</c:forEach>
                                
                                </tbody>
                            </table>
                            
                            <% if(AuthHelper.isAdmin(session) || AuthHelper.isMonteur(session)) { %>
                            	<a href="addklant.jsp" class="btn btn-primary">Klant toevoegen</a>
                            <% } %>
                            
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
<%@ include file="_footer.jsp" %>