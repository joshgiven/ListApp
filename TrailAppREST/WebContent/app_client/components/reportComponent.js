var module = angular.module('ngTrailApp');

var reportController = function(authService) {
  var ctrl = this;

  ctrl.user = authService.currentUser();

  ctrl.owner = function(report){
    if (ctrl.user && ctrl.user.id === report.user.id) {
      return true;
    }
    else return false;
  };
  ctrl.edit = false
  ctrl.toggle = function(){
	  ctrl.edit = !ctrl.edit;
  }
};

module.component('reportComponent', {
  template : `
      <div class="reportComponent well">
        <h4 ng-hide="$ctrl.reportQuiet">{{$ctrl.report.heading}}</h4>
        <button class="btn btn-primary btn-lg" ng-show=$ctrl.owner($ctrl.report)   
                ng-click=$ctrl.toggle() ng-disabled="$ctrl.edit">Edit</button>
	  <button class="btn btn-primary btn-lg" ng-show="$ctrl.edit" ng-click=$ctrl.toggle()>Cancel</button>
          <div>
          	<report-form-component ng-show=$ctrl.edit && $ctrl.owner($ctrl.report) edit=$ctrl.edit report-info=$ctrl.report >loading....</report-form-component>
          </div>
        <p ng-hide="$ctrl.reportQuiet">comment: {{$ctrl.report.comment}}</p>
        <p>Last Updated: {{$ctrl.report.timestamp | date : 'medium' : '-1200' }}</p>
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
