## 20180507更新

本次更新内容：

* 1、抽取Protocol接口，解耦Presenter与Model层，Presenter仅持有Protocol接口引用；

* 2、为Model层创建静态Factory，投入Protocol接口Class对象产出对应引用；

* 3、由于Factory为静态工厂，所以需要我们手动进行接口注册，现提供了两种注册方式，详见HttpFactory\DaoFactory.

---

		object DaoFactory {
		
		    private val mDaoGroup = DaoFactoryGroup()
		
		    init {
		        //TODO 在此处注册Dao接口
		        mDaoGroup.register(IUserDao::class.java, UserDaoProtocol)
		    }
		
		    fun <T : DaoInterface> getProtocol(clazz: Class<T>): T {
		        return mDaoGroup.getProtocol(clazz)
		    }
		
		}


---

		object HttpFactory {
		
		    private val mHttpGroup = HttpFactoryGroup()
		
		    @Suppress("UNCHECKED_CAST")
		    fun <T : HttpInterface> getProtocol(clazz: Class<T>): T {
		        return mHttpGroup.getProtocol(clazz) ?: registerNewProtocol(clazz)
		    }
		
		    @Suppress("UNCHECKED_CAST")
		    private fun <T : HttpInterface> registerNewProtocol(clazz: Class<T>): T {
		        //TODO 在此处注册Http接口
		        val protocol = when (clazz) {
		            IUserHttp::class.java -> UserHttpProtocol
		            else -> throw IllegalStateException("Http Interface Class Object NotFound : ${clazz.name}")
		        } as T
		        mHttpGroup.register(clazz, protocol)
		        return protocol
		    }
		}