mashapeToAppObj = function(obj) {
  var ret = {};
  var activity = obj.activities[0];

  if(activity && activity.activity_type_name === 'hiking') {
    ret.city = obj.city;
    ret.state = obj.state;
    ret.directions = obj.directions;
    ret.latitude = obj.lat;
    ret.longitude = obj.lon;

    ret.name = activity.name; // obj.name ?
    ret.apiId = activity.unique_id; // obj.unique_id ?
    ret.description = ((obj.description) ? obj.description : '') +
                      ' ' +
                      ((activity.description) ? activity.description : '');
    ret.length = activity.length;
    ret.imageUrl = activity.thumbnail;
  }

  return ret;
};

getTrailData = function() {
    return $.ajax({
            type: "GET",
            url: "./colorado.json",
            dataType: "json",
        });
        // .done(function(data, status) {
        //     console.log('It worked');
        //     console.log(data);
        // })
};

submitTrailData = function(trail) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/TrailAppREST/api/trails",
    dataType: "json",
    contentType: 'application/json',  //setting the request headers content-type
    data: JSON.stringify(trail),
    headers: {'Access-Control-Allow-Origin' : true}
  }).done(console.log(trail.name));
};

var codata = null;
getTrailData().done(function(data,status) {
  codata = data;
  codata = data.places.map(x => mashapeToAppObj(x));

  codata.forEach(function(v,i,a) {
    setTimeout(function(){
      submitTrailData(v);
    }, 1000*i);
  });
//  for(var i=0; i < codata.length; i++) {
//	  setTimeout(function(){
//		  submitTrailData(codata[i]);
//	  }, 1000*i);
//  }
});

/*





*/
