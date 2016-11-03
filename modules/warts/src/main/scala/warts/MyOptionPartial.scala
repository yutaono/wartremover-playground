package warts

import org.wartremover._

object MyOptionPartial extends WartTraverser {

  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val optionSymbol = rootMirror.staticClass("scala.Option")
    val FoldName: TermName = "fold"
    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, FoldName) if left.tpe.baseType(optionSymbol) != NoType =>
            u.error(tree.pos, "Option#fold is disabled - use Option#getOrElse and Option#map instead")
          // TODO: This ignores a lot
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree)=>
          case _ =>
            super.traverse(tree)
        }
      }
    }
  }
}
