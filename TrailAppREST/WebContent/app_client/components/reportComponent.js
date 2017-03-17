var module = angular.module('ngTrailApp');

var reportController = function() {
  var ctrl = this;
};

module.component('reportComponent', {
  template : `
      <div class="reportComponent well">
        <h4 ng-hide="$ctrl.reportQuiet">{{$ctrl.report.heading}}</h4>
        <p ng-hide="$ctrl.reportQuiet">comment: {{$ctrl.report.comment}}</p>
        <p>Last Updated: {{$ctrl.report.timestamp}}</p>
        <p ng-hide="$ctrl.reportQuiet">author: {{$ctrl.report.user.firstName}} {{$ctrl.report.user.lastName}}</p>
        <div class="">
          <ul class="list-inline">
            <li ng-repeat="status in $ctrl.report.tstatuses">
              {{status.statusType }} : <span class="label label-info">{{status.name}}</span>
            </li>
          </ul>
        </div>
      </div>
    `,

  controller : reportController,

  bindings : {
    report : '=',
    reportQuiet : '='
  }
});
