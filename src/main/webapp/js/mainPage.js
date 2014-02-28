/**
 * Created by kasutaja on 28.02.14.
 */

$("a").click(function swapPage(target, source) {
    var myUrl = $(this).attr("href") + "source"
    $(target).load(myUrl);
})