var module = angular.module('ngTrailApp');

module.factory('userModel', function($http, authService){
  var service = {};

  var userAPI = 'api/auth/users';

  service.getUser = function(id) {
    return $http({
      method : 'GET',
      url : [userAPI, id].join('/'),
      headers : {
        'x-access-token' : authService.getToken()
      }
    }).catch(function(reason){
      console.log('catch in userModel.getUser', reason);
    });
  };

  service.createUser = function(user) {
    return $http({
      method : 'POST',
      url : userAPI,
      headers : {
        'Content-Type' : 'application/json',
        'x-access-token' : authService.getToken()
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
        'x-access-token' : authService.getToken()
      },
      data : todo
    });
  };

  service.getUserFavs = function(user) {
    return $http({
      method : 'GET',
      // url : [userAPI, user.id, 'trails'].join('/'),
      url : [userAPI, 'trails'].join('/'),
      headers : {
        'x-access-token' : authService.getToken()
      }
    });
};
    service.addUserFavorite = function(userId, trailId) {
      return $http({
        method : 'PUT',
        // url : [userAPI, user.id, 'trails'].join('/'),
        url : [userAPI, 'trails', trailId].join('/'),
        headers : {
          'x-access-token' : authService.getToken()
        }
      });
  };

  return service;
});
