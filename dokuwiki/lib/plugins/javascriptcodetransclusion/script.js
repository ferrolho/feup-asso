/* DOKUWIKI:include_once githubAccess/underscore-min.js */
/* DOKUWIKI:include_once githubAccess/gh3.js */
/* DOKUWIKI:include_once githubAccess/git-hub-access.js */

jQuery(document).ready(function(){
	jQuery(".correct_syntax.javascript_transclusion_result").each(function(){
		var transclusion_element = this;

		var link = jQuery(this).attr('data-url');

		var githubUrl = link.split("github.com/")[1].split('/');

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
			//$("textarea").text(content);
			//console.log(content);
			console.log('gg');
			jQuery(transclusion_element).text(content);

			//var myRe = new RegExp("d(b+)d", "g");
			//var myArray = myRe.exec("cdbbdbsbz");
		});
	})
});

