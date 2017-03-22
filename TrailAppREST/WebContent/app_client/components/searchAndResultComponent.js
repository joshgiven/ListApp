var module = angular.module('ngTrailApp');

var searchAndResultComponentController = function(searchXferService, $location, $anchorScroll) {
  var ctrl = this;

  ctrl.scrollToResults = function() {
    //$anchorScroll('trailSearchResultList');

    // $anchorScroll.disableAutoScrolling().
    // $location.hash('trailSearchResultList');
    // $anchorScroll();
    // $location.hash('');

  };

  ctrl.submitSearch = function(searchParams, trails) {
    searchXferService.putSearchParams(searchParams);
    searchXferService.putSearchTrails(trails);

    ctrl.scrollToResults();
  };

  if(ctrl.trails.length) {
    ctrl.scrollToResults();
  }

};

module.component('searchAndResultComponent', {
  template : `
    <div class="searchAndResultComponent">
      <div class="row">
        <div class="col-md-2"><!-- spacer --></div>

        <div class="col-md-8 search">
          <search-component trails="$ctrl.trails"
                            search-params="$ctrl.searchParams"
                            on-submit="$ctrl.submitSearch">
            Loading search form...
          </search-component>

        </div>

        <div class="col-md-2"><!-- spacer --></div>
      </div>

      <div class="row">
        <div class="col-md-3"><!-- spacer --></div>



        <div class="col-md-3"><!-- spacer --></div>
      </div>

      <div class="row">

        <div  class="">
          <a id="trailSearchResultList"></a>
          <h4>Results</h4>
          <trails-list trails="$ctrl.trails"
                       report-quiet="true"
                       trail-quiet="true"
                       filter-by-condition="true"
                       parent="'searchview'">
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
