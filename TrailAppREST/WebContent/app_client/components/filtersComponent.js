var module = angular.module('ngTrailApp');

var filtersComponentController = function(condFilterCache, uiHelperService) {
  var ctrl = this;
  ctrl.cache = condFilterCache;
  ctrl.checks = {};
  ctrl.statusInfo = {};

  uiHelperService.getStatusInfo()
    .then(function(info) {
      ctrl.statusInfo = info;
    });

  ctrl.reset = function() {
    ctrl.cache.clearAll();
    ctrl.checks = {};
  };

  ctrl.put = function(type,status, value){
    if(type && status) {
      ctrl.cache.put(type, status, value);
    }
  };

  ctrl.types = function() {
    return Object.keys(ctrl.statusInfo);
  };

  ctrl.reset();
};

module.component('filtersComponent', {

  template : `
	  <h4>Filters</h4>
      <div class="col-md-4 filter" ng-repeat="type in $ctrl.types()">
        <h5 class='capitalize'>{{type}}</h5>
        <ul class="list-unstyled">
          <li ng-repeat="status in $ctrl.statusInfo[type]">
            <div class="checkbox text-left">
              <label>
                <input type="checkbox"
                       ng-click="$ctrl.put(type,status,$ctrl.checks[status])"
                       ng-model="$ctrl.checks[status]"/>{{status}}</label>

            </div>
          </li>
        </ul>
      </div>
  `,

  controller : filtersComponentController,

  bindings :{
    trail : '='
  }

});


module.factory('condFilterCache', function() {
  service = {};
  cache = {};

  service.put = function(type, status, value) {
    cache[type] = (cache[type]) ? cache[type] : {};
    cache[type][status] = value;
  };

  service.statuses = function(type) {
    return cache[type];
  };

  service.types = function() {
    return Object.keys(cache);
  };

  service.clearType = function(type) {
    return delete cache.type;
  };

  service.clearStatus = function(type, status) {
    return delete cache.type.status;
  };

  service.clearAll = function() {
    cache = {};
  };

  return service;
});


module.factory('uiHelperService', function($timeout){
  var service = {};

  var statuses = {
    ground : ['dry', 'icy', 'light mud', 'moderate mud', 'heavy mud', 'washed out'],
    snow : ['no snow', 'light snow cover', 'moderate snow cover', 'slushy snow cover', 'deep snow cover'],
    passability : ['open', 'hazardous', 'impassable', 'closed'],
  };

  service.getStatusInfo = function() {
    return $timeout(100).then(function() {
      return statuses;
    });
  };

  return service;
});
