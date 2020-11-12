const helpIcon = document.querySelector('.helpIcon');
const helpContent = document.querySelector('.helpIcon__content');

helpIcon.addEventListener('click',function(e){
    //helpContent.classList.toggle("hidden");
    $('.helpIcon__content').fadeToggle(500);
});
