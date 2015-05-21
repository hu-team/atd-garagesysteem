<%@page import="nl.atd.service.ArtikelService"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="nl.atd.service.ServiceProvider" %>
<%@ include file="_header.jsp" %>

            <div id="content" class="span10">
                <div class="row-fluid sortable ui-sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title="">
                            <h2><i class="halflings-icon white tags"></i><span class="break"></span>Artikel overzicht</h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
                                <thead>
                                    <tr role="row">
                                        <td>Naam</td>
                                        <td>Code</td>
                                        <td>Aantal</td>
                                        <td>Prijs</td>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                	<c:forEach var="artikel" items="${ServiceProvider.getArtikelService().getAlleArtikelen()}"> 
                                		<tr>
                                			<td>${artikel.naam}</td>
                                			<td>${artikel.code}</td>
                                			<td>${artikel.aantal}</td>
                                			<td>${artikel.prijs}</td>
                                			<td>
	                                            <a class="btn btn-success" href="#">
	                                                <i class="halflings-icon white zoom-in"></i>
	                                            </a>
	                                            <a class="btn btn-danger" href="#">
	                                                <i class="halflings-icon white trash"></i>
	                                            </a>
	                                        </td>
                                		</tr>
                                	</c:forEach>
                                
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
<%@ include file="_footer.jsp" %>