var module = angular.module('ngTrailApp');

module.service('searchXferService', function() {
  var service = this;
  var params = {};
  var trails = {};

  service.putSearchParams = function(searchParams) {
    params = searchParams;
  };

  service.getSearchParams = function() {
    return params;
  };

  service.putSearchTrails = function(searchTrails) {
    trails = searchTrails;
  };

  service.getSearchTrails = function() {
    return trails;
  };
});
