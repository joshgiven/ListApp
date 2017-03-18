var module = angular.module('ngTrailApp');

var loginController = function(authService, $location) {
  var vm = this;
  vm.error = null;

  vm.login = function(user) {
    vm.error = null;

    authService.login(user)
      .then(function(resp){
        vm.loginRoute();
      })
      .catch(function(resp) {
        //console.log('loginController.login catch', resp);
        if(resp.status === 404) {
          vm.error = "User not found";
        }
        else if(resp.status === 401) {
          vm.error = "Invalid password";
        }
        else {
          vm.error = "Error: " + resp.status + " : " + resp.statusText;
        }
      });
  };

  vm.loginRoute = function(){
	  var loggedInUser = authService.currentUser();
	  $location.path('/user/'+loggedInUser.id);
  };
};

module.component('loginComponent', {
  templateUrl : 'app_client/templates/login-component.view.html',
  controller : loginController
});
