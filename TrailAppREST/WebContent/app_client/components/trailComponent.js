var module = angular.module('ngTrailApp');

var trailController = function(trailModel) {
  var ctrl = this;
  ctrl.reports = [];

  ctrl.getReports = function(showAllReports) {
    if(showAllReports) {
      trailModel.getReports(ctrl.trail)
        .then(function(resp) {
          ctrl.reports = resp.data;
        });
    }
    else {
      ctrl.reports = [];
      if(ctrl.trail.latestReport)
        ctrl.reports.push(ctrl.trail.latestReport);
    }
  };

  ctrl.getReports(ctrl.showAllReports);
};

module.component('trailComponent', {
  controller : trailController,

  bindings : {
    trail : '=',
    showAllReports : '='
  },

  template : `
    <h3>{{$ctrl.trail.name}}</h3>
    <ul>
      <li ng-repeat="report in $ctrl.reports">
        <report-component report="report">Loading...</report-component>
      </li>
      <li ng-hide="$ctrl.reports && $ctrl.reports[0]">no reports</li>
    </ul>
  `
});
