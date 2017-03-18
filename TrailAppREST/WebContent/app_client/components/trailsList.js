var module = angular.module('ngTrailApp');


var trailListController = function($location) {
  var ctrl = this;

  ctrl.redirectToTrail = function(trail) {
    $location.path('/trail/' + trail.id);
  };
};

module.component('trailsList', {
  template : `
      <div class="trailsList">
        <ul class="list-unstyled">
          <li ng-repeat="trail in $ctrl.trails">
            <trail-component trail="trail"
                             show-all-reports="false"
                             report-quiet="$ctrl.reportQuiet"
                             trail-quiet="$ctrl.trailQuiet"
                            <!--ng-click="$ctrl.redirectToTrail(trail)-->
                             ">
                  Loading...
            </trail-component>
          </li>
          <li ng-hide="$ctrl.trails && $ctrl.trails[0]">no trails</li>
        </ul>
      </div>
    `,

  controller : trailListController,

  bindings : {
    trails : '=',
    reportQuiet : '=',
    trailQuiet : '='
  }
});
