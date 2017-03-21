var module = angular.module('ngTrailApp');

module.directive('checkImage', function($http){
  return {
    restrict : 'A',

    link : function(scope, element, attrs) {
      element.on('error', function() {
        console.log('replacing image', element.attr('src'));
        element.attr('src', './img/default-trail-thumb.jpg');
      });
    },
  };
});
