var module = angular.module('ngTrailApp');

module.filter('conditionFilter', function(condFilterCache){
  return function(trails, doFilter) {

    checkedConditions = condFilterCache.keys()
                                       .filter((x) => condFilterCache.get(x));

    var results = [];
    if(doFilter && checkedConditions.length) {
      trails.forEach(function(trail){
        var hits = trail.recentReport.tstatuses.filter((status) =>
          checkedConditions.includes(status.name));

        if(hits.length)
          results.push(trail);
      });
    }
    else {
      results = trails;
    }

    return results;
  };
});
