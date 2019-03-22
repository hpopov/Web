var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var concat = require('gulp-concat');
var sourcemaps = require('gulp-sourcemaps');
var cleanCss = require('gulp-clean-css');
var gulpIf = require('gulp-if');
var browserSync = require('browser-sync').create();

var config = {
    paths: {
        sass: './app/sass/**/*.scss', 
        html: './app/index.html'   
    },
    output: { 
        cssName: '/css/main.min.css', 
        path: './app'
    },
    isDevelop: true
};
gulp.task('sass', function() {
    return gulp.src(config.paths.sass) 
        .pipe(gulpIf(config.isDevelop, sourcemaps.init()))   
        .pipe(sass()) 
        .pipe(concat(config.output.cssName))      
        .pipe(autoprefixer())      
        .pipe(gulpIf(!config.isDevelop, cleanCss())) 
        .pipe(sourcemaps.write()) 
        .pipe(gulp.dest(config.output.path)) 
        .pipe(browserSync.stream());
});

gulp.task('watch-sass', function(){
	gulp.watch(config.paths.sass, gulp.series('sass'));
});
gulp.task('watch-html', function(){
	gulp.watch(config.paths.html).on('change', browserSync.reload);
});

gulp.task('serve', function() {
    browserSync.init({ 
        server: {
            baseDir: config.output.path 
        }
    });

});


gulp.task('default', gulp.series('sass', gulp.parallel('serve','watch-sass', 'watch-html')));