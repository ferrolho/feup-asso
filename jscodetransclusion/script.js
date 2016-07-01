/* DOKUWIKI:include_once highlight/highlight.pack.js */

jQuery(document).ready(function() {

	jQuery(".js-code").each(function() {
		var thisElement = this;

		var rawLink = jQuery(thisElement).attr('data-url');

		var linkTokens = rawLink.split('@');

		var link = linkTokens[0];
		var methodName = linkTokens[1];

		jQuery.ajax({
			url: link,
			success: function(data) {
				if (methodName) {
					jQuery(thisElement).text(getMethodFromFileString(data, methodName));
				}
				else{
					jQuery(thisElement).text(data);
				}
				hljs.highlightBlock(thisElement);
			}
		});

	});

});

function getMethodFromFileString(fileString, methodName){

    var regex_string = 'function(\\s)+'+methodName+'(\\s)*\\([A-Za-z\\s_0-9,]*\\)(\\s)*';
    var re = new RegExp(regex_string, "g");

    var method_start_index = fileString.search(re); 
    var method_body_start_index = fileString.indexOf('{', method_start_index);

    var stack = [];
    var current_char;
    var i = method_body_start_index+1; //skipping the first char: '{'
    var open_curlies = 1; // number of 'openning' curly brackets not closed
    var single_quote_opened = false;
    var double_quote_opened = false;

    for( ; i < fileString.length; i++){
        if(open_curlies == 0)
            break;
        current_char = fileString.charAt(i);
        switch(current_char){
            case '\'' : 
                if(double_quote_opened) break; // if double quote is opened, this char (') is part of the string
                if(single_quote_opened && fileString.charAt(i-1) != '\\') 
                    single_quote_opened = false;
                else single_quote_opened = true;
                break;

            case '"' : 
                if(single_quote_opened) break; // if single quote is opened, this char (") is part of the string
                if(double_quote_opened && fileString.charAt(i-1) != '\\') 
                    double_quote_opened = false;
                else double_quote_opened = true;
                break;

            case '{' : 
                if(single_quote_opened || double_quote_opened) break; //if inside a string, ignore curly brackets
                open_curlies++;
                break;

            case '}' : 
                if(single_quote_opened || double_quote_opened) break; //if inside a string, ignore curly brackets
                open_curlies--;
                break;

            default: break;
        }

    };
    return fileString.substring(method_start_index, i);

};
