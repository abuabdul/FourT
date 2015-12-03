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
		<div class="row">
			<div class="col-lg-12">
				<div class="box box-primary innerpage-wrapper">
					<div class="box-body">
						<div class="alert alert-danger">
							<i class="glyphicon glyphicon-warning-sign bigger"></i>&nbsp; 
							<span>Some Error Occurred. Please try again.</span> <br>
							<div>${exception}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		</div>
		<!-- /#page-content-wrapper -->