var module = angular.module('ngTrailApp', ['ngRoute']);

module.config(function($routeProvider){
  $routeProvider
    .when('/', {
      templateUrl: 'app_client/templates/welcome.view.html',
      controller: 'welcomeViewController',
      controllerAs: 'welcome'
    })
    .when('/search', {
      template: `
        <search-and-result-component trails="$resolve.trails"
                                     search-params="$resolve.searchParams">
          Loading...</search-and-result-component>
      `,
      resolve: {
        trails : function(searchXferService){
          var trails = Object.assign([], searchXferService.getSearchTrails());
          return trails;
        },
        searchParams : function(searchXferService){
          var params = Object.assign({}, searchXferService.getSearchParams());
          return params;
        },
      }
    })
    .when('/trail/:id', {
      template: `
        <trail-component trail="$resolve.trail" show-all-reports="true" >
          Loading Trail...</trail-component>
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
