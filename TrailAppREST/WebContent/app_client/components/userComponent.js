var module = angular.module('ngTrailApp');

var userController = function(userModel) {
  var ctrl = this;
  ctrl.userFavs = [];

  ctrl.loadUserFavs = function() {
    if(ctrl.user) {
      userModel.getUserFavs(ctrl.user)
        .then(function(resp) {
          ctrl.userFavs = resp.data;
        });
    }
  };

  ctrl.loadUserFavs();
};

module.component('userComponent', {
  template : `
    <div class="">
      <h3>{{$ctrl.user.firstName + ' ' + $ctrl.user.lastName}}</h3>
      <h4>Description</h4>
      <p>{{$ctrl.user.description}}</p>
      <h4>Favorite Trails</h4>
      <trails-list trails="$ctrl.userFavs">Loading trails...</trails-list>
      
      <!--
      <ul>
        <li ng-repeat="trail in $ctrl.userFavs">
          <trail-component trail="trail" showAllReports="false">Loading...</trail-component>
        </li>
      </ul>
      -->
    </div>
  `,

  controller : userController,

  bindings : {
    user : '='
  },

});
