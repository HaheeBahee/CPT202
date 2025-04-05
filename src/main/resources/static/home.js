// window.addEventListener('scroll', function() {
//
//     var welcome1 = document.getElementById('welcome1');
//     var news = document.getElementById('news');
//     var aboutus = document.getElementById('aboutus');
//     var intro = document.getElementById('intro');
//     var mission = document.getElementById('mission');
//     var joinus = document.getElementById('joinus');
//     var contact = document.getElementById('contact');
//     var offset = -85;
//     var position = window.scrollY+ offset;
//
//     if (position >= welcome1.offsetTop) {
//         addAnimation(welcome1, 'slide-from-bottom');
//     } else {
//         removeAnimation(welcome1, 'slide-from-bottom');
//     }
//
//     if (position >= news.offsetTop) {
//         addAnimation(news, 'slide-from-bottom');
//     } else {
//         removeAnimation(news, 'slide-from-bottom');
//     }
//
//     if (position >= aboutus.offsetTop) {
//         addAnimation(aboutus, 'slide-from-bottom');
//     } else {
//         removeAnimation(aboutus, 'slide-from-bottom');
//     }
//
//     if (position >= intro.offsetTop) {
//         addAnimation(intro, 'slide-from-bottom');
//     } else {
//         removeAnimation(intro, 'slide-from-bottom');
//     }
//
//     if (position >= mission.offsetTop) {
//         addAnimation(mission, 'slide-from-bottom');
//     } else {
//         removeAnimation(mission, 'slide-from-bottom');
//     }
//
//     if (position >= joinus.offsetTop) {
//         addAnimation(joinus, 'slide-from-bottom');
//     } else {
//         removeAnimation(joinus, 'slide-from-bottom');
//     }
//
//     if (position >= contact.offsetTop) {
//         addAnimation(contact, 'slide-from-bottom');
//     } else {
//         removeAnimation(contact, 'slide-from-bottom');
//     }
// });
//
// function addAnimation(element, className) {
//     if (!element.classList.contains(className)) {
//         element.classList.add(className);
//     }
// }
//
// function removeAnimation(element, className) {
//     if (element.classList.contains(className)) {
//         element.classList.remove(className);
//     }
// }
