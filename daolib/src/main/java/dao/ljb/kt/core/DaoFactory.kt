package dao.ljb.kt.core

import dao.ljb.kt.DaoConfig

/**
 * Author:Ljb
 * Time:2018/11/9
 * There is a lot of misery in life
 **/
object DaoFactory {

    private val mDaoGroup = DaoProtocolGroup()

    private fun <T> getNewProtocol(clazz: Class<T>): T = DaoConfig.getTransform().transformProtocol(clazz)

    @Suppress("UNCHECKED_CAST")
    fun <T : IDaoInterface> getProtocol(clazz: Class<T>): T {
        return mDaoGroup.getProtocol(clazz) ?: registerNewProtocol(clazz)
    }

    private fun <T : IDaoInterface> registerNewProtocol(clazz: Class<T>): T {
        val protocol = getNewProtocol(clazz)
        mDaoGroup.register(clazz, protocol)
        return protocol
    }

}