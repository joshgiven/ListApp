 var module = angular.module('ngTrailApp');

var searchController = function(authService, $location) {
  var vm = this;

};

module.component('searchComponent', {
  templateUrl : 'app_client/templates/search.view.html',
  controller : searchController
});