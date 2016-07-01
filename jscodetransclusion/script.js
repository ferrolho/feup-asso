jQuery(document).ready(function() {

	jQuery(".js-code").each(function() {

		var thisElement = this;

		var link = jQuery(thisElement).attr('data-url');

		jQuery.ajax({
			url: link,
			success: function(data) {
				jQuery(thisElement).html(data);
			}
		});

	});

});
