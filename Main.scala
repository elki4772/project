sealed trait Expr
sealed trait Value extends Expr

case class Cry() extends Value
case class Happy() extends Value
case class VeryHappy() extends Value
case class Sleepy() extends Value
case class Stun() extends Value

case class ManyVals(values: List[Value]) extends Value
case class ManyExprs(exprs: List[Expr]) extends Expr
case class Plus(e1: Expr, e2: Expr) extends Expr
case class Not(e1: Expr) extends Expr
case class Count(e1: Expr, e2: Expr) extends Expr

object Interpreter {
  def eval(e: Expr): Value = e match {
    case v: Value => v
    
    case ManyExprs(exprs) =>
      val evaluatedExprs = exprs.map(eval)
      if (evaluatedExprs.contains(Cry())) {
        Cry()
      } else {
        ManyVals(evaluatedExprs)
      }
    
    case Plus(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ManyVals(vals), v) =>
          val newVals = vals.map {
            case Cry() => v
            case Stun() => if (v == Cry()) Cry() else Stun()
            case Happy() => if (v == Cry()) Cry() else Happy()
            case VeryHappy() => VeryHappy()
            case Sleepy() => if (v == Cry()) Cry() else Sleepy()
            case anyOther => anyOther
          }
          ManyVals(newVals)
        case (VeryHappy(), _) => VeryHappy()
        case (_, VeryHappy()) => VeryHappy()
        case (Cry(), v2) => v2
        case (v1, Cry()) => Cry()
        case (v1, v2) => v1
      }
      
    case Not(e1) =>
      eval(e1) match {
        case Stun() => Sleepy()
        case Sleepy() => Stun()
        case Happy() => Cry()
        case VeryHappy() => Cry()
        case Cry() => VeryHappy()
        case ManyVals(vals) =>
          if (vals.length >= 2) {
            vals.reduceLeft((acc, v) => eval(Plus(acc, v)))
          } else {
            Cry()
          }
        case _ => Cry()
      }
  }
}
