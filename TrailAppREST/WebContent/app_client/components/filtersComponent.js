var module = angular.module('ngTrailApp');

var filtersComponentController = function() {
  var ctrl = this;


};

module.component('filtersComponent', {

  template : `
<div class="col-md-6 text-center">
  <div class="form-group">
    <label for="open">Open</label> <input class="form-control"
      type="checkbox" name="open"
      ng-model="searchConditions.recentReport.tstatuses" />

      <label for="closed">Closed</label> <input class="form-control"
        type="checkbox" name="closed"
        ng-model="searchConditions.recentReport.tstatuses" />
  </div>
  `,

controller : filtersComponentController,

bindings :{
  trail : '='
}

});
