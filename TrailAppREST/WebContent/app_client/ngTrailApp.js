var module = angular.module('ngTrailApp', ['ngRoute']);

module.config(function($routeProvider){
  $routeProvider
    .when('/', {
      templateUrl: 'app_client/templates/welcome.view.html',
    })
    .when('/search', {
      templateUrl: 'app_client/templates/search.form.html',
    })
    .when('/trail/:id', {
      templateUrl: 'app_client/templates/trail.view.html',
      resolve: {}
    })
    .when('/login', {
      templateUrl: 'app_client/templates/login-component.view.html',
    })
    .otherwise({
      redirectTo : '/'
    });

});
