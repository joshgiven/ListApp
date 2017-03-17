var module = angular.module('ngTrailApp');

var loginController = function(authService, $location) {
  var vm = this;

  vm.login = function(user) {
    authService.login(user)
      .then(function(resp){
        console.log('logged in!');
        $location.path('/users/'+user.id+"/trails");
      })
      .catch(function(err) {
        console.log('login failed!');
      });
  };

};

module.component('loginComponent', {
  templateUrl : 'app_client/templates/login-component.view.html',
  controller : loginController
});
