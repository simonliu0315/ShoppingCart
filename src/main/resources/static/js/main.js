(function($) {
  "use strict"

  // NAVIGATION
  var responsiveNav = $('#responsive-nav'),
    catToggle = $('#responsive-nav .category-nav .category-header'),
    catList = $('#responsive-nav .category-nav .category-list'),
    menuToggle = $('#responsive-nav .menu-nav .menu-header'),
    menuList = $('#responsive-nav .menu-nav .menu-list');

  catToggle.on('click', function() {
	  alert('catToggle click');
    menuList.removeClass('open');
    catList.toggleClass('open');
  });

  menuToggle.on('click', function() {
	  alert('menuToggle click');
    catList.removeClass('open');
    menuList.toggleClass('open');
  });

  $(document).click(function(event) {
    if (!$(event.target).closest(responsiveNav).length) {
      if (responsiveNav.hasClass('open')) {
    	  alert('open');
        //responsiveNav.removeClass('open');
        //$('#navigation').removeClass('shadow');
      } else {
    	  alert('not open');    	  
        if ($(event.target).closest('.nav-toggle > button').length) {
        	if (!menuList.hasClass('open')) {
          //if (!menuList.hasClass('open') && !catList.hasClass('open')) {
        	  alert('menuList open');
        	  $('.dropdown').addClass('open');
            menuList.addClass('open');
          }
          $('#navigation').addClass('shadow');
          responsiveNav.addClass('open');
        }else {
        	 alert('XXXX');
        	menuList.addClass('open');
        }
      }
    }
  });

  // HOME SLICK
  $('#home-slick').slick({
    autoplay: true,
    infinite: true,
    speed: 300,
    arrows: true,
  });
  //HOME SLICK 1
  $('#home-slick-1').slick({
    autoplay: true,
    infinite: true,
    speed: 300,
    arrows: true,
  });
  //HOME SLICK 2
  $('#home-slick-2').slick({
    autoplay: true,
    infinite: true,
    speed: 300,
    arrows: true,
  });

  // PRODUCTS SLICK
  $('#product-slick-1').slick({
    slidesToShow: 3,
    slidesToScroll: 2,
    autoplay: true,
    infinite: true,
    speed: 300,
    dots: true,
    arrows: false,
    appendDots: '.product-slick-dots-1',
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 480,
        settings: {
          dots: false,
          arrows: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
    ]
  });

  $('#product-slick-2').slick({
    slidesToShow: 3,
    slidesToScroll: 2,
    autoplay: true,
    infinite: true,
    speed: 300,
    dots: true,
    arrows: false,
    appendDots: '.product-slick-dots-2',
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 480,
        settings: {
          dots: false,
          arrows: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
    ]
  });

  // PRODUCT DETAILS SLICK
  $('#product-main-view').slick({
    infinite: true,
    speed: 300,
    dots: false,
    arrows: true,
    fade: true,
    asNavFor: '#product-view',
  });

  $('#product-view').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    centerMode: true,
    focusOnSelect: true,
    asNavFor: '#product-main-view',
  });

  // PRODUCT ZOOM
  $('#product-main-view .product-view').zoom();

  // PRICE SLIDER
  var slider = document.getElementById('price-slider');
  if (slider) {
    noUiSlider.create(slider, {
      start: [1, 10000],
      step: 500,
      connect: true,
      tooltips: [true, true],
      format: {
        to: function(value) {
          return value.toFixed(0) + '';
        },
        from: function(value) {
          return value
        }
      },
      range: {
        'min': 1,
        'max': 10000
      }
    });
    slider.noUiSlider.on('update', function( values, handle ) {
    	var value = values[handle];
    	if ( handle ) {
    		$("#price_high").val(value);
    	} else {
    		$("#price_low").val(value);
    	}
    });

  }

})(jQuery);
