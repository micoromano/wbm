<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
     <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.MenuItemObj.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="casaProd">Casa Produttrice</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.beer" id="casaProd" class="username form-control input-sm" placeholder="Inserisci il Nome del Produttore"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="nomeBirra">Nome Birra</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.type" id="nomeBirra" class="form-control input-sm" placeholder="Inserisci il Nome della Birra"/>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="nazionalita">Nazionalità</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.nationality" id="nazionalita" class="form-control input-sm" placeholder="Inserisci la nazionalità della Birra"/>
                              </div>
                          </div>
                      </div>
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="manifact">Manufattura</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.short_description" id="manifact" class="form-control input-sm" placeholder="Inserisci la Manufattura della Birra"/>
                              </div>
                          </div>
                      </div>
                        <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="grade">Gradi</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.grade" id="grade" class="form-control input-sm" placeholder="Inserisci i gradi alcolici  della Birra"/>
                              </div>
                          </div>
                      </div>
  					  <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="notePrice">Prezzo e Misura</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.MenuItemObj.note" id="notePrice" class="form-control input-sm" placeholder="Inserisci le note per il prezzo e la grandezza dei bicchieri della Birra"/>
                              </div>
                          </div>
                      </div>
                
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.MenuItemObj.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Menu delle Birre </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Posizione</th>
                              <th>Casa Produttrice</th>
                              <th>Nome Birra</th>
                              <th>Nazionalità</th>
                              <th>Manufattura</th>
                              <th>Gradi</th>
                              <th>Prezzo e Misura</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.MenuItemObjs">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.type"></span></td>
                              <td><span ng-bind="u.nationality"></span></td>
                              <td><span ng-bind="u.short_description"></span></td>
                              <td><span ng-bind="u.grade"></span></td>
                              <td><span ng-bind="u.note"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Modifica</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Elimina</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value="/resources/js/app.js"/>"></script> 
      <script src="<c:url value="/resources/js/menu_service.js"/>"></script> 
      <script src="<c:url value="/resources/js/menu_controller.js"/>"></script>
  </body>
</html>