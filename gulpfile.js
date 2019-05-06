var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var concat = require('gulp-concat');
var sourcemaps = require('gulp-sourcemaps');
var cleanCss = require('gulp-clean-css');
var gulpIf = require('gulp-if');
var browserSync = require('browser-sync').create();

var config = {

    isDevelop: true,

    paths: {
        sass: './app/sass/**/*.scss', 
        html: './app/index.html',
        filesToCopy: ['./app/index.html', './app/fonts/**/*','./app/images/**/*','./app/js/**/*'],
        css:  './app/css/main.min.css'
    },
    output: { 
        cssName: '/css/main.min.css', 
        path: './dist',
        devPath: './app'
    }
};
gulp.task('sass', function() {
    return gulp.src(config.paths.sass) 
        .pipe(gulpIf(config.isDevelop, sourcemaps.init()))   
        .pipe(sass()) 
        .pipe(concat(config.output.cssName))      
        .pipe(autoprefixer())      
        .pipe(sourcemaps.write()) 
        .pipe(gulp.dest(config.output.devPath)) 
        .pipe(browserSync.stream());
});
gulp.task('copyFiles', function(){
	gulp.src(config.paths.filesToCopy, {base: config.output.devPath})
	.pipe(gulp.dest(config.output.path));

	return gulp.src(config.paths.css, {base: config.output.devPath})
    .pipe(gulpIf(!config.isDevelop, cleanCss())) 
	.pipe(gulp.dest(config.output.path));
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
            baseDir: config.output.devPath 
        }
    });

});


gulp.task('default', gulp.series('sass', 'copyFiles', gulp.parallel('serve','watch-sass', 'watch-html')));
gulp.task('pack', gulp.series('sass', 'copyFiles'));