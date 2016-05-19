/* DOKUWIKI:include_once githubAccess/underscore-min.js */
/* DOKUWIKI:include_once githubAccess/gh3.js */
/* DOKUWIKI:include_once githubAccess/git-hub-access.js */

var configs = {
	user : 'arop',
	repository : 'SINF',
	branch : 'master'
};

var github = new GitHubAccess(configs);

github.getFile('site/app/views/cart/index.html.erb', null, function (content){
	//$("textarea").text(content);
	console.log(content);
});