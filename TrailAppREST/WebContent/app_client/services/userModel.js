var module = angular.module('ngTrailApp');

module.factory('userModel', function($http){
  var service = {};

  var userAPI = 'api/users';

  service.getUser = function(id) {
    return $http({
      method : 'GET',
      url : [userAPI, id].join('/'),
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  service.createUser = function(user) {
    return $http({
      method : 'POST',
      url : userAPI,
      headers : {
        'Content-Type' : 'application/json',
        // 'x-access-token' : authService.getToken()
      },
      data : user
    });
  };

  service.updateUser = function(id, user) {
    return $http({
      method : 'PUT',
      url : [userAPI, id].join('/'),
      headers : {
        'Content-Type' : 'application/json',
        // 'x-access-token' : authService.getToken()
      },
      data : todo
    });
  };

  service.getUserFavs = function(user) {
    return $http({
      method : 'GET',
      url : [userAPI, user.id, 'trails'].join('/'),
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  return service;
});
