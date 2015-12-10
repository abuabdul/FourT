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
						   	<c:if test="${saveTaskDetails}">
	                            <div class="alert alert-success">
	                               	<a href="#" class="close" data-dismiss="alert">&times;</a>
	                               	Resource Task Details saved successfully.
                                </div>
                           	</c:if>
						 	<c:url var="resourceTaskTrackerUrl" value="/secure/resource/taskTracker.go"/>
                            <form:form id="resourceTaskTrackerForm" class="form-horizontal" role="form" modelAttribute="resourceTaskTrackerForm" action="${resourceTaskTrackerUrl}" method="post">
								<div class="form-group">
                                    <label for="forResourceName" class="col-sm-4 control-label">Resource</label>
                                    <div class="col-xs-12 col-sm-8 col-md-5 selectContainer">
                                        <form:select class="form-control" path="resourceName">
                                              <form:option value="">Select</form:option>  
                							  <form:options items="${resourceNameList}"></form:options> 
                                         </form:select>
                                    </div>   
                                </div>
								<div class="form-group">
									<label for="forTaskDate" class="col-sm-4 control-label">Task Date</label>
									<div class="col-xs-12 col-sm-8 col-md-5">
										<form:input type="text" class="form-control" path="taskDate" placeholder="Task Date (23/07/2015)"/>
									</div>
								</div>
								<div class="form-group">
									<label for="forTaskDetail" class="col-sm-4 control-label">Task Details</label>
									<div class="col-xs-12 col-sm-8 col-md-5">
										<form:textarea class="form-control taskDetailDesc" rows="2" path="taskDetailList[0].taskDesc" placeholder="Task Details" />
									</div>
								</div>
								<div class="form-group">
									<label for="forTaskHours" class="col-sm-4 control-label">Task Hours</label>
									<div class="col-xs-12 col-sm-8 col-md-5">
										<form:input type="text" class="form-control taskDetailDuration" path="taskDetailList[0].duration" placeholder="Task Hours" />
									</div>
								</div>
								<div class="form-group">
									<label for="forTaskHours" class="col-sm-4 control-label">Task Status</label>
									<div class="col-xs-12 col-sm-8 col-md-5 selectContainer">
	                                       <form:select class="form-control taskDetailStatus" path="taskDetailList[0].status">
	                                             <form:option value="">Select</form:option>
	                                             <form:options items="${taskStatusList}"></form:options>
	                                       </form:select>
									</div>
									<div class="col-sm-2">
								   		<button type="button" class="btn btn-sm btn-primary add-task"><i class="glyphicon glyphicon-plus"></i></button>
									</div>
								</div>
								<div class="task-details-block hide">
									<div class="form-group">
										<label for="forTaskDetail" class="col-sm-4 control-label">Task Details</label>
										<div class="col-xs-12 col-sm-8 col-md-5">
											<textarea class="form-control taskDetailDesc" rows="2" name="taskDetailList.taskDesc" placeholder="Task Details"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="forTaskHours" class="col-sm-4 control-label">Task Hours</label>
										<div class="col-xs-12 col-sm-8 col-md-5">
											<input type="text" class="form-control taskDetailDuration" name="taskDetailList.duration" placeholder="Task Hours"></input>
										</div>
									</div>
									<div class="form-group">
										<label for="forTaskHours" class="col-sm-4 control-label">Task Status</label>
										<div class="col-xs-12 col-sm-8 col-md-5 selectContainer">
	                                       <select class="form-control taskDetailStatus" name="taskDetailList.status">
	                                             <option value="">Select</option>
	                                             <option value="In Analysis">In Analysis</option>
	                                             <option value="In Progress">In Progress</option>
	                                             <option value="Completed">Completed</option>
	                                       </select>
										</div>
										<div class="col-sm-2">
									   		<button type="button" class="btn btn-sm btn-primary remove-task"><i class="glyphicon glyphicon-minus"></i></button>
										</div>
									</div>
								</div>
								<div class="form-group">
								   	<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-xs-12 col-sm-8 col-md-5">
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
