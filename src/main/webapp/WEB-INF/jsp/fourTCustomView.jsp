<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
<!-- Page Content -->
<div id="page-content-wrapper">

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<span> <a href="#menu-toggle" class="btn btn-xs btn-default"
					id="menu-toggle">Toggle Menu</a>
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
				<h3>Customize Task Tracker View</h3>
				<div class="padding-top5px">
					<c:url var="resourceTaskTrackerDetailsUrl" value="/secure/resource/viewCustomTaskDetails.go" />
					<form:form id="resourceTaskTrackerForm" class="form-horizontal" role="form" modelAttribute="resourceTaskDetailForm" action="${resourceTaskTrackerDetailsUrl}" method="post">
						<div class="form-group">
							<label for="forTaskDetail" class="col-sm-4 control-label">Custom SQL Query</label>
							<div class="col-xs-12 col-sm-8 col-md-5">
								<form:textarea class="form-control customTaskDetails" rows="5" path="customQuery" placeholder="Write SQL Query to find task details" />
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
