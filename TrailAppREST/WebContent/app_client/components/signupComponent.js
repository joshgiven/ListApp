var module = angular.module('ngTrailApp');

var signupController = function(authService, $location) {
  var vm = this;

  vm.signup = function(user) {
	  console.log('in signup js user: '+ user.email);
    authService.signup(user)
      .then(function(resp){
        console.log('registered!');
        $location.path('/');
      })
      .catch(function(err) {
        console.log('register failed!');
      });
  };
};

module.component('signupComponent', {
  templateUrl : 'app_client/templates/signup-component.view.html',
  controller : signupController
});