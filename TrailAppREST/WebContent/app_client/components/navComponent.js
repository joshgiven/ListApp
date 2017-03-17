var app = angular.module('ngTrailApp');

app.component('navComponent', {

  templateUrl : 'app_client/templates/nav-component.view.html',

  controller : function(authService,$location) {
    var vm = this;
    
    vm.loggedIn = function(){
    	
    	return authService.isLoggedIn();
    }
    
    vm.user = authService.currentUser();

    vm.logout = function(){
    	
    	authService.logout();
    	$location.path('/');
    }
    
    $(document).ready(function() {
      var $pills = $('#navComponent ul li');
      $pills.click(function(e) {
        var $this = $(this);

        $pills.removeClass('active');

        if (!$this.hasClass('active')) {
          $this.addClass('active');
        }
      });
    });
  },

  bindings: {}
});
