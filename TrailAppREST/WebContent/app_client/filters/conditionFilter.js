var app = angular.module('ngTrailApp');

app.filter('showConditionFilter', function(){
  return function(trails, show) {
    var results = [];
    trails.forEach(function(trail){
      trail.condition.forEach(function(condition){
        if (condition === show ) {
          results.push(trail);
        }
      });
    });
    return results;
  };
});
