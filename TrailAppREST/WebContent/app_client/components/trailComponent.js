var module = angular.module('ngTrailApp');

var trailController = function(trailModel, userModel, authService) {
  var ctrl = this;
  ctrl.reports = [];
  ctrl.user = authService.currentUser();

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

  ctrl.addUserFavorite = function(userId, trailId){
    console.log("Trail id " , trailId);
    console.log("User id " , userId);
    userModel.addUserFavorite(userId, trailId);

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
    <div class="trailComponent">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3>{{$ctrl.trail.name}}</h3>
        </div>

        <div class="panel-body">
          <div>
            <div>
              <img ng-src="{{$ctrl.getImageUrl($ctrl.trail.imageUrl)}}"
                   ng-class="$ctrl.trailQuiet ? ['trailThumb','img-thumbnail','pull-left'] : []" />
            </div>

            <button class="btn btn-primary btn-lg type="button" ng-show="$ctrl.user.id"
                ng-click="$ctrl.addUserFavorite($ctrl.user.id, $ctrl.trail.id)">Add Favorite
            </button>

            <div ng-class="$ctrl.trailQuiet ? ['pull-left','well','text-left'] : []">
              <div>
                <h4>Info</h4>
                <ul ng-class="$ctrl.trailQuiet ? ['list-inline'] : ['list-unstyled']">
                  <li>Location: {{$ctrl.trail.city}}, {{$ctrl.trail.state}}</li>
                  <li>Long/Lat: {{$ctrl.trail.longitude}}/{{$ctrl.trail.latitude}}</li>
                  <li>Length: {{$ctrl.trail.length}} miles</li>
                </ul>
                <h4>Description</h4>
                <p>{{$ctrl.trail.description}}</p>
                <h4 ng-hide="$ctrl.trailQuiet">Directions</h4>
                <p ng-hide="$ctrl.trailQuiet">{{$ctrl.trail.directions}}</p>
              </div>

              <div>
                <button class="btn btn-primary btn-lg type="button" ng-hide="$ctrl.trailQuiet ||
                        showReportForm" ng-click="showReportForm = !showReportForm">Add Report
                </button></a>
                <button class="btn btn-primary btn-lg type="button" ng-hide="$ctrl.trailQuiet ||
                        !showReportForm" ng-click="showReportForm = !showReportForm">Hide Report
                </button></a>
                <report-form-component ng-hide="$ctrl.trailQuiet ||
                        !showReportForm" trail="$ctrl.trail">loading...
                </report-form-component>
              </div>

              <div>
                <h4 ng-hide="$ctrl.trailQuiet">Reports</h4>
                <h4 ng-hide="!$ctrl.trailQuiet">Status</h4>
                <report-list reports="$ctrl.reports" report-quiet="$ctrl.reportQuiet"
                             default-report="$ctrl.trail.recentReport">
                  Loading Reports...
                <report-list>
              </div>

            </div>
          </div>
        </div>

        <div class="panel-footer"></div>
      </div>
    </div>
  `
});
