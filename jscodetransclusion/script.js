/* DOKUWIKI:include_once highlight/highlight.pack.js */

jQuery(document).ready(function() {

    jQuery(".js-code").each(function() {
        var thisElement = this;

        var rawLink = jQuery(thisElement).attr('data-url');
        var error = false;

        var linkTokens = rawLink.split('@');

        var link = linkTokens[0];
        var methodName = linkTokens[1];

        var link_split = link.split('github.com');
        if(link_split.length > 1){
            link = 'https://raw.githubusercontent.com'+link_split[1];
            link_split = link.split('blob/');
            if (link_split.length < 2) 
                error = true;
            else{
                link = link_split[0] + link_split[1];
                
            }
            
        }
        else{
            link_split = link.split('raw.githubusercontent.com');
            if (link_split.length < 2)
                error = true;
            // link remains the same as it's already raw github content
        }

        if(error)
            badGitHubLink(thisElement);
        else{
            jQuery.ajax({
                url: link,
                success: function(data) {
                    if (methodName) {
                        var method_string = getMethodFromFileString(data, methodName);
                        if(method_string == null)
                            method_string = 'Method not found...';
                        jQuery(thisElement).text(method_string);
                    }
                    else{
                        jQuery(thisElement).text(data);
                    }
                    hljs.highlightBlock(thisElement);
                }
            });
        }   

    });

});

function badGitHubLink(elem){
    jQuery(elem).text('Bad GitHub link...');
}

function getMethodFromFileString(fileString, methodName){

    var regex_string = 'function(\\s)+'+methodName+'(\\s)*\\([A-Za-z\\s_0-9,]*\\)(\\s)*';
    var re = new RegExp(regex_string, "g");

    var method_start_index = fileString.search(re); 
    if(method_start_index == -1)
        return null;

    var method_body_start_index = fileString.indexOf('{', method_start_index);

    var current_char;
    var i = method_body_start_index+1; //skipping the first char: '{'
    var open_curlies = 1; // number of 'openning' curly brackets not closed
    var single_quote_opened = false;
    var double_quote_opened = false;
    var single_line_comment_opened = false;
    var multi_line_comment_opened = false;

    for( ; i < fileString.length; i++){
        if(open_curlies == 0)
            break;
        current_char = fileString.charAt(i);
        switch(current_char){
            case '\'' : 
                if(double_quote_opened || single_line_comment_opened || multi_line_comment_opened) 
                    break; // if double quote is opened, this char (') is part of the string
                if(single_quote_opened && fileString.charAt(i-1) != '\\') 
                    single_quote_opened = false;
                else single_quote_opened = true;
                break;

            case '"' : 
                if(single_quote_opened || single_line_comment_opened || multi_line_comment_opened) 
                    break; // if single quote is opened, this char (") is part of the string
                if(double_quote_opened && fileString.charAt(i-1) != '\\') 
                    double_quote_opened = false;
                else double_quote_opened = true;
                break;

            case '{' : 
                if(single_quote_opened || double_quote_opened || single_line_comment_opened || multi_line_comment_opened) 
                    break; //if inside a string, ignore curly brackets
                open_curlies++;
                break;

            case '}' : 
                if(single_quote_opened || double_quote_opened || single_line_comment_opened || multi_line_comment_opened) 
                    break; //if inside a string, ignore curly brackets
                open_curlies--;
                break;

            case '/' :
                if(single_quote_opened || double_quote_opened)
                    break; 
                if(fileString.charAt(i-1) == '/'){
                    single_line_comment_opened = true;
                }
                else if (fileString.charAt(i+1) == '*'){
                    multi_line_comment_opened = true;
                    i++;
                }
                break;

            case '\n' :
                if(single_line_comment_opened)
                    single_line_comment_opened = false;
                break;

            case '*' : 
                if(multi_line_comment_opened && fileString.charAt(i+1) == '/'){
                    multi_line_comment_opened = false;
                    i++;
                }
                break;

            default: break;
        }

    };
    return fileString.substring(method_start_index, i);
};
