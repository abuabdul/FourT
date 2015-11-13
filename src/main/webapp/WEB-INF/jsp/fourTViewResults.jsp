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
						<h3>Resource Task Tracker Details</h3>
						<div class="padding-top5px padding-bottom5px">
						 	<c:url var="resourceTaskTrackerViewUrl" value="/secure/resource/viewTaskDetailResults.go"/>
                            <form:form id="resourceTaskTrackerViewForm" class="form-inline" role="form" modelAttribute="resourceTaskTrackerForm" action="${resourceTaskTrackerViewUrl}" method="post">
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
                                        <form:input type="text" class="form-control" path="taskDetailList[0].duration" placeholder="Task Duration(Hours)"/>
                                    </div>   
                                 </div>
                                <div class="form-group">
                                    <div class="col-xs-12 col-sm-8 col-md-5">
                                        <form:select class="form-control" path="taskDetailList[0].status">
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
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
