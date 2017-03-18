var module = angular.module('ngTrailApp');

module.controller('welcomeViewController', function($location, searchXferService, authService) {
  var ctrl = this;

  ctrl.submitSearch = function(searchParams, trails) {
    searchXferService.putSearchParams(searchParams);
    searchXferService.putSearchTrails(trails);
    $location.path('/search'); //
  };

  if(authService.isLoggedIn()) {
    $location.path(['/user', authService.currentUser().id].join('/'));
  }
});
