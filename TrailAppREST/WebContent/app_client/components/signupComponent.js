var module = angular.module('ngTrailApp');

var signupController = function(authService, $location) {
  var vm = this;
  vm.error = null;

  vm.signup = function(user) {
    vm.error = null;

    authService.signup(user)
      .then(function(resp){
        vm.loginRoute();
      })
      .catch(function(resp) {
        if(resp.status === 401) {
          vm.error = "A user with that email address already exists";
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

module.component('signupComponent', {
  templateUrl : 'app_client/templates/signup-component.view.html',
  controller : signupController
});
