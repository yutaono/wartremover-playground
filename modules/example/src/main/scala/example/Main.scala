package example

object Main extends App {

  val a: Int = Option(1).fold(0)((b: Int) => b)

  println(a)

}
