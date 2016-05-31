/* DOKUWIKI:include_once githubAccess/underscore-min.js */
/* DOKUWIKI:include_once githubAccess/gh3.js */
/* DOKUWIKI:include_once githubAccess/git-hub-access.js */

jQuery(document).ready(function(){
	jQuery(".correct_syntax.javascript_transclusion_result").each(function(){
		var transclusion_element = this;
		var user = jQuery(this).attr('data-user');
		var project = jQuery(this).attr('data-project');
		var filepath = jQuery(this).attr('data-filepath');
		var method = jQuery(this).attr('data-method');

		var configs = {
			user : user,
			repository : project,
			branch : 'master'
		};

		var github = new GitHubAccess(configs);

		github.getFile(filepath, null, function (content){
			//$("textarea").text(content);
			//console.log(content);
			//console.log('gg');
			jQuery(transclusion_element).text(content);
		});
		
	})
});

