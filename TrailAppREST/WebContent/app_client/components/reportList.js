var module = angular.module('ngTrailApp');

var reportListController = function() {
  var ctrl = this;

  if(!ctrl.reports)
    ctrl.reports = [];

  if(!ctrl.reports.lenth)
    ctrl.reports.push(ctrl.defaultReport);
};

module.component('reportList', {
  template : `
      <div class="">
        <ul>
          <li ng-repeat="report in $ctrl.reports">
            <report-component report="report">Loading...</report-component>
          </li>
          <li ng-hide="$ctrl.reports && $ctrl.reports[0]">no reports</li>
        </ul>
      </div>
    `,

  controller : reportListController,

  bindings : {
    reports : '=',
    defaultReport : '='
  }
});
