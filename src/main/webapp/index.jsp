<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>FourT - Task Tracker</title>

<!-- Bootstrap -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>"
	rel="stylesheet">
<link href="<c:url value='/resources/css/simple-sidebar.css'/>"
	rel="stylesheet">

<!-- fourt style -->
<link href="<c:url value='/resources/css/fourt-style.css'/>"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Task Tracker </a></li>
				<li><a href="#">Key-in Tasks</a></li>
				<li><a href="#">View Tasks</a></li>
				<li><a href="#">Custom View</a></li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

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
					<div class="col-lg-7">
						<h3>Resource Task Tracker Form</h3>
						<div class="padding-top5px">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="resourceName" class="col-sm-4 control-label">Resource</label>
									<div class="col-sm-8">
										<input type="email" class="form-control" id="inputResource"
											placeholder="Resource Name">
									</div>
								</div>
								<div class="form-group">
									<label for="taskDate" class="col-sm-4 control-label">Task Date</label>
									<div class="col-sm-8">
										<input type="text" class="form-control"
											id="inputTaskDate" placeholder="Task Date">
									</div>
								</div>
								<div class="form-group">
									<label for="resourceName" class="col-sm-4 control-label">Task Details</label>
									<div class="col-sm-8">
										<textarea class="form-control" rows="5" placeholder="Task Details"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="taskHours" class="col-sm-4 control-label">Task Hours</label>
									<div class="col-sm-8">
										<input type="text" class="form-control"
											id="inputTaskHours" placeholder="Task Hours">
									</div>
								</div>
								<div class="form-group">
									<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
										<button type="button" class="btn btn-sm btn-primary pull-right">+</button>
									</div>
								</div>
								<div class="form-group">
								   	<label for="empty" class="col-sm-4 control-label"></label>
									<div class="col-sm-6">
										<button type="submit" class="btn btn-sm btn-primary">Submit</button>
										&nbsp;
										<button type="button" class="btn btn-sm btn-default">Reset</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="<c:url value='/resources/js/jquery.js'/>"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
