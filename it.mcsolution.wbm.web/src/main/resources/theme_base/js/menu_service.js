'use strict';
 
angular.module('myApp').factory('MenuService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/Spring4MVCAngularJSExample/user/';
 
    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser
    };
 
    return factory;
 
    function fetchAllItem() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Items');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createItem(MenuItemObj) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, MenuItemObj)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Item');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateItem(MenuItemObj, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, MenuItemObj)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Item');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteItem(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Item');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);