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
    <div class="searchAndResultComponent">
      <div class="row">
        <div class="col-md-4"><!-- spacer --></div>

        <div class="col-md-4 search">
          <search-component trails="$ctrl.trails"
                            search-params="$ctrl.searchParams"
                            on-submit="$ctrl.submitSearch">
            Loading search form...
          </search-component>
        </div>

        <div class="col-md-4"><!-- spacer --></div>
      </div>

      <div class="row">

        <div class="">
          <h4>Results</h4>
          <trails-list trails="$ctrl.trails"
                       report-quiet="true" trail-quiet="true">
            Loading results...
          </trails-list>
        </div>

      </div>
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
