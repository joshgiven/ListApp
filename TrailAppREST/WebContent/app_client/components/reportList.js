var module = angular.module('ngTrailApp');

var reportListController = function() {
  var ctrl = this;

  if(!ctrl.reports)
    ctrl.reports = [];

  if(!ctrl.reports.length)
    ctrl.reports.push(ctrl.defaultReport);
};

module.component('reportList', {
  template : `
      <div class="reportList">
        <ul class="list-unstyled">
          <li ng-repeat="report in $ctrl.reports | orderBy:'timestamp':true track by $index">
            <report-component report="report" report-quiet="$ctrl.reportQuiet">Loading...</report-component>
          </li>
          <li ng-hide="$ctrl.reports && $ctrl.reports[0]">no reports</li>
        </ul>
      </div>
    `,

  controller : reportListController,

  bindings : {
    reports : '=',
    defaultReport : '=',
    reportQuiet : '='
  }
});
