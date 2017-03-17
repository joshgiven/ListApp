var module = angular.module('ngTrailApp');

var trailController = function(trailModel) {
  var ctrl = this;

  console.log("reportQuiet in  trailComponent" + ctrl.reportQuiet);

  ctrl.reports = [];

  ctrl.loadReports = function(showAll) {
    if(showAll) {
      trailModel.getReports(ctrl.trail)
        .then(function(resp) {
          ctrl.reports = resp.data;
        });
    }
    else {
      ctrl.reports = [];
      if(ctrl.trail.recentReport) {
        ctrl.reports.push(ctrl.trail.recentReport);
      }
    }
  };

  ctrl.getImageUrl = function(url) {
    if(!url)
      url = "http://english.tw/wp-content/themes/qaengine/img/default-thumbnail.jpg";

    return url;
  };

  ctrl.loadReports(ctrl.showAllReports);
};

module.component('trailComponent', {
  controller : trailController,

  bindings : {
    trail : '=',
    showAllReports : '=',
    reportQuiet: '=',
    trailQuiet: '='
  },

  template : `
    <div class="">
      <h3>{{$ctrl.trail.name}}</h3>
      <img ng-src="{{$ctrl.getImageUrl($ctrl.trail.imageUrl)}}" />
      <p>location: {{$ctrl.trail.city}}, {{$ctrl.trail.state}}</p>
      <p>long/lat: {{$ctrl.trail.longitude}}/{{$ctrl.trail.latitude}}</p>
      <p>length: {{$ctrl.trail.length}} miles</p>
      <h4>Description</h4>
      <p>{{$ctrl.trail.description}}</p>
      <h4 ng-hide="$ctrl.trailQuiet">Directions</h4>
      <p ng-hide="$ctrl.trailQuiet">{{$ctrl.trail.directions}}</p>
      <h4 ng-hide="$ctrl.trailQuiet">Reports</h4>
      <h4 ng-hide="!$ctrl.trailQuiet">Status</h4>
      <report-list reports="$ctrl.reports" report-quiet="$ctrl.reportQuiet"
                   default-report="$ctrl.trail.recentReport">
        Loading Reports...
      <report-list>
    </div>
    <report-form-component ng-hide="$ctrl.trailQuiet" trail="$ctrl.trail">loading... </report-form-component>
  `
});
