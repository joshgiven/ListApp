var module = angular.module('ngTrailApp');

var reportController = function() {
  var ctrl = this;
};

module.component('reportComponent', {
  template : `
      <div>
        <h4>{{$ctrl.report.heading}}</h4>
        <p>{{$ctrl.report.comment}}</p>
        <p>{{$ctrl.report.timestamp}}</p>
        <p>{{$ctrl.report.tstatuses}}</p>
      </div>
    `,

  controller : reportController,

  bindings : {
    report : '='
  }
});
