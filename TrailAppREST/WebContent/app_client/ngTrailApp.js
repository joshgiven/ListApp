var module = angular.module('ngTrailApp', ['ngRoute']);

module.config(function($routeProvider){
  $routeProvider
    .when('/', {
      templateUrl: 'app_client/templates/welcome.view.html',
    })
    .when('/search', {
      templateUrl: 'app_client/templates/search.view.html',
    })
    .when('/trail/:id', {
      template: `
        <trail-component trail="$resolve.trail" showAllReports="true">Loading...</trail-component>
      `,
      resolve: {
         trail : function(trailModel, $route, $location) {
          var id = $route.current.params.id;

          return trailModel.getTrail(id)
            .then(function(resp) {
              return resp.data;
            })
            .catch(function(err) {
              $location.path('error');
            });
         }
      }
    })
    .when('/user/:id', {
      template: `
        <user-component user="$resolve.user">Loading...</user-component>
      `,
      resolve: {
        user : function(userModel, $route, $location) {
          var id = $route.current.params.id;

          return userModel.getUser(id)
            .then(function(resp) {
              return resp.data;
            })
            .catch(function(err) {
              $location.path('error');
            });
        }
      }
    })
    .when('/login', {
      templateUrl: 'app_client/templates/login.view.html',
    })
    .when('/signup', {
      templateUrl: 'app_client/templates/signup.view.html',
    })
    .otherwise({
      redirectTo : '/'
    });

});
