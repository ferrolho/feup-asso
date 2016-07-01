/* DOKUWIKI:include_once highlight/highlight.pack.js */

jQuery(document).ready(function() {

	jQuery(".js-code").each(function() {
		var thisElement = this;

		var rawLink = jQuery(thisElement).attr('data-url');

		var linkTokens = rawLink.split('@');

		var link = linkTokens[0];
		var methodName = linkTokens[1];

		if (methodName) {
			loadScript(link, function() {
				jQuery(thisElement).text(window[methodName].toString());
				hljs.highlightBlock(thisElement);
			});
		} else {
			jQuery.ajax({
				url: link,
				success: function(data) {
					jQuery(thisElement).text(data);
					hljs.highlightBlock(thisElement);
				}
			});
		}
	});

});

function loadScript(url, callback) {
    // Adding the script tag to the head as suggested before
    var head = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = url;

    // Then bind the event to the callback function.
    // There are several events for cross browser compatibility.
    script.onreadystatechange = callback;
    script.onload = callback;

    // Fire the loading
    head.appendChild(script);
}
