var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
//const Eureka = require('eureka-js-client').Eureka;

var indexRouter = require('./server/routes/index');
var usersRouter = require('./server/routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'server','views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'build')));

app.use('/server', indexRouter);
app.use('/users', usersRouter);
 
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'build/index.html'));
});

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

// const client = new Eureka({
//   // application instance information
//   instance: {
//     app: 'UIService',
//     hostName: 'localhost',
//     ipAddr: '127.0.0.1',
//     port: 3000,
//     vipAddress: 'jq.test.something.com',
// 	statusPageUrl: 'http://localhost:3000/info',
//     dataCenterInfo: {
//       name: 'MyOwn',
//     },
//   },
//   eureka: {
//     // eureka server host / port
//     host: 'localhost',
//     port: 8761,
// 	servicePath: '/eureka/apps/'
//   },
// });

// client.start();

module.exports = app;
