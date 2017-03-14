var app = angular.module('ngTrailApp');

app.factory('trailModel', function($http){
  var service = {};

  var trailAPI = 'api/trails';

  service.getTrails = function() {
    return $http({
      method : 'GET',
      url : trailAPI,
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  service.getTrail = function(id) {
    return $http({
      method : 'GET',
      url : [trailAPI, id].join('/'),
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  service.getReports = function(trail) {
    return $http({
      method : 'GET',
      url : [trailAPI, trail.id, 'reports'].join('/'),
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  return service;
});
