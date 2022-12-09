import org.gradle.api.artifacts.dsl.DependencyHandler
import java.util.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

enum class Type {
    IMPLEMENTATION,
    DEBUG,
    TEST,
    ANDROID_TEST,
    KAPT
}

fun DependencyHandler.implements(clazz: Any, type: Type = Type.IMPLEMENTATION) {
    clazz::class.declaredMemberProperties.forEach field@{
        if (!it.isConst) return@field
        it.isAccessible = true
        val value = it.getter.call()
        if (value !is String) return@field
        when (type) {
            Type.IMPLEMENTATION -> add("implementation", value)
            Type.DEBUG -> add("debugImplementation", value)
            Type.TEST -> add("testImplementation", value)
            Type.ANDROID_TEST -> add("androidTestImplementation", value)
            Type.KAPT -> add("kapt", value)
        }
    }
}


inline fun<reified T: Any> T.getClassFields(block: (type: String, name: String, value: String) -> Unit) {
    this::class.declaredMemberProperties.forEach field@{
        if (!it.isConst) return@field
        it.isAccessible = true
        val value = it.getter.call()
        if (value is String)
            block("String", it.name.toUpperCase(Locale.ROOT), "\"$value\"")
        if (value is Int)
            block("Integer", it.name.toUpperCase(Locale.ROOT), value.toString())
        if (value is Boolean)
            block("boolean", it.name.toUpperCase(Locale.ROOT), value.toString())
    }
}