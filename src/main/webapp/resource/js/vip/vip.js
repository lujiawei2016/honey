var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    effect: 'coverflow',
    grabCursor: true,
    centeredSlides: true,
    slidesPerView: 'auto',
    loop: true,
    initialSlide:6,
    coverflow: {
        rotate: 50,
        stretch: 100,
        depth: 400,
        modifier: 1,
        slideShadows : true
    }
});

$(document).ready(function(){
});