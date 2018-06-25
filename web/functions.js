$(document).ready(function(){
    $(".film-item").click(function(){
        var display = $(this).children('.tag-data').css('display');
        if (display == 'none')
            $(this).children(".tag-data, .tag-ator, .delete-icon").css("display", "flex");
        else if (display == 'flex')
            $(this).children(".tag-data, .tag-ator, .delete-icon").css("display", "none");
    });
    
    $(".hm-films").click(function(){
        var display = $(this).children('.hhidden').css('display');
        if (display == 'none'){
            $(this).children(".hhidden").css("display", "flex");
            $("html, body").animate({ scrollTop: $(document).height() }, 3000);
        }else if (display == 'flex'){
            $("html, body").animate({ scrollTop: 0 }, 1000);
            $(this).children(".hhidden").css("display", "none");
        }
    });
});
