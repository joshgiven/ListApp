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
    ctrl.cache.clear();
    ctrl.checks = {};
  };

  ctrl.put = function(key, value){
    if(key)
      ctrl.cache.put(key, value);
  };

  ctrl.types = function() {
    return Object.keys(ctrl.statusInfo);
  };

  ctrl.reset();
};

module.component('filtersComponent', {

  template : `
    <div class="row">
      <div class="col-md-4" ng-repeat="type in $ctrl.types()">
        <h5>{{type}}</h5>
        <ul class="list-unstyled">
          <li ng-repeat="status in $ctrl.statusInfo[type]">
            <div class="checkbox text-left">
              <label>
                <input type="checkbox"
                       ng-click="$ctrl.put(status,$ctrl.checks[status])"
                       ng-model="$ctrl.checks[status]"/>{{status}}</label>

            </div>
          </li>
        </ul>
      </div>
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

  service.put = function(key, value) {
    cache[key] = value;
  };

  service.get = function(key) {
    return cache[key];
  };

  service.keys = function() {
    return Object.keys(cache);
  };

  service.clear = function(key) {
    return delete cache.key;
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
