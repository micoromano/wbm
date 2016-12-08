<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>AEstimo Login</title>
 	<link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/resources/vendor/metisMenu/metisMenu.min.css"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
 <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Accesso Sicuro</h3>
                    </div>
                    <div class="panel-body">
                    <c:if test="${user eq false}">  
            			<div class="alert alert-danger">
                              Attenzione Utente non Autorizzato o Credenziali Errate.
                            </div>
                    </c:if>
                       <form role="form" action="${pageContext.request.contextPath}/login/doLogin/" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Utente" name="u" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="p" type="password" value="">
                                </div>
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Accedi"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/resources/vendor/metisMenu/metisMenu.min.js"/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>
</body>
</html>