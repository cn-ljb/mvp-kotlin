# MVPKotlin

该项目是本人使用Kotlin语言搭建的Android MVP架构实现案例，已在部分小项目中使用，感兴趣的同学可以看看，欢迎指证不足。

>**什么是MVP架构？**

MVP架构的概念其实也不必我多说了，网上有大把大把的详解文章，这里就简单介绍下：

通常一般的Android项目结构，我们会在Activity\Fragment中编写大量代码，例如：网络请求、数据填充、页面切换等等，这种项目结构宏观的称之为MVC。

MVC：我们可以把数据源（网络请求、IO...）看作Model层，xml等布局文件看作View层，Activity\Fragment看作Controller层。但在android中xml能力太薄弱了，以至于Activity不得不做很多本不属于它的工作。

MVP：在MVP架构中Model层与MVC一样作为数据源，不过将Activity\Fragment都看作为View层的一部分负责数据的展示和填充，将Model层与View层的关联操作交给了Presenter层。

一个基本的MVP架构图如下：

![](https://i.imgur.com/08WQWqx.png)

与之前的Android MVC相比，不仅Activity的分工不明确问题得到了解决，还带来另一个好处：Model层与View层不再直接可见，耦合问题得到解决。

>**该项目MVP架构**

在此基础上，该项目中的MVP架构对每个模块进行细化，大致架构图如下：

![](https://i.imgur.com/iUFAzk3.png)

* 1、View层根据自己的需要继承对应的BaseMVPActivity\BaseMVPFragment\BaseMVPFragmentActivity，并实现createPresenter()函数，它们提供基础的View层模版；

		class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.ILoginView {
		
		    override fun createPresenter() = LoginPresenter(this)
			...
		｝	


* 2、Presenter层提供了基础的IBasePresent接口模板，考虑到整个项目使用rxjava2作为异步库，为了方便管理rx生命周期，额外提供了一个BaseRxLifePresenter抽象类；

		class LoginPresenter(mvpView: LoginContract.ILoginView) : LoginContract.ILoginPresenter(mvpView) {
			
				//rxjava生命周期管理举例
			   	override fun delayGoHomeTask() {
			        Observable.timer(1500, TimeUnit.MILLISECONDS)
			                .subscribe { getMvpView().goHome() }
			                .bindRxLife(RxLife.ON_DESTROY)
			    }
				
				//登录功能
				override fun login(userName: String) {
			        RxUtils.dispose(mLoginDisposable)
			        mLoginDisposable = UserProtocol.getUserInfoByName(userName)
			               .subscribeOn(Schedulers.io())
			                .observeOn(AndroidSchedulers.mainThread())
			                .subscribe({
			                    if (it.message.isNullOrBlank()) {
			                        LoginUser.name = it.login
			                        getMvpView().loginSuccess()
			                    } else {
			                        getMvpView().loginError(it.message)
			                    }
			                }, {
			                    getMvpView().loginError(null)
			                }).bindRxLife(RxLife.ON_DESTROY)
			    }
		｝

* 3、View层与Presenter层的交互通过接口的形式规范化行为进行解耦。例如上方的LoginActivity与LoginPresenter的交互范围都在LoginContract中进行限制；
		
		interface LoginContract {
		
		    interface ILoginView : IBaseView {
		        fun loginSuccess()
		        fun loginError(errorMsg: String?)
		        fun goHome()
		    }

		    abstract class ILoginPresenter(mvpView: ILoginView) : BaseRxLifePresenter<ILoginView>(mvpView) {
		        abstract fun login(userName: String)
		        abstract fun delayGoHomeTask()
		    }
		}

（注：由于使用了BaseRxLifePresenter是个抽象类，所以Presenter的限制行为通过abstract抽象类实现，如果不使用BaseRxLifePresenter，单独管理rx生命周期，建议统一定义为interface）

* 4、Modle层抽取出Protocol层，用于包装数据源并提供转换为Observable的函数，从而方便与Rxjava2集合使用（项目中提供两个基础的BaseDAOProtocol、BaseHttpProtocol，也可自行定义适合自己的数据源封装类）；

		object UserProtocol : BaseHttpProtocol() {
		
		    fun getUserInfoByName(userName: String): Observable<User> {
		        val url = "$HTTP_API_DOMAIN/users/${nvl(userName)}"
		        return createObservable(url, XgoHttpClient.METHOD_GET) {
		            JsonParser.fromJsonObj(it, User::class.java)
		        }
		    }
			
			...
		｝

* 5、一个View对应一个Presenter，一个Presenter可具备多个Protocol.



