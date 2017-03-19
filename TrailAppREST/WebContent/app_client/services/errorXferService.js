var module = angular.module('ngTrailApp');

module.service('errorXferService', function() {
  var service = this;
  var error = null;

  service.hasError = function() {
    return Boolean(error);
  };

  service.getError = function() {
    return error;
  };

  service.putError = function(err) {
    error = err;
  };
});
