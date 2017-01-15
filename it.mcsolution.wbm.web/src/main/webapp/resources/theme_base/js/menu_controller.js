'use strict';
 
angular.module('myApp').controller('UserController', ['$scope', 'MenuService', function($scope, MenuService) {
    var self = this;
    self.MenuItemObj={id:null,beer : '',glass :'',grade : '',note : '',short_description :'',type : '',nationality : ''};
    self.MenuItemObjs=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllItem();
 
    function fetchAllItem(){
    	MenuService.fetchAllItem()
            .then(
            function(d) {
                self.MenuItemObjs = d;
            },
            function(errResponse){
                console.error('Error while fetching MenuItem');
            }
        );
    }
 
    function createItem(MenuItemObj){
    	MenuService.createItem(MenuItemObj)
            .then(
            	fetchAllItem,
            function(errResponse){
                console.error('Error while creating MenuItem');
            }
        );
    }
 
    function updateItem(MenuItemObj, id){
    	MenuService.updateItem(MenuItemObj, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating MenuItem');
            }
        );
    }
 
    function deleteItem(id){
    	MenuService.deleteItem(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }
 
    function submit() {
        if(self.user.id===null){
            console.log('Saving New Item', self.user);
            createItem(self.MenuItemObj);
        }else{
        	updateItem(self.MenuItemObj, self.MenuItemObj.id);
            console.log('Item updated with id ', self.MenuItemObj.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.MenuItemObjs.length; i++){
            if(self.MenuItemObjs[i].id === id) {
            	self.MenuItemObj = angular.copy(self.MenuItemObjs[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.MenuItemObj.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteItem(id);
    }
 
 
    function reset(){
    	self.MenuItemObj={id:null,beer : '',glass :'',grade : '',note : '',short_description :'',type : '',nationality : ''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);