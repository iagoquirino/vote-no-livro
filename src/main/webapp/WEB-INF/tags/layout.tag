<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		    <title>Vote no Livro</title>
		<link rel="styleSheet" href="<c:url value="/assets/css/normalize.css"/>">
		<link rel="styleSheet" href="<c:url value="/assets/css/demo.css"/>">
		<link rel="styleSheet" href="<c:url value="/assets/css/component_button.css"/>">
		<link rel="styleSheet" href="<c:url value="/assets/css/component_check.css"/>">
		<link rel="styleSheet" href="<c:url value="/assets/css/content.css"/>">
		<script src="<c:url value="/assets/js/modernizr.custom.js"/>"></script>		
	</head>
	<body>
		<div class="container">
			<header class="codrops-header">
				<h1>Vote no Livro</h1>
				<p>Vote no seu livro favorito e seja feliz! Não assista TV. Leia um Livro!</p>
			</header>
			<section>
				<jsp:doBody/>	
			</section>
		</div><!-- /container -->
		<script src="<c:url value="/assets/js/classie.js"/>"></script>
		<script src="<c:url value="/assets/js/uiMorphingButton_fixed.js"/>"></script>
		<script>
			(function() {
				var docElem = window.document.documentElement, didScroll, scrollPosition;

				// trick to prevent scrolling when opening/closing button
				function noScrollFn() {
					window.scrollTo( scrollPosition ? scrollPosition.x : 0, scrollPosition ? scrollPosition.y : 0 );
				}

				function noScroll() {
					window.removeEventListener( 'scroll', scrollHandler );
					window.addEventListener( 'scroll', noScrollFn );
				}

				function scrollFn() {
					window.addEventListener( 'scroll', scrollHandler );
				}

				function canScroll() {
					window.removeEventListener( 'scroll', noScrollFn );
					scrollFn();
				}

				function scrollHandler() {
					if( !didScroll ) {
						didScroll = true;
						setTimeout( function() { scrollPage(); }, 60 );
					}
				};

				function scrollPage() {
					scrollPosition = { x : window.pageXOffset || docElem.scrollLeft, y : window.pageYOffset || docElem.scrollTop };
					didScroll = false;
				};

				scrollFn();

				[].slice.call( document.querySelectorAll( '.morph-button' ) ).forEach( function( bttn ) {
					new UIMorphingButton( bttn, {
						closeEl : '.icon-close',
						onBeforeOpen : function() {
							// don't allow to scroll
							noScroll();
						},
						onAfterOpen : function() {
							// can scroll again
							canScroll();
						},
						onBeforeClose : function() {
							// don't allow to scroll
							noScroll();
						},
						onAfterClose : function() {
							// can scroll again
							canScroll();
						}
					} );
				} );

				// for demo purposes only
				[].slice.call( document.querySelectorAll( 'form button' ) ).forEach( function( bttn ) { 
					bttn.addEventListener( 'click', function( ev ) { ev.preventDefault(); } );
				} );
			})();
		</script>
		<script src="<c:url value="/assets/js/svgcheckbx.js"/>"></script>
	</body>
</html>