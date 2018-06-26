$(document).ready(function(){
    $(".film-item").click(function(){
        var display = $(this).children('.tag-data').css('display');
        if (display == 'none')
            $(this).children(".tag-data, .tag-ator, .delete-icon").css("display", "flex");
        else if (display == 'flex')
            $(this).children(".tag-data, .tag-ator, .delete-icon").css("display", "none");
    });

    $(".al-tag").click(function(){
        var display = $(this).siblings('.tag-datal').css('display');
        if (display == 'none')
            $(this).siblings(".tag-datal, .delete-icon, input").css("display", "flex");
        else if (display == 'flex')
            $(this).siblings(".tag-datal, .delete-icon, input").css("display", "none");
    });

    $.fn.textWidth = function(text, font) {
        //alert("cu");
        if (!$.fn.textWidth.fakeEl) $.fn.textWidth.fakeEl = $('<span>').hide().appendTo(document.body);
        
        $.fn.textWidth.fakeEl.text(text || this.val() || this.text() || this.attr('placeholder')).css('font', font || this.css('font'));
        
        return $.fn.textWidth.fakeEl.width();
    };
    
    $('.width-dynamic').on('input', function() {
        var inputWidth = $(this).textWidth();
        $(this).css({
            width: inputWidth
        })
    }).trigger('input');
    
    
    function inputWidth(elem, minW, maxW) {
        elem = $(this);
        console.log(elem)
    }
    
    var targetElem = $('.width-dynamic');
    
    inputWidth(targetElem);
});


