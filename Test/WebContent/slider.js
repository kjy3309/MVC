const slider = "slider";
const firstContent = document.querySelector(".ad__content:first-child");


function slide(){
    const currentSlide = document.querySelector(`.${slider}`);

    if(currentSlide){
        currentSlide.classList.remove(slider);
        const nextSlide = currentSlide.nextElementSibling;
        if(nextSlide){
            nextSlide.classList.add(slider);
        } else {
            firstContent.classList.add(slider)
        }
    }else{
        firstContent.classList.add(slider);
    }
}

slide();

setInterval(slide, 5000);
