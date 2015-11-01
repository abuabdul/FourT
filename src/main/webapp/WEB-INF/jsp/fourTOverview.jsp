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
						<h3>Resource Task Tracker Form</h3>
						<div class="padding-top5px">
						 	<c:url var="resourceTaskTrackerUrl" value="/fourt/secure/resource/taskTracker.go"/>
                            <form:form id="resourceTaskTrackerForm" class="form-horizontal" role="form" modelAttribute="resourceTaskTrackerForm" action="${resourceTaskTrackerUrl}" method="post">
								<div class="form-group">
                                    <label for="forResourceName" class="col-sm-4 control-label">Resource</label>
                                    <div class="col-xs-12 col-sm-8 col-md-5 selectContainer">
                                        <form:select class="form-control" id="ResourceName" path="resourceName">
                                             <form:option value="">Select</form:option>
                                             <form:option value="Abubacker Siddik">Abubacker Siddik</form:option>
                                             <form:option value="Raja">Raja</form:option>
                                             <form:option value="Sathish">Sathish</form:option>
                                         </form:select>
                                    </div>   
                                </div>
								<div class="form-group">
									<label for="forTaskDate" class="col-sm-4 control-label">Task Date</label>
									<div class="col-xs-12 col-sm-8 col-md-5">
										<form:input type="text" class="form-control" path="taskDate" placeholder="Task Date"/>
									</div>
								</div>
							<%-- 	<c:forEach items="taskDetailList" var="taskDetail" varStatus="status">
								   <div id="TaskDetailsBlock">
										<div class="form-group">
											<label for="forTaskDetail" class="col-sm-4 control-label">Task Details</label>
											<div class="col-sm-8">
												<form:textarea class="form-control taskDetailDesc" rows="5" path="${taskDetail.taskDesc}" placeholder="Task Details"/>
											</div>
										</div>
										<div class="form-group">
											<label for="forTaskHours" class="col-sm-4 control-label">Task Hours</label>
											<div class="col-sm-8">
												<form:input type="text" class="form-control taskDetailDuration" path="${taskDetail.duration}" placeholder="Task Hours"/>
											</div>
										</div>
								    </div> 
								</c:forEach> --%>
								<div class="form-group">
									<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-xs-12 col-sm-8 col-md-5">
										<button type="button" class="btn btn-sm btn-primary pull-right">+</button>
									</div>
								</div>
								<div class="form-group">
								   	<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-sm-6">
										<button type="submit" class="btn btn-sm btn-primary">Submit</button>
										&nbsp;
										<button type="button" id="resetButton" class="btn btn-sm btn-default">Reset</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
