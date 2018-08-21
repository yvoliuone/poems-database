# poems-database
This is a simple WeChat Mini Program that recommends poems to users based on user preference. The program displays, in a swiper, two sentences from each recommended poem, and the user can click on it to see the full poem if interested. The program makes the recommendation based on the poems that the user clicks to see and the poems that the user "likes" (by clicking the "like" button in the bottom of a full poem).

Front-end is done in WXML, which is a variant of HTML for WeChat Mini Program development, CSS and JavaScript. Back-end in Java (SSM Framework). Poem database stored in MySQL.


The idea behind the recommendation system is quite simple: I defined ten categories of poems and each poem is linked with some (usually 2) categories. Recommendation is based on the user's three favorite categories, which may vary in time.
