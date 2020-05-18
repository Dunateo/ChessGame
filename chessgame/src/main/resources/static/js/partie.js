$("img").mouseover(function(){
  console.log(this.getAttribute('id'));
  $("this").animate({left: '250px'});
});