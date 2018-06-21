var gulp = require('gulp');
var sass = require('gulp-ruby-sass');
var watch = require('gulp-watch');
//variável de acesso = plugin;

//criação da função sass
gulp.task('sass', () => {
  return sass('web/sass/**/*.sass').pipe(gulp.dest('web/css'))
  //pega todos os arquivos .sass na pasta(e subs), e define a pasta de destino pós processamento
});

gulp.task('watch', () => {
  gulp.watch('web/sass/**/*.sass', ['sass']);
  //roda a task [sass] dentro da pasta escolhida
});