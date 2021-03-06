// !LANGUAGE: +InlineClasses

inline class Foo(val value: Int)

fun <T> id(x: T): T = x
inline fun <T> inlinedId(x: T): T = x

fun <T> T.idExtension(): T = this
inline fun <T> T.inlinedIdExtension(): T = this

fun test(f: Foo) {
    inlinedId(f) // box
    inlinedId(f).idExtension() // box

    f.inlinedIdExtension() // box

    val a = inlinedId(f).idExtension() // box unbox
    val b = inlinedId(f).inlinedIdExtension() // box unbox
}

fun box(): String {
    val f = Foo(11)

    id(inlinedId(f))
    inlinedId(id(f))

    inlinedId(f) // box
    inlinedId(f).idExtension() // box

    f.inlinedIdExtension() // box

    val a = inlinedId(f).idExtension() // box unbox
    val b = inlinedId(f).inlinedIdExtension() // box unbox

    if (a.value != 11) return "fail"
    if (b.value != 11) return "fail"

    if (inlinedId(Foo(10)).value != 10) return "fail"
    if (Foo(20).inlinedIdExtension().value != 20) return "fail"

    return "OK"

}