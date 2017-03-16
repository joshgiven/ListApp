var module = angular.module('ngTrailApp');

var reportController = function() {
  var ctrl = this;
};

module.component('reportComponent', {
  template : `
      <div class="">
        <h4>{{$ctrl.report.heading}}</h4>
        <p>{{$ctrl.report.comment}}</p>
        <p>{{$ctrl.report.timestamp}}</p>
        <p>{{$ctrl.report.user.firstName}}</p>
        <p>{{$ctrl.report.user.lastName}}</p>
        <!-- $ctrl.report.trail -->
        <div ng-repeat="status in $ctrl.report.tstatuses">
          <p>{{tstatus.statusType }}</p>
          <p>{{tstatus.name}}</p>
        </div>
      </div>
    `,

  controller : reportController,

  bindings : {
    report : '='
  }
});
