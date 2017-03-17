var module = angular.module('ngTrailApp');

var reportController = function() {
  var ctrl = this;
  console.log("report quiet in reportComponent: " + ctrl.reportQuiet);
};



module.component('reportComponent', {
  template : `
      <div class="">
        <h4 ng-hide="$ctrl.reportQuiet">{{$ctrl.report.heading}}</h4>
        <p ng-hide="$ctrl.reportQuiet">comment: {{$ctrl.report.comment}}</p>
        <p>Last Updated: {{$ctrl.report.timestamp}}</p>
        <p ng-hide="$ctrl.reportQuiet">author: {{$ctrl.report.user.firstName}} {{$ctrl.report.user.lastName}}</p>
        <div class="">
          <p>status:<p>
          <ul>
            <li ng-repeat="status in $ctrl.report.tstatuses">
              {{status.statusType }} : {{status.name}}
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
