/* DOKUWIKI:include_once githubAccess/underscore-min.js */
/* DOKUWIKI:include_once githubAccess/gh3.js */
/* DOKUWIKI:include_once githubAccess/git-hub-access.js */
/* DOKUWIKI:include_once highlight/highlight.pack.js */

jQuery(document).ready(function(){
	
	jQuery(".correct_syntax.javascript_transclusion_result").each(function(){
		var transclusion_element = this;

		var link = jQuery(this).attr('data-url');

		var split_link = link.split("github.com/"); 
		if (split_link.length < 2){
			jQuery('span', transclusion_element).html('Invalid URL');
			return;
		}

		var githubUrl = split_link[1].split('/');

		if (githubUrl.length < 4){
			jQuery('span', transclusion_element).html('Invalid GitHub URL');
			return;
		}
		
		var user = githubUrl[0];
		var repos = githubUrl[1];
		var branch = githubUrl[3];

		var filepath = link.split(branch+'/')[1];

		var configs = {
			user : user,
			repository : repos,
			branch : branch
		};

		var github = new GitHubAccess(configs);

		github.getFile(filepath, null, function (content){
			jQuery('span', transclusion_element).html(content);

			jQuery('span', transclusion_element).each(function(i, block) {
			    hljs.highlightBlock(block);
			});
		});
	})
});

