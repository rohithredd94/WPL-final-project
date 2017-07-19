var express = require('express');
var router = express.Router();

var monk = require('monk');
var db = monk('localhost:27017/movies');

router.get('/', function(req, res) {
    var collection = db.get('moviesData');
    collection.find({}, function(err, movies){
        if (err) throw err;
      	res.json(movies);
    });
});

module.exports = router;
