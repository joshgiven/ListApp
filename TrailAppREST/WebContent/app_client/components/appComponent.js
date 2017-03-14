var module = angular.module('ngTrailApp');

var appController = function() {
  var ctrl = this;
};

module.component('appComponent', {
  template : `
    <div class="container">
      <nav-component>Loading...</nav-component>
      <ng-view>Loading...</ng-view>
    </div>
    `,

  controller : appController
});
