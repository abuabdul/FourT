<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>

    <div class="container-fluid herocontainer-inner">
        <div class="container">
            <div class="row">
                 <%@ include file="/WEB-INF/jsp/includes/brand-navigation.jsp"%>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-4 col-md-3">
                   	<%@ include file="/WEB-INF/jsp/includes/left-navigation.jsp"%>
                    <%@ include file="/WEB-INF/jsp/includes/left-column.jsp"%> 
                </div>
                <div class="col-xs-12 col-sm-8 col-md-9">
                    <div class="box box-primary innerpage-wrapper">
                        <div class="box-body">  
                                <div class="alert alert-danger">
									<i class="fa fa-exclamation-triangle fa-2x"></i>&nbsp; <span>Some Error Occurred. If the problem persists, please contact our Administrator at
									admin@melapalayam.co.in.</span>
									<br>
									<!-- REMOVE later -->
									${exception.message}
                                </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>        