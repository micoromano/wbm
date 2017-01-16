<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BEER LIST</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	 <link href="<c:url value="/resources/dist/css/theme.css"/>" rel="stylesheet">
	


<!-- Latest compiled and minified JavaScript -->
     <!-- jQuery -->
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 	<link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/resources/vendor/metisMenu/metisMenu.min.css"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
  

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/resources/vendor/metisMenu/metisMenu.min.js"/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>
</head>
<body>
<style>
::-webkit-scrollbar { 
    display: none; 
}
</style>
<script type="text/javascript">
$( document ).ready(function() {
	
	  setInterval(function() {
         window.location.reload();
       }, 120000); 
	 
	 
	 
	 
$("body").animate({
	scrollTop: $("body").height()
}, 60000, function(){
	$('body').animate({
	   scrollTop:0
	}, 60000); 
});
     

});
</script>
	<div class="container itemContainer" style="width: 100%;height: auto;text-align: center;background-color:black">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<c:forEach var="list" items="${user}" varStatus="loop">
							<div class="col-4k-6 col-md-6 singleItem">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="itemID col-lg-2 itemBubble">
												<i>${loop.index +1}</i>
											</div>
											<div class="itemDesc col-lg-9">
												<div class="firstRow">
													<strong class="itemName">${list.beer}</strong> - <strong
														class="itemType">${list.type}</strong> -
												</div>
												<div class="lastRow">
													<span class="itemCountry">${list.nationality}</span> <strong
														class="itemManifacturer">${list.short_Description}</strong>
													- <span class="itemDegree">${list.grade}</span> <span
														class="itemPrice">${list.note}</span>
												</div>

											</div>
											<!--<div class="itemIcon col-md-2 itemBubble">
																<i>1</i>
																<i>1</i>
									</div>-->

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>