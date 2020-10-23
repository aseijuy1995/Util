package edu.yujie.networkconnectutil.pattern

/**
 * @author YuJie on 2020/7/9
 * @describe 封裝有參數的單例模式
 * @constructor SingletonHolder<T, A>(creator:(A)->T)
 * @param T 初始化類別
 * @param A 初始化所需參數
 * @param creator 初始化器
 */

open class SingletonProperty<T : Any, A>(private val creator: (A) -> T) {
    @Volatile
    private var sInstance: T? = null

    fun get(arg: A): T =
        sInstance ?: synchronized(this) {
            sInstance ?: creator(arg).also {
                sInstance = it
            }
        }
}