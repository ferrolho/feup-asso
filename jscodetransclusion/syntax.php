<?php
/**
 * DokuWiki Plugin jscodetransclusion (Syntax Component)
 *
 * @license GPL 2 http://www.gnu.org/licenses/gpl-2.0.html
 * @author  Henrique Ferrolho <henriqueferrolho@gmail.com>
 */

// must be run within Dokuwiki
if (!defined('DOKU_INC')) die();

class syntax_plugin_jscodetransclusion extends DokuWiki_Syntax_Plugin {

    /**
     * @return string Syntax mode type
     */
    public function getType() {
        return 'substition';
    }

    /**
     * @return string Paragraph type
     */
    public function getPType() {
        return 'normal';
    }

    /**
     * @return int Sort order - Low numbers go before high numbers
     */
    public function getSort() {
        return 999;
    }

    /**
     * Connect lookup pattern to lexer.
     *
     * @param string $mode Parser mode
     */
    public function connectTo($mode) {
        $this->Lexer->addEntryPattern('<js-code src="',$mode,'plugin_jscodetransclusion');
    }

    public function postConnect() {
        $this->Lexer->addExitPattern('">','plugin_jscodetransclusion');
    }

    /**
     * Handle matches of the jscodetransclusion syntax
     *
     * @param string $match The match of the syntax
     * @param int    $state The state of the handler
     * @param int    $pos The position in the document
     * @param Doku_Handler    $handler The handler
     * @return array Data for the renderer
     */
    public function handle($match, $state, $pos, Doku_Handler &$handler){
        return $state == DOKU_LEXER_UNMATCHED ? $match : null;
    }

    /**
     * Render xhtml output or metadata
     *
     * @param string         $mode      Renderer mode (supported modes: xhtml)
     * @param Doku_Renderer  $renderer  The renderer
     * @param array          $data      The data from the handler() function
     * @return bool If rendering was successful.
     */
    public function render($mode, Doku_Renderer &$renderer, $data) {
        if($mode != 'xhtml') return false;

        if ($data) {
            $element = '<div class="js-code" data-url="' . $data . '">Loading code...</div>';

            $renderer->doc .= $element;
        }

        return true;
    }

}

// vim:ts=4:sw=4:et:
