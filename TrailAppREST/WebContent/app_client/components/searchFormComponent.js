

var module = angular.module('ngTrailApp');

var searchFormComponentController = function(searchService) {
  var ctrl = this;
  ctrl.search = {};

  ctrl.executeSearch = function() {
    searchService.execute(ctrl.search)
      .then(function(resp) {
        ctrl.trails = resp.data;
      });
  };

};

module.component('searchFormComponent', {
  template : `
  <form class="form" name="searchForm" novalidate ng-init="$ctrl.search">
    <div class="form-group">
      <label for="city">city</label>
      <input class="form-control" type="text" name="city" placeholder="city" ng-model="$ctrl.search.city"/>
    </div>

    <div class="form-group">
      <label for="state">state</label>
      <input class="form-control" type="text" name="state" placeholder="state" ng-model="$ctrl.search.state"/>
    </div>

    <div class="form-group">
      <label for="radius">radius</label>
      <input class="form-control" type="number" name="radius" placeholder="radius" ng-model="$ctrl.search.radius"/>
    </div>

    <div class="form-group">
      <label for="length-min">length (min)</label>
      <input class="form-control" type="number" name="length-min" placeholder="length" ng-model="$ctrl.search.lengthMin"/>
    </div>

    <div class="form-group">
      <label for="length-max">length (max)</label>
      <input class="form-control" type="number" name="length-max" placeholder="length" ng-model="$ctrl.search.lengthMax"/>
    </div>

    <div class="form-group">
      <label for="snow">since</label>
      <input class="form-control" type="date" name="since" placeholder="since" ng-model="$ctrl.search.since"/>
    </div>

    <button class="btn" type="button" name="button" ng-click="$ctrl.search = {}">clear</button>
    <button class="btn" type="button" name="button" ng-click="$ctrl.executeSearch()">submit</button>
  </form>
</div>
    `,

  controller : searchFormComponentController,

  bindings : {
    trails : '='
  }
});
