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

// Array.prototype.contains = function contains(obj) {
//   console.log("Trail: " + obj.name);
//   for (var i = 0; i < this.length; i++) {
//       console.log("This id: " + this[i].id);
//       console.log("Trail id: " + obj.id);
//       if (this[i].id === obj.id) {
//           console.log("I'm true");
//           return true;
//       }
//   }
//   console.log("I'm false");
//   return false;
// };

module.component('userComponent', {
  template : `
    <div class="">
      <h3>{{$ctrl.user.firstName + ' ' + $ctrl.user.lastName}}</h3>
      <h4>Description</h4>
      <!-- <p>{{$ctrl.user.description}}</p> -->
      <h4>Favorite Trails</h4>
      <trails-list trails="$ctrl.userFavs" parent="'userview'">Loading trails...</trails-list>
    </div>
  `,

  controller : userController,

  bindings : {
    user : '='
  },

});
