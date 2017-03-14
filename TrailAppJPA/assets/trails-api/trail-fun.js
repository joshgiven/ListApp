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

var codata = null;
getTrailData().done((data,status) => codata = data);
