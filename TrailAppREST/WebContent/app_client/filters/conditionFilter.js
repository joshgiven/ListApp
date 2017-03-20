var module = angular.module('ngTrailApp');

module.filter('conditionFilter', function(condFilterCache){
  return function(trails, doFilter) {
    var checkedConditions = [];
    condFilterCache.types().forEach(function(type) {
      var obj = condFilterCache.statuses(type);
      var arr = Object.keys(obj).filter((x) => obj[x]);
      if(arr.length)
        checkedConditions.push(arr);
    });

    //console.log(checkedConditions);

    var results = trails;
    checkedConditions.forEach(function(cSet){
      results = results.filter(function(trail) {
        var hits = [];
        if(trail.recentReport && trail.recentReport.tstatuses) {
          hits = trail.recentReport.tstatuses.filter(function(x) {
            return cSet.includes(x.name);
          });
        }
        return hits.length > 0;
      });
    });

    // console.log("in", trails);
    // console.log("out", results);

    return results;
  };
});
