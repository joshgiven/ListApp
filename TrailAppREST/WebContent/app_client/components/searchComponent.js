 var module = angular.module('ngTrailApp');

 var searchFormComponentController = function(searchService, locationService) {
   var ctrl = this;

   ctrl.getDefaultParams = function() {
     return {state:'Colorado', radius:'10'};
   };

   ctrl.trails = Object.assign([], ctrl.trails);
   ctrl.search = Object.assign(ctrl.getDefaultParams(), ctrl.searchParams);
   ctrl.states = locationService.getStateData();

   ctrl.executeSearch = function(params) {
     ctrl.search = params;

     searchService.findTrails(ctrl.search)
       .then(function(trails) {
         ctrl.trails = trails;

         if(ctrl.onSubmit) {
           ctrl.onSubmit(ctrl.search, ctrl.trails);
         }
       });
   };

   ctrl.keywordSearch = function(keywords) {
     searchService.findTrailsByKeyword(keywords)
       .then(function(trails) {
         ctrl.trails = trails;

         if(ctrl.onSubmit) {
           ctrl.onSubmit(ctrl.search, ctrl.trails);
         }
       });
   };

 };

module.component('searchComponent', {
  templateUrl : 'app_client/templates/search.view.html',

  controller : searchFormComponentController,

  bindings : {
    trails : '=',
    onSubmit : '<',
    searchParams : '<'
  }
});
