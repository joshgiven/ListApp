 var module = angular.module('ngTrailApp');

 var searchFormComponentController = function(searchService) {
   var ctrl = this;
   ctrl.search = {};

   ctrl.executeSearch = function() {
     searchService.execute(ctrl.search)
       .then(function(resp) {
         ctrl.trails = resp.data;
       });
   };

 };

module.component('searchComponent', {
  templateUrl : 'app_client/templates/search.view.html',

  controller : searchFormComponentController,

  bindings : {
    trails : '='
  }
});
