<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<span> <a href="#menu-toggle"
							class="btn btn-xs btn-default" id="menu-toggle">Toggle Menu</a>
						</span>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<h1 class="pull-right">FourT</h1>
					</div>
				</div>
				<div class="row row-border">
					<div class="col-lg-12">
						<h3>Resource Task Details</h3>
						<div class="padding-bottom5px">
						 	<c:url var="resourceTaskTrackerViewUrl" value="/secure/resource/viewTaskDetailResults.go"/>
                            <form:form id="resourceTaskTrackerViewForm" class="form-inline" role="form" modelAttribute="resourceTaskDetailForm" action="${resourceTaskTrackerViewUrl}" method="post">
								<div class="form-group">
                                    <div class="col-xs-12 col-sm-8 col-md-5">
                                        <form:select class="form-control" path="resourceName">
                                             <form:option value="">Resource Name</form:option>
                                             <form:option value="Abubacker Siddik">Abubacker Siddik</form:option>
                                             <form:option value="Raja">Raja</form:option>
                                             <form:option value="Sathish">Sathish</form:option>
                                         </form:select>
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <div class="col-xs-12 col-sm-8 col-md-5">
                                        <form:input type="text" class="form-control" path="taskDate" placeholder="Task Date(23/07/2015)"/>
                                    </div>   
                                 </div>
                                 <div class="form-group">
                                    <div class="col-xs-12 col-sm-8 col-md-5">
                                        <form:input type="text" class="form-control" path="duration" placeholder="Task Duration(Hours)"/>
                                    </div>   
                                 </div>
                                <div class="form-group">
                                    <div class="col-xs-12 col-sm-8 col-md-5">
                                        <form:select class="form-control" path="status">
	                                         <form:option value="">Task Status</form:option>
	                                         <form:option value="In Analysis">In Analysis</form:option>
	                                         <form:option value="In Progress">In Progress</form:option>
	                                         <form:option value="Completed">Completed</form:option>
	                                     </form:select>
                                    </div>   
                                 </div>
								<div class="form-group">
									<div class="col-xs-12 col-sm-8 col-md-5">
										<button type="submit" class="btn btn-sm btn-primary">Submit</button>
									</div>
								</div>
							</form:form>
						</div>
						<c:if test="${(empty resourceTaskDetails) and (not viewTasksLanding)}">
							<div class="table-responsive padding-3px">
							    <div class="page-header">
	  								<h2><small>No Results Found</small></h2>
								</div>
							</div>
						</c:if>			
						<c:if test="${not empty resourceTaskDetails}">
							<div class="table-responsive padding-3px">
							    <div class="page-header">
	  								<h2><small>Results based on above filters:</small></h2>
								</div>
								<table id="tasktracker" class="table table-striped table-bordered">
									   <thead>
									 		<tr>
									 		    <th>#</th>
												<th>Task Date</th>
												<th>Name</th>
												<th>Task Description</th>
												<th>Duration(Hrs)</th>
												<th>Status</th>
											</tr>
										</thead>
										<tfoot>
									 		<tr>
									 		    <th>#</th>
												<th>Task Date</th>
												<th>Name</th>
												<th>Task Description</th>
												<th>Duration(Hrs)</th>
												<th>Status</th>
											</tr>
										</tfoot>
										<tbody>
										    <c:forEach items="${resourceTaskDetails}" var="viewTaskDtl">
												<tr>
												    <td></td>
												    <td>${viewTaskDtl.taskDate}</td>
												    <td>${viewTaskDtl.resourceName}</td>
												    <td>${viewTaskDtl.taskDesc}</td>
												    <td>${viewTaskDtl.duration}</td>
												    <td>${viewTaskDtl.status}</td>
												</tr>
											</c:forEach>
										</tbody>
								</table>
						  </div>
					  </c:if>  
				 </div>
			 </div>
		 </div>
	 </div>
		<!-- /#page-content-wrapper -->		