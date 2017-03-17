var module = angular.module('ngTrailApp');

var searchAndResultComponentController = function(searchXferService) {
  var ctrl = this;

  ctrl.submitSearch = function(searchParams, trails) {
    searchXferService.putSearchParams(searchParams);
    searchXferService.putSearchTrails(trails);
  };
};

module.component('searchAndResultComponent', {
  template : `
    <div class="">
      <search-component trails="$ctrl.trails"
                        search-params="$ctrl.searchParams"
                        on-submit="$ctrl.submitSearch">
        Loading search form...
      </search-component>
      <h4>list</h4>
      <trails-list trails="$ctrl.trails" report-quiet="true" trail-quiet="true">Loading results...</trails-list>
    </div>
    `,

  controller : searchAndResultComponentController,

  bindings : {
    trails : '=',
    searchParams : '<',
    reportQuiet : '=',
    trailQuiet : '=',
  }
});
