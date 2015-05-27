<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.KlantService" %>
<%@page import="nl.atd.model.Klant" %>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isAdmin(session) ) response.sendRedirect(application.getContextPath() + "/secure/"); %>

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
                            <form class="form-horizontal" method="post" action="addklus">
                                <fieldset>
                                    <div class="control-group">
                                    	<label class="control-label">Klant: </label>
                                    	<div class="controls">
                                    		<select name="klant">
                                    			<c:forEach items="${ServiceProvider.getKlantService.getKlantAlleKlanten() }" var="klant">
                                    				<option value="${klant.gebruikersnaam}">${klant.naam}</option>
                                    			</c:forEach>
                                    		</select>
                                    	</div>
                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">Type: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 " name="type">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Uren: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 " name="uren">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Datum: </label>
                                        <div class="controls">
                                            <input type="text" class="span6 datepicker" name="datum">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Omschrijving: </label>
                                        <div class="controls">
                                            <textarea name="" id="" class="cleditor" name="omschrijving">
                                            </textarea>
                                        </div>
                                    </div>
                                </fieldset>
                        </div>
                    </div>
                    <!--/span-->
                </div>
                <!--/row-->
                <div class="row-fluid sortable">
                    <div class="box span12">
                        <div class="box-header" data-original-title>
                            <h2><i class="halflings-icon white edit"></i><span class="break"></span>Onderdelen toevoegen</h2>
                        </div>
                        <div class="box-content">
                            <fieldset>
                                <div class="control-group">
                                    <div class="select-box">
                                        <label>Artikelen</label>
                                        <select id="leftValues" size="5">
                                            <option value="Rubber">Rubber</option>
                                            <option value="Plastic">Plastic</option>
                                            <option value="Metaal">Metaal</option>
                                        </select>
                                    </div>
                                    <div class="buttongrp">
                                        <input type="button" id="btnLeft" value="&lt;&lt;" />
                                        <input type="button" id="btnRight" value="&gt;&gt;" />
                                    </div>
                                    <div class="select-box">
                                        <label>Geselecteerd</label>
                                        <select id="rightValues" size="4"></select>
                                    </div>
                                    <div class="select-box" id="artikelbox">
                                        <label>Aantal</label>
<!--                                         <input type="text" data-name="metaal" value="0"/>
                                        <input type="text" data-name="plastic" value="0"/> -->
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Klus inplannen</button>
                </div>
                </form>
            </div>
            <!--/.fluid-container-->
            <!-- end: Content -->
        </div>
        <!--/#content.span10-->
    </div>
<%@ include file="_footer.jsp" %>