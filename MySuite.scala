class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

//**TODO: Your tests here

  test("eval-value: T.T evaluates to T.T") {
    assert(Interpreter.eval(Cry()) == Cry())
  }

  test("eval-value: :) evaluates to :)") {
    assert(Interpreter.eval(Happy()) == Happy())
  }

  /*
  test("eval-many-exprs: evaluates list of expressions to list of values") {
    val exprs = ManyExprs(List(Cry(), Happy(), VeryHappy()))
    val expected = ManyVals(List(Cry(), Happy(), VeryHappy()))
    assert(Interpreter.eval(exprs) == expected)
  }
  */

  test("eval-many-exprs-error: contains Cry evaluates to Cry") {
    val exprs = ManyExprs(List(Cry(), Happy(), VeryHappy()))
    assert(Interpreter.eval(exprs) == Cry())
  }

  test("eval-plus-stay-uwu: VeryHappy + anything evaluates to VeryHappy") {
    val plusExpr = Plus(VeryHappy(), Cry())
    assert(Interpreter.eval(plusExpr) == VeryHappy())
  }

  test("eval-plus-become-uwu: anything + VeryHappy evaluates to VeryHappy") {
    val plusExpr = Plus(Cry(), VeryHappy())
    assert(Interpreter.eval(plusExpr) == VeryHappy())
  }

  test("eval-plus-move-on: Cry + anything evaluates to the other value") {
    val plusExpr = Plus(Cry(), Happy())
    assert(Interpreter.eval(plusExpr) == Happy())
  }

  test("eval-plus-hard-day: Happy or Stun + Cry evaluates to Cry") {
    val plusExpr1 = Plus(Happy(), Cry())
    val plusExpr2 = Plus(Stun(), Cry())
    assert(Interpreter.eval(plusExpr1) == Cry())
    assert(Interpreter.eval(plusExpr2) == Cry())
  }

  test("eval-plus-meh: non-special cases") {
    val plusExpr = Plus(Happy(), Stun())
    assert(Interpreter.eval(plusExpr) == Happy())
  }

  test("eval-not-stun: Not(Stun) evaluates to Sleepy") {
    assert(Interpreter.eval(Not(Stun())) == Sleepy())
  }

  test("eval-not-sleepy: Not(Sleepy) evaluates to Stun") {
    assert(Interpreter.eval(Not(Sleepy())) == Stun())
  }

  test("eval-not-(very)-happy: Not(Happy) or Not(VeryHappy) evaluates to Cry") {
    assert(Interpreter.eval(Not(Happy())) == Cry())
    assert(Interpreter.eval(Not(VeryHappy())) == Cry())
  }

  test("eval-not-cry: Not(Cry) evaluates to VeryHappy") {
    assert(Interpreter.eval(Not(Cry())) == VeryHappy())
  }

  /*
  test("eval-not-many-values: Not of a list reduces to a single value") {
    val notExpr = Not(ManyVals(List(Cry(), Happy(), Stun(), Sleepy())))
    val expected = Sleepy()  // based on the given example
    assert(Interpreter.eval(notExpr) == expected)
  }
  */

  /*
  test("Complex sentence 1") {
    val expr = Plus(ManyVals(List(Cry(), VeryHappy(), Happy())), Not(ManyVals(List(Cry(), Stun(), Sleepy(), Happy()))))
    val expected = ManyVals(List(Happy(), VeryHappy(), Cry()))
    assert(Interpreter.eval(expr) == expected)
  }
  */

  test("Complex sentence 2") {
    val expr = Not(Plus(ManyVals(List(VeryHappy(), Stun())), Happy()))
    val expected = VeryHappy()
    assert(Interpreter.eval(expr) == expected)
  }

  test("Complex sentence 3") {
    val expr = Plus(Not(ManyVals(List(Happy(), Sleepy()))), VeryHappy())
    val expected = VeryHappy()
    assert(Interpreter.eval(expr) == expected)
  }

  test("Complex sentence 4") {
    val expr = Plus(Not(ManyVals(List(Cry(), VeryHappy()))), Not(ManyVals(List(Sleepy(), Stun()))))
    val expected = VeryHappy()
    assert(Interpreter.eval(expr) == expected)
  }

  /*
  test("Complex sentence 5") {
    val expr = Not(Plus(Cry(), VeryHappy()))
    val expected = VeryHappy()
    assert(Interpreter.eval(expr) == expected)
  }
  */
}


