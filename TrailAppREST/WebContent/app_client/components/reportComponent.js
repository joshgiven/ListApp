var module = angular.module('ngTrailApp');

var reportController = function() {
  var ctrl = this;
};


var tmp =
  {
    "tstatuses" : [
      {
        "id":5,
        "name":"no snow",
        "statusType":"snow"
      },
      {
        "id":8,
        "name":"washed out",
        "statusType":"ground"
      },
      {
        "id":15,
        "name":"hazardous",
        "statusType":"passability"
      }]
  };

module.component('reportComponent', {
  template : `
      <div class="">
        <h4>{{$ctrl.report.heading}}</h4>
        <p>comment: {{$ctrl.report.comment}}</p>
        <p>time: {{$ctrl.report.timestamp | date : 'medium' : '-1200' }}</p>
        <p>author: {{$ctrl.report.user.firstName}} {{$ctrl.report.user.lastName}}</p>
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
    report : '='
  }
});
