/**
 *
 */

function allcheck( tf ) {
   var ElementsCount = document.searchform.elements.length; // チェックボックスの数
   for( i=0 ; i<ElementsCount ; i++ ) {
      document.searchform.elements[i].checked = tf; // ON・OFFを切り替え
   }
}