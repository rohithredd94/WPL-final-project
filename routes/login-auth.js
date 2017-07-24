var express = require('express');
var router = express.Router();

var monk = require('monk');//Object for monk
var db = monk('localhost:27017/movies'); //Using monk to connect to MongoDB
console.log("hello from login-auth");
router.get('/', function(req, res) {
    var collection = db.get('users');
    console.log("Hello");
    collection.find({}, function(err, users){
        if (err) throw err;
        console.log(users);
        res.json(users);
    });
});

router.post('/', function(req, res){
    var collection = db.get('users');
    console.log(req.body);
    /*collection.insert({
        title: req.body.title,
        description: req.body.description
    }, function(err, video){
        if (err) throw err;

        res.json(video);
    });*/
    collection.findOne({name:req.body.email, password: req.body.password}, function(err, user){
        if(err) throw err;

        res.redirect("/#/main");
    });
    console.log("Inside login-auth-post");
});

module.exports = router;