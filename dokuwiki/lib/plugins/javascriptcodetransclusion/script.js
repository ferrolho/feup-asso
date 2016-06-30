/* DOKUWIKI:include_once githubAccess/underscore-min.js */
/* DOKUWIKI:include_once githubAccess/gh3.js */
/* DOKUWIKI:include_once githubAccess/git-hub-access.js */
/* DOKUWIKI:include_once highlight/highlight.pack.js */
/* DOKUWIKI:include_once iframe/appleofmyiframe.js */


jQuery(document).ready(function(){
	
	jQuery(".correct_syntax.javascript_transclusion_result").each(function(){
		var transclusion_element = this;

		var link = jQuery(this).attr('data-url');

		var split_link = link.split("github.com/"); 
		if (split_link.length < 2){
			jQuery('span', transclusion_element).html('Invalid URL');
			return;
		}

		var function_link = split_link[1].split(',');
		console.log("link: " + function_link);
		//there is a function
		if(function_link.length > 1) {
			var func_name = function_link[1];
			console.log("f_name: " + func_name);
		}

		var githubUrl = function_link[0].split('/');

		if (githubUrl.length < 4){
			jQuery('span', transclusion_element).html('Invalid GitHub URL');
			return;
		}
		
		var user = githubUrl[0];
		var repos = githubUrl[1];
		var branch = githubUrl[3];
		var filepath = "";

		if(function_link.length > 1) {
			filepath = link.split(',')[0].split(branch+'/')[1];
		} else {
			filepath = link.split(branch+'/')[1];
		}
		var configs = {
			user : user,
			repository : repos,
			branch : branch
		};

		var github = new GitHubAccess(configs);

		github.getFile(filepath, null, function (content){
			if(func_name != undefined) {
				//TODO get function code here
				//getFunctionBody(content,func_name);
			}

			jQuery('span', transclusion_element).html(content);

			jQuery('span', transclusion_element).each(function(i, block) {
				hljs.highlightBlock(block);
			});
		});
	})
});

/*function getFunctionBody(content, function_name) {
	var callFunction = function_name + "(122).toString()";
	var iframe_content = "<script>eval("+content+"); console.log("+ callFunction + ");</script>";

	console.log("funcBody");
	jQuery.iframe(iframe_content).appendTo('body');
};*/
