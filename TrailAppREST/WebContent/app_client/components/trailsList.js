var module = angular.module('ngTrailApp');

var trailListController = function() {
  var ctrl = this;
};

module.component('trailsList', {
  template : `
      <div class="">
        <ul>
          <li ng-repeat="trail in $ctrl.trails">
            <trail-component trail="trail" show-all-reports="false">Loading...</report-component>
          </li>
          <li ng-hide="$ctrl.trails && $ctrl.trails[0]">no trails</li>
        </ul>
      </div>
    `,

  controller : trailListController,

  bindings : {
    trails : '='
  }
});
