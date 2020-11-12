const toogleBtn = document.querySelector('.navbar__toogleBtn');
const menu = document.querySelector('.navbar__menu');
const login = document.querySelector('.navbar__login');

toogleBtn.addEventListener('click', function(){
    menu.classList.toggle('active');
    login.classList.toggle('active');
});




