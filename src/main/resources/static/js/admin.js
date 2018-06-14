$(function () {
    //退出
    $("#a_sign_out").on("click", function () {
        $("#form_sign_out").submit();
    });
    pageInitModule.setWidth();
    pageInitModule.setSidebar();
});
$(window).resize(function () {
    pageInitModule.setWidth();
});

var pageInitModule = (function (mod) {
    mod.setWidth = function () {
        if ($(window).width() < 768) {
            $(".sidebar").css({left: -220});
            $(".all").css({marginLeft: 0});
        } else {
            $(".sidebar").css({left: 0});
            $(".all").css({marginLeft: 220});
        }
    };
    mod.setSidebar = function () {
        $('[data-target="sidebar"]').click(function () {
            var asideleft = $(".sidebar").offset().left;
            if (asideleft === 0) {
                $(".sidebar").animate({left: -220});
                $(".all").animate({marginLeft: 0});
            }
            else {
                $(".sidebar").animate({left: 0});
                $(".all").animate({marginLeft: 220});
            }
        });
        $(".has-sub>a").click(function () {
            $(this).parent().siblings().find(".sub-menu").slideUp();
            $(this).parent().find(".sub-menu").slideToggle();
        });
        var _strcurrenturl = window.location.href.toLowerCase();
        $(".navbar-nav a[href],.sidebar a[href]").each(function () {
            var href = $(this).attr("href").toLowerCase();
            var isActive = false;
            $(".breadcrumb>li a[href]").each(function (index) {
                if (href === $(this).attr("href").toLowerCase()) {
                    isActive = true;
                    return false;
                }
            });
            if (_strcurrenturl.indexOf(href) > -1 || isActive) {
                $(this).parent().addClass("active");
                if ($(this).parents("ul").attr("class") === "sub-menu") {
                    $(this).parents("ul").slideDown();
                    $(this).parents(".has-sub").addClass("active");
                }
            }
        })
    };
    return mod;
})(window.pageInitModule || {});