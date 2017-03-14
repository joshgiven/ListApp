var module = angular.module('ngTrailApp');

var loginController = function(authService, $location) {
  var vm = this;

  vm.login = function(user) {
    authService.login(user)
      .then(function(resp){
        console.log('logged in!');
        $location.path('/todos');
      })
      .catch(function(err) {
        console.log('login failed!');
      });
  };

  vm.signup = function(user) {
    authService.signup(user)
      .then(function(resp){
        console.log('registered!');
      })
      .catch(function(err) {
        console.log('register failed!');
      });
  };
};

module.component('loginComponent', {
  templateUrl : 'app_client/templates/login-component.view.html',

  controller : loginController
});
