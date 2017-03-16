var module = angular.module('ngTrailApp');

var searchAndResultComponentController = function() {
  var ctrl = this;

};

module.component('searchAndResultComponent', {
  template : `
    <div class="">
      <h4>form</h4>
      <search-form-component trails="$ctrl.trails">Loading search form...</search-form-component>
      <!--
      <h4>partial</h4>
      <ng-include src="'app_client/templates/search.view.html'"></ng-include>
      -->
      <h4>list</h4>
      <trails-list trails="$ctrl.trails" >Loading results...</trails-list>
    </div>
    `,

  controller : searchAndResultComponentController,

  bindings : {
    trails : '='
  }
});
